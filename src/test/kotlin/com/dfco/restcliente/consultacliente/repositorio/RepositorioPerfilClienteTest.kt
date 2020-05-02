package com.dfco.restcliente.consultacliente.repositorio

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
internal class RepositorioPerfilClienteTest {

    lateinit var perfil: PerfilCliente

    lateinit var id: UUID

    @Autowired
    lateinit var repositorioPerfilCliente: RepositorioPerfilCliente

    @Before
    fun setUp() {
        id = UUID.randomUUID()
        perfil = PerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("diego@dfco.com")
                .telefono("+34 123456789")
                .build()
    }

    @Test
    fun dadoQueGuardoUnRegistroEntoncesPuedoRecuperarlo() {

        // When
        repositorioPerfilCliente.save(perfil)
        val result = repositorioPerfilCliente.findById(id)

        // Then
        val perfilCliente = result.orElse(null)
        assertThat(perfilCliente.id, `is`(id))
        assertThat(perfilCliente.nombre, `is`("David"))
    }
}