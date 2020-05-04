package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.impl.ServicioEventoClienteCreadoImpl
import com.dfco.restcliente.consultacliente.evento.EventoClienteCreado
import com.dfco.restcliente.consultacliente.shared.ConversorEventoToPerfilCliente
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.slf4j.Logger
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
                .email("diego@dfco.com")
                .telefono("+34 123456789")
                .build()
        eventoClienteCreado = EventoClienteCreado.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("diego@dfco.com")
                .telefono("+34 123456789")
                .build()

    }

    @Test
    fun registrar() {
        //Given
        val mockRepository = Mockito.mock(RepositorioPerfilCliente::class.java)
        val conversorEventoToPerfilCliente = Mockito.mock(ConversorEventoToPerfilCliente::class.java)
        val logger = Mockito.mock(Logger::class.java)
        Mockito.`when`(mockRepository.save(perfil)).thenReturn(perfil)
        Mockito.`when`(conversorEventoToPerfilCliente.convert(eventoClienteCreado)).thenReturn(perfil)
        val sut = ServicioEventoClienteCreadoImpl(mockRepository, conversorEventoToPerfilCliente,logger)

        // When
        sut.registrar(eventoClienteCreado)

        // Then
        Mockito.verify(mockRepository).save(perfil)
    }
}