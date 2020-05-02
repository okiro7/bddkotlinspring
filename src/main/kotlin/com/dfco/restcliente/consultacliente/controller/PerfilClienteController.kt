package com.dfco.restcliente.consultacliente.controller

import com.dfco.restcliente.consultacliente.servicio.ServicioConsultaPerfilCliente
import com.dfco.restcliente.consultacliente.vo.RespuestaPerfilCliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class PerfilClienteController (@Autowired val servicio :ServicioConsultaPerfilCliente) {

    @GetMapping(value = ["/cliente/perfil/{id}"])
    operator fun get(@PathVariable id: String): RespuestaPerfilCliente {
        val result = servicio.consultar(UUID.fromString(id))
        return result
    }
}