package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.BlogContenido;
import io.github.jhipster.application.repository.BlogContenidoRepository;
import io.github.jhipster.application.repository.search.BlogContenidoSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link BlogContenido}.
 */
@Service
@Transactional
public class BlogContenidoService {

    private final Logger log = LoggerFactory.getLogger(BlogContenidoService.class);

    private final BlogContenidoRepository blogContenidoRepository;

    private final BlogContenidoSearchRepository blogContenidoSearchRepository;

    public BlogContenidoService(BlogContenidoRepository blogContenidoRepository, BlogContenidoSearchRepository blogContenidoSearchRepository) {
        this.blogContenidoRepository = blogContenidoRepository;
        this.blogContenidoSearchRepository = blogContenidoSearchRepository;
    }

    /**
     * Save a blogContenido.
     *
     * @param blogContenido the entity to save.
     * @return the persisted entity.
     */
    public BlogContenido save(BlogContenido blogContenido) {
        log.debug("Request to save BlogContenido : {}", blogContenido);
        BlogContenido result = blogContenidoRepository.save(blogContenido);
        blogContenidoSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the blogContenidos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlogContenido> findAll(Pageable pageable) {
        log.debug("Request to get all BlogContenidos");
        return blogContenidoRepository.findAll(pageable);
    }


    /**
     * Get one blogContenido by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BlogContenido> findOne(Long id) {
        log.debug("Request to get BlogContenido : {}", id);
        return blogContenidoRepository.findById(id);
    }

    /**
     * Delete the blogContenido by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BlogContenido : {}", id);
        blogContenidoRepository.deleteById(id);
        blogContenidoSearchRepository.deleteById(id);
    }

    /**
     * Search for the blogContenido corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlogContenido> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of BlogContenidos for query {}", query);
        return blogContenidoSearchRepository.search(queryStringQuery(query), pageable);    }
}
