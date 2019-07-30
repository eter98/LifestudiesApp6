package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PasoCRM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PasoCRM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PasoCRMRepository extends JpaRepository<PasoCRM, Long>, JpaSpecificationExecutor<PasoCRM> {

}
