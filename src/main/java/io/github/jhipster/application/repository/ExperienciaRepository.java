package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Experiencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Experiencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long>, JpaSpecificationExecutor<Experiencia> {

}
