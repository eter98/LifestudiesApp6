package io.github.jhipster.application.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.CRM;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CRMRepository;
import io.github.jhipster.application.repository.search.CRMSearchRepository;
import io.github.jhipster.application.service.dto.CRMCriteria;

/**
 * Service for executing complex queries for {@link CRM} entities in the database.
 * The main input is a {@link CRMCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CRM} or a {@link Page} of {@link CRM} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CRMQueryService extends QueryService<CRM> {

    private final Logger log = LoggerFactory.getLogger(CRMQueryService.class);

    private final CRMRepository cRMRepository;

    private final CRMSearchRepository cRMSearchRepository;

    public CRMQueryService(CRMRepository cRMRepository, CRMSearchRepository cRMSearchRepository) {
        this.cRMRepository = cRMRepository;
        this.cRMSearchRepository = cRMSearchRepository;
    }

    /**
     * Return a {@link List} of {@link CRM} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CRM> findByCriteria(CRMCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CRM> specification = createSpecification(criteria);
        return cRMRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CRM} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CRM> findByCriteria(CRMCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CRM> specification = createSpecification(criteria);
        return cRMRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CRMCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CRM> specification = createSpecification(criteria);
        return cRMRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<CRM> createSpecification(CRMCriteria criteria) {
        Specification<CRM> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CRM_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), CRM_.fecha));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), CRM_.descripcion));
            }
            if (criteria.getAsesor() != null) {
                specification = specification.and(buildSpecification(criteria.getAsesor(), CRM_.asesor));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildSpecification(criteria.getEstado(), CRM_.estado));
            }
            if (criteria.getTipoLead() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoLead(), CRM_.tipoLead));
            }
            if (criteria.getCerrado() != null) {
                specification = specification.and(buildSpecification(criteria.getCerrado(), CRM_.cerrado));
            }
            if (criteria.getUsuarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsuarioId(),
                    root -> root.join(CRM_.usuario, JoinType.LEFT).get(PerfilUsuario_.id)));
            }
            if (criteria.getProcesoId() != null) {
                specification = specification.and(buildSpecification(criteria.getProcesoId(),
                    root -> root.join(CRM_.proceso, JoinType.LEFT).get(PasoCRM_.id)));
            }
            if (criteria.getAgenciaId() != null) {
                specification = specification.and(buildSpecification(criteria.getAgenciaId(),
                    root -> root.join(CRM_.agencia, JoinType.LEFT).get(Agencia_.id)));
            }
            if (criteria.getCotizacionId() != null) {
                specification = specification.and(buildSpecification(criteria.getCotizacionId(),
                    root -> root.join(CRM_.cotizacion, JoinType.LEFT).get(Cotizacion_.id)));
            }
        }
        return specification;
    }
}
