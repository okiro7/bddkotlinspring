package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.vo.EventoClienteCreado
import java.util.*

interface ServicioEventoClienteCreado {
    fun registrar(evento : EventoClienteCreado)
}