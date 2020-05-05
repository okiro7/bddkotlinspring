package com.dfco.restcliente.consultacliente.bus

import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado
import com.dfco.restcliente.consultacliente.servicio.ServicioEventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.APPConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class ConsumidorEventoClienteCreado(@Autowired val servicio: ServicioEventoClienteCreado) {

    @KafkaListener(topics = [APPConstants.TOPIC], groupId = "bdd-spring-boot")
    fun procesarEvento(ack: Acknowledgment, @Payload evento: EventoClienteCreado) {
        servicio.registrar(evento)
        ack.acknowledge()
    }
}