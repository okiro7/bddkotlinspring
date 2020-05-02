package com.dfco.restcliente.consultacliente.modelo

import java.time.LocalDate
import java.util.*

class PerfilCliente(
    val id: UUID?,
    val nombre: String?,
    val fechaNacimiento: LocalDate?,
    val email: String?,
    val telefono: String?) {

        data class Builder(
                var id: UUID? = null,
                var nombre: String? = null,
                var fechaNacimiento: LocalDate? = null,
                var email: String? = null,
                var telefono: String? = null
        ){
            fun id(id: UUID) = apply { this.id = id }
            fun nombre(nombre: String) = apply { this.nombre = nombre }
            fun fechaNacimiento(fechaNacimiento: LocalDate) = apply { this.fechaNacimiento = fechaNacimiento }
            fun email(email: String) = apply { this.email = email }
            fun telefono(telefono: String) = apply { this.telefono = telefono }
            fun build() = PerfilCliente(id, nombre, fechaNacimiento, email,telefono)
        }
}