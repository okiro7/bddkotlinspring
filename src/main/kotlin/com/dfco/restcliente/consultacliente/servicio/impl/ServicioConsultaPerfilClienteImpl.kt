package com.dfco.restcliente.consultacliente.servicio.impl

import com.dfco.restcliente.consultacliente.shared.ConversorPerfilCliente
import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.ServicioConsultaPerfilCliente
import com.dfco.restcliente.consultacliente.vo.RespuestaPerfilCliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServicioConsultaPerfilClienteImpl (@Autowired val repositorioPerfilCliente: RepositorioPerfilCliente , @Autowired val conversor : ConversorPerfilCliente) : ServicioConsultaPerfilCliente {

    override fun consultar(idCliente: UUID): RespuestaPerfilCliente {
        val perfilCliente = repositorioPerfilCliente.findById(idCliente)
        return conversor.convert(perfilCliente.orElse(null));
    }
}