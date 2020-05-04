package com.dfco.restcliente.consultacliente.servicio.impl

import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.ServicioEventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.ConversorEventoToPerfilCliente
import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired

class ServicioEventoClienteCreadoImpl @Autowired constructor(private val repositorioPerfilCliente: RepositorioPerfilCliente,private val conversor: ConversorEventoToPerfilCliente, private val log: Logger)  : ServicioEventoClienteCreado {

    override fun registrar(evento: EventoClienteCreado) {
        log.info("Registrando evento: {}" ,evento)
        val perfilCliente = conversor.convert(evento)
         repositorioPerfilCliente.save(perfilCliente)
    }
}