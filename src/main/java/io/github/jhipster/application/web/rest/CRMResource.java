package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.CRM;
import io.github.jhipster.application.service.CRMService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.CRMCriteria;
import io.github.jhipster.application.service.CRMQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.CRM}.
 */
@RestController
@RequestMapping("/api")
public class CRMResource {

    private final Logger log = LoggerFactory.getLogger(CRMResource.class);

    private static final String ENTITY_NAME = "cRM";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CRMService cRMService;

    private final CRMQueryService cRMQueryService;

    public CRMResource(CRMService cRMService, CRMQueryService cRMQueryService) {
        this.cRMService = cRMService;
        this.cRMQueryService = cRMQueryService;
    }

    /**
     * {@code POST  /crms} : Create a new cRM.
     *
     * @param cRM the cRM to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cRM, or with status {@code 400 (Bad Request)} if the cRM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/crms")
    public ResponseEntity<CRM> createCRM(@RequestBody CRM cRM) throws URISyntaxException {
        log.debug("REST request to save CRM : {}", cRM);
        if (cRM.getId() != null) {
            throw new BadRequestAlertException("A new cRM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CRM result = cRMService.save(cRM);
        return ResponseEntity.created(new URI("/api/crms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /crms} : Updates an existing cRM.
     *
     * @param cRM the cRM to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cRM,
     * or with status {@code 400 (Bad Request)} if the cRM is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cRM couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/crms")
    public ResponseEntity<CRM> updateCRM(@RequestBody CRM cRM) throws URISyntaxException {
        log.debug("REST request to update CRM : {}", cRM);
        if (cRM.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CRM result = cRMService.save(cRM);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cRM.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /crms} : get all the cRMS.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cRMS in body.
     */
    @GetMapping("/crms")
    public ResponseEntity<List<CRM>> getAllCRMS(CRMCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CRMS by criteria: {}", criteria);
        Page<CRM> page = cRMQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /crms/count} : count all the cRMS.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/crms/count")
    public ResponseEntity<Long> countCRMS(CRMCriteria criteria) {
        log.debug("REST request to count CRMS by criteria: {}", criteria);
        return ResponseEntity.ok().body(cRMQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /crms/:id} : get the "id" cRM.
     *
     * @param id the id of the cRM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cRM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/crms/{id}")
    public ResponseEntity<CRM> getCRM(@PathVariable Long id) {
        log.debug("REST request to get CRM : {}", id);
        Optional<CRM> cRM = cRMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cRM);
    }

    /**
     * {@code DELETE  /crms/:id} : delete the "id" cRM.
     *
     * @param id the id of the cRM to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/crms/{id}")
    public ResponseEntity<Void> deleteCRM(@PathVariable Long id) {
        log.debug("REST request to delete CRM : {}", id);
        cRMService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/crms?query=:query} : search for the cRM corresponding
     * to the query.
     *
     * @param query the query of the cRM search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/crms")
    public ResponseEntity<List<CRM>> searchCRMS(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of CRMS for query {}", query);
        Page<CRM> page = cRMService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
