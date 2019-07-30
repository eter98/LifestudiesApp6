package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.repository.PerfilUsuarioRepository;
import io.github.jhipster.application.repository.search.PerfilUsuarioSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link PerfilUsuario}.
 */
@Service
@Transactional
public class PerfilUsuarioService {

    private final Logger log = LoggerFactory.getLogger(PerfilUsuarioService.class);

    private final PerfilUsuarioRepository perfilUsuarioRepository;

    private final PerfilUsuarioSearchRepository perfilUsuarioSearchRepository;

    public PerfilUsuarioService(PerfilUsuarioRepository perfilUsuarioRepository, PerfilUsuarioSearchRepository perfilUsuarioSearchRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.perfilUsuarioSearchRepository = perfilUsuarioSearchRepository;
    }

    /**
     * Save a perfilUsuario.
     *
     * @param perfilUsuario the entity to save.
     * @return the persisted entity.
     */
    public PerfilUsuario save(PerfilUsuario perfilUsuario) {
        log.debug("Request to save PerfilUsuario : {}", perfilUsuario);
        PerfilUsuario result = perfilUsuarioRepository.save(perfilUsuario);
        perfilUsuarioSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the perfilUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PerfilUsuario> findAll(Pageable pageable) {
        log.debug("Request to get all PerfilUsuarios");
        return perfilUsuarioRepository.findAll(pageable);
    }


    /**
     * Get one perfilUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PerfilUsuario> findOne(Long id) {
        log.debug("Request to get PerfilUsuario : {}", id);
        return perfilUsuarioRepository.findById(id);
    }

    /**
     * Delete the perfilUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PerfilUsuario : {}", id);
        perfilUsuarioRepository.deleteById(id);
        perfilUsuarioSearchRepository.deleteById(id);
    }

    /**
     * Search for the perfilUsuario corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PerfilUsuario> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PerfilUsuarios for query {}", query);
        return perfilUsuarioSearchRepository.search(queryStringQuery(query), pageable);    }
}
