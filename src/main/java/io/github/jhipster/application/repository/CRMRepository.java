package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CRM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CRM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CRMRepository extends JpaRepository<CRM, Long>, JpaSpecificationExecutor<CRM> {

}
