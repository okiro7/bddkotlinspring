package com.dfco.restcliente.consultacliente.shared

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.vo.RespuestaPerfilCliente
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class ConversorPerfilCliente : Converter<PerfilCliente, RespuestaPerfilCliente>{

    override fun convert(perfilCliente: PerfilCliente): RespuestaPerfilCliente {
       return    RespuestaPerfilCliente.Builder()
               .id(id = perfilCliente.id!!)
               .nombre(perfilCliente.nombre!!)
               .fechaNacimiento(perfilCliente.fechaNacimiento!!)
               .email(perfilCliente.email!!)
               .telefono(perfilCliente.telefono!!)
               .build()
    }
}