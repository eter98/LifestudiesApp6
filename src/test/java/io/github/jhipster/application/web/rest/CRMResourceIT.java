package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.CRM;
import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.domain.PasoCRM;
import io.github.jhipster.application.domain.Agencia;
import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.repository.CRMRepository;
import io.github.jhipster.application.repository.search.CRMSearchRepository;
import io.github.jhipster.application.service.CRMService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.CRMCriteria;
import io.github.jhipster.application.service.CRMQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.sameInstant;
import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Asesord;
import io.github.jhipster.application.domain.enumeration.Estadod;
import io.github.jhipster.application.domain.enumeration.TipoLeadd;
/**
 * Integration tests for the {@Link CRMResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class CRMResourceIT {

    private static final ZonedDateTime DEFAULT_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DOCUMENTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOCUMENTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DOCUMENTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOCUMENTO_CONTENT_TYPE = "image/png";

    private static final Asesord DEFAULT_ASESOR = Asesord.CarlosFranco;
    private static final Asesord UPDATED_ASESOR = Asesord.CatalinaFranco;

    private static final Estadod DEFAULT_ESTADO = Estadod.Pendiente;
    private static final Estadod UPDATED_ESTADO = Estadod.EnProceso;

    private static final TipoLeadd DEFAULT_TIPO_LEAD = TipoLeadd.AltaPrioridad;
    private static final TipoLeadd UPDATED_TIPO_LEAD = TipoLeadd.MediaPrioridad;

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CERRADO = false;
    private static final Boolean UPDATED_CERRADO = true;

    @Autowired
    private CRMRepository cRMRepository;

    @Autowired
    private CRMService cRMService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.CRMSearchRepositoryMockConfiguration
     */
    @Autowired
    private CRMSearchRepository mockCRMSearchRepository;

    @Autowired
    private CRMQueryService cRMQueryService;

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

    private MockMvc restCRMMockMvc;

    private CRM cRM;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CRMResource cRMResource = new CRMResource(cRMService, cRMQueryService);
        this.restCRMMockMvc = MockMvcBuilders.standaloneSetup(cRMResource)
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
    public static CRM createEntity(EntityManager em) {
        CRM cRM = new CRM()
            .fecha(DEFAULT_FECHA)
            .descripcion(DEFAULT_DESCRIPCION)
            .documento(DEFAULT_DOCUMENTO)
            .documentoContentType(DEFAULT_DOCUMENTO_CONTENT_TYPE)
            .asesor(DEFAULT_ASESOR)
            .estado(DEFAULT_ESTADO)
            .tipoLead(DEFAULT_TIPO_LEAD)
            .comentarios(DEFAULT_COMENTARIOS)
            .cerrado(DEFAULT_CERRADO);
        return cRM;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CRM createUpdatedEntity(EntityManager em) {
        CRM cRM = new CRM()
            .fecha(UPDATED_FECHA)
            .descripcion(UPDATED_DESCRIPCION)
            .documento(UPDATED_DOCUMENTO)
            .documentoContentType(UPDATED_DOCUMENTO_CONTENT_TYPE)
            .asesor(UPDATED_ASESOR)
            .estado(UPDATED_ESTADO)
            .tipoLead(UPDATED_TIPO_LEAD)
            .comentarios(UPDATED_COMENTARIOS)
            .cerrado(UPDATED_CERRADO);
        return cRM;
    }

    @BeforeEach
    public void initTest() {
        cRM = createEntity(em);
    }

    @Test
    @Transactional
    public void createCRM() throws Exception {
        int databaseSizeBeforeCreate = cRMRepository.findAll().size();

        // Create the CRM
        restCRMMockMvc.perform(post("/api/crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cRM)))
            .andExpect(status().isCreated());

        // Validate the CRM in the database
        List<CRM> cRMList = cRMRepository.findAll();
        assertThat(cRMList).hasSize(databaseSizeBeforeCreate + 1);
        CRM testCRM = cRMList.get(cRMList.size() - 1);
        assertThat(testCRM.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testCRM.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testCRM.getDocumento()).isEqualTo(DEFAULT_DOCUMENTO);
        assertThat(testCRM.getDocumentoContentType()).isEqualTo(DEFAULT_DOCUMENTO_CONTENT_TYPE);
        assertThat(testCRM.getAsesor()).isEqualTo(DEFAULT_ASESOR);
        assertThat(testCRM.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCRM.getTipoLead()).isEqualTo(DEFAULT_TIPO_LEAD);
        assertThat(testCRM.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testCRM.isCerrado()).isEqualTo(DEFAULT_CERRADO);

        // Validate the CRM in Elasticsearch
        verify(mockCRMSearchRepository, times(1)).save(testCRM);
    }

    @Test
    @Transactional
    public void createCRMWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cRMRepository.findAll().size();

        // Create the CRM with an existing ID
        cRM.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCRMMockMvc.perform(post("/api/crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cRM)))
            .andExpect(status().isBadRequest());

        // Validate the CRM in the database
        List<CRM> cRMList = cRMRepository.findAll();
        assertThat(cRMList).hasSize(databaseSizeBeforeCreate);

        // Validate the CRM in Elasticsearch
        verify(mockCRMSearchRepository, times(0)).save(cRM);
    }


    @Test
    @Transactional
    public void getAllCRMS() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList
        restCRMMockMvc.perform(get("/api/crms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].documentoContentType").value(hasItem(DEFAULT_DOCUMENTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documento").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENTO))))
            .andExpect(jsonPath("$.[*].asesor").value(hasItem(DEFAULT_ASESOR.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].tipoLead").value(hasItem(DEFAULT_TIPO_LEAD.toString())))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS.toString())))
            .andExpect(jsonPath("$.[*].cerrado").value(hasItem(DEFAULT_CERRADO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCRM() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get the cRM
        restCRMMockMvc.perform(get("/api/crms/{id}", cRM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cRM.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(sameInstant(DEFAULT_FECHA)))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.documentoContentType").value(DEFAULT_DOCUMENTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.documento").value(Base64Utils.encodeToString(DEFAULT_DOCUMENTO)))
            .andExpect(jsonPath("$.asesor").value(DEFAULT_ASESOR.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.tipoLead").value(DEFAULT_TIPO_LEAD.toString()))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS.toString()))
            .andExpect(jsonPath("$.cerrado").value(DEFAULT_CERRADO.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllCRMSByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where fecha equals to DEFAULT_FECHA
        defaultCRMShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the cRMList where fecha equals to UPDATED_FECHA
        defaultCRMShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllCRMSByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultCRMShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the cRMList where fecha equals to UPDATED_FECHA
        defaultCRMShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllCRMSByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where fecha is not null
        defaultCRMShouldBeFound("fecha.specified=true");

        // Get all the cRMList where fecha is null
        defaultCRMShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllCRMSByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where fecha greater than or equals to DEFAULT_FECHA
        defaultCRMShouldBeFound("fecha.greaterOrEqualThan=" + DEFAULT_FECHA);

        // Get all the cRMList where fecha greater than or equals to UPDATED_FECHA
        defaultCRMShouldNotBeFound("fecha.greaterOrEqualThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllCRMSByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where fecha less than or equals to DEFAULT_FECHA
        defaultCRMShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the cRMList where fecha less than or equals to UPDATED_FECHA
        defaultCRMShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }


    @Test
    @Transactional
    public void getAllCRMSByDescripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where descripcion equals to DEFAULT_DESCRIPCION
        defaultCRMShouldBeFound("descripcion.equals=" + DEFAULT_DESCRIPCION);

        // Get all the cRMList where descripcion equals to UPDATED_DESCRIPCION
        defaultCRMShouldNotBeFound("descripcion.equals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllCRMSByDescripcionIsInShouldWork() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where descripcion in DEFAULT_DESCRIPCION or UPDATED_DESCRIPCION
        defaultCRMShouldBeFound("descripcion.in=" + DEFAULT_DESCRIPCION + "," + UPDATED_DESCRIPCION);

        // Get all the cRMList where descripcion equals to UPDATED_DESCRIPCION
        defaultCRMShouldNotBeFound("descripcion.in=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllCRMSByDescripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where descripcion is not null
        defaultCRMShouldBeFound("descripcion.specified=true");

        // Get all the cRMList where descripcion is null
        defaultCRMShouldNotBeFound("descripcion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCRMSByAsesorIsEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where asesor equals to DEFAULT_ASESOR
        defaultCRMShouldBeFound("asesor.equals=" + DEFAULT_ASESOR);

        // Get all the cRMList where asesor equals to UPDATED_ASESOR
        defaultCRMShouldNotBeFound("asesor.equals=" + UPDATED_ASESOR);
    }

    @Test
    @Transactional
    public void getAllCRMSByAsesorIsInShouldWork() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where asesor in DEFAULT_ASESOR or UPDATED_ASESOR
        defaultCRMShouldBeFound("asesor.in=" + DEFAULT_ASESOR + "," + UPDATED_ASESOR);

        // Get all the cRMList where asesor equals to UPDATED_ASESOR
        defaultCRMShouldNotBeFound("asesor.in=" + UPDATED_ASESOR);
    }

    @Test
    @Transactional
    public void getAllCRMSByAsesorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where asesor is not null
        defaultCRMShouldBeFound("asesor.specified=true");

        // Get all the cRMList where asesor is null
        defaultCRMShouldNotBeFound("asesor.specified=false");
    }

    @Test
    @Transactional
    public void getAllCRMSByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where estado equals to DEFAULT_ESTADO
        defaultCRMShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the cRMList where estado equals to UPDATED_ESTADO
        defaultCRMShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCRMSByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultCRMShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the cRMList where estado equals to UPDATED_ESTADO
        defaultCRMShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCRMSByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where estado is not null
        defaultCRMShouldBeFound("estado.specified=true");

        // Get all the cRMList where estado is null
        defaultCRMShouldNotBeFound("estado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCRMSByTipoLeadIsEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where tipoLead equals to DEFAULT_TIPO_LEAD
        defaultCRMShouldBeFound("tipoLead.equals=" + DEFAULT_TIPO_LEAD);

        // Get all the cRMList where tipoLead equals to UPDATED_TIPO_LEAD
        defaultCRMShouldNotBeFound("tipoLead.equals=" + UPDATED_TIPO_LEAD);
    }

    @Test
    @Transactional
    public void getAllCRMSByTipoLeadIsInShouldWork() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where tipoLead in DEFAULT_TIPO_LEAD or UPDATED_TIPO_LEAD
        defaultCRMShouldBeFound("tipoLead.in=" + DEFAULT_TIPO_LEAD + "," + UPDATED_TIPO_LEAD);

        // Get all the cRMList where tipoLead equals to UPDATED_TIPO_LEAD
        defaultCRMShouldNotBeFound("tipoLead.in=" + UPDATED_TIPO_LEAD);
    }

    @Test
    @Transactional
    public void getAllCRMSByTipoLeadIsNullOrNotNull() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where tipoLead is not null
        defaultCRMShouldBeFound("tipoLead.specified=true");

        // Get all the cRMList where tipoLead is null
        defaultCRMShouldNotBeFound("tipoLead.specified=false");
    }

    @Test
    @Transactional
    public void getAllCRMSByCerradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where cerrado equals to DEFAULT_CERRADO
        defaultCRMShouldBeFound("cerrado.equals=" + DEFAULT_CERRADO);

        // Get all the cRMList where cerrado equals to UPDATED_CERRADO
        defaultCRMShouldNotBeFound("cerrado.equals=" + UPDATED_CERRADO);
    }

    @Test
    @Transactional
    public void getAllCRMSByCerradoIsInShouldWork() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where cerrado in DEFAULT_CERRADO or UPDATED_CERRADO
        defaultCRMShouldBeFound("cerrado.in=" + DEFAULT_CERRADO + "," + UPDATED_CERRADO);

        // Get all the cRMList where cerrado equals to UPDATED_CERRADO
        defaultCRMShouldNotBeFound("cerrado.in=" + UPDATED_CERRADO);
    }

    @Test
    @Transactional
    public void getAllCRMSByCerradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cRMRepository.saveAndFlush(cRM);

        // Get all the cRMList where cerrado is not null
        defaultCRMShouldBeFound("cerrado.specified=true");

        // Get all the cRMList where cerrado is null
        defaultCRMShouldNotBeFound("cerrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCRMSByUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        PerfilUsuario usuario = PerfilUsuarioResourceIT.createEntity(em);
        em.persist(usuario);
        em.flush();
        cRM.setUsuario(usuario);
        cRMRepository.saveAndFlush(cRM);
        Long usuarioId = usuario.getId();

        // Get all the cRMList where usuario equals to usuarioId
        defaultCRMShouldBeFound("usuarioId.equals=" + usuarioId);

        // Get all the cRMList where usuario equals to usuarioId + 1
        defaultCRMShouldNotBeFound("usuarioId.equals=" + (usuarioId + 1));
    }


    @Test
    @Transactional
    public void getAllCRMSByProcesoIsEqualToSomething() throws Exception {
        // Initialize the database
        PasoCRM proceso = PasoCRMResourceIT.createEntity(em);
        em.persist(proceso);
        em.flush();
        cRM.setProceso(proceso);
        cRMRepository.saveAndFlush(cRM);
        Long procesoId = proceso.getId();

        // Get all the cRMList where proceso equals to procesoId
        defaultCRMShouldBeFound("procesoId.equals=" + procesoId);

        // Get all the cRMList where proceso equals to procesoId + 1
        defaultCRMShouldNotBeFound("procesoId.equals=" + (procesoId + 1));
    }


    @Test
    @Transactional
    public void getAllCRMSByAgenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        Agencia agencia = AgenciaResourceIT.createEntity(em);
        em.persist(agencia);
        em.flush();
        cRM.setAgencia(agencia);
        cRMRepository.saveAndFlush(cRM);
        Long agenciaId = agencia.getId();

        // Get all the cRMList where agencia equals to agenciaId
        defaultCRMShouldBeFound("agenciaId.equals=" + agenciaId);

        // Get all the cRMList where agencia equals to agenciaId + 1
        defaultCRMShouldNotBeFound("agenciaId.equals=" + (agenciaId + 1));
    }


    @Test
    @Transactional
    public void getAllCRMSByCotizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        Cotizacion cotizacion = CotizacionResourceIT.createEntity(em);
        em.persist(cotizacion);
        em.flush();
        cRM.setCotizacion(cotizacion);
        cRMRepository.saveAndFlush(cRM);
        Long cotizacionId = cotizacion.getId();

        // Get all the cRMList where cotizacion equals to cotizacionId
        defaultCRMShouldBeFound("cotizacionId.equals=" + cotizacionId);

        // Get all the cRMList where cotizacion equals to cotizacionId + 1
        defaultCRMShouldNotBeFound("cotizacionId.equals=" + (cotizacionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCRMShouldBeFound(String filter) throws Exception {
        restCRMMockMvc.perform(get("/api/crms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].documentoContentType").value(hasItem(DEFAULT_DOCUMENTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documento").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENTO))))
            .andExpect(jsonPath("$.[*].asesor").value(hasItem(DEFAULT_ASESOR.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].tipoLead").value(hasItem(DEFAULT_TIPO_LEAD.toString())))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS.toString())))
            .andExpect(jsonPath("$.[*].cerrado").value(hasItem(DEFAULT_CERRADO.booleanValue())));

        // Check, that the count call also returns 1
        restCRMMockMvc.perform(get("/api/crms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCRMShouldNotBeFound(String filter) throws Exception {
        restCRMMockMvc.perform(get("/api/crms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCRMMockMvc.perform(get("/api/crms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCRM() throws Exception {
        // Get the cRM
        restCRMMockMvc.perform(get("/api/crms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCRM() throws Exception {
        // Initialize the database
        cRMService.save(cRM);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockCRMSearchRepository);

        int databaseSizeBeforeUpdate = cRMRepository.findAll().size();

        // Update the cRM
        CRM updatedCRM = cRMRepository.findById(cRM.getId()).get();
        // Disconnect from session so that the updates on updatedCRM are not directly saved in db
        em.detach(updatedCRM);
        updatedCRM
            .fecha(UPDATED_FECHA)
            .descripcion(UPDATED_DESCRIPCION)
            .documento(UPDATED_DOCUMENTO)
            .documentoContentType(UPDATED_DOCUMENTO_CONTENT_TYPE)
            .asesor(UPDATED_ASESOR)
            .estado(UPDATED_ESTADO)
            .tipoLead(UPDATED_TIPO_LEAD)
            .comentarios(UPDATED_COMENTARIOS)
            .cerrado(UPDATED_CERRADO);

        restCRMMockMvc.perform(put("/api/crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCRM)))
            .andExpect(status().isOk());

        // Validate the CRM in the database
        List<CRM> cRMList = cRMRepository.findAll();
        assertThat(cRMList).hasSize(databaseSizeBeforeUpdate);
        CRM testCRM = cRMList.get(cRMList.size() - 1);
        assertThat(testCRM.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testCRM.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testCRM.getDocumento()).isEqualTo(UPDATED_DOCUMENTO);
        assertThat(testCRM.getDocumentoContentType()).isEqualTo(UPDATED_DOCUMENTO_CONTENT_TYPE);
        assertThat(testCRM.getAsesor()).isEqualTo(UPDATED_ASESOR);
        assertThat(testCRM.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCRM.getTipoLead()).isEqualTo(UPDATED_TIPO_LEAD);
        assertThat(testCRM.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testCRM.isCerrado()).isEqualTo(UPDATED_CERRADO);

        // Validate the CRM in Elasticsearch
        verify(mockCRMSearchRepository, times(1)).save(testCRM);
    }

    @Test
    @Transactional
    public void updateNonExistingCRM() throws Exception {
        int databaseSizeBeforeUpdate = cRMRepository.findAll().size();

        // Create the CRM

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCRMMockMvc.perform(put("/api/crms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cRM)))
            .andExpect(status().isBadRequest());

        // Validate the CRM in the database
        List<CRM> cRMList = cRMRepository.findAll();
        assertThat(cRMList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CRM in Elasticsearch
        verify(mockCRMSearchRepository, times(0)).save(cRM);
    }

    @Test
    @Transactional
    public void deleteCRM() throws Exception {
        // Initialize the database
        cRMService.save(cRM);

        int databaseSizeBeforeDelete = cRMRepository.findAll().size();

        // Delete the cRM
        restCRMMockMvc.perform(delete("/api/crms/{id}", cRM.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CRM> cRMList = cRMRepository.findAll();
        assertThat(cRMList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CRM in Elasticsearch
        verify(mockCRMSearchRepository, times(1)).deleteById(cRM.getId());
    }

    @Test
    @Transactional
    public void searchCRM() throws Exception {
        // Initialize the database
        cRMService.save(cRM);
        when(mockCRMSearchRepository.search(queryStringQuery("id:" + cRM.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(cRM), PageRequest.of(0, 1), 1));
        // Search the cRM
        restCRMMockMvc.perform(get("/api/_search/crms?query=id:" + cRM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cRM.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].documentoContentType").value(hasItem(DEFAULT_DOCUMENTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].documento").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOCUMENTO))))
            .andExpect(jsonPath("$.[*].asesor").value(hasItem(DEFAULT_ASESOR.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].tipoLead").value(hasItem(DEFAULT_TIPO_LEAD.toString())))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS.toString())))
            .andExpect(jsonPath("$.[*].cerrado").value(hasItem(DEFAULT_CERRADO.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CRM.class);
        CRM cRM1 = new CRM();
        cRM1.setId(1L);
        CRM cRM2 = new CRM();
        cRM2.setId(cRM1.getId());
        assertThat(cRM1).isEqualTo(cRM2);
        cRM2.setId(2L);
        assertThat(cRM1).isNotEqualTo(cRM2);
        cRM1.setId(null);
        assertThat(cRM1).isNotEqualTo(cRM2);
    }
}
