package com.dfco.restcliente.consultacliente.repositorio

import com.dfco.restcliente.consultacliente.modelo.PerfilCliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface RepositorioPerfilCliente : JpaRepository<PerfilCliente, UUID>