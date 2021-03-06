package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.impl.ServicioConsultaPerfilClienteImpl
import com.dfco.restcliente.consultacliente.shared.ConversorPerfilCliente
import com.dfco.restcliente.consultacliente.vo.RespuestaPerfilCliente
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDate
import java.util.*

internal class ServicioConsultaPerfilClienteTest {

    lateinit var perfil: PerfilCliente
    lateinit var id: UUID
    lateinit var respuestaPerfilCliente: RespuestaPerfilCliente
    @MockBean
    lateinit var servicio: ServicioConsultaPerfilCliente

    @BeforeEach
    fun setUp() {
        id = UUID.randomUUID()
        perfil = PerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("diegodfco.com")
                .telefono("+34 123456789")
                .build()
        respuestaPerfilCliente = RespuestaPerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("diegodfco@dfco.com")
                .telefono("+34 123456789")
                .build()

    }

    @Test
    fun dadoIdClienteExistenteEntoncesDevuelvePerfilCliente() {
        // Given
        val mockRepository = Mockito.mock(RepositorioPerfilCliente::class.java)
        val conversorPerfilCliente = Mockito.mock(ConversorPerfilCliente::class.java)
        Mockito.`when`(mockRepository.findById(id)).thenReturn(Optional.of(perfil))
        Mockito.`when`(conversorPerfilCliente.convert(perfil)).thenReturn(respuestaPerfilCliente)
        val sut = ServicioConsultaPerfilClienteImpl(mockRepository, conversorPerfilCliente)

        // When
        val result: RespuestaPerfilCliente = sut.consultar(id)

        // Then
        assertThat(result.id, `is`(id))
        verify(mockRepository).findById(perfil.id!!)
        assertThat(perfil.id, `is`(id))
    }
}