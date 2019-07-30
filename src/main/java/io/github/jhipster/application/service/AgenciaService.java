package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Agencia;
import io.github.jhipster.application.repository.AgenciaRepository;
import io.github.jhipster.application.repository.search.AgenciaSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Agencia}.
 */
@Service
@Transactional
public class AgenciaService {

    private final Logger log = LoggerFactory.getLogger(AgenciaService.class);

    private final AgenciaRepository agenciaRepository;

    private final AgenciaSearchRepository agenciaSearchRepository;

    public AgenciaService(AgenciaRepository agenciaRepository, AgenciaSearchRepository agenciaSearchRepository) {
        this.agenciaRepository = agenciaRepository;
        this.agenciaSearchRepository = agenciaSearchRepository;
    }

    /**
     * Save a agencia.
     *
     * @param agencia the entity to save.
     * @return the persisted entity.
     */
    public Agencia save(Agencia agencia) {
        log.debug("Request to save Agencia : {}", agencia);
        Agencia result = agenciaRepository.save(agencia);
        agenciaSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the agencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Agencia> findAll(Pageable pageable) {
        log.debug("Request to get all Agencias");
        return agenciaRepository.findAll(pageable);
    }


    /**
     * Get one agencia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Agencia> findOne(Long id) {
        log.debug("Request to get Agencia : {}", id);
        return agenciaRepository.findById(id);
    }

    /**
     * Delete the agencia by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Agencia : {}", id);
        agenciaRepository.deleteById(id);
        agenciaSearchRepository.deleteById(id);
    }

    /**
     * Search for the agencia corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Agencia> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Agencias for query {}", query);
        return agenciaSearchRepository.search(queryStringQuery(query), pageable);    }
}
