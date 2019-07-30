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

import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ProgramasRepository;
import io.github.jhipster.application.repository.search.ProgramasSearchRepository;
import io.github.jhipster.application.service.dto.ProgramasCriteria;

/**
 * Service for executing complex queries for {@link Programas} entities in the database.
 * The main input is a {@link ProgramasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Programas} or a {@link Page} of {@link Programas} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProgramasQueryService extends QueryService<Programas> {

    private final Logger log = LoggerFactory.getLogger(ProgramasQueryService.class);

    private final ProgramasRepository programasRepository;

    private final ProgramasSearchRepository programasSearchRepository;

    public ProgramasQueryService(ProgramasRepository programasRepository, ProgramasSearchRepository programasSearchRepository) {
        this.programasRepository = programasRepository;
        this.programasSearchRepository = programasSearchRepository;
    }

    /**
     * Return a {@link List} of {@link Programas} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Programas> findByCriteria(ProgramasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Programas> specification = createSpecification(criteria);
        return programasRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Programas} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Programas> findByCriteria(ProgramasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Programas> specification = createSpecification(criteria);
        return programasRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProgramasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Programas> specification = createSpecification(criteria);
        return programasRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Programas> createSpecification(ProgramasCriteria criteria) {
        Specification<Programas> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Programas_.id));
            }
            if (criteria.getRegistro() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegistro(), Programas_.registro));
            }
            if (criteria.getMoneda() != null) {
                specification = specification.and(buildSpecification(criteria.getMoneda(), Programas_.moneda));
            }
            if (criteria.getCurso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurso(), Programas_.curso));
            }
            if (criteria.getHorario() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHorario(), Programas_.horario));
            }
            if (criteria.getFrecuencia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFrecuencia(), Programas_.frecuencia));
            }
            if (criteria.getDuracion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDuracion(), Programas_.duracion));
            }
            if (criteria.getInstitucionId() != null) {
                specification = specification.and(buildSpecification(criteria.getInstitucionId(),
                    root -> root.join(Programas_.institucion, JoinType.LEFT).get(Institucion_.id)));
            }
            if (criteria.getCiudadId() != null) {
                specification = specification.and(buildSpecification(criteria.getCiudadId(),
                    root -> root.join(Programas_.ciudad, JoinType.LEFT).get(Ciudad_.id)));
            }
            if (criteria.getTipoProgramaId() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoProgramaId(),
                    root -> root.join(Programas_.tipoPrograma, JoinType.LEFT).get(TipoPrograma_.id)));
            }
        }
        return specification;
    }
}
