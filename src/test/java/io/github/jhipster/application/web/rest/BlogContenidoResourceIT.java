package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.BlogContenido;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.repository.BlogContenidoRepository;
import io.github.jhipster.application.repository.search.BlogContenidoSearchRepository;
import io.github.jhipster.application.service.BlogContenidoService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.BlogContenidoCriteria;
import io.github.jhipster.application.service.BlogContenidoQueryService;

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

import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Destinod;
/**
 * Integration tests for the {@Link BlogContenidoResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class BlogContenidoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final Nacionalidadd DEFAULT_NACIONALIDAD = Nacionalidadd.Argentina;
    private static final Nacionalidadd UPDATED_NACIONALIDAD = Nacionalidadd.Bolivia;

    private static final Destinod DEFAULT_PAIS_ESTUDIO = Destinod.ALEMANIA;
    private static final Destinod UPDATED_PAIS_ESTUDIO = Destinod.AUSTRALIA;

    private static final Integer DEFAULT_CALIFICACION_PAIS = 1;
    private static final Integer UPDATED_CALIFICACION_PAIS = 2;

    private static final String DEFAULT_CIUDAD_VIVE = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD_VIVE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICACION_CIUDAD = 1;
    private static final Integer UPDATED_CALIFICACION_CIUDAD = 2;

    private static final String DEFAULT_PROGRAMA_REALIZADO = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_REALIZADO = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUCION_ESTUDIO = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUCION_ESTUDIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICACION_INSTITUCION = 1;
    private static final Integer UPDATED_CALIFICACION_INSTITUCION = 2;

    private static final Boolean DEFAULT_AGENCIA_ESTUDIOS = false;
    private static final Boolean UPDATED_AGENCIA_ESTUDIOS = true;

    private static final String DEFAULT_NOMBRE_AGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_AGENCIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICACION_AGENCIA = 1;
    private static final Integer UPDATED_CALIFICACION_AGENCIA = 2;

    private static final Integer DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO = 1;
    private static final Integer UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO = 2;

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CONTENIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTENIDO = "BBBBBBBBBB";

    @Autowired
    private BlogContenidoRepository blogContenidoRepository;

    @Autowired
    private BlogContenidoService blogContenidoService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.BlogContenidoSearchRepositoryMockConfiguration
     */
    @Autowired
    private BlogContenidoSearchRepository mockBlogContenidoSearchRepository;

    @Autowired
    private BlogContenidoQueryService blogContenidoQueryService;

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

    private MockMvc restBlogContenidoMockMvc;

    private BlogContenido blogContenido;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlogContenidoResource blogContenidoResource = new BlogContenidoResource(blogContenidoService, blogContenidoQueryService);
        this.restBlogContenidoMockMvc = MockMvcBuilders.standaloneSetup(blogContenidoResource)
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
    public static BlogContenido createEntity(EntityManager em) {
        BlogContenido blogContenido = new BlogContenido()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .correo(DEFAULT_CORREO)
            .nacionalidad(DEFAULT_NACIONALIDAD)
            .paisEstudio(DEFAULT_PAIS_ESTUDIO)
            .calificacionPais(DEFAULT_CALIFICACION_PAIS)
            .ciudadVive(DEFAULT_CIUDAD_VIVE)
            .calificacionCiudad(DEFAULT_CALIFICACION_CIUDAD)
            .programaRealizado(DEFAULT_PROGRAMA_REALIZADO)
            .institucionEstudio(DEFAULT_INSTITUCION_ESTUDIO)
            .calificacionInstitucion(DEFAULT_CALIFICACION_INSTITUCION)
            .agenciaEstudios(DEFAULT_AGENCIA_ESTUDIOS)
            .nombreAgencia(DEFAULT_NOMBRE_AGENCIA)
            .calificacionAgencia(DEFAULT_CALIFICACION_AGENCIA)
            .calificacionExperienciaEstudio(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO)
            .fecha(DEFAULT_FECHA)
            .titulo(DEFAULT_TITULO)
            .imagen(DEFAULT_IMAGEN)
            .imagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE)
            .contenido(DEFAULT_CONTENIDO);
        return blogContenido;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlogContenido createUpdatedEntity(EntityManager em) {
        BlogContenido blogContenido = new BlogContenido()
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .correo(UPDATED_CORREO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .paisEstudio(UPDATED_PAIS_ESTUDIO)
            .calificacionPais(UPDATED_CALIFICACION_PAIS)
            .ciudadVive(UPDATED_CIUDAD_VIVE)
            .calificacionCiudad(UPDATED_CALIFICACION_CIUDAD)
            .programaRealizado(UPDATED_PROGRAMA_REALIZADO)
            .institucionEstudio(UPDATED_INSTITUCION_ESTUDIO)
            .calificacionInstitucion(UPDATED_CALIFICACION_INSTITUCION)
            .agenciaEstudios(UPDATED_AGENCIA_ESTUDIOS)
            .nombreAgencia(UPDATED_NOMBRE_AGENCIA)
            .calificacionAgencia(UPDATED_CALIFICACION_AGENCIA)
            .calificacionExperienciaEstudio(UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO)
            .fecha(UPDATED_FECHA)
            .titulo(UPDATED_TITULO)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE)
            .contenido(UPDATED_CONTENIDO);
        return blogContenido;
    }

    @BeforeEach
    public void initTest() {
        blogContenido = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlogContenido() throws Exception {
        int databaseSizeBeforeCreate = blogContenidoRepository.findAll().size();

        // Create the BlogContenido
        restBlogContenidoMockMvc.perform(post("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogContenido)))
            .andExpect(status().isCreated());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeCreate + 1);
        BlogContenido testBlogContenido = blogContenidoList.get(blogContenidoList.size() - 1);
        assertThat(testBlogContenido.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testBlogContenido.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testBlogContenido.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testBlogContenido.getNacionalidad()).isEqualTo(DEFAULT_NACIONALIDAD);
        assertThat(testBlogContenido.getPaisEstudio()).isEqualTo(DEFAULT_PAIS_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionPais()).isEqualTo(DEFAULT_CALIFICACION_PAIS);
        assertThat(testBlogContenido.getCiudadVive()).isEqualTo(DEFAULT_CIUDAD_VIVE);
        assertThat(testBlogContenido.getCalificacionCiudad()).isEqualTo(DEFAULT_CALIFICACION_CIUDAD);
        assertThat(testBlogContenido.getProgramaRealizado()).isEqualTo(DEFAULT_PROGRAMA_REALIZADO);
        assertThat(testBlogContenido.getInstitucionEstudio()).isEqualTo(DEFAULT_INSTITUCION_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionInstitucion()).isEqualTo(DEFAULT_CALIFICACION_INSTITUCION);
        assertThat(testBlogContenido.isAgenciaEstudios()).isEqualTo(DEFAULT_AGENCIA_ESTUDIOS);
        assertThat(testBlogContenido.getNombreAgencia()).isEqualTo(DEFAULT_NOMBRE_AGENCIA);
        assertThat(testBlogContenido.getCalificacionAgencia()).isEqualTo(DEFAULT_CALIFICACION_AGENCIA);
        assertThat(testBlogContenido.getCalificacionExperienciaEstudio()).isEqualTo(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO);
        assertThat(testBlogContenido.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testBlogContenido.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testBlogContenido.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testBlogContenido.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
        assertThat(testBlogContenido.getContenido()).isEqualTo(DEFAULT_CONTENIDO);

        // Validate the BlogContenido in Elasticsearch
        verify(mockBlogContenidoSearchRepository, times(1)).save(testBlogContenido);
    }

    @Test
    @Transactional
    public void createBlogContenidoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blogContenidoRepository.findAll().size();

        // Create the BlogContenido with an existing ID
        blogContenido.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlogContenidoMockMvc.perform(post("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogContenido)))
            .andExpect(status().isBadRequest());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeCreate);

        // Validate the BlogContenido in Elasticsearch
        verify(mockBlogContenidoSearchRepository, times(0)).save(blogContenido);
    }


    @Test
    @Transactional
    public void getAllBlogContenidos() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blogContenido.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO.toString())))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].paisEstudio").value(hasItem(DEFAULT_PAIS_ESTUDIO.toString())))
            .andExpect(jsonPath("$.[*].calificacionPais").value(hasItem(DEFAULT_CALIFICACION_PAIS)))
            .andExpect(jsonPath("$.[*].ciudadVive").value(hasItem(DEFAULT_CIUDAD_VIVE.toString())))
            .andExpect(jsonPath("$.[*].calificacionCiudad").value(hasItem(DEFAULT_CALIFICACION_CIUDAD)))
            .andExpect(jsonPath("$.[*].programaRealizado").value(hasItem(DEFAULT_PROGRAMA_REALIZADO.toString())))
            .andExpect(jsonPath("$.[*].institucionEstudio").value(hasItem(DEFAULT_INSTITUCION_ESTUDIO.toString())))
            .andExpect(jsonPath("$.[*].calificacionInstitucion").value(hasItem(DEFAULT_CALIFICACION_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agenciaEstudios").value(hasItem(DEFAULT_AGENCIA_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA.toString())))
            .andExpect(jsonPath("$.[*].calificacionAgencia").value(hasItem(DEFAULT_CALIFICACION_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificacionExperienciaEstudio").value(hasItem(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())));
    }
    
    @Test
    @Transactional
    public void getBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get the blogContenido
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos/{id}", blogContenido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blogContenido.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO.toString()))
            .andExpect(jsonPath("$.nacionalidad").value(DEFAULT_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.paisEstudio").value(DEFAULT_PAIS_ESTUDIO.toString()))
            .andExpect(jsonPath("$.calificacionPais").value(DEFAULT_CALIFICACION_PAIS))
            .andExpect(jsonPath("$.ciudadVive").value(DEFAULT_CIUDAD_VIVE.toString()))
            .andExpect(jsonPath("$.calificacionCiudad").value(DEFAULT_CALIFICACION_CIUDAD))
            .andExpect(jsonPath("$.programaRealizado").value(DEFAULT_PROGRAMA_REALIZADO.toString()))
            .andExpect(jsonPath("$.institucionEstudio").value(DEFAULT_INSTITUCION_ESTUDIO.toString()))
            .andExpect(jsonPath("$.calificacionInstitucion").value(DEFAULT_CALIFICACION_INSTITUCION))
            .andExpect(jsonPath("$.agenciaEstudios").value(DEFAULT_AGENCIA_ESTUDIOS.booleanValue()))
            .andExpect(jsonPath("$.nombreAgencia").value(DEFAULT_NOMBRE_AGENCIA.toString()))
            .andExpect(jsonPath("$.calificacionAgencia").value(DEFAULT_CALIFICACION_AGENCIA))
            .andExpect(jsonPath("$.calificacionExperienciaEstudio").value(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)))
            .andExpect(jsonPath("$.contenido").value(DEFAULT_CONTENIDO.toString()));
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nombre equals to DEFAULT_NOMBRE
        defaultBlogContenidoShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the blogContenidoList where nombre equals to UPDATED_NOMBRE
        defaultBlogContenidoShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultBlogContenidoShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the blogContenidoList where nombre equals to UPDATED_NOMBRE
        defaultBlogContenidoShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nombre is not null
        defaultBlogContenidoShouldBeFound("nombre.specified=true");

        // Get all the blogContenidoList where nombre is null
        defaultBlogContenidoShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByApellidoIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where apellido equals to DEFAULT_APELLIDO
        defaultBlogContenidoShouldBeFound("apellido.equals=" + DEFAULT_APELLIDO);

        // Get all the blogContenidoList where apellido equals to UPDATED_APELLIDO
        defaultBlogContenidoShouldNotBeFound("apellido.equals=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByApellidoIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where apellido in DEFAULT_APELLIDO or UPDATED_APELLIDO
        defaultBlogContenidoShouldBeFound("apellido.in=" + DEFAULT_APELLIDO + "," + UPDATED_APELLIDO);

        // Get all the blogContenidoList where apellido equals to UPDATED_APELLIDO
        defaultBlogContenidoShouldNotBeFound("apellido.in=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByApellidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where apellido is not null
        defaultBlogContenidoShouldBeFound("apellido.specified=true");

        // Get all the blogContenidoList where apellido is null
        defaultBlogContenidoShouldNotBeFound("apellido.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCorreoIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where correo equals to DEFAULT_CORREO
        defaultBlogContenidoShouldBeFound("correo.equals=" + DEFAULT_CORREO);

        // Get all the blogContenidoList where correo equals to UPDATED_CORREO
        defaultBlogContenidoShouldNotBeFound("correo.equals=" + UPDATED_CORREO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCorreoIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where correo in DEFAULT_CORREO or UPDATED_CORREO
        defaultBlogContenidoShouldBeFound("correo.in=" + DEFAULT_CORREO + "," + UPDATED_CORREO);

        // Get all the blogContenidoList where correo equals to UPDATED_CORREO
        defaultBlogContenidoShouldNotBeFound("correo.in=" + UPDATED_CORREO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCorreoIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where correo is not null
        defaultBlogContenidoShouldBeFound("correo.specified=true");

        // Get all the blogContenidoList where correo is null
        defaultBlogContenidoShouldNotBeFound("correo.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNacionalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nacionalidad equals to DEFAULT_NACIONALIDAD
        defaultBlogContenidoShouldBeFound("nacionalidad.equals=" + DEFAULT_NACIONALIDAD);

        // Get all the blogContenidoList where nacionalidad equals to UPDATED_NACIONALIDAD
        defaultBlogContenidoShouldNotBeFound("nacionalidad.equals=" + UPDATED_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNacionalidadIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nacionalidad in DEFAULT_NACIONALIDAD or UPDATED_NACIONALIDAD
        defaultBlogContenidoShouldBeFound("nacionalidad.in=" + DEFAULT_NACIONALIDAD + "," + UPDATED_NACIONALIDAD);

        // Get all the blogContenidoList where nacionalidad equals to UPDATED_NACIONALIDAD
        defaultBlogContenidoShouldNotBeFound("nacionalidad.in=" + UPDATED_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNacionalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nacionalidad is not null
        defaultBlogContenidoShouldBeFound("nacionalidad.specified=true");

        // Get all the blogContenidoList where nacionalidad is null
        defaultBlogContenidoShouldNotBeFound("nacionalidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByPaisEstudioIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where paisEstudio equals to DEFAULT_PAIS_ESTUDIO
        defaultBlogContenidoShouldBeFound("paisEstudio.equals=" + DEFAULT_PAIS_ESTUDIO);

        // Get all the blogContenidoList where paisEstudio equals to UPDATED_PAIS_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("paisEstudio.equals=" + UPDATED_PAIS_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByPaisEstudioIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where paisEstudio in DEFAULT_PAIS_ESTUDIO or UPDATED_PAIS_ESTUDIO
        defaultBlogContenidoShouldBeFound("paisEstudio.in=" + DEFAULT_PAIS_ESTUDIO + "," + UPDATED_PAIS_ESTUDIO);

        // Get all the blogContenidoList where paisEstudio equals to UPDATED_PAIS_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("paisEstudio.in=" + UPDATED_PAIS_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByPaisEstudioIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where paisEstudio is not null
        defaultBlogContenidoShouldBeFound("paisEstudio.specified=true");

        // Get all the blogContenidoList where paisEstudio is null
        defaultBlogContenidoShouldNotBeFound("paisEstudio.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionPais equals to DEFAULT_CALIFICACION_PAIS
        defaultBlogContenidoShouldBeFound("calificacionPais.equals=" + DEFAULT_CALIFICACION_PAIS);

        // Get all the blogContenidoList where calificacionPais equals to UPDATED_CALIFICACION_PAIS
        defaultBlogContenidoShouldNotBeFound("calificacionPais.equals=" + UPDATED_CALIFICACION_PAIS);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionPaisIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionPais in DEFAULT_CALIFICACION_PAIS or UPDATED_CALIFICACION_PAIS
        defaultBlogContenidoShouldBeFound("calificacionPais.in=" + DEFAULT_CALIFICACION_PAIS + "," + UPDATED_CALIFICACION_PAIS);

        // Get all the blogContenidoList where calificacionPais equals to UPDATED_CALIFICACION_PAIS
        defaultBlogContenidoShouldNotBeFound("calificacionPais.in=" + UPDATED_CALIFICACION_PAIS);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionPais is not null
        defaultBlogContenidoShouldBeFound("calificacionPais.specified=true");

        // Get all the blogContenidoList where calificacionPais is null
        defaultBlogContenidoShouldNotBeFound("calificacionPais.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionPaisIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionPais greater than or equals to DEFAULT_CALIFICACION_PAIS
        defaultBlogContenidoShouldBeFound("calificacionPais.greaterOrEqualThan=" + DEFAULT_CALIFICACION_PAIS);

        // Get all the blogContenidoList where calificacionPais greater than or equals to UPDATED_CALIFICACION_PAIS
        defaultBlogContenidoShouldNotBeFound("calificacionPais.greaterOrEqualThan=" + UPDATED_CALIFICACION_PAIS);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionPaisIsLessThanSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionPais less than or equals to DEFAULT_CALIFICACION_PAIS
        defaultBlogContenidoShouldNotBeFound("calificacionPais.lessThan=" + DEFAULT_CALIFICACION_PAIS);

        // Get all the blogContenidoList where calificacionPais less than or equals to UPDATED_CALIFICACION_PAIS
        defaultBlogContenidoShouldBeFound("calificacionPais.lessThan=" + UPDATED_CALIFICACION_PAIS);
    }


    @Test
    @Transactional
    public void getAllBlogContenidosByCiudadViveIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where ciudadVive equals to DEFAULT_CIUDAD_VIVE
        defaultBlogContenidoShouldBeFound("ciudadVive.equals=" + DEFAULT_CIUDAD_VIVE);

        // Get all the blogContenidoList where ciudadVive equals to UPDATED_CIUDAD_VIVE
        defaultBlogContenidoShouldNotBeFound("ciudadVive.equals=" + UPDATED_CIUDAD_VIVE);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCiudadViveIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where ciudadVive in DEFAULT_CIUDAD_VIVE or UPDATED_CIUDAD_VIVE
        defaultBlogContenidoShouldBeFound("ciudadVive.in=" + DEFAULT_CIUDAD_VIVE + "," + UPDATED_CIUDAD_VIVE);

        // Get all the blogContenidoList where ciudadVive equals to UPDATED_CIUDAD_VIVE
        defaultBlogContenidoShouldNotBeFound("ciudadVive.in=" + UPDATED_CIUDAD_VIVE);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCiudadViveIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where ciudadVive is not null
        defaultBlogContenidoShouldBeFound("ciudadVive.specified=true");

        // Get all the blogContenidoList where ciudadVive is null
        defaultBlogContenidoShouldNotBeFound("ciudadVive.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionCiudadIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionCiudad equals to DEFAULT_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldBeFound("calificacionCiudad.equals=" + DEFAULT_CALIFICACION_CIUDAD);

        // Get all the blogContenidoList where calificacionCiudad equals to UPDATED_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldNotBeFound("calificacionCiudad.equals=" + UPDATED_CALIFICACION_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionCiudadIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionCiudad in DEFAULT_CALIFICACION_CIUDAD or UPDATED_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldBeFound("calificacionCiudad.in=" + DEFAULT_CALIFICACION_CIUDAD + "," + UPDATED_CALIFICACION_CIUDAD);

        // Get all the blogContenidoList where calificacionCiudad equals to UPDATED_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldNotBeFound("calificacionCiudad.in=" + UPDATED_CALIFICACION_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionCiudadIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionCiudad is not null
        defaultBlogContenidoShouldBeFound("calificacionCiudad.specified=true");

        // Get all the blogContenidoList where calificacionCiudad is null
        defaultBlogContenidoShouldNotBeFound("calificacionCiudad.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionCiudadIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionCiudad greater than or equals to DEFAULT_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldBeFound("calificacionCiudad.greaterOrEqualThan=" + DEFAULT_CALIFICACION_CIUDAD);

        // Get all the blogContenidoList where calificacionCiudad greater than or equals to UPDATED_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldNotBeFound("calificacionCiudad.greaterOrEqualThan=" + UPDATED_CALIFICACION_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionCiudadIsLessThanSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionCiudad less than or equals to DEFAULT_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldNotBeFound("calificacionCiudad.lessThan=" + DEFAULT_CALIFICACION_CIUDAD);

        // Get all the blogContenidoList where calificacionCiudad less than or equals to UPDATED_CALIFICACION_CIUDAD
        defaultBlogContenidoShouldBeFound("calificacionCiudad.lessThan=" + UPDATED_CALIFICACION_CIUDAD);
    }


    @Test
    @Transactional
    public void getAllBlogContenidosByProgramaRealizadoIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where programaRealizado equals to DEFAULT_PROGRAMA_REALIZADO
        defaultBlogContenidoShouldBeFound("programaRealizado.equals=" + DEFAULT_PROGRAMA_REALIZADO);

        // Get all the blogContenidoList where programaRealizado equals to UPDATED_PROGRAMA_REALIZADO
        defaultBlogContenidoShouldNotBeFound("programaRealizado.equals=" + UPDATED_PROGRAMA_REALIZADO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByProgramaRealizadoIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where programaRealizado in DEFAULT_PROGRAMA_REALIZADO or UPDATED_PROGRAMA_REALIZADO
        defaultBlogContenidoShouldBeFound("programaRealizado.in=" + DEFAULT_PROGRAMA_REALIZADO + "," + UPDATED_PROGRAMA_REALIZADO);

        // Get all the blogContenidoList where programaRealizado equals to UPDATED_PROGRAMA_REALIZADO
        defaultBlogContenidoShouldNotBeFound("programaRealizado.in=" + UPDATED_PROGRAMA_REALIZADO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByProgramaRealizadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where programaRealizado is not null
        defaultBlogContenidoShouldBeFound("programaRealizado.specified=true");

        // Get all the blogContenidoList where programaRealizado is null
        defaultBlogContenidoShouldNotBeFound("programaRealizado.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByInstitucionEstudioIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where institucionEstudio equals to DEFAULT_INSTITUCION_ESTUDIO
        defaultBlogContenidoShouldBeFound("institucionEstudio.equals=" + DEFAULT_INSTITUCION_ESTUDIO);

        // Get all the blogContenidoList where institucionEstudio equals to UPDATED_INSTITUCION_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("institucionEstudio.equals=" + UPDATED_INSTITUCION_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByInstitucionEstudioIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where institucionEstudio in DEFAULT_INSTITUCION_ESTUDIO or UPDATED_INSTITUCION_ESTUDIO
        defaultBlogContenidoShouldBeFound("institucionEstudio.in=" + DEFAULT_INSTITUCION_ESTUDIO + "," + UPDATED_INSTITUCION_ESTUDIO);

        // Get all the blogContenidoList where institucionEstudio equals to UPDATED_INSTITUCION_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("institucionEstudio.in=" + UPDATED_INSTITUCION_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByInstitucionEstudioIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where institucionEstudio is not null
        defaultBlogContenidoShouldBeFound("institucionEstudio.specified=true");

        // Get all the blogContenidoList where institucionEstudio is null
        defaultBlogContenidoShouldNotBeFound("institucionEstudio.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionInstitucionIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionInstitucion equals to DEFAULT_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldBeFound("calificacionInstitucion.equals=" + DEFAULT_CALIFICACION_INSTITUCION);

        // Get all the blogContenidoList where calificacionInstitucion equals to UPDATED_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldNotBeFound("calificacionInstitucion.equals=" + UPDATED_CALIFICACION_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionInstitucionIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionInstitucion in DEFAULT_CALIFICACION_INSTITUCION or UPDATED_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldBeFound("calificacionInstitucion.in=" + DEFAULT_CALIFICACION_INSTITUCION + "," + UPDATED_CALIFICACION_INSTITUCION);

        // Get all the blogContenidoList where calificacionInstitucion equals to UPDATED_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldNotBeFound("calificacionInstitucion.in=" + UPDATED_CALIFICACION_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionInstitucionIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionInstitucion is not null
        defaultBlogContenidoShouldBeFound("calificacionInstitucion.specified=true");

        // Get all the blogContenidoList where calificacionInstitucion is null
        defaultBlogContenidoShouldNotBeFound("calificacionInstitucion.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionInstitucionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionInstitucion greater than or equals to DEFAULT_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldBeFound("calificacionInstitucion.greaterOrEqualThan=" + DEFAULT_CALIFICACION_INSTITUCION);

        // Get all the blogContenidoList where calificacionInstitucion greater than or equals to UPDATED_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldNotBeFound("calificacionInstitucion.greaterOrEqualThan=" + UPDATED_CALIFICACION_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionInstitucionIsLessThanSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionInstitucion less than or equals to DEFAULT_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldNotBeFound("calificacionInstitucion.lessThan=" + DEFAULT_CALIFICACION_INSTITUCION);

        // Get all the blogContenidoList where calificacionInstitucion less than or equals to UPDATED_CALIFICACION_INSTITUCION
        defaultBlogContenidoShouldBeFound("calificacionInstitucion.lessThan=" + UPDATED_CALIFICACION_INSTITUCION);
    }


    @Test
    @Transactional
    public void getAllBlogContenidosByAgenciaEstudiosIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where agenciaEstudios equals to DEFAULT_AGENCIA_ESTUDIOS
        defaultBlogContenidoShouldBeFound("agenciaEstudios.equals=" + DEFAULT_AGENCIA_ESTUDIOS);

        // Get all the blogContenidoList where agenciaEstudios equals to UPDATED_AGENCIA_ESTUDIOS
        defaultBlogContenidoShouldNotBeFound("agenciaEstudios.equals=" + UPDATED_AGENCIA_ESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByAgenciaEstudiosIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where agenciaEstudios in DEFAULT_AGENCIA_ESTUDIOS or UPDATED_AGENCIA_ESTUDIOS
        defaultBlogContenidoShouldBeFound("agenciaEstudios.in=" + DEFAULT_AGENCIA_ESTUDIOS + "," + UPDATED_AGENCIA_ESTUDIOS);

        // Get all the blogContenidoList where agenciaEstudios equals to UPDATED_AGENCIA_ESTUDIOS
        defaultBlogContenidoShouldNotBeFound("agenciaEstudios.in=" + UPDATED_AGENCIA_ESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByAgenciaEstudiosIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where agenciaEstudios is not null
        defaultBlogContenidoShouldBeFound("agenciaEstudios.specified=true");

        // Get all the blogContenidoList where agenciaEstudios is null
        defaultBlogContenidoShouldNotBeFound("agenciaEstudios.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNombreAgenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nombreAgencia equals to DEFAULT_NOMBRE_AGENCIA
        defaultBlogContenidoShouldBeFound("nombreAgencia.equals=" + DEFAULT_NOMBRE_AGENCIA);

        // Get all the blogContenidoList where nombreAgencia equals to UPDATED_NOMBRE_AGENCIA
        defaultBlogContenidoShouldNotBeFound("nombreAgencia.equals=" + UPDATED_NOMBRE_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNombreAgenciaIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nombreAgencia in DEFAULT_NOMBRE_AGENCIA or UPDATED_NOMBRE_AGENCIA
        defaultBlogContenidoShouldBeFound("nombreAgencia.in=" + DEFAULT_NOMBRE_AGENCIA + "," + UPDATED_NOMBRE_AGENCIA);

        // Get all the blogContenidoList where nombreAgencia equals to UPDATED_NOMBRE_AGENCIA
        defaultBlogContenidoShouldNotBeFound("nombreAgencia.in=" + UPDATED_NOMBRE_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByNombreAgenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where nombreAgencia is not null
        defaultBlogContenidoShouldBeFound("nombreAgencia.specified=true");

        // Get all the blogContenidoList where nombreAgencia is null
        defaultBlogContenidoShouldNotBeFound("nombreAgencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionAgenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionAgencia equals to DEFAULT_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldBeFound("calificacionAgencia.equals=" + DEFAULT_CALIFICACION_AGENCIA);

        // Get all the blogContenidoList where calificacionAgencia equals to UPDATED_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldNotBeFound("calificacionAgencia.equals=" + UPDATED_CALIFICACION_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionAgenciaIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionAgencia in DEFAULT_CALIFICACION_AGENCIA or UPDATED_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldBeFound("calificacionAgencia.in=" + DEFAULT_CALIFICACION_AGENCIA + "," + UPDATED_CALIFICACION_AGENCIA);

        // Get all the blogContenidoList where calificacionAgencia equals to UPDATED_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldNotBeFound("calificacionAgencia.in=" + UPDATED_CALIFICACION_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionAgenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionAgencia is not null
        defaultBlogContenidoShouldBeFound("calificacionAgencia.specified=true");

        // Get all the blogContenidoList where calificacionAgencia is null
        defaultBlogContenidoShouldNotBeFound("calificacionAgencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionAgenciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionAgencia greater than or equals to DEFAULT_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldBeFound("calificacionAgencia.greaterOrEqualThan=" + DEFAULT_CALIFICACION_AGENCIA);

        // Get all the blogContenidoList where calificacionAgencia greater than or equals to UPDATED_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldNotBeFound("calificacionAgencia.greaterOrEqualThan=" + UPDATED_CALIFICACION_AGENCIA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionAgenciaIsLessThanSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionAgencia less than or equals to DEFAULT_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldNotBeFound("calificacionAgencia.lessThan=" + DEFAULT_CALIFICACION_AGENCIA);

        // Get all the blogContenidoList where calificacionAgencia less than or equals to UPDATED_CALIFICACION_AGENCIA
        defaultBlogContenidoShouldBeFound("calificacionAgencia.lessThan=" + UPDATED_CALIFICACION_AGENCIA);
    }


    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionExperienciaEstudioIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionExperienciaEstudio equals to DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldBeFound("calificacionExperienciaEstudio.equals=" + DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO);

        // Get all the blogContenidoList where calificacionExperienciaEstudio equals to UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("calificacionExperienciaEstudio.equals=" + UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionExperienciaEstudioIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionExperienciaEstudio in DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO or UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldBeFound("calificacionExperienciaEstudio.in=" + DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO + "," + UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);

        // Get all the blogContenidoList where calificacionExperienciaEstudio equals to UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("calificacionExperienciaEstudio.in=" + UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionExperienciaEstudioIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionExperienciaEstudio is not null
        defaultBlogContenidoShouldBeFound("calificacionExperienciaEstudio.specified=true");

        // Get all the blogContenidoList where calificacionExperienciaEstudio is null
        defaultBlogContenidoShouldNotBeFound("calificacionExperienciaEstudio.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionExperienciaEstudioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionExperienciaEstudio greater than or equals to DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldBeFound("calificacionExperienciaEstudio.greaterOrEqualThan=" + DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO);

        // Get all the blogContenidoList where calificacionExperienciaEstudio greater than or equals to UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("calificacionExperienciaEstudio.greaterOrEqualThan=" + UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByCalificacionExperienciaEstudioIsLessThanSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where calificacionExperienciaEstudio less than or equals to DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldNotBeFound("calificacionExperienciaEstudio.lessThan=" + DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO);

        // Get all the blogContenidoList where calificacionExperienciaEstudio less than or equals to UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO
        defaultBlogContenidoShouldBeFound("calificacionExperienciaEstudio.lessThan=" + UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);
    }


    @Test
    @Transactional
    public void getAllBlogContenidosByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where fecha equals to DEFAULT_FECHA
        defaultBlogContenidoShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the blogContenidoList where fecha equals to UPDATED_FECHA
        defaultBlogContenidoShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultBlogContenidoShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the blogContenidoList where fecha equals to UPDATED_FECHA
        defaultBlogContenidoShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where fecha is not null
        defaultBlogContenidoShouldBeFound("fecha.specified=true");

        // Get all the blogContenidoList where fecha is null
        defaultBlogContenidoShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where fecha greater than or equals to DEFAULT_FECHA
        defaultBlogContenidoShouldBeFound("fecha.greaterOrEqualThan=" + DEFAULT_FECHA);

        // Get all the blogContenidoList where fecha greater than or equals to UPDATED_FECHA
        defaultBlogContenidoShouldNotBeFound("fecha.greaterOrEqualThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where fecha less than or equals to DEFAULT_FECHA
        defaultBlogContenidoShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the blogContenidoList where fecha less than or equals to UPDATED_FECHA
        defaultBlogContenidoShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }


    @Test
    @Transactional
    public void getAllBlogContenidosByTituloIsEqualToSomething() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where titulo equals to DEFAULT_TITULO
        defaultBlogContenidoShouldBeFound("titulo.equals=" + DEFAULT_TITULO);

        // Get all the blogContenidoList where titulo equals to UPDATED_TITULO
        defaultBlogContenidoShouldNotBeFound("titulo.equals=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByTituloIsInShouldWork() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where titulo in DEFAULT_TITULO or UPDATED_TITULO
        defaultBlogContenidoShouldBeFound("titulo.in=" + DEFAULT_TITULO + "," + UPDATED_TITULO);

        // Get all the blogContenidoList where titulo equals to UPDATED_TITULO
        defaultBlogContenidoShouldNotBeFound("titulo.in=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByTituloIsNullOrNotNull() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList where titulo is not null
        defaultBlogContenidoShouldBeFound("titulo.specified=true");

        // Get all the blogContenidoList where titulo is null
        defaultBlogContenidoShouldNotBeFound("titulo.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlogContenidosByUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        User usuario = UserResourceIT.createEntity(em);
        em.persist(usuario);
        em.flush();
        blogContenido.setUsuario(usuario);
        blogContenidoRepository.saveAndFlush(blogContenido);
        Long usuarioId = usuario.getId();

        // Get all the blogContenidoList where usuario equals to usuarioId
        defaultBlogContenidoShouldBeFound("usuarioId.equals=" + usuarioId);

        // Get all the blogContenidoList where usuario equals to usuarioId + 1
        defaultBlogContenidoShouldNotBeFound("usuarioId.equals=" + (usuarioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBlogContenidoShouldBeFound(String filter) throws Exception {
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blogContenido.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].paisEstudio").value(hasItem(DEFAULT_PAIS_ESTUDIO.toString())))
            .andExpect(jsonPath("$.[*].calificacionPais").value(hasItem(DEFAULT_CALIFICACION_PAIS)))
            .andExpect(jsonPath("$.[*].ciudadVive").value(hasItem(DEFAULT_CIUDAD_VIVE)))
            .andExpect(jsonPath("$.[*].calificacionCiudad").value(hasItem(DEFAULT_CALIFICACION_CIUDAD)))
            .andExpect(jsonPath("$.[*].programaRealizado").value(hasItem(DEFAULT_PROGRAMA_REALIZADO)))
            .andExpect(jsonPath("$.[*].institucionEstudio").value(hasItem(DEFAULT_INSTITUCION_ESTUDIO)))
            .andExpect(jsonPath("$.[*].calificacionInstitucion").value(hasItem(DEFAULT_CALIFICACION_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agenciaEstudios").value(hasItem(DEFAULT_AGENCIA_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificacionAgencia").value(hasItem(DEFAULT_CALIFICACION_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificacionExperienciaEstudio").value(hasItem(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())));

        // Check, that the count call also returns 1
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBlogContenidoShouldNotBeFound(String filter) throws Exception {
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBlogContenido() throws Exception {
        // Get the blogContenido
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoService.save(blogContenido);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockBlogContenidoSearchRepository);

        int databaseSizeBeforeUpdate = blogContenidoRepository.findAll().size();

        // Update the blogContenido
        BlogContenido updatedBlogContenido = blogContenidoRepository.findById(blogContenido.getId()).get();
        // Disconnect from session so that the updates on updatedBlogContenido are not directly saved in db
        em.detach(updatedBlogContenido);
        updatedBlogContenido
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .correo(UPDATED_CORREO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .paisEstudio(UPDATED_PAIS_ESTUDIO)
            .calificacionPais(UPDATED_CALIFICACION_PAIS)
            .ciudadVive(UPDATED_CIUDAD_VIVE)
            .calificacionCiudad(UPDATED_CALIFICACION_CIUDAD)
            .programaRealizado(UPDATED_PROGRAMA_REALIZADO)
            .institucionEstudio(UPDATED_INSTITUCION_ESTUDIO)
            .calificacionInstitucion(UPDATED_CALIFICACION_INSTITUCION)
            .agenciaEstudios(UPDATED_AGENCIA_ESTUDIOS)
            .nombreAgencia(UPDATED_NOMBRE_AGENCIA)
            .calificacionAgencia(UPDATED_CALIFICACION_AGENCIA)
            .calificacionExperienciaEstudio(UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO)
            .fecha(UPDATED_FECHA)
            .titulo(UPDATED_TITULO)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE)
            .contenido(UPDATED_CONTENIDO);

        restBlogContenidoMockMvc.perform(put("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlogContenido)))
            .andExpect(status().isOk());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeUpdate);
        BlogContenido testBlogContenido = blogContenidoList.get(blogContenidoList.size() - 1);
        assertThat(testBlogContenido.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testBlogContenido.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testBlogContenido.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testBlogContenido.getNacionalidad()).isEqualTo(UPDATED_NACIONALIDAD);
        assertThat(testBlogContenido.getPaisEstudio()).isEqualTo(UPDATED_PAIS_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionPais()).isEqualTo(UPDATED_CALIFICACION_PAIS);
        assertThat(testBlogContenido.getCiudadVive()).isEqualTo(UPDATED_CIUDAD_VIVE);
        assertThat(testBlogContenido.getCalificacionCiudad()).isEqualTo(UPDATED_CALIFICACION_CIUDAD);
        assertThat(testBlogContenido.getProgramaRealizado()).isEqualTo(UPDATED_PROGRAMA_REALIZADO);
        assertThat(testBlogContenido.getInstitucionEstudio()).isEqualTo(UPDATED_INSTITUCION_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionInstitucion()).isEqualTo(UPDATED_CALIFICACION_INSTITUCION);
        assertThat(testBlogContenido.isAgenciaEstudios()).isEqualTo(UPDATED_AGENCIA_ESTUDIOS);
        assertThat(testBlogContenido.getNombreAgencia()).isEqualTo(UPDATED_NOMBRE_AGENCIA);
        assertThat(testBlogContenido.getCalificacionAgencia()).isEqualTo(UPDATED_CALIFICACION_AGENCIA);
        assertThat(testBlogContenido.getCalificacionExperienciaEstudio()).isEqualTo(UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);
        assertThat(testBlogContenido.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testBlogContenido.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testBlogContenido.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testBlogContenido.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
        assertThat(testBlogContenido.getContenido()).isEqualTo(UPDATED_CONTENIDO);

        // Validate the BlogContenido in Elasticsearch
        verify(mockBlogContenidoSearchRepository, times(1)).save(testBlogContenido);
    }

    @Test
    @Transactional
    public void updateNonExistingBlogContenido() throws Exception {
        int databaseSizeBeforeUpdate = blogContenidoRepository.findAll().size();

        // Create the BlogContenido

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlogContenidoMockMvc.perform(put("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogContenido)))
            .andExpect(status().isBadRequest());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the BlogContenido in Elasticsearch
        verify(mockBlogContenidoSearchRepository, times(0)).save(blogContenido);
    }

    @Test
    @Transactional
    public void deleteBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoService.save(blogContenido);

        int databaseSizeBeforeDelete = blogContenidoRepository.findAll().size();

        // Delete the blogContenido
        restBlogContenidoMockMvc.perform(delete("/api/blog-contenidos/{id}", blogContenido.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the BlogContenido in Elasticsearch
        verify(mockBlogContenidoSearchRepository, times(1)).deleteById(blogContenido.getId());
    }

    @Test
    @Transactional
    public void searchBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoService.save(blogContenido);
        when(mockBlogContenidoSearchRepository.search(queryStringQuery("id:" + blogContenido.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(blogContenido), PageRequest.of(0, 1), 1));
        // Search the blogContenido
        restBlogContenidoMockMvc.perform(get("/api/_search/blog-contenidos?query=id:" + blogContenido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blogContenido.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].paisEstudio").value(hasItem(DEFAULT_PAIS_ESTUDIO.toString())))
            .andExpect(jsonPath("$.[*].calificacionPais").value(hasItem(DEFAULT_CALIFICACION_PAIS)))
            .andExpect(jsonPath("$.[*].ciudadVive").value(hasItem(DEFAULT_CIUDAD_VIVE)))
            .andExpect(jsonPath("$.[*].calificacionCiudad").value(hasItem(DEFAULT_CALIFICACION_CIUDAD)))
            .andExpect(jsonPath("$.[*].programaRealizado").value(hasItem(DEFAULT_PROGRAMA_REALIZADO)))
            .andExpect(jsonPath("$.[*].institucionEstudio").value(hasItem(DEFAULT_INSTITUCION_ESTUDIO)))
            .andExpect(jsonPath("$.[*].calificacionInstitucion").value(hasItem(DEFAULT_CALIFICACION_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agenciaEstudios").value(hasItem(DEFAULT_AGENCIA_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificacionAgencia").value(hasItem(DEFAULT_CALIFICACION_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificacionExperienciaEstudio").value(hasItem(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlogContenido.class);
        BlogContenido blogContenido1 = new BlogContenido();
        blogContenido1.setId(1L);
        BlogContenido blogContenido2 = new BlogContenido();
        blogContenido2.setId(blogContenido1.getId());
        assertThat(blogContenido1).isEqualTo(blogContenido2);
        blogContenido2.setId(2L);
        assertThat(blogContenido1).isNotEqualTo(blogContenido2);
        blogContenido1.setId(null);
        assertThat(blogContenido1).isNotEqualTo(blogContenido2);
    }
}
