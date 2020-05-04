package com.dfco.restcliente.consultacliente.controller

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.servicio.ServicioConsultaPerfilCliente
import com.dfco.restcliente.consultacliente.vo.RespuestaPerfilCliente
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.doReturn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PerfilClienteControllerTest {

    lateinit var id: UUID
    lateinit var respuestaPerfilCliente: RespuestaPerfilCliente

    @MockBean
    lateinit var servicio: ServicioConsultaPerfilCliente

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Before
    fun setUp() {
        id = UUID.randomUUID()
        respuestaPerfilCliente = RespuestaPerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("diegodfco@dfco.com")
                .telefono("+34 123456789")
                .build()
    }

    @Test
    fun dadoIdClienteExistenteEntoncesDevuelvePerfilCliente() {

        // Given
        doReturn(respuestaPerfilCliente).`when`(servicio).consultar(id)

        // When
        val result = restTemplate.getForEntity("/cliente/perfil/{id}", RespuestaPerfilCliente::class.java, id)

        // Then
        assertThat(result.getStatusCode(), `is`(HttpStatus.OK))
        val respuesta: RespuestaPerfilCliente? = result.getBody()
        assertThat(respuesta?.id, `is`(id))
    }
}