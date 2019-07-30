package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.repository.CotizacionRepository;
import io.github.jhipster.application.repository.search.CotizacionSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Cotizacion}.
 */
@Service
@Transactional
public class CotizacionService {

    private final Logger log = LoggerFactory.getLogger(CotizacionService.class);

    private final CotizacionRepository cotizacionRepository;

    private final CotizacionSearchRepository cotizacionSearchRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository, CotizacionSearchRepository cotizacionSearchRepository) {
        this.cotizacionRepository = cotizacionRepository;
        this.cotizacionSearchRepository = cotizacionSearchRepository;
    }

    /**
     * Save a cotizacion.
     *
     * @param cotizacion the entity to save.
     * @return the persisted entity.
     */
    public Cotizacion save(Cotizacion cotizacion) {
        log.debug("Request to save Cotizacion : {}", cotizacion);
        Cotizacion result = cotizacionRepository.save(cotizacion);
        cotizacionSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the cotizacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Cotizacion> findAll(Pageable pageable) {
        log.debug("Request to get all Cotizacions");
        return cotizacionRepository.findAll(pageable);
    }


    /**
     * Get one cotizacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Cotizacion> findOne(Long id) {
        log.debug("Request to get Cotizacion : {}", id);
        return cotizacionRepository.findById(id);
    }

    /**
     * Delete the cotizacion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cotizacion : {}", id);
        cotizacionRepository.deleteById(id);
        cotizacionSearchRepository.deleteById(id);
    }

    /**
     * Search for the cotizacion corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Cotizacion> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Cotizacions for query {}", query);
        return cotizacionSearchRepository.search(queryStringQuery(query), pageable);    }
}
