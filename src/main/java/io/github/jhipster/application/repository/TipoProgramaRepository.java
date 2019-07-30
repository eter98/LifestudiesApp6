package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TipoPrograma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoPrograma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoProgramaRepository extends JpaRepository<TipoPrograma, Long>, JpaSpecificationExecutor<TipoPrograma> {

}
