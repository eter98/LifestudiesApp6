package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.repository.TipoProgramaRepository;
import io.github.jhipster.application.repository.search.TipoProgramaSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TipoPrograma}.
 */
@Service
@Transactional
public class TipoProgramaService {

    private final Logger log = LoggerFactory.getLogger(TipoProgramaService.class);

    private final TipoProgramaRepository tipoProgramaRepository;

    private final TipoProgramaSearchRepository tipoProgramaSearchRepository;

    public TipoProgramaService(TipoProgramaRepository tipoProgramaRepository, TipoProgramaSearchRepository tipoProgramaSearchRepository) {
        this.tipoProgramaRepository = tipoProgramaRepository;
        this.tipoProgramaSearchRepository = tipoProgramaSearchRepository;
    }

    /**
     * Save a tipoPrograma.
     *
     * @param tipoPrograma the entity to save.
     * @return the persisted entity.
     */
    public TipoPrograma save(TipoPrograma tipoPrograma) {
        log.debug("Request to save TipoPrograma : {}", tipoPrograma);
        TipoPrograma result = tipoProgramaRepository.save(tipoPrograma);
        tipoProgramaSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the tipoProgramas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoPrograma> findAll(Pageable pageable) {
        log.debug("Request to get all TipoProgramas");
        return tipoProgramaRepository.findAll(pageable);
    }


    /**
     * Get one tipoPrograma by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TipoPrograma> findOne(Long id) {
        log.debug("Request to get TipoPrograma : {}", id);
        return tipoProgramaRepository.findById(id);
    }

    /**
     * Delete the tipoPrograma by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoPrograma : {}", id);
        tipoProgramaRepository.deleteById(id);
        tipoProgramaSearchRepository.deleteById(id);
    }

    /**
     * Search for the tipoPrograma corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoPrograma> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TipoProgramas for query {}", query);
        return tipoProgramaSearchRepository.search(queryStringQuery(query), pageable);    }
}
