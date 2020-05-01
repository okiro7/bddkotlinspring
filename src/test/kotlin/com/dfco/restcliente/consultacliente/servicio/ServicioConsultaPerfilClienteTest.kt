package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.servicio.impl.ServicioConsultaPerfilClienteImpl
import com.dfco.restcliente.consultacliente.servicio.vo.RespuestaPerfilCliente
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import java.util.*


class ServicioConsultaPerfilClienteTest{

    @InjectMocks
    private lateinit var sut: ServicioConsultaPerfilClienteImpl

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun dadoIdClienteExistenteEntoncesDevuelvePerfilCliente() {
        // Given
        val id = UUID.randomUUID()

        // When
        val result: RespuestaPerfilCliente = sut.consultar(id)

        // Then
        assertThat(result, `is`(not(nullValue())))
        assertThat(result.id, `is`(id))
        assertThat(result.nombre, `is`("David"))
        assertThat(result.fechaNacimiento, `is`(LocalDate.of(1976, 2, 28)))
        assertThat(result.email, `is`("dgarciagil@autentia.com"))
        assertThat(result.telefono, `is`("+34 123456789"))
    }
}