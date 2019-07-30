package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.Institucion;
import io.github.jhipster.application.domain.Ciudad;
import io.github.jhipster.application.repository.InstitucionRepository;
import io.github.jhipster.application.repository.search.InstitucionSearchRepository;
import io.github.jhipster.application.service.InstitucionService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.InstitucionCriteria;
import io.github.jhipster.application.service.InstitucionQueryService;

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
 * Integration tests for the {@Link InstitucionResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class InstitucionResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTO = "BBBBBBBBBB";

    private static final String DEFAULT_REPRESENTANTE = "AAAAAAAAAA";
    private static final String UPDATED_REPRESENTANTE = "BBBBBBBBBB";

    private static final String DEFAULT_SKYPE = "AAAAAAAAAA";
    private static final String UPDATED_SKYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_ESTATUS = "AAAAAAAAAA";
    private static final String UPDATED_ESTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_PROGRAMAS = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_PROGRAMAS = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMA_ESTANDAR = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_ESTANDAR = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMA_INTENSIVO = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_INTENSIVO = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMA_NEGOCIOS = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_NEGOCIOS = "BBBBBBBBBB";

    private static final String DEFAULT_PREPARACION_EXAMEN = "AAAAAAAAAA";
    private static final String UPDATED_PREPARACION_EXAMEN = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMA_ACADEMICO = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_ACADEMICO = "BBBBBBBBBB";

    private static final Integer DEFAULT_DESCUENTO = 1;
    private static final Integer UPDATED_DESCUENTO = 2;

    private static final String DEFAULT_INSCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_INSCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIALES = "AAAAAAAAAA";
    private static final String UPDATED_MATERIALES = "BBBBBBBBBB";

    private static final String DEFAULT_SEGURO_MEDICO = "AAAAAAAAAA";
    private static final String UPDATED_SEGURO_MEDICO = "BBBBBBBBBB";

    private static final String DEFAULT_ALOJAMIENTO_SENCILLO = "AAAAAAAAAA";
    private static final String UPDATED_ALOJAMIENTO_SENCILLO = "BBBBBBBBBB";

    private static final String DEFAULT_ALOJAMIENTO_DOBLE = "AAAAAAAAAA";
    private static final String UPDATED_ALOJAMIENTO_DOBLE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSPORTE_AEROPUERTO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSPORTE_AEROPUERTO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_CURSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CURSO = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPORADA_ALTA = "AAAAAAAAAA";
    private static final String UPDATED_TEMPORADA_ALTA = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPORADA_BAJA = "AAAAAAAAAA";
    private static final String UPDATED_TEMPORADA_BAJA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIAL = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_HORARIOS = "AAAAAAAAAA";
    private static final String UPDATED_HORARIOS = "BBBBBBBBBB";

    private static final String DEFAULT_INSTALACIONES = "AAAAAAAAAA";
    private static final String UPDATED_INSTALACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_NACIONALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_NACIONALIDAD = "BBBBBBBBBB";

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

    private static final byte[] DEFAULT_FOTO_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_3_CONTENT_TYPE = "image/png";

    @Autowired
    private InstitucionRepository institucionRepository;

    @Autowired
    private InstitucionService institucionService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.InstitucionSearchRepositoryMockConfiguration
     */
    @Autowired
    private InstitucionSearchRepository mockInstitucionSearchRepository;

    @Autowired
    private InstitucionQueryService institucionQueryService;

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

    private MockMvc restInstitucionMockMvc;

    private Institucion institucion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstitucionResource institucionResource = new InstitucionResource(institucionService, institucionQueryService);
        this.restInstitucionMockMvc = MockMvcBuilders.standaloneSetup(institucionResource)
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
    public static Institucion createEntity(EntityManager em) {
        Institucion institucion = new Institucion()
            .codigo(DEFAULT_CODIGO)
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .direccion(DEFAULT_DIRECCION)
            .website(DEFAULT_WEBSITE)
            .contacto(DEFAULT_CONTACTO)
            .representante(DEFAULT_REPRESENTANTE)
            .skype(DEFAULT_SKYPE)
            .telefono(DEFAULT_TELEFONO)
            .estatus(DEFAULT_ESTATUS)
            .tipoProgramas(DEFAULT_TIPO_PROGRAMAS)
            .programaEstandar(DEFAULT_PROGRAMA_ESTANDAR)
            .programaIntensivo(DEFAULT_PROGRAMA_INTENSIVO)
            .programaNegocios(DEFAULT_PROGRAMA_NEGOCIOS)
            .preparacionExamen(DEFAULT_PREPARACION_EXAMEN)
            .programaAcademico(DEFAULT_PROGRAMA_ACADEMICO)
            .descuento(DEFAULT_DESCUENTO)
            .inscripcion(DEFAULT_INSCRIPCION)
            .materiales(DEFAULT_MATERIALES)
            .seguroMedico(DEFAULT_SEGURO_MEDICO)
            .alojamientoSencillo(DEFAULT_ALOJAMIENTO_SENCILLO)
            .alojamientoDoble(DEFAULT_ALOJAMIENTO_DOBLE)
            .transporteAeropuerto(DEFAULT_TRANSPORTE_AEROPUERTO)
            .tipoCurso(DEFAULT_TIPO_CURSO)
            .temporadaAlta(DEFAULT_TEMPORADA_ALTA)
            .temporadaBaja(DEFAULT_TEMPORADA_BAJA)
            .fechaInicial(DEFAULT_FECHA_INICIAL)
            .horarios(DEFAULT_HORARIOS)
            .instalaciones(DEFAULT_INSTALACIONES)
            .nacionalidad(DEFAULT_NACIONALIDAD)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE)
            .foto3(DEFAULT_FOTO_3)
            .foto3ContentType(DEFAULT_FOTO_3_CONTENT_TYPE);
        return institucion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Institucion createUpdatedEntity(EntityManager em) {
        Institucion institucion = new Institucion()
            .codigo(UPDATED_CODIGO)
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .direccion(UPDATED_DIRECCION)
            .website(UPDATED_WEBSITE)
            .contacto(UPDATED_CONTACTO)
            .representante(UPDATED_REPRESENTANTE)
            .skype(UPDATED_SKYPE)
            .telefono(UPDATED_TELEFONO)
            .estatus(UPDATED_ESTATUS)
            .tipoProgramas(UPDATED_TIPO_PROGRAMAS)
            .programaEstandar(UPDATED_PROGRAMA_ESTANDAR)
            .programaIntensivo(UPDATED_PROGRAMA_INTENSIVO)
            .programaNegocios(UPDATED_PROGRAMA_NEGOCIOS)
            .preparacionExamen(UPDATED_PREPARACION_EXAMEN)
            .programaAcademico(UPDATED_PROGRAMA_ACADEMICO)
            .descuento(UPDATED_DESCUENTO)
            .inscripcion(UPDATED_INSCRIPCION)
            .materiales(UPDATED_MATERIALES)
            .seguroMedico(UPDATED_SEGURO_MEDICO)
            .alojamientoSencillo(UPDATED_ALOJAMIENTO_SENCILLO)
            .alojamientoDoble(UPDATED_ALOJAMIENTO_DOBLE)
            .transporteAeropuerto(UPDATED_TRANSPORTE_AEROPUERTO)
            .tipoCurso(UPDATED_TIPO_CURSO)
            .temporadaAlta(UPDATED_TEMPORADA_ALTA)
            .temporadaBaja(UPDATED_TEMPORADA_BAJA)
            .fechaInicial(UPDATED_FECHA_INICIAL)
            .horarios(UPDATED_HORARIOS)
            .instalaciones(UPDATED_INSTALACIONES)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .foto3(UPDATED_FOTO_3)
            .foto3ContentType(UPDATED_FOTO_3_CONTENT_TYPE);
        return institucion;
    }

    @BeforeEach
    public void initTest() {
        institucion = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstitucion() throws Exception {
        int databaseSizeBeforeCreate = institucionRepository.findAll().size();

        // Create the Institucion
        restInstitucionMockMvc.perform(post("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(institucion)))
            .andExpect(status().isCreated());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeCreate + 1);
        Institucion testInstitucion = institucionList.get(institucionList.size() - 1);
        assertThat(testInstitucion.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testInstitucion.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testInstitucion.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testInstitucion.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testInstitucion.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testInstitucion.getContacto()).isEqualTo(DEFAULT_CONTACTO);
        assertThat(testInstitucion.getRepresentante()).isEqualTo(DEFAULT_REPRESENTANTE);
        assertThat(testInstitucion.getSkype()).isEqualTo(DEFAULT_SKYPE);
        assertThat(testInstitucion.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testInstitucion.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testInstitucion.getTipoProgramas()).isEqualTo(DEFAULT_TIPO_PROGRAMAS);
        assertThat(testInstitucion.getProgramaEstandar()).isEqualTo(DEFAULT_PROGRAMA_ESTANDAR);
        assertThat(testInstitucion.getProgramaIntensivo()).isEqualTo(DEFAULT_PROGRAMA_INTENSIVO);
        assertThat(testInstitucion.getProgramaNegocios()).isEqualTo(DEFAULT_PROGRAMA_NEGOCIOS);
        assertThat(testInstitucion.getPreparacionExamen()).isEqualTo(DEFAULT_PREPARACION_EXAMEN);
        assertThat(testInstitucion.getProgramaAcademico()).isEqualTo(DEFAULT_PROGRAMA_ACADEMICO);
        assertThat(testInstitucion.getDescuento()).isEqualTo(DEFAULT_DESCUENTO);
        assertThat(testInstitucion.getInscripcion()).isEqualTo(DEFAULT_INSCRIPCION);
        assertThat(testInstitucion.getMateriales()).isEqualTo(DEFAULT_MATERIALES);
        assertThat(testInstitucion.getSeguroMedico()).isEqualTo(DEFAULT_SEGURO_MEDICO);
        assertThat(testInstitucion.getAlojamientoSencillo()).isEqualTo(DEFAULT_ALOJAMIENTO_SENCILLO);
        assertThat(testInstitucion.getAlojamientoDoble()).isEqualTo(DEFAULT_ALOJAMIENTO_DOBLE);
        assertThat(testInstitucion.getTransporteAeropuerto()).isEqualTo(DEFAULT_TRANSPORTE_AEROPUERTO);
        assertThat(testInstitucion.getTipoCurso()).isEqualTo(DEFAULT_TIPO_CURSO);
        assertThat(testInstitucion.getTemporadaAlta()).isEqualTo(DEFAULT_TEMPORADA_ALTA);
        assertThat(testInstitucion.getTemporadaBaja()).isEqualTo(DEFAULT_TEMPORADA_BAJA);
        assertThat(testInstitucion.getFechaInicial()).isEqualTo(DEFAULT_FECHA_INICIAL);
        assertThat(testInstitucion.getHorarios()).isEqualTo(DEFAULT_HORARIOS);
        assertThat(testInstitucion.getInstalaciones()).isEqualTo(DEFAULT_INSTALACIONES);
        assertThat(testInstitucion.getNacionalidad()).isEqualTo(DEFAULT_NACIONALIDAD);
        assertThat(testInstitucion.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testInstitucion.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
        assertThat(testInstitucion.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testInstitucion.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testInstitucion.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testInstitucion.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);
        assertThat(testInstitucion.getFoto3()).isEqualTo(DEFAULT_FOTO_3);
        assertThat(testInstitucion.getFoto3ContentType()).isEqualTo(DEFAULT_FOTO_3_CONTENT_TYPE);

        // Validate the Institucion in Elasticsearch
        verify(mockInstitucionSearchRepository, times(1)).save(testInstitucion);
    }

    @Test
    @Transactional
    public void createInstitucionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = institucionRepository.findAll().size();

        // Create the Institucion with an existing ID
        institucion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstitucionMockMvc.perform(post("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(institucion)))
            .andExpect(status().isBadRequest());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeCreate);

        // Validate the Institucion in Elasticsearch
        verify(mockInstitucionSearchRepository, times(0)).save(institucion);
    }


    @Test
    @Transactional
    public void getAllInstitucions() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList
        restInstitucionMockMvc.perform(get("/api/institucions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(institucion.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].contacto").value(hasItem(DEFAULT_CONTACTO.toString())))
            .andExpect(jsonPath("$.[*].representante").value(hasItem(DEFAULT_REPRESENTANTE.toString())))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.toString())))
            .andExpect(jsonPath("$.[*].tipoProgramas").value(hasItem(DEFAULT_TIPO_PROGRAMAS.toString())))
            .andExpect(jsonPath("$.[*].programaEstandar").value(hasItem(DEFAULT_PROGRAMA_ESTANDAR.toString())))
            .andExpect(jsonPath("$.[*].programaIntensivo").value(hasItem(DEFAULT_PROGRAMA_INTENSIVO.toString())))
            .andExpect(jsonPath("$.[*].programaNegocios").value(hasItem(DEFAULT_PROGRAMA_NEGOCIOS.toString())))
            .andExpect(jsonPath("$.[*].preparacionExamen").value(hasItem(DEFAULT_PREPARACION_EXAMEN.toString())))
            .andExpect(jsonPath("$.[*].programaAcademico").value(hasItem(DEFAULT_PROGRAMA_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].descuento").value(hasItem(DEFAULT_DESCUENTO)))
            .andExpect(jsonPath("$.[*].inscripcion").value(hasItem(DEFAULT_INSCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].materiales").value(hasItem(DEFAULT_MATERIALES.toString())))
            .andExpect(jsonPath("$.[*].seguroMedico").value(hasItem(DEFAULT_SEGURO_MEDICO.toString())))
            .andExpect(jsonPath("$.[*].alojamientoSencillo").value(hasItem(DEFAULT_ALOJAMIENTO_SENCILLO.toString())))
            .andExpect(jsonPath("$.[*].alojamientoDoble").value(hasItem(DEFAULT_ALOJAMIENTO_DOBLE.toString())))
            .andExpect(jsonPath("$.[*].transporteAeropuerto").value(hasItem(DEFAULT_TRANSPORTE_AEROPUERTO.toString())))
            .andExpect(jsonPath("$.[*].tipoCurso").value(hasItem(DEFAULT_TIPO_CURSO.toString())))
            .andExpect(jsonPath("$.[*].temporadaAlta").value(hasItem(DEFAULT_TEMPORADA_ALTA.toString())))
            .andExpect(jsonPath("$.[*].temporadaBaja").value(hasItem(DEFAULT_TEMPORADA_BAJA.toString())))
            .andExpect(jsonPath("$.[*].fechaInicial").value(hasItem(DEFAULT_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].horarios").value(hasItem(DEFAULT_HORARIOS.toString())))
            .andExpect(jsonPath("$.[*].instalaciones").value(hasItem(DEFAULT_INSTALACIONES.toString())))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].foto3ContentType").value(hasItem(DEFAULT_FOTO_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_3))));
    }
    
    @Test
    @Transactional
    public void getInstitucion() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get the institucion
        restInstitucionMockMvc.perform(get("/api/institucions/{id}", institucion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(institucion.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.contacto").value(DEFAULT_CONTACTO.toString()))
            .andExpect(jsonPath("$.representante").value(DEFAULT_REPRESENTANTE.toString()))
            .andExpect(jsonPath("$.skype").value(DEFAULT_SKYPE.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.toString()))
            .andExpect(jsonPath("$.tipoProgramas").value(DEFAULT_TIPO_PROGRAMAS.toString()))
            .andExpect(jsonPath("$.programaEstandar").value(DEFAULT_PROGRAMA_ESTANDAR.toString()))
            .andExpect(jsonPath("$.programaIntensivo").value(DEFAULT_PROGRAMA_INTENSIVO.toString()))
            .andExpect(jsonPath("$.programaNegocios").value(DEFAULT_PROGRAMA_NEGOCIOS.toString()))
            .andExpect(jsonPath("$.preparacionExamen").value(DEFAULT_PREPARACION_EXAMEN.toString()))
            .andExpect(jsonPath("$.programaAcademico").value(DEFAULT_PROGRAMA_ACADEMICO.toString()))
            .andExpect(jsonPath("$.descuento").value(DEFAULT_DESCUENTO))
            .andExpect(jsonPath("$.inscripcion").value(DEFAULT_INSCRIPCION.toString()))
            .andExpect(jsonPath("$.materiales").value(DEFAULT_MATERIALES.toString()))
            .andExpect(jsonPath("$.seguroMedico").value(DEFAULT_SEGURO_MEDICO.toString()))
            .andExpect(jsonPath("$.alojamientoSencillo").value(DEFAULT_ALOJAMIENTO_SENCILLO.toString()))
            .andExpect(jsonPath("$.alojamientoDoble").value(DEFAULT_ALOJAMIENTO_DOBLE.toString()))
            .andExpect(jsonPath("$.transporteAeropuerto").value(DEFAULT_TRANSPORTE_AEROPUERTO.toString()))
            .andExpect(jsonPath("$.tipoCurso").value(DEFAULT_TIPO_CURSO.toString()))
            .andExpect(jsonPath("$.temporadaAlta").value(DEFAULT_TEMPORADA_ALTA.toString()))
            .andExpect(jsonPath("$.temporadaBaja").value(DEFAULT_TEMPORADA_BAJA.toString()))
            .andExpect(jsonPath("$.fechaInicial").value(DEFAULT_FECHA_INICIAL.toString()))
            .andExpect(jsonPath("$.horarios").value(DEFAULT_HORARIOS.toString()))
            .andExpect(jsonPath("$.instalaciones").value(DEFAULT_INSTALACIONES.toString()))
            .andExpect(jsonPath("$.nacionalidad").value(DEFAULT_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)))
            .andExpect(jsonPath("$.foto3ContentType").value(DEFAULT_FOTO_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto3").value(Base64Utils.encodeToString(DEFAULT_FOTO_3)));
    }

    @Test
    @Transactional
    public void getAllInstitucionsByCodigoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where codigo equals to DEFAULT_CODIGO
        defaultInstitucionShouldBeFound("codigo.equals=" + DEFAULT_CODIGO);

        // Get all the institucionList where codigo equals to UPDATED_CODIGO
        defaultInstitucionShouldNotBeFound("codigo.equals=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByCodigoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where codigo in DEFAULT_CODIGO or UPDATED_CODIGO
        defaultInstitucionShouldBeFound("codigo.in=" + DEFAULT_CODIGO + "," + UPDATED_CODIGO);

        // Get all the institucionList where codigo equals to UPDATED_CODIGO
        defaultInstitucionShouldNotBeFound("codigo.in=" + UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByCodigoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where codigo is not null
        defaultInstitucionShouldBeFound("codigo.specified=true");

        // Get all the institucionList where codigo is null
        defaultInstitucionShouldNotBeFound("codigo.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where nombre equals to DEFAULT_NOMBRE
        defaultInstitucionShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the institucionList where nombre equals to UPDATED_NOMBRE
        defaultInstitucionShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultInstitucionShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the institucionList where nombre equals to UPDATED_NOMBRE
        defaultInstitucionShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where nombre is not null
        defaultInstitucionShouldBeFound("nombre.specified=true");

        // Get all the institucionList where nombre is null
        defaultInstitucionShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where direccion equals to DEFAULT_DIRECCION
        defaultInstitucionShouldBeFound("direccion.equals=" + DEFAULT_DIRECCION);

        // Get all the institucionList where direccion equals to UPDATED_DIRECCION
        defaultInstitucionShouldNotBeFound("direccion.equals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDireccionIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where direccion in DEFAULT_DIRECCION or UPDATED_DIRECCION
        defaultInstitucionShouldBeFound("direccion.in=" + DEFAULT_DIRECCION + "," + UPDATED_DIRECCION);

        // Get all the institucionList where direccion equals to UPDATED_DIRECCION
        defaultInstitucionShouldNotBeFound("direccion.in=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDireccionIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where direccion is not null
        defaultInstitucionShouldBeFound("direccion.specified=true");

        // Get all the institucionList where direccion is null
        defaultInstitucionShouldNotBeFound("direccion.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByWebsiteIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where website equals to DEFAULT_WEBSITE
        defaultInstitucionShouldBeFound("website.equals=" + DEFAULT_WEBSITE);

        // Get all the institucionList where website equals to UPDATED_WEBSITE
        defaultInstitucionShouldNotBeFound("website.equals=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByWebsiteIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where website in DEFAULT_WEBSITE or UPDATED_WEBSITE
        defaultInstitucionShouldBeFound("website.in=" + DEFAULT_WEBSITE + "," + UPDATED_WEBSITE);

        // Get all the institucionList where website equals to UPDATED_WEBSITE
        defaultInstitucionShouldNotBeFound("website.in=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByWebsiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where website is not null
        defaultInstitucionShouldBeFound("website.specified=true");

        // Get all the institucionList where website is null
        defaultInstitucionShouldNotBeFound("website.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByContactoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where contacto equals to DEFAULT_CONTACTO
        defaultInstitucionShouldBeFound("contacto.equals=" + DEFAULT_CONTACTO);

        // Get all the institucionList where contacto equals to UPDATED_CONTACTO
        defaultInstitucionShouldNotBeFound("contacto.equals=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByContactoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where contacto in DEFAULT_CONTACTO or UPDATED_CONTACTO
        defaultInstitucionShouldBeFound("contacto.in=" + DEFAULT_CONTACTO + "," + UPDATED_CONTACTO);

        // Get all the institucionList where contacto equals to UPDATED_CONTACTO
        defaultInstitucionShouldNotBeFound("contacto.in=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByContactoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where contacto is not null
        defaultInstitucionShouldBeFound("contacto.specified=true");

        // Get all the institucionList where contacto is null
        defaultInstitucionShouldNotBeFound("contacto.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByRepresentanteIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where representante equals to DEFAULT_REPRESENTANTE
        defaultInstitucionShouldBeFound("representante.equals=" + DEFAULT_REPRESENTANTE);

        // Get all the institucionList where representante equals to UPDATED_REPRESENTANTE
        defaultInstitucionShouldNotBeFound("representante.equals=" + UPDATED_REPRESENTANTE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByRepresentanteIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where representante in DEFAULT_REPRESENTANTE or UPDATED_REPRESENTANTE
        defaultInstitucionShouldBeFound("representante.in=" + DEFAULT_REPRESENTANTE + "," + UPDATED_REPRESENTANTE);

        // Get all the institucionList where representante equals to UPDATED_REPRESENTANTE
        defaultInstitucionShouldNotBeFound("representante.in=" + UPDATED_REPRESENTANTE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByRepresentanteIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where representante is not null
        defaultInstitucionShouldBeFound("representante.specified=true");

        // Get all the institucionList where representante is null
        defaultInstitucionShouldNotBeFound("representante.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsBySkypeIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where skype equals to DEFAULT_SKYPE
        defaultInstitucionShouldBeFound("skype.equals=" + DEFAULT_SKYPE);

        // Get all the institucionList where skype equals to UPDATED_SKYPE
        defaultInstitucionShouldNotBeFound("skype.equals=" + UPDATED_SKYPE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsBySkypeIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where skype in DEFAULT_SKYPE or UPDATED_SKYPE
        defaultInstitucionShouldBeFound("skype.in=" + DEFAULT_SKYPE + "," + UPDATED_SKYPE);

        // Get all the institucionList where skype equals to UPDATED_SKYPE
        defaultInstitucionShouldNotBeFound("skype.in=" + UPDATED_SKYPE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsBySkypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where skype is not null
        defaultInstitucionShouldBeFound("skype.specified=true");

        // Get all the institucionList where skype is null
        defaultInstitucionShouldNotBeFound("skype.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where telefono equals to DEFAULT_TELEFONO
        defaultInstitucionShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the institucionList where telefono equals to UPDATED_TELEFONO
        defaultInstitucionShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultInstitucionShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the institucionList where telefono equals to UPDATED_TELEFONO
        defaultInstitucionShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where telefono is not null
        defaultInstitucionShouldBeFound("telefono.specified=true");

        // Get all the institucionList where telefono is null
        defaultInstitucionShouldNotBeFound("telefono.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where estatus equals to DEFAULT_ESTATUS
        defaultInstitucionShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the institucionList where estatus equals to UPDATED_ESTATUS
        defaultInstitucionShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultInstitucionShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the institucionList where estatus equals to UPDATED_ESTATUS
        defaultInstitucionShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where estatus is not null
        defaultInstitucionShouldBeFound("estatus.specified=true");

        // Get all the institucionList where estatus is null
        defaultInstitucionShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTipoProgramasIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where tipoProgramas equals to DEFAULT_TIPO_PROGRAMAS
        defaultInstitucionShouldBeFound("tipoProgramas.equals=" + DEFAULT_TIPO_PROGRAMAS);

        // Get all the institucionList where tipoProgramas equals to UPDATED_TIPO_PROGRAMAS
        defaultInstitucionShouldNotBeFound("tipoProgramas.equals=" + UPDATED_TIPO_PROGRAMAS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTipoProgramasIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where tipoProgramas in DEFAULT_TIPO_PROGRAMAS or UPDATED_TIPO_PROGRAMAS
        defaultInstitucionShouldBeFound("tipoProgramas.in=" + DEFAULT_TIPO_PROGRAMAS + "," + UPDATED_TIPO_PROGRAMAS);

        // Get all the institucionList where tipoProgramas equals to UPDATED_TIPO_PROGRAMAS
        defaultInstitucionShouldNotBeFound("tipoProgramas.in=" + UPDATED_TIPO_PROGRAMAS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTipoProgramasIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where tipoProgramas is not null
        defaultInstitucionShouldBeFound("tipoProgramas.specified=true");

        // Get all the institucionList where tipoProgramas is null
        defaultInstitucionShouldNotBeFound("tipoProgramas.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaEstandarIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaEstandar equals to DEFAULT_PROGRAMA_ESTANDAR
        defaultInstitucionShouldBeFound("programaEstandar.equals=" + DEFAULT_PROGRAMA_ESTANDAR);

        // Get all the institucionList where programaEstandar equals to UPDATED_PROGRAMA_ESTANDAR
        defaultInstitucionShouldNotBeFound("programaEstandar.equals=" + UPDATED_PROGRAMA_ESTANDAR);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaEstandarIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaEstandar in DEFAULT_PROGRAMA_ESTANDAR or UPDATED_PROGRAMA_ESTANDAR
        defaultInstitucionShouldBeFound("programaEstandar.in=" + DEFAULT_PROGRAMA_ESTANDAR + "," + UPDATED_PROGRAMA_ESTANDAR);

        // Get all the institucionList where programaEstandar equals to UPDATED_PROGRAMA_ESTANDAR
        defaultInstitucionShouldNotBeFound("programaEstandar.in=" + UPDATED_PROGRAMA_ESTANDAR);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaEstandarIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaEstandar is not null
        defaultInstitucionShouldBeFound("programaEstandar.specified=true");

        // Get all the institucionList where programaEstandar is null
        defaultInstitucionShouldNotBeFound("programaEstandar.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaIntensivoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaIntensivo equals to DEFAULT_PROGRAMA_INTENSIVO
        defaultInstitucionShouldBeFound("programaIntensivo.equals=" + DEFAULT_PROGRAMA_INTENSIVO);

        // Get all the institucionList where programaIntensivo equals to UPDATED_PROGRAMA_INTENSIVO
        defaultInstitucionShouldNotBeFound("programaIntensivo.equals=" + UPDATED_PROGRAMA_INTENSIVO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaIntensivoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaIntensivo in DEFAULT_PROGRAMA_INTENSIVO or UPDATED_PROGRAMA_INTENSIVO
        defaultInstitucionShouldBeFound("programaIntensivo.in=" + DEFAULT_PROGRAMA_INTENSIVO + "," + UPDATED_PROGRAMA_INTENSIVO);

        // Get all the institucionList where programaIntensivo equals to UPDATED_PROGRAMA_INTENSIVO
        defaultInstitucionShouldNotBeFound("programaIntensivo.in=" + UPDATED_PROGRAMA_INTENSIVO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaIntensivoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaIntensivo is not null
        defaultInstitucionShouldBeFound("programaIntensivo.specified=true");

        // Get all the institucionList where programaIntensivo is null
        defaultInstitucionShouldNotBeFound("programaIntensivo.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaNegociosIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaNegocios equals to DEFAULT_PROGRAMA_NEGOCIOS
        defaultInstitucionShouldBeFound("programaNegocios.equals=" + DEFAULT_PROGRAMA_NEGOCIOS);

        // Get all the institucionList where programaNegocios equals to UPDATED_PROGRAMA_NEGOCIOS
        defaultInstitucionShouldNotBeFound("programaNegocios.equals=" + UPDATED_PROGRAMA_NEGOCIOS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaNegociosIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaNegocios in DEFAULT_PROGRAMA_NEGOCIOS or UPDATED_PROGRAMA_NEGOCIOS
        defaultInstitucionShouldBeFound("programaNegocios.in=" + DEFAULT_PROGRAMA_NEGOCIOS + "," + UPDATED_PROGRAMA_NEGOCIOS);

        // Get all the institucionList where programaNegocios equals to UPDATED_PROGRAMA_NEGOCIOS
        defaultInstitucionShouldNotBeFound("programaNegocios.in=" + UPDATED_PROGRAMA_NEGOCIOS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaNegociosIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaNegocios is not null
        defaultInstitucionShouldBeFound("programaNegocios.specified=true");

        // Get all the institucionList where programaNegocios is null
        defaultInstitucionShouldNotBeFound("programaNegocios.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByPreparacionExamenIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where preparacionExamen equals to DEFAULT_PREPARACION_EXAMEN
        defaultInstitucionShouldBeFound("preparacionExamen.equals=" + DEFAULT_PREPARACION_EXAMEN);

        // Get all the institucionList where preparacionExamen equals to UPDATED_PREPARACION_EXAMEN
        defaultInstitucionShouldNotBeFound("preparacionExamen.equals=" + UPDATED_PREPARACION_EXAMEN);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByPreparacionExamenIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where preparacionExamen in DEFAULT_PREPARACION_EXAMEN or UPDATED_PREPARACION_EXAMEN
        defaultInstitucionShouldBeFound("preparacionExamen.in=" + DEFAULT_PREPARACION_EXAMEN + "," + UPDATED_PREPARACION_EXAMEN);

        // Get all the institucionList where preparacionExamen equals to UPDATED_PREPARACION_EXAMEN
        defaultInstitucionShouldNotBeFound("preparacionExamen.in=" + UPDATED_PREPARACION_EXAMEN);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByPreparacionExamenIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where preparacionExamen is not null
        defaultInstitucionShouldBeFound("preparacionExamen.specified=true");

        // Get all the institucionList where preparacionExamen is null
        defaultInstitucionShouldNotBeFound("preparacionExamen.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaAcademicoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaAcademico equals to DEFAULT_PROGRAMA_ACADEMICO
        defaultInstitucionShouldBeFound("programaAcademico.equals=" + DEFAULT_PROGRAMA_ACADEMICO);

        // Get all the institucionList where programaAcademico equals to UPDATED_PROGRAMA_ACADEMICO
        defaultInstitucionShouldNotBeFound("programaAcademico.equals=" + UPDATED_PROGRAMA_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaAcademicoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaAcademico in DEFAULT_PROGRAMA_ACADEMICO or UPDATED_PROGRAMA_ACADEMICO
        defaultInstitucionShouldBeFound("programaAcademico.in=" + DEFAULT_PROGRAMA_ACADEMICO + "," + UPDATED_PROGRAMA_ACADEMICO);

        // Get all the institucionList where programaAcademico equals to UPDATED_PROGRAMA_ACADEMICO
        defaultInstitucionShouldNotBeFound("programaAcademico.in=" + UPDATED_PROGRAMA_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByProgramaAcademicoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where programaAcademico is not null
        defaultInstitucionShouldBeFound("programaAcademico.specified=true");

        // Get all the institucionList where programaAcademico is null
        defaultInstitucionShouldNotBeFound("programaAcademico.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDescuentoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where descuento equals to DEFAULT_DESCUENTO
        defaultInstitucionShouldBeFound("descuento.equals=" + DEFAULT_DESCUENTO);

        // Get all the institucionList where descuento equals to UPDATED_DESCUENTO
        defaultInstitucionShouldNotBeFound("descuento.equals=" + UPDATED_DESCUENTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDescuentoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where descuento in DEFAULT_DESCUENTO or UPDATED_DESCUENTO
        defaultInstitucionShouldBeFound("descuento.in=" + DEFAULT_DESCUENTO + "," + UPDATED_DESCUENTO);

        // Get all the institucionList where descuento equals to UPDATED_DESCUENTO
        defaultInstitucionShouldNotBeFound("descuento.in=" + UPDATED_DESCUENTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDescuentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where descuento is not null
        defaultInstitucionShouldBeFound("descuento.specified=true");

        // Get all the institucionList where descuento is null
        defaultInstitucionShouldNotBeFound("descuento.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDescuentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where descuento greater than or equals to DEFAULT_DESCUENTO
        defaultInstitucionShouldBeFound("descuento.greaterOrEqualThan=" + DEFAULT_DESCUENTO);

        // Get all the institucionList where descuento greater than or equals to UPDATED_DESCUENTO
        defaultInstitucionShouldNotBeFound("descuento.greaterOrEqualThan=" + UPDATED_DESCUENTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByDescuentoIsLessThanSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where descuento less than or equals to DEFAULT_DESCUENTO
        defaultInstitucionShouldNotBeFound("descuento.lessThan=" + DEFAULT_DESCUENTO);

        // Get all the institucionList where descuento less than or equals to UPDATED_DESCUENTO
        defaultInstitucionShouldBeFound("descuento.lessThan=" + UPDATED_DESCUENTO);
    }


    @Test
    @Transactional
    public void getAllInstitucionsByInscripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where inscripcion equals to DEFAULT_INSCRIPCION
        defaultInstitucionShouldBeFound("inscripcion.equals=" + DEFAULT_INSCRIPCION);

        // Get all the institucionList where inscripcion equals to UPDATED_INSCRIPCION
        defaultInstitucionShouldNotBeFound("inscripcion.equals=" + UPDATED_INSCRIPCION);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByInscripcionIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where inscripcion in DEFAULT_INSCRIPCION or UPDATED_INSCRIPCION
        defaultInstitucionShouldBeFound("inscripcion.in=" + DEFAULT_INSCRIPCION + "," + UPDATED_INSCRIPCION);

        // Get all the institucionList where inscripcion equals to UPDATED_INSCRIPCION
        defaultInstitucionShouldNotBeFound("inscripcion.in=" + UPDATED_INSCRIPCION);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByInscripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where inscripcion is not null
        defaultInstitucionShouldBeFound("inscripcion.specified=true");

        // Get all the institucionList where inscripcion is null
        defaultInstitucionShouldNotBeFound("inscripcion.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByMaterialesIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where materiales equals to DEFAULT_MATERIALES
        defaultInstitucionShouldBeFound("materiales.equals=" + DEFAULT_MATERIALES);

        // Get all the institucionList where materiales equals to UPDATED_MATERIALES
        defaultInstitucionShouldNotBeFound("materiales.equals=" + UPDATED_MATERIALES);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByMaterialesIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where materiales in DEFAULT_MATERIALES or UPDATED_MATERIALES
        defaultInstitucionShouldBeFound("materiales.in=" + DEFAULT_MATERIALES + "," + UPDATED_MATERIALES);

        // Get all the institucionList where materiales equals to UPDATED_MATERIALES
        defaultInstitucionShouldNotBeFound("materiales.in=" + UPDATED_MATERIALES);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByMaterialesIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where materiales is not null
        defaultInstitucionShouldBeFound("materiales.specified=true");

        // Get all the institucionList where materiales is null
        defaultInstitucionShouldNotBeFound("materiales.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsBySeguroMedicoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where seguroMedico equals to DEFAULT_SEGURO_MEDICO
        defaultInstitucionShouldBeFound("seguroMedico.equals=" + DEFAULT_SEGURO_MEDICO);

        // Get all the institucionList where seguroMedico equals to UPDATED_SEGURO_MEDICO
        defaultInstitucionShouldNotBeFound("seguroMedico.equals=" + UPDATED_SEGURO_MEDICO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsBySeguroMedicoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where seguroMedico in DEFAULT_SEGURO_MEDICO or UPDATED_SEGURO_MEDICO
        defaultInstitucionShouldBeFound("seguroMedico.in=" + DEFAULT_SEGURO_MEDICO + "," + UPDATED_SEGURO_MEDICO);

        // Get all the institucionList where seguroMedico equals to UPDATED_SEGURO_MEDICO
        defaultInstitucionShouldNotBeFound("seguroMedico.in=" + UPDATED_SEGURO_MEDICO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsBySeguroMedicoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where seguroMedico is not null
        defaultInstitucionShouldBeFound("seguroMedico.specified=true");

        // Get all the institucionList where seguroMedico is null
        defaultInstitucionShouldNotBeFound("seguroMedico.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByAlojamientoSencilloIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where alojamientoSencillo equals to DEFAULT_ALOJAMIENTO_SENCILLO
        defaultInstitucionShouldBeFound("alojamientoSencillo.equals=" + DEFAULT_ALOJAMIENTO_SENCILLO);

        // Get all the institucionList where alojamientoSencillo equals to UPDATED_ALOJAMIENTO_SENCILLO
        defaultInstitucionShouldNotBeFound("alojamientoSencillo.equals=" + UPDATED_ALOJAMIENTO_SENCILLO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByAlojamientoSencilloIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where alojamientoSencillo in DEFAULT_ALOJAMIENTO_SENCILLO or UPDATED_ALOJAMIENTO_SENCILLO
        defaultInstitucionShouldBeFound("alojamientoSencillo.in=" + DEFAULT_ALOJAMIENTO_SENCILLO + "," + UPDATED_ALOJAMIENTO_SENCILLO);

        // Get all the institucionList where alojamientoSencillo equals to UPDATED_ALOJAMIENTO_SENCILLO
        defaultInstitucionShouldNotBeFound("alojamientoSencillo.in=" + UPDATED_ALOJAMIENTO_SENCILLO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByAlojamientoSencilloIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where alojamientoSencillo is not null
        defaultInstitucionShouldBeFound("alojamientoSencillo.specified=true");

        // Get all the institucionList where alojamientoSencillo is null
        defaultInstitucionShouldNotBeFound("alojamientoSencillo.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByAlojamientoDobleIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where alojamientoDoble equals to DEFAULT_ALOJAMIENTO_DOBLE
        defaultInstitucionShouldBeFound("alojamientoDoble.equals=" + DEFAULT_ALOJAMIENTO_DOBLE);

        // Get all the institucionList where alojamientoDoble equals to UPDATED_ALOJAMIENTO_DOBLE
        defaultInstitucionShouldNotBeFound("alojamientoDoble.equals=" + UPDATED_ALOJAMIENTO_DOBLE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByAlojamientoDobleIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where alojamientoDoble in DEFAULT_ALOJAMIENTO_DOBLE or UPDATED_ALOJAMIENTO_DOBLE
        defaultInstitucionShouldBeFound("alojamientoDoble.in=" + DEFAULT_ALOJAMIENTO_DOBLE + "," + UPDATED_ALOJAMIENTO_DOBLE);

        // Get all the institucionList where alojamientoDoble equals to UPDATED_ALOJAMIENTO_DOBLE
        defaultInstitucionShouldNotBeFound("alojamientoDoble.in=" + UPDATED_ALOJAMIENTO_DOBLE);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByAlojamientoDobleIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where alojamientoDoble is not null
        defaultInstitucionShouldBeFound("alojamientoDoble.specified=true");

        // Get all the institucionList where alojamientoDoble is null
        defaultInstitucionShouldNotBeFound("alojamientoDoble.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTransporteAeropuertoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where transporteAeropuerto equals to DEFAULT_TRANSPORTE_AEROPUERTO
        defaultInstitucionShouldBeFound("transporteAeropuerto.equals=" + DEFAULT_TRANSPORTE_AEROPUERTO);

        // Get all the institucionList where transporteAeropuerto equals to UPDATED_TRANSPORTE_AEROPUERTO
        defaultInstitucionShouldNotBeFound("transporteAeropuerto.equals=" + UPDATED_TRANSPORTE_AEROPUERTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTransporteAeropuertoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where transporteAeropuerto in DEFAULT_TRANSPORTE_AEROPUERTO or UPDATED_TRANSPORTE_AEROPUERTO
        defaultInstitucionShouldBeFound("transporteAeropuerto.in=" + DEFAULT_TRANSPORTE_AEROPUERTO + "," + UPDATED_TRANSPORTE_AEROPUERTO);

        // Get all the institucionList where transporteAeropuerto equals to UPDATED_TRANSPORTE_AEROPUERTO
        defaultInstitucionShouldNotBeFound("transporteAeropuerto.in=" + UPDATED_TRANSPORTE_AEROPUERTO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTransporteAeropuertoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where transporteAeropuerto is not null
        defaultInstitucionShouldBeFound("transporteAeropuerto.specified=true");

        // Get all the institucionList where transporteAeropuerto is null
        defaultInstitucionShouldNotBeFound("transporteAeropuerto.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTipoCursoIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where tipoCurso equals to DEFAULT_TIPO_CURSO
        defaultInstitucionShouldBeFound("tipoCurso.equals=" + DEFAULT_TIPO_CURSO);

        // Get all the institucionList where tipoCurso equals to UPDATED_TIPO_CURSO
        defaultInstitucionShouldNotBeFound("tipoCurso.equals=" + UPDATED_TIPO_CURSO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTipoCursoIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where tipoCurso in DEFAULT_TIPO_CURSO or UPDATED_TIPO_CURSO
        defaultInstitucionShouldBeFound("tipoCurso.in=" + DEFAULT_TIPO_CURSO + "," + UPDATED_TIPO_CURSO);

        // Get all the institucionList where tipoCurso equals to UPDATED_TIPO_CURSO
        defaultInstitucionShouldNotBeFound("tipoCurso.in=" + UPDATED_TIPO_CURSO);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTipoCursoIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where tipoCurso is not null
        defaultInstitucionShouldBeFound("tipoCurso.specified=true");

        // Get all the institucionList where tipoCurso is null
        defaultInstitucionShouldNotBeFound("tipoCurso.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTemporadaAltaIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where temporadaAlta equals to DEFAULT_TEMPORADA_ALTA
        defaultInstitucionShouldBeFound("temporadaAlta.equals=" + DEFAULT_TEMPORADA_ALTA);

        // Get all the institucionList where temporadaAlta equals to UPDATED_TEMPORADA_ALTA
        defaultInstitucionShouldNotBeFound("temporadaAlta.equals=" + UPDATED_TEMPORADA_ALTA);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTemporadaAltaIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where temporadaAlta in DEFAULT_TEMPORADA_ALTA or UPDATED_TEMPORADA_ALTA
        defaultInstitucionShouldBeFound("temporadaAlta.in=" + DEFAULT_TEMPORADA_ALTA + "," + UPDATED_TEMPORADA_ALTA);

        // Get all the institucionList where temporadaAlta equals to UPDATED_TEMPORADA_ALTA
        defaultInstitucionShouldNotBeFound("temporadaAlta.in=" + UPDATED_TEMPORADA_ALTA);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTemporadaAltaIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where temporadaAlta is not null
        defaultInstitucionShouldBeFound("temporadaAlta.specified=true");

        // Get all the institucionList where temporadaAlta is null
        defaultInstitucionShouldNotBeFound("temporadaAlta.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTemporadaBajaIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where temporadaBaja equals to DEFAULT_TEMPORADA_BAJA
        defaultInstitucionShouldBeFound("temporadaBaja.equals=" + DEFAULT_TEMPORADA_BAJA);

        // Get all the institucionList where temporadaBaja equals to UPDATED_TEMPORADA_BAJA
        defaultInstitucionShouldNotBeFound("temporadaBaja.equals=" + UPDATED_TEMPORADA_BAJA);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTemporadaBajaIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where temporadaBaja in DEFAULT_TEMPORADA_BAJA or UPDATED_TEMPORADA_BAJA
        defaultInstitucionShouldBeFound("temporadaBaja.in=" + DEFAULT_TEMPORADA_BAJA + "," + UPDATED_TEMPORADA_BAJA);

        // Get all the institucionList where temporadaBaja equals to UPDATED_TEMPORADA_BAJA
        defaultInstitucionShouldNotBeFound("temporadaBaja.in=" + UPDATED_TEMPORADA_BAJA);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByTemporadaBajaIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where temporadaBaja is not null
        defaultInstitucionShouldBeFound("temporadaBaja.specified=true");

        // Get all the institucionList where temporadaBaja is null
        defaultInstitucionShouldNotBeFound("temporadaBaja.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByFechaInicialIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where fechaInicial equals to DEFAULT_FECHA_INICIAL
        defaultInstitucionShouldBeFound("fechaInicial.equals=" + DEFAULT_FECHA_INICIAL);

        // Get all the institucionList where fechaInicial equals to UPDATED_FECHA_INICIAL
        defaultInstitucionShouldNotBeFound("fechaInicial.equals=" + UPDATED_FECHA_INICIAL);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByFechaInicialIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where fechaInicial in DEFAULT_FECHA_INICIAL or UPDATED_FECHA_INICIAL
        defaultInstitucionShouldBeFound("fechaInicial.in=" + DEFAULT_FECHA_INICIAL + "," + UPDATED_FECHA_INICIAL);

        // Get all the institucionList where fechaInicial equals to UPDATED_FECHA_INICIAL
        defaultInstitucionShouldNotBeFound("fechaInicial.in=" + UPDATED_FECHA_INICIAL);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByFechaInicialIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where fechaInicial is not null
        defaultInstitucionShouldBeFound("fechaInicial.specified=true");

        // Get all the institucionList where fechaInicial is null
        defaultInstitucionShouldNotBeFound("fechaInicial.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByFechaInicialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where fechaInicial greater than or equals to DEFAULT_FECHA_INICIAL
        defaultInstitucionShouldBeFound("fechaInicial.greaterOrEqualThan=" + DEFAULT_FECHA_INICIAL);

        // Get all the institucionList where fechaInicial greater than or equals to UPDATED_FECHA_INICIAL
        defaultInstitucionShouldNotBeFound("fechaInicial.greaterOrEqualThan=" + UPDATED_FECHA_INICIAL);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByFechaInicialIsLessThanSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where fechaInicial less than or equals to DEFAULT_FECHA_INICIAL
        defaultInstitucionShouldNotBeFound("fechaInicial.lessThan=" + DEFAULT_FECHA_INICIAL);

        // Get all the institucionList where fechaInicial less than or equals to UPDATED_FECHA_INICIAL
        defaultInstitucionShouldBeFound("fechaInicial.lessThan=" + UPDATED_FECHA_INICIAL);
    }


    @Test
    @Transactional
    public void getAllInstitucionsByHorariosIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where horarios equals to DEFAULT_HORARIOS
        defaultInstitucionShouldBeFound("horarios.equals=" + DEFAULT_HORARIOS);

        // Get all the institucionList where horarios equals to UPDATED_HORARIOS
        defaultInstitucionShouldNotBeFound("horarios.equals=" + UPDATED_HORARIOS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByHorariosIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where horarios in DEFAULT_HORARIOS or UPDATED_HORARIOS
        defaultInstitucionShouldBeFound("horarios.in=" + DEFAULT_HORARIOS + "," + UPDATED_HORARIOS);

        // Get all the institucionList where horarios equals to UPDATED_HORARIOS
        defaultInstitucionShouldNotBeFound("horarios.in=" + UPDATED_HORARIOS);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByHorariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where horarios is not null
        defaultInstitucionShouldBeFound("horarios.specified=true");

        // Get all the institucionList where horarios is null
        defaultInstitucionShouldNotBeFound("horarios.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByInstalacionesIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where instalaciones equals to DEFAULT_INSTALACIONES
        defaultInstitucionShouldBeFound("instalaciones.equals=" + DEFAULT_INSTALACIONES);

        // Get all the institucionList where instalaciones equals to UPDATED_INSTALACIONES
        defaultInstitucionShouldNotBeFound("instalaciones.equals=" + UPDATED_INSTALACIONES);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByInstalacionesIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where instalaciones in DEFAULT_INSTALACIONES or UPDATED_INSTALACIONES
        defaultInstitucionShouldBeFound("instalaciones.in=" + DEFAULT_INSTALACIONES + "," + UPDATED_INSTALACIONES);

        // Get all the institucionList where instalaciones equals to UPDATED_INSTALACIONES
        defaultInstitucionShouldNotBeFound("instalaciones.in=" + UPDATED_INSTALACIONES);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByInstalacionesIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where instalaciones is not null
        defaultInstitucionShouldBeFound("instalaciones.specified=true");

        // Get all the institucionList where instalaciones is null
        defaultInstitucionShouldNotBeFound("instalaciones.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByNacionalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where nacionalidad equals to DEFAULT_NACIONALIDAD
        defaultInstitucionShouldBeFound("nacionalidad.equals=" + DEFAULT_NACIONALIDAD);

        // Get all the institucionList where nacionalidad equals to UPDATED_NACIONALIDAD
        defaultInstitucionShouldNotBeFound("nacionalidad.equals=" + UPDATED_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByNacionalidadIsInShouldWork() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where nacionalidad in DEFAULT_NACIONALIDAD or UPDATED_NACIONALIDAD
        defaultInstitucionShouldBeFound("nacionalidad.in=" + DEFAULT_NACIONALIDAD + "," + UPDATED_NACIONALIDAD);

        // Get all the institucionList where nacionalidad equals to UPDATED_NACIONALIDAD
        defaultInstitucionShouldNotBeFound("nacionalidad.in=" + UPDATED_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllInstitucionsByNacionalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList where nacionalidad is not null
        defaultInstitucionShouldBeFound("nacionalidad.specified=true");

        // Get all the institucionList where nacionalidad is null
        defaultInstitucionShouldNotBeFound("nacionalidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllInstitucionsByCiudadIsEqualToSomething() throws Exception {
        // Initialize the database
        Ciudad ciudad = CiudadResourceIT.createEntity(em);
        em.persist(ciudad);
        em.flush();
        institucion.setCiudad(ciudad);
        institucionRepository.saveAndFlush(institucion);
        Long ciudadId = ciudad.getId();

        // Get all the institucionList where ciudad equals to ciudadId
        defaultInstitucionShouldBeFound("ciudadId.equals=" + ciudadId);

        // Get all the institucionList where ciudad equals to ciudadId + 1
        defaultInstitucionShouldNotBeFound("ciudadId.equals=" + (ciudadId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultInstitucionShouldBeFound(String filter) throws Exception {
        restInstitucionMockMvc.perform(get("/api/institucions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(institucion.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].contacto").value(hasItem(DEFAULT_CONTACTO)))
            .andExpect(jsonPath("$.[*].representante").value(hasItem(DEFAULT_REPRESENTANTE)))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].tipoProgramas").value(hasItem(DEFAULT_TIPO_PROGRAMAS)))
            .andExpect(jsonPath("$.[*].programaEstandar").value(hasItem(DEFAULT_PROGRAMA_ESTANDAR)))
            .andExpect(jsonPath("$.[*].programaIntensivo").value(hasItem(DEFAULT_PROGRAMA_INTENSIVO)))
            .andExpect(jsonPath("$.[*].programaNegocios").value(hasItem(DEFAULT_PROGRAMA_NEGOCIOS)))
            .andExpect(jsonPath("$.[*].preparacionExamen").value(hasItem(DEFAULT_PREPARACION_EXAMEN)))
            .andExpect(jsonPath("$.[*].programaAcademico").value(hasItem(DEFAULT_PROGRAMA_ACADEMICO)))
            .andExpect(jsonPath("$.[*].descuento").value(hasItem(DEFAULT_DESCUENTO)))
            .andExpect(jsonPath("$.[*].inscripcion").value(hasItem(DEFAULT_INSCRIPCION)))
            .andExpect(jsonPath("$.[*].materiales").value(hasItem(DEFAULT_MATERIALES)))
            .andExpect(jsonPath("$.[*].seguroMedico").value(hasItem(DEFAULT_SEGURO_MEDICO)))
            .andExpect(jsonPath("$.[*].alojamientoSencillo").value(hasItem(DEFAULT_ALOJAMIENTO_SENCILLO)))
            .andExpect(jsonPath("$.[*].alojamientoDoble").value(hasItem(DEFAULT_ALOJAMIENTO_DOBLE)))
            .andExpect(jsonPath("$.[*].transporteAeropuerto").value(hasItem(DEFAULT_TRANSPORTE_AEROPUERTO)))
            .andExpect(jsonPath("$.[*].tipoCurso").value(hasItem(DEFAULT_TIPO_CURSO)))
            .andExpect(jsonPath("$.[*].temporadaAlta").value(hasItem(DEFAULT_TEMPORADA_ALTA)))
            .andExpect(jsonPath("$.[*].temporadaBaja").value(hasItem(DEFAULT_TEMPORADA_BAJA)))
            .andExpect(jsonPath("$.[*].fechaInicial").value(hasItem(DEFAULT_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].horarios").value(hasItem(DEFAULT_HORARIOS)))
            .andExpect(jsonPath("$.[*].instalaciones").value(hasItem(DEFAULT_INSTALACIONES)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD)))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].foto3ContentType").value(hasItem(DEFAULT_FOTO_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_3))));

        // Check, that the count call also returns 1
        restInstitucionMockMvc.perform(get("/api/institucions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultInstitucionShouldNotBeFound(String filter) throws Exception {
        restInstitucionMockMvc.perform(get("/api/institucions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restInstitucionMockMvc.perform(get("/api/institucions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingInstitucion() throws Exception {
        // Get the institucion
        restInstitucionMockMvc.perform(get("/api/institucions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstitucion() throws Exception {
        // Initialize the database
        institucionService.save(institucion);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockInstitucionSearchRepository);

        int databaseSizeBeforeUpdate = institucionRepository.findAll().size();

        // Update the institucion
        Institucion updatedInstitucion = institucionRepository.findById(institucion.getId()).get();
        // Disconnect from session so that the updates on updatedInstitucion are not directly saved in db
        em.detach(updatedInstitucion);
        updatedInstitucion
            .codigo(UPDATED_CODIGO)
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .direccion(UPDATED_DIRECCION)
            .website(UPDATED_WEBSITE)
            .contacto(UPDATED_CONTACTO)
            .representante(UPDATED_REPRESENTANTE)
            .skype(UPDATED_SKYPE)
            .telefono(UPDATED_TELEFONO)
            .estatus(UPDATED_ESTATUS)
            .tipoProgramas(UPDATED_TIPO_PROGRAMAS)
            .programaEstandar(UPDATED_PROGRAMA_ESTANDAR)
            .programaIntensivo(UPDATED_PROGRAMA_INTENSIVO)
            .programaNegocios(UPDATED_PROGRAMA_NEGOCIOS)
            .preparacionExamen(UPDATED_PREPARACION_EXAMEN)
            .programaAcademico(UPDATED_PROGRAMA_ACADEMICO)
            .descuento(UPDATED_DESCUENTO)
            .inscripcion(UPDATED_INSCRIPCION)
            .materiales(UPDATED_MATERIALES)
            .seguroMedico(UPDATED_SEGURO_MEDICO)
            .alojamientoSencillo(UPDATED_ALOJAMIENTO_SENCILLO)
            .alojamientoDoble(UPDATED_ALOJAMIENTO_DOBLE)
            .transporteAeropuerto(UPDATED_TRANSPORTE_AEROPUERTO)
            .tipoCurso(UPDATED_TIPO_CURSO)
            .temporadaAlta(UPDATED_TEMPORADA_ALTA)
            .temporadaBaja(UPDATED_TEMPORADA_BAJA)
            .fechaInicial(UPDATED_FECHA_INICIAL)
            .horarios(UPDATED_HORARIOS)
            .instalaciones(UPDATED_INSTALACIONES)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .foto3(UPDATED_FOTO_3)
            .foto3ContentType(UPDATED_FOTO_3_CONTENT_TYPE);

        restInstitucionMockMvc.perform(put("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstitucion)))
            .andExpect(status().isOk());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeUpdate);
        Institucion testInstitucion = institucionList.get(institucionList.size() - 1);
        assertThat(testInstitucion.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testInstitucion.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testInstitucion.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testInstitucion.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testInstitucion.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testInstitucion.getContacto()).isEqualTo(UPDATED_CONTACTO);
        assertThat(testInstitucion.getRepresentante()).isEqualTo(UPDATED_REPRESENTANTE);
        assertThat(testInstitucion.getSkype()).isEqualTo(UPDATED_SKYPE);
        assertThat(testInstitucion.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testInstitucion.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testInstitucion.getTipoProgramas()).isEqualTo(UPDATED_TIPO_PROGRAMAS);
        assertThat(testInstitucion.getProgramaEstandar()).isEqualTo(UPDATED_PROGRAMA_ESTANDAR);
        assertThat(testInstitucion.getProgramaIntensivo()).isEqualTo(UPDATED_PROGRAMA_INTENSIVO);
        assertThat(testInstitucion.getProgramaNegocios()).isEqualTo(UPDATED_PROGRAMA_NEGOCIOS);
        assertThat(testInstitucion.getPreparacionExamen()).isEqualTo(UPDATED_PREPARACION_EXAMEN);
        assertThat(testInstitucion.getProgramaAcademico()).isEqualTo(UPDATED_PROGRAMA_ACADEMICO);
        assertThat(testInstitucion.getDescuento()).isEqualTo(UPDATED_DESCUENTO);
        assertThat(testInstitucion.getInscripcion()).isEqualTo(UPDATED_INSCRIPCION);
        assertThat(testInstitucion.getMateriales()).isEqualTo(UPDATED_MATERIALES);
        assertThat(testInstitucion.getSeguroMedico()).isEqualTo(UPDATED_SEGURO_MEDICO);
        assertThat(testInstitucion.getAlojamientoSencillo()).isEqualTo(UPDATED_ALOJAMIENTO_SENCILLO);
        assertThat(testInstitucion.getAlojamientoDoble()).isEqualTo(UPDATED_ALOJAMIENTO_DOBLE);
        assertThat(testInstitucion.getTransporteAeropuerto()).isEqualTo(UPDATED_TRANSPORTE_AEROPUERTO);
        assertThat(testInstitucion.getTipoCurso()).isEqualTo(UPDATED_TIPO_CURSO);
        assertThat(testInstitucion.getTemporadaAlta()).isEqualTo(UPDATED_TEMPORADA_ALTA);
        assertThat(testInstitucion.getTemporadaBaja()).isEqualTo(UPDATED_TEMPORADA_BAJA);
        assertThat(testInstitucion.getFechaInicial()).isEqualTo(UPDATED_FECHA_INICIAL);
        assertThat(testInstitucion.getHorarios()).isEqualTo(UPDATED_HORARIOS);
        assertThat(testInstitucion.getInstalaciones()).isEqualTo(UPDATED_INSTALACIONES);
        assertThat(testInstitucion.getNacionalidad()).isEqualTo(UPDATED_NACIONALIDAD);
        assertThat(testInstitucion.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testInstitucion.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
        assertThat(testInstitucion.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testInstitucion.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testInstitucion.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testInstitucion.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);
        assertThat(testInstitucion.getFoto3()).isEqualTo(UPDATED_FOTO_3);
        assertThat(testInstitucion.getFoto3ContentType()).isEqualTo(UPDATED_FOTO_3_CONTENT_TYPE);

        // Validate the Institucion in Elasticsearch
        verify(mockInstitucionSearchRepository, times(1)).save(testInstitucion);
    }

    @Test
    @Transactional
    public void updateNonExistingInstitucion() throws Exception {
        int databaseSizeBeforeUpdate = institucionRepository.findAll().size();

        // Create the Institucion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitucionMockMvc.perform(put("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(institucion)))
            .andExpect(status().isBadRequest());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Institucion in Elasticsearch
        verify(mockInstitucionSearchRepository, times(0)).save(institucion);
    }

    @Test
    @Transactional
    public void deleteInstitucion() throws Exception {
        // Initialize the database
        institucionService.save(institucion);

        int databaseSizeBeforeDelete = institucionRepository.findAll().size();

        // Delete the institucion
        restInstitucionMockMvc.perform(delete("/api/institucions/{id}", institucion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Institucion in Elasticsearch
        verify(mockInstitucionSearchRepository, times(1)).deleteById(institucion.getId());
    }

    @Test
    @Transactional
    public void searchInstitucion() throws Exception {
        // Initialize the database
        institucionService.save(institucion);
        when(mockInstitucionSearchRepository.search(queryStringQuery("id:" + institucion.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(institucion), PageRequest.of(0, 1), 1));
        // Search the institucion
        restInstitucionMockMvc.perform(get("/api/_search/institucions?query=id:" + institucion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(institucion.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].contacto").value(hasItem(DEFAULT_CONTACTO)))
            .andExpect(jsonPath("$.[*].representante").value(hasItem(DEFAULT_REPRESENTANTE)))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].tipoProgramas").value(hasItem(DEFAULT_TIPO_PROGRAMAS)))
            .andExpect(jsonPath("$.[*].programaEstandar").value(hasItem(DEFAULT_PROGRAMA_ESTANDAR)))
            .andExpect(jsonPath("$.[*].programaIntensivo").value(hasItem(DEFAULT_PROGRAMA_INTENSIVO)))
            .andExpect(jsonPath("$.[*].programaNegocios").value(hasItem(DEFAULT_PROGRAMA_NEGOCIOS)))
            .andExpect(jsonPath("$.[*].preparacionExamen").value(hasItem(DEFAULT_PREPARACION_EXAMEN)))
            .andExpect(jsonPath("$.[*].programaAcademico").value(hasItem(DEFAULT_PROGRAMA_ACADEMICO)))
            .andExpect(jsonPath("$.[*].descuento").value(hasItem(DEFAULT_DESCUENTO)))
            .andExpect(jsonPath("$.[*].inscripcion").value(hasItem(DEFAULT_INSCRIPCION)))
            .andExpect(jsonPath("$.[*].materiales").value(hasItem(DEFAULT_MATERIALES)))
            .andExpect(jsonPath("$.[*].seguroMedico").value(hasItem(DEFAULT_SEGURO_MEDICO)))
            .andExpect(jsonPath("$.[*].alojamientoSencillo").value(hasItem(DEFAULT_ALOJAMIENTO_SENCILLO)))
            .andExpect(jsonPath("$.[*].alojamientoDoble").value(hasItem(DEFAULT_ALOJAMIENTO_DOBLE)))
            .andExpect(jsonPath("$.[*].transporteAeropuerto").value(hasItem(DEFAULT_TRANSPORTE_AEROPUERTO)))
            .andExpect(jsonPath("$.[*].tipoCurso").value(hasItem(DEFAULT_TIPO_CURSO)))
            .andExpect(jsonPath("$.[*].temporadaAlta").value(hasItem(DEFAULT_TEMPORADA_ALTA)))
            .andExpect(jsonPath("$.[*].temporadaBaja").value(hasItem(DEFAULT_TEMPORADA_BAJA)))
            .andExpect(jsonPath("$.[*].fechaInicial").value(hasItem(DEFAULT_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].horarios").value(hasItem(DEFAULT_HORARIOS)))
            .andExpect(jsonPath("$.[*].instalaciones").value(hasItem(DEFAULT_INSTALACIONES)))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD)))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].foto3ContentType").value(hasItem(DEFAULT_FOTO_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_3))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Institucion.class);
        Institucion institucion1 = new Institucion();
        institucion1.setId(1L);
        Institucion institucion2 = new Institucion();
        institucion2.setId(institucion1.getId());
        assertThat(institucion1).isEqualTo(institucion2);
        institucion2.setId(2L);
        assertThat(institucion1).isNotEqualTo(institucion2);
        institucion1.setId(null);
        assertThat(institucion1).isNotEqualTo(institucion2);
    }
}
