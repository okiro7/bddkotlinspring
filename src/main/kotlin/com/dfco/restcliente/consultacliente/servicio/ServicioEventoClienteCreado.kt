package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado

interface ServicioEventoClienteCreado {
    fun registrar(evento : EventoClienteCreado)
}