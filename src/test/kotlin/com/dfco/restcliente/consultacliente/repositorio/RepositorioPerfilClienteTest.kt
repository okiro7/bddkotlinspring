package com.dfco.restcliente.consultacliente.repositorio

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
internal class RepositorioPerfilClienteTest {

   // lateinit var perfil: PerfilCliente

    lateinit var id: UUID

    @Autowired
    lateinit var  repositorioPerfilCliente: RepositorioPerfilCliente

    @Before
    fun setUp() {
        id = UUID.randomUUID()
    }

    @Test
    fun dadoQueGuardoUnRegistroEntoncesPuedoRecuperarlo() {
        repositorioPerfilCliente.save(PerfilCliente(id = id, nombre = "David",fechaNacimiento = LocalDate.of(1976, 2, 28),email = "dfc@se.com",telefono = "123456789"))
    }
}