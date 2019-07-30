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

import io.github.jhipster.application.domain.BlogContenido;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BlogContenidoRepository;
import io.github.jhipster.application.repository.search.BlogContenidoSearchRepository;
import io.github.jhipster.application.service.dto.BlogContenidoCriteria;

/**
 * Service for executing complex queries for {@link BlogContenido} entities in the database.
 * The main input is a {@link BlogContenidoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlogContenido} or a {@link Page} of {@link BlogContenido} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlogContenidoQueryService extends QueryService<BlogContenido> {

    private final Logger log = LoggerFactory.getLogger(BlogContenidoQueryService.class);

    private final BlogContenidoRepository blogContenidoRepository;

    private final BlogContenidoSearchRepository blogContenidoSearchRepository;

    public BlogContenidoQueryService(BlogContenidoRepository blogContenidoRepository, BlogContenidoSearchRepository blogContenidoSearchRepository) {
        this.blogContenidoRepository = blogContenidoRepository;
        this.blogContenidoSearchRepository = blogContenidoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link BlogContenido} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BlogContenido> findByCriteria(BlogContenidoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BlogContenido> specification = createSpecification(criteria);
        return blogContenidoRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BlogContenido} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlogContenido> findByCriteria(BlogContenidoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BlogContenido> specification = createSpecification(criteria);
        return blogContenidoRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BlogContenidoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BlogContenido> specification = createSpecification(criteria);
        return blogContenidoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<BlogContenido> createSpecification(BlogContenidoCriteria criteria) {
        Specification<BlogContenido> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BlogContenido_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), BlogContenido_.nombre));
            }
            if (criteria.getApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApellido(), BlogContenido_.apellido));
            }
            if (criteria.getCorreo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorreo(), BlogContenido_.correo));
            }
            if (criteria.getNacionalidad() != null) {
                specification = specification.and(buildSpecification(criteria.getNacionalidad(), BlogContenido_.nacionalidad));
            }
            if (criteria.getPaisEstudio() != null) {
                specification = specification.and(buildSpecification(criteria.getPaisEstudio(), BlogContenido_.paisEstudio));
            }
            if (criteria.getCalificacionPais() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificacionPais(), BlogContenido_.calificacionPais));
            }
            if (criteria.getCiudadVive() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCiudadVive(), BlogContenido_.ciudadVive));
            }
            if (criteria.getCalificacionCiudad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificacionCiudad(), BlogContenido_.calificacionCiudad));
            }
            if (criteria.getProgramaRealizado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProgramaRealizado(), BlogContenido_.programaRealizado));
            }
            if (criteria.getInstitucionEstudio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstitucionEstudio(), BlogContenido_.institucionEstudio));
            }
            if (criteria.getCalificacionInstitucion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificacionInstitucion(), BlogContenido_.calificacionInstitucion));
            }
            if (criteria.getAgenciaEstudios() != null) {
                specification = specification.and(buildSpecification(criteria.getAgenciaEstudios(), BlogContenido_.agenciaEstudios));
            }
            if (criteria.getNombreAgencia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreAgencia(), BlogContenido_.nombreAgencia));
            }
            if (criteria.getCalificacionAgencia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificacionAgencia(), BlogContenido_.calificacionAgencia));
            }
            if (criteria.getCalificacionExperienciaEstudio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalificacionExperienciaEstudio(), BlogContenido_.calificacionExperienciaEstudio));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), BlogContenido_.fecha));
            }
            if (criteria.getTitulo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitulo(), BlogContenido_.titulo));
            }
            if (criteria.getUsuarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsuarioId(),
                    root -> root.join(BlogContenido_.usuario, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
