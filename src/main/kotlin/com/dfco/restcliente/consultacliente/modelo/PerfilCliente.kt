package com.dfco.restcliente.consultacliente.modelo

import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PerfilCliente constructor(
        @Id val id: UUID? = null,
        val nombre: String? = null,
        val fechaNacimiento: LocalDate? = null,
        val email: String? = null,
        val telefono: String? = null){

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