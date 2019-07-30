package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.repository.ProgramasRepository;
import io.github.jhipster.application.repository.search.ProgramasSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Programas}.
 */
@Service
@Transactional
public class ProgramasService {

    private final Logger log = LoggerFactory.getLogger(ProgramasService.class);

    private final ProgramasRepository programasRepository;

    private final ProgramasSearchRepository programasSearchRepository;

    public ProgramasService(ProgramasRepository programasRepository, ProgramasSearchRepository programasSearchRepository) {
        this.programasRepository = programasRepository;
        this.programasSearchRepository = programasSearchRepository;
    }

    /**
     * Save a programas.
     *
     * @param programas the entity to save.
     * @return the persisted entity.
     */
    public Programas save(Programas programas) {
        log.debug("Request to save Programas : {}", programas);
        Programas result = programasRepository.save(programas);
        programasSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the programas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Programas> findAll(Pageable pageable) {
        log.debug("Request to get all Programas");
        return programasRepository.findAll(pageable);
    }


    /**
     * Get one programas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Programas> findOne(Long id) {
        log.debug("Request to get Programas : {}", id);
        return programasRepository.findById(id);
    }

    /**
     * Delete the programas by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Programas : {}", id);
        programasRepository.deleteById(id);
        programasSearchRepository.deleteById(id);
    }

    /**
     * Search for the programas corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Programas> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Programas for query {}", query);
        return programasSearchRepository.search(queryStringQuery(query), pageable);    }
}
