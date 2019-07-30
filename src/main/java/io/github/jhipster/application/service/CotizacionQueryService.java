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

import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.CotizacionRepository;
import io.github.jhipster.application.repository.search.CotizacionSearchRepository;
import io.github.jhipster.application.service.dto.CotizacionCriteria;

/**
 * Service for executing complex queries for {@link Cotizacion} entities in the database.
 * The main input is a {@link CotizacionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Cotizacion} or a {@link Page} of {@link Cotizacion} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CotizacionQueryService extends QueryService<Cotizacion> {

    private final Logger log = LoggerFactory.getLogger(CotizacionQueryService.class);

    private final CotizacionRepository cotizacionRepository;

    private final CotizacionSearchRepository cotizacionSearchRepository;

    public CotizacionQueryService(CotizacionRepository cotizacionRepository, CotizacionSearchRepository cotizacionSearchRepository) {
        this.cotizacionRepository = cotizacionRepository;
        this.cotizacionSearchRepository = cotizacionSearchRepository;
    }

    /**
     * Return a {@link List} of {@link Cotizacion} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Cotizacion> findByCriteria(CotizacionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cotizacion> specification = createSpecification(criteria);
        return cotizacionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Cotizacion} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Cotizacion> findByCriteria(CotizacionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cotizacion> specification = createSpecification(criteria);
        return cotizacionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CotizacionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cotizacion> specification = createSpecification(criteria);
        return cotizacionRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Cotizacion> createSpecification(CotizacionCriteria criteria) {
        Specification<Cotizacion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Cotizacion_.id));
            }
            if (criteria.getFechaViaje() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaViaje(), Cotizacion_.fechaViaje));
            }
            if (criteria.getAlojamiento() != null) {
                specification = specification.and(buildSpecification(criteria.getAlojamiento(), Cotizacion_.alojamiento));
            }
            if (criteria.getPasajeAereo() != null) {
                specification = specification.and(buildSpecification(criteria.getPasajeAereo(), Cotizacion_.pasajeAereo));
            }
            if (criteria.getTranspAeropuerto() != null) {
                specification = specification.and(buildSpecification(criteria.getTranspAeropuerto(), Cotizacion_.transpAeropuerto));
            }
            if (criteria.getUsarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsarioId(),
                    root -> root.join(Cotizacion_.usario, JoinType.LEFT).get(PerfilUsuario_.id)));
            }
            if (criteria.getCursoId() != null) {
                specification = specification.and(buildSpecification(criteria.getCursoId(),
                    root -> root.join(Cotizacion_.curso, JoinType.LEFT).get(Programas_.id)));
            }
            if (criteria.getCRMId() != null) {
                specification = specification.and(buildSpecification(criteria.getCRMId(),
                    root -> root.join(Cotizacion_.cRMS, JoinType.LEFT).get(CRM_.id)));
            }
        }
        return specification;
    }
}
