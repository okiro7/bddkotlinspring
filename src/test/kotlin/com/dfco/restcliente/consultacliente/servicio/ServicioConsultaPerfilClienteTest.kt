package com.dfco.restcliente.consultacliente.servicio

import com.dfco.restcliente.consultacliente.ConversorPerfilCliente
import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import com.dfco.restcliente.consultacliente.repositorio.RepositorioPerfilCliente
import com.dfco.restcliente.consultacliente.servicio.impl.ServicioConsultaPerfilClienteImpl
import com.dfco.restcliente.consultacliente.servicio.vo.RespuestaPerfilCliente
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.time.LocalDate
import java.util.UUID


/*@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)*/
class ServicioConsultaPerfilClienteTest{

   // @InjectMocks
    //private lateinit var sut: ServicioConsultaPerfilClienteImpl

   // @Mock
   // private lateinit var mockRepository: RepositorioPerfilCliente

    //@Mock
   // private lateinit var mockConversor: ConversorPerfilCliente

    /*@Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }*/

    @Test
    fun dadoIdClienteExistenteEntoncesDevuelvePerfilCliente() {

        // Given
        val id = UUID.randomUUID()
        val perfil: PerfilCliente = PerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("dgarciagil@autentia.com")
                .telefono("+34 123456789")
                .build()

        val respuestaPerfilCliente: RespuestaPerfilCliente = RespuestaPerfilCliente.Builder()
                .id(id)
                .nombre("David")
                .fechaNacimiento(LocalDate.of(1976, 2, 28))
                .email("dgarciagil@autentia.com")
                .telefono("+34 123456789")
                .build()
        val mockRepository = Mockito.mock(RepositorioPerfilCliente::class.java)
        val conversorPerfilCliente = Mockito.mock(ConversorPerfilCliente::class.java)
        Mockito.`when`(mockRepository.findById(id)).thenReturn(perfil)
        Mockito.`when`(conversorPerfilCliente.convert(perfil)).thenReturn(respuestaPerfilCliente)


        val sut = ServicioConsultaPerfilClienteImpl(mockRepository,conversorPerfilCliente)


        // When
        val result: RespuestaPerfilCliente = sut.consultar(id)

        // Then
        verify(mockRepository).findById(perfil.id!!)
        assertThat(perfil.id, `is`(id))
    }
}