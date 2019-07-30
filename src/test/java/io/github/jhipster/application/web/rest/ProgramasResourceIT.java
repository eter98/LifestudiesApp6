package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.domain.Institucion;
import io.github.jhipster.application.domain.Ciudad;
import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.repository.ProgramasRepository;
import io.github.jhipster.application.repository.search.ProgramasSearchRepository;
import io.github.jhipster.application.service.ProgramasService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ProgramasCriteria;
import io.github.jhipster.application.service.ProgramasQueryService;

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

import io.github.jhipster.application.domain.enumeration.Monedad;
/**
 * Integration tests for the {@Link ProgramasResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class ProgramasResourceIT {

    private static final Integer DEFAULT_REGISTRO = 1;
    private static final Integer UPDATED_REGISTRO = 2;

    private static final Monedad DEFAULT_MONEDA = Monedad.COP;
    private static final Monedad UPDATED_MONEDA = Monedad.USD;

    private static final String DEFAULT_CURSO = "AAAAAAAAAA";
    private static final String UPDATED_CURSO = "BBBBBBBBBB";

    private static final String DEFAULT_HORARIO = "AAAAAAAAAA";
    private static final String UPDATED_HORARIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_FRECUENCIA = 1;
    private static final Integer UPDATED_FRECUENCIA = 2;

    private static final Integer DEFAULT_DURACION = 1;
    private static final Integer UPDATED_DURACION = 2;

    private static final byte[] DEFAULT_FOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_2_CONTENT_TYPE = "image/png";

    @Autowired
    private ProgramasRepository programasRepository;

    @Autowired
    private ProgramasService programasService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ProgramasSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProgramasSearchRepository mockProgramasSearchRepository;

    @Autowired
    private ProgramasQueryService programasQueryService;

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

    private MockMvc restProgramasMockMvc;

    private Programas programas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgramasResource programasResource = new ProgramasResource(programasService, programasQueryService);
        this.restProgramasMockMvc = MockMvcBuilders.standaloneSetup(programasResource)
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
    public static Programas createEntity(EntityManager em) {
        Programas programas = new Programas()
            .registro(DEFAULT_REGISTRO)
            .moneda(DEFAULT_MONEDA)
            .curso(DEFAULT_CURSO)
            .horario(DEFAULT_HORARIO)
            .frecuencia(DEFAULT_FRECUENCIA)
            .duracion(DEFAULT_DURACION)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE);
        return programas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Programas createUpdatedEntity(EntityManager em) {
        Programas programas = new Programas()
            .registro(UPDATED_REGISTRO)
            .moneda(UPDATED_MONEDA)
            .curso(UPDATED_CURSO)
            .horario(UPDATED_HORARIO)
            .frecuencia(UPDATED_FRECUENCIA)
            .duracion(UPDATED_DURACION)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE);
        return programas;
    }

    @BeforeEach
    public void initTest() {
        programas = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgramas() throws Exception {
        int databaseSizeBeforeCreate = programasRepository.findAll().size();

        // Create the Programas
        restProgramasMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programas)))
            .andExpect(status().isCreated());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeCreate + 1);
        Programas testProgramas = programasList.get(programasList.size() - 1);
        assertThat(testProgramas.getRegistro()).isEqualTo(DEFAULT_REGISTRO);
        assertThat(testProgramas.getMoneda()).isEqualTo(DEFAULT_MONEDA);
        assertThat(testProgramas.getCurso()).isEqualTo(DEFAULT_CURSO);
        assertThat(testProgramas.getHorario()).isEqualTo(DEFAULT_HORARIO);
        assertThat(testProgramas.getFrecuencia()).isEqualTo(DEFAULT_FRECUENCIA);
        assertThat(testProgramas.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testProgramas.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testProgramas.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testProgramas.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testProgramas.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);

        // Validate the Programas in Elasticsearch
        verify(mockProgramasSearchRepository, times(1)).save(testProgramas);
    }

    @Test
    @Transactional
    public void createProgramasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programasRepository.findAll().size();

        // Create the Programas with an existing ID
        programas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramasMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programas)))
            .andExpect(status().isBadRequest());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeCreate);

        // Validate the Programas in Elasticsearch
        verify(mockProgramasSearchRepository, times(0)).save(programas);
    }


    @Test
    @Transactional
    public void getAllProgramas() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList
        restProgramasMockMvc.perform(get("/api/programas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programas.getId().intValue())))
            .andExpect(jsonPath("$.[*].registro").value(hasItem(DEFAULT_REGISTRO)))
            .andExpect(jsonPath("$.[*].moneda").value(hasItem(DEFAULT_MONEDA.toString())))
            .andExpect(jsonPath("$.[*].curso").value(hasItem(DEFAULT_CURSO.toString())))
            .andExpect(jsonPath("$.[*].horario").value(hasItem(DEFAULT_HORARIO.toString())))
            .andExpect(jsonPath("$.[*].frecuencia").value(hasItem(DEFAULT_FRECUENCIA)))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION)))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))));
    }
    
    @Test
    @Transactional
    public void getProgramas() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get the programas
        restProgramasMockMvc.perform(get("/api/programas/{id}", programas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(programas.getId().intValue()))
            .andExpect(jsonPath("$.registro").value(DEFAULT_REGISTRO))
            .andExpect(jsonPath("$.moneda").value(DEFAULT_MONEDA.toString()))
            .andExpect(jsonPath("$.curso").value(DEFAULT_CURSO.toString()))
            .andExpect(jsonPath("$.horario").value(DEFAULT_HORARIO.toString()))
            .andExpect(jsonPath("$.frecuencia").value(DEFAULT_FRECUENCIA))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)));
    }

    @Test
    @Transactional
    public void getAllProgramasByRegistroIsEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where registro equals to DEFAULT_REGISTRO
        defaultProgramasShouldBeFound("registro.equals=" + DEFAULT_REGISTRO);

        // Get all the programasList where registro equals to UPDATED_REGISTRO
        defaultProgramasShouldNotBeFound("registro.equals=" + UPDATED_REGISTRO);
    }

    @Test
    @Transactional
    public void getAllProgramasByRegistroIsInShouldWork() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where registro in DEFAULT_REGISTRO or UPDATED_REGISTRO
        defaultProgramasShouldBeFound("registro.in=" + DEFAULT_REGISTRO + "," + UPDATED_REGISTRO);

        // Get all the programasList where registro equals to UPDATED_REGISTRO
        defaultProgramasShouldNotBeFound("registro.in=" + UPDATED_REGISTRO);
    }

    @Test
    @Transactional
    public void getAllProgramasByRegistroIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where registro is not null
        defaultProgramasShouldBeFound("registro.specified=true");

        // Get all the programasList where registro is null
        defaultProgramasShouldNotBeFound("registro.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasByRegistroIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where registro greater than or equals to DEFAULT_REGISTRO
        defaultProgramasShouldBeFound("registro.greaterOrEqualThan=" + DEFAULT_REGISTRO);

        // Get all the programasList where registro greater than or equals to UPDATED_REGISTRO
        defaultProgramasShouldNotBeFound("registro.greaterOrEqualThan=" + UPDATED_REGISTRO);
    }

    @Test
    @Transactional
    public void getAllProgramasByRegistroIsLessThanSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where registro less than or equals to DEFAULT_REGISTRO
        defaultProgramasShouldNotBeFound("registro.lessThan=" + DEFAULT_REGISTRO);

        // Get all the programasList where registro less than or equals to UPDATED_REGISTRO
        defaultProgramasShouldBeFound("registro.lessThan=" + UPDATED_REGISTRO);
    }


    @Test
    @Transactional
    public void getAllProgramasByMonedaIsEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where moneda equals to DEFAULT_MONEDA
        defaultProgramasShouldBeFound("moneda.equals=" + DEFAULT_MONEDA);

        // Get all the programasList where moneda equals to UPDATED_MONEDA
        defaultProgramasShouldNotBeFound("moneda.equals=" + UPDATED_MONEDA);
    }

    @Test
    @Transactional
    public void getAllProgramasByMonedaIsInShouldWork() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where moneda in DEFAULT_MONEDA or UPDATED_MONEDA
        defaultProgramasShouldBeFound("moneda.in=" + DEFAULT_MONEDA + "," + UPDATED_MONEDA);

        // Get all the programasList where moneda equals to UPDATED_MONEDA
        defaultProgramasShouldNotBeFound("moneda.in=" + UPDATED_MONEDA);
    }

    @Test
    @Transactional
    public void getAllProgramasByMonedaIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where moneda is not null
        defaultProgramasShouldBeFound("moneda.specified=true");

        // Get all the programasList where moneda is null
        defaultProgramasShouldNotBeFound("moneda.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasByCursoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where curso equals to DEFAULT_CURSO
        defaultProgramasShouldBeFound("curso.equals=" + DEFAULT_CURSO);

        // Get all the programasList where curso equals to UPDATED_CURSO
        defaultProgramasShouldNotBeFound("curso.equals=" + UPDATED_CURSO);
    }

    @Test
    @Transactional
    public void getAllProgramasByCursoIsInShouldWork() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where curso in DEFAULT_CURSO or UPDATED_CURSO
        defaultProgramasShouldBeFound("curso.in=" + DEFAULT_CURSO + "," + UPDATED_CURSO);

        // Get all the programasList where curso equals to UPDATED_CURSO
        defaultProgramasShouldNotBeFound("curso.in=" + UPDATED_CURSO);
    }

    @Test
    @Transactional
    public void getAllProgramasByCursoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where curso is not null
        defaultProgramasShouldBeFound("curso.specified=true");

        // Get all the programasList where curso is null
        defaultProgramasShouldNotBeFound("curso.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasByHorarioIsEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where horario equals to DEFAULT_HORARIO
        defaultProgramasShouldBeFound("horario.equals=" + DEFAULT_HORARIO);

        // Get all the programasList where horario equals to UPDATED_HORARIO
        defaultProgramasShouldNotBeFound("horario.equals=" + UPDATED_HORARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasByHorarioIsInShouldWork() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where horario in DEFAULT_HORARIO or UPDATED_HORARIO
        defaultProgramasShouldBeFound("horario.in=" + DEFAULT_HORARIO + "," + UPDATED_HORARIO);

        // Get all the programasList where horario equals to UPDATED_HORARIO
        defaultProgramasShouldNotBeFound("horario.in=" + UPDATED_HORARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasByHorarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where horario is not null
        defaultProgramasShouldBeFound("horario.specified=true");

        // Get all the programasList where horario is null
        defaultProgramasShouldNotBeFound("horario.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasByFrecuenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where frecuencia equals to DEFAULT_FRECUENCIA
        defaultProgramasShouldBeFound("frecuencia.equals=" + DEFAULT_FRECUENCIA);

        // Get all the programasList where frecuencia equals to UPDATED_FRECUENCIA
        defaultProgramasShouldNotBeFound("frecuencia.equals=" + UPDATED_FRECUENCIA);
    }

    @Test
    @Transactional
    public void getAllProgramasByFrecuenciaIsInShouldWork() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where frecuencia in DEFAULT_FRECUENCIA or UPDATED_FRECUENCIA
        defaultProgramasShouldBeFound("frecuencia.in=" + DEFAULT_FRECUENCIA + "," + UPDATED_FRECUENCIA);

        // Get all the programasList where frecuencia equals to UPDATED_FRECUENCIA
        defaultProgramasShouldNotBeFound("frecuencia.in=" + UPDATED_FRECUENCIA);
    }

    @Test
    @Transactional
    public void getAllProgramasByFrecuenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where frecuencia is not null
        defaultProgramasShouldBeFound("frecuencia.specified=true");

        // Get all the programasList where frecuencia is null
        defaultProgramasShouldNotBeFound("frecuencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasByFrecuenciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where frecuencia greater than or equals to DEFAULT_FRECUENCIA
        defaultProgramasShouldBeFound("frecuencia.greaterOrEqualThan=" + DEFAULT_FRECUENCIA);

        // Get all the programasList where frecuencia greater than or equals to UPDATED_FRECUENCIA
        defaultProgramasShouldNotBeFound("frecuencia.greaterOrEqualThan=" + UPDATED_FRECUENCIA);
    }

    @Test
    @Transactional
    public void getAllProgramasByFrecuenciaIsLessThanSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where frecuencia less than or equals to DEFAULT_FRECUENCIA
        defaultProgramasShouldNotBeFound("frecuencia.lessThan=" + DEFAULT_FRECUENCIA);

        // Get all the programasList where frecuencia less than or equals to UPDATED_FRECUENCIA
        defaultProgramasShouldBeFound("frecuencia.lessThan=" + UPDATED_FRECUENCIA);
    }


    @Test
    @Transactional
    public void getAllProgramasByDuracionIsEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where duracion equals to DEFAULT_DURACION
        defaultProgramasShouldBeFound("duracion.equals=" + DEFAULT_DURACION);

        // Get all the programasList where duracion equals to UPDATED_DURACION
        defaultProgramasShouldNotBeFound("duracion.equals=" + UPDATED_DURACION);
    }

    @Test
    @Transactional
    public void getAllProgramasByDuracionIsInShouldWork() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where duracion in DEFAULT_DURACION or UPDATED_DURACION
        defaultProgramasShouldBeFound("duracion.in=" + DEFAULT_DURACION + "," + UPDATED_DURACION);

        // Get all the programasList where duracion equals to UPDATED_DURACION
        defaultProgramasShouldNotBeFound("duracion.in=" + UPDATED_DURACION);
    }

    @Test
    @Transactional
    public void getAllProgramasByDuracionIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where duracion is not null
        defaultProgramasShouldBeFound("duracion.specified=true");

        // Get all the programasList where duracion is null
        defaultProgramasShouldNotBeFound("duracion.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasByDuracionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where duracion greater than or equals to DEFAULT_DURACION
        defaultProgramasShouldBeFound("duracion.greaterOrEqualThan=" + DEFAULT_DURACION);

        // Get all the programasList where duracion greater than or equals to UPDATED_DURACION
        defaultProgramasShouldNotBeFound("duracion.greaterOrEqualThan=" + UPDATED_DURACION);
    }

    @Test
    @Transactional
    public void getAllProgramasByDuracionIsLessThanSomething() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList where duracion less than or equals to DEFAULT_DURACION
        defaultProgramasShouldNotBeFound("duracion.lessThan=" + DEFAULT_DURACION);

        // Get all the programasList where duracion less than or equals to UPDATED_DURACION
        defaultProgramasShouldBeFound("duracion.lessThan=" + UPDATED_DURACION);
    }


    @Test
    @Transactional
    public void getAllProgramasByInstitucionIsEqualToSomething() throws Exception {
        // Initialize the database
        Institucion institucion = InstitucionResourceIT.createEntity(em);
        em.persist(institucion);
        em.flush();
        programas.setInstitucion(institucion);
        programasRepository.saveAndFlush(programas);
        Long institucionId = institucion.getId();

        // Get all the programasList where institucion equals to institucionId
        defaultProgramasShouldBeFound("institucionId.equals=" + institucionId);

        // Get all the programasList where institucion equals to institucionId + 1
        defaultProgramasShouldNotBeFound("institucionId.equals=" + (institucionId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasByCiudadIsEqualToSomething() throws Exception {
        // Initialize the database
        Ciudad ciudad = CiudadResourceIT.createEntity(em);
        em.persist(ciudad);
        em.flush();
        programas.setCiudad(ciudad);
        programasRepository.saveAndFlush(programas);
        Long ciudadId = ciudad.getId();

        // Get all the programasList where ciudad equals to ciudadId
        defaultProgramasShouldBeFound("ciudadId.equals=" + ciudadId);

        // Get all the programasList where ciudad equals to ciudadId + 1
        defaultProgramasShouldNotBeFound("ciudadId.equals=" + (ciudadId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasByTipoProgramaIsEqualToSomething() throws Exception {
        // Initialize the database
        TipoPrograma tipoPrograma = TipoProgramaResourceIT.createEntity(em);
        em.persist(tipoPrograma);
        em.flush();
        programas.setTipoPrograma(tipoPrograma);
        programasRepository.saveAndFlush(programas);
        Long tipoProgramaId = tipoPrograma.getId();

        // Get all the programasList where tipoPrograma equals to tipoProgramaId
        defaultProgramasShouldBeFound("tipoProgramaId.equals=" + tipoProgramaId);

        // Get all the programasList where tipoPrograma equals to tipoProgramaId + 1
        defaultProgramasShouldNotBeFound("tipoProgramaId.equals=" + (tipoProgramaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProgramasShouldBeFound(String filter) throws Exception {
        restProgramasMockMvc.perform(get("/api/programas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programas.getId().intValue())))
            .andExpect(jsonPath("$.[*].registro").value(hasItem(DEFAULT_REGISTRO)))
            .andExpect(jsonPath("$.[*].moneda").value(hasItem(DEFAULT_MONEDA.toString())))
            .andExpect(jsonPath("$.[*].curso").value(hasItem(DEFAULT_CURSO)))
            .andExpect(jsonPath("$.[*].horario").value(hasItem(DEFAULT_HORARIO)))
            .andExpect(jsonPath("$.[*].frecuencia").value(hasItem(DEFAULT_FRECUENCIA)))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION)))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))));

        // Check, that the count call also returns 1
        restProgramasMockMvc.perform(get("/api/programas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProgramasShouldNotBeFound(String filter) throws Exception {
        restProgramasMockMvc.perform(get("/api/programas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProgramasMockMvc.perform(get("/api/programas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProgramas() throws Exception {
        // Get the programas
        restProgramasMockMvc.perform(get("/api/programas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgramas() throws Exception {
        // Initialize the database
        programasService.save(programas);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockProgramasSearchRepository);

        int databaseSizeBeforeUpdate = programasRepository.findAll().size();

        // Update the programas
        Programas updatedProgramas = programasRepository.findById(programas.getId()).get();
        // Disconnect from session so that the updates on updatedProgramas are not directly saved in db
        em.detach(updatedProgramas);
        updatedProgramas
            .registro(UPDATED_REGISTRO)
            .moneda(UPDATED_MONEDA)
            .curso(UPDATED_CURSO)
            .horario(UPDATED_HORARIO)
            .frecuencia(UPDATED_FRECUENCIA)
            .duracion(UPDATED_DURACION)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE);

        restProgramasMockMvc.perform(put("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProgramas)))
            .andExpect(status().isOk());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeUpdate);
        Programas testProgramas = programasList.get(programasList.size() - 1);
        assertThat(testProgramas.getRegistro()).isEqualTo(UPDATED_REGISTRO);
        assertThat(testProgramas.getMoneda()).isEqualTo(UPDATED_MONEDA);
        assertThat(testProgramas.getCurso()).isEqualTo(UPDATED_CURSO);
        assertThat(testProgramas.getHorario()).isEqualTo(UPDATED_HORARIO);
        assertThat(testProgramas.getFrecuencia()).isEqualTo(UPDATED_FRECUENCIA);
        assertThat(testProgramas.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testProgramas.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testProgramas.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testProgramas.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testProgramas.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);

        // Validate the Programas in Elasticsearch
        verify(mockProgramasSearchRepository, times(1)).save(testProgramas);
    }

    @Test
    @Transactional
    public void updateNonExistingProgramas() throws Exception {
        int databaseSizeBeforeUpdate = programasRepository.findAll().size();

        // Create the Programas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgramasMockMvc.perform(put("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programas)))
            .andExpect(status().isBadRequest());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Programas in Elasticsearch
        verify(mockProgramasSearchRepository, times(0)).save(programas);
    }

    @Test
    @Transactional
    public void deleteProgramas() throws Exception {
        // Initialize the database
        programasService.save(programas);

        int databaseSizeBeforeDelete = programasRepository.findAll().size();

        // Delete the programas
        restProgramasMockMvc.perform(delete("/api/programas/{id}", programas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Programas in Elasticsearch
        verify(mockProgramasSearchRepository, times(1)).deleteById(programas.getId());
    }

    @Test
    @Transactional
    public void searchProgramas() throws Exception {
        // Initialize the database
        programasService.save(programas);
        when(mockProgramasSearchRepository.search(queryStringQuery("id:" + programas.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(programas), PageRequest.of(0, 1), 1));
        // Search the programas
        restProgramasMockMvc.perform(get("/api/_search/programas?query=id:" + programas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programas.getId().intValue())))
            .andExpect(jsonPath("$.[*].registro").value(hasItem(DEFAULT_REGISTRO)))
            .andExpect(jsonPath("$.[*].moneda").value(hasItem(DEFAULT_MONEDA.toString())))
            .andExpect(jsonPath("$.[*].curso").value(hasItem(DEFAULT_CURSO)))
            .andExpect(jsonPath("$.[*].horario").value(hasItem(DEFAULT_HORARIO)))
            .andExpect(jsonPath("$.[*].frecuencia").value(hasItem(DEFAULT_FRECUENCIA)))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION)))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Programas.class);
        Programas programas1 = new Programas();
        programas1.setId(1L);
        Programas programas2 = new Programas();
        programas2.setId(programas1.getId());
        assertThat(programas1).isEqualTo(programas2);
        programas2.setId(2L);
        assertThat(programas1).isNotEqualTo(programas2);
        programas1.setId(null);
        assertThat(programas1).isNotEqualTo(programas2);
    }
}
