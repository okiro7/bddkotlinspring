package com.dfco.restcliente.consultacliente.shared

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.servicio.vo.EventoClienteCreado
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
public class ConversorEventoToPerfilCliente: Converter<EventoClienteCreado, PerfilCliente> {

    override fun convert(eventoClienteCreado: EventoClienteCreado): PerfilCliente {
        return    PerfilCliente.Builder()
                .id(id = eventoClienteCreado.id!!)
                .nombre(eventoClienteCreado.nombre!!)
                .fechaNacimiento(eventoClienteCreado.fechaNacimiento!!)
                .email(eventoClienteCreado.email!!)
                .telefono(eventoClienteCreado.telefono!!)
                .build()
    }
}