package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PasoCRM;
import io.github.jhipster.application.repository.PasoCRMRepository;
import io.github.jhipster.application.repository.search.PasoCRMSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link PasoCRM}.
 */
@Service
@Transactional
public class PasoCRMService {

    private final Logger log = LoggerFactory.getLogger(PasoCRMService.class);

    private final PasoCRMRepository pasoCRMRepository;

    private final PasoCRMSearchRepository pasoCRMSearchRepository;

    public PasoCRMService(PasoCRMRepository pasoCRMRepository, PasoCRMSearchRepository pasoCRMSearchRepository) {
        this.pasoCRMRepository = pasoCRMRepository;
        this.pasoCRMSearchRepository = pasoCRMSearchRepository;
    }

    /**
     * Save a pasoCRM.
     *
     * @param pasoCRM the entity to save.
     * @return the persisted entity.
     */
    public PasoCRM save(PasoCRM pasoCRM) {
        log.debug("Request to save PasoCRM : {}", pasoCRM);
        PasoCRM result = pasoCRMRepository.save(pasoCRM);
        pasoCRMSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the pasoCRMS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PasoCRM> findAll(Pageable pageable) {
        log.debug("Request to get all PasoCRMS");
        return pasoCRMRepository.findAll(pageable);
    }


    /**
     * Get one pasoCRM by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PasoCRM> findOne(Long id) {
        log.debug("Request to get PasoCRM : {}", id);
        return pasoCRMRepository.findById(id);
    }

    /**
     * Delete the pasoCRM by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PasoCRM : {}", id);
        pasoCRMRepository.deleteById(id);
        pasoCRMSearchRepository.deleteById(id);
    }

    /**
     * Search for the pasoCRM corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PasoCRM> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PasoCRMS for query {}", query);
        return pasoCRMSearchRepository.search(queryStringQuery(query), pageable);    }
}
