package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.service.PerfilUsuarioService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PerfilUsuarioCriteria;
import io.github.jhipster.application.service.PerfilUsuarioQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.PerfilUsuario}.
 */
@RestController
@RequestMapping("/api")
public class PerfilUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(PerfilUsuarioResource.class);

    private static final String ENTITY_NAME = "perfilUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerfilUsuarioService perfilUsuarioService;

    private final PerfilUsuarioQueryService perfilUsuarioQueryService;

    public PerfilUsuarioResource(PerfilUsuarioService perfilUsuarioService, PerfilUsuarioQueryService perfilUsuarioQueryService) {
        this.perfilUsuarioService = perfilUsuarioService;
        this.perfilUsuarioQueryService = perfilUsuarioQueryService;
    }

    /**
     * {@code POST  /perfil-usuarios} : Create a new perfilUsuario.
     *
     * @param perfilUsuario the perfilUsuario to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perfilUsuario, or with status {@code 400 (Bad Request)} if the perfilUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/perfil-usuarios")
    public ResponseEntity<PerfilUsuario> createPerfilUsuario(@RequestBody PerfilUsuario perfilUsuario) throws URISyntaxException {
        log.debug("REST request to save PerfilUsuario : {}", perfilUsuario);
        if (perfilUsuario.getId() != null) {
            throw new BadRequestAlertException("A new perfilUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilUsuario result = perfilUsuarioService.save(perfilUsuario);
        return ResponseEntity.created(new URI("/api/perfil-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /perfil-usuarios} : Updates an existing perfilUsuario.
     *
     * @param perfilUsuario the perfilUsuario to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perfilUsuario,
     * or with status {@code 400 (Bad Request)} if the perfilUsuario is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perfilUsuario couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/perfil-usuarios")
    public ResponseEntity<PerfilUsuario> updatePerfilUsuario(@RequestBody PerfilUsuario perfilUsuario) throws URISyntaxException {
        log.debug("REST request to update PerfilUsuario : {}", perfilUsuario);
        if (perfilUsuario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilUsuario result = perfilUsuarioService.save(perfilUsuario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, perfilUsuario.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /perfil-usuarios} : get all the perfilUsuarios.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of perfilUsuarios in body.
     */
    @GetMapping("/perfil-usuarios")
    public ResponseEntity<List<PerfilUsuario>> getAllPerfilUsuarios(PerfilUsuarioCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PerfilUsuarios by criteria: {}", criteria);
        Page<PerfilUsuario> page = perfilUsuarioQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /perfil-usuarios/count} : count all the perfilUsuarios.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/perfil-usuarios/count")
    public ResponseEntity<Long> countPerfilUsuarios(PerfilUsuarioCriteria criteria) {
        log.debug("REST request to count PerfilUsuarios by criteria: {}", criteria);
        return ResponseEntity.ok().body(perfilUsuarioQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /perfil-usuarios/:id} : get the "id" perfilUsuario.
     *
     * @param id the id of the perfilUsuario to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perfilUsuario, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/perfil-usuarios/{id}")
    public ResponseEntity<PerfilUsuario> getPerfilUsuario(@PathVariable Long id) {
        log.debug("REST request to get PerfilUsuario : {}", id);
        Optional<PerfilUsuario> perfilUsuario = perfilUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(perfilUsuario);
    }

    /**
     * {@code DELETE  /perfil-usuarios/:id} : delete the "id" perfilUsuario.
     *
     * @param id the id of the perfilUsuario to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/perfil-usuarios/{id}")
    public ResponseEntity<Void> deletePerfilUsuario(@PathVariable Long id) {
        log.debug("REST request to delete PerfilUsuario : {}", id);
        perfilUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/perfil-usuarios?query=:query} : search for the perfilUsuario corresponding
     * to the query.
     *
     * @param query the query of the perfilUsuario search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/perfil-usuarios")
    public ResponseEntity<List<PerfilUsuario>> searchPerfilUsuarios(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of PerfilUsuarios for query {}", query);
        Page<PerfilUsuario> page = perfilUsuarioService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
