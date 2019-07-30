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

import io.github.jhipster.application.domain.Agencia;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.AgenciaRepository;
import io.github.jhipster.application.repository.search.AgenciaSearchRepository;
import io.github.jhipster.application.service.dto.AgenciaCriteria;

/**
 * Service for executing complex queries for {@link Agencia} entities in the database.
 * The main input is a {@link AgenciaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Agencia} or a {@link Page} of {@link Agencia} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AgenciaQueryService extends QueryService<Agencia> {

    private final Logger log = LoggerFactory.getLogger(AgenciaQueryService.class);

    private final AgenciaRepository agenciaRepository;

    private final AgenciaSearchRepository agenciaSearchRepository;

    public AgenciaQueryService(AgenciaRepository agenciaRepository, AgenciaSearchRepository agenciaSearchRepository) {
        this.agenciaRepository = agenciaRepository;
        this.agenciaSearchRepository = agenciaSearchRepository;
    }

    /**
     * Return a {@link List} of {@link Agencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Agencia> findByCriteria(AgenciaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Agencia> specification = createSpecification(criteria);
        return agenciaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Agencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Agencia> findByCriteria(AgenciaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Agencia> specification = createSpecification(criteria);
        return agenciaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AgenciaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Agencia> specification = createSpecification(criteria);
        return agenciaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Agencia> createSpecification(AgenciaCriteria criteria) {
        Specification<Agencia> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Agencia_.id));
            }
            if (criteria.getNombreAgencia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreAgencia(), Agencia_.nombreAgencia));
            }
            if (criteria.getCodigo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigo(), Agencia_.codigo));
            }
            if (criteria.getEncargado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncargado(), Agencia_.encargado));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), Agencia_.direccion));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), Agencia_.telefono));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Agencia_.email));
            }
            if (criteria.getWhatsapp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWhatsapp(), Agencia_.whatsapp));
            }
            if (criteria.getAsesor1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAsesor1(), Agencia_.asesor1));
            }
            if (criteria.getAsesor2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAsesor2(), Agencia_.asesor2));
            }
            if (criteria.getAsesor3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAsesor3(), Agencia_.asesor3));
            }
            if (criteria.getSede1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSede1(), Agencia_.sede1));
            }
            if (criteria.getSede2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSede2(), Agencia_.sede2));
            }
            if (criteria.getPaisId() != null) {
                specification = specification.and(buildSpecification(criteria.getPaisId(),
                    root -> root.join(Agencia_.pais, JoinType.LEFT).get(Pais_.id)));
            }
        }
        return specification;
    }
}
