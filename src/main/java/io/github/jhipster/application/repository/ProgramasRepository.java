package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Programas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Programas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramasRepository extends JpaRepository<Programas, Long>, JpaSpecificationExecutor<Programas> {

}
