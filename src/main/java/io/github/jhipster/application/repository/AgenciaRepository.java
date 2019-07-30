package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Agencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Agencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long>, JpaSpecificationExecutor<Agencia> {

}
