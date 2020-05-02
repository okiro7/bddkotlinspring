package com.dfco.restcliente.consultacliente.servicio.impl

import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.ServicioEventoClienteCreado
import com.dfco.restcliente.consultacliente.servicio.vo.EventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.ConversorEventoToPerfilCliente
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class ServicioEventoClienteCreadoImpl(@Autowired val repositorioPerfilCliente: RepositorioPerfilCliente, @Autowired val conversor: ConversorEventoToPerfilCliente)  : ServicioEventoClienteCreado {

    override fun registrar(evento: EventoClienteCreado):UUID {
        val perfilCliente = conversor.convert(evento)
        return repositorioPerfilCliente.save(perfilCliente)
    }


}