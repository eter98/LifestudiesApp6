package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.CRM;
import io.github.jhipster.application.repository.CRMRepository;
import io.github.jhipster.application.repository.search.CRMSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CRM}.
 */
@Service
@Transactional
public class CRMService {

    private final Logger log = LoggerFactory.getLogger(CRMService.class);

    private final CRMRepository cRMRepository;

    private final CRMSearchRepository cRMSearchRepository;

    public CRMService(CRMRepository cRMRepository, CRMSearchRepository cRMSearchRepository) {
        this.cRMRepository = cRMRepository;
        this.cRMSearchRepository = cRMSearchRepository;
    }

    /**
     * Save a cRM.
     *
     * @param cRM the entity to save.
     * @return the persisted entity.
     */
    public CRM save(CRM cRM) {
        log.debug("Request to save CRM : {}", cRM);
        CRM result = cRMRepository.save(cRM);
        cRMSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the cRMS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CRM> findAll(Pageable pageable) {
        log.debug("Request to get all CRMS");
        return cRMRepository.findAll(pageable);
    }


    /**
     * Get one cRM by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CRM> findOne(Long id) {
        log.debug("Request to get CRM : {}", id);
        return cRMRepository.findById(id);
    }

    /**
     * Delete the cRM by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CRM : {}", id);
        cRMRepository.deleteById(id);
        cRMSearchRepository.deleteById(id);
    }

    /**
     * Search for the cRM corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CRM> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CRMS for query {}", query);
        return cRMSearchRepository.search(queryStringQuery(query), pageable);    }
}
