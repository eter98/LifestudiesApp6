package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.repository.TipoProgramaRepository;
import io.github.jhipster.application.repository.search.TipoProgramaSearchRepository;
import io.github.jhipster.application.service.TipoProgramaService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.TipoProgramaCriteria;
import io.github.jhipster.application.service.TipoProgramaQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link TipoProgramaResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class TipoProgramaResourceIT {

    private static final String DEFAULT_TIPO_PROGRAMA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_PROGRAMA = "BBBBBBBBBB";

    @Autowired
    private TipoProgramaRepository tipoProgramaRepository;

    @Autowired
    private TipoProgramaService tipoProgramaService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.TipoProgramaSearchRepositoryMockConfiguration
     */
    @Autowired
    private TipoProgramaSearchRepository mockTipoProgramaSearchRepository;

    @Autowired
    private TipoProgramaQueryService tipoProgramaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTipoProgramaMockMvc;

    private TipoPrograma tipoPrograma;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoProgramaResource tipoProgramaResource = new TipoProgramaResource(tipoProgramaService, tipoProgramaQueryService);
        this.restTipoProgramaMockMvc = MockMvcBuilders.standaloneSetup(tipoProgramaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoPrograma createEntity(EntityManager em) {
        TipoPrograma tipoPrograma = new TipoPrograma()
            .tipoPrograma(DEFAULT_TIPO_PROGRAMA);
        return tipoPrograma;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoPrograma createUpdatedEntity(EntityManager em) {
        TipoPrograma tipoPrograma = new TipoPrograma()
            .tipoPrograma(UPDATED_TIPO_PROGRAMA);
        return tipoPrograma;
    }

    @BeforeEach
    public void initTest() {
        tipoPrograma = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoPrograma() throws Exception {
        int databaseSizeBeforeCreate = tipoProgramaRepository.findAll().size();

        // Create the TipoPrograma
        restTipoProgramaMockMvc.perform(post("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrograma)))
            .andExpect(status().isCreated());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeCreate + 1);
        TipoPrograma testTipoPrograma = tipoProgramaList.get(tipoProgramaList.size() - 1);
        assertThat(testTipoPrograma.getTipoPrograma()).isEqualTo(DEFAULT_TIPO_PROGRAMA);

        // Validate the TipoPrograma in Elasticsearch
        verify(mockTipoProgramaSearchRepository, times(1)).save(testTipoPrograma);
    }

    @Test
    @Transactional
    public void createTipoProgramaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoProgramaRepository.findAll().size();

        // Create the TipoPrograma with an existing ID
        tipoPrograma.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoProgramaMockMvc.perform(post("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrograma)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeCreate);

        // Validate the TipoPrograma in Elasticsearch
        verify(mockTipoProgramaSearchRepository, times(0)).save(tipoPrograma);
    }


    @Test
    @Transactional
    public void getAllTipoProgramas() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get all the tipoProgramaList
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoPrograma.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get the tipoPrograma
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas/{id}", tipoPrograma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoPrograma.getId().intValue()))
            .andExpect(jsonPath("$.tipoPrograma").value(DEFAULT_TIPO_PROGRAMA.toString()));
    }

    @Test
    @Transactional
    public void getAllTipoProgramasByTipoProgramaIsEqualToSomething() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get all the tipoProgramaList where tipoPrograma equals to DEFAULT_TIPO_PROGRAMA
        defaultTipoProgramaShouldBeFound("tipoPrograma.equals=" + DEFAULT_TIPO_PROGRAMA);

        // Get all the tipoProgramaList where tipoPrograma equals to UPDATED_TIPO_PROGRAMA
        defaultTipoProgramaShouldNotBeFound("tipoPrograma.equals=" + UPDATED_TIPO_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllTipoProgramasByTipoProgramaIsInShouldWork() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get all the tipoProgramaList where tipoPrograma in DEFAULT_TIPO_PROGRAMA or UPDATED_TIPO_PROGRAMA
        defaultTipoProgramaShouldBeFound("tipoPrograma.in=" + DEFAULT_TIPO_PROGRAMA + "," + UPDATED_TIPO_PROGRAMA);

        // Get all the tipoProgramaList where tipoPrograma equals to UPDATED_TIPO_PROGRAMA
        defaultTipoProgramaShouldNotBeFound("tipoPrograma.in=" + UPDATED_TIPO_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllTipoProgramasByTipoProgramaIsNullOrNotNull() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get all the tipoProgramaList where tipoPrograma is not null
        defaultTipoProgramaShouldBeFound("tipoPrograma.specified=true");

        // Get all the tipoProgramaList where tipoPrograma is null
        defaultTipoProgramaShouldNotBeFound("tipoPrograma.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTipoProgramaShouldBeFound(String filter) throws Exception {
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoPrograma.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA)));

        // Check, that the count call also returns 1
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTipoProgramaShouldNotBeFound(String filter) throws Exception {
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTipoPrograma() throws Exception {
        // Get the tipoPrograma
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaService.save(tipoPrograma);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockTipoProgramaSearchRepository);

        int databaseSizeBeforeUpdate = tipoProgramaRepository.findAll().size();

        // Update the tipoPrograma
        TipoPrograma updatedTipoPrograma = tipoProgramaRepository.findById(tipoPrograma.getId()).get();
        // Disconnect from session so that the updates on updatedTipoPrograma are not directly saved in db
        em.detach(updatedTipoPrograma);
        updatedTipoPrograma
            .tipoPrograma(UPDATED_TIPO_PROGRAMA);

        restTipoProgramaMockMvc.perform(put("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoPrograma)))
            .andExpect(status().isOk());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeUpdate);
        TipoPrograma testTipoPrograma = tipoProgramaList.get(tipoProgramaList.size() - 1);
        assertThat(testTipoPrograma.getTipoPrograma()).isEqualTo(UPDATED_TIPO_PROGRAMA);

        // Validate the TipoPrograma in Elasticsearch
        verify(mockTipoProgramaSearchRepository, times(1)).save(testTipoPrograma);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoPrograma() throws Exception {
        int databaseSizeBeforeUpdate = tipoProgramaRepository.findAll().size();

        // Create the TipoPrograma

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoProgramaMockMvc.perform(put("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrograma)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TipoPrograma in Elasticsearch
        verify(mockTipoProgramaSearchRepository, times(0)).save(tipoPrograma);
    }

    @Test
    @Transactional
    public void deleteTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaService.save(tipoPrograma);

        int databaseSizeBeforeDelete = tipoProgramaRepository.findAll().size();

        // Delete the tipoPrograma
        restTipoProgramaMockMvc.perform(delete("/api/tipo-programas/{id}", tipoPrograma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TipoPrograma in Elasticsearch
        verify(mockTipoProgramaSearchRepository, times(1)).deleteById(tipoPrograma.getId());
    }

    @Test
    @Transactional
    public void searchTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaService.save(tipoPrograma);
        when(mockTipoProgramaSearchRepository.search(queryStringQuery("id:" + tipoPrograma.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(tipoPrograma), PageRequest.of(0, 1), 1));
        // Search the tipoPrograma
        restTipoProgramaMockMvc.perform(get("/api/_search/tipo-programas?query=id:" + tipoPrograma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoPrograma.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoPrograma.class);
        TipoPrograma tipoPrograma1 = new TipoPrograma();
        tipoPrograma1.setId(1L);
        TipoPrograma tipoPrograma2 = new TipoPrograma();
        tipoPrograma2.setId(tipoPrograma1.getId());
        assertThat(tipoPrograma1).isEqualTo(tipoPrograma2);
        tipoPrograma2.setId(2L);
        assertThat(tipoPrograma1).isNotEqualTo(tipoPrograma2);
        tipoPrograma1.setId(null);
        assertThat(tipoPrograma1).isNotEqualTo(tipoPrograma2);
    }
}
