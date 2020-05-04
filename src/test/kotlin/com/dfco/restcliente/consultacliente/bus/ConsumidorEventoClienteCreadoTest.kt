package com.dfco.restcliente.consultacliente.bus

import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado
import com.dfco.restcliente.consultacliente.servicio.ServicioEventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.APPConstants
import org.apache.kafka.clients.producer.ProducerConfig
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.rule.KafkaEmbedded
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@EmbeddedKafka(topics = [APPConstants.TOPIC], brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "auto.create.topics.enable=false"])
@EnableAutoConfiguration(exclude = [KafkaAutoConfiguration::class])
@ConditionalOnMissingBean(name = ["kafkaListenerContainerFactory"])
class ConsumidorEventoClienteCreadoIT {
    @TestConfiguration
    class TestConfig {
        @Bean
        fun producerFactory(kafkaEmbedded: KafkaEmbedded): ProducerFactory<UUID, EventoClienteCreado> {
            val props = KafkaTestUtils.producerProps(kafkaEmbedded)
            props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = UuidSerializer::class.java
            props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = EventoClienteCreadoSerializer::class.java
            props[JsonSerializer.ADD_TYPE_INFO_HEADERS] = false
            return DefaultKafkaProducerFactory(props)
        }

        @Bean
        fun kafkaTemplate(producerFactory: ProducerFactory<UUID, EventoClienteCreado>): KafkaTemplate<UUID, EventoClienteCreado> {
            val kafkaTemplate = KafkaTemplate(producerFactory)
            kafkaTemplate.defaultTopic = APPConstants.TOPIC
            return kafkaTemplate
        }
    }

    @MockBean
    lateinit var  servicio: ServicioEventoClienteCreado

    @Autowired
    lateinit var kafkaEmbedded: KafkaEmbedded

    @Autowired
    lateinit var  kafkaListenerEndpointRegistry: KafkaListenerEndpointRegistry

    @Autowired
    lateinit var  kafkaTemplate: KafkaTemplate<UUID, EventoClienteCreado>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        for (messageListenerContainer in kafkaListenerEndpointRegistry!!.listenerContainers) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, kafkaEmbedded!!.partitionsPerTopic)
        }
    }

    @Test
    fun dadoEventoClienteCreadoEntoncesRegistraPerfilCliente() {
        // Given
        val id = UUID.randomUUID()
        val evento: EventoClienteCreado = EventoClienteCreado.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("dgarciagil@autentia.com")
                .telefono("+34 123456789")
                .build()

        // When
        kafkaTemplate.send(APPConstants.TOPIC, id, evento)

        // Then
        val eventoCaptor = ArgumentCaptor.forClass(EventoClienteCreado::class.java)
        Mockito.verify(servicio, Mockito.timeout(10000)).registrar(eventoCaptor.capture())
        val result = eventoCaptor.value
        Assert.assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        Assert.assertThat(result.id, Matchers.`is`(id))
        Assert.assertThat(result.nombre, Matchers.`is`("David"))
        Assert.assertThat(result.fechaNacimiento, Matchers.`is`(LocalDate.of(1976, 2, 28)))
        Assert.assertThat(result.email, Matchers.`is`("dgarciagil@autentia.com"))
        Assert.assertThat(result.telefono, Matchers.`is`("+34 123456789"))
    }
}