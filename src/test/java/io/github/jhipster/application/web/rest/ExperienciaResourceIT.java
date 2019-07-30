package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.Experiencia;
import io.github.jhipster.application.repository.ExperienciaRepository;
import io.github.jhipster.application.repository.search.ExperienciaSearchRepository;
import io.github.jhipster.application.service.ExperienciaService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ExperienciaCriteria;
import io.github.jhipster.application.service.ExperienciaQueryService;

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
 * Integration tests for the {@Link ExperienciaResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class ExperienciaResourceIT {

    private static final Integer DEFAULT_IDENTIFICACION = 1;
    private static final Integer UPDATED_IDENTIFICACION = 2;

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_AREA = 1;
    private static final Integer UPDATED_AREA = 2;

    private static final Integer DEFAULT_TELEFONO = 1;
    private static final Integer UPDATED_TELEFONO = 2;

    private static final String DEFAULT_NACIONALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_NACIONALIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_PAIS_DESTINO = "AAAAAAAAAA";
    private static final String UPDATED_PAIS_DESTINO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICA_PAIS = 1;
    private static final Integer UPDATED_CALIFICA_PAIS = 2;

    private static final String DEFAULT_PROGRAMA = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUCION = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICA_INSTITUCION = 1;
    private static final Integer UPDATED_CALIFICA_INSTITUCION = 2;

    private static final String DEFAULT_AGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_AGENCIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICA_AGENCIA = 1;
    private static final Integer UPDATED_CALIFICA_AGENCIA = 2;

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CONTENIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTENIDO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Autowired
    private ExperienciaService experienciaService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ExperienciaSearchRepositoryMockConfiguration
     */
    @Autowired
    private ExperienciaSearchRepository mockExperienciaSearchRepository;

    @Autowired
    private ExperienciaQueryService experienciaQueryService;

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

    private MockMvc restExperienciaMockMvc;

    private Experiencia experiencia;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExperienciaResource experienciaResource = new ExperienciaResource(experienciaService, experienciaQueryService);
        this.restExperienciaMockMvc = MockMvcBuilders.standaloneSetup(experienciaResource)
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
    public static Experiencia createEntity(EntityManager em) {
        Experiencia experiencia = new Experiencia()
            .identificacion(DEFAULT_IDENTIFICACION)
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .mail(DEFAULT_MAIL)
            .area(DEFAULT_AREA)
            .telefono(DEFAULT_TELEFONO)
            .nacionalidad(DEFAULT_NACIONALIDAD)
            .paisDestino(DEFAULT_PAIS_DESTINO)
            .calificaPais(DEFAULT_CALIFICA_PAIS)
            .programa(DEFAULT_PROGRAMA)
            .institucion(DEFAULT_INSTITUCION)
            .calificaInstitucion(DEFAULT_CALIFICA_INSTITUCION)
            .agencia(DEFAULT_AGENCIA)
            .calificaAgencia(DEFAULT_CALIFICA_AGENCIA)
            .fecha(DEFAULT_FECHA)
            .contenido(DEFAULT_CONTENIDO)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE);
        return experiencia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Experiencia createUpdatedEntity(EntityManager em) {
        Experiencia experiencia = new Experiencia()
            .identificacion(UPDATED_IDENTIFICACION)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .mail(UPDATED_MAIL)
            .area(UPDATED_AREA)
            .telefono(UPDATED_TELEFONO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .paisDestino(UPDATED_PAIS_DESTINO)
            .calificaPais(UPDATED_CALIFICA_PAIS)
            .programa(UPDATED_PROGRAMA)
            .institucion(UPDATED_INSTITUCION)
            .calificaInstitucion(UPDATED_CALIFICA_INSTITUCION)
            .agencia(UPDATED_AGENCIA)
            .calificaAgencia(UPDATED_CALIFICA_AGENCIA)
            .fecha(UPDATED_FECHA)
            .contenido(UPDATED_CONTENIDO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);
        return experiencia;
    }

    @BeforeEach
    public void initTest() {
        experiencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createExperiencia() throws Exception {
        int databaseSizeBeforeCreate = experienciaRepository.findAll().size();

        // Create the Experiencia
        restExperienciaMockMvc.perform(post("/api/experiencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(experiencia)))
            .andExpect(status().isCreated());

        // Validate the Experiencia in the database
        List<Experiencia> experienciaList = experienciaRepository.findAll();
        assertThat(experienciaList).hasSize(databaseSizeBeforeCreate + 1);
        Experiencia testExperiencia = experienciaList.get(experienciaList.size() - 1);
        assertThat(testExperiencia.getIdentificacion()).isEqualTo(DEFAULT_IDENTIFICACION);
        assertThat(testExperiencia.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testExperiencia.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testExperiencia.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testExperiencia.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testExperiencia.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testExperiencia.getNacionalidad()).isEqualTo(DEFAULT_NACIONALIDAD);
        assertThat(testExperiencia.getPaisDestino()).isEqualTo(DEFAULT_PAIS_DESTINO);
        assertThat(testExperiencia.getCalificaPais()).isEqualTo(DEFAULT_CALIFICA_PAIS);
        assertThat(testExperiencia.getPrograma()).isEqualTo(DEFAULT_PROGRAMA);
        assertThat(testExperiencia.getInstitucion()).isEqualTo(DEFAULT_INSTITUCION);
        assertThat(testExperiencia.getCalificaInstitucion()).isEqualTo(DEFAULT_CALIFICA_INSTITUCION);
        assertThat(testExperiencia.getAgencia()).isEqualTo(DEFAULT_AGENCIA);
        assertThat(testExperiencia.getCalificaAgencia()).isEqualTo(DEFAULT_CALIFICA_AGENCIA);
        assertThat(testExperiencia.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testExperiencia.getContenido()).isEqualTo(DEFAULT_CONTENIDO);
        assertThat(testExperiencia.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testExperiencia.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);

        // Validate the Experiencia in Elasticsearch
        verify(mockExperienciaSearchRepository, times(1)).save(testExperiencia);
    }

    @Test
    @Transactional
    public void createExperienciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = experienciaRepository.findAll().size();

        // Create the Experiencia with an existing ID
        experiencia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExperienciaMockMvc.perform(post("/api/experiencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(experiencia)))
            .andExpect(status().isBadRequest());

        // Validate the Experiencia in the database
        List<Experiencia> experienciaList = experienciaRepository.findAll();
        assertThat(experienciaList).hasSize(databaseSizeBeforeCreate);

        // Validate the Experiencia in Elasticsearch
        verify(mockExperienciaSearchRepository, times(0)).save(experiencia);
    }


    @Test
    @Transactional
    public void getAllExperiencias() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList
        restExperienciaMockMvc.perform(get("/api/experiencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(experiencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].paisDestino").value(hasItem(DEFAULT_PAIS_DESTINO.toString())))
            .andExpect(jsonPath("$.[*].calificaPais").value(hasItem(DEFAULT_CALIFICA_PAIS)))
            .andExpect(jsonPath("$.[*].programa").value(hasItem(DEFAULT_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].institucion").value(hasItem(DEFAULT_INSTITUCION.toString())))
            .andExpect(jsonPath("$.[*].calificaInstitucion").value(hasItem(DEFAULT_CALIFICA_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agencia").value(hasItem(DEFAULT_AGENCIA.toString())))
            .andExpect(jsonPath("$.[*].calificaAgencia").value(hasItem(DEFAULT_CALIFICA_AGENCIA)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }
    
    @Test
    @Transactional
    public void getExperiencia() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get the experiencia
        restExperienciaMockMvc.perform(get("/api/experiencias/{id}", experiencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(experiencia.getId().intValue()))
            .andExpect(jsonPath("$.identificacion").value(DEFAULT_IDENTIFICACION))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.nacionalidad").value(DEFAULT_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.paisDestino").value(DEFAULT_PAIS_DESTINO.toString()))
            .andExpect(jsonPath("$.calificaPais").value(DEFAULT_CALIFICA_PAIS))
            .andExpect(jsonPath("$.programa").value(DEFAULT_PROGRAMA.toString()))
            .andExpect(jsonPath("$.institucion").value(DEFAULT_INSTITUCION.toString()))
            .andExpect(jsonPath("$.calificaInstitucion").value(DEFAULT_CALIFICA_INSTITUCION))
            .andExpect(jsonPath("$.agencia").value(DEFAULT_AGENCIA.toString()))
            .andExpect(jsonPath("$.calificaAgencia").value(DEFAULT_CALIFICA_AGENCIA))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.contenido").value(DEFAULT_CONTENIDO.toString()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)));
    }

    @Test
    @Transactional
    public void getAllExperienciasByIdentificacionIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where identificacion equals to DEFAULT_IDENTIFICACION
        defaultExperienciaShouldBeFound("identificacion.equals=" + DEFAULT_IDENTIFICACION);

        // Get all the experienciaList where identificacion equals to UPDATED_IDENTIFICACION
        defaultExperienciaShouldNotBeFound("identificacion.equals=" + UPDATED_IDENTIFICACION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByIdentificacionIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where identificacion in DEFAULT_IDENTIFICACION or UPDATED_IDENTIFICACION
        defaultExperienciaShouldBeFound("identificacion.in=" + DEFAULT_IDENTIFICACION + "," + UPDATED_IDENTIFICACION);

        // Get all the experienciaList where identificacion equals to UPDATED_IDENTIFICACION
        defaultExperienciaShouldNotBeFound("identificacion.in=" + UPDATED_IDENTIFICACION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByIdentificacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where identificacion is not null
        defaultExperienciaShouldBeFound("identificacion.specified=true");

        // Get all the experienciaList where identificacion is null
        defaultExperienciaShouldNotBeFound("identificacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByIdentificacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where identificacion greater than or equals to DEFAULT_IDENTIFICACION
        defaultExperienciaShouldBeFound("identificacion.greaterOrEqualThan=" + DEFAULT_IDENTIFICACION);

        // Get all the experienciaList where identificacion greater than or equals to UPDATED_IDENTIFICACION
        defaultExperienciaShouldNotBeFound("identificacion.greaterOrEqualThan=" + UPDATED_IDENTIFICACION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByIdentificacionIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where identificacion less than or equals to DEFAULT_IDENTIFICACION
        defaultExperienciaShouldNotBeFound("identificacion.lessThan=" + DEFAULT_IDENTIFICACION);

        // Get all the experienciaList where identificacion less than or equals to UPDATED_IDENTIFICACION
        defaultExperienciaShouldBeFound("identificacion.lessThan=" + UPDATED_IDENTIFICACION);
    }


    @Test
    @Transactional
    public void getAllExperienciasByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where nombre equals to DEFAULT_NOMBRE
        defaultExperienciaShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the experienciaList where nombre equals to UPDATED_NOMBRE
        defaultExperienciaShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllExperienciasByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultExperienciaShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the experienciaList where nombre equals to UPDATED_NOMBRE
        defaultExperienciaShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllExperienciasByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where nombre is not null
        defaultExperienciaShouldBeFound("nombre.specified=true");

        // Get all the experienciaList where nombre is null
        defaultExperienciaShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByApellidoIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where apellido equals to DEFAULT_APELLIDO
        defaultExperienciaShouldBeFound("apellido.equals=" + DEFAULT_APELLIDO);

        // Get all the experienciaList where apellido equals to UPDATED_APELLIDO
        defaultExperienciaShouldNotBeFound("apellido.equals=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByApellidoIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where apellido in DEFAULT_APELLIDO or UPDATED_APELLIDO
        defaultExperienciaShouldBeFound("apellido.in=" + DEFAULT_APELLIDO + "," + UPDATED_APELLIDO);

        // Get all the experienciaList where apellido equals to UPDATED_APELLIDO
        defaultExperienciaShouldNotBeFound("apellido.in=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByApellidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where apellido is not null
        defaultExperienciaShouldBeFound("apellido.specified=true");

        // Get all the experienciaList where apellido is null
        defaultExperienciaShouldNotBeFound("apellido.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByMailIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where mail equals to DEFAULT_MAIL
        defaultExperienciaShouldBeFound("mail.equals=" + DEFAULT_MAIL);

        // Get all the experienciaList where mail equals to UPDATED_MAIL
        defaultExperienciaShouldNotBeFound("mail.equals=" + UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void getAllExperienciasByMailIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where mail in DEFAULT_MAIL or UPDATED_MAIL
        defaultExperienciaShouldBeFound("mail.in=" + DEFAULT_MAIL + "," + UPDATED_MAIL);

        // Get all the experienciaList where mail equals to UPDATED_MAIL
        defaultExperienciaShouldNotBeFound("mail.in=" + UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void getAllExperienciasByMailIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where mail is not null
        defaultExperienciaShouldBeFound("mail.specified=true");

        // Get all the experienciaList where mail is null
        defaultExperienciaShouldNotBeFound("mail.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where area equals to DEFAULT_AREA
        defaultExperienciaShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the experienciaList where area equals to UPDATED_AREA
        defaultExperienciaShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where area in DEFAULT_AREA or UPDATED_AREA
        defaultExperienciaShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the experienciaList where area equals to UPDATED_AREA
        defaultExperienciaShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where area is not null
        defaultExperienciaShouldBeFound("area.specified=true");

        // Get all the experienciaList where area is null
        defaultExperienciaShouldNotBeFound("area.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByAreaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where area greater than or equals to DEFAULT_AREA
        defaultExperienciaShouldBeFound("area.greaterOrEqualThan=" + DEFAULT_AREA);

        // Get all the experienciaList where area greater than or equals to UPDATED_AREA
        defaultExperienciaShouldNotBeFound("area.greaterOrEqualThan=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByAreaIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where area less than or equals to DEFAULT_AREA
        defaultExperienciaShouldNotBeFound("area.lessThan=" + DEFAULT_AREA);

        // Get all the experienciaList where area less than or equals to UPDATED_AREA
        defaultExperienciaShouldBeFound("area.lessThan=" + UPDATED_AREA);
    }


    @Test
    @Transactional
    public void getAllExperienciasByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where telefono equals to DEFAULT_TELEFONO
        defaultExperienciaShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the experienciaList where telefono equals to UPDATED_TELEFONO
        defaultExperienciaShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultExperienciaShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the experienciaList where telefono equals to UPDATED_TELEFONO
        defaultExperienciaShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where telefono is not null
        defaultExperienciaShouldBeFound("telefono.specified=true");

        // Get all the experienciaList where telefono is null
        defaultExperienciaShouldNotBeFound("telefono.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByTelefonoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where telefono greater than or equals to DEFAULT_TELEFONO
        defaultExperienciaShouldBeFound("telefono.greaterOrEqualThan=" + DEFAULT_TELEFONO);

        // Get all the experienciaList where telefono greater than or equals to UPDATED_TELEFONO
        defaultExperienciaShouldNotBeFound("telefono.greaterOrEqualThan=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByTelefonoIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where telefono less than or equals to DEFAULT_TELEFONO
        defaultExperienciaShouldNotBeFound("telefono.lessThan=" + DEFAULT_TELEFONO);

        // Get all the experienciaList where telefono less than or equals to UPDATED_TELEFONO
        defaultExperienciaShouldBeFound("telefono.lessThan=" + UPDATED_TELEFONO);
    }


    @Test
    @Transactional
    public void getAllExperienciasByNacionalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where nacionalidad equals to DEFAULT_NACIONALIDAD
        defaultExperienciaShouldBeFound("nacionalidad.equals=" + DEFAULT_NACIONALIDAD);

        // Get all the experienciaList where nacionalidad equals to UPDATED_NACIONALIDAD
        defaultExperienciaShouldNotBeFound("nacionalidad.equals=" + UPDATED_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllExperienciasByNacionalidadIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where nacionalidad in DEFAULT_NACIONALIDAD or UPDATED_NACIONALIDAD
        defaultExperienciaShouldBeFound("nacionalidad.in=" + DEFAULT_NACIONALIDAD + "," + UPDATED_NACIONALIDAD);

        // Get all the experienciaList where nacionalidad equals to UPDATED_NACIONALIDAD
        defaultExperienciaShouldNotBeFound("nacionalidad.in=" + UPDATED_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllExperienciasByNacionalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where nacionalidad is not null
        defaultExperienciaShouldBeFound("nacionalidad.specified=true");

        // Get all the experienciaList where nacionalidad is null
        defaultExperienciaShouldNotBeFound("nacionalidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByPaisDestinoIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where paisDestino equals to DEFAULT_PAIS_DESTINO
        defaultExperienciaShouldBeFound("paisDestino.equals=" + DEFAULT_PAIS_DESTINO);

        // Get all the experienciaList where paisDestino equals to UPDATED_PAIS_DESTINO
        defaultExperienciaShouldNotBeFound("paisDestino.equals=" + UPDATED_PAIS_DESTINO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByPaisDestinoIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where paisDestino in DEFAULT_PAIS_DESTINO or UPDATED_PAIS_DESTINO
        defaultExperienciaShouldBeFound("paisDestino.in=" + DEFAULT_PAIS_DESTINO + "," + UPDATED_PAIS_DESTINO);

        // Get all the experienciaList where paisDestino equals to UPDATED_PAIS_DESTINO
        defaultExperienciaShouldNotBeFound("paisDestino.in=" + UPDATED_PAIS_DESTINO);
    }

    @Test
    @Transactional
    public void getAllExperienciasByPaisDestinoIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where paisDestino is not null
        defaultExperienciaShouldBeFound("paisDestino.specified=true");

        // Get all the experienciaList where paisDestino is null
        defaultExperienciaShouldNotBeFound("paisDestino.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaPais equals to DEFAULT_CALIFICA_PAIS
        defaultExperienciaShouldBeFound("calificaPais.equals=" + DEFAULT_CALIFICA_PAIS);

        // Get all the experienciaList where calificaPais equals to UPDATED_CALIFICA_PAIS
        defaultExperienciaShouldNotBeFound("calificaPais.equals=" + UPDATED_CALIFICA_PAIS);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaPaisIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaPais in DEFAULT_CALIFICA_PAIS or UPDATED_CALIFICA_PAIS
        defaultExperienciaShouldBeFound("calificaPais.in=" + DEFAULT_CALIFICA_PAIS + "," + UPDATED_CALIFICA_PAIS);

        // Get all the experienciaList where calificaPais equals to UPDATED_CALIFICA_PAIS
        defaultExperienciaShouldNotBeFound("calificaPais.in=" + UPDATED_CALIFICA_PAIS);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaPais is not null
        defaultExperienciaShouldBeFound("calificaPais.specified=true");

        // Get all the experienciaList where calificaPais is null
        defaultExperienciaShouldNotBeFound("calificaPais.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaPaisIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaPais greater than or equals to DEFAULT_CALIFICA_PAIS
        defaultExperienciaShouldBeFound("calificaPais.greaterOrEqualThan=" + DEFAULT_CALIFICA_PAIS);

        // Get all the experienciaList where calificaPais greater than or equals to UPDATED_CALIFICA_PAIS
        defaultExperienciaShouldNotBeFound("calificaPais.greaterOrEqualThan=" + UPDATED_CALIFICA_PAIS);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaPaisIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaPais less than or equals to DEFAULT_CALIFICA_PAIS
        defaultExperienciaShouldNotBeFound("calificaPais.lessThan=" + DEFAULT_CALIFICA_PAIS);

        // Get all the experienciaList where calificaPais less than or equals to UPDATED_CALIFICA_PAIS
        defaultExperienciaShouldBeFound("calificaPais.lessThan=" + UPDATED_CALIFICA_PAIS);
    }


    @Test
    @Transactional
    public void getAllExperienciasByProgramaIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where programa equals to DEFAULT_PROGRAMA
        defaultExperienciaShouldBeFound("programa.equals=" + DEFAULT_PROGRAMA);

        // Get all the experienciaList where programa equals to UPDATED_PROGRAMA
        defaultExperienciaShouldNotBeFound("programa.equals=" + UPDATED_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByProgramaIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where programa in DEFAULT_PROGRAMA or UPDATED_PROGRAMA
        defaultExperienciaShouldBeFound("programa.in=" + DEFAULT_PROGRAMA + "," + UPDATED_PROGRAMA);

        // Get all the experienciaList where programa equals to UPDATED_PROGRAMA
        defaultExperienciaShouldNotBeFound("programa.in=" + UPDATED_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByProgramaIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where programa is not null
        defaultExperienciaShouldBeFound("programa.specified=true");

        // Get all the experienciaList where programa is null
        defaultExperienciaShouldNotBeFound("programa.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByInstitucionIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where institucion equals to DEFAULT_INSTITUCION
        defaultExperienciaShouldBeFound("institucion.equals=" + DEFAULT_INSTITUCION);

        // Get all the experienciaList where institucion equals to UPDATED_INSTITUCION
        defaultExperienciaShouldNotBeFound("institucion.equals=" + UPDATED_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByInstitucionIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where institucion in DEFAULT_INSTITUCION or UPDATED_INSTITUCION
        defaultExperienciaShouldBeFound("institucion.in=" + DEFAULT_INSTITUCION + "," + UPDATED_INSTITUCION);

        // Get all the experienciaList where institucion equals to UPDATED_INSTITUCION
        defaultExperienciaShouldNotBeFound("institucion.in=" + UPDATED_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByInstitucionIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where institucion is not null
        defaultExperienciaShouldBeFound("institucion.specified=true");

        // Get all the experienciaList where institucion is null
        defaultExperienciaShouldNotBeFound("institucion.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaInstitucionIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaInstitucion equals to DEFAULT_CALIFICA_INSTITUCION
        defaultExperienciaShouldBeFound("calificaInstitucion.equals=" + DEFAULT_CALIFICA_INSTITUCION);

        // Get all the experienciaList where calificaInstitucion equals to UPDATED_CALIFICA_INSTITUCION
        defaultExperienciaShouldNotBeFound("calificaInstitucion.equals=" + UPDATED_CALIFICA_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaInstitucionIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaInstitucion in DEFAULT_CALIFICA_INSTITUCION or UPDATED_CALIFICA_INSTITUCION
        defaultExperienciaShouldBeFound("calificaInstitucion.in=" + DEFAULT_CALIFICA_INSTITUCION + "," + UPDATED_CALIFICA_INSTITUCION);

        // Get all the experienciaList where calificaInstitucion equals to UPDATED_CALIFICA_INSTITUCION
        defaultExperienciaShouldNotBeFound("calificaInstitucion.in=" + UPDATED_CALIFICA_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaInstitucionIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaInstitucion is not null
        defaultExperienciaShouldBeFound("calificaInstitucion.specified=true");

        // Get all the experienciaList where calificaInstitucion is null
        defaultExperienciaShouldNotBeFound("calificaInstitucion.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaInstitucionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaInstitucion greater than or equals to DEFAULT_CALIFICA_INSTITUCION
        defaultExperienciaShouldBeFound("calificaInstitucion.greaterOrEqualThan=" + DEFAULT_CALIFICA_INSTITUCION);

        // Get all the experienciaList where calificaInstitucion greater than or equals to UPDATED_CALIFICA_INSTITUCION
        defaultExperienciaShouldNotBeFound("calificaInstitucion.greaterOrEqualThan=" + UPDATED_CALIFICA_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaInstitucionIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaInstitucion less than or equals to DEFAULT_CALIFICA_INSTITUCION
        defaultExperienciaShouldNotBeFound("calificaInstitucion.lessThan=" + DEFAULT_CALIFICA_INSTITUCION);

        // Get all the experienciaList where calificaInstitucion less than or equals to UPDATED_CALIFICA_INSTITUCION
        defaultExperienciaShouldBeFound("calificaInstitucion.lessThan=" + UPDATED_CALIFICA_INSTITUCION);
    }


    @Test
    @Transactional
    public void getAllExperienciasByAgenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where agencia equals to DEFAULT_AGENCIA
        defaultExperienciaShouldBeFound("agencia.equals=" + DEFAULT_AGENCIA);

        // Get all the experienciaList where agencia equals to UPDATED_AGENCIA
        defaultExperienciaShouldNotBeFound("agencia.equals=" + UPDATED_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByAgenciaIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where agencia in DEFAULT_AGENCIA or UPDATED_AGENCIA
        defaultExperienciaShouldBeFound("agencia.in=" + DEFAULT_AGENCIA + "," + UPDATED_AGENCIA);

        // Get all the experienciaList where agencia equals to UPDATED_AGENCIA
        defaultExperienciaShouldNotBeFound("agencia.in=" + UPDATED_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByAgenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where agencia is not null
        defaultExperienciaShouldBeFound("agencia.specified=true");

        // Get all the experienciaList where agencia is null
        defaultExperienciaShouldNotBeFound("agencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaAgenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaAgencia equals to DEFAULT_CALIFICA_AGENCIA
        defaultExperienciaShouldBeFound("calificaAgencia.equals=" + DEFAULT_CALIFICA_AGENCIA);

        // Get all the experienciaList where calificaAgencia equals to UPDATED_CALIFICA_AGENCIA
        defaultExperienciaShouldNotBeFound("calificaAgencia.equals=" + UPDATED_CALIFICA_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaAgenciaIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaAgencia in DEFAULT_CALIFICA_AGENCIA or UPDATED_CALIFICA_AGENCIA
        defaultExperienciaShouldBeFound("calificaAgencia.in=" + DEFAULT_CALIFICA_AGENCIA + "," + UPDATED_CALIFICA_AGENCIA);

        // Get all the experienciaList where calificaAgencia equals to UPDATED_CALIFICA_AGENCIA
        defaultExperienciaShouldNotBeFound("calificaAgencia.in=" + UPDATED_CALIFICA_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaAgenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaAgencia is not null
        defaultExperienciaShouldBeFound("calificaAgencia.specified=true");

        // Get all the experienciaList where calificaAgencia is null
        defaultExperienciaShouldNotBeFound("calificaAgencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaAgenciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaAgencia greater than or equals to DEFAULT_CALIFICA_AGENCIA
        defaultExperienciaShouldBeFound("calificaAgencia.greaterOrEqualThan=" + DEFAULT_CALIFICA_AGENCIA);

        // Get all the experienciaList where calificaAgencia greater than or equals to UPDATED_CALIFICA_AGENCIA
        defaultExperienciaShouldNotBeFound("calificaAgencia.greaterOrEqualThan=" + UPDATED_CALIFICA_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByCalificaAgenciaIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where calificaAgencia less than or equals to DEFAULT_CALIFICA_AGENCIA
        defaultExperienciaShouldNotBeFound("calificaAgencia.lessThan=" + DEFAULT_CALIFICA_AGENCIA);

        // Get all the experienciaList where calificaAgencia less than or equals to UPDATED_CALIFICA_AGENCIA
        defaultExperienciaShouldBeFound("calificaAgencia.lessThan=" + UPDATED_CALIFICA_AGENCIA);
    }


    @Test
    @Transactional
    public void getAllExperienciasByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where fecha equals to DEFAULT_FECHA
        defaultExperienciaShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the experienciaList where fecha equals to UPDATED_FECHA
        defaultExperienciaShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultExperienciaShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the experienciaList where fecha equals to UPDATED_FECHA
        defaultExperienciaShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where fecha is not null
        defaultExperienciaShouldBeFound("fecha.specified=true");

        // Get all the experienciaList where fecha is null
        defaultExperienciaShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllExperienciasByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where fecha greater than or equals to DEFAULT_FECHA
        defaultExperienciaShouldBeFound("fecha.greaterOrEqualThan=" + DEFAULT_FECHA);

        // Get all the experienciaList where fecha greater than or equals to UPDATED_FECHA
        defaultExperienciaShouldNotBeFound("fecha.greaterOrEqualThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllExperienciasByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        experienciaRepository.saveAndFlush(experiencia);

        // Get all the experienciaList where fecha less than or equals to DEFAULT_FECHA
        defaultExperienciaShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the experienciaList where fecha less than or equals to UPDATED_FECHA
        defaultExperienciaShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultExperienciaShouldBeFound(String filter) throws Exception {
        restExperienciaMockMvc.perform(get("/api/experiencias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(experiencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD)))
            .andExpect(jsonPath("$.[*].paisDestino").value(hasItem(DEFAULT_PAIS_DESTINO)))
            .andExpect(jsonPath("$.[*].calificaPais").value(hasItem(DEFAULT_CALIFICA_PAIS)))
            .andExpect(jsonPath("$.[*].programa").value(hasItem(DEFAULT_PROGRAMA)))
            .andExpect(jsonPath("$.[*].institucion").value(hasItem(DEFAULT_INSTITUCION)))
            .andExpect(jsonPath("$.[*].calificaInstitucion").value(hasItem(DEFAULT_CALIFICA_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agencia").value(hasItem(DEFAULT_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificaAgencia").value(hasItem(DEFAULT_CALIFICA_AGENCIA)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));

        // Check, that the count call also returns 1
        restExperienciaMockMvc.perform(get("/api/experiencias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultExperienciaShouldNotBeFound(String filter) throws Exception {
        restExperienciaMockMvc.perform(get("/api/experiencias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restExperienciaMockMvc.perform(get("/api/experiencias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingExperiencia() throws Exception {
        // Get the experiencia
        restExperienciaMockMvc.perform(get("/api/experiencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExperiencia() throws Exception {
        // Initialize the database
        experienciaService.save(experiencia);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockExperienciaSearchRepository);

        int databaseSizeBeforeUpdate = experienciaRepository.findAll().size();

        // Update the experiencia
        Experiencia updatedExperiencia = experienciaRepository.findById(experiencia.getId()).get();
        // Disconnect from session so that the updates on updatedExperiencia are not directly saved in db
        em.detach(updatedExperiencia);
        updatedExperiencia
            .identificacion(UPDATED_IDENTIFICACION)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .mail(UPDATED_MAIL)
            .area(UPDATED_AREA)
            .telefono(UPDATED_TELEFONO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .paisDestino(UPDATED_PAIS_DESTINO)
            .calificaPais(UPDATED_CALIFICA_PAIS)
            .programa(UPDATED_PROGRAMA)
            .institucion(UPDATED_INSTITUCION)
            .calificaInstitucion(UPDATED_CALIFICA_INSTITUCION)
            .agencia(UPDATED_AGENCIA)
            .calificaAgencia(UPDATED_CALIFICA_AGENCIA)
            .fecha(UPDATED_FECHA)
            .contenido(UPDATED_CONTENIDO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);

        restExperienciaMockMvc.perform(put("/api/experiencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExperiencia)))
            .andExpect(status().isOk());

        // Validate the Experiencia in the database
        List<Experiencia> experienciaList = experienciaRepository.findAll();
        assertThat(experienciaList).hasSize(databaseSizeBeforeUpdate);
        Experiencia testExperiencia = experienciaList.get(experienciaList.size() - 1);
        assertThat(testExperiencia.getIdentificacion()).isEqualTo(UPDATED_IDENTIFICACION);
        assertThat(testExperiencia.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testExperiencia.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testExperiencia.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testExperiencia.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testExperiencia.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testExperiencia.getNacionalidad()).isEqualTo(UPDATED_NACIONALIDAD);
        assertThat(testExperiencia.getPaisDestino()).isEqualTo(UPDATED_PAIS_DESTINO);
        assertThat(testExperiencia.getCalificaPais()).isEqualTo(UPDATED_CALIFICA_PAIS);
        assertThat(testExperiencia.getPrograma()).isEqualTo(UPDATED_PROGRAMA);
        assertThat(testExperiencia.getInstitucion()).isEqualTo(UPDATED_INSTITUCION);
        assertThat(testExperiencia.getCalificaInstitucion()).isEqualTo(UPDATED_CALIFICA_INSTITUCION);
        assertThat(testExperiencia.getAgencia()).isEqualTo(UPDATED_AGENCIA);
        assertThat(testExperiencia.getCalificaAgencia()).isEqualTo(UPDATED_CALIFICA_AGENCIA);
        assertThat(testExperiencia.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testExperiencia.getContenido()).isEqualTo(UPDATED_CONTENIDO);
        assertThat(testExperiencia.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testExperiencia.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);

        // Validate the Experiencia in Elasticsearch
        verify(mockExperienciaSearchRepository, times(1)).save(testExperiencia);
    }

    @Test
    @Transactional
    public void updateNonExistingExperiencia() throws Exception {
        int databaseSizeBeforeUpdate = experienciaRepository.findAll().size();

        // Create the Experiencia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExperienciaMockMvc.perform(put("/api/experiencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(experiencia)))
            .andExpect(status().isBadRequest());

        // Validate the Experiencia in the database
        List<Experiencia> experienciaList = experienciaRepository.findAll();
        assertThat(experienciaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Experiencia in Elasticsearch
        verify(mockExperienciaSearchRepository, times(0)).save(experiencia);
    }

    @Test
    @Transactional
    public void deleteExperiencia() throws Exception {
        // Initialize the database
        experienciaService.save(experiencia);

        int databaseSizeBeforeDelete = experienciaRepository.findAll().size();

        // Delete the experiencia
        restExperienciaMockMvc.perform(delete("/api/experiencias/{id}", experiencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Experiencia> experienciaList = experienciaRepository.findAll();
        assertThat(experienciaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Experiencia in Elasticsearch
        verify(mockExperienciaSearchRepository, times(1)).deleteById(experiencia.getId());
    }

    @Test
    @Transactional
    public void searchExperiencia() throws Exception {
        // Initialize the database
        experienciaService.save(experiencia);
        when(mockExperienciaSearchRepository.search(queryStringQuery("id:" + experiencia.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(experiencia), PageRequest.of(0, 1), 1));
        // Search the experiencia
        restExperienciaMockMvc.perform(get("/api/_search/experiencias?query=id:" + experiencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(experiencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD)))
            .andExpect(jsonPath("$.[*].paisDestino").value(hasItem(DEFAULT_PAIS_DESTINO)))
            .andExpect(jsonPath("$.[*].calificaPais").value(hasItem(DEFAULT_CALIFICA_PAIS)))
            .andExpect(jsonPath("$.[*].programa").value(hasItem(DEFAULT_PROGRAMA)))
            .andExpect(jsonPath("$.[*].institucion").value(hasItem(DEFAULT_INSTITUCION)))
            .andExpect(jsonPath("$.[*].calificaInstitucion").value(hasItem(DEFAULT_CALIFICA_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agencia").value(hasItem(DEFAULT_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificaAgencia").value(hasItem(DEFAULT_CALIFICA_AGENCIA)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Experiencia.class);
        Experiencia experiencia1 = new Experiencia();
        experiencia1.setId(1L);
        Experiencia experiencia2 = new Experiencia();
        experiencia2.setId(experiencia1.getId());
        assertThat(experiencia1).isEqualTo(experiencia2);
        experiencia2.setId(2L);
        assertThat(experiencia1).isNotEqualTo(experiencia2);
        experiencia1.setId(null);
        assertThat(experiencia1).isNotEqualTo(experiencia2);
    }
}
