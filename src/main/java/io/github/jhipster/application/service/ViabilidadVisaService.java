package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ViabilidadVisa;
import io.github.jhipster.application.repository.ViabilidadVisaRepository;
import io.github.jhipster.application.repository.search.ViabilidadVisaSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ViabilidadVisa}.
 */
@Service
@Transactional
public class ViabilidadVisaService {

    private final Logger log = LoggerFactory.getLogger(ViabilidadVisaService.class);

    private final ViabilidadVisaRepository viabilidadVisaRepository;

    private final ViabilidadVisaSearchRepository viabilidadVisaSearchRepository;

    public ViabilidadVisaService(ViabilidadVisaRepository viabilidadVisaRepository, ViabilidadVisaSearchRepository viabilidadVisaSearchRepository) {
        this.viabilidadVisaRepository = viabilidadVisaRepository;
        this.viabilidadVisaSearchRepository = viabilidadVisaSearchRepository;
    }

    /**
     * Save a viabilidadVisa.
     *
     * @param viabilidadVisa the entity to save.
     * @return the persisted entity.
     */
    public ViabilidadVisa save(ViabilidadVisa viabilidadVisa) {
        log.debug("Request to save ViabilidadVisa : {}", viabilidadVisa);
        ViabilidadVisa result = viabilidadVisaRepository.save(viabilidadVisa);
        viabilidadVisaSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the viabilidadVisas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ViabilidadVisa> findAll(Pageable pageable) {
        log.debug("Request to get all ViabilidadVisas");
        return viabilidadVisaRepository.findAll(pageable);
    }


    /**
     * Get one viabilidadVisa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViabilidadVisa> findOne(Long id) {
        log.debug("Request to get ViabilidadVisa : {}", id);
        return viabilidadVisaRepository.findById(id);
    }

    /**
     * Delete the viabilidadVisa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViabilidadVisa : {}", id);
        viabilidadVisaRepository.deleteById(id);
        viabilidadVisaSearchRepository.deleteById(id);
    }

    /**
     * Search for the viabilidadVisa corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ViabilidadVisa> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ViabilidadVisas for query {}", query);
        return viabilidadVisaSearchRepository.search(queryStringQuery(query), pageable);    }
}
