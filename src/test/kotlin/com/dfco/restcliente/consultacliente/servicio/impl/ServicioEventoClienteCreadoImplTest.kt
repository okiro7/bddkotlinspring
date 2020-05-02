package com.dfco.restcliente.consultacliente.servicio.impl

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.vo.EventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.ConversorEventoToPerfilCliente
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate
import java.util.*

internal class ServicioEventoClienteCreadoImplTest {


    lateinit var perfil: PerfilCliente
    lateinit var id: UUID
    lateinit var eventoClienteCreado: EventoClienteCreado

    @BeforeEach
    fun setUp() {
        id = UUID.randomUUID()
        perfil = PerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("dgarciagil@autentia.com")
                .telefono("+34 123456789")
                .build()
        eventoClienteCreado = EventoClienteCreado.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("dgarciagil@autentia.com")
                .telefono("+34 123456789")
                .build()

    }

    @Test
    fun registrar() {
        //Given
        val mockRepository = Mockito.mock(RepositorioPerfilCliente::class.java)
        val conversorEventoToPerfilCliente = Mockito.mock(ConversorEventoToPerfilCliente::class.java)
        Mockito.`when`(mockRepository.save(perfil)).thenReturn(id)
        Mockito.`when`(conversorEventoToPerfilCliente.convert(eventoClienteCreado)).thenReturn(perfil)
        val sut = ServicioEventoClienteCreadoImpl(mockRepository, conversorEventoToPerfilCliente)

        // When
        val result = sut.registrar(eventoClienteCreado)

        // Then
        MatcherAssert.assertThat(result, Matchers.`is`(id))
        Mockito.verify(mockRepository).save(perfil)
        MatcherAssert.assertThat(mockRepository.save(perfil), Matchers.`is`(id))
    }
}