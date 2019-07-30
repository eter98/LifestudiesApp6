package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.service.CotizacionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.CotizacionCriteria;
import io.github.jhipster.application.service.CotizacionQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Cotizacion}.
 */
@RestController
@RequestMapping("/api")
public class CotizacionResource {

    private final Logger log = LoggerFactory.getLogger(CotizacionResource.class);

    private static final String ENTITY_NAME = "cotizacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CotizacionService cotizacionService;

    private final CotizacionQueryService cotizacionQueryService;

    public CotizacionResource(CotizacionService cotizacionService, CotizacionQueryService cotizacionQueryService) {
        this.cotizacionService = cotizacionService;
        this.cotizacionQueryService = cotizacionQueryService;
    }

    /**
     * {@code POST  /cotizacions} : Create a new cotizacion.
     *
     * @param cotizacion the cotizacion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cotizacion, or with status {@code 400 (Bad Request)} if the cotizacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cotizacions")
    public ResponseEntity<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion) throws URISyntaxException {
        log.debug("REST request to save Cotizacion : {}", cotizacion);
        if (cotizacion.getId() != null) {
            throw new BadRequestAlertException("A new cotizacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cotizacion result = cotizacionService.save(cotizacion);
        return ResponseEntity.created(new URI("/api/cotizacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cotizacions} : Updates an existing cotizacion.
     *
     * @param cotizacion the cotizacion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cotizacion,
     * or with status {@code 400 (Bad Request)} if the cotizacion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cotizacion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cotizacions")
    public ResponseEntity<Cotizacion> updateCotizacion(@RequestBody Cotizacion cotizacion) throws URISyntaxException {
        log.debug("REST request to update Cotizacion : {}", cotizacion);
        if (cotizacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cotizacion result = cotizacionService.save(cotizacion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cotizacion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cotizacions} : get all the cotizacions.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cotizacions in body.
     */
    @GetMapping("/cotizacions")
    public ResponseEntity<List<Cotizacion>> getAllCotizacions(CotizacionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Cotizacions by criteria: {}", criteria);
        Page<Cotizacion> page = cotizacionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cotizacions/count} : count all the cotizacions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cotizacions/count")
    public ResponseEntity<Long> countCotizacions(CotizacionCriteria criteria) {
        log.debug("REST request to count Cotizacions by criteria: {}", criteria);
        return ResponseEntity.ok().body(cotizacionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cotizacions/:id} : get the "id" cotizacion.
     *
     * @param id the id of the cotizacion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cotizacion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cotizacions/{id}")
    public ResponseEntity<Cotizacion> getCotizacion(@PathVariable Long id) {
        log.debug("REST request to get Cotizacion : {}", id);
        Optional<Cotizacion> cotizacion = cotizacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cotizacion);
    }

    /**
     * {@code DELETE  /cotizacions/:id} : delete the "id" cotizacion.
     *
     * @param id the id of the cotizacion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cotizacions/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable Long id) {
        log.debug("REST request to delete Cotizacion : {}", id);
        cotizacionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/cotizacions?query=:query} : search for the cotizacion corresponding
     * to the query.
     *
     * @param query the query of the cotizacion search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/cotizacions")
    public ResponseEntity<List<Cotizacion>> searchCotizacions(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Cotizacions for query {}", query);
        Page<Cotizacion> page = cotizacionService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
