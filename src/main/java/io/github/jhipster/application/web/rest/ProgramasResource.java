package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.service.ProgramasService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ProgramasCriteria;
import io.github.jhipster.application.service.ProgramasQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Programas}.
 */
@RestController
@RequestMapping("/api")
public class ProgramasResource {

    private final Logger log = LoggerFactory.getLogger(ProgramasResource.class);

    private static final String ENTITY_NAME = "programas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProgramasService programasService;

    private final ProgramasQueryService programasQueryService;

    public ProgramasResource(ProgramasService programasService, ProgramasQueryService programasQueryService) {
        this.programasService = programasService;
        this.programasQueryService = programasQueryService;
    }

    /**
     * {@code POST  /programas} : Create a new programas.
     *
     * @param programas the programas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new programas, or with status {@code 400 (Bad Request)} if the programas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/programas")
    public ResponseEntity<Programas> createProgramas(@RequestBody Programas programas) throws URISyntaxException {
        log.debug("REST request to save Programas : {}", programas);
        if (programas.getId() != null) {
            throw new BadRequestAlertException("A new programas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Programas result = programasService.save(programas);
        return ResponseEntity.created(new URI("/api/programas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /programas} : Updates an existing programas.
     *
     * @param programas the programas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated programas,
     * or with status {@code 400 (Bad Request)} if the programas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the programas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/programas")
    public ResponseEntity<Programas> updateProgramas(@RequestBody Programas programas) throws URISyntaxException {
        log.debug("REST request to update Programas : {}", programas);
        if (programas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Programas result = programasService.save(programas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, programas.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /programas} : get all the programas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of programas in body.
     */
    @GetMapping("/programas")
    public ResponseEntity<List<Programas>> getAllProgramas(ProgramasCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Programas by criteria: {}", criteria);
        Page<Programas> page = programasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /programas/count} : count all the programas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/programas/count")
    public ResponseEntity<Long> countProgramas(ProgramasCriteria criteria) {
        log.debug("REST request to count Programas by criteria: {}", criteria);
        return ResponseEntity.ok().body(programasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /programas/:id} : get the "id" programas.
     *
     * @param id the id of the programas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the programas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/programas/{id}")
    public ResponseEntity<Programas> getProgramas(@PathVariable Long id) {
        log.debug("REST request to get Programas : {}", id);
        Optional<Programas> programas = programasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(programas);
    }

    /**
     * {@code DELETE  /programas/:id} : delete the "id" programas.
     *
     * @param id the id of the programas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/programas/{id}")
    public ResponseEntity<Void> deleteProgramas(@PathVariable Long id) {
        log.debug("REST request to delete Programas : {}", id);
        programasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/programas?query=:query} : search for the programas corresponding
     * to the query.
     *
     * @param query the query of the programas search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/programas")
    public ResponseEntity<List<Programas>> searchProgramas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Programas for query {}", query);
        Page<Programas> page = programasService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
