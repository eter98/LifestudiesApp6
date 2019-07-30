package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.ViabilidadVisa;
import io.github.jhipster.application.service.ViabilidadVisaService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ViabilidadVisaCriteria;
import io.github.jhipster.application.service.ViabilidadVisaQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ViabilidadVisa}.
 */
@RestController
@RequestMapping("/api")
public class ViabilidadVisaResource {

    private final Logger log = LoggerFactory.getLogger(ViabilidadVisaResource.class);

    private static final String ENTITY_NAME = "viabilidadVisa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViabilidadVisaService viabilidadVisaService;

    private final ViabilidadVisaQueryService viabilidadVisaQueryService;

    public ViabilidadVisaResource(ViabilidadVisaService viabilidadVisaService, ViabilidadVisaQueryService viabilidadVisaQueryService) {
        this.viabilidadVisaService = viabilidadVisaService;
        this.viabilidadVisaQueryService = viabilidadVisaQueryService;
    }

    /**
     * {@code POST  /viabilidad-visas} : Create a new viabilidadVisa.
     *
     * @param viabilidadVisa the viabilidadVisa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viabilidadVisa, or with status {@code 400 (Bad Request)} if the viabilidadVisa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/viabilidad-visas")
    public ResponseEntity<ViabilidadVisa> createViabilidadVisa(@RequestBody ViabilidadVisa viabilidadVisa) throws URISyntaxException {
        log.debug("REST request to save ViabilidadVisa : {}", viabilidadVisa);
        if (viabilidadVisa.getId() != null) {
            throw new BadRequestAlertException("A new viabilidadVisa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViabilidadVisa result = viabilidadVisaService.save(viabilidadVisa);
        return ResponseEntity.created(new URI("/api/viabilidad-visas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /viabilidad-visas} : Updates an existing viabilidadVisa.
     *
     * @param viabilidadVisa the viabilidadVisa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viabilidadVisa,
     * or with status {@code 400 (Bad Request)} if the viabilidadVisa is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viabilidadVisa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/viabilidad-visas")
    public ResponseEntity<ViabilidadVisa> updateViabilidadVisa(@RequestBody ViabilidadVisa viabilidadVisa) throws URISyntaxException {
        log.debug("REST request to update ViabilidadVisa : {}", viabilidadVisa);
        if (viabilidadVisa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ViabilidadVisa result = viabilidadVisaService.save(viabilidadVisa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viabilidadVisa.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /viabilidad-visas} : get all the viabilidadVisas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viabilidadVisas in body.
     */
    @GetMapping("/viabilidad-visas")
    public ResponseEntity<List<ViabilidadVisa>> getAllViabilidadVisas(ViabilidadVisaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ViabilidadVisas by criteria: {}", criteria);
        Page<ViabilidadVisa> page = viabilidadVisaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /viabilidad-visas/count} : count all the viabilidadVisas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/viabilidad-visas/count")
    public ResponseEntity<Long> countViabilidadVisas(ViabilidadVisaCriteria criteria) {
        log.debug("REST request to count ViabilidadVisas by criteria: {}", criteria);
        return ResponseEntity.ok().body(viabilidadVisaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /viabilidad-visas/:id} : get the "id" viabilidadVisa.
     *
     * @param id the id of the viabilidadVisa to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viabilidadVisa, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/viabilidad-visas/{id}")
    public ResponseEntity<ViabilidadVisa> getViabilidadVisa(@PathVariable Long id) {
        log.debug("REST request to get ViabilidadVisa : {}", id);
        Optional<ViabilidadVisa> viabilidadVisa = viabilidadVisaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viabilidadVisa);
    }

    /**
     * {@code DELETE  /viabilidad-visas/:id} : delete the "id" viabilidadVisa.
     *
     * @param id the id of the viabilidadVisa to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/viabilidad-visas/{id}")
    public ResponseEntity<Void> deleteViabilidadVisa(@PathVariable Long id) {
        log.debug("REST request to delete ViabilidadVisa : {}", id);
        viabilidadVisaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/viabilidad-visas?query=:query} : search for the viabilidadVisa corresponding
     * to the query.
     *
     * @param query the query of the viabilidadVisa search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/viabilidad-visas")
    public ResponseEntity<List<ViabilidadVisa>> searchViabilidadVisas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ViabilidadVisas for query {}", query);
        Page<ViabilidadVisa> page = viabilidadVisaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
