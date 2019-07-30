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

import io.github.jhipster.application.domain.ViabilidadVisa;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ViabilidadVisaRepository;
import io.github.jhipster.application.repository.search.ViabilidadVisaSearchRepository;
import io.github.jhipster.application.service.dto.ViabilidadVisaCriteria;

/**
 * Service for executing complex queries for {@link ViabilidadVisa} entities in the database.
 * The main input is a {@link ViabilidadVisaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ViabilidadVisa} or a {@link Page} of {@link ViabilidadVisa} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ViabilidadVisaQueryService extends QueryService<ViabilidadVisa> {

    private final Logger log = LoggerFactory.getLogger(ViabilidadVisaQueryService.class);

    private final ViabilidadVisaRepository viabilidadVisaRepository;

    private final ViabilidadVisaSearchRepository viabilidadVisaSearchRepository;

    public ViabilidadVisaQueryService(ViabilidadVisaRepository viabilidadVisaRepository, ViabilidadVisaSearchRepository viabilidadVisaSearchRepository) {
        this.viabilidadVisaRepository = viabilidadVisaRepository;
        this.viabilidadVisaSearchRepository = viabilidadVisaSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ViabilidadVisa} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ViabilidadVisa> findByCriteria(ViabilidadVisaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ViabilidadVisa> specification = createSpecification(criteria);
        return viabilidadVisaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link ViabilidadVisa} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ViabilidadVisa> findByCriteria(ViabilidadVisaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ViabilidadVisa> specification = createSpecification(criteria);
        return viabilidadVisaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ViabilidadVisaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ViabilidadVisa> specification = createSpecification(criteria);
        return viabilidadVisaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<ViabilidadVisa> createSpecification(ViabilidadVisaCriteria criteria) {
        Specification<ViabilidadVisa> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ViabilidadVisa_.id));
            }
            if (criteria.getDestino() != null) {
                specification = specification.and(buildSpecification(criteria.getDestino(), ViabilidadVisa_.destino));
            }
            if (criteria.getTipoPrograma() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoPrograma(), ViabilidadVisa_.tipoPrograma));
            }
            if (criteria.getDuracion() != null) {
                specification = specification.and(buildSpecification(criteria.getDuracion(), ViabilidadVisa_.duracion));
            }
            if (criteria.getNacionalidadPrincipal() != null) {
                specification = specification.and(buildSpecification(criteria.getNacionalidadPrincipal(), ViabilidadVisa_.nacionalidadPrincipal));
            }
            if (criteria.getOtraNacionalidad() != null) {
                specification = specification.and(buildSpecification(criteria.getOtraNacionalidad(), ViabilidadVisa_.otraNacionalidad));
            }
            if (criteria.getFechaNacimiento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaNacimiento(), ViabilidadVisa_.fechaNacimiento));
            }
            if (criteria.getGenero() != null) {
                specification = specification.and(buildSpecification(criteria.getGenero(), ViabilidadVisa_.genero));
            }
            if (criteria.getEstadoCivil() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoCivil(), ViabilidadVisa_.estadoCivil));
            }
            if (criteria.getViajaAcompanado() != null) {
                specification = specification.and(buildSpecification(criteria.getViajaAcompanado(), ViabilidadVisa_.viajaAcompanado));
            }
            if (criteria.getPersonasCargo() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonasCargo(), ViabilidadVisa_.personasCargo));
            }
            if (criteria.getNivelAcademico() != null) {
                specification = specification.and(buildSpecification(criteria.getNivelAcademico(), ViabilidadVisa_.nivelAcademico));
            }
            if (criteria.getProfesion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfesion(), ViabilidadVisa_.profesion));
            }
            if (criteria.getFechaGadruacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaGadruacion(), ViabilidadVisa_.fechaGadruacion));
            }
            if (criteria.getOcupacionActual() != null) {
                specification = specification.and(buildSpecification(criteria.getOcupacionActual(), ViabilidadVisa_.ocupacionActual));
            }
            if (criteria.getCarta() != null) {
                specification = specification.and(buildSpecification(criteria.getCarta(), ViabilidadVisa_.carta));
            }
            if (criteria.getSuspendidoEstudios() != null) {
                specification = specification.and(buildSpecification(criteria.getSuspendidoEstudios(), ViabilidadVisa_.suspendidoEstudios));
            }
            if (criteria.getPeridoSupencionEstu() != null) {
                specification = specification.and(buildSpecification(criteria.getPeridoSupencionEstu(), ViabilidadVisa_.peridoSupencionEstu));
            }
            if (criteria.getRazonSuspencion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRazonSuspencion(), ViabilidadVisa_.razonSuspencion));
            }
            if (criteria.getTipoContrato() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoContrato(), ViabilidadVisa_.tipoContrato));
            }
            if (criteria.getLicenciaLaboral() != null) {
                specification = specification.and(buildSpecification(criteria.getLicenciaLaboral(), ViabilidadVisa_.licenciaLaboral));
            }
            if (criteria.getNivelSalarial() != null) {
                specification = specification.and(buildSpecification(criteria.getNivelSalarial(), ViabilidadVisa_.nivelSalarial));
            }
            if (criteria.getTipoLaborIndependiente() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoLaborIndependiente(), ViabilidadVisa_.tipoLaborIndependiente));
            }
            if (criteria.getTiempoIndependiente() != null) {
                specification = specification.and(buildSpecification(criteria.getTiempoIndependiente(), ViabilidadVisa_.tiempoIndependiente));
            }
            if (criteria.getNivelIngresosIndependiente() != null) {
                specification = specification.and(buildSpecification(criteria.getNivelIngresosIndependiente(), ViabilidadVisa_.nivelIngresosIndependiente));
            }
            if (criteria.getTiempoDesempleado() != null) {
                specification = specification.and(buildSpecification(criteria.getTiempoDesempleado(), ViabilidadVisa_.tiempoDesempleado));
            }
            if (criteria.getQuienFinanciaEstudios() != null) {
                specification = specification.and(buildSpecification(criteria.getQuienFinanciaEstudios(), ViabilidadVisa_.quienFinanciaEstudios));
            }
            if (criteria.getParentesco() != null) {
                specification = specification.and(buildSpecification(criteria.getParentesco(), ViabilidadVisa_.parentesco));
            }
            if (criteria.getPresupuestoDisponible() != null) {
                specification = specification.and(buildSpecification(criteria.getPresupuestoDisponible(), ViabilidadVisa_.presupuestoDisponible));
            }
            if (criteria.getAhorroDisponible() != null) {
                specification = specification.and(buildSpecification(criteria.getAhorroDisponible(), ViabilidadVisa_.ahorroDisponible));
            }
            if (criteria.getIdioma() != null) {
                specification = specification.and(buildSpecification(criteria.getIdioma(), ViabilidadVisa_.idioma));
            }
            if (criteria.getCertificarIdioma() != null) {
                specification = specification.and(buildSpecification(criteria.getCertificarIdioma(), ViabilidadVisa_.certificarIdioma));
            }
            if (criteria.getCertificacionIdioma() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificacionIdioma(), ViabilidadVisa_.certificacionIdioma));
            }
            if (criteria.getPuntajeCertificacion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPuntajeCertificacion(), ViabilidadVisa_.puntajeCertificacion));
            }
            if (criteria.getSalidasPais() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSalidasPais(), ViabilidadVisa_.salidasPais));
            }
            if (criteria.getPaisesVisitados() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaisesVisitados(), ViabilidadVisa_.paisesVisitados));
            }
            if (criteria.getVisaPais() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVisaPais(), ViabilidadVisa_.visaPais));
            }
            if (criteria.getEstudiadoAnterior() != null) {
                specification = specification.and(buildSpecification(criteria.getEstudiadoAnterior(), ViabilidadVisa_.estudiadoAnterior));
            }
            if (criteria.getFueraPais() != null) {
                specification = specification.and(buildSpecification(criteria.getFueraPais(), ViabilidadVisa_.fueraPais));
            }
            if (criteria.getPaisFuera() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaisFuera(), ViabilidadVisa_.paisFuera));
            }
            if (criteria.getExtenderEstadia() != null) {
                specification = specification.and(buildSpecification(criteria.getExtenderEstadia(), ViabilidadVisa_.extenderEstadia));
            }
            if (criteria.getNegadoVisa() != null) {
                specification = specification.and(buildSpecification(criteria.getNegadoVisa(), ViabilidadVisa_.negadoVisa));
            }
            if (criteria.getPaisNegado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaisNegado(), ViabilidadVisa_.paisNegado));
            }
            if (criteria.getFamiliaresDestino() != null) {
                specification = specification.and(buildSpecification(criteria.getFamiliaresDestino(), ViabilidadVisa_.familiaresDestino));
            }
            if (criteria.getEstatusMigratorio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstatusMigratorio(), ViabilidadVisa_.estatusMigratorio));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), ViabilidadVisa_.nombre));
            }
            if (criteria.getApelliod() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApelliod(), ViabilidadVisa_.apelliod));
            }
            if (criteria.getCorreo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorreo(), ViabilidadVisa_.correo));
            }
        }
        return specification;
    }
}
