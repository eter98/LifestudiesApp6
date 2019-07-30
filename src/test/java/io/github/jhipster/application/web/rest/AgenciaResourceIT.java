package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.Agencia;
import io.github.jhipster.application.domain.Pais;
import io.github.jhipster.application.repository.AgenciaRepository;
import io.github.jhipster.application.repository.search.AgenciaSearchRepository;
import io.github.jhipster.application.service.AgenciaService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.AgenciaCriteria;
import io.github.jhipster.application.service.AgenciaQueryService;

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
 * Integration tests for the {@Link AgenciaResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class AgenciaResourceIT {

    private static final String DEFAULT_NOMBRE_AGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_AGENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_ENCARGADO = "AAAAAAAAAA";
    private static final String UPDATED_ENCARGADO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WHATSAPP = "AAAAAAAAAA";
    private static final String UPDATED_WHATSAPP = "BBBBBBBBBB";

    private static final String DEFAULT_ASESOR_1 = "AAAAAAAAAA";
    private static final String UPDATED_ASESOR_1 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO_ASESOR_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_ASESOR_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_ASESOR_1_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ASESOR_2 = "AAAAAAAAAA";
    private static final String UPDATED_ASESOR_2 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO_ASESOR_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_ASESOR_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_ASESOR_2_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ASESOR_3 = "AAAAAAAAAA";
    private static final String UPDATED_ASESOR_3 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO_ASESOR_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_ASESOR_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_ASESOR_3_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_2_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SEDE_1 = "AAAAAAAAAA";
    private static final String UPDATED_SEDE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SEDE_2 = "AAAAAAAAAA";
    private static final String UPDATED_SEDE_2 = "BBBBBBBBBB";

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private AgenciaService agenciaService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.AgenciaSearchRepositoryMockConfiguration
     */
    @Autowired
    private AgenciaSearchRepository mockAgenciaSearchRepository;

    @Autowired
    private AgenciaQueryService agenciaQueryService;

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

    private MockMvc restAgenciaMockMvc;

    private Agencia agencia;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AgenciaResource agenciaResource = new AgenciaResource(agenciaService, agenciaQueryService);
        this.restAgenciaMockMvc = MockMvcBuilders.standaloneSetup(agenciaResource)
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
    public static Agencia createEntity(EntityManager em) {
        Agencia agencia = new Agencia()
            .nombreAgencia(DEFAULT_NOMBRE_AGENCIA)
            .codigo(DEFAULT_CODIGO)
            .descripcion(DEFAULT_DESCRIPCION)
            .encargado(DEFAULT_ENCARGADO)
            .direccion(DEFAULT_DIRECCION)
            .telefono(DEFAULT_TELEFONO)
            .email(DEFAULT_EMAIL)
            .whatsapp(DEFAULT_WHATSAPP)
            .asesor1(DEFAULT_ASESOR_1)
            .fotoAsesor1(DEFAULT_FOTO_ASESOR_1)
            .fotoAsesor1ContentType(DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE)
            .asesor2(DEFAULT_ASESOR_2)
            .fotoAsesor2(DEFAULT_FOTO_ASESOR_2)
            .fotoAsesor2ContentType(DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE)
            .asesor3(DEFAULT_ASESOR_3)
            .fotoAsesor3(DEFAULT_FOTO_ASESOR_3)
            .fotoAsesor3ContentType(DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE)
            .sede1(DEFAULT_SEDE_1)
            .sede2(DEFAULT_SEDE_2);
        return agencia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agencia createUpdatedEntity(EntityManager em) {
        Agencia agencia = new Agencia()
            .nombreAgencia(UPDATED_NOMBRE_AGENCIA)
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .encargado(UPDATED_ENCARGADO)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .whatsapp(UPDATED_WHATSAPP)
            .asesor1(UPDATED_ASESOR_1)
            .fotoAsesor1(UPDATED_FOTO_ASESOR_1)
            .fotoAsesor1ContentType(UPDATED_FOTO_ASESOR_1_CONTENT_TYPE)
            .asesor2(UPDATED_ASESOR_2)
            .fotoAsesor2(UPDATED_FOTO_ASESOR_2)
            .fotoAsesor2ContentType(UPDATED_FOTO_ASESOR_2_CONTENT_TYPE)
            .asesor3(UPDATED_ASESOR_3)
            .fotoAsesor3(UPDATED_FOTO_ASESOR_3)
            .fotoAsesor3ContentType(UPDATED_FOTO_ASESOR_3_CONTENT_TYPE)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .sede1(UPDATED_SEDE_1)
            .sede2(UPDATED_SEDE_2);
        return agencia;
    }

    @BeforeEach
    public void initTest() {
        agencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgencia() throws Exception {
        int databaseSizeBeforeCreate = agenciaRepository.findAll().size();

        // Create the Agencia
        restAgenciaMockMvc.perform(post("/api/agencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agencia)))
            .andExpect(status().isCreated());

        // Validate the Agencia in the database
        List<Agencia> agenciaList = agenciaRepository.findAll();
        assertThat(agenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Agencia testAgencia = agenciaList.get(agenciaList.size() - 1);
        assertThat(testAgencia.getNombreAgencia()).isEqualTo(DEFAULT_NOMBRE_AGENCIA);
        assertThat(testAgencia.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testAgencia.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testAgencia.getEncargado()).isEqualTo(DEFAULT_ENCARGADO);
        assertThat(testAgencia.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testAgencia.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testAgencia.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAgencia.getWhatsapp()).isEqualTo(DEFAULT_WHATSAPP);
        assertThat(testAgencia.getAsesor1()).isEqualTo(DEFAULT_ASESOR_1);
        assertThat(testAgencia.getFotoAsesor1()).isEqualTo(DEFAULT_FOTO_ASESOR_1);
        assertThat(testAgencia.getFotoAsesor1ContentType()).isEqualTo(DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE);
        assertThat(testAgencia.getAsesor2()).isEqualTo(DEFAULT_ASESOR_2);
        assertThat(testAgencia.getFotoAsesor2()).isEqualTo(DEFAULT_FOTO_ASESOR_2);
        assertThat(testAgencia.getFotoAsesor2ContentType()).isEqualTo(DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE);
        assertThat(testAgencia.getAsesor3()).isEqualTo(DEFAULT_ASESOR_3);
        assertThat(testAgencia.getFotoAsesor3()).isEqualTo(DEFAULT_FOTO_ASESOR_3);
        assertThat(testAgencia.getFotoAsesor3ContentType()).isEqualTo(DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE);
        assertThat(testAgencia.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testAgencia.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
        assertThat(testAgencia.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testAgencia.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testAgencia.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testAgencia.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);
        assertThat(testAgencia.getSede1()).isEqualTo(DEFAULT_SEDE_1);
        assertThat(testAgencia.getSede2()).isEqualTo(DEFAULT_SEDE_2);

        // Validate the Agencia in Elasticsearch
        verify(mockAgenciaSearchRepository, times(1)).save(testAgencia);
    }

    @Test
    @Transactional
    public void createAgenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agenciaRepository.findAll().size();

        // Create the Agencia with an existing ID
        agencia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgenciaMockMvc.perform(post("/api/agencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agencia)))
            .andExpect(status().isBadRequest());

        // Validate the Agencia in the database
        List<Agencia> agenciaList = agenciaRepository.findAll();
        assertThat(agenciaList).hasSize(databaseSizeBeforeCreate);

        // Validate the Agencia in Elasticsearch
        verify(mockAgenciaSearchRepository, times(0)).save(agencia);
    }


    @Test
    @Transactional
    public void getAllAgencias() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList
        restAgenciaMockMvc.perform(get("/api/agencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA.toString())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].encargado").value(hasItem(DEFAULT_ENCARGADO.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].whatsapp").value(hasItem(DEFAULT_WHATSAPP.toString())))
            .andExpect(jsonPath("$.[*].asesor1").value(hasItem(DEFAULT_ASESOR_1.toString())))
            .andExpect(jsonPath("$.[*].fotoAsesor1ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_1))))
            .andExpect(jsonPath("$.[*].asesor2").value(hasItem(DEFAULT_ASESOR_2.toString())))
            .andExpect(jsonPath("$.[*].fotoAsesor2ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_2))))
            .andExpect(jsonPath("$.[*].asesor3").value(hasItem(DEFAULT_ASESOR_3.toString())))
            .andExpect(jsonPath("$.[*].fotoAsesor3ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_3))))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].sede1").value(hasItem(DEFAULT_SEDE_1.toString())))
            .andExpect(jsonPath("$.[*].sede2").value(hasItem(DEFAULT_SEDE_2.toString())));
    }
    
    @Test
    @Transactional
    public void getAgencia() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get the agencia
        restAgenciaMockMvc.perform(get("/api/agencias/{id}", agencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agencia.getId().intValue()))
            .andExpect(jsonPath("$.nombreAgencia").value(DEFAULT_NOMBRE_AGENCIA.toString()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.encargado").value(DEFAULT_ENCARGADO.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.whatsapp").value(DEFAULT_WHATSAPP.toString()))
            .andExpect(jsonPath("$.asesor1").value(DEFAULT_ASESOR_1.toString()))
            .andExpect(jsonPath("$.fotoAsesor1ContentType").value(DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotoAsesor1").value(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_1)))
            .andExpect(jsonPath("$.asesor2").value(DEFAULT_ASESOR_2.toString()))
            .andExpect(jsonPath("$.fotoAsesor2ContentType").value(DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotoAsesor2").value(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_2)))
            .andExpect(jsonPath("$.asesor3").value(DEFAULT_ASESOR_3.toString()))
            .andExpect(jsonPath("$.fotoAsesor3ContentType").value(DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotoAsesor3").value(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_3)))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)))
            .andExpect(jsonPath("$.sede1").value(DEFAULT_SEDE_1.toString()))
            .andExpect(jsonPath("$.sede2").value(DEFAULT_SEDE_2.toString()));
    }

    @Test
    @Transactional
    public void getAllAgenciasByNombreAgenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where nombreAgencia equals to DEFAULT_NOMBRE_AGENCIA
        defaultAgenciaShouldBeFound("nombreAgencia.equals=" + DEFAULT_NOMBRE_AGENCIA);

        // Get all the agenciaList where nombreAgencia equals to UPDATED_NOMBRE_AGENCIA
        defaultAgenciaShouldNotBeFound("nombreAgencia.equals=" + UPDATED_NOMBRE_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllAgenciasByNombreAgenciaIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where nombreAgencia in DEFAULT_NOMBRE_AGENCIA or UPDATED_NOMBRE_AGENCIA
        defaultAgenciaShouldBeFound("nombreAgencia.in=" + DEFAULT_NOMBRE_AGENCIA + "," + UPDATED_NOMBRE_AGENCIA);

        // Get all the agenciaList where nombreAgencia equals to UPDATED_NOMBRE_AGENCIA
        defaultAgenciaShouldNotBeFound("nombreAgencia.in=" + UPDATED_NOMBRE_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllAgenciasByNombreAgenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where nombreAgencia is not null
        defaultAgenciaShouldBeFound("nombreAgencia.specified=true");

        // Get all the agenciaList where nombreAgencia is null
        defaultAgenciaShouldNotBeFound("nombreAgencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByCodigoIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where codigo equals to DEFAULT_CODIGO
        defaultAgenciaShouldBeFound("codigo.equals=" + DEFAULT_CODIGO);

        // Get all the agenciaList where codigo equals to UPDATED_CODIGO
        defaultAgenciaShouldNotBeFound("codigo.equals=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllAgenciasByCodigoIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where codigo in DEFAULT_CODIGO or UPDATED_CODIGO
        defaultAgenciaShouldBeFound("codigo.in=" + DEFAULT_CODIGO + "," + UPDATED_CODIGO);

        // Get all the agenciaList where codigo equals to UPDATED_CODIGO
        defaultAgenciaShouldNotBeFound("codigo.in=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllAgenciasByCodigoIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where codigo is not null
        defaultAgenciaShouldBeFound("codigo.specified=true");

        // Get all the agenciaList where codigo is null
        defaultAgenciaShouldNotBeFound("codigo.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByEncargadoIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where encargado equals to DEFAULT_ENCARGADO
        defaultAgenciaShouldBeFound("encargado.equals=" + DEFAULT_ENCARGADO);

        // Get all the agenciaList where encargado equals to UPDATED_ENCARGADO
        defaultAgenciaShouldNotBeFound("encargado.equals=" + UPDATED_ENCARGADO);
    }

    @Test
    @Transactional
    public void getAllAgenciasByEncargadoIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where encargado in DEFAULT_ENCARGADO or UPDATED_ENCARGADO
        defaultAgenciaShouldBeFound("encargado.in=" + DEFAULT_ENCARGADO + "," + UPDATED_ENCARGADO);

        // Get all the agenciaList where encargado equals to UPDATED_ENCARGADO
        defaultAgenciaShouldNotBeFound("encargado.in=" + UPDATED_ENCARGADO);
    }

    @Test
    @Transactional
    public void getAllAgenciasByEncargadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where encargado is not null
        defaultAgenciaShouldBeFound("encargado.specified=true");

        // Get all the agenciaList where encargado is null
        defaultAgenciaShouldNotBeFound("encargado.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where direccion equals to DEFAULT_DIRECCION
        defaultAgenciaShouldBeFound("direccion.equals=" + DEFAULT_DIRECCION);

        // Get all the agenciaList where direccion equals to UPDATED_DIRECCION
        defaultAgenciaShouldNotBeFound("direccion.equals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllAgenciasByDireccionIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where direccion in DEFAULT_DIRECCION or UPDATED_DIRECCION
        defaultAgenciaShouldBeFound("direccion.in=" + DEFAULT_DIRECCION + "," + UPDATED_DIRECCION);

        // Get all the agenciaList where direccion equals to UPDATED_DIRECCION
        defaultAgenciaShouldNotBeFound("direccion.in=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllAgenciasByDireccionIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where direccion is not null
        defaultAgenciaShouldBeFound("direccion.specified=true");

        // Get all the agenciaList where direccion is null
        defaultAgenciaShouldNotBeFound("direccion.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where telefono equals to DEFAULT_TELEFONO
        defaultAgenciaShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the agenciaList where telefono equals to UPDATED_TELEFONO
        defaultAgenciaShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllAgenciasByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultAgenciaShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the agenciaList where telefono equals to UPDATED_TELEFONO
        defaultAgenciaShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllAgenciasByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where telefono is not null
        defaultAgenciaShouldBeFound("telefono.specified=true");

        // Get all the agenciaList where telefono is null
        defaultAgenciaShouldNotBeFound("telefono.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where email equals to DEFAULT_EMAIL
        defaultAgenciaShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the agenciaList where email equals to UPDATED_EMAIL
        defaultAgenciaShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAgenciasByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultAgenciaShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the agenciaList where email equals to UPDATED_EMAIL
        defaultAgenciaShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAgenciasByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where email is not null
        defaultAgenciaShouldBeFound("email.specified=true");

        // Get all the agenciaList where email is null
        defaultAgenciaShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByWhatsappIsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where whatsapp equals to DEFAULT_WHATSAPP
        defaultAgenciaShouldBeFound("whatsapp.equals=" + DEFAULT_WHATSAPP);

        // Get all the agenciaList where whatsapp equals to UPDATED_WHATSAPP
        defaultAgenciaShouldNotBeFound("whatsapp.equals=" + UPDATED_WHATSAPP);
    }

    @Test
    @Transactional
    public void getAllAgenciasByWhatsappIsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where whatsapp in DEFAULT_WHATSAPP or UPDATED_WHATSAPP
        defaultAgenciaShouldBeFound("whatsapp.in=" + DEFAULT_WHATSAPP + "," + UPDATED_WHATSAPP);

        // Get all the agenciaList where whatsapp equals to UPDATED_WHATSAPP
        defaultAgenciaShouldNotBeFound("whatsapp.in=" + UPDATED_WHATSAPP);
    }

    @Test
    @Transactional
    public void getAllAgenciasByWhatsappIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where whatsapp is not null
        defaultAgenciaShouldBeFound("whatsapp.specified=true");

        // Get all the agenciaList where whatsapp is null
        defaultAgenciaShouldNotBeFound("whatsapp.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor1IsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor1 equals to DEFAULT_ASESOR_1
        defaultAgenciaShouldBeFound("asesor1.equals=" + DEFAULT_ASESOR_1);

        // Get all the agenciaList where asesor1 equals to UPDATED_ASESOR_1
        defaultAgenciaShouldNotBeFound("asesor1.equals=" + UPDATED_ASESOR_1);
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor1IsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor1 in DEFAULT_ASESOR_1 or UPDATED_ASESOR_1
        defaultAgenciaShouldBeFound("asesor1.in=" + DEFAULT_ASESOR_1 + "," + UPDATED_ASESOR_1);

        // Get all the agenciaList where asesor1 equals to UPDATED_ASESOR_1
        defaultAgenciaShouldNotBeFound("asesor1.in=" + UPDATED_ASESOR_1);
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor1IsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor1 is not null
        defaultAgenciaShouldBeFound("asesor1.specified=true");

        // Get all the agenciaList where asesor1 is null
        defaultAgenciaShouldNotBeFound("asesor1.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor2IsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor2 equals to DEFAULT_ASESOR_2
        defaultAgenciaShouldBeFound("asesor2.equals=" + DEFAULT_ASESOR_2);

        // Get all the agenciaList where asesor2 equals to UPDATED_ASESOR_2
        defaultAgenciaShouldNotBeFound("asesor2.equals=" + UPDATED_ASESOR_2);
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor2IsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor2 in DEFAULT_ASESOR_2 or UPDATED_ASESOR_2
        defaultAgenciaShouldBeFound("asesor2.in=" + DEFAULT_ASESOR_2 + "," + UPDATED_ASESOR_2);

        // Get all the agenciaList where asesor2 equals to UPDATED_ASESOR_2
        defaultAgenciaShouldNotBeFound("asesor2.in=" + UPDATED_ASESOR_2);
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor2IsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor2 is not null
        defaultAgenciaShouldBeFound("asesor2.specified=true");

        // Get all the agenciaList where asesor2 is null
        defaultAgenciaShouldNotBeFound("asesor2.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor3IsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor3 equals to DEFAULT_ASESOR_3
        defaultAgenciaShouldBeFound("asesor3.equals=" + DEFAULT_ASESOR_3);

        // Get all the agenciaList where asesor3 equals to UPDATED_ASESOR_3
        defaultAgenciaShouldNotBeFound("asesor3.equals=" + UPDATED_ASESOR_3);
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor3IsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor3 in DEFAULT_ASESOR_3 or UPDATED_ASESOR_3
        defaultAgenciaShouldBeFound("asesor3.in=" + DEFAULT_ASESOR_3 + "," + UPDATED_ASESOR_3);

        // Get all the agenciaList where asesor3 equals to UPDATED_ASESOR_3
        defaultAgenciaShouldNotBeFound("asesor3.in=" + UPDATED_ASESOR_3);
    }

    @Test
    @Transactional
    public void getAllAgenciasByAsesor3IsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where asesor3 is not null
        defaultAgenciaShouldBeFound("asesor3.specified=true");

        // Get all the agenciaList where asesor3 is null
        defaultAgenciaShouldNotBeFound("asesor3.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasBySede1IsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where sede1 equals to DEFAULT_SEDE_1
        defaultAgenciaShouldBeFound("sede1.equals=" + DEFAULT_SEDE_1);

        // Get all the agenciaList where sede1 equals to UPDATED_SEDE_1
        defaultAgenciaShouldNotBeFound("sede1.equals=" + UPDATED_SEDE_1);
    }

    @Test
    @Transactional
    public void getAllAgenciasBySede1IsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where sede1 in DEFAULT_SEDE_1 or UPDATED_SEDE_1
        defaultAgenciaShouldBeFound("sede1.in=" + DEFAULT_SEDE_1 + "," + UPDATED_SEDE_1);

        // Get all the agenciaList where sede1 equals to UPDATED_SEDE_1
        defaultAgenciaShouldNotBeFound("sede1.in=" + UPDATED_SEDE_1);
    }

    @Test
    @Transactional
    public void getAllAgenciasBySede1IsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where sede1 is not null
        defaultAgenciaShouldBeFound("sede1.specified=true");

        // Get all the agenciaList where sede1 is null
        defaultAgenciaShouldNotBeFound("sede1.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasBySede2IsEqualToSomething() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where sede2 equals to DEFAULT_SEDE_2
        defaultAgenciaShouldBeFound("sede2.equals=" + DEFAULT_SEDE_2);

        // Get all the agenciaList where sede2 equals to UPDATED_SEDE_2
        defaultAgenciaShouldNotBeFound("sede2.equals=" + UPDATED_SEDE_2);
    }

    @Test
    @Transactional
    public void getAllAgenciasBySede2IsInShouldWork() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where sede2 in DEFAULT_SEDE_2 or UPDATED_SEDE_2
        defaultAgenciaShouldBeFound("sede2.in=" + DEFAULT_SEDE_2 + "," + UPDATED_SEDE_2);

        // Get all the agenciaList where sede2 equals to UPDATED_SEDE_2
        defaultAgenciaShouldNotBeFound("sede2.in=" + UPDATED_SEDE_2);
    }

    @Test
    @Transactional
    public void getAllAgenciasBySede2IsNullOrNotNull() throws Exception {
        // Initialize the database
        agenciaRepository.saveAndFlush(agencia);

        // Get all the agenciaList where sede2 is not null
        defaultAgenciaShouldBeFound("sede2.specified=true");

        // Get all the agenciaList where sede2 is null
        defaultAgenciaShouldNotBeFound("sede2.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciasByPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        Pais pais = PaisResourceIT.createEntity(em);
        em.persist(pais);
        em.flush();
        agencia.setPais(pais);
        agenciaRepository.saveAndFlush(agencia);
        Long paisId = pais.getId();

        // Get all the agenciaList where pais equals to paisId
        defaultAgenciaShouldBeFound("paisId.equals=" + paisId);

        // Get all the agenciaList where pais equals to paisId + 1
        defaultAgenciaShouldNotBeFound("paisId.equals=" + (paisId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAgenciaShouldBeFound(String filter) throws Exception {
        restAgenciaMockMvc.perform(get("/api/agencias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].encargado").value(hasItem(DEFAULT_ENCARGADO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].whatsapp").value(hasItem(DEFAULT_WHATSAPP)))
            .andExpect(jsonPath("$.[*].asesor1").value(hasItem(DEFAULT_ASESOR_1)))
            .andExpect(jsonPath("$.[*].fotoAsesor1ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_1))))
            .andExpect(jsonPath("$.[*].asesor2").value(hasItem(DEFAULT_ASESOR_2)))
            .andExpect(jsonPath("$.[*].fotoAsesor2ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_2))))
            .andExpect(jsonPath("$.[*].asesor3").value(hasItem(DEFAULT_ASESOR_3)))
            .andExpect(jsonPath("$.[*].fotoAsesor3ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_3))))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].sede1").value(hasItem(DEFAULT_SEDE_1)))
            .andExpect(jsonPath("$.[*].sede2").value(hasItem(DEFAULT_SEDE_2)));

        // Check, that the count call also returns 1
        restAgenciaMockMvc.perform(get("/api/agencias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAgenciaShouldNotBeFound(String filter) throws Exception {
        restAgenciaMockMvc.perform(get("/api/agencias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAgenciaMockMvc.perform(get("/api/agencias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAgencia() throws Exception {
        // Get the agencia
        restAgenciaMockMvc.perform(get("/api/agencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgencia() throws Exception {
        // Initialize the database
        agenciaService.save(agencia);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockAgenciaSearchRepository);

        int databaseSizeBeforeUpdate = agenciaRepository.findAll().size();

        // Update the agencia
        Agencia updatedAgencia = agenciaRepository.findById(agencia.getId()).get();
        // Disconnect from session so that the updates on updatedAgencia are not directly saved in db
        em.detach(updatedAgencia);
        updatedAgencia
            .nombreAgencia(UPDATED_NOMBRE_AGENCIA)
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .encargado(UPDATED_ENCARGADO)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .whatsapp(UPDATED_WHATSAPP)
            .asesor1(UPDATED_ASESOR_1)
            .fotoAsesor1(UPDATED_FOTO_ASESOR_1)
            .fotoAsesor1ContentType(UPDATED_FOTO_ASESOR_1_CONTENT_TYPE)
            .asesor2(UPDATED_ASESOR_2)
            .fotoAsesor2(UPDATED_FOTO_ASESOR_2)
            .fotoAsesor2ContentType(UPDATED_FOTO_ASESOR_2_CONTENT_TYPE)
            .asesor3(UPDATED_ASESOR_3)
            .fotoAsesor3(UPDATED_FOTO_ASESOR_3)
            .fotoAsesor3ContentType(UPDATED_FOTO_ASESOR_3_CONTENT_TYPE)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .sede1(UPDATED_SEDE_1)
            .sede2(UPDATED_SEDE_2);

        restAgenciaMockMvc.perform(put("/api/agencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAgencia)))
            .andExpect(status().isOk());

        // Validate the Agencia in the database
        List<Agencia> agenciaList = agenciaRepository.findAll();
        assertThat(agenciaList).hasSize(databaseSizeBeforeUpdate);
        Agencia testAgencia = agenciaList.get(agenciaList.size() - 1);
        assertThat(testAgencia.getNombreAgencia()).isEqualTo(UPDATED_NOMBRE_AGENCIA);
        assertThat(testAgencia.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testAgencia.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testAgencia.getEncargado()).isEqualTo(UPDATED_ENCARGADO);
        assertThat(testAgencia.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testAgencia.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testAgencia.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAgencia.getWhatsapp()).isEqualTo(UPDATED_WHATSAPP);
        assertThat(testAgencia.getAsesor1()).isEqualTo(UPDATED_ASESOR_1);
        assertThat(testAgencia.getFotoAsesor1()).isEqualTo(UPDATED_FOTO_ASESOR_1);
        assertThat(testAgencia.getFotoAsesor1ContentType()).isEqualTo(UPDATED_FOTO_ASESOR_1_CONTENT_TYPE);
        assertThat(testAgencia.getAsesor2()).isEqualTo(UPDATED_ASESOR_2);
        assertThat(testAgencia.getFotoAsesor2()).isEqualTo(UPDATED_FOTO_ASESOR_2);
        assertThat(testAgencia.getFotoAsesor2ContentType()).isEqualTo(UPDATED_FOTO_ASESOR_2_CONTENT_TYPE);
        assertThat(testAgencia.getAsesor3()).isEqualTo(UPDATED_ASESOR_3);
        assertThat(testAgencia.getFotoAsesor3()).isEqualTo(UPDATED_FOTO_ASESOR_3);
        assertThat(testAgencia.getFotoAsesor3ContentType()).isEqualTo(UPDATED_FOTO_ASESOR_3_CONTENT_TYPE);
        assertThat(testAgencia.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testAgencia.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
        assertThat(testAgencia.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testAgencia.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testAgencia.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testAgencia.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);
        assertThat(testAgencia.getSede1()).isEqualTo(UPDATED_SEDE_1);
        assertThat(testAgencia.getSede2()).isEqualTo(UPDATED_SEDE_2);

        // Validate the Agencia in Elasticsearch
        verify(mockAgenciaSearchRepository, times(1)).save(testAgencia);
    }

    @Test
    @Transactional
    public void updateNonExistingAgencia() throws Exception {
        int databaseSizeBeforeUpdate = agenciaRepository.findAll().size();

        // Create the Agencia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgenciaMockMvc.perform(put("/api/agencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agencia)))
            .andExpect(status().isBadRequest());

        // Validate the Agencia in the database
        List<Agencia> agenciaList = agenciaRepository.findAll();
        assertThat(agenciaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Agencia in Elasticsearch
        verify(mockAgenciaSearchRepository, times(0)).save(agencia);
    }

    @Test
    @Transactional
    public void deleteAgencia() throws Exception {
        // Initialize the database
        agenciaService.save(agencia);

        int databaseSizeBeforeDelete = agenciaRepository.findAll().size();

        // Delete the agencia
        restAgenciaMockMvc.perform(delete("/api/agencias/{id}", agencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agencia> agenciaList = agenciaRepository.findAll();
        assertThat(agenciaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Agencia in Elasticsearch
        verify(mockAgenciaSearchRepository, times(1)).deleteById(agencia.getId());
    }

    @Test
    @Transactional
    public void searchAgencia() throws Exception {
        // Initialize the database
        agenciaService.save(agencia);
        when(mockAgenciaSearchRepository.search(queryStringQuery("id:" + agencia.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(agencia), PageRequest.of(0, 1), 1));
        // Search the agencia
        restAgenciaMockMvc.perform(get("/api/_search/agencias?query=id:" + agencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].encargado").value(hasItem(DEFAULT_ENCARGADO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].whatsapp").value(hasItem(DEFAULT_WHATSAPP)))
            .andExpect(jsonPath("$.[*].asesor1").value(hasItem(DEFAULT_ASESOR_1)))
            .andExpect(jsonPath("$.[*].fotoAsesor1ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_1))))
            .andExpect(jsonPath("$.[*].asesor2").value(hasItem(DEFAULT_ASESOR_2)))
            .andExpect(jsonPath("$.[*].fotoAsesor2ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_2))))
            .andExpect(jsonPath("$.[*].asesor3").value(hasItem(DEFAULT_ASESOR_3)))
            .andExpect(jsonPath("$.[*].fotoAsesor3ContentType").value(hasItem(DEFAULT_FOTO_ASESOR_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoAsesor3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_ASESOR_3))))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].sede1").value(hasItem(DEFAULT_SEDE_1)))
            .andExpect(jsonPath("$.[*].sede2").value(hasItem(DEFAULT_SEDE_2)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Agencia.class);
        Agencia agencia1 = new Agencia();
        agencia1.setId(1L);
        Agencia agencia2 = new Agencia();
        agencia2.setId(agencia1.getId());
        assertThat(agencia1).isEqualTo(agencia2);
        agencia2.setId(2L);
        assertThat(agencia1).isNotEqualTo(agencia2);
        agencia1.setId(null);
        assertThat(agencia1).isNotEqualTo(agencia2);
    }
}
