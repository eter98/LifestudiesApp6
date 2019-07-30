package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.repository.PerfilUsuarioRepository;
import io.github.jhipster.application.repository.search.PerfilUsuarioSearchRepository;
import io.github.jhipster.application.service.PerfilUsuarioService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PerfilUsuarioCriteria;
import io.github.jhipster.application.service.PerfilUsuarioQueryService;

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
 * Integration tests for the {@Link PerfilUsuarioResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class PerfilUsuarioResourceIT {

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_IDENTIFICACION = 1;
    private static final Integer UPDATED_IDENTIFICACION = 2;

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_AREA = 1;
    private static final Integer UPDATED_AREA = 2;

    private static final Integer DEFAULT_TELEFONO = 1;
    private static final Integer UPDATED_TELEFONO = 2;

    private static final String DEFAULT_NIVEL_ACADEMICO = "AAAAAAAAAA";
    private static final String UPDATED_NIVEL_ACADEMICO = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_ACADEMICA = "AAAAAAAAAA";
    private static final String UPDATED_AREA_ACADEMICA = "BBBBBBBBBB";

    private static final Integer DEFAULT_TERMINO_ACADEMICO = 1;
    private static final Integer UPDATED_TERMINO_ACADEMICO = 2;

    private static final Integer DEFAULT_PUNTAJE_ICFES = 1;
    private static final Integer UPDATED_PUNTAJE_ICFES = 2;

    private static final Integer DEFAULT_PROMEDIO_ACADEMICO = 1;
    private static final Integer UPDATED_PROMEDIO_ACADEMICO = 2;

    private static final Boolean DEFAULT_DOMINIO_IDIOMA = false;
    private static final Boolean UPDATED_DOMINIO_IDIOMA = true;

    private static final String DEFAULT_IDIOMAS = "AAAAAAAAAA";
    private static final String UPDATED_IDIOMAS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXAMEN_IDIOMA = false;
    private static final Boolean UPDATED_EXAMEN_IDIOMA = true;

    private static final String DEFAULT_EXAMEN_REALIZADO = "AAAAAAAAAA";
    private static final String UPDATED_EXAMEN_REALIZADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PUNTAJE_IDIOMA = 1;
    private static final Integer UPDATED_PUNTAJE_IDIOMA = 2;

    private static final Boolean DEFAULT_BECA_OTORGADA = false;
    private static final Boolean UPDATED_BECA_OTORGADA = true;

    private static final Integer DEFAULT_TIPO_BECA = 1;
    private static final Integer UPDATED_TIPO_BECA = 2;

    private static final String DEFAULT_BECA_INSTITUCION = "AAAAAAAAAA";
    private static final String UPDATED_BECA_INSTITUCION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_GRUPO_SOCIAL = false;
    private static final Boolean UPDATED_GRUPO_SOCIAL = true;

    private static final String DEFAULT_FUNDACION = "AAAAAAAAAA";
    private static final String UPDATED_FUNDACION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MONITOR = false;
    private static final Boolean UPDATED_MONITOR = true;

    private static final String DEFAULT_MONITOR_MATERIA = "AAAAAAAAAA";
    private static final String UPDATED_MONITOR_MATERIA = "BBBBBBBBBB";

    private static final String DEFAULT_LOGROS_ACADEMICOS = "AAAAAAAAAA";
    private static final String UPDATED_LOGROS_ACADEMICOS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXPERIENCIA_LABORAL = false;
    private static final Boolean UPDATED_EXPERIENCIA_LABORAL = true;

    private static final String DEFAULT_AREA_LABORAL = "AAAAAAAAAA";
    private static final String UPDATED_AREA_LABORAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROGRAMAREALIZAR = 1;
    private static final Integer UPDATED_PROGRAMAREALIZAR = 2;

    private static final String DEFAULT_PROGRAMA_AREA = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_AREA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_PROGRAMA_ENCONTRADO = false;
    private static final Boolean UPDATED_PROGRAMA_ENCONTRADO = true;

    private static final String DEFAULT_NOMBRE_PROGRAMA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_PROGRAMA = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSIDAD = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSIDAD = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAIS = 1;
    private static final Integer UPDATED_PAIS = 2;

    private static final String DEFAULT_MERECEDOR_BECA = "AAAAAAAAAA";
    private static final String UPDATED_MERECEDOR_BECA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.PerfilUsuarioSearchRepositoryMockConfiguration
     */
    @Autowired
    private PerfilUsuarioSearchRepository mockPerfilUsuarioSearchRepository;

    @Autowired
    private PerfilUsuarioQueryService perfilUsuarioQueryService;

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

    private MockMvc restPerfilUsuarioMockMvc;

    private PerfilUsuario perfilUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PerfilUsuarioResource perfilUsuarioResource = new PerfilUsuarioResource(perfilUsuarioService, perfilUsuarioQueryService);
        this.restPerfilUsuarioMockMvc = MockMvcBuilders.standaloneSetup(perfilUsuarioResource)
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
    public static PerfilUsuario createEntity(EntityManager em) {
        PerfilUsuario perfilUsuario = new PerfilUsuario()
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .identificacion(DEFAULT_IDENTIFICACION)
            .mail(DEFAULT_MAIL)
            .area(DEFAULT_AREA)
            .telefono(DEFAULT_TELEFONO)
            .nivelAcademico(DEFAULT_NIVEL_ACADEMICO)
            .areaAcademica(DEFAULT_AREA_ACADEMICA)
            .terminoAcademico(DEFAULT_TERMINO_ACADEMICO)
            .puntajeICFES(DEFAULT_PUNTAJE_ICFES)
            .promedioAcademico(DEFAULT_PROMEDIO_ACADEMICO)
            .dominioIdioma(DEFAULT_DOMINIO_IDIOMA)
            .idiomas(DEFAULT_IDIOMAS)
            .examenIdioma(DEFAULT_EXAMEN_IDIOMA)
            .examenRealizado(DEFAULT_EXAMEN_REALIZADO)
            .puntajeIdioma(DEFAULT_PUNTAJE_IDIOMA)
            .becaOtorgada(DEFAULT_BECA_OTORGADA)
            .tipoBeca(DEFAULT_TIPO_BECA)
            .becaInstitucion(DEFAULT_BECA_INSTITUCION)
            .grupoSocial(DEFAULT_GRUPO_SOCIAL)
            .fundacion(DEFAULT_FUNDACION)
            .monitor(DEFAULT_MONITOR)
            .monitorMateria(DEFAULT_MONITOR_MATERIA)
            .logrosAcademicos(DEFAULT_LOGROS_ACADEMICOS)
            .experienciaLaboral(DEFAULT_EXPERIENCIA_LABORAL)
            .areaLaboral(DEFAULT_AREA_LABORAL)
            .programarealizar(DEFAULT_PROGRAMAREALIZAR)
            .programaArea(DEFAULT_PROGRAMA_AREA)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .programaEncontrado(DEFAULT_PROGRAMA_ENCONTRADO)
            .nombrePrograma(DEFAULT_NOMBRE_PROGRAMA)
            .universidad(DEFAULT_UNIVERSIDAD)
            .pais(DEFAULT_PAIS)
            .merecedorBeca(DEFAULT_MERECEDOR_BECA)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE);
        return perfilUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerfilUsuario createUpdatedEntity(EntityManager em) {
        PerfilUsuario perfilUsuario = new PerfilUsuario()
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .identificacion(UPDATED_IDENTIFICACION)
            .mail(UPDATED_MAIL)
            .area(UPDATED_AREA)
            .telefono(UPDATED_TELEFONO)
            .nivelAcademico(UPDATED_NIVEL_ACADEMICO)
            .areaAcademica(UPDATED_AREA_ACADEMICA)
            .terminoAcademico(UPDATED_TERMINO_ACADEMICO)
            .puntajeICFES(UPDATED_PUNTAJE_ICFES)
            .promedioAcademico(UPDATED_PROMEDIO_ACADEMICO)
            .dominioIdioma(UPDATED_DOMINIO_IDIOMA)
            .idiomas(UPDATED_IDIOMAS)
            .examenIdioma(UPDATED_EXAMEN_IDIOMA)
            .examenRealizado(UPDATED_EXAMEN_REALIZADO)
            .puntajeIdioma(UPDATED_PUNTAJE_IDIOMA)
            .becaOtorgada(UPDATED_BECA_OTORGADA)
            .tipoBeca(UPDATED_TIPO_BECA)
            .becaInstitucion(UPDATED_BECA_INSTITUCION)
            .grupoSocial(UPDATED_GRUPO_SOCIAL)
            .fundacion(UPDATED_FUNDACION)
            .monitor(UPDATED_MONITOR)
            .monitorMateria(UPDATED_MONITOR_MATERIA)
            .logrosAcademicos(UPDATED_LOGROS_ACADEMICOS)
            .experienciaLaboral(UPDATED_EXPERIENCIA_LABORAL)
            .areaLaboral(UPDATED_AREA_LABORAL)
            .programarealizar(UPDATED_PROGRAMAREALIZAR)
            .programaArea(UPDATED_PROGRAMA_AREA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .programaEncontrado(UPDATED_PROGRAMA_ENCONTRADO)
            .nombrePrograma(UPDATED_NOMBRE_PROGRAMA)
            .universidad(UPDATED_UNIVERSIDAD)
            .pais(UPDATED_PAIS)
            .merecedorBeca(UPDATED_MERECEDOR_BECA)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);
        return perfilUsuario;
    }

    @BeforeEach
    public void initTest() {
        perfilUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerfilUsuario() throws Exception {
        int databaseSizeBeforeCreate = perfilUsuarioRepository.findAll().size();

        // Create the PerfilUsuario
        restPerfilUsuarioMockMvc.perform(post("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilUsuario)))
            .andExpect(status().isCreated());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        PerfilUsuario testPerfilUsuario = perfilUsuarioList.get(perfilUsuarioList.size() - 1);
        assertThat(testPerfilUsuario.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testPerfilUsuario.getIdentificacion()).isEqualTo(DEFAULT_IDENTIFICACION);
        assertThat(testPerfilUsuario.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testPerfilUsuario.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testPerfilUsuario.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testPerfilUsuario.getNivelAcademico()).isEqualTo(DEFAULT_NIVEL_ACADEMICO);
        assertThat(testPerfilUsuario.getAreaAcademica()).isEqualTo(DEFAULT_AREA_ACADEMICA);
        assertThat(testPerfilUsuario.getTerminoAcademico()).isEqualTo(DEFAULT_TERMINO_ACADEMICO);
        assertThat(testPerfilUsuario.getPuntajeICFES()).isEqualTo(DEFAULT_PUNTAJE_ICFES);
        assertThat(testPerfilUsuario.getPromedioAcademico()).isEqualTo(DEFAULT_PROMEDIO_ACADEMICO);
        assertThat(testPerfilUsuario.isDominioIdioma()).isEqualTo(DEFAULT_DOMINIO_IDIOMA);
        assertThat(testPerfilUsuario.getIdiomas()).isEqualTo(DEFAULT_IDIOMAS);
        assertThat(testPerfilUsuario.isExamenIdioma()).isEqualTo(DEFAULT_EXAMEN_IDIOMA);
        assertThat(testPerfilUsuario.getExamenRealizado()).isEqualTo(DEFAULT_EXAMEN_REALIZADO);
        assertThat(testPerfilUsuario.getPuntajeIdioma()).isEqualTo(DEFAULT_PUNTAJE_IDIOMA);
        assertThat(testPerfilUsuario.isBecaOtorgada()).isEqualTo(DEFAULT_BECA_OTORGADA);
        assertThat(testPerfilUsuario.getTipoBeca()).isEqualTo(DEFAULT_TIPO_BECA);
        assertThat(testPerfilUsuario.getBecaInstitucion()).isEqualTo(DEFAULT_BECA_INSTITUCION);
        assertThat(testPerfilUsuario.isGrupoSocial()).isEqualTo(DEFAULT_GRUPO_SOCIAL);
        assertThat(testPerfilUsuario.getFundacion()).isEqualTo(DEFAULT_FUNDACION);
        assertThat(testPerfilUsuario.isMonitor()).isEqualTo(DEFAULT_MONITOR);
        assertThat(testPerfilUsuario.getMonitorMateria()).isEqualTo(DEFAULT_MONITOR_MATERIA);
        assertThat(testPerfilUsuario.getLogrosAcademicos()).isEqualTo(DEFAULT_LOGROS_ACADEMICOS);
        assertThat(testPerfilUsuario.isExperienciaLaboral()).isEqualTo(DEFAULT_EXPERIENCIA_LABORAL);
        assertThat(testPerfilUsuario.getAreaLaboral()).isEqualTo(DEFAULT_AREA_LABORAL);
        assertThat(testPerfilUsuario.getProgramarealizar()).isEqualTo(DEFAULT_PROGRAMAREALIZAR);
        assertThat(testPerfilUsuario.getProgramaArea()).isEqualTo(DEFAULT_PROGRAMA_AREA);
        assertThat(testPerfilUsuario.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testPerfilUsuario.isProgramaEncontrado()).isEqualTo(DEFAULT_PROGRAMA_ENCONTRADO);
        assertThat(testPerfilUsuario.getNombrePrograma()).isEqualTo(DEFAULT_NOMBRE_PROGRAMA);
        assertThat(testPerfilUsuario.getUniversidad()).isEqualTo(DEFAULT_UNIVERSIDAD);
        assertThat(testPerfilUsuario.getPais()).isEqualTo(DEFAULT_PAIS);
        assertThat(testPerfilUsuario.getMerecedorBeca()).isEqualTo(DEFAULT_MERECEDOR_BECA);
        assertThat(testPerfilUsuario.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testPerfilUsuario.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);

        // Validate the PerfilUsuario in Elasticsearch
        verify(mockPerfilUsuarioSearchRepository, times(1)).save(testPerfilUsuario);
    }

    @Test
    @Transactional
    public void createPerfilUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perfilUsuarioRepository.findAll().size();

        // Create the PerfilUsuario with an existing ID
        perfilUsuario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerfilUsuarioMockMvc.perform(post("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilUsuario)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeCreate);

        // Validate the PerfilUsuario in Elasticsearch
        verify(mockPerfilUsuarioSearchRepository, times(0)).save(perfilUsuario);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuarios() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].areaAcademica").value(hasItem(DEFAULT_AREA_ACADEMICA.toString())))
            .andExpect(jsonPath("$.[*].terminoAcademico").value(hasItem(DEFAULT_TERMINO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].puntajeICFES").value(hasItem(DEFAULT_PUNTAJE_ICFES)))
            .andExpect(jsonPath("$.[*].promedioAcademico").value(hasItem(DEFAULT_PROMEDIO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].dominioIdioma").value(hasItem(DEFAULT_DOMINIO_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].idiomas").value(hasItem(DEFAULT_IDIOMAS.toString())))
            .andExpect(jsonPath("$.[*].examenIdioma").value(hasItem(DEFAULT_EXAMEN_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].examenRealizado").value(hasItem(DEFAULT_EXAMEN_REALIZADO.toString())))
            .andExpect(jsonPath("$.[*].puntajeIdioma").value(hasItem(DEFAULT_PUNTAJE_IDIOMA)))
            .andExpect(jsonPath("$.[*].becaOtorgada").value(hasItem(DEFAULT_BECA_OTORGADA.booleanValue())))
            .andExpect(jsonPath("$.[*].tipoBeca").value(hasItem(DEFAULT_TIPO_BECA)))
            .andExpect(jsonPath("$.[*].becaInstitucion").value(hasItem(DEFAULT_BECA_INSTITUCION.toString())))
            .andExpect(jsonPath("$.[*].grupoSocial").value(hasItem(DEFAULT_GRUPO_SOCIAL.booleanValue())))
            .andExpect(jsonPath("$.[*].fundacion").value(hasItem(DEFAULT_FUNDACION.toString())))
            .andExpect(jsonPath("$.[*].monitor").value(hasItem(DEFAULT_MONITOR.booleanValue())))
            .andExpect(jsonPath("$.[*].monitorMateria").value(hasItem(DEFAULT_MONITOR_MATERIA.toString())))
            .andExpect(jsonPath("$.[*].logrosAcademicos").value(hasItem(DEFAULT_LOGROS_ACADEMICOS.toString())))
            .andExpect(jsonPath("$.[*].experienciaLaboral").value(hasItem(DEFAULT_EXPERIENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].areaLaboral").value(hasItem(DEFAULT_AREA_LABORAL.toString())))
            .andExpect(jsonPath("$.[*].programarealizar").value(hasItem(DEFAULT_PROGRAMAREALIZAR)))
            .andExpect(jsonPath("$.[*].programaArea").value(hasItem(DEFAULT_PROGRAMA_AREA.toString())))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].programaEncontrado").value(hasItem(DEFAULT_PROGRAMA_ENCONTRADO.booleanValue())))
            .andExpect(jsonPath("$.[*].nombrePrograma").value(hasItem(DEFAULT_NOMBRE_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].universidad").value(hasItem(DEFAULT_UNIVERSIDAD.toString())))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].merecedorBeca").value(hasItem(DEFAULT_MERECEDOR_BECA.toString())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }
    
    @Test
    @Transactional
    public void getPerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get the perfilUsuario
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios/{id}", perfilUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perfilUsuario.getId().intValue()))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.identificacion").value(DEFAULT_IDENTIFICACION))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.nivelAcademico").value(DEFAULT_NIVEL_ACADEMICO.toString()))
            .andExpect(jsonPath("$.areaAcademica").value(DEFAULT_AREA_ACADEMICA.toString()))
            .andExpect(jsonPath("$.terminoAcademico").value(DEFAULT_TERMINO_ACADEMICO))
            .andExpect(jsonPath("$.puntajeICFES").value(DEFAULT_PUNTAJE_ICFES))
            .andExpect(jsonPath("$.promedioAcademico").value(DEFAULT_PROMEDIO_ACADEMICO))
            .andExpect(jsonPath("$.dominioIdioma").value(DEFAULT_DOMINIO_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.idiomas").value(DEFAULT_IDIOMAS.toString()))
            .andExpect(jsonPath("$.examenIdioma").value(DEFAULT_EXAMEN_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.examenRealizado").value(DEFAULT_EXAMEN_REALIZADO.toString()))
            .andExpect(jsonPath("$.puntajeIdioma").value(DEFAULT_PUNTAJE_IDIOMA))
            .andExpect(jsonPath("$.becaOtorgada").value(DEFAULT_BECA_OTORGADA.booleanValue()))
            .andExpect(jsonPath("$.tipoBeca").value(DEFAULT_TIPO_BECA))
            .andExpect(jsonPath("$.becaInstitucion").value(DEFAULT_BECA_INSTITUCION.toString()))
            .andExpect(jsonPath("$.grupoSocial").value(DEFAULT_GRUPO_SOCIAL.booleanValue()))
            .andExpect(jsonPath("$.fundacion").value(DEFAULT_FUNDACION.toString()))
            .andExpect(jsonPath("$.monitor").value(DEFAULT_MONITOR.booleanValue()))
            .andExpect(jsonPath("$.monitorMateria").value(DEFAULT_MONITOR_MATERIA.toString()))
            .andExpect(jsonPath("$.logrosAcademicos").value(DEFAULT_LOGROS_ACADEMICOS.toString()))
            .andExpect(jsonPath("$.experienciaLaboral").value(DEFAULT_EXPERIENCIA_LABORAL.booleanValue()))
            .andExpect(jsonPath("$.areaLaboral").value(DEFAULT_AREA_LABORAL.toString()))
            .andExpect(jsonPath("$.programarealizar").value(DEFAULT_PROGRAMAREALIZAR))
            .andExpect(jsonPath("$.programaArea").value(DEFAULT_PROGRAMA_AREA.toString()))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.programaEncontrado").value(DEFAULT_PROGRAMA_ENCONTRADO.booleanValue()))
            .andExpect(jsonPath("$.nombrePrograma").value(DEFAULT_NOMBRE_PROGRAMA.toString()))
            .andExpect(jsonPath("$.universidad").value(DEFAULT_UNIVERSIDAD.toString()))
            .andExpect(jsonPath("$.pais").value(DEFAULT_PAIS))
            .andExpect(jsonPath("$.merecedorBeca").value(DEFAULT_MERECEDOR_BECA.toString()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)));
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaNacimientoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaNacimiento equals to DEFAULT_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldBeFound("fechaNacimiento.equals=" + DEFAULT_FECHA_NACIMIENTO);

        // Get all the perfilUsuarioList where fechaNacimiento equals to UPDATED_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldNotBeFound("fechaNacimiento.equals=" + UPDATED_FECHA_NACIMIENTO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaNacimientoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaNacimiento in DEFAULT_FECHA_NACIMIENTO or UPDATED_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldBeFound("fechaNacimiento.in=" + DEFAULT_FECHA_NACIMIENTO + "," + UPDATED_FECHA_NACIMIENTO);

        // Get all the perfilUsuarioList where fechaNacimiento equals to UPDATED_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldNotBeFound("fechaNacimiento.in=" + UPDATED_FECHA_NACIMIENTO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaNacimientoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaNacimiento is not null
        defaultPerfilUsuarioShouldBeFound("fechaNacimiento.specified=true");

        // Get all the perfilUsuarioList where fechaNacimiento is null
        defaultPerfilUsuarioShouldNotBeFound("fechaNacimiento.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaNacimientoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaNacimiento greater than or equals to DEFAULT_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldBeFound("fechaNacimiento.greaterOrEqualThan=" + DEFAULT_FECHA_NACIMIENTO);

        // Get all the perfilUsuarioList where fechaNacimiento greater than or equals to UPDATED_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldNotBeFound("fechaNacimiento.greaterOrEqualThan=" + UPDATED_FECHA_NACIMIENTO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaNacimientoIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaNacimiento less than or equals to DEFAULT_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldNotBeFound("fechaNacimiento.lessThan=" + DEFAULT_FECHA_NACIMIENTO);

        // Get all the perfilUsuarioList where fechaNacimiento less than or equals to UPDATED_FECHA_NACIMIENTO
        defaultPerfilUsuarioShouldBeFound("fechaNacimiento.lessThan=" + UPDATED_FECHA_NACIMIENTO);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdentificacionIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where identificacion equals to DEFAULT_IDENTIFICACION
        defaultPerfilUsuarioShouldBeFound("identificacion.equals=" + DEFAULT_IDENTIFICACION);

        // Get all the perfilUsuarioList where identificacion equals to UPDATED_IDENTIFICACION
        defaultPerfilUsuarioShouldNotBeFound("identificacion.equals=" + UPDATED_IDENTIFICACION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdentificacionIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where identificacion in DEFAULT_IDENTIFICACION or UPDATED_IDENTIFICACION
        defaultPerfilUsuarioShouldBeFound("identificacion.in=" + DEFAULT_IDENTIFICACION + "," + UPDATED_IDENTIFICACION);

        // Get all the perfilUsuarioList where identificacion equals to UPDATED_IDENTIFICACION
        defaultPerfilUsuarioShouldNotBeFound("identificacion.in=" + UPDATED_IDENTIFICACION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdentificacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where identificacion is not null
        defaultPerfilUsuarioShouldBeFound("identificacion.specified=true");

        // Get all the perfilUsuarioList where identificacion is null
        defaultPerfilUsuarioShouldNotBeFound("identificacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdentificacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where identificacion greater than or equals to DEFAULT_IDENTIFICACION
        defaultPerfilUsuarioShouldBeFound("identificacion.greaterOrEqualThan=" + DEFAULT_IDENTIFICACION);

        // Get all the perfilUsuarioList where identificacion greater than or equals to UPDATED_IDENTIFICACION
        defaultPerfilUsuarioShouldNotBeFound("identificacion.greaterOrEqualThan=" + UPDATED_IDENTIFICACION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdentificacionIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where identificacion less than or equals to DEFAULT_IDENTIFICACION
        defaultPerfilUsuarioShouldNotBeFound("identificacion.lessThan=" + DEFAULT_IDENTIFICACION);

        // Get all the perfilUsuarioList where identificacion less than or equals to UPDATED_IDENTIFICACION
        defaultPerfilUsuarioShouldBeFound("identificacion.lessThan=" + UPDATED_IDENTIFICACION);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByMailIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where mail equals to DEFAULT_MAIL
        defaultPerfilUsuarioShouldBeFound("mail.equals=" + DEFAULT_MAIL);

        // Get all the perfilUsuarioList where mail equals to UPDATED_MAIL
        defaultPerfilUsuarioShouldNotBeFound("mail.equals=" + UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMailIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where mail in DEFAULT_MAIL or UPDATED_MAIL
        defaultPerfilUsuarioShouldBeFound("mail.in=" + DEFAULT_MAIL + "," + UPDATED_MAIL);

        // Get all the perfilUsuarioList where mail equals to UPDATED_MAIL
        defaultPerfilUsuarioShouldNotBeFound("mail.in=" + UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMailIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where mail is not null
        defaultPerfilUsuarioShouldBeFound("mail.specified=true");

        // Get all the perfilUsuarioList where mail is null
        defaultPerfilUsuarioShouldNotBeFound("mail.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where area equals to DEFAULT_AREA
        defaultPerfilUsuarioShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the perfilUsuarioList where area equals to UPDATED_AREA
        defaultPerfilUsuarioShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where area in DEFAULT_AREA or UPDATED_AREA
        defaultPerfilUsuarioShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the perfilUsuarioList where area equals to UPDATED_AREA
        defaultPerfilUsuarioShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where area is not null
        defaultPerfilUsuarioShouldBeFound("area.specified=true");

        // Get all the perfilUsuarioList where area is null
        defaultPerfilUsuarioShouldNotBeFound("area.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where area greater than or equals to DEFAULT_AREA
        defaultPerfilUsuarioShouldBeFound("area.greaterOrEqualThan=" + DEFAULT_AREA);

        // Get all the perfilUsuarioList where area greater than or equals to UPDATED_AREA
        defaultPerfilUsuarioShouldNotBeFound("area.greaterOrEqualThan=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where area less than or equals to DEFAULT_AREA
        defaultPerfilUsuarioShouldNotBeFound("area.lessThan=" + DEFAULT_AREA);

        // Get all the perfilUsuarioList where area less than or equals to UPDATED_AREA
        defaultPerfilUsuarioShouldBeFound("area.lessThan=" + UPDATED_AREA);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where telefono equals to DEFAULT_TELEFONO
        defaultPerfilUsuarioShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the perfilUsuarioList where telefono equals to UPDATED_TELEFONO
        defaultPerfilUsuarioShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultPerfilUsuarioShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the perfilUsuarioList where telefono equals to UPDATED_TELEFONO
        defaultPerfilUsuarioShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where telefono is not null
        defaultPerfilUsuarioShouldBeFound("telefono.specified=true");

        // Get all the perfilUsuarioList where telefono is null
        defaultPerfilUsuarioShouldNotBeFound("telefono.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTelefonoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where telefono greater than or equals to DEFAULT_TELEFONO
        defaultPerfilUsuarioShouldBeFound("telefono.greaterOrEqualThan=" + DEFAULT_TELEFONO);

        // Get all the perfilUsuarioList where telefono greater than or equals to UPDATED_TELEFONO
        defaultPerfilUsuarioShouldNotBeFound("telefono.greaterOrEqualThan=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTelefonoIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where telefono less than or equals to DEFAULT_TELEFONO
        defaultPerfilUsuarioShouldNotBeFound("telefono.lessThan=" + DEFAULT_TELEFONO);

        // Get all the perfilUsuarioList where telefono less than or equals to UPDATED_TELEFONO
        defaultPerfilUsuarioShouldBeFound("telefono.lessThan=" + UPDATED_TELEFONO);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByNivelAcademicoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where nivelAcademico equals to DEFAULT_NIVEL_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("nivelAcademico.equals=" + DEFAULT_NIVEL_ACADEMICO);

        // Get all the perfilUsuarioList where nivelAcademico equals to UPDATED_NIVEL_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("nivelAcademico.equals=" + UPDATED_NIVEL_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByNivelAcademicoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where nivelAcademico in DEFAULT_NIVEL_ACADEMICO or UPDATED_NIVEL_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("nivelAcademico.in=" + DEFAULT_NIVEL_ACADEMICO + "," + UPDATED_NIVEL_ACADEMICO);

        // Get all the perfilUsuarioList where nivelAcademico equals to UPDATED_NIVEL_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("nivelAcademico.in=" + UPDATED_NIVEL_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByNivelAcademicoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where nivelAcademico is not null
        defaultPerfilUsuarioShouldBeFound("nivelAcademico.specified=true");

        // Get all the perfilUsuarioList where nivelAcademico is null
        defaultPerfilUsuarioShouldNotBeFound("nivelAcademico.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaAcademicaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where areaAcademica equals to DEFAULT_AREA_ACADEMICA
        defaultPerfilUsuarioShouldBeFound("areaAcademica.equals=" + DEFAULT_AREA_ACADEMICA);

        // Get all the perfilUsuarioList where areaAcademica equals to UPDATED_AREA_ACADEMICA
        defaultPerfilUsuarioShouldNotBeFound("areaAcademica.equals=" + UPDATED_AREA_ACADEMICA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaAcademicaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where areaAcademica in DEFAULT_AREA_ACADEMICA or UPDATED_AREA_ACADEMICA
        defaultPerfilUsuarioShouldBeFound("areaAcademica.in=" + DEFAULT_AREA_ACADEMICA + "," + UPDATED_AREA_ACADEMICA);

        // Get all the perfilUsuarioList where areaAcademica equals to UPDATED_AREA_ACADEMICA
        defaultPerfilUsuarioShouldNotBeFound("areaAcademica.in=" + UPDATED_AREA_ACADEMICA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaAcademicaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where areaAcademica is not null
        defaultPerfilUsuarioShouldBeFound("areaAcademica.specified=true");

        // Get all the perfilUsuarioList where areaAcademica is null
        defaultPerfilUsuarioShouldNotBeFound("areaAcademica.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTerminoAcademicoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where terminoAcademico equals to DEFAULT_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("terminoAcademico.equals=" + DEFAULT_TERMINO_ACADEMICO);

        // Get all the perfilUsuarioList where terminoAcademico equals to UPDATED_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("terminoAcademico.equals=" + UPDATED_TERMINO_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTerminoAcademicoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where terminoAcademico in DEFAULT_TERMINO_ACADEMICO or UPDATED_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("terminoAcademico.in=" + DEFAULT_TERMINO_ACADEMICO + "," + UPDATED_TERMINO_ACADEMICO);

        // Get all the perfilUsuarioList where terminoAcademico equals to UPDATED_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("terminoAcademico.in=" + UPDATED_TERMINO_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTerminoAcademicoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where terminoAcademico is not null
        defaultPerfilUsuarioShouldBeFound("terminoAcademico.specified=true");

        // Get all the perfilUsuarioList where terminoAcademico is null
        defaultPerfilUsuarioShouldNotBeFound("terminoAcademico.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTerminoAcademicoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where terminoAcademico greater than or equals to DEFAULT_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("terminoAcademico.greaterOrEqualThan=" + DEFAULT_TERMINO_ACADEMICO);

        // Get all the perfilUsuarioList where terminoAcademico greater than or equals to UPDATED_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("terminoAcademico.greaterOrEqualThan=" + UPDATED_TERMINO_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTerminoAcademicoIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where terminoAcademico less than or equals to DEFAULT_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("terminoAcademico.lessThan=" + DEFAULT_TERMINO_ACADEMICO);

        // Get all the perfilUsuarioList where terminoAcademico less than or equals to UPDATED_TERMINO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("terminoAcademico.lessThan=" + UPDATED_TERMINO_ACADEMICO);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeICFESIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeICFES equals to DEFAULT_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldBeFound("puntajeICFES.equals=" + DEFAULT_PUNTAJE_ICFES);

        // Get all the perfilUsuarioList where puntajeICFES equals to UPDATED_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldNotBeFound("puntajeICFES.equals=" + UPDATED_PUNTAJE_ICFES);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeICFESIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeICFES in DEFAULT_PUNTAJE_ICFES or UPDATED_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldBeFound("puntajeICFES.in=" + DEFAULT_PUNTAJE_ICFES + "," + UPDATED_PUNTAJE_ICFES);

        // Get all the perfilUsuarioList where puntajeICFES equals to UPDATED_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldNotBeFound("puntajeICFES.in=" + UPDATED_PUNTAJE_ICFES);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeICFESIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeICFES is not null
        defaultPerfilUsuarioShouldBeFound("puntajeICFES.specified=true");

        // Get all the perfilUsuarioList where puntajeICFES is null
        defaultPerfilUsuarioShouldNotBeFound("puntajeICFES.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeICFESIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeICFES greater than or equals to DEFAULT_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldBeFound("puntajeICFES.greaterOrEqualThan=" + DEFAULT_PUNTAJE_ICFES);

        // Get all the perfilUsuarioList where puntajeICFES greater than or equals to UPDATED_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldNotBeFound("puntajeICFES.greaterOrEqualThan=" + UPDATED_PUNTAJE_ICFES);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeICFESIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeICFES less than or equals to DEFAULT_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldNotBeFound("puntajeICFES.lessThan=" + DEFAULT_PUNTAJE_ICFES);

        // Get all the perfilUsuarioList where puntajeICFES less than or equals to UPDATED_PUNTAJE_ICFES
        defaultPerfilUsuarioShouldBeFound("puntajeICFES.lessThan=" + UPDATED_PUNTAJE_ICFES);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByPromedioAcademicoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where promedioAcademico equals to DEFAULT_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("promedioAcademico.equals=" + DEFAULT_PROMEDIO_ACADEMICO);

        // Get all the perfilUsuarioList where promedioAcademico equals to UPDATED_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("promedioAcademico.equals=" + UPDATED_PROMEDIO_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPromedioAcademicoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where promedioAcademico in DEFAULT_PROMEDIO_ACADEMICO or UPDATED_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("promedioAcademico.in=" + DEFAULT_PROMEDIO_ACADEMICO + "," + UPDATED_PROMEDIO_ACADEMICO);

        // Get all the perfilUsuarioList where promedioAcademico equals to UPDATED_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("promedioAcademico.in=" + UPDATED_PROMEDIO_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPromedioAcademicoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where promedioAcademico is not null
        defaultPerfilUsuarioShouldBeFound("promedioAcademico.specified=true");

        // Get all the perfilUsuarioList where promedioAcademico is null
        defaultPerfilUsuarioShouldNotBeFound("promedioAcademico.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPromedioAcademicoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where promedioAcademico greater than or equals to DEFAULT_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("promedioAcademico.greaterOrEqualThan=" + DEFAULT_PROMEDIO_ACADEMICO);

        // Get all the perfilUsuarioList where promedioAcademico greater than or equals to UPDATED_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("promedioAcademico.greaterOrEqualThan=" + UPDATED_PROMEDIO_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPromedioAcademicoIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where promedioAcademico less than or equals to DEFAULT_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldNotBeFound("promedioAcademico.lessThan=" + DEFAULT_PROMEDIO_ACADEMICO);

        // Get all the perfilUsuarioList where promedioAcademico less than or equals to UPDATED_PROMEDIO_ACADEMICO
        defaultPerfilUsuarioShouldBeFound("promedioAcademico.lessThan=" + UPDATED_PROMEDIO_ACADEMICO);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByDominioIdiomaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where dominioIdioma equals to DEFAULT_DOMINIO_IDIOMA
        defaultPerfilUsuarioShouldBeFound("dominioIdioma.equals=" + DEFAULT_DOMINIO_IDIOMA);

        // Get all the perfilUsuarioList where dominioIdioma equals to UPDATED_DOMINIO_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("dominioIdioma.equals=" + UPDATED_DOMINIO_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByDominioIdiomaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where dominioIdioma in DEFAULT_DOMINIO_IDIOMA or UPDATED_DOMINIO_IDIOMA
        defaultPerfilUsuarioShouldBeFound("dominioIdioma.in=" + DEFAULT_DOMINIO_IDIOMA + "," + UPDATED_DOMINIO_IDIOMA);

        // Get all the perfilUsuarioList where dominioIdioma equals to UPDATED_DOMINIO_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("dominioIdioma.in=" + UPDATED_DOMINIO_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByDominioIdiomaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where dominioIdioma is not null
        defaultPerfilUsuarioShouldBeFound("dominioIdioma.specified=true");

        // Get all the perfilUsuarioList where dominioIdioma is null
        defaultPerfilUsuarioShouldNotBeFound("dominioIdioma.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdiomasIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where idiomas equals to DEFAULT_IDIOMAS
        defaultPerfilUsuarioShouldBeFound("idiomas.equals=" + DEFAULT_IDIOMAS);

        // Get all the perfilUsuarioList where idiomas equals to UPDATED_IDIOMAS
        defaultPerfilUsuarioShouldNotBeFound("idiomas.equals=" + UPDATED_IDIOMAS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdiomasIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where idiomas in DEFAULT_IDIOMAS or UPDATED_IDIOMAS
        defaultPerfilUsuarioShouldBeFound("idiomas.in=" + DEFAULT_IDIOMAS + "," + UPDATED_IDIOMAS);

        // Get all the perfilUsuarioList where idiomas equals to UPDATED_IDIOMAS
        defaultPerfilUsuarioShouldNotBeFound("idiomas.in=" + UPDATED_IDIOMAS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByIdiomasIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where idiomas is not null
        defaultPerfilUsuarioShouldBeFound("idiomas.specified=true");

        // Get all the perfilUsuarioList where idiomas is null
        defaultPerfilUsuarioShouldNotBeFound("idiomas.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExamenIdiomaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where examenIdioma equals to DEFAULT_EXAMEN_IDIOMA
        defaultPerfilUsuarioShouldBeFound("examenIdioma.equals=" + DEFAULT_EXAMEN_IDIOMA);

        // Get all the perfilUsuarioList where examenIdioma equals to UPDATED_EXAMEN_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("examenIdioma.equals=" + UPDATED_EXAMEN_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExamenIdiomaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where examenIdioma in DEFAULT_EXAMEN_IDIOMA or UPDATED_EXAMEN_IDIOMA
        defaultPerfilUsuarioShouldBeFound("examenIdioma.in=" + DEFAULT_EXAMEN_IDIOMA + "," + UPDATED_EXAMEN_IDIOMA);

        // Get all the perfilUsuarioList where examenIdioma equals to UPDATED_EXAMEN_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("examenIdioma.in=" + UPDATED_EXAMEN_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExamenIdiomaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where examenIdioma is not null
        defaultPerfilUsuarioShouldBeFound("examenIdioma.specified=true");

        // Get all the perfilUsuarioList where examenIdioma is null
        defaultPerfilUsuarioShouldNotBeFound("examenIdioma.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExamenRealizadoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where examenRealizado equals to DEFAULT_EXAMEN_REALIZADO
        defaultPerfilUsuarioShouldBeFound("examenRealizado.equals=" + DEFAULT_EXAMEN_REALIZADO);

        // Get all the perfilUsuarioList where examenRealizado equals to UPDATED_EXAMEN_REALIZADO
        defaultPerfilUsuarioShouldNotBeFound("examenRealizado.equals=" + UPDATED_EXAMEN_REALIZADO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExamenRealizadoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where examenRealizado in DEFAULT_EXAMEN_REALIZADO or UPDATED_EXAMEN_REALIZADO
        defaultPerfilUsuarioShouldBeFound("examenRealizado.in=" + DEFAULT_EXAMEN_REALIZADO + "," + UPDATED_EXAMEN_REALIZADO);

        // Get all the perfilUsuarioList where examenRealizado equals to UPDATED_EXAMEN_REALIZADO
        defaultPerfilUsuarioShouldNotBeFound("examenRealizado.in=" + UPDATED_EXAMEN_REALIZADO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExamenRealizadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where examenRealizado is not null
        defaultPerfilUsuarioShouldBeFound("examenRealizado.specified=true");

        // Get all the perfilUsuarioList where examenRealizado is null
        defaultPerfilUsuarioShouldNotBeFound("examenRealizado.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeIdiomaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeIdioma equals to DEFAULT_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldBeFound("puntajeIdioma.equals=" + DEFAULT_PUNTAJE_IDIOMA);

        // Get all the perfilUsuarioList where puntajeIdioma equals to UPDATED_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("puntajeIdioma.equals=" + UPDATED_PUNTAJE_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeIdiomaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeIdioma in DEFAULT_PUNTAJE_IDIOMA or UPDATED_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldBeFound("puntajeIdioma.in=" + DEFAULT_PUNTAJE_IDIOMA + "," + UPDATED_PUNTAJE_IDIOMA);

        // Get all the perfilUsuarioList where puntajeIdioma equals to UPDATED_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("puntajeIdioma.in=" + UPDATED_PUNTAJE_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeIdiomaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeIdioma is not null
        defaultPerfilUsuarioShouldBeFound("puntajeIdioma.specified=true");

        // Get all the perfilUsuarioList where puntajeIdioma is null
        defaultPerfilUsuarioShouldNotBeFound("puntajeIdioma.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeIdiomaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeIdioma greater than or equals to DEFAULT_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldBeFound("puntajeIdioma.greaterOrEqualThan=" + DEFAULT_PUNTAJE_IDIOMA);

        // Get all the perfilUsuarioList where puntajeIdioma greater than or equals to UPDATED_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("puntajeIdioma.greaterOrEqualThan=" + UPDATED_PUNTAJE_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPuntajeIdiomaIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where puntajeIdioma less than or equals to DEFAULT_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldNotBeFound("puntajeIdioma.lessThan=" + DEFAULT_PUNTAJE_IDIOMA);

        // Get all the perfilUsuarioList where puntajeIdioma less than or equals to UPDATED_PUNTAJE_IDIOMA
        defaultPerfilUsuarioShouldBeFound("puntajeIdioma.lessThan=" + UPDATED_PUNTAJE_IDIOMA);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByBecaOtorgadaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where becaOtorgada equals to DEFAULT_BECA_OTORGADA
        defaultPerfilUsuarioShouldBeFound("becaOtorgada.equals=" + DEFAULT_BECA_OTORGADA);

        // Get all the perfilUsuarioList where becaOtorgada equals to UPDATED_BECA_OTORGADA
        defaultPerfilUsuarioShouldNotBeFound("becaOtorgada.equals=" + UPDATED_BECA_OTORGADA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByBecaOtorgadaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where becaOtorgada in DEFAULT_BECA_OTORGADA or UPDATED_BECA_OTORGADA
        defaultPerfilUsuarioShouldBeFound("becaOtorgada.in=" + DEFAULT_BECA_OTORGADA + "," + UPDATED_BECA_OTORGADA);

        // Get all the perfilUsuarioList where becaOtorgada equals to UPDATED_BECA_OTORGADA
        defaultPerfilUsuarioShouldNotBeFound("becaOtorgada.in=" + UPDATED_BECA_OTORGADA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByBecaOtorgadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where becaOtorgada is not null
        defaultPerfilUsuarioShouldBeFound("becaOtorgada.specified=true");

        // Get all the perfilUsuarioList where becaOtorgada is null
        defaultPerfilUsuarioShouldNotBeFound("becaOtorgada.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTipoBecaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where tipoBeca equals to DEFAULT_TIPO_BECA
        defaultPerfilUsuarioShouldBeFound("tipoBeca.equals=" + DEFAULT_TIPO_BECA);

        // Get all the perfilUsuarioList where tipoBeca equals to UPDATED_TIPO_BECA
        defaultPerfilUsuarioShouldNotBeFound("tipoBeca.equals=" + UPDATED_TIPO_BECA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTipoBecaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where tipoBeca in DEFAULT_TIPO_BECA or UPDATED_TIPO_BECA
        defaultPerfilUsuarioShouldBeFound("tipoBeca.in=" + DEFAULT_TIPO_BECA + "," + UPDATED_TIPO_BECA);

        // Get all the perfilUsuarioList where tipoBeca equals to UPDATED_TIPO_BECA
        defaultPerfilUsuarioShouldNotBeFound("tipoBeca.in=" + UPDATED_TIPO_BECA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTipoBecaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where tipoBeca is not null
        defaultPerfilUsuarioShouldBeFound("tipoBeca.specified=true");

        // Get all the perfilUsuarioList where tipoBeca is null
        defaultPerfilUsuarioShouldNotBeFound("tipoBeca.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTipoBecaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where tipoBeca greater than or equals to DEFAULT_TIPO_BECA
        defaultPerfilUsuarioShouldBeFound("tipoBeca.greaterOrEqualThan=" + DEFAULT_TIPO_BECA);

        // Get all the perfilUsuarioList where tipoBeca greater than or equals to UPDATED_TIPO_BECA
        defaultPerfilUsuarioShouldNotBeFound("tipoBeca.greaterOrEqualThan=" + UPDATED_TIPO_BECA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByTipoBecaIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where tipoBeca less than or equals to DEFAULT_TIPO_BECA
        defaultPerfilUsuarioShouldNotBeFound("tipoBeca.lessThan=" + DEFAULT_TIPO_BECA);

        // Get all the perfilUsuarioList where tipoBeca less than or equals to UPDATED_TIPO_BECA
        defaultPerfilUsuarioShouldBeFound("tipoBeca.lessThan=" + UPDATED_TIPO_BECA);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByBecaInstitucionIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where becaInstitucion equals to DEFAULT_BECA_INSTITUCION
        defaultPerfilUsuarioShouldBeFound("becaInstitucion.equals=" + DEFAULT_BECA_INSTITUCION);

        // Get all the perfilUsuarioList where becaInstitucion equals to UPDATED_BECA_INSTITUCION
        defaultPerfilUsuarioShouldNotBeFound("becaInstitucion.equals=" + UPDATED_BECA_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByBecaInstitucionIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where becaInstitucion in DEFAULT_BECA_INSTITUCION or UPDATED_BECA_INSTITUCION
        defaultPerfilUsuarioShouldBeFound("becaInstitucion.in=" + DEFAULT_BECA_INSTITUCION + "," + UPDATED_BECA_INSTITUCION);

        // Get all the perfilUsuarioList where becaInstitucion equals to UPDATED_BECA_INSTITUCION
        defaultPerfilUsuarioShouldNotBeFound("becaInstitucion.in=" + UPDATED_BECA_INSTITUCION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByBecaInstitucionIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where becaInstitucion is not null
        defaultPerfilUsuarioShouldBeFound("becaInstitucion.specified=true");

        // Get all the perfilUsuarioList where becaInstitucion is null
        defaultPerfilUsuarioShouldNotBeFound("becaInstitucion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByGrupoSocialIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where grupoSocial equals to DEFAULT_GRUPO_SOCIAL
        defaultPerfilUsuarioShouldBeFound("grupoSocial.equals=" + DEFAULT_GRUPO_SOCIAL);

        // Get all the perfilUsuarioList where grupoSocial equals to UPDATED_GRUPO_SOCIAL
        defaultPerfilUsuarioShouldNotBeFound("grupoSocial.equals=" + UPDATED_GRUPO_SOCIAL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByGrupoSocialIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where grupoSocial in DEFAULT_GRUPO_SOCIAL or UPDATED_GRUPO_SOCIAL
        defaultPerfilUsuarioShouldBeFound("grupoSocial.in=" + DEFAULT_GRUPO_SOCIAL + "," + UPDATED_GRUPO_SOCIAL);

        // Get all the perfilUsuarioList where grupoSocial equals to UPDATED_GRUPO_SOCIAL
        defaultPerfilUsuarioShouldNotBeFound("grupoSocial.in=" + UPDATED_GRUPO_SOCIAL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByGrupoSocialIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where grupoSocial is not null
        defaultPerfilUsuarioShouldBeFound("grupoSocial.specified=true");

        // Get all the perfilUsuarioList where grupoSocial is null
        defaultPerfilUsuarioShouldNotBeFound("grupoSocial.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFundacionIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fundacion equals to DEFAULT_FUNDACION
        defaultPerfilUsuarioShouldBeFound("fundacion.equals=" + DEFAULT_FUNDACION);

        // Get all the perfilUsuarioList where fundacion equals to UPDATED_FUNDACION
        defaultPerfilUsuarioShouldNotBeFound("fundacion.equals=" + UPDATED_FUNDACION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFundacionIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fundacion in DEFAULT_FUNDACION or UPDATED_FUNDACION
        defaultPerfilUsuarioShouldBeFound("fundacion.in=" + DEFAULT_FUNDACION + "," + UPDATED_FUNDACION);

        // Get all the perfilUsuarioList where fundacion equals to UPDATED_FUNDACION
        defaultPerfilUsuarioShouldNotBeFound("fundacion.in=" + UPDATED_FUNDACION);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFundacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fundacion is not null
        defaultPerfilUsuarioShouldBeFound("fundacion.specified=true");

        // Get all the perfilUsuarioList where fundacion is null
        defaultPerfilUsuarioShouldNotBeFound("fundacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMonitorIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where monitor equals to DEFAULT_MONITOR
        defaultPerfilUsuarioShouldBeFound("monitor.equals=" + DEFAULT_MONITOR);

        // Get all the perfilUsuarioList where monitor equals to UPDATED_MONITOR
        defaultPerfilUsuarioShouldNotBeFound("monitor.equals=" + UPDATED_MONITOR);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMonitorIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where monitor in DEFAULT_MONITOR or UPDATED_MONITOR
        defaultPerfilUsuarioShouldBeFound("monitor.in=" + DEFAULT_MONITOR + "," + UPDATED_MONITOR);

        // Get all the perfilUsuarioList where monitor equals to UPDATED_MONITOR
        defaultPerfilUsuarioShouldNotBeFound("monitor.in=" + UPDATED_MONITOR);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMonitorIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where monitor is not null
        defaultPerfilUsuarioShouldBeFound("monitor.specified=true");

        // Get all the perfilUsuarioList where monitor is null
        defaultPerfilUsuarioShouldNotBeFound("monitor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMonitorMateriaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where monitorMateria equals to DEFAULT_MONITOR_MATERIA
        defaultPerfilUsuarioShouldBeFound("monitorMateria.equals=" + DEFAULT_MONITOR_MATERIA);

        // Get all the perfilUsuarioList where monitorMateria equals to UPDATED_MONITOR_MATERIA
        defaultPerfilUsuarioShouldNotBeFound("monitorMateria.equals=" + UPDATED_MONITOR_MATERIA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMonitorMateriaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where monitorMateria in DEFAULT_MONITOR_MATERIA or UPDATED_MONITOR_MATERIA
        defaultPerfilUsuarioShouldBeFound("monitorMateria.in=" + DEFAULT_MONITOR_MATERIA + "," + UPDATED_MONITOR_MATERIA);

        // Get all the perfilUsuarioList where monitorMateria equals to UPDATED_MONITOR_MATERIA
        defaultPerfilUsuarioShouldNotBeFound("monitorMateria.in=" + UPDATED_MONITOR_MATERIA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMonitorMateriaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where monitorMateria is not null
        defaultPerfilUsuarioShouldBeFound("monitorMateria.specified=true");

        // Get all the perfilUsuarioList where monitorMateria is null
        defaultPerfilUsuarioShouldNotBeFound("monitorMateria.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByLogrosAcademicosIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where logrosAcademicos equals to DEFAULT_LOGROS_ACADEMICOS
        defaultPerfilUsuarioShouldBeFound("logrosAcademicos.equals=" + DEFAULT_LOGROS_ACADEMICOS);

        // Get all the perfilUsuarioList where logrosAcademicos equals to UPDATED_LOGROS_ACADEMICOS
        defaultPerfilUsuarioShouldNotBeFound("logrosAcademicos.equals=" + UPDATED_LOGROS_ACADEMICOS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByLogrosAcademicosIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where logrosAcademicos in DEFAULT_LOGROS_ACADEMICOS or UPDATED_LOGROS_ACADEMICOS
        defaultPerfilUsuarioShouldBeFound("logrosAcademicos.in=" + DEFAULT_LOGROS_ACADEMICOS + "," + UPDATED_LOGROS_ACADEMICOS);

        // Get all the perfilUsuarioList where logrosAcademicos equals to UPDATED_LOGROS_ACADEMICOS
        defaultPerfilUsuarioShouldNotBeFound("logrosAcademicos.in=" + UPDATED_LOGROS_ACADEMICOS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByLogrosAcademicosIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where logrosAcademicos is not null
        defaultPerfilUsuarioShouldBeFound("logrosAcademicos.specified=true");

        // Get all the perfilUsuarioList where logrosAcademicos is null
        defaultPerfilUsuarioShouldNotBeFound("logrosAcademicos.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExperienciaLaboralIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where experienciaLaboral equals to DEFAULT_EXPERIENCIA_LABORAL
        defaultPerfilUsuarioShouldBeFound("experienciaLaboral.equals=" + DEFAULT_EXPERIENCIA_LABORAL);

        // Get all the perfilUsuarioList where experienciaLaboral equals to UPDATED_EXPERIENCIA_LABORAL
        defaultPerfilUsuarioShouldNotBeFound("experienciaLaboral.equals=" + UPDATED_EXPERIENCIA_LABORAL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExperienciaLaboralIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where experienciaLaboral in DEFAULT_EXPERIENCIA_LABORAL or UPDATED_EXPERIENCIA_LABORAL
        defaultPerfilUsuarioShouldBeFound("experienciaLaboral.in=" + DEFAULT_EXPERIENCIA_LABORAL + "," + UPDATED_EXPERIENCIA_LABORAL);

        // Get all the perfilUsuarioList where experienciaLaboral equals to UPDATED_EXPERIENCIA_LABORAL
        defaultPerfilUsuarioShouldNotBeFound("experienciaLaboral.in=" + UPDATED_EXPERIENCIA_LABORAL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByExperienciaLaboralIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where experienciaLaboral is not null
        defaultPerfilUsuarioShouldBeFound("experienciaLaboral.specified=true");

        // Get all the perfilUsuarioList where experienciaLaboral is null
        defaultPerfilUsuarioShouldNotBeFound("experienciaLaboral.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaLaboralIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where areaLaboral equals to DEFAULT_AREA_LABORAL
        defaultPerfilUsuarioShouldBeFound("areaLaboral.equals=" + DEFAULT_AREA_LABORAL);

        // Get all the perfilUsuarioList where areaLaboral equals to UPDATED_AREA_LABORAL
        defaultPerfilUsuarioShouldNotBeFound("areaLaboral.equals=" + UPDATED_AREA_LABORAL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaLaboralIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where areaLaboral in DEFAULT_AREA_LABORAL or UPDATED_AREA_LABORAL
        defaultPerfilUsuarioShouldBeFound("areaLaboral.in=" + DEFAULT_AREA_LABORAL + "," + UPDATED_AREA_LABORAL);

        // Get all the perfilUsuarioList where areaLaboral equals to UPDATED_AREA_LABORAL
        defaultPerfilUsuarioShouldNotBeFound("areaLaboral.in=" + UPDATED_AREA_LABORAL);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByAreaLaboralIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where areaLaboral is not null
        defaultPerfilUsuarioShouldBeFound("areaLaboral.specified=true");

        // Get all the perfilUsuarioList where areaLaboral is null
        defaultPerfilUsuarioShouldNotBeFound("areaLaboral.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramarealizarIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programarealizar equals to DEFAULT_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldBeFound("programarealizar.equals=" + DEFAULT_PROGRAMAREALIZAR);

        // Get all the perfilUsuarioList where programarealizar equals to UPDATED_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldNotBeFound("programarealizar.equals=" + UPDATED_PROGRAMAREALIZAR);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramarealizarIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programarealizar in DEFAULT_PROGRAMAREALIZAR or UPDATED_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldBeFound("programarealizar.in=" + DEFAULT_PROGRAMAREALIZAR + "," + UPDATED_PROGRAMAREALIZAR);

        // Get all the perfilUsuarioList where programarealizar equals to UPDATED_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldNotBeFound("programarealizar.in=" + UPDATED_PROGRAMAREALIZAR);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramarealizarIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programarealizar is not null
        defaultPerfilUsuarioShouldBeFound("programarealizar.specified=true");

        // Get all the perfilUsuarioList where programarealizar is null
        defaultPerfilUsuarioShouldNotBeFound("programarealizar.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramarealizarIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programarealizar greater than or equals to DEFAULT_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldBeFound("programarealizar.greaterOrEqualThan=" + DEFAULT_PROGRAMAREALIZAR);

        // Get all the perfilUsuarioList where programarealizar greater than or equals to UPDATED_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldNotBeFound("programarealizar.greaterOrEqualThan=" + UPDATED_PROGRAMAREALIZAR);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramarealizarIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programarealizar less than or equals to DEFAULT_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldNotBeFound("programarealizar.lessThan=" + DEFAULT_PROGRAMAREALIZAR);

        // Get all the perfilUsuarioList where programarealizar less than or equals to UPDATED_PROGRAMAREALIZAR
        defaultPerfilUsuarioShouldBeFound("programarealizar.lessThan=" + UPDATED_PROGRAMAREALIZAR);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramaAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programaArea equals to DEFAULT_PROGRAMA_AREA
        defaultPerfilUsuarioShouldBeFound("programaArea.equals=" + DEFAULT_PROGRAMA_AREA);

        // Get all the perfilUsuarioList where programaArea equals to UPDATED_PROGRAMA_AREA
        defaultPerfilUsuarioShouldNotBeFound("programaArea.equals=" + UPDATED_PROGRAMA_AREA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramaAreaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programaArea in DEFAULT_PROGRAMA_AREA or UPDATED_PROGRAMA_AREA
        defaultPerfilUsuarioShouldBeFound("programaArea.in=" + DEFAULT_PROGRAMA_AREA + "," + UPDATED_PROGRAMA_AREA);

        // Get all the perfilUsuarioList where programaArea equals to UPDATED_PROGRAMA_AREA
        defaultPerfilUsuarioShouldNotBeFound("programaArea.in=" + UPDATED_PROGRAMA_AREA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramaAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programaArea is not null
        defaultPerfilUsuarioShouldBeFound("programaArea.specified=true");

        // Get all the perfilUsuarioList where programaArea is null
        defaultPerfilUsuarioShouldNotBeFound("programaArea.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaInicio equals to DEFAULT_FECHA_INICIO
        defaultPerfilUsuarioShouldBeFound("fechaInicio.equals=" + DEFAULT_FECHA_INICIO);

        // Get all the perfilUsuarioList where fechaInicio equals to UPDATED_FECHA_INICIO
        defaultPerfilUsuarioShouldNotBeFound("fechaInicio.equals=" + UPDATED_FECHA_INICIO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaInicioIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaInicio in DEFAULT_FECHA_INICIO or UPDATED_FECHA_INICIO
        defaultPerfilUsuarioShouldBeFound("fechaInicio.in=" + DEFAULT_FECHA_INICIO + "," + UPDATED_FECHA_INICIO);

        // Get all the perfilUsuarioList where fechaInicio equals to UPDATED_FECHA_INICIO
        defaultPerfilUsuarioShouldNotBeFound("fechaInicio.in=" + UPDATED_FECHA_INICIO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaInicioIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaInicio is not null
        defaultPerfilUsuarioShouldBeFound("fechaInicio.specified=true");

        // Get all the perfilUsuarioList where fechaInicio is null
        defaultPerfilUsuarioShouldNotBeFound("fechaInicio.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaInicioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaInicio greater than or equals to DEFAULT_FECHA_INICIO
        defaultPerfilUsuarioShouldBeFound("fechaInicio.greaterOrEqualThan=" + DEFAULT_FECHA_INICIO);

        // Get all the perfilUsuarioList where fechaInicio greater than or equals to UPDATED_FECHA_INICIO
        defaultPerfilUsuarioShouldNotBeFound("fechaInicio.greaterOrEqualThan=" + UPDATED_FECHA_INICIO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByFechaInicioIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where fechaInicio less than or equals to DEFAULT_FECHA_INICIO
        defaultPerfilUsuarioShouldNotBeFound("fechaInicio.lessThan=" + DEFAULT_FECHA_INICIO);

        // Get all the perfilUsuarioList where fechaInicio less than or equals to UPDATED_FECHA_INICIO
        defaultPerfilUsuarioShouldBeFound("fechaInicio.lessThan=" + UPDATED_FECHA_INICIO);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramaEncontradoIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programaEncontrado equals to DEFAULT_PROGRAMA_ENCONTRADO
        defaultPerfilUsuarioShouldBeFound("programaEncontrado.equals=" + DEFAULT_PROGRAMA_ENCONTRADO);

        // Get all the perfilUsuarioList where programaEncontrado equals to UPDATED_PROGRAMA_ENCONTRADO
        defaultPerfilUsuarioShouldNotBeFound("programaEncontrado.equals=" + UPDATED_PROGRAMA_ENCONTRADO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramaEncontradoIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programaEncontrado in DEFAULT_PROGRAMA_ENCONTRADO or UPDATED_PROGRAMA_ENCONTRADO
        defaultPerfilUsuarioShouldBeFound("programaEncontrado.in=" + DEFAULT_PROGRAMA_ENCONTRADO + "," + UPDATED_PROGRAMA_ENCONTRADO);

        // Get all the perfilUsuarioList where programaEncontrado equals to UPDATED_PROGRAMA_ENCONTRADO
        defaultPerfilUsuarioShouldNotBeFound("programaEncontrado.in=" + UPDATED_PROGRAMA_ENCONTRADO);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByProgramaEncontradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where programaEncontrado is not null
        defaultPerfilUsuarioShouldBeFound("programaEncontrado.specified=true");

        // Get all the perfilUsuarioList where programaEncontrado is null
        defaultPerfilUsuarioShouldNotBeFound("programaEncontrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByNombreProgramaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where nombrePrograma equals to DEFAULT_NOMBRE_PROGRAMA
        defaultPerfilUsuarioShouldBeFound("nombrePrograma.equals=" + DEFAULT_NOMBRE_PROGRAMA);

        // Get all the perfilUsuarioList where nombrePrograma equals to UPDATED_NOMBRE_PROGRAMA
        defaultPerfilUsuarioShouldNotBeFound("nombrePrograma.equals=" + UPDATED_NOMBRE_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByNombreProgramaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where nombrePrograma in DEFAULT_NOMBRE_PROGRAMA or UPDATED_NOMBRE_PROGRAMA
        defaultPerfilUsuarioShouldBeFound("nombrePrograma.in=" + DEFAULT_NOMBRE_PROGRAMA + "," + UPDATED_NOMBRE_PROGRAMA);

        // Get all the perfilUsuarioList where nombrePrograma equals to UPDATED_NOMBRE_PROGRAMA
        defaultPerfilUsuarioShouldNotBeFound("nombrePrograma.in=" + UPDATED_NOMBRE_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByNombreProgramaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where nombrePrograma is not null
        defaultPerfilUsuarioShouldBeFound("nombrePrograma.specified=true");

        // Get all the perfilUsuarioList where nombrePrograma is null
        defaultPerfilUsuarioShouldNotBeFound("nombrePrograma.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByUniversidadIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where universidad equals to DEFAULT_UNIVERSIDAD
        defaultPerfilUsuarioShouldBeFound("universidad.equals=" + DEFAULT_UNIVERSIDAD);

        // Get all the perfilUsuarioList where universidad equals to UPDATED_UNIVERSIDAD
        defaultPerfilUsuarioShouldNotBeFound("universidad.equals=" + UPDATED_UNIVERSIDAD);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByUniversidadIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where universidad in DEFAULT_UNIVERSIDAD or UPDATED_UNIVERSIDAD
        defaultPerfilUsuarioShouldBeFound("universidad.in=" + DEFAULT_UNIVERSIDAD + "," + UPDATED_UNIVERSIDAD);

        // Get all the perfilUsuarioList where universidad equals to UPDATED_UNIVERSIDAD
        defaultPerfilUsuarioShouldNotBeFound("universidad.in=" + UPDATED_UNIVERSIDAD);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByUniversidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where universidad is not null
        defaultPerfilUsuarioShouldBeFound("universidad.specified=true");

        // Get all the perfilUsuarioList where universidad is null
        defaultPerfilUsuarioShouldNotBeFound("universidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where pais equals to DEFAULT_PAIS
        defaultPerfilUsuarioShouldBeFound("pais.equals=" + DEFAULT_PAIS);

        // Get all the perfilUsuarioList where pais equals to UPDATED_PAIS
        defaultPerfilUsuarioShouldNotBeFound("pais.equals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPaisIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where pais in DEFAULT_PAIS or UPDATED_PAIS
        defaultPerfilUsuarioShouldBeFound("pais.in=" + DEFAULT_PAIS + "," + UPDATED_PAIS);

        // Get all the perfilUsuarioList where pais equals to UPDATED_PAIS
        defaultPerfilUsuarioShouldNotBeFound("pais.in=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where pais is not null
        defaultPerfilUsuarioShouldBeFound("pais.specified=true");

        // Get all the perfilUsuarioList where pais is null
        defaultPerfilUsuarioShouldNotBeFound("pais.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPaisIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where pais greater than or equals to DEFAULT_PAIS
        defaultPerfilUsuarioShouldBeFound("pais.greaterOrEqualThan=" + DEFAULT_PAIS);

        // Get all the perfilUsuarioList where pais greater than or equals to UPDATED_PAIS
        defaultPerfilUsuarioShouldNotBeFound("pais.greaterOrEqualThan=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByPaisIsLessThanSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where pais less than or equals to DEFAULT_PAIS
        defaultPerfilUsuarioShouldNotBeFound("pais.lessThan=" + DEFAULT_PAIS);

        // Get all the perfilUsuarioList where pais less than or equals to UPDATED_PAIS
        defaultPerfilUsuarioShouldBeFound("pais.lessThan=" + UPDATED_PAIS);
    }


    @Test
    @Transactional
    public void getAllPerfilUsuariosByMerecedorBecaIsEqualToSomething() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where merecedorBeca equals to DEFAULT_MERECEDOR_BECA
        defaultPerfilUsuarioShouldBeFound("merecedorBeca.equals=" + DEFAULT_MERECEDOR_BECA);

        // Get all the perfilUsuarioList where merecedorBeca equals to UPDATED_MERECEDOR_BECA
        defaultPerfilUsuarioShouldNotBeFound("merecedorBeca.equals=" + UPDATED_MERECEDOR_BECA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMerecedorBecaIsInShouldWork() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where merecedorBeca in DEFAULT_MERECEDOR_BECA or UPDATED_MERECEDOR_BECA
        defaultPerfilUsuarioShouldBeFound("merecedorBeca.in=" + DEFAULT_MERECEDOR_BECA + "," + UPDATED_MERECEDOR_BECA);

        // Get all the perfilUsuarioList where merecedorBeca equals to UPDATED_MERECEDOR_BECA
        defaultPerfilUsuarioShouldNotBeFound("merecedorBeca.in=" + UPDATED_MERECEDOR_BECA);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByMerecedorBecaIsNullOrNotNull() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList where merecedorBeca is not null
        defaultPerfilUsuarioShouldBeFound("merecedorBeca.specified=true");

        // Get all the perfilUsuarioList where merecedorBeca is null
        defaultPerfilUsuarioShouldNotBeFound("merecedorBeca.specified=false");
    }

    @Test
    @Transactional
    public void getAllPerfilUsuariosByUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        User usuario = UserResourceIT.createEntity(em);
        em.persist(usuario);
        em.flush();
        perfilUsuario.setUsuario(usuario);
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);
        Long usuarioId = usuario.getId();

        // Get all the perfilUsuarioList where usuario equals to usuarioId
        defaultPerfilUsuarioShouldBeFound("usuarioId.equals=" + usuarioId);

        // Get all the perfilUsuarioList where usuario equals to usuarioId + 1
        defaultPerfilUsuarioShouldNotBeFound("usuarioId.equals=" + (usuarioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPerfilUsuarioShouldBeFound(String filter) throws Exception {
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO)))
            .andExpect(jsonPath("$.[*].areaAcademica").value(hasItem(DEFAULT_AREA_ACADEMICA)))
            .andExpect(jsonPath("$.[*].terminoAcademico").value(hasItem(DEFAULT_TERMINO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].puntajeICFES").value(hasItem(DEFAULT_PUNTAJE_ICFES)))
            .andExpect(jsonPath("$.[*].promedioAcademico").value(hasItem(DEFAULT_PROMEDIO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].dominioIdioma").value(hasItem(DEFAULT_DOMINIO_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].idiomas").value(hasItem(DEFAULT_IDIOMAS)))
            .andExpect(jsonPath("$.[*].examenIdioma").value(hasItem(DEFAULT_EXAMEN_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].examenRealizado").value(hasItem(DEFAULT_EXAMEN_REALIZADO)))
            .andExpect(jsonPath("$.[*].puntajeIdioma").value(hasItem(DEFAULT_PUNTAJE_IDIOMA)))
            .andExpect(jsonPath("$.[*].becaOtorgada").value(hasItem(DEFAULT_BECA_OTORGADA.booleanValue())))
            .andExpect(jsonPath("$.[*].tipoBeca").value(hasItem(DEFAULT_TIPO_BECA)))
            .andExpect(jsonPath("$.[*].becaInstitucion").value(hasItem(DEFAULT_BECA_INSTITUCION)))
            .andExpect(jsonPath("$.[*].grupoSocial").value(hasItem(DEFAULT_GRUPO_SOCIAL.booleanValue())))
            .andExpect(jsonPath("$.[*].fundacion").value(hasItem(DEFAULT_FUNDACION)))
            .andExpect(jsonPath("$.[*].monitor").value(hasItem(DEFAULT_MONITOR.booleanValue())))
            .andExpect(jsonPath("$.[*].monitorMateria").value(hasItem(DEFAULT_MONITOR_MATERIA)))
            .andExpect(jsonPath("$.[*].logrosAcademicos").value(hasItem(DEFAULT_LOGROS_ACADEMICOS)))
            .andExpect(jsonPath("$.[*].experienciaLaboral").value(hasItem(DEFAULT_EXPERIENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].areaLaboral").value(hasItem(DEFAULT_AREA_LABORAL)))
            .andExpect(jsonPath("$.[*].programarealizar").value(hasItem(DEFAULT_PROGRAMAREALIZAR)))
            .andExpect(jsonPath("$.[*].programaArea").value(hasItem(DEFAULT_PROGRAMA_AREA)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].programaEncontrado").value(hasItem(DEFAULT_PROGRAMA_ENCONTRADO.booleanValue())))
            .andExpect(jsonPath("$.[*].nombrePrograma").value(hasItem(DEFAULT_NOMBRE_PROGRAMA)))
            .andExpect(jsonPath("$.[*].universidad").value(hasItem(DEFAULT_UNIVERSIDAD)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].merecedorBeca").value(hasItem(DEFAULT_MERECEDOR_BECA)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));

        // Check, that the count call also returns 1
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPerfilUsuarioShouldNotBeFound(String filter) throws Exception {
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPerfilUsuario() throws Exception {
        // Get the perfilUsuario
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioService.save(perfilUsuario);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockPerfilUsuarioSearchRepository);

        int databaseSizeBeforeUpdate = perfilUsuarioRepository.findAll().size();

        // Update the perfilUsuario
        PerfilUsuario updatedPerfilUsuario = perfilUsuarioRepository.findById(perfilUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedPerfilUsuario are not directly saved in db
        em.detach(updatedPerfilUsuario);
        updatedPerfilUsuario
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .identificacion(UPDATED_IDENTIFICACION)
            .mail(UPDATED_MAIL)
            .area(UPDATED_AREA)
            .telefono(UPDATED_TELEFONO)
            .nivelAcademico(UPDATED_NIVEL_ACADEMICO)
            .areaAcademica(UPDATED_AREA_ACADEMICA)
            .terminoAcademico(UPDATED_TERMINO_ACADEMICO)
            .puntajeICFES(UPDATED_PUNTAJE_ICFES)
            .promedioAcademico(UPDATED_PROMEDIO_ACADEMICO)
            .dominioIdioma(UPDATED_DOMINIO_IDIOMA)
            .idiomas(UPDATED_IDIOMAS)
            .examenIdioma(UPDATED_EXAMEN_IDIOMA)
            .examenRealizado(UPDATED_EXAMEN_REALIZADO)
            .puntajeIdioma(UPDATED_PUNTAJE_IDIOMA)
            .becaOtorgada(UPDATED_BECA_OTORGADA)
            .tipoBeca(UPDATED_TIPO_BECA)
            .becaInstitucion(UPDATED_BECA_INSTITUCION)
            .grupoSocial(UPDATED_GRUPO_SOCIAL)
            .fundacion(UPDATED_FUNDACION)
            .monitor(UPDATED_MONITOR)
            .monitorMateria(UPDATED_MONITOR_MATERIA)
            .logrosAcademicos(UPDATED_LOGROS_ACADEMICOS)
            .experienciaLaboral(UPDATED_EXPERIENCIA_LABORAL)
            .areaLaboral(UPDATED_AREA_LABORAL)
            .programarealizar(UPDATED_PROGRAMAREALIZAR)
            .programaArea(UPDATED_PROGRAMA_AREA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .programaEncontrado(UPDATED_PROGRAMA_ENCONTRADO)
            .nombrePrograma(UPDATED_NOMBRE_PROGRAMA)
            .universidad(UPDATED_UNIVERSIDAD)
            .pais(UPDATED_PAIS)
            .merecedorBeca(UPDATED_MERECEDOR_BECA)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE);

        restPerfilUsuarioMockMvc.perform(put("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerfilUsuario)))
            .andExpect(status().isOk());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeUpdate);
        PerfilUsuario testPerfilUsuario = perfilUsuarioList.get(perfilUsuarioList.size() - 1);
        assertThat(testPerfilUsuario.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testPerfilUsuario.getIdentificacion()).isEqualTo(UPDATED_IDENTIFICACION);
        assertThat(testPerfilUsuario.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testPerfilUsuario.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testPerfilUsuario.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testPerfilUsuario.getNivelAcademico()).isEqualTo(UPDATED_NIVEL_ACADEMICO);
        assertThat(testPerfilUsuario.getAreaAcademica()).isEqualTo(UPDATED_AREA_ACADEMICA);
        assertThat(testPerfilUsuario.getTerminoAcademico()).isEqualTo(UPDATED_TERMINO_ACADEMICO);
        assertThat(testPerfilUsuario.getPuntajeICFES()).isEqualTo(UPDATED_PUNTAJE_ICFES);
        assertThat(testPerfilUsuario.getPromedioAcademico()).isEqualTo(UPDATED_PROMEDIO_ACADEMICO);
        assertThat(testPerfilUsuario.isDominioIdioma()).isEqualTo(UPDATED_DOMINIO_IDIOMA);
        assertThat(testPerfilUsuario.getIdiomas()).isEqualTo(UPDATED_IDIOMAS);
        assertThat(testPerfilUsuario.isExamenIdioma()).isEqualTo(UPDATED_EXAMEN_IDIOMA);
        assertThat(testPerfilUsuario.getExamenRealizado()).isEqualTo(UPDATED_EXAMEN_REALIZADO);
        assertThat(testPerfilUsuario.getPuntajeIdioma()).isEqualTo(UPDATED_PUNTAJE_IDIOMA);
        assertThat(testPerfilUsuario.isBecaOtorgada()).isEqualTo(UPDATED_BECA_OTORGADA);
        assertThat(testPerfilUsuario.getTipoBeca()).isEqualTo(UPDATED_TIPO_BECA);
        assertThat(testPerfilUsuario.getBecaInstitucion()).isEqualTo(UPDATED_BECA_INSTITUCION);
        assertThat(testPerfilUsuario.isGrupoSocial()).isEqualTo(UPDATED_GRUPO_SOCIAL);
        assertThat(testPerfilUsuario.getFundacion()).isEqualTo(UPDATED_FUNDACION);
        assertThat(testPerfilUsuario.isMonitor()).isEqualTo(UPDATED_MONITOR);
        assertThat(testPerfilUsuario.getMonitorMateria()).isEqualTo(UPDATED_MONITOR_MATERIA);
        assertThat(testPerfilUsuario.getLogrosAcademicos()).isEqualTo(UPDATED_LOGROS_ACADEMICOS);
        assertThat(testPerfilUsuario.isExperienciaLaboral()).isEqualTo(UPDATED_EXPERIENCIA_LABORAL);
        assertThat(testPerfilUsuario.getAreaLaboral()).isEqualTo(UPDATED_AREA_LABORAL);
        assertThat(testPerfilUsuario.getProgramarealizar()).isEqualTo(UPDATED_PROGRAMAREALIZAR);
        assertThat(testPerfilUsuario.getProgramaArea()).isEqualTo(UPDATED_PROGRAMA_AREA);
        assertThat(testPerfilUsuario.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testPerfilUsuario.isProgramaEncontrado()).isEqualTo(UPDATED_PROGRAMA_ENCONTRADO);
        assertThat(testPerfilUsuario.getNombrePrograma()).isEqualTo(UPDATED_NOMBRE_PROGRAMA);
        assertThat(testPerfilUsuario.getUniversidad()).isEqualTo(UPDATED_UNIVERSIDAD);
        assertThat(testPerfilUsuario.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testPerfilUsuario.getMerecedorBeca()).isEqualTo(UPDATED_MERECEDOR_BECA);
        assertThat(testPerfilUsuario.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testPerfilUsuario.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);

        // Validate the PerfilUsuario in Elasticsearch
        verify(mockPerfilUsuarioSearchRepository, times(1)).save(testPerfilUsuario);
    }

    @Test
    @Transactional
    public void updateNonExistingPerfilUsuario() throws Exception {
        int databaseSizeBeforeUpdate = perfilUsuarioRepository.findAll().size();

        // Create the PerfilUsuario

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerfilUsuarioMockMvc.perform(put("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilUsuario)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PerfilUsuario in Elasticsearch
        verify(mockPerfilUsuarioSearchRepository, times(0)).save(perfilUsuario);
    }

    @Test
    @Transactional
    public void deletePerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioService.save(perfilUsuario);

        int databaseSizeBeforeDelete = perfilUsuarioRepository.findAll().size();

        // Delete the perfilUsuario
        restPerfilUsuarioMockMvc.perform(delete("/api/perfil-usuarios/{id}", perfilUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PerfilUsuario in Elasticsearch
        verify(mockPerfilUsuarioSearchRepository, times(1)).deleteById(perfilUsuario.getId());
    }

    @Test
    @Transactional
    public void searchPerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioService.save(perfilUsuario);
        when(mockPerfilUsuarioSearchRepository.search(queryStringQuery("id:" + perfilUsuario.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(perfilUsuario), PageRequest.of(0, 1), 1));
        // Search the perfilUsuario
        restPerfilUsuarioMockMvc.perform(get("/api/_search/perfil-usuarios?query=id:" + perfilUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO)))
            .andExpect(jsonPath("$.[*].areaAcademica").value(hasItem(DEFAULT_AREA_ACADEMICA)))
            .andExpect(jsonPath("$.[*].terminoAcademico").value(hasItem(DEFAULT_TERMINO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].puntajeICFES").value(hasItem(DEFAULT_PUNTAJE_ICFES)))
            .andExpect(jsonPath("$.[*].promedioAcademico").value(hasItem(DEFAULT_PROMEDIO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].dominioIdioma").value(hasItem(DEFAULT_DOMINIO_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].idiomas").value(hasItem(DEFAULT_IDIOMAS)))
            .andExpect(jsonPath("$.[*].examenIdioma").value(hasItem(DEFAULT_EXAMEN_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].examenRealizado").value(hasItem(DEFAULT_EXAMEN_REALIZADO)))
            .andExpect(jsonPath("$.[*].puntajeIdioma").value(hasItem(DEFAULT_PUNTAJE_IDIOMA)))
            .andExpect(jsonPath("$.[*].becaOtorgada").value(hasItem(DEFAULT_BECA_OTORGADA.booleanValue())))
            .andExpect(jsonPath("$.[*].tipoBeca").value(hasItem(DEFAULT_TIPO_BECA)))
            .andExpect(jsonPath("$.[*].becaInstitucion").value(hasItem(DEFAULT_BECA_INSTITUCION)))
            .andExpect(jsonPath("$.[*].grupoSocial").value(hasItem(DEFAULT_GRUPO_SOCIAL.booleanValue())))
            .andExpect(jsonPath("$.[*].fundacion").value(hasItem(DEFAULT_FUNDACION)))
            .andExpect(jsonPath("$.[*].monitor").value(hasItem(DEFAULT_MONITOR.booleanValue())))
            .andExpect(jsonPath("$.[*].monitorMateria").value(hasItem(DEFAULT_MONITOR_MATERIA)))
            .andExpect(jsonPath("$.[*].logrosAcademicos").value(hasItem(DEFAULT_LOGROS_ACADEMICOS)))
            .andExpect(jsonPath("$.[*].experienciaLaboral").value(hasItem(DEFAULT_EXPERIENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].areaLaboral").value(hasItem(DEFAULT_AREA_LABORAL)))
            .andExpect(jsonPath("$.[*].programarealizar").value(hasItem(DEFAULT_PROGRAMAREALIZAR)))
            .andExpect(jsonPath("$.[*].programaArea").value(hasItem(DEFAULT_PROGRAMA_AREA)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].programaEncontrado").value(hasItem(DEFAULT_PROGRAMA_ENCONTRADO.booleanValue())))
            .andExpect(jsonPath("$.[*].nombrePrograma").value(hasItem(DEFAULT_NOMBRE_PROGRAMA)))
            .andExpect(jsonPath("$.[*].universidad").value(hasItem(DEFAULT_UNIVERSIDAD)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].merecedorBeca").value(hasItem(DEFAULT_MERECEDOR_BECA)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerfilUsuario.class);
        PerfilUsuario perfilUsuario1 = new PerfilUsuario();
        perfilUsuario1.setId(1L);
        PerfilUsuario perfilUsuario2 = new PerfilUsuario();
        perfilUsuario2.setId(perfilUsuario1.getId());
        assertThat(perfilUsuario1).isEqualTo(perfilUsuario2);
        perfilUsuario2.setId(2L);
        assertThat(perfilUsuario1).isNotEqualTo(perfilUsuario2);
        perfilUsuario1.setId(null);
        assertThat(perfilUsuario1).isNotEqualTo(perfilUsuario2);
    }
}
