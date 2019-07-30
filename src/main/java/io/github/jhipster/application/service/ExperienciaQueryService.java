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

import io.github.jhipster.application.domain.Experiencia;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ExperienciaRepository;
import io.github.jhipster.application.repository.search.ExperienciaSearchRepository;
import io.github.jhipster.application.service.dto.ExperienciaCriteria;

/**
 * Service for executing complex queries for {@link Experiencia} entities in the database.
 * The main input is a {@link ExperienciaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Experiencia} or a {@link Page} of {@link Experiencia} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ExperienciaQueryService extends QueryService<Experiencia> {

    private final Logger log = LoggerFactory.getLogger(ExperienciaQueryService.class);

    private final ExperienciaRepository experienciaRepository;

    private final ExperienciaSearchRepository experienciaSearchRepository;

    public ExperienciaQueryService(ExperienciaRepository experienciaRepository, ExperienciaSearchRepository experienciaSearchRepository) {
        this.experienciaRepository = experienciaRepository;
        this.experienciaSearchRepository = experienciaSearchRepository;
    }

    /**
     * Return a {@link List} of {@link Experiencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Experiencia> findByCriteria(ExperienciaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Experiencia> specification = createSpecification(criteria);
        return experienciaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Experiencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Experiencia> findByCriteria(ExperienciaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Experiencia> specification = createSpecification(criteria);
        return experienciaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ExperienciaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Experiencia> specification = createSpecification(criteria);
        return experienciaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Experiencia> createSpecification(ExperienciaCriteria criteria) {
        Specification<Experiencia> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Experiencia_.id));
            }
            if (criteria.getIdentificacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdentificacion(), Experiencia_.identificacion));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Experiencia_.nombre));
            }
            if (criteria.getApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApellido(), Experiencia_.apellido));
            }
            if (criteria.getMail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMail(), Experiencia_.mail));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArea(), Experiencia_.area));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTelefono(), Experiencia_.telefono));
            }
            if (criteria.getNacionalidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNacionalidad(), Experiencia_.nacionalidad));
            }
            if (criteria.getPaisDestino() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaisDestino(), Experiencia_.paisDestino));
            }
            if (criteria.getCalificaPais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificaPais(), Experiencia_.calificaPais));
            }
            if (criteria.getPrograma() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrograma(), Experiencia_.programa));
            }
            if (criteria.getInstitucion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstitucion(), Experiencia_.institucion));
            }
            if (criteria.getCalificaInstitucion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificaInstitucion(), Experiencia_.calificaInstitucion));
            }
            if (criteria.getAgencia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgencia(), Experiencia_.agencia));
            }
            if (criteria.getCalificaAgencia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificaAgencia(), Experiencia_.calificaAgencia));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Experiencia_.fecha));
            }
        }
        return specification;
    }
}
