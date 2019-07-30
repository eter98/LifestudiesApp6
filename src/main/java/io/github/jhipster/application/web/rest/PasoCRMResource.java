package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.PasoCRM;
import io.github.jhipster.application.service.PasoCRMService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PasoCRMCriteria;
import io.github.jhipster.application.service.PasoCRMQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PasoCRM}.
 */
@RestController
@RequestMapping("/api")
public class PasoCRMResource {

    private final Logger log = LoggerFactory.getLogger(PasoCRMResource.class);

    private static final String ENTITY_NAME = "pasoCRM";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PasoCRMService pasoCRMService;

    private final PasoCRMQueryService pasoCRMQueryService;

    public PasoCRMResource(PasoCRMService pasoCRMService, PasoCRMQueryService pasoCRMQueryService) {
        this.pasoCRMService = pasoCRMService;
        this.pasoCRMQueryService = pasoCRMQueryService;
    }

    /**
     * {@code POST  /paso-crms} : Create a new pasoCRM.
     *
     * @param pasoCRM the pasoCRM to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pasoCRM, or with status {@code 400 (Bad Request)} if the pasoCRM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paso-crms")
    public ResponseEntity<PasoCRM> createPasoCRM(@RequestBody PasoCRM pasoCRM) throws URISyntaxException {
        log.debug("REST request to save PasoCRM : {}", pasoCRM);
        if (pasoCRM.getId() != null) {
            throw new BadRequestAlertException("A new pasoCRM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PasoCRM result = pasoCRMService.save(pasoCRM);
        return ResponseEntity.created(new URI("/api/paso-crms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paso-crms} : Updates an existing pasoCRM.
     *
     * @param pasoCRM the pasoCRM to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pasoCRM,
     * or with status {@code 400 (Bad Request)} if the pasoCRM is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pasoCRM couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paso-crms")
    public ResponseEntity<PasoCRM> updatePasoCRM(@RequestBody PasoCRM pasoCRM) throws URISyntaxException {
        log.debug("REST request to update PasoCRM : {}", pasoCRM);
        if (pasoCRM.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PasoCRM result = pasoCRMService.save(pasoCRM);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pasoCRM.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paso-crms} : get all the pasoCRMS.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pasoCRMS in body.
     */
    @GetMapping("/paso-crms")
    public ResponseEntity<List<PasoCRM>> getAllPasoCRMS(PasoCRMCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PasoCRMS by criteria: {}", criteria);
        Page<PasoCRM> page = pasoCRMQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /paso-crms/count} : count all the pasoCRMS.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/paso-crms/count")
    public ResponseEntity<Long> countPasoCRMS(PasoCRMCriteria criteria) {
        log.debug("REST request to count PasoCRMS by criteria: {}", criteria);
        return ResponseEntity.ok().body(pasoCRMQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /paso-crms/:id} : get the "id" pasoCRM.
     *
     * @param id the id of the pasoCRM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pasoCRM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paso-crms/{id}")
    public ResponseEntity<PasoCRM> getPasoCRM(@PathVariable Long id) {
        log.debug("REST request to get PasoCRM : {}", id);
        Optional<PasoCRM> pasoCRM = pasoCRMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pasoCRM);
    }

    /**
     * {@code DELETE  /paso-crms/:id} : delete the "id" pasoCRM.
     *
     * @param id the id of the pasoCRM to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paso-crms/{id}")
    public ResponseEntity<Void> deletePasoCRM(@PathVariable Long id) {
        log.debug("REST request to delete PasoCRM : {}", id);
        pasoCRMService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/paso-crms?query=:query} : search for the pasoCRM corresponding
     * to the query.
     *
     * @param query the query of the pasoCRM search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/paso-crms")
    public ResponseEntity<List<PasoCRM>> searchPasoCRMS(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of PasoCRMS for query {}", query);
        Page<PasoCRM> page = pasoCRMService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
