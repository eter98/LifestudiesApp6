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

import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PerfilUsuarioRepository;
import io.github.jhipster.application.repository.search.PerfilUsuarioSearchRepository;
import io.github.jhipster.application.service.dto.PerfilUsuarioCriteria;

/**
 * Service for executing complex queries for {@link PerfilUsuario} entities in the database.
 * The main input is a {@link PerfilUsuarioCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PerfilUsuario} or a {@link Page} of {@link PerfilUsuario} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PerfilUsuarioQueryService extends QueryService<PerfilUsuario> {

    private final Logger log = LoggerFactory.getLogger(PerfilUsuarioQueryService.class);

    private final PerfilUsuarioRepository perfilUsuarioRepository;

    private final PerfilUsuarioSearchRepository perfilUsuarioSearchRepository;

    public PerfilUsuarioQueryService(PerfilUsuarioRepository perfilUsuarioRepository, PerfilUsuarioSearchRepository perfilUsuarioSearchRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.perfilUsuarioSearchRepository = perfilUsuarioSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PerfilUsuario} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PerfilUsuario> findByCriteria(PerfilUsuarioCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PerfilUsuario> specification = createSpecification(criteria);
        return perfilUsuarioRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link PerfilUsuario} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PerfilUsuario> findByCriteria(PerfilUsuarioCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PerfilUsuario> specification = createSpecification(criteria);
        return perfilUsuarioRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PerfilUsuarioCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PerfilUsuario> specification = createSpecification(criteria);
        return perfilUsuarioRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<PerfilUsuario> createSpecification(PerfilUsuarioCriteria criteria) {
        Specification<PerfilUsuario> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PerfilUsuario_.id));
            }
            if (criteria.getFechaNacimiento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaNacimiento(), PerfilUsuario_.fechaNacimiento));
            }
            if (criteria.getIdentificacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdentificacion(), PerfilUsuario_.identificacion));
            }
            if (criteria.getMail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMail(), PerfilUsuario_.mail));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArea(), PerfilUsuario_.area));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTelefono(), PerfilUsuario_.telefono));
            }
            if (criteria.getNivelAcademico() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNivelAcademico(), PerfilUsuario_.nivelAcademico));
            }
            if (criteria.getAreaAcademica() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAreaAcademica(), PerfilUsuario_.areaAcademica));
            }
            if (criteria.getTerminoAcademico() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTerminoAcademico(), PerfilUsuario_.terminoAcademico));
            }
            if (criteria.getPuntajeICFES() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPuntajeICFES(), PerfilUsuario_.puntajeICFES));
            }
            if (criteria.getPromedioAcademico() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPromedioAcademico(), PerfilUsuario_.promedioAcademico));
            }
            if (criteria.getDominioIdioma() != null) {
                specification = specification.and(buildSpecification(criteria.getDominioIdioma(), PerfilUsuario_.dominioIdioma));
            }
            if (criteria.getIdiomas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdiomas(), PerfilUsuario_.idiomas));
            }
            if (criteria.getExamenIdioma() != null) {
                specification = specification.and(buildSpecification(criteria.getExamenIdioma(), PerfilUsuario_.examenIdioma));
            }
            if (criteria.getExamenRealizado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExamenRealizado(), PerfilUsuario_.examenRealizado));
            }
            if (criteria.getPuntajeIdioma() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPuntajeIdioma(), PerfilUsuario_.puntajeIdioma));
            }
            if (criteria.getBecaOtorgada() != null) {
                specification = specification.and(buildSpecification(criteria.getBecaOtorgada(), PerfilUsuario_.becaOtorgada));
            }
            if (criteria.getTipoBeca() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTipoBeca(), PerfilUsuario_.tipoBeca));
            }
            if (criteria.getBecaInstitucion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBecaInstitucion(), PerfilUsuario_.becaInstitucion));
            }
            if (criteria.getGrupoSocial() != null) {
                specification = specification.and(buildSpecification(criteria.getGrupoSocial(), PerfilUsuario_.grupoSocial));
            }
            if (criteria.getFundacion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFundacion(), PerfilUsuario_.fundacion));
            }
            if (criteria.getMonitor() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitor(), PerfilUsuario_.monitor));
            }
            if (criteria.getMonitorMateria() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMonitorMateria(), PerfilUsuario_.monitorMateria));
            }
            if (criteria.getLogrosAcademicos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogrosAcademicos(), PerfilUsuario_.logrosAcademicos));
            }
            if (criteria.getExperienciaLaboral() != null) {
                specification = specification.and(buildSpecification(criteria.getExperienciaLaboral(), PerfilUsuario_.experienciaLaboral));
            }
            if (criteria.getAreaLaboral() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAreaLaboral(), PerfilUsuario_.areaLaboral));
            }
            if (criteria.getProgramarealizar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProgramarealizar(), PerfilUsuario_.programarealizar));
            }
            if (criteria.getProgramaArea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProgramaArea(), PerfilUsuario_.programaArea));
            }
            if (criteria.getFechaInicio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaInicio(), PerfilUsuario_.fechaInicio));
            }
            if (criteria.getProgramaEncontrado() != null) {
                specification = specification.and(buildSpecification(criteria.getProgramaEncontrado(), PerfilUsuario_.programaEncontrado));
            }
            if (criteria.getNombrePrograma() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombrePrograma(), PerfilUsuario_.nombrePrograma));
            }
            if (criteria.getUniversidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUniversidad(), PerfilUsuario_.universidad));
            }
            if (criteria.getPais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPais(), PerfilUsuario_.pais));
            }
            if (criteria.getMerecedorBeca() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMerecedorBeca(), PerfilUsuario_.merecedorBeca));
            }
            if (criteria.getUsuarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsuarioId(),
                    root -> root.join(PerfilUsuario_.usuario, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
