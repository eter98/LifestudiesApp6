package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.domain.CRM;
import io.github.jhipster.application.repository.CotizacionRepository;
import io.github.jhipster.application.repository.search.CotizacionSearchRepository;
import io.github.jhipster.application.service.CotizacionService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.CotizacionCriteria;
import io.github.jhipster.application.service.CotizacionQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@Link CotizacionResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class CotizacionResourceIT {

    private static final LocalDate DEFAULT_FECHA_VIAJE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_VIAJE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_ALOJAMIENTO = false;
    private static final Boolean UPDATED_ALOJAMIENTO = true;

    private static final Boolean DEFAULT_PASAJE_AEREO = false;
    private static final Boolean UPDATED_PASAJE_AEREO = true;

    private static final Boolean DEFAULT_TRANSP_AEROPUERTO = false;
    private static final Boolean UPDATED_TRANSP_AEROPUERTO = true;

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private CotizacionService cotizacionService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.CotizacionSearchRepositoryMockConfiguration
     */
    @Autowired
    private CotizacionSearchRepository mockCotizacionSearchRepository;

    @Autowired
    private CotizacionQueryService cotizacionQueryService;

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

    private MockMvc restCotizacionMockMvc;

    private Cotizacion cotizacion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CotizacionResource cotizacionResource = new CotizacionResource(cotizacionService, cotizacionQueryService);
        this.restCotizacionMockMvc = MockMvcBuilders.standaloneSetup(cotizacionResource)
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
    public static Cotizacion createEntity(EntityManager em) {
        Cotizacion cotizacion = new Cotizacion()
            .fechaViaje(DEFAULT_FECHA_VIAJE)
            .alojamiento(DEFAULT_ALOJAMIENTO)
            .pasajeAereo(DEFAULT_PASAJE_AEREO)
            .transpAeropuerto(DEFAULT_TRANSP_AEROPUERTO);
        return cotizacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cotizacion createUpdatedEntity(EntityManager em) {
        Cotizacion cotizacion = new Cotizacion()
            .fechaViaje(UPDATED_FECHA_VIAJE)
            .alojamiento(UPDATED_ALOJAMIENTO)
            .pasajeAereo(UPDATED_PASAJE_AEREO)
            .transpAeropuerto(UPDATED_TRANSP_AEROPUERTO);
        return cotizacion;
    }

    @BeforeEach
    public void initTest() {
        cotizacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCotizacion() throws Exception {
        int databaseSizeBeforeCreate = cotizacionRepository.findAll().size();

        // Create the Cotizacion
        restCotizacionMockMvc.perform(post("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cotizacion)))
            .andExpect(status().isCreated());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeCreate + 1);
        Cotizacion testCotizacion = cotizacionList.get(cotizacionList.size() - 1);
        assertThat(testCotizacion.getFechaViaje()).isEqualTo(DEFAULT_FECHA_VIAJE);
        assertThat(testCotizacion.isAlojamiento()).isEqualTo(DEFAULT_ALOJAMIENTO);
        assertThat(testCotizacion.isPasajeAereo()).isEqualTo(DEFAULT_PASAJE_AEREO);
        assertThat(testCotizacion.isTranspAeropuerto()).isEqualTo(DEFAULT_TRANSP_AEROPUERTO);

        // Validate the Cotizacion in Elasticsearch
        verify(mockCotizacionSearchRepository, times(1)).save(testCotizacion);
    }

    @Test
    @Transactional
    public void createCotizacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cotizacionRepository.findAll().size();

        // Create the Cotizacion with an existing ID
        cotizacion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCotizacionMockMvc.perform(post("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cotizacion)))
            .andExpect(status().isBadRequest());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeCreate);

        // Validate the Cotizacion in Elasticsearch
        verify(mockCotizacionSearchRepository, times(0)).save(cotizacion);
    }


    @Test
    @Transactional
    public void getAllCotizacions() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList
        restCotizacionMockMvc.perform(get("/api/cotizacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cotizacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaViaje").value(hasItem(DEFAULT_FECHA_VIAJE.toString())))
            .andExpect(jsonPath("$.[*].alojamiento").value(hasItem(DEFAULT_ALOJAMIENTO.booleanValue())))
            .andExpect(jsonPath("$.[*].pasajeAereo").value(hasItem(DEFAULT_PASAJE_AEREO.booleanValue())))
            .andExpect(jsonPath("$.[*].transpAeropuerto").value(hasItem(DEFAULT_TRANSP_AEROPUERTO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCotizacion() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get the cotizacion
        restCotizacionMockMvc.perform(get("/api/cotizacions/{id}", cotizacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cotizacion.getId().intValue()))
            .andExpect(jsonPath("$.fechaViaje").value(DEFAULT_FECHA_VIAJE.toString()))
            .andExpect(jsonPath("$.alojamiento").value(DEFAULT_ALOJAMIENTO.booleanValue()))
            .andExpect(jsonPath("$.pasajeAereo").value(DEFAULT_PASAJE_AEREO.booleanValue()))
            .andExpect(jsonPath("$.transpAeropuerto").value(DEFAULT_TRANSP_AEROPUERTO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllCotizacionsByFechaViajeIsEqualToSomething() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where fechaViaje equals to DEFAULT_FECHA_VIAJE
        defaultCotizacionShouldBeFound("fechaViaje.equals=" + DEFAULT_FECHA_VIAJE);

        // Get all the cotizacionList where fechaViaje equals to UPDATED_FECHA_VIAJE
        defaultCotizacionShouldNotBeFound("fechaViaje.equals=" + UPDATED_FECHA_VIAJE);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByFechaViajeIsInShouldWork() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where fechaViaje in DEFAULT_FECHA_VIAJE or UPDATED_FECHA_VIAJE
        defaultCotizacionShouldBeFound("fechaViaje.in=" + DEFAULT_FECHA_VIAJE + "," + UPDATED_FECHA_VIAJE);

        // Get all the cotizacionList where fechaViaje equals to UPDATED_FECHA_VIAJE
        defaultCotizacionShouldNotBeFound("fechaViaje.in=" + UPDATED_FECHA_VIAJE);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByFechaViajeIsNullOrNotNull() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where fechaViaje is not null
        defaultCotizacionShouldBeFound("fechaViaje.specified=true");

        // Get all the cotizacionList where fechaViaje is null
        defaultCotizacionShouldNotBeFound("fechaViaje.specified=false");
    }

    @Test
    @Transactional
    public void getAllCotizacionsByFechaViajeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where fechaViaje greater than or equals to DEFAULT_FECHA_VIAJE
        defaultCotizacionShouldBeFound("fechaViaje.greaterOrEqualThan=" + DEFAULT_FECHA_VIAJE);

        // Get all the cotizacionList where fechaViaje greater than or equals to UPDATED_FECHA_VIAJE
        defaultCotizacionShouldNotBeFound("fechaViaje.greaterOrEqualThan=" + UPDATED_FECHA_VIAJE);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByFechaViajeIsLessThanSomething() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where fechaViaje less than or equals to DEFAULT_FECHA_VIAJE
        defaultCotizacionShouldNotBeFound("fechaViaje.lessThan=" + DEFAULT_FECHA_VIAJE);

        // Get all the cotizacionList where fechaViaje less than or equals to UPDATED_FECHA_VIAJE
        defaultCotizacionShouldBeFound("fechaViaje.lessThan=" + UPDATED_FECHA_VIAJE);
    }


    @Test
    @Transactional
    public void getAllCotizacionsByAlojamientoIsEqualToSomething() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where alojamiento equals to DEFAULT_ALOJAMIENTO
        defaultCotizacionShouldBeFound("alojamiento.equals=" + DEFAULT_ALOJAMIENTO);

        // Get all the cotizacionList where alojamiento equals to UPDATED_ALOJAMIENTO
        defaultCotizacionShouldNotBeFound("alojamiento.equals=" + UPDATED_ALOJAMIENTO);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByAlojamientoIsInShouldWork() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where alojamiento in DEFAULT_ALOJAMIENTO or UPDATED_ALOJAMIENTO
        defaultCotizacionShouldBeFound("alojamiento.in=" + DEFAULT_ALOJAMIENTO + "," + UPDATED_ALOJAMIENTO);

        // Get all the cotizacionList where alojamiento equals to UPDATED_ALOJAMIENTO
        defaultCotizacionShouldNotBeFound("alojamiento.in=" + UPDATED_ALOJAMIENTO);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByAlojamientoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where alojamiento is not null
        defaultCotizacionShouldBeFound("alojamiento.specified=true");

        // Get all the cotizacionList where alojamiento is null
        defaultCotizacionShouldNotBeFound("alojamiento.specified=false");
    }

    @Test
    @Transactional
    public void getAllCotizacionsByPasajeAereoIsEqualToSomething() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where pasajeAereo equals to DEFAULT_PASAJE_AEREO
        defaultCotizacionShouldBeFound("pasajeAereo.equals=" + DEFAULT_PASAJE_AEREO);

        // Get all the cotizacionList where pasajeAereo equals to UPDATED_PASAJE_AEREO
        defaultCotizacionShouldNotBeFound("pasajeAereo.equals=" + UPDATED_PASAJE_AEREO);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByPasajeAereoIsInShouldWork() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where pasajeAereo in DEFAULT_PASAJE_AEREO or UPDATED_PASAJE_AEREO
        defaultCotizacionShouldBeFound("pasajeAereo.in=" + DEFAULT_PASAJE_AEREO + "," + UPDATED_PASAJE_AEREO);

        // Get all the cotizacionList where pasajeAereo equals to UPDATED_PASAJE_AEREO
        defaultCotizacionShouldNotBeFound("pasajeAereo.in=" + UPDATED_PASAJE_AEREO);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByPasajeAereoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where pasajeAereo is not null
        defaultCotizacionShouldBeFound("pasajeAereo.specified=true");

        // Get all the cotizacionList where pasajeAereo is null
        defaultCotizacionShouldNotBeFound("pasajeAereo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCotizacionsByTranspAeropuertoIsEqualToSomething() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where transpAeropuerto equals to DEFAULT_TRANSP_AEROPUERTO
        defaultCotizacionShouldBeFound("transpAeropuerto.equals=" + DEFAULT_TRANSP_AEROPUERTO);

        // Get all the cotizacionList where transpAeropuerto equals to UPDATED_TRANSP_AEROPUERTO
        defaultCotizacionShouldNotBeFound("transpAeropuerto.equals=" + UPDATED_TRANSP_AEROPUERTO);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByTranspAeropuertoIsInShouldWork() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where transpAeropuerto in DEFAULT_TRANSP_AEROPUERTO or UPDATED_TRANSP_AEROPUERTO
        defaultCotizacionShouldBeFound("transpAeropuerto.in=" + DEFAULT_TRANSP_AEROPUERTO + "," + UPDATED_TRANSP_AEROPUERTO);

        // Get all the cotizacionList where transpAeropuerto equals to UPDATED_TRANSP_AEROPUERTO
        defaultCotizacionShouldNotBeFound("transpAeropuerto.in=" + UPDATED_TRANSP_AEROPUERTO);
    }

    @Test
    @Transactional
    public void getAllCotizacionsByTranspAeropuertoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList where transpAeropuerto is not null
        defaultCotizacionShouldBeFound("transpAeropuerto.specified=true");

        // Get all the cotizacionList where transpAeropuerto is null
        defaultCotizacionShouldNotBeFound("transpAeropuerto.specified=false");
    }

    @Test
    @Transactional
    public void getAllCotizacionsByUsarioIsEqualToSomething() throws Exception {
        // Initialize the database
        PerfilUsuario usario = PerfilUsuarioResourceIT.createEntity(em);
        em.persist(usario);
        em.flush();
        cotizacion.setUsario(usario);
        cotizacionRepository.saveAndFlush(cotizacion);
        Long usarioId = usario.getId();

        // Get all the cotizacionList where usario equals to usarioId
        defaultCotizacionShouldBeFound("usarioId.equals=" + usarioId);

        // Get all the cotizacionList where usario equals to usarioId + 1
        defaultCotizacionShouldNotBeFound("usarioId.equals=" + (usarioId + 1));
    }


    @Test
    @Transactional
    public void getAllCotizacionsByCursoIsEqualToSomething() throws Exception {
        // Initialize the database
        Programas curso = ProgramasResourceIT.createEntity(em);
        em.persist(curso);
        em.flush();
        cotizacion.setCurso(curso);
        cotizacionRepository.saveAndFlush(cotizacion);
        Long cursoId = curso.getId();

        // Get all the cotizacionList where curso equals to cursoId
        defaultCotizacionShouldBeFound("cursoId.equals=" + cursoId);

        // Get all the cotizacionList where curso equals to cursoId + 1
        defaultCotizacionShouldNotBeFound("cursoId.equals=" + (cursoId + 1));
    }


    @Test
    @Transactional
    public void getAllCotizacionsByCRMIsEqualToSomething() throws Exception {
        // Initialize the database
        CRM cRM = CRMResourceIT.createEntity(em);
        em.persist(cRM);
        em.flush();
        cotizacion.addCRM(cRM);
        cotizacionRepository.saveAndFlush(cotizacion);
        Long cRMId = cRM.getId();

        // Get all the cotizacionList where cRM equals to cRMId
        defaultCotizacionShouldBeFound("cRMId.equals=" + cRMId);

        // Get all the cotizacionList where cRM equals to cRMId + 1
        defaultCotizacionShouldNotBeFound("cRMId.equals=" + (cRMId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCotizacionShouldBeFound(String filter) throws Exception {
        restCotizacionMockMvc.perform(get("/api/cotizacions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cotizacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaViaje").value(hasItem(DEFAULT_FECHA_VIAJE.toString())))
            .andExpect(jsonPath("$.[*].alojamiento").value(hasItem(DEFAULT_ALOJAMIENTO.booleanValue())))
            .andExpect(jsonPath("$.[*].pasajeAereo").value(hasItem(DEFAULT_PASAJE_AEREO.booleanValue())))
            .andExpect(jsonPath("$.[*].transpAeropuerto").value(hasItem(DEFAULT_TRANSP_AEROPUERTO.booleanValue())));

        // Check, that the count call also returns 1
        restCotizacionMockMvc.perform(get("/api/cotizacions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCotizacionShouldNotBeFound(String filter) throws Exception {
        restCotizacionMockMvc.perform(get("/api/cotizacions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCotizacionMockMvc.perform(get("/api/cotizacions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCotizacion() throws Exception {
        // Get the cotizacion
        restCotizacionMockMvc.perform(get("/api/cotizacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCotizacion() throws Exception {
        // Initialize the database
        cotizacionService.save(cotizacion);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockCotizacionSearchRepository);

        int databaseSizeBeforeUpdate = cotizacionRepository.findAll().size();

        // Update the cotizacion
        Cotizacion updatedCotizacion = cotizacionRepository.findById(cotizacion.getId()).get();
        // Disconnect from session so that the updates on updatedCotizacion are not directly saved in db
        em.detach(updatedCotizacion);
        updatedCotizacion
            .fechaViaje(UPDATED_FECHA_VIAJE)
            .alojamiento(UPDATED_ALOJAMIENTO)
            .pasajeAereo(UPDATED_PASAJE_AEREO)
            .transpAeropuerto(UPDATED_TRANSP_AEROPUERTO);

        restCotizacionMockMvc.perform(put("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCotizacion)))
            .andExpect(status().isOk());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeUpdate);
        Cotizacion testCotizacion = cotizacionList.get(cotizacionList.size() - 1);
        assertThat(testCotizacion.getFechaViaje()).isEqualTo(UPDATED_FECHA_VIAJE);
        assertThat(testCotizacion.isAlojamiento()).isEqualTo(UPDATED_ALOJAMIENTO);
        assertThat(testCotizacion.isPasajeAereo()).isEqualTo(UPDATED_PASAJE_AEREO);
        assertThat(testCotizacion.isTranspAeropuerto()).isEqualTo(UPDATED_TRANSP_AEROPUERTO);

        // Validate the Cotizacion in Elasticsearch
        verify(mockCotizacionSearchRepository, times(1)).save(testCotizacion);
    }

    @Test
    @Transactional
    public void updateNonExistingCotizacion() throws Exception {
        int databaseSizeBeforeUpdate = cotizacionRepository.findAll().size();

        // Create the Cotizacion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCotizacionMockMvc.perform(put("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cotizacion)))
            .andExpect(status().isBadRequest());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Cotizacion in Elasticsearch
        verify(mockCotizacionSearchRepository, times(0)).save(cotizacion);
    }

    @Test
    @Transactional
    public void deleteCotizacion() throws Exception {
        // Initialize the database
        cotizacionService.save(cotizacion);

        int databaseSizeBeforeDelete = cotizacionRepository.findAll().size();

        // Delete the cotizacion
        restCotizacionMockMvc.perform(delete("/api/cotizacions/{id}", cotizacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Cotizacion in Elasticsearch
        verify(mockCotizacionSearchRepository, times(1)).deleteById(cotizacion.getId());
    }

    @Test
    @Transactional
    public void searchCotizacion() throws Exception {
        // Initialize the database
        cotizacionService.save(cotizacion);
        when(mockCotizacionSearchRepository.search(queryStringQuery("id:" + cotizacion.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(cotizacion), PageRequest.of(0, 1), 1));
        // Search the cotizacion
        restCotizacionMockMvc.perform(get("/api/_search/cotizacions?query=id:" + cotizacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cotizacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaViaje").value(hasItem(DEFAULT_FECHA_VIAJE.toString())))
            .andExpect(jsonPath("$.[*].alojamiento").value(hasItem(DEFAULT_ALOJAMIENTO.booleanValue())))
            .andExpect(jsonPath("$.[*].pasajeAereo").value(hasItem(DEFAULT_PASAJE_AEREO.booleanValue())))
            .andExpect(jsonPath("$.[*].transpAeropuerto").value(hasItem(DEFAULT_TRANSP_AEROPUERTO.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cotizacion.class);
        Cotizacion cotizacion1 = new Cotizacion();
        cotizacion1.setId(1L);
        Cotizacion cotizacion2 = new Cotizacion();
        cotizacion2.setId(cotizacion1.getId());
        assertThat(cotizacion1).isEqualTo(cotizacion2);
        cotizacion2.setId(2L);
        assertThat(cotizacion1).isNotEqualTo(cotizacion2);
        cotizacion1.setId(null);
        assertThat(cotizacion1).isNotEqualTo(cotizacion2);
    }
}
