package com.dfco.restcliente.consultacliente.servicio.impl

import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.ServicioEventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.ConversorEventoToPerfilCliente
import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServicioEventoClienteCreadoImpl @Autowired constructor(private val repositorioPerfilCliente: RepositorioPerfilCliente,private val conversor: ConversorEventoToPerfilCliente)  : ServicioEventoClienteCreado {

    private val log = LoggerFactory.getLogger(ServicioEventoClienteCreadoImpl::class.java)

    override fun registrar(evento: EventoClienteCreado) {
        log.info("Registrando evento: {}" ,evento)
        val perfilCliente = conversor.convert(evento)
         repositorioPerfilCliente.save(perfilCliente)
    }
}