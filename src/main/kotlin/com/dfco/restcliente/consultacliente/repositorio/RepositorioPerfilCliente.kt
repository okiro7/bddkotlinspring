package com.dfco.restcliente.consultacliente.repositorio

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RepositorioPerfilCliente {
    fun findById(id: UUID): PerfilCliente
}