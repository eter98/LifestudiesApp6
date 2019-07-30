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

import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.TipoProgramaRepository;
import io.github.jhipster.application.repository.search.TipoProgramaSearchRepository;
import io.github.jhipster.application.service.dto.TipoProgramaCriteria;

/**
 * Service for executing complex queries for {@link TipoPrograma} entities in the database.
 * The main input is a {@link TipoProgramaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TipoPrograma} or a {@link Page} of {@link TipoPrograma} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TipoProgramaQueryService extends QueryService<TipoPrograma> {

    private final Logger log = LoggerFactory.getLogger(TipoProgramaQueryService.class);

    private final TipoProgramaRepository tipoProgramaRepository;

    private final TipoProgramaSearchRepository tipoProgramaSearchRepository;

    public TipoProgramaQueryService(TipoProgramaRepository tipoProgramaRepository, TipoProgramaSearchRepository tipoProgramaSearchRepository) {
        this.tipoProgramaRepository = tipoProgramaRepository;
        this.tipoProgramaSearchRepository = tipoProgramaSearchRepository;
    }

    /**
     * Return a {@link List} of {@link TipoPrograma} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TipoPrograma> findByCriteria(TipoProgramaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TipoPrograma> specification = createSpecification(criteria);
        return tipoProgramaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TipoPrograma} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoPrograma> findByCriteria(TipoProgramaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TipoPrograma> specification = createSpecification(criteria);
        return tipoProgramaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TipoProgramaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TipoPrograma> specification = createSpecification(criteria);
        return tipoProgramaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<TipoPrograma> createSpecification(TipoProgramaCriteria criteria) {
        Specification<TipoPrograma> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TipoPrograma_.id));
            }
            if (criteria.getTipoPrograma() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoPrograma(), TipoPrograma_.tipoPrograma));
            }
        }
        return specification;
    }
}
