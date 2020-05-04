package com.dfco.restcliente.consultacliente.bus

import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.kafka.support.serializer.JsonDeserializer


class EventoClienteCreadoDeserializer : JsonDeserializer<EventoClienteCreado?>() {
    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {
        objectMapper.registerModule(JavaTimeModule())
        super.configure(configs, isKey)
    }
}