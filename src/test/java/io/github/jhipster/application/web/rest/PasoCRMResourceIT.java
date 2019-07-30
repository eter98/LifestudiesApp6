package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.PasoCRM;
import io.github.jhipster.application.repository.PasoCRMRepository;
import io.github.jhipster.application.repository.search.PasoCRMSearchRepository;
import io.github.jhipster.application.service.PasoCRMService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PasoCRMCriteria;
import io.github.jhipster.application.service.PasoCRMQueryService;

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

import io.github.jhipster.application.domain.enumeration.Estadod;
/**
 * Integration tests for the {@Link PasoCRMResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class PasoCRMResourceIT {

    private static final Integer DEFAULT_CODIGO = 1;
    private static final Integer UPDATED_CODIGO = 2;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURACION_DIAS = 1;
    private static final Integer UPDATED_DURACION_DIAS = 2;

    private static final Estadod DEFAULT_ESTADO = Estadod.Pendiente;
    private static final Estadod UPDATED_ESTADO = Estadod.EnProceso;

    @Autowired
    private PasoCRMRepository pasoCRMRepository;

    @Autowired
    private PasoCRMService pasoCRMService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.PasoCRMSearchRepositoryMockConfiguration
     */
    @Autowired
    private PasoCRMSearchRepository mockPasoCRMSearchRepository;

    @Autowired
    private PasoCRMQueryService pasoCRMQueryService;

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

    private MockMvc restPasoCRMMockMvc;

    private PasoCRM pasoCRM;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PasoCRMResource pasoCRMResource = new PasoCRMResource(pasoCRMService, pasoCRMQueryService);
        this.restPasoCRMMockMvc = MockMvcBuilders.standaloneSetup(pasoCRMResource)
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
    public static PasoCRM createEntity(EntityManager em) {
        PasoCRM pasoCRM = new PasoCRM()
            .codigo(DEFAULT_CODIGO)
            .descripcion(DEFAULT_DESCRIPCION)
            .duracionDias(DEFAULT_DURACION_DIAS)
            .estado(DEFAULT_ESTADO);
        return pasoCRM;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PasoCRM createUpdatedEntity(EntityManager em) {
        PasoCRM pasoCRM = new PasoCRM()
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .duracionDias(UPDATED_DURACION_DIAS)
            .estado(UPDATED_ESTADO);
        return pasoCRM;
    }

    @BeforeEach
    public void initTest() {
        pasoCRM = createEntity(em);
    }

    @Test
    @Transactional
    public void createPasoCRM() throws Exception {
        int databaseSizeBeforeCreate = pasoCRMRepository.findAll().size();

        // Create the PasoCRM
        restPasoCRMMockMvc.perform(post("/api/paso-crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pasoCRM)))
            .andExpect(status().isCreated());

        // Validate the PasoCRM in the database
        List<PasoCRM> pasoCRMList = pasoCRMRepository.findAll();
        assertThat(pasoCRMList).hasSize(databaseSizeBeforeCreate + 1);
        PasoCRM testPasoCRM = pasoCRMList.get(pasoCRMList.size() - 1);
        assertThat(testPasoCRM.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testPasoCRM.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPasoCRM.getDuracionDias()).isEqualTo(DEFAULT_DURACION_DIAS);
        assertThat(testPasoCRM.getEstado()).isEqualTo(DEFAULT_ESTADO);

        // Validate the PasoCRM in Elasticsearch
        verify(mockPasoCRMSearchRepository, times(1)).save(testPasoCRM);
    }

    @Test
    @Transactional
    public void createPasoCRMWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pasoCRMRepository.findAll().size();

        // Create the PasoCRM with an existing ID
        pasoCRM.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPasoCRMMockMvc.perform(post("/api/paso-crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pasoCRM)))
            .andExpect(status().isBadRequest());

        // Validate the PasoCRM in the database
        List<PasoCRM> pasoCRMList = pasoCRMRepository.findAll();
        assertThat(pasoCRMList).hasSize(databaseSizeBeforeCreate);

        // Validate the PasoCRM in Elasticsearch
        verify(mockPasoCRMSearchRepository, times(0)).save(pasoCRM);
    }


    @Test
    @Transactional
    public void getAllPasoCRMS() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList
        restPasoCRMMockMvc.perform(get("/api/paso-crms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pasoCRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].duracionDias").value(hasItem(DEFAULT_DURACION_DIAS)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getPasoCRM() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get the pasoCRM
        restPasoCRMMockMvc.perform(get("/api/paso-crms/{id}", pasoCRM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pasoCRM.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.duracionDias").value(DEFAULT_DURACION_DIAS))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByCodigoIsEqualToSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where codigo equals to DEFAULT_CODIGO
        defaultPasoCRMShouldBeFound("codigo.equals=" + DEFAULT_CODIGO);

        // Get all the pasoCRMList where codigo equals to UPDATED_CODIGO
        defaultPasoCRMShouldNotBeFound("codigo.equals=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByCodigoIsInShouldWork() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where codigo in DEFAULT_CODIGO or UPDATED_CODIGO
        defaultPasoCRMShouldBeFound("codigo.in=" + DEFAULT_CODIGO + "," + UPDATED_CODIGO);

        // Get all the pasoCRMList where codigo equals to UPDATED_CODIGO
        defaultPasoCRMShouldNotBeFound("codigo.in=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByCodigoIsNullOrNotNull() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where codigo is not null
        defaultPasoCRMShouldBeFound("codigo.specified=true");

        // Get all the pasoCRMList where codigo is null
        defaultPasoCRMShouldNotBeFound("codigo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByCodigoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where codigo greater than or equals to DEFAULT_CODIGO
        defaultPasoCRMShouldBeFound("codigo.greaterOrEqualThan=" + DEFAULT_CODIGO);

        // Get all the pasoCRMList where codigo greater than or equals to UPDATED_CODIGO
        defaultPasoCRMShouldNotBeFound("codigo.greaterOrEqualThan=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByCodigoIsLessThanSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where codigo less than or equals to DEFAULT_CODIGO
        defaultPasoCRMShouldNotBeFound("codigo.lessThan=" + DEFAULT_CODIGO);

        // Get all the pasoCRMList where codigo less than or equals to UPDATED_CODIGO
        defaultPasoCRMShouldBeFound("codigo.lessThan=" + UPDATED_CODIGO);
    }


    @Test
    @Transactional
    public void getAllPasoCRMSByDescripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where descripcion equals to DEFAULT_DESCRIPCION
        defaultPasoCRMShouldBeFound("descripcion.equals=" + DEFAULT_DESCRIPCION);

        // Get all the pasoCRMList where descripcion equals to UPDATED_DESCRIPCION
        defaultPasoCRMShouldNotBeFound("descripcion.equals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDescripcionIsInShouldWork() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where descripcion in DEFAULT_DESCRIPCION or UPDATED_DESCRIPCION
        defaultPasoCRMShouldBeFound("descripcion.in=" + DEFAULT_DESCRIPCION + "," + UPDATED_DESCRIPCION);

        // Get all the pasoCRMList where descripcion equals to UPDATED_DESCRIPCION
        defaultPasoCRMShouldNotBeFound("descripcion.in=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDescripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where descripcion is not null
        defaultPasoCRMShouldBeFound("descripcion.specified=true");

        // Get all the pasoCRMList where descripcion is null
        defaultPasoCRMShouldNotBeFound("descripcion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDuracionDiasIsEqualToSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where duracionDias equals to DEFAULT_DURACION_DIAS
        defaultPasoCRMShouldBeFound("duracionDias.equals=" + DEFAULT_DURACION_DIAS);

        // Get all the pasoCRMList where duracionDias equals to UPDATED_DURACION_DIAS
        defaultPasoCRMShouldNotBeFound("duracionDias.equals=" + UPDATED_DURACION_DIAS);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDuracionDiasIsInShouldWork() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where duracionDias in DEFAULT_DURACION_DIAS or UPDATED_DURACION_DIAS
        defaultPasoCRMShouldBeFound("duracionDias.in=" + DEFAULT_DURACION_DIAS + "," + UPDATED_DURACION_DIAS);

        // Get all the pasoCRMList where duracionDias equals to UPDATED_DURACION_DIAS
        defaultPasoCRMShouldNotBeFound("duracionDias.in=" + UPDATED_DURACION_DIAS);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDuracionDiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where duracionDias is not null
        defaultPasoCRMShouldBeFound("duracionDias.specified=true");

        // Get all the pasoCRMList where duracionDias is null
        defaultPasoCRMShouldNotBeFound("duracionDias.specified=false");
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDuracionDiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where duracionDias greater than or equals to DEFAULT_DURACION_DIAS
        defaultPasoCRMShouldBeFound("duracionDias.greaterOrEqualThan=" + DEFAULT_DURACION_DIAS);

        // Get all the pasoCRMList where duracionDias greater than or equals to UPDATED_DURACION_DIAS
        defaultPasoCRMShouldNotBeFound("duracionDias.greaterOrEqualThan=" + UPDATED_DURACION_DIAS);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByDuracionDiasIsLessThanSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where duracionDias less than or equals to DEFAULT_DURACION_DIAS
        defaultPasoCRMShouldNotBeFound("duracionDias.lessThan=" + DEFAULT_DURACION_DIAS);

        // Get all the pasoCRMList where duracionDias less than or equals to UPDATED_DURACION_DIAS
        defaultPasoCRMShouldBeFound("duracionDias.lessThan=" + UPDATED_DURACION_DIAS);
    }


    @Test
    @Transactional
    public void getAllPasoCRMSByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where estado equals to DEFAULT_ESTADO
        defaultPasoCRMShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the pasoCRMList where estado equals to UPDATED_ESTADO
        defaultPasoCRMShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultPasoCRMShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the pasoCRMList where estado equals to UPDATED_ESTADO
        defaultPasoCRMShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllPasoCRMSByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        pasoCRMRepository.saveAndFlush(pasoCRM);

        // Get all the pasoCRMList where estado is not null
        defaultPasoCRMShouldBeFound("estado.specified=true");

        // Get all the pasoCRMList where estado is null
        defaultPasoCRMShouldNotBeFound("estado.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPasoCRMShouldBeFound(String filter) throws Exception {
        restPasoCRMMockMvc.perform(get("/api/paso-crms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pasoCRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].duracionDias").value(hasItem(DEFAULT_DURACION_DIAS)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));

        // Check, that the count call also returns 1
        restPasoCRMMockMvc.perform(get("/api/paso-crms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPasoCRMShouldNotBeFound(String filter) throws Exception {
        restPasoCRMMockMvc.perform(get("/api/paso-crms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPasoCRMMockMvc.perform(get("/api/paso-crms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPasoCRM() throws Exception {
        // Get the pasoCRM
        restPasoCRMMockMvc.perform(get("/api/paso-crms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePasoCRM() throws Exception {
        // Initialize the database
        pasoCRMService.save(pasoCRM);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockPasoCRMSearchRepository);

        int databaseSizeBeforeUpdate = pasoCRMRepository.findAll().size();

        // Update the pasoCRM
        PasoCRM updatedPasoCRM = pasoCRMRepository.findById(pasoCRM.getId()).get();
        // Disconnect from session so that the updates on updatedPasoCRM are not directly saved in db
        em.detach(updatedPasoCRM);
        updatedPasoCRM
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .duracionDias(UPDATED_DURACION_DIAS)
            .estado(UPDATED_ESTADO);

        restPasoCRMMockMvc.perform(put("/api/paso-crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPasoCRM)))
            .andExpect(status().isOk());

        // Validate the PasoCRM in the database
        List<PasoCRM> pasoCRMList = pasoCRMRepository.findAll();
        assertThat(pasoCRMList).hasSize(databaseSizeBeforeUpdate);
        PasoCRM testPasoCRM = pasoCRMList.get(pasoCRMList.size() - 1);
        assertThat(testPasoCRM.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testPasoCRM.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPasoCRM.getDuracionDias()).isEqualTo(UPDATED_DURACION_DIAS);
        assertThat(testPasoCRM.getEstado()).isEqualTo(UPDATED_ESTADO);

        // Validate the PasoCRM in Elasticsearch
        verify(mockPasoCRMSearchRepository, times(1)).save(testPasoCRM);
    }

    @Test
    @Transactional
    public void updateNonExistingPasoCRM() throws Exception {
        int databaseSizeBeforeUpdate = pasoCRMRepository.findAll().size();

        // Create the PasoCRM

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPasoCRMMockMvc.perform(put("/api/paso-crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pasoCRM)))
            .andExpect(status().isBadRequest());

        // Validate the PasoCRM in the database
        List<PasoCRM> pasoCRMList = pasoCRMRepository.findAll();
        assertThat(pasoCRMList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PasoCRM in Elasticsearch
        verify(mockPasoCRMSearchRepository, times(0)).save(pasoCRM);
    }

    @Test
    @Transactional
    public void deletePasoCRM() throws Exception {
        // Initialize the database
        pasoCRMService.save(pasoCRM);

        int databaseSizeBeforeDelete = pasoCRMRepository.findAll().size();

        // Delete the pasoCRM
        restPasoCRMMockMvc.perform(delete("/api/paso-crms/{id}", pasoCRM.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PasoCRM> pasoCRMList = pasoCRMRepository.findAll();
        assertThat(pasoCRMList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PasoCRM in Elasticsearch
        verify(mockPasoCRMSearchRepository, times(1)).deleteById(pasoCRM.getId());
    }

    @Test
    @Transactional
    public void searchPasoCRM() throws Exception {
        // Initialize the database
        pasoCRMService.save(pasoCRM);
        when(mockPasoCRMSearchRepository.search(queryStringQuery("id:" + pasoCRM.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(pasoCRM), PageRequest.of(0, 1), 1));
        // Search the pasoCRM
        restPasoCRMMockMvc.perform(get("/api/_search/paso-crms?query=id:" + pasoCRM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pasoCRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].duracionDias").value(hasItem(DEFAULT_DURACION_DIAS)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PasoCRM.class);
        PasoCRM pasoCRM1 = new PasoCRM();
        pasoCRM1.setId(1L);
        PasoCRM pasoCRM2 = new PasoCRM();
        pasoCRM2.setId(pasoCRM1.getId());
        assertThat(pasoCRM1).isEqualTo(pasoCRM2);
        pasoCRM2.setId(2L);
        assertThat(pasoCRM1).isNotEqualTo(pasoCRM2);
        pasoCRM1.setId(null);
        assertThat(pasoCRM1).isNotEqualTo(pasoCRM2);
    }
}
