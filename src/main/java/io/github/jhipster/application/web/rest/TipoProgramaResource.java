package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.service.TipoProgramaService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.TipoProgramaCriteria;
import io.github.jhipster.application.service.TipoProgramaQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.TipoPrograma}.
 */
@RestController
@RequestMapping("/api")
public class TipoProgramaResource {

    private final Logger log = LoggerFactory.getLogger(TipoProgramaResource.class);

    private static final String ENTITY_NAME = "tipoPrograma";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoProgramaService tipoProgramaService;

    private final TipoProgramaQueryService tipoProgramaQueryService;

    public TipoProgramaResource(TipoProgramaService tipoProgramaService, TipoProgramaQueryService tipoProgramaQueryService) {
        this.tipoProgramaService = tipoProgramaService;
        this.tipoProgramaQueryService = tipoProgramaQueryService;
    }

    /**
     * {@code POST  /tipo-programas} : Create a new tipoPrograma.
     *
     * @param tipoPrograma the tipoPrograma to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoPrograma, or with status {@code 400 (Bad Request)} if the tipoPrograma has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-programas")
    public ResponseEntity<TipoPrograma> createTipoPrograma(@RequestBody TipoPrograma tipoPrograma) throws URISyntaxException {
        log.debug("REST request to save TipoPrograma : {}", tipoPrograma);
        if (tipoPrograma.getId() != null) {
            throw new BadRequestAlertException("A new tipoPrograma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoPrograma result = tipoProgramaService.save(tipoPrograma);
        return ResponseEntity.created(new URI("/api/tipo-programas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-programas} : Updates an existing tipoPrograma.
     *
     * @param tipoPrograma the tipoPrograma to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoPrograma,
     * or with status {@code 400 (Bad Request)} if the tipoPrograma is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoPrograma couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-programas")
    public ResponseEntity<TipoPrograma> updateTipoPrograma(@RequestBody TipoPrograma tipoPrograma) throws URISyntaxException {
        log.debug("REST request to update TipoPrograma : {}", tipoPrograma);
        if (tipoPrograma.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoPrograma result = tipoProgramaService.save(tipoPrograma);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoPrograma.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-programas} : get all the tipoProgramas.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoProgramas in body.
     */
    @GetMapping("/tipo-programas")
    public ResponseEntity<List<TipoPrograma>> getAllTipoProgramas(TipoProgramaCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get TipoProgramas by criteria: {}", criteria);
        Page<TipoPrograma> page = tipoProgramaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /tipo-programas/count} : count all the tipoProgramas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/tipo-programas/count")
    public ResponseEntity<Long> countTipoProgramas(TipoProgramaCriteria criteria) {
        log.debug("REST request to count TipoProgramas by criteria: {}", criteria);
        return ResponseEntity.ok().body(tipoProgramaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tipo-programas/:id} : get the "id" tipoPrograma.
     *
     * @param id the id of the tipoPrograma to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoPrograma, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-programas/{id}")
    public ResponseEntity<TipoPrograma> getTipoPrograma(@PathVariable Long id) {
        log.debug("REST request to get TipoPrograma : {}", id);
        Optional<TipoPrograma> tipoPrograma = tipoProgramaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoPrograma);
    }

    /**
     * {@code DELETE  /tipo-programas/:id} : delete the "id" tipoPrograma.
     *
     * @param id the id of the tipoPrograma to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-programas/{id}")
    public ResponseEntity<Void> deleteTipoPrograma(@PathVariable Long id) {
        log.debug("REST request to delete TipoPrograma : {}", id);
        tipoProgramaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tipo-programas?query=:query} : search for the tipoPrograma corresponding
     * to the query.
     *
     * @param query the query of the tipoPrograma search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/tipo-programas")
    public ResponseEntity<List<TipoPrograma>> searchTipoProgramas(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of TipoProgramas for query {}", query);
        Page<TipoPrograma> page = tipoProgramaService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
