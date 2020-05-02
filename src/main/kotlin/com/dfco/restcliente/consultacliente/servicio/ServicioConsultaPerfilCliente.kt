package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.vo.RespuestaPerfilCliente
import java.util.*

interface ServicioConsultaPerfilCliente {
    fun consultar( idCliente: UUID) : RespuestaPerfilCliente
}