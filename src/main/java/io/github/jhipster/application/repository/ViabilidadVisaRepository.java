package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ViabilidadVisa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ViabilidadVisa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViabilidadVisaRepository extends JpaRepository<ViabilidadVisa, Long>, JpaSpecificationExecutor<ViabilidadVisa> {

}
