package com.dfco.restcliente.consultacliente.servicio.impl

import com.dfco.restcliente.consultacliente.servicio.ServicioConsultaPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.vo.RespuestaPerfilCliente
import java.time.LocalDate
import java.util.*


class ServicioConsultaPerfilClienteImpl : ServicioConsultaPerfilCliente {
    override fun consultar(idCliente: UUID): RespuestaPerfilCliente {
        return RespuestaPerfilCliente.Builder()
                .id(idCliente)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("dgarciagil@autentia.com")
                .telefono("+34 123456789")
                .build()
    }
}