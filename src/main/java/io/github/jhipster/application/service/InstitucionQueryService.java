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

import io.github.jhipster.application.domain.Institucion;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.InstitucionRepository;
import io.github.jhipster.application.repository.search.InstitucionSearchRepository;
import io.github.jhipster.application.service.dto.InstitucionCriteria;

/**
 * Service for executing complex queries for {@link Institucion} entities in the database.
 * The main input is a {@link InstitucionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Institucion} or a {@link Page} of {@link Institucion} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InstitucionQueryService extends QueryService<Institucion> {

    private final Logger log = LoggerFactory.getLogger(InstitucionQueryService.class);

    private final InstitucionRepository institucionRepository;

    private final InstitucionSearchRepository institucionSearchRepository;

    public InstitucionQueryService(InstitucionRepository institucionRepository, InstitucionSearchRepository institucionSearchRepository) {
        this.institucionRepository = institucionRepository;
        this.institucionSearchRepository = institucionSearchRepository;
    }

    /**
     * Return a {@link List} of {@link Institucion} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Institucion> findByCriteria(InstitucionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Institucion> specification = createSpecification(criteria);
        return institucionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Institucion} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Institucion> findByCriteria(InstitucionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Institucion> specification = createSpecification(criteria);
        return institucionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InstitucionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Institucion> specification = createSpecification(criteria);
        return institucionRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Institucion> createSpecification(InstitucionCriteria criteria) {
        Specification<Institucion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Institucion_.id));
            }
            if (criteria.getCodigo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigo(), Institucion_.codigo));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Institucion_.nombre));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), Institucion_.direccion));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), Institucion_.website));
            }
            if (criteria.getContacto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContacto(), Institucion_.contacto));
            }
            if (criteria.getRepresentante() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRepresentante(), Institucion_.representante));
            }
            if (criteria.getSkype() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSkype(), Institucion_.skype));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), Institucion_.telefono));
            }
            if (criteria.getEstatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstatus(), Institucion_.estatus));
            }
            if (criteria.getTipoProgramas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoProgramas(), Institucion_.tipoProgramas));
            }
            if (criteria.getProgramaEstandar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProgramaEstandar(), Institucion_.programaEstandar));
            }
            if (criteria.getProgramaIntensivo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProgramaIntensivo(), Institucion_.programaIntensivo));
            }
            if (criteria.getProgramaNegocios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProgramaNegocios(), Institucion_.programaNegocios));
            }
            if (criteria.getPreparacionExamen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPreparacionExamen(), Institucion_.preparacionExamen));
            }
            if (criteria.getProgramaAcademico() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProgramaAcademico(), Institucion_.programaAcademico));
            }
            if (criteria.getDescuento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDescuento(), Institucion_.descuento));
            }
            if (criteria.getInscripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInscripcion(), Institucion_.inscripcion));
            }
            if (criteria.getMateriales() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMateriales(), Institucion_.materiales));
            }
            if (criteria.getSeguroMedico() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSeguroMedico(), Institucion_.seguroMedico));
            }
            if (criteria.getAlojamientoSencillo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlojamientoSencillo(), Institucion_.alojamientoSencillo));
            }
            if (criteria.getAlojamientoDoble() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlojamientoDoble(), Institucion_.alojamientoDoble));
            }
            if (criteria.getTransporteAeropuerto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransporteAeropuerto(), Institucion_.transporteAeropuerto));
            }
            if (criteria.getTipoCurso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoCurso(), Institucion_.tipoCurso));
            }
            if (criteria.getTemporadaAlta() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTemporadaAlta(), Institucion_.temporadaAlta));
            }
            if (criteria.getTemporadaBaja() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTemporadaBaja(), Institucion_.temporadaBaja));
            }
            if (criteria.getFechaInicial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaInicial(), Institucion_.fechaInicial));
            }
            if (criteria.getHorarios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHorarios(), Institucion_.horarios));
            }
            if (criteria.getInstalaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstalaciones(), Institucion_.instalaciones));
            }
            if (criteria.getNacionalidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNacionalidad(), Institucion_.nacionalidad));
            }
            if (criteria.getCiudadId() != null) {
                specification = specification.and(buildSpecification(criteria.getCiudadId(),
                    root -> root.join(Institucion_.ciudad, JoinType.LEFT).get(Ciudad_.id)));
            }
        }
        return specification;
    }
}
