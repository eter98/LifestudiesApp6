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

import io.github.jhipster.application.domain.PasoCRM;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PasoCRMRepository;
import io.github.jhipster.application.repository.search.PasoCRMSearchRepository;
import io.github.jhipster.application.service.dto.PasoCRMCriteria;

/**
 * Service for executing complex queries for {@link PasoCRM} entities in the database.
 * The main input is a {@link PasoCRMCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PasoCRM} or a {@link Page} of {@link PasoCRM} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PasoCRMQueryService extends QueryService<PasoCRM> {

    private final Logger log = LoggerFactory.getLogger(PasoCRMQueryService.class);

    private final PasoCRMRepository pasoCRMRepository;

    private final PasoCRMSearchRepository pasoCRMSearchRepository;

    public PasoCRMQueryService(PasoCRMRepository pasoCRMRepository, PasoCRMSearchRepository pasoCRMSearchRepository) {
        this.pasoCRMRepository = pasoCRMRepository;
        this.pasoCRMSearchRepository = pasoCRMSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PasoCRM} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PasoCRM> findByCriteria(PasoCRMCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PasoCRM> specification = createSpecification(criteria);
        return pasoCRMRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link PasoCRM} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PasoCRM> findByCriteria(PasoCRMCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PasoCRM> specification = createSpecification(criteria);
        return pasoCRMRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PasoCRMCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PasoCRM> specification = createSpecification(criteria);
        return pasoCRMRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<PasoCRM> createSpecification(PasoCRMCriteria criteria) {
        Specification<PasoCRM> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PasoCRM_.id));
            }
            if (criteria.getCodigo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCodigo(), PasoCRM_.codigo));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), PasoCRM_.descripcion));
            }
            if (criteria.getDuracionDias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDuracionDias(), PasoCRM_.duracionDias));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildSpecification(criteria.getEstado(), PasoCRM_.estado));
            }
        }
        return specification;
    }
}
