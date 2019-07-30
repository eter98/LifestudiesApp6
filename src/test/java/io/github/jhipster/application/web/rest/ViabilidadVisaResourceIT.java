package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp6App;
import io.github.jhipster.application.domain.ViabilidadVisa;
import io.github.jhipster.application.repository.ViabilidadVisaRepository;
import io.github.jhipster.application.repository.search.ViabilidadVisaSearchRepository;
import io.github.jhipster.application.service.ViabilidadVisaService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ViabilidadVisaCriteria;
import io.github.jhipster.application.service.ViabilidadVisaQueryService;

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

import io.github.jhipster.application.domain.enumeration.Destinod;
import io.github.jhipster.application.domain.enumeration.TipoProgramad;
import io.github.jhipster.application.domain.enumeration.Duraciond;
import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Generod;
import io.github.jhipster.application.domain.enumeration.EstadoCivild;
import io.github.jhipster.application.domain.enumeration.PersonasCargod;
import io.github.jhipster.application.domain.enumeration.NivelAcademicod;
import io.github.jhipster.application.domain.enumeration.OcupacionActuald;
import io.github.jhipster.application.domain.enumeration.PeridoSupencionEstud;
import io.github.jhipster.application.domain.enumeration.TipoContratod;
import io.github.jhipster.application.domain.enumeration.NivelSalariald;
import io.github.jhipster.application.domain.enumeration.TipoLaborIndependiented;
import io.github.jhipster.application.domain.enumeration.PeridoSupencionEstud;
import io.github.jhipster.application.domain.enumeration.NivelSalariald;
import io.github.jhipster.application.domain.enumeration.PeridoSupencionEstud;
import io.github.jhipster.application.domain.enumeration.QuienFinanciaEstudiosd;
import io.github.jhipster.application.domain.enumeration.Parentescod;
import io.github.jhipster.application.domain.enumeration.NivelSalariald;
/**
 * Integration tests for the {@Link ViabilidadVisaResource} REST controller.
 */
@SpringBootTest(classes = LifestudiesApp6App.class)
public class ViabilidadVisaResourceIT {

    private static final Destinod DEFAULT_DESTINO = Destinod.ALEMANIA;
    private static final Destinod UPDATED_DESTINO = Destinod.AUSTRALIA;

    private static final TipoProgramad DEFAULT_TIPO_PROGRAMA = TipoProgramad.Programas_Idiomas;
    private static final TipoProgramad UPDATED_TIPO_PROGRAMA = TipoProgramad.Curso_Verano_idiomas;

    private static final Duraciond DEFAULT_DURACION = Duraciond.UN_MES;
    private static final Duraciond UPDATED_DURACION = Duraciond.DOS_MESES;

    private static final Nacionalidadd DEFAULT_NACIONALIDAD_PRINCIPAL = Nacionalidadd.Argentina;
    private static final Nacionalidadd UPDATED_NACIONALIDAD_PRINCIPAL = Nacionalidadd.Bolivia;

    private static final Nacionalidadd DEFAULT_OTRA_NACIONALIDAD = Nacionalidadd.Argentina;
    private static final Nacionalidadd UPDATED_OTRA_NACIONALIDAD = Nacionalidadd.Bolivia;

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final Generod DEFAULT_GENERO = Generod.Hombre;
    private static final Generod UPDATED_GENERO = Generod.Mujer;

    private static final EstadoCivild DEFAULT_ESTADO_CIVIL = EstadoCivild.Soltero;
    private static final EstadoCivild UPDATED_ESTADO_CIVIL = EstadoCivild.Casado;

    private static final Boolean DEFAULT_VIAJA_ACOMPANADO = false;
    private static final Boolean UPDATED_VIAJA_ACOMPANADO = true;

    private static final PersonasCargod DEFAULT_PERSONAS_CARGO = PersonasCargod.NINGUNA;
    private static final PersonasCargod UPDATED_PERSONAS_CARGO = PersonasCargod.UNA;

    private static final NivelAcademicod DEFAULT_NIVEL_ACADEMICO = NivelAcademicod.PRIMARIA;
    private static final NivelAcademicod UPDATED_NIVEL_ACADEMICO = NivelAcademicod.SECUNDARIA;

    private static final String DEFAULT_PROFESION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_GADRUACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_GADRUACION = LocalDate.now(ZoneId.systemDefault());

    private static final OcupacionActuald DEFAULT_OCUPACION_ACTUAL = OcupacionActuald.Estudiante;
    private static final OcupacionActuald UPDATED_OCUPACION_ACTUAL = OcupacionActuald.Estudiante_Graduado_sin_Laborar;

    private static final Boolean DEFAULT_CARTA = false;
    private static final Boolean UPDATED_CARTA = true;

    private static final Boolean DEFAULT_SUSPENDIDO_ESTUDIOS = false;
    private static final Boolean UPDATED_SUSPENDIDO_ESTUDIOS = true;

    private static final PeridoSupencionEstud DEFAULT_PERIDO_SUPENCION_ESTU = PeridoSupencionEstud.SEIS_MESES;
    private static final PeridoSupencionEstud UPDATED_PERIDO_SUPENCION_ESTU = PeridoSupencionEstud.UN_ANO;

    private static final String DEFAULT_RAZON_SUSPENCION = "AAAAAAAAAA";
    private static final String UPDATED_RAZON_SUSPENCION = "BBBBBBBBBB";

    private static final TipoContratod DEFAULT_TIPO_CONTRATO = TipoContratod.TERMINO_FIJO;
    private static final TipoContratod UPDATED_TIPO_CONTRATO = TipoContratod.TERMINO_INDEFNIDO;

    private static final Boolean DEFAULT_LICENCIA_LABORAL = false;
    private static final Boolean UPDATED_LICENCIA_LABORAL = true;

    private static final NivelSalariald DEFAULT_NIVEL_SALARIAL = NivelSalariald.MENOS_US600;
    private static final NivelSalariald UPDATED_NIVEL_SALARIAL = NivelSalariald.US700_US1000;

    private static final TipoLaborIndependiented DEFAULT_TIPO_LABOR_INDEPENDIENTE = TipoLaborIndependiented.PROFESIONAL_CUENTA_PROPIA;
    private static final TipoLaborIndependiented UPDATED_TIPO_LABOR_INDEPENDIENTE = TipoLaborIndependiented.ESTABLECIMIENTO_COMERCIO;

    private static final PeridoSupencionEstud DEFAULT_TIEMPO_INDEPENDIENTE = PeridoSupencionEstud.SEIS_MESES;
    private static final PeridoSupencionEstud UPDATED_TIEMPO_INDEPENDIENTE = PeridoSupencionEstud.UN_ANO;

    private static final NivelSalariald DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE = NivelSalariald.MENOS_US600;
    private static final NivelSalariald UPDATED_NIVEL_INGRESOS_INDEPENDIENTE = NivelSalariald.US700_US1000;

    private static final PeridoSupencionEstud DEFAULT_TIEMPO_DESEMPLEADO = PeridoSupencionEstud.SEIS_MESES;
    private static final PeridoSupencionEstud UPDATED_TIEMPO_DESEMPLEADO = PeridoSupencionEstud.UN_ANO;

    private static final QuienFinanciaEstudiosd DEFAULT_QUIEN_FINANCIA_ESTUDIOS = QuienFinanciaEstudiosd.Tu_mismo;
    private static final QuienFinanciaEstudiosd UPDATED_QUIEN_FINANCIA_ESTUDIOS = QuienFinanciaEstudiosd.Un_familiar;

    private static final Parentescod DEFAULT_PARENTESCO = Parentescod.Padre_Madre;
    private static final Parentescod UPDATED_PARENTESCO = Parentescod.Hermano;

    private static final NivelSalariald DEFAULT_PRESUPUESTO_DISPONIBLE = NivelSalariald.MENOS_US600;
    private static final NivelSalariald UPDATED_PRESUPUESTO_DISPONIBLE = NivelSalariald.US700_US1000;

    private static final Boolean DEFAULT_AHORRO_DISPONIBLE = false;
    private static final Boolean UPDATED_AHORRO_DISPONIBLE = true;

    private static final Boolean DEFAULT_IDIOMA = false;
    private static final Boolean UPDATED_IDIOMA = true;

    private static final Boolean DEFAULT_CERTIFICAR_IDIOMA = false;
    private static final Boolean UPDATED_CERTIFICAR_IDIOMA = true;

    private static final String DEFAULT_CERTIFICACION_IDIOMA = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICACION_IDIOMA = "BBBBBBBBBB";

    private static final String DEFAULT_PUNTAJE_CERTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_PUNTAJE_CERTIFICACION = "BBBBBBBBBB";

    private static final String DEFAULT_SALIDAS_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_SALIDAS_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_PAISES_VISITADOS = "AAAAAAAAAA";
    private static final String UPDATED_PAISES_VISITADOS = "BBBBBBBBBB";

    private static final String DEFAULT_VISA_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_VISA_PAIS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTUDIADO_ANTERIOR = false;
    private static final Boolean UPDATED_ESTUDIADO_ANTERIOR = true;

    private static final Boolean DEFAULT_FUERA_PAIS = false;
    private static final Boolean UPDATED_FUERA_PAIS = true;

    private static final String DEFAULT_PAIS_FUERA = "AAAAAAAAAA";
    private static final String UPDATED_PAIS_FUERA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXTENDER_ESTADIA = false;
    private static final Boolean UPDATED_EXTENDER_ESTADIA = true;

    private static final Boolean DEFAULT_NEGADO_VISA = false;
    private static final Boolean UPDATED_NEGADO_VISA = true;

    private static final String DEFAULT_PAIS_NEGADO = "AAAAAAAAAA";
    private static final String UPDATED_PAIS_NEGADO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAMILIARES_DESTINO = false;
    private static final Boolean UPDATED_FAMILIARES_DESTINO = true;

    private static final String DEFAULT_ESTATUS_MIGRATORIO = "AAAAAAAAAA";
    private static final String UPDATED_ESTATUS_MIGRATORIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIOD = "AAAAAAAAAA";
    private static final String UPDATED_APELLIOD = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    @Autowired
    private ViabilidadVisaRepository viabilidadVisaRepository;

    @Autowired
    private ViabilidadVisaService viabilidadVisaService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ViabilidadVisaSearchRepositoryMockConfiguration
     */
    @Autowired
    private ViabilidadVisaSearchRepository mockViabilidadVisaSearchRepository;

    @Autowired
    private ViabilidadVisaQueryService viabilidadVisaQueryService;

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

    private MockMvc restViabilidadVisaMockMvc;

    private ViabilidadVisa viabilidadVisa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ViabilidadVisaResource viabilidadVisaResource = new ViabilidadVisaResource(viabilidadVisaService, viabilidadVisaQueryService);
        this.restViabilidadVisaMockMvc = MockMvcBuilders.standaloneSetup(viabilidadVisaResource)
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
    public static ViabilidadVisa createEntity(EntityManager em) {
        ViabilidadVisa viabilidadVisa = new ViabilidadVisa()
            .destino(DEFAULT_DESTINO)
            .tipoPrograma(DEFAULT_TIPO_PROGRAMA)
            .duracion(DEFAULT_DURACION)
            .nacionalidadPrincipal(DEFAULT_NACIONALIDAD_PRINCIPAL)
            .otraNacionalidad(DEFAULT_OTRA_NACIONALIDAD)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .genero(DEFAULT_GENERO)
            .estadoCivil(DEFAULT_ESTADO_CIVIL)
            .viajaAcompanado(DEFAULT_VIAJA_ACOMPANADO)
            .personasCargo(DEFAULT_PERSONAS_CARGO)
            .nivelAcademico(DEFAULT_NIVEL_ACADEMICO)
            .profesion(DEFAULT_PROFESION)
            .fechaGadruacion(DEFAULT_FECHA_GADRUACION)
            .ocupacionActual(DEFAULT_OCUPACION_ACTUAL)
            .carta(DEFAULT_CARTA)
            .suspendidoEstudios(DEFAULT_SUSPENDIDO_ESTUDIOS)
            .peridoSupencionEstu(DEFAULT_PERIDO_SUPENCION_ESTU)
            .razonSuspencion(DEFAULT_RAZON_SUSPENCION)
            .tipoContrato(DEFAULT_TIPO_CONTRATO)
            .licenciaLaboral(DEFAULT_LICENCIA_LABORAL)
            .nivelSalarial(DEFAULT_NIVEL_SALARIAL)
            .tipoLaborIndependiente(DEFAULT_TIPO_LABOR_INDEPENDIENTE)
            .tiempoIndependiente(DEFAULT_TIEMPO_INDEPENDIENTE)
            .nivelIngresosIndependiente(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE)
            .tiempoDesempleado(DEFAULT_TIEMPO_DESEMPLEADO)
            .quienFinanciaEstudios(DEFAULT_QUIEN_FINANCIA_ESTUDIOS)
            .parentesco(DEFAULT_PARENTESCO)
            .presupuestoDisponible(DEFAULT_PRESUPUESTO_DISPONIBLE)
            .ahorroDisponible(DEFAULT_AHORRO_DISPONIBLE)
            .idioma(DEFAULT_IDIOMA)
            .certificarIdioma(DEFAULT_CERTIFICAR_IDIOMA)
            .certificacionIdioma(DEFAULT_CERTIFICACION_IDIOMA)
            .puntajeCertificacion(DEFAULT_PUNTAJE_CERTIFICACION)
            .salidasPais(DEFAULT_SALIDAS_PAIS)
            .paisesVisitados(DEFAULT_PAISES_VISITADOS)
            .visaPais(DEFAULT_VISA_PAIS)
            .estudiadoAnterior(DEFAULT_ESTUDIADO_ANTERIOR)
            .fueraPais(DEFAULT_FUERA_PAIS)
            .paisFuera(DEFAULT_PAIS_FUERA)
            .extenderEstadia(DEFAULT_EXTENDER_ESTADIA)
            .negadoVisa(DEFAULT_NEGADO_VISA)
            .paisNegado(DEFAULT_PAIS_NEGADO)
            .familiaresDestino(DEFAULT_FAMILIARES_DESTINO)
            .estatusMigratorio(DEFAULT_ESTATUS_MIGRATORIO)
            .nombre(DEFAULT_NOMBRE)
            .apelliod(DEFAULT_APELLIOD)
            .correo(DEFAULT_CORREO);
        return viabilidadVisa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViabilidadVisa createUpdatedEntity(EntityManager em) {
        ViabilidadVisa viabilidadVisa = new ViabilidadVisa()
            .destino(UPDATED_DESTINO)
            .tipoPrograma(UPDATED_TIPO_PROGRAMA)
            .duracion(UPDATED_DURACION)
            .nacionalidadPrincipal(UPDATED_NACIONALIDAD_PRINCIPAL)
            .otraNacionalidad(UPDATED_OTRA_NACIONALIDAD)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .genero(UPDATED_GENERO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .viajaAcompanado(UPDATED_VIAJA_ACOMPANADO)
            .personasCargo(UPDATED_PERSONAS_CARGO)
            .nivelAcademico(UPDATED_NIVEL_ACADEMICO)
            .profesion(UPDATED_PROFESION)
            .fechaGadruacion(UPDATED_FECHA_GADRUACION)
            .ocupacionActual(UPDATED_OCUPACION_ACTUAL)
            .carta(UPDATED_CARTA)
            .suspendidoEstudios(UPDATED_SUSPENDIDO_ESTUDIOS)
            .peridoSupencionEstu(UPDATED_PERIDO_SUPENCION_ESTU)
            .razonSuspencion(UPDATED_RAZON_SUSPENCION)
            .tipoContrato(UPDATED_TIPO_CONTRATO)
            .licenciaLaboral(UPDATED_LICENCIA_LABORAL)
            .nivelSalarial(UPDATED_NIVEL_SALARIAL)
            .tipoLaborIndependiente(UPDATED_TIPO_LABOR_INDEPENDIENTE)
            .tiempoIndependiente(UPDATED_TIEMPO_INDEPENDIENTE)
            .nivelIngresosIndependiente(UPDATED_NIVEL_INGRESOS_INDEPENDIENTE)
            .tiempoDesempleado(UPDATED_TIEMPO_DESEMPLEADO)
            .quienFinanciaEstudios(UPDATED_QUIEN_FINANCIA_ESTUDIOS)
            .parentesco(UPDATED_PARENTESCO)
            .presupuestoDisponible(UPDATED_PRESUPUESTO_DISPONIBLE)
            .ahorroDisponible(UPDATED_AHORRO_DISPONIBLE)
            .idioma(UPDATED_IDIOMA)
            .certificarIdioma(UPDATED_CERTIFICAR_IDIOMA)
            .certificacionIdioma(UPDATED_CERTIFICACION_IDIOMA)
            .puntajeCertificacion(UPDATED_PUNTAJE_CERTIFICACION)
            .salidasPais(UPDATED_SALIDAS_PAIS)
            .paisesVisitados(UPDATED_PAISES_VISITADOS)
            .visaPais(UPDATED_VISA_PAIS)
            .estudiadoAnterior(UPDATED_ESTUDIADO_ANTERIOR)
            .fueraPais(UPDATED_FUERA_PAIS)
            .paisFuera(UPDATED_PAIS_FUERA)
            .extenderEstadia(UPDATED_EXTENDER_ESTADIA)
            .negadoVisa(UPDATED_NEGADO_VISA)
            .paisNegado(UPDATED_PAIS_NEGADO)
            .familiaresDestino(UPDATED_FAMILIARES_DESTINO)
            .estatusMigratorio(UPDATED_ESTATUS_MIGRATORIO)
            .nombre(UPDATED_NOMBRE)
            .apelliod(UPDATED_APELLIOD)
            .correo(UPDATED_CORREO);
        return viabilidadVisa;
    }

    @BeforeEach
    public void initTest() {
        viabilidadVisa = createEntity(em);
    }

    @Test
    @Transactional
    public void createViabilidadVisa() throws Exception {
        int databaseSizeBeforeCreate = viabilidadVisaRepository.findAll().size();

        // Create the ViabilidadVisa
        restViabilidadVisaMockMvc.perform(post("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(viabilidadVisa)))
            .andExpect(status().isCreated());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeCreate + 1);
        ViabilidadVisa testViabilidadVisa = viabilidadVisaList.get(viabilidadVisaList.size() - 1);
        assertThat(testViabilidadVisa.getDestino()).isEqualTo(DEFAULT_DESTINO);
        assertThat(testViabilidadVisa.getTipoPrograma()).isEqualTo(DEFAULT_TIPO_PROGRAMA);
        assertThat(testViabilidadVisa.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testViabilidadVisa.getNacionalidadPrincipal()).isEqualTo(DEFAULT_NACIONALIDAD_PRINCIPAL);
        assertThat(testViabilidadVisa.getOtraNacionalidad()).isEqualTo(DEFAULT_OTRA_NACIONALIDAD);
        assertThat(testViabilidadVisa.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testViabilidadVisa.getGenero()).isEqualTo(DEFAULT_GENERO);
        assertThat(testViabilidadVisa.getEstadoCivil()).isEqualTo(DEFAULT_ESTADO_CIVIL);
        assertThat(testViabilidadVisa.isViajaAcompanado()).isEqualTo(DEFAULT_VIAJA_ACOMPANADO);
        assertThat(testViabilidadVisa.getPersonasCargo()).isEqualTo(DEFAULT_PERSONAS_CARGO);
        assertThat(testViabilidadVisa.getNivelAcademico()).isEqualTo(DEFAULT_NIVEL_ACADEMICO);
        assertThat(testViabilidadVisa.getProfesion()).isEqualTo(DEFAULT_PROFESION);
        assertThat(testViabilidadVisa.getFechaGadruacion()).isEqualTo(DEFAULT_FECHA_GADRUACION);
        assertThat(testViabilidadVisa.getOcupacionActual()).isEqualTo(DEFAULT_OCUPACION_ACTUAL);
        assertThat(testViabilidadVisa.isCarta()).isEqualTo(DEFAULT_CARTA);
        assertThat(testViabilidadVisa.isSuspendidoEstudios()).isEqualTo(DEFAULT_SUSPENDIDO_ESTUDIOS);
        assertThat(testViabilidadVisa.getPeridoSupencionEstu()).isEqualTo(DEFAULT_PERIDO_SUPENCION_ESTU);
        assertThat(testViabilidadVisa.getRazonSuspencion()).isEqualTo(DEFAULT_RAZON_SUSPENCION);
        assertThat(testViabilidadVisa.getTipoContrato()).isEqualTo(DEFAULT_TIPO_CONTRATO);
        assertThat(testViabilidadVisa.isLicenciaLaboral()).isEqualTo(DEFAULT_LICENCIA_LABORAL);
        assertThat(testViabilidadVisa.getNivelSalarial()).isEqualTo(DEFAULT_NIVEL_SALARIAL);
        assertThat(testViabilidadVisa.getTipoLaborIndependiente()).isEqualTo(DEFAULT_TIPO_LABOR_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoIndependiente()).isEqualTo(DEFAULT_TIEMPO_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getNivelIngresosIndependiente()).isEqualTo(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoDesempleado()).isEqualTo(DEFAULT_TIEMPO_DESEMPLEADO);
        assertThat(testViabilidadVisa.getQuienFinanciaEstudios()).isEqualTo(DEFAULT_QUIEN_FINANCIA_ESTUDIOS);
        assertThat(testViabilidadVisa.getParentesco()).isEqualTo(DEFAULT_PARENTESCO);
        assertThat(testViabilidadVisa.getPresupuestoDisponible()).isEqualTo(DEFAULT_PRESUPUESTO_DISPONIBLE);
        assertThat(testViabilidadVisa.isAhorroDisponible()).isEqualTo(DEFAULT_AHORRO_DISPONIBLE);
        assertThat(testViabilidadVisa.isIdioma()).isEqualTo(DEFAULT_IDIOMA);
        assertThat(testViabilidadVisa.isCertificarIdioma()).isEqualTo(DEFAULT_CERTIFICAR_IDIOMA);
        assertThat(testViabilidadVisa.getCertificacionIdioma()).isEqualTo(DEFAULT_CERTIFICACION_IDIOMA);
        assertThat(testViabilidadVisa.getPuntajeCertificacion()).isEqualTo(DEFAULT_PUNTAJE_CERTIFICACION);
        assertThat(testViabilidadVisa.getSalidasPais()).isEqualTo(DEFAULT_SALIDAS_PAIS);
        assertThat(testViabilidadVisa.getPaisesVisitados()).isEqualTo(DEFAULT_PAISES_VISITADOS);
        assertThat(testViabilidadVisa.getVisaPais()).isEqualTo(DEFAULT_VISA_PAIS);
        assertThat(testViabilidadVisa.isEstudiadoAnterior()).isEqualTo(DEFAULT_ESTUDIADO_ANTERIOR);
        assertThat(testViabilidadVisa.isFueraPais()).isEqualTo(DEFAULT_FUERA_PAIS);
        assertThat(testViabilidadVisa.getPaisFuera()).isEqualTo(DEFAULT_PAIS_FUERA);
        assertThat(testViabilidadVisa.isExtenderEstadia()).isEqualTo(DEFAULT_EXTENDER_ESTADIA);
        assertThat(testViabilidadVisa.isNegadoVisa()).isEqualTo(DEFAULT_NEGADO_VISA);
        assertThat(testViabilidadVisa.getPaisNegado()).isEqualTo(DEFAULT_PAIS_NEGADO);
        assertThat(testViabilidadVisa.isFamiliaresDestino()).isEqualTo(DEFAULT_FAMILIARES_DESTINO);
        assertThat(testViabilidadVisa.getEstatusMigratorio()).isEqualTo(DEFAULT_ESTATUS_MIGRATORIO);
        assertThat(testViabilidadVisa.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testViabilidadVisa.getApelliod()).isEqualTo(DEFAULT_APELLIOD);
        assertThat(testViabilidadVisa.getCorreo()).isEqualTo(DEFAULT_CORREO);

        // Validate the ViabilidadVisa in Elasticsearch
        verify(mockViabilidadVisaSearchRepository, times(1)).save(testViabilidadVisa);
    }

    @Test
    @Transactional
    public void createViabilidadVisaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = viabilidadVisaRepository.findAll().size();

        // Create the ViabilidadVisa with an existing ID
        viabilidadVisa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restViabilidadVisaMockMvc.perform(post("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(viabilidadVisa)))
            .andExpect(status().isBadRequest());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeCreate);

        // Validate the ViabilidadVisa in Elasticsearch
        verify(mockViabilidadVisaSearchRepository, times(0)).save(viabilidadVisa);
    }


    @Test
    @Transactional
    public void getAllViabilidadVisas() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viabilidadVisa.getId().intValue())))
            .andExpect(jsonPath("$.[*].destino").value(hasItem(DEFAULT_DESTINO.toString())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.toString())))
            .andExpect(jsonPath("$.[*].nacionalidadPrincipal").value(hasItem(DEFAULT_NACIONALIDAD_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].otraNacionalidad").value(hasItem(DEFAULT_OTRA_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].viajaAcompanado").value(hasItem(DEFAULT_VIAJA_ACOMPANADO.booleanValue())))
            .andExpect(jsonPath("$.[*].personasCargo").value(hasItem(DEFAULT_PERSONAS_CARGO.toString())))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].profesion").value(hasItem(DEFAULT_PROFESION.toString())))
            .andExpect(jsonPath("$.[*].fechaGadruacion").value(hasItem(DEFAULT_FECHA_GADRUACION.toString())))
            .andExpect(jsonPath("$.[*].ocupacionActual").value(hasItem(DEFAULT_OCUPACION_ACTUAL.toString())))
            .andExpect(jsonPath("$.[*].carta").value(hasItem(DEFAULT_CARTA.booleanValue())))
            .andExpect(jsonPath("$.[*].suspendidoEstudios").value(hasItem(DEFAULT_SUSPENDIDO_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].peridoSupencionEstu").value(hasItem(DEFAULT_PERIDO_SUPENCION_ESTU.toString())))
            .andExpect(jsonPath("$.[*].razonSuspencion").value(hasItem(DEFAULT_RAZON_SUSPENCION.toString())))
            .andExpect(jsonPath("$.[*].tipoContrato").value(hasItem(DEFAULT_TIPO_CONTRATO.toString())))
            .andExpect(jsonPath("$.[*].licenciaLaboral").value(hasItem(DEFAULT_LICENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].nivelSalarial").value(hasItem(DEFAULT_NIVEL_SALARIAL.toString())))
            .andExpect(jsonPath("$.[*].tipoLaborIndependiente").value(hasItem(DEFAULT_TIPO_LABOR_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoIndependiente").value(hasItem(DEFAULT_TIEMPO_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].nivelIngresosIndependiente").value(hasItem(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoDesempleado").value(hasItem(DEFAULT_TIEMPO_DESEMPLEADO.toString())))
            .andExpect(jsonPath("$.[*].quienFinanciaEstudios").value(hasItem(DEFAULT_QUIEN_FINANCIA_ESTUDIOS.toString())))
            .andExpect(jsonPath("$.[*].parentesco").value(hasItem(DEFAULT_PARENTESCO.toString())))
            .andExpect(jsonPath("$.[*].presupuestoDisponible").value(hasItem(DEFAULT_PRESUPUESTO_DISPONIBLE.toString())))
            .andExpect(jsonPath("$.[*].ahorroDisponible").value(hasItem(DEFAULT_AHORRO_DISPONIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].idioma").value(hasItem(DEFAULT_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificarIdioma").value(hasItem(DEFAULT_CERTIFICAR_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificacionIdioma").value(hasItem(DEFAULT_CERTIFICACION_IDIOMA.toString())))
            .andExpect(jsonPath("$.[*].puntajeCertificacion").value(hasItem(DEFAULT_PUNTAJE_CERTIFICACION.toString())))
            .andExpect(jsonPath("$.[*].salidasPais").value(hasItem(DEFAULT_SALIDAS_PAIS.toString())))
            .andExpect(jsonPath("$.[*].paisesVisitados").value(hasItem(DEFAULT_PAISES_VISITADOS.toString())))
            .andExpect(jsonPath("$.[*].visaPais").value(hasItem(DEFAULT_VISA_PAIS.toString())))
            .andExpect(jsonPath("$.[*].estudiadoAnterior").value(hasItem(DEFAULT_ESTUDIADO_ANTERIOR.booleanValue())))
            .andExpect(jsonPath("$.[*].fueraPais").value(hasItem(DEFAULT_FUERA_PAIS.booleanValue())))
            .andExpect(jsonPath("$.[*].paisFuera").value(hasItem(DEFAULT_PAIS_FUERA.toString())))
            .andExpect(jsonPath("$.[*].extenderEstadia").value(hasItem(DEFAULT_EXTENDER_ESTADIA.booleanValue())))
            .andExpect(jsonPath("$.[*].negadoVisa").value(hasItem(DEFAULT_NEGADO_VISA.booleanValue())))
            .andExpect(jsonPath("$.[*].paisNegado").value(hasItem(DEFAULT_PAIS_NEGADO.toString())))
            .andExpect(jsonPath("$.[*].familiaresDestino").value(hasItem(DEFAULT_FAMILIARES_DESTINO.booleanValue())))
            .andExpect(jsonPath("$.[*].estatusMigratorio").value(hasItem(DEFAULT_ESTATUS_MIGRATORIO.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apelliod").value(hasItem(DEFAULT_APELLIOD.toString())))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO.toString())));
    }
    
    @Test
    @Transactional
    public void getViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get the viabilidadVisa
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas/{id}", viabilidadVisa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(viabilidadVisa.getId().intValue()))
            .andExpect(jsonPath("$.destino").value(DEFAULT_DESTINO.toString()))
            .andExpect(jsonPath("$.tipoPrograma").value(DEFAULT_TIPO_PROGRAMA.toString()))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION.toString()))
            .andExpect(jsonPath("$.nacionalidadPrincipal").value(DEFAULT_NACIONALIDAD_PRINCIPAL.toString()))
            .andExpect(jsonPath("$.otraNacionalidad").value(DEFAULT_OTRA_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.genero").value(DEFAULT_GENERO.toString()))
            .andExpect(jsonPath("$.estadoCivil").value(DEFAULT_ESTADO_CIVIL.toString()))
            .andExpect(jsonPath("$.viajaAcompanado").value(DEFAULT_VIAJA_ACOMPANADO.booleanValue()))
            .andExpect(jsonPath("$.personasCargo").value(DEFAULT_PERSONAS_CARGO.toString()))
            .andExpect(jsonPath("$.nivelAcademico").value(DEFAULT_NIVEL_ACADEMICO.toString()))
            .andExpect(jsonPath("$.profesion").value(DEFAULT_PROFESION.toString()))
            .andExpect(jsonPath("$.fechaGadruacion").value(DEFAULT_FECHA_GADRUACION.toString()))
            .andExpect(jsonPath("$.ocupacionActual").value(DEFAULT_OCUPACION_ACTUAL.toString()))
            .andExpect(jsonPath("$.carta").value(DEFAULT_CARTA.booleanValue()))
            .andExpect(jsonPath("$.suspendidoEstudios").value(DEFAULT_SUSPENDIDO_ESTUDIOS.booleanValue()))
            .andExpect(jsonPath("$.peridoSupencionEstu").value(DEFAULT_PERIDO_SUPENCION_ESTU.toString()))
            .andExpect(jsonPath("$.razonSuspencion").value(DEFAULT_RAZON_SUSPENCION.toString()))
            .andExpect(jsonPath("$.tipoContrato").value(DEFAULT_TIPO_CONTRATO.toString()))
            .andExpect(jsonPath("$.licenciaLaboral").value(DEFAULT_LICENCIA_LABORAL.booleanValue()))
            .andExpect(jsonPath("$.nivelSalarial").value(DEFAULT_NIVEL_SALARIAL.toString()))
            .andExpect(jsonPath("$.tipoLaborIndependiente").value(DEFAULT_TIPO_LABOR_INDEPENDIENTE.toString()))
            .andExpect(jsonPath("$.tiempoIndependiente").value(DEFAULT_TIEMPO_INDEPENDIENTE.toString()))
            .andExpect(jsonPath("$.nivelIngresosIndependiente").value(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE.toString()))
            .andExpect(jsonPath("$.tiempoDesempleado").value(DEFAULT_TIEMPO_DESEMPLEADO.toString()))
            .andExpect(jsonPath("$.quienFinanciaEstudios").value(DEFAULT_QUIEN_FINANCIA_ESTUDIOS.toString()))
            .andExpect(jsonPath("$.parentesco").value(DEFAULT_PARENTESCO.toString()))
            .andExpect(jsonPath("$.presupuestoDisponible").value(DEFAULT_PRESUPUESTO_DISPONIBLE.toString()))
            .andExpect(jsonPath("$.ahorroDisponible").value(DEFAULT_AHORRO_DISPONIBLE.booleanValue()))
            .andExpect(jsonPath("$.idioma").value(DEFAULT_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.certificarIdioma").value(DEFAULT_CERTIFICAR_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.certificacionIdioma").value(DEFAULT_CERTIFICACION_IDIOMA.toString()))
            .andExpect(jsonPath("$.puntajeCertificacion").value(DEFAULT_PUNTAJE_CERTIFICACION.toString()))
            .andExpect(jsonPath("$.salidasPais").value(DEFAULT_SALIDAS_PAIS.toString()))
            .andExpect(jsonPath("$.paisesVisitados").value(DEFAULT_PAISES_VISITADOS.toString()))
            .andExpect(jsonPath("$.visaPais").value(DEFAULT_VISA_PAIS.toString()))
            .andExpect(jsonPath("$.estudiadoAnterior").value(DEFAULT_ESTUDIADO_ANTERIOR.booleanValue()))
            .andExpect(jsonPath("$.fueraPais").value(DEFAULT_FUERA_PAIS.booleanValue()))
            .andExpect(jsonPath("$.paisFuera").value(DEFAULT_PAIS_FUERA.toString()))
            .andExpect(jsonPath("$.extenderEstadia").value(DEFAULT_EXTENDER_ESTADIA.booleanValue()))
            .andExpect(jsonPath("$.negadoVisa").value(DEFAULT_NEGADO_VISA.booleanValue()))
            .andExpect(jsonPath("$.paisNegado").value(DEFAULT_PAIS_NEGADO.toString()))
            .andExpect(jsonPath("$.familiaresDestino").value(DEFAULT_FAMILIARES_DESTINO.booleanValue()))
            .andExpect(jsonPath("$.estatusMigratorio").value(DEFAULT_ESTATUS_MIGRATORIO.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apelliod").value(DEFAULT_APELLIOD.toString()))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO.toString()));
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByDestinoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where destino equals to DEFAULT_DESTINO
        defaultViabilidadVisaShouldBeFound("destino.equals=" + DEFAULT_DESTINO);

        // Get all the viabilidadVisaList where destino equals to UPDATED_DESTINO
        defaultViabilidadVisaShouldNotBeFound("destino.equals=" + UPDATED_DESTINO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByDestinoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where destino in DEFAULT_DESTINO or UPDATED_DESTINO
        defaultViabilidadVisaShouldBeFound("destino.in=" + DEFAULT_DESTINO + "," + UPDATED_DESTINO);

        // Get all the viabilidadVisaList where destino equals to UPDATED_DESTINO
        defaultViabilidadVisaShouldNotBeFound("destino.in=" + UPDATED_DESTINO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByDestinoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where destino is not null
        defaultViabilidadVisaShouldBeFound("destino.specified=true");

        // Get all the viabilidadVisaList where destino is null
        defaultViabilidadVisaShouldNotBeFound("destino.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoProgramaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoPrograma equals to DEFAULT_TIPO_PROGRAMA
        defaultViabilidadVisaShouldBeFound("tipoPrograma.equals=" + DEFAULT_TIPO_PROGRAMA);

        // Get all the viabilidadVisaList where tipoPrograma equals to UPDATED_TIPO_PROGRAMA
        defaultViabilidadVisaShouldNotBeFound("tipoPrograma.equals=" + UPDATED_TIPO_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoProgramaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoPrograma in DEFAULT_TIPO_PROGRAMA or UPDATED_TIPO_PROGRAMA
        defaultViabilidadVisaShouldBeFound("tipoPrograma.in=" + DEFAULT_TIPO_PROGRAMA + "," + UPDATED_TIPO_PROGRAMA);

        // Get all the viabilidadVisaList where tipoPrograma equals to UPDATED_TIPO_PROGRAMA
        defaultViabilidadVisaShouldNotBeFound("tipoPrograma.in=" + UPDATED_TIPO_PROGRAMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoProgramaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoPrograma is not null
        defaultViabilidadVisaShouldBeFound("tipoPrograma.specified=true");

        // Get all the viabilidadVisaList where tipoPrograma is null
        defaultViabilidadVisaShouldNotBeFound("tipoPrograma.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByDuracionIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where duracion equals to DEFAULT_DURACION
        defaultViabilidadVisaShouldBeFound("duracion.equals=" + DEFAULT_DURACION);

        // Get all the viabilidadVisaList where duracion equals to UPDATED_DURACION
        defaultViabilidadVisaShouldNotBeFound("duracion.equals=" + UPDATED_DURACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByDuracionIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where duracion in DEFAULT_DURACION or UPDATED_DURACION
        defaultViabilidadVisaShouldBeFound("duracion.in=" + DEFAULT_DURACION + "," + UPDATED_DURACION);

        // Get all the viabilidadVisaList where duracion equals to UPDATED_DURACION
        defaultViabilidadVisaShouldNotBeFound("duracion.in=" + UPDATED_DURACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByDuracionIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where duracion is not null
        defaultViabilidadVisaShouldBeFound("duracion.specified=true");

        // Get all the viabilidadVisaList where duracion is null
        defaultViabilidadVisaShouldNotBeFound("duracion.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNacionalidadPrincipalIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nacionalidadPrincipal equals to DEFAULT_NACIONALIDAD_PRINCIPAL
        defaultViabilidadVisaShouldBeFound("nacionalidadPrincipal.equals=" + DEFAULT_NACIONALIDAD_PRINCIPAL);

        // Get all the viabilidadVisaList where nacionalidadPrincipal equals to UPDATED_NACIONALIDAD_PRINCIPAL
        defaultViabilidadVisaShouldNotBeFound("nacionalidadPrincipal.equals=" + UPDATED_NACIONALIDAD_PRINCIPAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNacionalidadPrincipalIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nacionalidadPrincipal in DEFAULT_NACIONALIDAD_PRINCIPAL or UPDATED_NACIONALIDAD_PRINCIPAL
        defaultViabilidadVisaShouldBeFound("nacionalidadPrincipal.in=" + DEFAULT_NACIONALIDAD_PRINCIPAL + "," + UPDATED_NACIONALIDAD_PRINCIPAL);

        // Get all the viabilidadVisaList where nacionalidadPrincipal equals to UPDATED_NACIONALIDAD_PRINCIPAL
        defaultViabilidadVisaShouldNotBeFound("nacionalidadPrincipal.in=" + UPDATED_NACIONALIDAD_PRINCIPAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNacionalidadPrincipalIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nacionalidadPrincipal is not null
        defaultViabilidadVisaShouldBeFound("nacionalidadPrincipal.specified=true");

        // Get all the viabilidadVisaList where nacionalidadPrincipal is null
        defaultViabilidadVisaShouldNotBeFound("nacionalidadPrincipal.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByOtraNacionalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where otraNacionalidad equals to DEFAULT_OTRA_NACIONALIDAD
        defaultViabilidadVisaShouldBeFound("otraNacionalidad.equals=" + DEFAULT_OTRA_NACIONALIDAD);

        // Get all the viabilidadVisaList where otraNacionalidad equals to UPDATED_OTRA_NACIONALIDAD
        defaultViabilidadVisaShouldNotBeFound("otraNacionalidad.equals=" + UPDATED_OTRA_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByOtraNacionalidadIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where otraNacionalidad in DEFAULT_OTRA_NACIONALIDAD or UPDATED_OTRA_NACIONALIDAD
        defaultViabilidadVisaShouldBeFound("otraNacionalidad.in=" + DEFAULT_OTRA_NACIONALIDAD + "," + UPDATED_OTRA_NACIONALIDAD);

        // Get all the viabilidadVisaList where otraNacionalidad equals to UPDATED_OTRA_NACIONALIDAD
        defaultViabilidadVisaShouldNotBeFound("otraNacionalidad.in=" + UPDATED_OTRA_NACIONALIDAD);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByOtraNacionalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where otraNacionalidad is not null
        defaultViabilidadVisaShouldBeFound("otraNacionalidad.specified=true");

        // Get all the viabilidadVisaList where otraNacionalidad is null
        defaultViabilidadVisaShouldNotBeFound("otraNacionalidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaNacimientoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaNacimiento equals to DEFAULT_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldBeFound("fechaNacimiento.equals=" + DEFAULT_FECHA_NACIMIENTO);

        // Get all the viabilidadVisaList where fechaNacimiento equals to UPDATED_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldNotBeFound("fechaNacimiento.equals=" + UPDATED_FECHA_NACIMIENTO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaNacimientoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaNacimiento in DEFAULT_FECHA_NACIMIENTO or UPDATED_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldBeFound("fechaNacimiento.in=" + DEFAULT_FECHA_NACIMIENTO + "," + UPDATED_FECHA_NACIMIENTO);

        // Get all the viabilidadVisaList where fechaNacimiento equals to UPDATED_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldNotBeFound("fechaNacimiento.in=" + UPDATED_FECHA_NACIMIENTO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaNacimientoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaNacimiento is not null
        defaultViabilidadVisaShouldBeFound("fechaNacimiento.specified=true");

        // Get all the viabilidadVisaList where fechaNacimiento is null
        defaultViabilidadVisaShouldNotBeFound("fechaNacimiento.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaNacimientoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaNacimiento greater than or equals to DEFAULT_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldBeFound("fechaNacimiento.greaterOrEqualThan=" + DEFAULT_FECHA_NACIMIENTO);

        // Get all the viabilidadVisaList where fechaNacimiento greater than or equals to UPDATED_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldNotBeFound("fechaNacimiento.greaterOrEqualThan=" + UPDATED_FECHA_NACIMIENTO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaNacimientoIsLessThanSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaNacimiento less than or equals to DEFAULT_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldNotBeFound("fechaNacimiento.lessThan=" + DEFAULT_FECHA_NACIMIENTO);

        // Get all the viabilidadVisaList where fechaNacimiento less than or equals to UPDATED_FECHA_NACIMIENTO
        defaultViabilidadVisaShouldBeFound("fechaNacimiento.lessThan=" + UPDATED_FECHA_NACIMIENTO);
    }


    @Test
    @Transactional
    public void getAllViabilidadVisasByGeneroIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where genero equals to DEFAULT_GENERO
        defaultViabilidadVisaShouldBeFound("genero.equals=" + DEFAULT_GENERO);

        // Get all the viabilidadVisaList where genero equals to UPDATED_GENERO
        defaultViabilidadVisaShouldNotBeFound("genero.equals=" + UPDATED_GENERO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByGeneroIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where genero in DEFAULT_GENERO or UPDATED_GENERO
        defaultViabilidadVisaShouldBeFound("genero.in=" + DEFAULT_GENERO + "," + UPDATED_GENERO);

        // Get all the viabilidadVisaList where genero equals to UPDATED_GENERO
        defaultViabilidadVisaShouldNotBeFound("genero.in=" + UPDATED_GENERO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByGeneroIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where genero is not null
        defaultViabilidadVisaShouldBeFound("genero.specified=true");

        // Get all the viabilidadVisaList where genero is null
        defaultViabilidadVisaShouldNotBeFound("genero.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstadoCivilIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estadoCivil equals to DEFAULT_ESTADO_CIVIL
        defaultViabilidadVisaShouldBeFound("estadoCivil.equals=" + DEFAULT_ESTADO_CIVIL);

        // Get all the viabilidadVisaList where estadoCivil equals to UPDATED_ESTADO_CIVIL
        defaultViabilidadVisaShouldNotBeFound("estadoCivil.equals=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstadoCivilIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estadoCivil in DEFAULT_ESTADO_CIVIL or UPDATED_ESTADO_CIVIL
        defaultViabilidadVisaShouldBeFound("estadoCivil.in=" + DEFAULT_ESTADO_CIVIL + "," + UPDATED_ESTADO_CIVIL);

        // Get all the viabilidadVisaList where estadoCivil equals to UPDATED_ESTADO_CIVIL
        defaultViabilidadVisaShouldNotBeFound("estadoCivil.in=" + UPDATED_ESTADO_CIVIL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstadoCivilIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estadoCivil is not null
        defaultViabilidadVisaShouldBeFound("estadoCivil.specified=true");

        // Get all the viabilidadVisaList where estadoCivil is null
        defaultViabilidadVisaShouldNotBeFound("estadoCivil.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByViajaAcompanadoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where viajaAcompanado equals to DEFAULT_VIAJA_ACOMPANADO
        defaultViabilidadVisaShouldBeFound("viajaAcompanado.equals=" + DEFAULT_VIAJA_ACOMPANADO);

        // Get all the viabilidadVisaList where viajaAcompanado equals to UPDATED_VIAJA_ACOMPANADO
        defaultViabilidadVisaShouldNotBeFound("viajaAcompanado.equals=" + UPDATED_VIAJA_ACOMPANADO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByViajaAcompanadoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where viajaAcompanado in DEFAULT_VIAJA_ACOMPANADO or UPDATED_VIAJA_ACOMPANADO
        defaultViabilidadVisaShouldBeFound("viajaAcompanado.in=" + DEFAULT_VIAJA_ACOMPANADO + "," + UPDATED_VIAJA_ACOMPANADO);

        // Get all the viabilidadVisaList where viajaAcompanado equals to UPDATED_VIAJA_ACOMPANADO
        defaultViabilidadVisaShouldNotBeFound("viajaAcompanado.in=" + UPDATED_VIAJA_ACOMPANADO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByViajaAcompanadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where viajaAcompanado is not null
        defaultViabilidadVisaShouldBeFound("viajaAcompanado.specified=true");

        // Get all the viabilidadVisaList where viajaAcompanado is null
        defaultViabilidadVisaShouldNotBeFound("viajaAcompanado.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPersonasCargoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where personasCargo equals to DEFAULT_PERSONAS_CARGO
        defaultViabilidadVisaShouldBeFound("personasCargo.equals=" + DEFAULT_PERSONAS_CARGO);

        // Get all the viabilidadVisaList where personasCargo equals to UPDATED_PERSONAS_CARGO
        defaultViabilidadVisaShouldNotBeFound("personasCargo.equals=" + UPDATED_PERSONAS_CARGO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPersonasCargoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where personasCargo in DEFAULT_PERSONAS_CARGO or UPDATED_PERSONAS_CARGO
        defaultViabilidadVisaShouldBeFound("personasCargo.in=" + DEFAULT_PERSONAS_CARGO + "," + UPDATED_PERSONAS_CARGO);

        // Get all the viabilidadVisaList where personasCargo equals to UPDATED_PERSONAS_CARGO
        defaultViabilidadVisaShouldNotBeFound("personasCargo.in=" + UPDATED_PERSONAS_CARGO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPersonasCargoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where personasCargo is not null
        defaultViabilidadVisaShouldBeFound("personasCargo.specified=true");

        // Get all the viabilidadVisaList where personasCargo is null
        defaultViabilidadVisaShouldNotBeFound("personasCargo.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelAcademicoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelAcademico equals to DEFAULT_NIVEL_ACADEMICO
        defaultViabilidadVisaShouldBeFound("nivelAcademico.equals=" + DEFAULT_NIVEL_ACADEMICO);

        // Get all the viabilidadVisaList where nivelAcademico equals to UPDATED_NIVEL_ACADEMICO
        defaultViabilidadVisaShouldNotBeFound("nivelAcademico.equals=" + UPDATED_NIVEL_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelAcademicoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelAcademico in DEFAULT_NIVEL_ACADEMICO or UPDATED_NIVEL_ACADEMICO
        defaultViabilidadVisaShouldBeFound("nivelAcademico.in=" + DEFAULT_NIVEL_ACADEMICO + "," + UPDATED_NIVEL_ACADEMICO);

        // Get all the viabilidadVisaList where nivelAcademico equals to UPDATED_NIVEL_ACADEMICO
        defaultViabilidadVisaShouldNotBeFound("nivelAcademico.in=" + UPDATED_NIVEL_ACADEMICO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelAcademicoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelAcademico is not null
        defaultViabilidadVisaShouldBeFound("nivelAcademico.specified=true");

        // Get all the viabilidadVisaList where nivelAcademico is null
        defaultViabilidadVisaShouldNotBeFound("nivelAcademico.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByProfesionIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where profesion equals to DEFAULT_PROFESION
        defaultViabilidadVisaShouldBeFound("profesion.equals=" + DEFAULT_PROFESION);

        // Get all the viabilidadVisaList where profesion equals to UPDATED_PROFESION
        defaultViabilidadVisaShouldNotBeFound("profesion.equals=" + UPDATED_PROFESION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByProfesionIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where profesion in DEFAULT_PROFESION or UPDATED_PROFESION
        defaultViabilidadVisaShouldBeFound("profesion.in=" + DEFAULT_PROFESION + "," + UPDATED_PROFESION);

        // Get all the viabilidadVisaList where profesion equals to UPDATED_PROFESION
        defaultViabilidadVisaShouldNotBeFound("profesion.in=" + UPDATED_PROFESION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByProfesionIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where profesion is not null
        defaultViabilidadVisaShouldBeFound("profesion.specified=true");

        // Get all the viabilidadVisaList where profesion is null
        defaultViabilidadVisaShouldNotBeFound("profesion.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaGadruacionIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaGadruacion equals to DEFAULT_FECHA_GADRUACION
        defaultViabilidadVisaShouldBeFound("fechaGadruacion.equals=" + DEFAULT_FECHA_GADRUACION);

        // Get all the viabilidadVisaList where fechaGadruacion equals to UPDATED_FECHA_GADRUACION
        defaultViabilidadVisaShouldNotBeFound("fechaGadruacion.equals=" + UPDATED_FECHA_GADRUACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaGadruacionIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaGadruacion in DEFAULT_FECHA_GADRUACION or UPDATED_FECHA_GADRUACION
        defaultViabilidadVisaShouldBeFound("fechaGadruacion.in=" + DEFAULT_FECHA_GADRUACION + "," + UPDATED_FECHA_GADRUACION);

        // Get all the viabilidadVisaList where fechaGadruacion equals to UPDATED_FECHA_GADRUACION
        defaultViabilidadVisaShouldNotBeFound("fechaGadruacion.in=" + UPDATED_FECHA_GADRUACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaGadruacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaGadruacion is not null
        defaultViabilidadVisaShouldBeFound("fechaGadruacion.specified=true");

        // Get all the viabilidadVisaList where fechaGadruacion is null
        defaultViabilidadVisaShouldNotBeFound("fechaGadruacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaGadruacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaGadruacion greater than or equals to DEFAULT_FECHA_GADRUACION
        defaultViabilidadVisaShouldBeFound("fechaGadruacion.greaterOrEqualThan=" + DEFAULT_FECHA_GADRUACION);

        // Get all the viabilidadVisaList where fechaGadruacion greater than or equals to UPDATED_FECHA_GADRUACION
        defaultViabilidadVisaShouldNotBeFound("fechaGadruacion.greaterOrEqualThan=" + UPDATED_FECHA_GADRUACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFechaGadruacionIsLessThanSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fechaGadruacion less than or equals to DEFAULT_FECHA_GADRUACION
        defaultViabilidadVisaShouldNotBeFound("fechaGadruacion.lessThan=" + DEFAULT_FECHA_GADRUACION);

        // Get all the viabilidadVisaList where fechaGadruacion less than or equals to UPDATED_FECHA_GADRUACION
        defaultViabilidadVisaShouldBeFound("fechaGadruacion.lessThan=" + UPDATED_FECHA_GADRUACION);
    }


    @Test
    @Transactional
    public void getAllViabilidadVisasByOcupacionActualIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where ocupacionActual equals to DEFAULT_OCUPACION_ACTUAL
        defaultViabilidadVisaShouldBeFound("ocupacionActual.equals=" + DEFAULT_OCUPACION_ACTUAL);

        // Get all the viabilidadVisaList where ocupacionActual equals to UPDATED_OCUPACION_ACTUAL
        defaultViabilidadVisaShouldNotBeFound("ocupacionActual.equals=" + UPDATED_OCUPACION_ACTUAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByOcupacionActualIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where ocupacionActual in DEFAULT_OCUPACION_ACTUAL or UPDATED_OCUPACION_ACTUAL
        defaultViabilidadVisaShouldBeFound("ocupacionActual.in=" + DEFAULT_OCUPACION_ACTUAL + "," + UPDATED_OCUPACION_ACTUAL);

        // Get all the viabilidadVisaList where ocupacionActual equals to UPDATED_OCUPACION_ACTUAL
        defaultViabilidadVisaShouldNotBeFound("ocupacionActual.in=" + UPDATED_OCUPACION_ACTUAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByOcupacionActualIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where ocupacionActual is not null
        defaultViabilidadVisaShouldBeFound("ocupacionActual.specified=true");

        // Get all the viabilidadVisaList where ocupacionActual is null
        defaultViabilidadVisaShouldNotBeFound("ocupacionActual.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCartaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where carta equals to DEFAULT_CARTA
        defaultViabilidadVisaShouldBeFound("carta.equals=" + DEFAULT_CARTA);

        // Get all the viabilidadVisaList where carta equals to UPDATED_CARTA
        defaultViabilidadVisaShouldNotBeFound("carta.equals=" + UPDATED_CARTA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCartaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where carta in DEFAULT_CARTA or UPDATED_CARTA
        defaultViabilidadVisaShouldBeFound("carta.in=" + DEFAULT_CARTA + "," + UPDATED_CARTA);

        // Get all the viabilidadVisaList where carta equals to UPDATED_CARTA
        defaultViabilidadVisaShouldNotBeFound("carta.in=" + UPDATED_CARTA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCartaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where carta is not null
        defaultViabilidadVisaShouldBeFound("carta.specified=true");

        // Get all the viabilidadVisaList where carta is null
        defaultViabilidadVisaShouldNotBeFound("carta.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasBySuspendidoEstudiosIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where suspendidoEstudios equals to DEFAULT_SUSPENDIDO_ESTUDIOS
        defaultViabilidadVisaShouldBeFound("suspendidoEstudios.equals=" + DEFAULT_SUSPENDIDO_ESTUDIOS);

        // Get all the viabilidadVisaList where suspendidoEstudios equals to UPDATED_SUSPENDIDO_ESTUDIOS
        defaultViabilidadVisaShouldNotBeFound("suspendidoEstudios.equals=" + UPDATED_SUSPENDIDO_ESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasBySuspendidoEstudiosIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where suspendidoEstudios in DEFAULT_SUSPENDIDO_ESTUDIOS or UPDATED_SUSPENDIDO_ESTUDIOS
        defaultViabilidadVisaShouldBeFound("suspendidoEstudios.in=" + DEFAULT_SUSPENDIDO_ESTUDIOS + "," + UPDATED_SUSPENDIDO_ESTUDIOS);

        // Get all the viabilidadVisaList where suspendidoEstudios equals to UPDATED_SUSPENDIDO_ESTUDIOS
        defaultViabilidadVisaShouldNotBeFound("suspendidoEstudios.in=" + UPDATED_SUSPENDIDO_ESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasBySuspendidoEstudiosIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where suspendidoEstudios is not null
        defaultViabilidadVisaShouldBeFound("suspendidoEstudios.specified=true");

        // Get all the viabilidadVisaList where suspendidoEstudios is null
        defaultViabilidadVisaShouldNotBeFound("suspendidoEstudios.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPeridoSupencionEstuIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where peridoSupencionEstu equals to DEFAULT_PERIDO_SUPENCION_ESTU
        defaultViabilidadVisaShouldBeFound("peridoSupencionEstu.equals=" + DEFAULT_PERIDO_SUPENCION_ESTU);

        // Get all the viabilidadVisaList where peridoSupencionEstu equals to UPDATED_PERIDO_SUPENCION_ESTU
        defaultViabilidadVisaShouldNotBeFound("peridoSupencionEstu.equals=" + UPDATED_PERIDO_SUPENCION_ESTU);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPeridoSupencionEstuIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where peridoSupencionEstu in DEFAULT_PERIDO_SUPENCION_ESTU or UPDATED_PERIDO_SUPENCION_ESTU
        defaultViabilidadVisaShouldBeFound("peridoSupencionEstu.in=" + DEFAULT_PERIDO_SUPENCION_ESTU + "," + UPDATED_PERIDO_SUPENCION_ESTU);

        // Get all the viabilidadVisaList where peridoSupencionEstu equals to UPDATED_PERIDO_SUPENCION_ESTU
        defaultViabilidadVisaShouldNotBeFound("peridoSupencionEstu.in=" + UPDATED_PERIDO_SUPENCION_ESTU);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPeridoSupencionEstuIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where peridoSupencionEstu is not null
        defaultViabilidadVisaShouldBeFound("peridoSupencionEstu.specified=true");

        // Get all the viabilidadVisaList where peridoSupencionEstu is null
        defaultViabilidadVisaShouldNotBeFound("peridoSupencionEstu.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByRazonSuspencionIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where razonSuspencion equals to DEFAULT_RAZON_SUSPENCION
        defaultViabilidadVisaShouldBeFound("razonSuspencion.equals=" + DEFAULT_RAZON_SUSPENCION);

        // Get all the viabilidadVisaList where razonSuspencion equals to UPDATED_RAZON_SUSPENCION
        defaultViabilidadVisaShouldNotBeFound("razonSuspencion.equals=" + UPDATED_RAZON_SUSPENCION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByRazonSuspencionIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where razonSuspencion in DEFAULT_RAZON_SUSPENCION or UPDATED_RAZON_SUSPENCION
        defaultViabilidadVisaShouldBeFound("razonSuspencion.in=" + DEFAULT_RAZON_SUSPENCION + "," + UPDATED_RAZON_SUSPENCION);

        // Get all the viabilidadVisaList where razonSuspencion equals to UPDATED_RAZON_SUSPENCION
        defaultViabilidadVisaShouldNotBeFound("razonSuspencion.in=" + UPDATED_RAZON_SUSPENCION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByRazonSuspencionIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where razonSuspencion is not null
        defaultViabilidadVisaShouldBeFound("razonSuspencion.specified=true");

        // Get all the viabilidadVisaList where razonSuspencion is null
        defaultViabilidadVisaShouldNotBeFound("razonSuspencion.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoContrato equals to DEFAULT_TIPO_CONTRATO
        defaultViabilidadVisaShouldBeFound("tipoContrato.equals=" + DEFAULT_TIPO_CONTRATO);

        // Get all the viabilidadVisaList where tipoContrato equals to UPDATED_TIPO_CONTRATO
        defaultViabilidadVisaShouldNotBeFound("tipoContrato.equals=" + UPDATED_TIPO_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoContratoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoContrato in DEFAULT_TIPO_CONTRATO or UPDATED_TIPO_CONTRATO
        defaultViabilidadVisaShouldBeFound("tipoContrato.in=" + DEFAULT_TIPO_CONTRATO + "," + UPDATED_TIPO_CONTRATO);

        // Get all the viabilidadVisaList where tipoContrato equals to UPDATED_TIPO_CONTRATO
        defaultViabilidadVisaShouldNotBeFound("tipoContrato.in=" + UPDATED_TIPO_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoContratoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoContrato is not null
        defaultViabilidadVisaShouldBeFound("tipoContrato.specified=true");

        // Get all the viabilidadVisaList where tipoContrato is null
        defaultViabilidadVisaShouldNotBeFound("tipoContrato.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByLicenciaLaboralIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where licenciaLaboral equals to DEFAULT_LICENCIA_LABORAL
        defaultViabilidadVisaShouldBeFound("licenciaLaboral.equals=" + DEFAULT_LICENCIA_LABORAL);

        // Get all the viabilidadVisaList where licenciaLaboral equals to UPDATED_LICENCIA_LABORAL
        defaultViabilidadVisaShouldNotBeFound("licenciaLaboral.equals=" + UPDATED_LICENCIA_LABORAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByLicenciaLaboralIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where licenciaLaboral in DEFAULT_LICENCIA_LABORAL or UPDATED_LICENCIA_LABORAL
        defaultViabilidadVisaShouldBeFound("licenciaLaboral.in=" + DEFAULT_LICENCIA_LABORAL + "," + UPDATED_LICENCIA_LABORAL);

        // Get all the viabilidadVisaList where licenciaLaboral equals to UPDATED_LICENCIA_LABORAL
        defaultViabilidadVisaShouldNotBeFound("licenciaLaboral.in=" + UPDATED_LICENCIA_LABORAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByLicenciaLaboralIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where licenciaLaboral is not null
        defaultViabilidadVisaShouldBeFound("licenciaLaboral.specified=true");

        // Get all the viabilidadVisaList where licenciaLaboral is null
        defaultViabilidadVisaShouldNotBeFound("licenciaLaboral.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelSalarialIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelSalarial equals to DEFAULT_NIVEL_SALARIAL
        defaultViabilidadVisaShouldBeFound("nivelSalarial.equals=" + DEFAULT_NIVEL_SALARIAL);

        // Get all the viabilidadVisaList where nivelSalarial equals to UPDATED_NIVEL_SALARIAL
        defaultViabilidadVisaShouldNotBeFound("nivelSalarial.equals=" + UPDATED_NIVEL_SALARIAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelSalarialIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelSalarial in DEFAULT_NIVEL_SALARIAL or UPDATED_NIVEL_SALARIAL
        defaultViabilidadVisaShouldBeFound("nivelSalarial.in=" + DEFAULT_NIVEL_SALARIAL + "," + UPDATED_NIVEL_SALARIAL);

        // Get all the viabilidadVisaList where nivelSalarial equals to UPDATED_NIVEL_SALARIAL
        defaultViabilidadVisaShouldNotBeFound("nivelSalarial.in=" + UPDATED_NIVEL_SALARIAL);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelSalarialIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelSalarial is not null
        defaultViabilidadVisaShouldBeFound("nivelSalarial.specified=true");

        // Get all the viabilidadVisaList where nivelSalarial is null
        defaultViabilidadVisaShouldNotBeFound("nivelSalarial.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoLaborIndependienteIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoLaborIndependiente equals to DEFAULT_TIPO_LABOR_INDEPENDIENTE
        defaultViabilidadVisaShouldBeFound("tipoLaborIndependiente.equals=" + DEFAULT_TIPO_LABOR_INDEPENDIENTE);

        // Get all the viabilidadVisaList where tipoLaborIndependiente equals to UPDATED_TIPO_LABOR_INDEPENDIENTE
        defaultViabilidadVisaShouldNotBeFound("tipoLaborIndependiente.equals=" + UPDATED_TIPO_LABOR_INDEPENDIENTE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoLaborIndependienteIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoLaborIndependiente in DEFAULT_TIPO_LABOR_INDEPENDIENTE or UPDATED_TIPO_LABOR_INDEPENDIENTE
        defaultViabilidadVisaShouldBeFound("tipoLaborIndependiente.in=" + DEFAULT_TIPO_LABOR_INDEPENDIENTE + "," + UPDATED_TIPO_LABOR_INDEPENDIENTE);

        // Get all the viabilidadVisaList where tipoLaborIndependiente equals to UPDATED_TIPO_LABOR_INDEPENDIENTE
        defaultViabilidadVisaShouldNotBeFound("tipoLaborIndependiente.in=" + UPDATED_TIPO_LABOR_INDEPENDIENTE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTipoLaborIndependienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tipoLaborIndependiente is not null
        defaultViabilidadVisaShouldBeFound("tipoLaborIndependiente.specified=true");

        // Get all the viabilidadVisaList where tipoLaborIndependiente is null
        defaultViabilidadVisaShouldNotBeFound("tipoLaborIndependiente.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTiempoIndependienteIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tiempoIndependiente equals to DEFAULT_TIEMPO_INDEPENDIENTE
        defaultViabilidadVisaShouldBeFound("tiempoIndependiente.equals=" + DEFAULT_TIEMPO_INDEPENDIENTE);

        // Get all the viabilidadVisaList where tiempoIndependiente equals to UPDATED_TIEMPO_INDEPENDIENTE
        defaultViabilidadVisaShouldNotBeFound("tiempoIndependiente.equals=" + UPDATED_TIEMPO_INDEPENDIENTE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTiempoIndependienteIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tiempoIndependiente in DEFAULT_TIEMPO_INDEPENDIENTE or UPDATED_TIEMPO_INDEPENDIENTE
        defaultViabilidadVisaShouldBeFound("tiempoIndependiente.in=" + DEFAULT_TIEMPO_INDEPENDIENTE + "," + UPDATED_TIEMPO_INDEPENDIENTE);

        // Get all the viabilidadVisaList where tiempoIndependiente equals to UPDATED_TIEMPO_INDEPENDIENTE
        defaultViabilidadVisaShouldNotBeFound("tiempoIndependiente.in=" + UPDATED_TIEMPO_INDEPENDIENTE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTiempoIndependienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tiempoIndependiente is not null
        defaultViabilidadVisaShouldBeFound("tiempoIndependiente.specified=true");

        // Get all the viabilidadVisaList where tiempoIndependiente is null
        defaultViabilidadVisaShouldNotBeFound("tiempoIndependiente.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelIngresosIndependienteIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelIngresosIndependiente equals to DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE
        defaultViabilidadVisaShouldBeFound("nivelIngresosIndependiente.equals=" + DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE);

        // Get all the viabilidadVisaList where nivelIngresosIndependiente equals to UPDATED_NIVEL_INGRESOS_INDEPENDIENTE
        defaultViabilidadVisaShouldNotBeFound("nivelIngresosIndependiente.equals=" + UPDATED_NIVEL_INGRESOS_INDEPENDIENTE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelIngresosIndependienteIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelIngresosIndependiente in DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE or UPDATED_NIVEL_INGRESOS_INDEPENDIENTE
        defaultViabilidadVisaShouldBeFound("nivelIngresosIndependiente.in=" + DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE + "," + UPDATED_NIVEL_INGRESOS_INDEPENDIENTE);

        // Get all the viabilidadVisaList where nivelIngresosIndependiente equals to UPDATED_NIVEL_INGRESOS_INDEPENDIENTE
        defaultViabilidadVisaShouldNotBeFound("nivelIngresosIndependiente.in=" + UPDATED_NIVEL_INGRESOS_INDEPENDIENTE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNivelIngresosIndependienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nivelIngresosIndependiente is not null
        defaultViabilidadVisaShouldBeFound("nivelIngresosIndependiente.specified=true");

        // Get all the viabilidadVisaList where nivelIngresosIndependiente is null
        defaultViabilidadVisaShouldNotBeFound("nivelIngresosIndependiente.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTiempoDesempleadoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tiempoDesempleado equals to DEFAULT_TIEMPO_DESEMPLEADO
        defaultViabilidadVisaShouldBeFound("tiempoDesempleado.equals=" + DEFAULT_TIEMPO_DESEMPLEADO);

        // Get all the viabilidadVisaList where tiempoDesempleado equals to UPDATED_TIEMPO_DESEMPLEADO
        defaultViabilidadVisaShouldNotBeFound("tiempoDesempleado.equals=" + UPDATED_TIEMPO_DESEMPLEADO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTiempoDesempleadoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tiempoDesempleado in DEFAULT_TIEMPO_DESEMPLEADO or UPDATED_TIEMPO_DESEMPLEADO
        defaultViabilidadVisaShouldBeFound("tiempoDesempleado.in=" + DEFAULT_TIEMPO_DESEMPLEADO + "," + UPDATED_TIEMPO_DESEMPLEADO);

        // Get all the viabilidadVisaList where tiempoDesempleado equals to UPDATED_TIEMPO_DESEMPLEADO
        defaultViabilidadVisaShouldNotBeFound("tiempoDesempleado.in=" + UPDATED_TIEMPO_DESEMPLEADO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByTiempoDesempleadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where tiempoDesempleado is not null
        defaultViabilidadVisaShouldBeFound("tiempoDesempleado.specified=true");

        // Get all the viabilidadVisaList where tiempoDesempleado is null
        defaultViabilidadVisaShouldNotBeFound("tiempoDesempleado.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByQuienFinanciaEstudiosIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where quienFinanciaEstudios equals to DEFAULT_QUIEN_FINANCIA_ESTUDIOS
        defaultViabilidadVisaShouldBeFound("quienFinanciaEstudios.equals=" + DEFAULT_QUIEN_FINANCIA_ESTUDIOS);

        // Get all the viabilidadVisaList where quienFinanciaEstudios equals to UPDATED_QUIEN_FINANCIA_ESTUDIOS
        defaultViabilidadVisaShouldNotBeFound("quienFinanciaEstudios.equals=" + UPDATED_QUIEN_FINANCIA_ESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByQuienFinanciaEstudiosIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where quienFinanciaEstudios in DEFAULT_QUIEN_FINANCIA_ESTUDIOS or UPDATED_QUIEN_FINANCIA_ESTUDIOS
        defaultViabilidadVisaShouldBeFound("quienFinanciaEstudios.in=" + DEFAULT_QUIEN_FINANCIA_ESTUDIOS + "," + UPDATED_QUIEN_FINANCIA_ESTUDIOS);

        // Get all the viabilidadVisaList where quienFinanciaEstudios equals to UPDATED_QUIEN_FINANCIA_ESTUDIOS
        defaultViabilidadVisaShouldNotBeFound("quienFinanciaEstudios.in=" + UPDATED_QUIEN_FINANCIA_ESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByQuienFinanciaEstudiosIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where quienFinanciaEstudios is not null
        defaultViabilidadVisaShouldBeFound("quienFinanciaEstudios.specified=true");

        // Get all the viabilidadVisaList where quienFinanciaEstudios is null
        defaultViabilidadVisaShouldNotBeFound("quienFinanciaEstudios.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByParentescoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where parentesco equals to DEFAULT_PARENTESCO
        defaultViabilidadVisaShouldBeFound("parentesco.equals=" + DEFAULT_PARENTESCO);

        // Get all the viabilidadVisaList where parentesco equals to UPDATED_PARENTESCO
        defaultViabilidadVisaShouldNotBeFound("parentesco.equals=" + UPDATED_PARENTESCO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByParentescoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where parentesco in DEFAULT_PARENTESCO or UPDATED_PARENTESCO
        defaultViabilidadVisaShouldBeFound("parentesco.in=" + DEFAULT_PARENTESCO + "," + UPDATED_PARENTESCO);

        // Get all the viabilidadVisaList where parentesco equals to UPDATED_PARENTESCO
        defaultViabilidadVisaShouldNotBeFound("parentesco.in=" + UPDATED_PARENTESCO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByParentescoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where parentesco is not null
        defaultViabilidadVisaShouldBeFound("parentesco.specified=true");

        // Get all the viabilidadVisaList where parentesco is null
        defaultViabilidadVisaShouldNotBeFound("parentesco.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPresupuestoDisponibleIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where presupuestoDisponible equals to DEFAULT_PRESUPUESTO_DISPONIBLE
        defaultViabilidadVisaShouldBeFound("presupuestoDisponible.equals=" + DEFAULT_PRESUPUESTO_DISPONIBLE);

        // Get all the viabilidadVisaList where presupuestoDisponible equals to UPDATED_PRESUPUESTO_DISPONIBLE
        defaultViabilidadVisaShouldNotBeFound("presupuestoDisponible.equals=" + UPDATED_PRESUPUESTO_DISPONIBLE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPresupuestoDisponibleIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where presupuestoDisponible in DEFAULT_PRESUPUESTO_DISPONIBLE or UPDATED_PRESUPUESTO_DISPONIBLE
        defaultViabilidadVisaShouldBeFound("presupuestoDisponible.in=" + DEFAULT_PRESUPUESTO_DISPONIBLE + "," + UPDATED_PRESUPUESTO_DISPONIBLE);

        // Get all the viabilidadVisaList where presupuestoDisponible equals to UPDATED_PRESUPUESTO_DISPONIBLE
        defaultViabilidadVisaShouldNotBeFound("presupuestoDisponible.in=" + UPDATED_PRESUPUESTO_DISPONIBLE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPresupuestoDisponibleIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where presupuestoDisponible is not null
        defaultViabilidadVisaShouldBeFound("presupuestoDisponible.specified=true");

        // Get all the viabilidadVisaList where presupuestoDisponible is null
        defaultViabilidadVisaShouldNotBeFound("presupuestoDisponible.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByAhorroDisponibleIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where ahorroDisponible equals to DEFAULT_AHORRO_DISPONIBLE
        defaultViabilidadVisaShouldBeFound("ahorroDisponible.equals=" + DEFAULT_AHORRO_DISPONIBLE);

        // Get all the viabilidadVisaList where ahorroDisponible equals to UPDATED_AHORRO_DISPONIBLE
        defaultViabilidadVisaShouldNotBeFound("ahorroDisponible.equals=" + UPDATED_AHORRO_DISPONIBLE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByAhorroDisponibleIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where ahorroDisponible in DEFAULT_AHORRO_DISPONIBLE or UPDATED_AHORRO_DISPONIBLE
        defaultViabilidadVisaShouldBeFound("ahorroDisponible.in=" + DEFAULT_AHORRO_DISPONIBLE + "," + UPDATED_AHORRO_DISPONIBLE);

        // Get all the viabilidadVisaList where ahorroDisponible equals to UPDATED_AHORRO_DISPONIBLE
        defaultViabilidadVisaShouldNotBeFound("ahorroDisponible.in=" + UPDATED_AHORRO_DISPONIBLE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByAhorroDisponibleIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where ahorroDisponible is not null
        defaultViabilidadVisaShouldBeFound("ahorroDisponible.specified=true");

        // Get all the viabilidadVisaList where ahorroDisponible is null
        defaultViabilidadVisaShouldNotBeFound("ahorroDisponible.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByIdiomaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where idioma equals to DEFAULT_IDIOMA
        defaultViabilidadVisaShouldBeFound("idioma.equals=" + DEFAULT_IDIOMA);

        // Get all the viabilidadVisaList where idioma equals to UPDATED_IDIOMA
        defaultViabilidadVisaShouldNotBeFound("idioma.equals=" + UPDATED_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByIdiomaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where idioma in DEFAULT_IDIOMA or UPDATED_IDIOMA
        defaultViabilidadVisaShouldBeFound("idioma.in=" + DEFAULT_IDIOMA + "," + UPDATED_IDIOMA);

        // Get all the viabilidadVisaList where idioma equals to UPDATED_IDIOMA
        defaultViabilidadVisaShouldNotBeFound("idioma.in=" + UPDATED_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByIdiomaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where idioma is not null
        defaultViabilidadVisaShouldBeFound("idioma.specified=true");

        // Get all the viabilidadVisaList where idioma is null
        defaultViabilidadVisaShouldNotBeFound("idioma.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCertificarIdiomaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where certificarIdioma equals to DEFAULT_CERTIFICAR_IDIOMA
        defaultViabilidadVisaShouldBeFound("certificarIdioma.equals=" + DEFAULT_CERTIFICAR_IDIOMA);

        // Get all the viabilidadVisaList where certificarIdioma equals to UPDATED_CERTIFICAR_IDIOMA
        defaultViabilidadVisaShouldNotBeFound("certificarIdioma.equals=" + UPDATED_CERTIFICAR_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCertificarIdiomaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where certificarIdioma in DEFAULT_CERTIFICAR_IDIOMA or UPDATED_CERTIFICAR_IDIOMA
        defaultViabilidadVisaShouldBeFound("certificarIdioma.in=" + DEFAULT_CERTIFICAR_IDIOMA + "," + UPDATED_CERTIFICAR_IDIOMA);

        // Get all the viabilidadVisaList where certificarIdioma equals to UPDATED_CERTIFICAR_IDIOMA
        defaultViabilidadVisaShouldNotBeFound("certificarIdioma.in=" + UPDATED_CERTIFICAR_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCertificarIdiomaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where certificarIdioma is not null
        defaultViabilidadVisaShouldBeFound("certificarIdioma.specified=true");

        // Get all the viabilidadVisaList where certificarIdioma is null
        defaultViabilidadVisaShouldNotBeFound("certificarIdioma.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCertificacionIdiomaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where certificacionIdioma equals to DEFAULT_CERTIFICACION_IDIOMA
        defaultViabilidadVisaShouldBeFound("certificacionIdioma.equals=" + DEFAULT_CERTIFICACION_IDIOMA);

        // Get all the viabilidadVisaList where certificacionIdioma equals to UPDATED_CERTIFICACION_IDIOMA
        defaultViabilidadVisaShouldNotBeFound("certificacionIdioma.equals=" + UPDATED_CERTIFICACION_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCertificacionIdiomaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where certificacionIdioma in DEFAULT_CERTIFICACION_IDIOMA or UPDATED_CERTIFICACION_IDIOMA
        defaultViabilidadVisaShouldBeFound("certificacionIdioma.in=" + DEFAULT_CERTIFICACION_IDIOMA + "," + UPDATED_CERTIFICACION_IDIOMA);

        // Get all the viabilidadVisaList where certificacionIdioma equals to UPDATED_CERTIFICACION_IDIOMA
        defaultViabilidadVisaShouldNotBeFound("certificacionIdioma.in=" + UPDATED_CERTIFICACION_IDIOMA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCertificacionIdiomaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where certificacionIdioma is not null
        defaultViabilidadVisaShouldBeFound("certificacionIdioma.specified=true");

        // Get all the viabilidadVisaList where certificacionIdioma is null
        defaultViabilidadVisaShouldNotBeFound("certificacionIdioma.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPuntajeCertificacionIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where puntajeCertificacion equals to DEFAULT_PUNTAJE_CERTIFICACION
        defaultViabilidadVisaShouldBeFound("puntajeCertificacion.equals=" + DEFAULT_PUNTAJE_CERTIFICACION);

        // Get all the viabilidadVisaList where puntajeCertificacion equals to UPDATED_PUNTAJE_CERTIFICACION
        defaultViabilidadVisaShouldNotBeFound("puntajeCertificacion.equals=" + UPDATED_PUNTAJE_CERTIFICACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPuntajeCertificacionIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where puntajeCertificacion in DEFAULT_PUNTAJE_CERTIFICACION or UPDATED_PUNTAJE_CERTIFICACION
        defaultViabilidadVisaShouldBeFound("puntajeCertificacion.in=" + DEFAULT_PUNTAJE_CERTIFICACION + "," + UPDATED_PUNTAJE_CERTIFICACION);

        // Get all the viabilidadVisaList where puntajeCertificacion equals to UPDATED_PUNTAJE_CERTIFICACION
        defaultViabilidadVisaShouldNotBeFound("puntajeCertificacion.in=" + UPDATED_PUNTAJE_CERTIFICACION);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPuntajeCertificacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where puntajeCertificacion is not null
        defaultViabilidadVisaShouldBeFound("puntajeCertificacion.specified=true");

        // Get all the viabilidadVisaList where puntajeCertificacion is null
        defaultViabilidadVisaShouldNotBeFound("puntajeCertificacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasBySalidasPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where salidasPais equals to DEFAULT_SALIDAS_PAIS
        defaultViabilidadVisaShouldBeFound("salidasPais.equals=" + DEFAULT_SALIDAS_PAIS);

        // Get all the viabilidadVisaList where salidasPais equals to UPDATED_SALIDAS_PAIS
        defaultViabilidadVisaShouldNotBeFound("salidasPais.equals=" + UPDATED_SALIDAS_PAIS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasBySalidasPaisIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where salidasPais in DEFAULT_SALIDAS_PAIS or UPDATED_SALIDAS_PAIS
        defaultViabilidadVisaShouldBeFound("salidasPais.in=" + DEFAULT_SALIDAS_PAIS + "," + UPDATED_SALIDAS_PAIS);

        // Get all the viabilidadVisaList where salidasPais equals to UPDATED_SALIDAS_PAIS
        defaultViabilidadVisaShouldNotBeFound("salidasPais.in=" + UPDATED_SALIDAS_PAIS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasBySalidasPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where salidasPais is not null
        defaultViabilidadVisaShouldBeFound("salidasPais.specified=true");

        // Get all the viabilidadVisaList where salidasPais is null
        defaultViabilidadVisaShouldNotBeFound("salidasPais.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisesVisitadosIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisesVisitados equals to DEFAULT_PAISES_VISITADOS
        defaultViabilidadVisaShouldBeFound("paisesVisitados.equals=" + DEFAULT_PAISES_VISITADOS);

        // Get all the viabilidadVisaList where paisesVisitados equals to UPDATED_PAISES_VISITADOS
        defaultViabilidadVisaShouldNotBeFound("paisesVisitados.equals=" + UPDATED_PAISES_VISITADOS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisesVisitadosIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisesVisitados in DEFAULT_PAISES_VISITADOS or UPDATED_PAISES_VISITADOS
        defaultViabilidadVisaShouldBeFound("paisesVisitados.in=" + DEFAULT_PAISES_VISITADOS + "," + UPDATED_PAISES_VISITADOS);

        // Get all the viabilidadVisaList where paisesVisitados equals to UPDATED_PAISES_VISITADOS
        defaultViabilidadVisaShouldNotBeFound("paisesVisitados.in=" + UPDATED_PAISES_VISITADOS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisesVisitadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisesVisitados is not null
        defaultViabilidadVisaShouldBeFound("paisesVisitados.specified=true");

        // Get all the viabilidadVisaList where paisesVisitados is null
        defaultViabilidadVisaShouldNotBeFound("paisesVisitados.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByVisaPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where visaPais equals to DEFAULT_VISA_PAIS
        defaultViabilidadVisaShouldBeFound("visaPais.equals=" + DEFAULT_VISA_PAIS);

        // Get all the viabilidadVisaList where visaPais equals to UPDATED_VISA_PAIS
        defaultViabilidadVisaShouldNotBeFound("visaPais.equals=" + UPDATED_VISA_PAIS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByVisaPaisIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where visaPais in DEFAULT_VISA_PAIS or UPDATED_VISA_PAIS
        defaultViabilidadVisaShouldBeFound("visaPais.in=" + DEFAULT_VISA_PAIS + "," + UPDATED_VISA_PAIS);

        // Get all the viabilidadVisaList where visaPais equals to UPDATED_VISA_PAIS
        defaultViabilidadVisaShouldNotBeFound("visaPais.in=" + UPDATED_VISA_PAIS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByVisaPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where visaPais is not null
        defaultViabilidadVisaShouldBeFound("visaPais.specified=true");

        // Get all the viabilidadVisaList where visaPais is null
        defaultViabilidadVisaShouldNotBeFound("visaPais.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstudiadoAnteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estudiadoAnterior equals to DEFAULT_ESTUDIADO_ANTERIOR
        defaultViabilidadVisaShouldBeFound("estudiadoAnterior.equals=" + DEFAULT_ESTUDIADO_ANTERIOR);

        // Get all the viabilidadVisaList where estudiadoAnterior equals to UPDATED_ESTUDIADO_ANTERIOR
        defaultViabilidadVisaShouldNotBeFound("estudiadoAnterior.equals=" + UPDATED_ESTUDIADO_ANTERIOR);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstudiadoAnteriorIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estudiadoAnterior in DEFAULT_ESTUDIADO_ANTERIOR or UPDATED_ESTUDIADO_ANTERIOR
        defaultViabilidadVisaShouldBeFound("estudiadoAnterior.in=" + DEFAULT_ESTUDIADO_ANTERIOR + "," + UPDATED_ESTUDIADO_ANTERIOR);

        // Get all the viabilidadVisaList where estudiadoAnterior equals to UPDATED_ESTUDIADO_ANTERIOR
        defaultViabilidadVisaShouldNotBeFound("estudiadoAnterior.in=" + UPDATED_ESTUDIADO_ANTERIOR);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstudiadoAnteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estudiadoAnterior is not null
        defaultViabilidadVisaShouldBeFound("estudiadoAnterior.specified=true");

        // Get all the viabilidadVisaList where estudiadoAnterior is null
        defaultViabilidadVisaShouldNotBeFound("estudiadoAnterior.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFueraPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fueraPais equals to DEFAULT_FUERA_PAIS
        defaultViabilidadVisaShouldBeFound("fueraPais.equals=" + DEFAULT_FUERA_PAIS);

        // Get all the viabilidadVisaList where fueraPais equals to UPDATED_FUERA_PAIS
        defaultViabilidadVisaShouldNotBeFound("fueraPais.equals=" + UPDATED_FUERA_PAIS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFueraPaisIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fueraPais in DEFAULT_FUERA_PAIS or UPDATED_FUERA_PAIS
        defaultViabilidadVisaShouldBeFound("fueraPais.in=" + DEFAULT_FUERA_PAIS + "," + UPDATED_FUERA_PAIS);

        // Get all the viabilidadVisaList where fueraPais equals to UPDATED_FUERA_PAIS
        defaultViabilidadVisaShouldNotBeFound("fueraPais.in=" + UPDATED_FUERA_PAIS);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFueraPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where fueraPais is not null
        defaultViabilidadVisaShouldBeFound("fueraPais.specified=true");

        // Get all the viabilidadVisaList where fueraPais is null
        defaultViabilidadVisaShouldNotBeFound("fueraPais.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisFueraIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisFuera equals to DEFAULT_PAIS_FUERA
        defaultViabilidadVisaShouldBeFound("paisFuera.equals=" + DEFAULT_PAIS_FUERA);

        // Get all the viabilidadVisaList where paisFuera equals to UPDATED_PAIS_FUERA
        defaultViabilidadVisaShouldNotBeFound("paisFuera.equals=" + UPDATED_PAIS_FUERA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisFueraIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisFuera in DEFAULT_PAIS_FUERA or UPDATED_PAIS_FUERA
        defaultViabilidadVisaShouldBeFound("paisFuera.in=" + DEFAULT_PAIS_FUERA + "," + UPDATED_PAIS_FUERA);

        // Get all the viabilidadVisaList where paisFuera equals to UPDATED_PAIS_FUERA
        defaultViabilidadVisaShouldNotBeFound("paisFuera.in=" + UPDATED_PAIS_FUERA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisFueraIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisFuera is not null
        defaultViabilidadVisaShouldBeFound("paisFuera.specified=true");

        // Get all the viabilidadVisaList where paisFuera is null
        defaultViabilidadVisaShouldNotBeFound("paisFuera.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByExtenderEstadiaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where extenderEstadia equals to DEFAULT_EXTENDER_ESTADIA
        defaultViabilidadVisaShouldBeFound("extenderEstadia.equals=" + DEFAULT_EXTENDER_ESTADIA);

        // Get all the viabilidadVisaList where extenderEstadia equals to UPDATED_EXTENDER_ESTADIA
        defaultViabilidadVisaShouldNotBeFound("extenderEstadia.equals=" + UPDATED_EXTENDER_ESTADIA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByExtenderEstadiaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where extenderEstadia in DEFAULT_EXTENDER_ESTADIA or UPDATED_EXTENDER_ESTADIA
        defaultViabilidadVisaShouldBeFound("extenderEstadia.in=" + DEFAULT_EXTENDER_ESTADIA + "," + UPDATED_EXTENDER_ESTADIA);

        // Get all the viabilidadVisaList where extenderEstadia equals to UPDATED_EXTENDER_ESTADIA
        defaultViabilidadVisaShouldNotBeFound("extenderEstadia.in=" + UPDATED_EXTENDER_ESTADIA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByExtenderEstadiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where extenderEstadia is not null
        defaultViabilidadVisaShouldBeFound("extenderEstadia.specified=true");

        // Get all the viabilidadVisaList where extenderEstadia is null
        defaultViabilidadVisaShouldNotBeFound("extenderEstadia.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNegadoVisaIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where negadoVisa equals to DEFAULT_NEGADO_VISA
        defaultViabilidadVisaShouldBeFound("negadoVisa.equals=" + DEFAULT_NEGADO_VISA);

        // Get all the viabilidadVisaList where negadoVisa equals to UPDATED_NEGADO_VISA
        defaultViabilidadVisaShouldNotBeFound("negadoVisa.equals=" + UPDATED_NEGADO_VISA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNegadoVisaIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where negadoVisa in DEFAULT_NEGADO_VISA or UPDATED_NEGADO_VISA
        defaultViabilidadVisaShouldBeFound("negadoVisa.in=" + DEFAULT_NEGADO_VISA + "," + UPDATED_NEGADO_VISA);

        // Get all the viabilidadVisaList where negadoVisa equals to UPDATED_NEGADO_VISA
        defaultViabilidadVisaShouldNotBeFound("negadoVisa.in=" + UPDATED_NEGADO_VISA);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNegadoVisaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where negadoVisa is not null
        defaultViabilidadVisaShouldBeFound("negadoVisa.specified=true");

        // Get all the viabilidadVisaList where negadoVisa is null
        defaultViabilidadVisaShouldNotBeFound("negadoVisa.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisNegadoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisNegado equals to DEFAULT_PAIS_NEGADO
        defaultViabilidadVisaShouldBeFound("paisNegado.equals=" + DEFAULT_PAIS_NEGADO);

        // Get all the viabilidadVisaList where paisNegado equals to UPDATED_PAIS_NEGADO
        defaultViabilidadVisaShouldNotBeFound("paisNegado.equals=" + UPDATED_PAIS_NEGADO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisNegadoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisNegado in DEFAULT_PAIS_NEGADO or UPDATED_PAIS_NEGADO
        defaultViabilidadVisaShouldBeFound("paisNegado.in=" + DEFAULT_PAIS_NEGADO + "," + UPDATED_PAIS_NEGADO);

        // Get all the viabilidadVisaList where paisNegado equals to UPDATED_PAIS_NEGADO
        defaultViabilidadVisaShouldNotBeFound("paisNegado.in=" + UPDATED_PAIS_NEGADO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByPaisNegadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where paisNegado is not null
        defaultViabilidadVisaShouldBeFound("paisNegado.specified=true");

        // Get all the viabilidadVisaList where paisNegado is null
        defaultViabilidadVisaShouldNotBeFound("paisNegado.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFamiliaresDestinoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where familiaresDestino equals to DEFAULT_FAMILIARES_DESTINO
        defaultViabilidadVisaShouldBeFound("familiaresDestino.equals=" + DEFAULT_FAMILIARES_DESTINO);

        // Get all the viabilidadVisaList where familiaresDestino equals to UPDATED_FAMILIARES_DESTINO
        defaultViabilidadVisaShouldNotBeFound("familiaresDestino.equals=" + UPDATED_FAMILIARES_DESTINO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFamiliaresDestinoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where familiaresDestino in DEFAULT_FAMILIARES_DESTINO or UPDATED_FAMILIARES_DESTINO
        defaultViabilidadVisaShouldBeFound("familiaresDestino.in=" + DEFAULT_FAMILIARES_DESTINO + "," + UPDATED_FAMILIARES_DESTINO);

        // Get all the viabilidadVisaList where familiaresDestino equals to UPDATED_FAMILIARES_DESTINO
        defaultViabilidadVisaShouldNotBeFound("familiaresDestino.in=" + UPDATED_FAMILIARES_DESTINO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByFamiliaresDestinoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where familiaresDestino is not null
        defaultViabilidadVisaShouldBeFound("familiaresDestino.specified=true");

        // Get all the viabilidadVisaList where familiaresDestino is null
        defaultViabilidadVisaShouldNotBeFound("familiaresDestino.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstatusMigratorioIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estatusMigratorio equals to DEFAULT_ESTATUS_MIGRATORIO
        defaultViabilidadVisaShouldBeFound("estatusMigratorio.equals=" + DEFAULT_ESTATUS_MIGRATORIO);

        // Get all the viabilidadVisaList where estatusMigratorio equals to UPDATED_ESTATUS_MIGRATORIO
        defaultViabilidadVisaShouldNotBeFound("estatusMigratorio.equals=" + UPDATED_ESTATUS_MIGRATORIO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstatusMigratorioIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estatusMigratorio in DEFAULT_ESTATUS_MIGRATORIO or UPDATED_ESTATUS_MIGRATORIO
        defaultViabilidadVisaShouldBeFound("estatusMigratorio.in=" + DEFAULT_ESTATUS_MIGRATORIO + "," + UPDATED_ESTATUS_MIGRATORIO);

        // Get all the viabilidadVisaList where estatusMigratorio equals to UPDATED_ESTATUS_MIGRATORIO
        defaultViabilidadVisaShouldNotBeFound("estatusMigratorio.in=" + UPDATED_ESTATUS_MIGRATORIO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByEstatusMigratorioIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where estatusMigratorio is not null
        defaultViabilidadVisaShouldBeFound("estatusMigratorio.specified=true");

        // Get all the viabilidadVisaList where estatusMigratorio is null
        defaultViabilidadVisaShouldNotBeFound("estatusMigratorio.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nombre equals to DEFAULT_NOMBRE
        defaultViabilidadVisaShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the viabilidadVisaList where nombre equals to UPDATED_NOMBRE
        defaultViabilidadVisaShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultViabilidadVisaShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the viabilidadVisaList where nombre equals to UPDATED_NOMBRE
        defaultViabilidadVisaShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where nombre is not null
        defaultViabilidadVisaShouldBeFound("nombre.specified=true");

        // Get all the viabilidadVisaList where nombre is null
        defaultViabilidadVisaShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByApelliodIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where apelliod equals to DEFAULT_APELLIOD
        defaultViabilidadVisaShouldBeFound("apelliod.equals=" + DEFAULT_APELLIOD);

        // Get all the viabilidadVisaList where apelliod equals to UPDATED_APELLIOD
        defaultViabilidadVisaShouldNotBeFound("apelliod.equals=" + UPDATED_APELLIOD);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByApelliodIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where apelliod in DEFAULT_APELLIOD or UPDATED_APELLIOD
        defaultViabilidadVisaShouldBeFound("apelliod.in=" + DEFAULT_APELLIOD + "," + UPDATED_APELLIOD);

        // Get all the viabilidadVisaList where apelliod equals to UPDATED_APELLIOD
        defaultViabilidadVisaShouldNotBeFound("apelliod.in=" + UPDATED_APELLIOD);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByApelliodIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where apelliod is not null
        defaultViabilidadVisaShouldBeFound("apelliod.specified=true");

        // Get all the viabilidadVisaList where apelliod is null
        defaultViabilidadVisaShouldNotBeFound("apelliod.specified=false");
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCorreoIsEqualToSomething() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where correo equals to DEFAULT_CORREO
        defaultViabilidadVisaShouldBeFound("correo.equals=" + DEFAULT_CORREO);

        // Get all the viabilidadVisaList where correo equals to UPDATED_CORREO
        defaultViabilidadVisaShouldNotBeFound("correo.equals=" + UPDATED_CORREO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCorreoIsInShouldWork() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where correo in DEFAULT_CORREO or UPDATED_CORREO
        defaultViabilidadVisaShouldBeFound("correo.in=" + DEFAULT_CORREO + "," + UPDATED_CORREO);

        // Get all the viabilidadVisaList where correo equals to UPDATED_CORREO
        defaultViabilidadVisaShouldNotBeFound("correo.in=" + UPDATED_CORREO);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisasByCorreoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList where correo is not null
        defaultViabilidadVisaShouldBeFound("correo.specified=true");

        // Get all the viabilidadVisaList where correo is null
        defaultViabilidadVisaShouldNotBeFound("correo.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultViabilidadVisaShouldBeFound(String filter) throws Exception {
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viabilidadVisa.getId().intValue())))
            .andExpect(jsonPath("$.[*].destino").value(hasItem(DEFAULT_DESTINO.toString())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.toString())))
            .andExpect(jsonPath("$.[*].nacionalidadPrincipal").value(hasItem(DEFAULT_NACIONALIDAD_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].otraNacionalidad").value(hasItem(DEFAULT_OTRA_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].viajaAcompanado").value(hasItem(DEFAULT_VIAJA_ACOMPANADO.booleanValue())))
            .andExpect(jsonPath("$.[*].personasCargo").value(hasItem(DEFAULT_PERSONAS_CARGO.toString())))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].profesion").value(hasItem(DEFAULT_PROFESION)))
            .andExpect(jsonPath("$.[*].fechaGadruacion").value(hasItem(DEFAULT_FECHA_GADRUACION.toString())))
            .andExpect(jsonPath("$.[*].ocupacionActual").value(hasItem(DEFAULT_OCUPACION_ACTUAL.toString())))
            .andExpect(jsonPath("$.[*].carta").value(hasItem(DEFAULT_CARTA.booleanValue())))
            .andExpect(jsonPath("$.[*].suspendidoEstudios").value(hasItem(DEFAULT_SUSPENDIDO_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].peridoSupencionEstu").value(hasItem(DEFAULT_PERIDO_SUPENCION_ESTU.toString())))
            .andExpect(jsonPath("$.[*].razonSuspencion").value(hasItem(DEFAULT_RAZON_SUSPENCION)))
            .andExpect(jsonPath("$.[*].tipoContrato").value(hasItem(DEFAULT_TIPO_CONTRATO.toString())))
            .andExpect(jsonPath("$.[*].licenciaLaboral").value(hasItem(DEFAULT_LICENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].nivelSalarial").value(hasItem(DEFAULT_NIVEL_SALARIAL.toString())))
            .andExpect(jsonPath("$.[*].tipoLaborIndependiente").value(hasItem(DEFAULT_TIPO_LABOR_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoIndependiente").value(hasItem(DEFAULT_TIEMPO_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].nivelIngresosIndependiente").value(hasItem(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoDesempleado").value(hasItem(DEFAULT_TIEMPO_DESEMPLEADO.toString())))
            .andExpect(jsonPath("$.[*].quienFinanciaEstudios").value(hasItem(DEFAULT_QUIEN_FINANCIA_ESTUDIOS.toString())))
            .andExpect(jsonPath("$.[*].parentesco").value(hasItem(DEFAULT_PARENTESCO.toString())))
            .andExpect(jsonPath("$.[*].presupuestoDisponible").value(hasItem(DEFAULT_PRESUPUESTO_DISPONIBLE.toString())))
            .andExpect(jsonPath("$.[*].ahorroDisponible").value(hasItem(DEFAULT_AHORRO_DISPONIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].idioma").value(hasItem(DEFAULT_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificarIdioma").value(hasItem(DEFAULT_CERTIFICAR_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificacionIdioma").value(hasItem(DEFAULT_CERTIFICACION_IDIOMA)))
            .andExpect(jsonPath("$.[*].puntajeCertificacion").value(hasItem(DEFAULT_PUNTAJE_CERTIFICACION)))
            .andExpect(jsonPath("$.[*].salidasPais").value(hasItem(DEFAULT_SALIDAS_PAIS)))
            .andExpect(jsonPath("$.[*].paisesVisitados").value(hasItem(DEFAULT_PAISES_VISITADOS)))
            .andExpect(jsonPath("$.[*].visaPais").value(hasItem(DEFAULT_VISA_PAIS)))
            .andExpect(jsonPath("$.[*].estudiadoAnterior").value(hasItem(DEFAULT_ESTUDIADO_ANTERIOR.booleanValue())))
            .andExpect(jsonPath("$.[*].fueraPais").value(hasItem(DEFAULT_FUERA_PAIS.booleanValue())))
            .andExpect(jsonPath("$.[*].paisFuera").value(hasItem(DEFAULT_PAIS_FUERA)))
            .andExpect(jsonPath("$.[*].extenderEstadia").value(hasItem(DEFAULT_EXTENDER_ESTADIA.booleanValue())))
            .andExpect(jsonPath("$.[*].negadoVisa").value(hasItem(DEFAULT_NEGADO_VISA.booleanValue())))
            .andExpect(jsonPath("$.[*].paisNegado").value(hasItem(DEFAULT_PAIS_NEGADO)))
            .andExpect(jsonPath("$.[*].familiaresDestino").value(hasItem(DEFAULT_FAMILIARES_DESTINO.booleanValue())))
            .andExpect(jsonPath("$.[*].estatusMigratorio").value(hasItem(DEFAULT_ESTATUS_MIGRATORIO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apelliod").value(hasItem(DEFAULT_APELLIOD)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)));

        // Check, that the count call also returns 1
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultViabilidadVisaShouldNotBeFound(String filter) throws Exception {
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingViabilidadVisa() throws Exception {
        // Get the viabilidadVisa
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaService.save(viabilidadVisa);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockViabilidadVisaSearchRepository);

        int databaseSizeBeforeUpdate = viabilidadVisaRepository.findAll().size();

        // Update the viabilidadVisa
        ViabilidadVisa updatedViabilidadVisa = viabilidadVisaRepository.findById(viabilidadVisa.getId()).get();
        // Disconnect from session so that the updates on updatedViabilidadVisa are not directly saved in db
        em.detach(updatedViabilidadVisa);
        updatedViabilidadVisa
            .destino(UPDATED_DESTINO)
            .tipoPrograma(UPDATED_TIPO_PROGRAMA)
            .duracion(UPDATED_DURACION)
            .nacionalidadPrincipal(UPDATED_NACIONALIDAD_PRINCIPAL)
            .otraNacionalidad(UPDATED_OTRA_NACIONALIDAD)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .genero(UPDATED_GENERO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .viajaAcompanado(UPDATED_VIAJA_ACOMPANADO)
            .personasCargo(UPDATED_PERSONAS_CARGO)
            .nivelAcademico(UPDATED_NIVEL_ACADEMICO)
            .profesion(UPDATED_PROFESION)
            .fechaGadruacion(UPDATED_FECHA_GADRUACION)
            .ocupacionActual(UPDATED_OCUPACION_ACTUAL)
            .carta(UPDATED_CARTA)
            .suspendidoEstudios(UPDATED_SUSPENDIDO_ESTUDIOS)
            .peridoSupencionEstu(UPDATED_PERIDO_SUPENCION_ESTU)
            .razonSuspencion(UPDATED_RAZON_SUSPENCION)
            .tipoContrato(UPDATED_TIPO_CONTRATO)
            .licenciaLaboral(UPDATED_LICENCIA_LABORAL)
            .nivelSalarial(UPDATED_NIVEL_SALARIAL)
            .tipoLaborIndependiente(UPDATED_TIPO_LABOR_INDEPENDIENTE)
            .tiempoIndependiente(UPDATED_TIEMPO_INDEPENDIENTE)
            .nivelIngresosIndependiente(UPDATED_NIVEL_INGRESOS_INDEPENDIENTE)
            .tiempoDesempleado(UPDATED_TIEMPO_DESEMPLEADO)
            .quienFinanciaEstudios(UPDATED_QUIEN_FINANCIA_ESTUDIOS)
            .parentesco(UPDATED_PARENTESCO)
            .presupuestoDisponible(UPDATED_PRESUPUESTO_DISPONIBLE)
            .ahorroDisponible(UPDATED_AHORRO_DISPONIBLE)
            .idioma(UPDATED_IDIOMA)
            .certificarIdioma(UPDATED_CERTIFICAR_IDIOMA)
            .certificacionIdioma(UPDATED_CERTIFICACION_IDIOMA)
            .puntajeCertificacion(UPDATED_PUNTAJE_CERTIFICACION)
            .salidasPais(UPDATED_SALIDAS_PAIS)
            .paisesVisitados(UPDATED_PAISES_VISITADOS)
            .visaPais(UPDATED_VISA_PAIS)
            .estudiadoAnterior(UPDATED_ESTUDIADO_ANTERIOR)
            .fueraPais(UPDATED_FUERA_PAIS)
            .paisFuera(UPDATED_PAIS_FUERA)
            .extenderEstadia(UPDATED_EXTENDER_ESTADIA)
            .negadoVisa(UPDATED_NEGADO_VISA)
            .paisNegado(UPDATED_PAIS_NEGADO)
            .familiaresDestino(UPDATED_FAMILIARES_DESTINO)
            .estatusMigratorio(UPDATED_ESTATUS_MIGRATORIO)
            .nombre(UPDATED_NOMBRE)
            .apelliod(UPDATED_APELLIOD)
            .correo(UPDATED_CORREO);

        restViabilidadVisaMockMvc.perform(put("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedViabilidadVisa)))
            .andExpect(status().isOk());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeUpdate);
        ViabilidadVisa testViabilidadVisa = viabilidadVisaList.get(viabilidadVisaList.size() - 1);
        assertThat(testViabilidadVisa.getDestino()).isEqualTo(UPDATED_DESTINO);
        assertThat(testViabilidadVisa.getTipoPrograma()).isEqualTo(UPDATED_TIPO_PROGRAMA);
        assertThat(testViabilidadVisa.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testViabilidadVisa.getNacionalidadPrincipal()).isEqualTo(UPDATED_NACIONALIDAD_PRINCIPAL);
        assertThat(testViabilidadVisa.getOtraNacionalidad()).isEqualTo(UPDATED_OTRA_NACIONALIDAD);
        assertThat(testViabilidadVisa.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testViabilidadVisa.getGenero()).isEqualTo(UPDATED_GENERO);
        assertThat(testViabilidadVisa.getEstadoCivil()).isEqualTo(UPDATED_ESTADO_CIVIL);
        assertThat(testViabilidadVisa.isViajaAcompanado()).isEqualTo(UPDATED_VIAJA_ACOMPANADO);
        assertThat(testViabilidadVisa.getPersonasCargo()).isEqualTo(UPDATED_PERSONAS_CARGO);
        assertThat(testViabilidadVisa.getNivelAcademico()).isEqualTo(UPDATED_NIVEL_ACADEMICO);
        assertThat(testViabilidadVisa.getProfesion()).isEqualTo(UPDATED_PROFESION);
        assertThat(testViabilidadVisa.getFechaGadruacion()).isEqualTo(UPDATED_FECHA_GADRUACION);
        assertThat(testViabilidadVisa.getOcupacionActual()).isEqualTo(UPDATED_OCUPACION_ACTUAL);
        assertThat(testViabilidadVisa.isCarta()).isEqualTo(UPDATED_CARTA);
        assertThat(testViabilidadVisa.isSuspendidoEstudios()).isEqualTo(UPDATED_SUSPENDIDO_ESTUDIOS);
        assertThat(testViabilidadVisa.getPeridoSupencionEstu()).isEqualTo(UPDATED_PERIDO_SUPENCION_ESTU);
        assertThat(testViabilidadVisa.getRazonSuspencion()).isEqualTo(UPDATED_RAZON_SUSPENCION);
        assertThat(testViabilidadVisa.getTipoContrato()).isEqualTo(UPDATED_TIPO_CONTRATO);
        assertThat(testViabilidadVisa.isLicenciaLaboral()).isEqualTo(UPDATED_LICENCIA_LABORAL);
        assertThat(testViabilidadVisa.getNivelSalarial()).isEqualTo(UPDATED_NIVEL_SALARIAL);
        assertThat(testViabilidadVisa.getTipoLaborIndependiente()).isEqualTo(UPDATED_TIPO_LABOR_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoIndependiente()).isEqualTo(UPDATED_TIEMPO_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getNivelIngresosIndependiente()).isEqualTo(UPDATED_NIVEL_INGRESOS_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoDesempleado()).isEqualTo(UPDATED_TIEMPO_DESEMPLEADO);
        assertThat(testViabilidadVisa.getQuienFinanciaEstudios()).isEqualTo(UPDATED_QUIEN_FINANCIA_ESTUDIOS);
        assertThat(testViabilidadVisa.getParentesco()).isEqualTo(UPDATED_PARENTESCO);
        assertThat(testViabilidadVisa.getPresupuestoDisponible()).isEqualTo(UPDATED_PRESUPUESTO_DISPONIBLE);
        assertThat(testViabilidadVisa.isAhorroDisponible()).isEqualTo(UPDATED_AHORRO_DISPONIBLE);
        assertThat(testViabilidadVisa.isIdioma()).isEqualTo(UPDATED_IDIOMA);
        assertThat(testViabilidadVisa.isCertificarIdioma()).isEqualTo(UPDATED_CERTIFICAR_IDIOMA);
        assertThat(testViabilidadVisa.getCertificacionIdioma()).isEqualTo(UPDATED_CERTIFICACION_IDIOMA);
        assertThat(testViabilidadVisa.getPuntajeCertificacion()).isEqualTo(UPDATED_PUNTAJE_CERTIFICACION);
        assertThat(testViabilidadVisa.getSalidasPais()).isEqualTo(UPDATED_SALIDAS_PAIS);
        assertThat(testViabilidadVisa.getPaisesVisitados()).isEqualTo(UPDATED_PAISES_VISITADOS);
        assertThat(testViabilidadVisa.getVisaPais()).isEqualTo(UPDATED_VISA_PAIS);
        assertThat(testViabilidadVisa.isEstudiadoAnterior()).isEqualTo(UPDATED_ESTUDIADO_ANTERIOR);
        assertThat(testViabilidadVisa.isFueraPais()).isEqualTo(UPDATED_FUERA_PAIS);
        assertThat(testViabilidadVisa.getPaisFuera()).isEqualTo(UPDATED_PAIS_FUERA);
        assertThat(testViabilidadVisa.isExtenderEstadia()).isEqualTo(UPDATED_EXTENDER_ESTADIA);
        assertThat(testViabilidadVisa.isNegadoVisa()).isEqualTo(UPDATED_NEGADO_VISA);
        assertThat(testViabilidadVisa.getPaisNegado()).isEqualTo(UPDATED_PAIS_NEGADO);
        assertThat(testViabilidadVisa.isFamiliaresDestino()).isEqualTo(UPDATED_FAMILIARES_DESTINO);
        assertThat(testViabilidadVisa.getEstatusMigratorio()).isEqualTo(UPDATED_ESTATUS_MIGRATORIO);
        assertThat(testViabilidadVisa.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testViabilidadVisa.getApelliod()).isEqualTo(UPDATED_APELLIOD);
        assertThat(testViabilidadVisa.getCorreo()).isEqualTo(UPDATED_CORREO);

        // Validate the ViabilidadVisa in Elasticsearch
        verify(mockViabilidadVisaSearchRepository, times(1)).save(testViabilidadVisa);
    }

    @Test
    @Transactional
    public void updateNonExistingViabilidadVisa() throws Exception {
        int databaseSizeBeforeUpdate = viabilidadVisaRepository.findAll().size();

        // Create the ViabilidadVisa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViabilidadVisaMockMvc.perform(put("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(viabilidadVisa)))
            .andExpect(status().isBadRequest());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ViabilidadVisa in Elasticsearch
        verify(mockViabilidadVisaSearchRepository, times(0)).save(viabilidadVisa);
    }

    @Test
    @Transactional
    public void deleteViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaService.save(viabilidadVisa);

        int databaseSizeBeforeDelete = viabilidadVisaRepository.findAll().size();

        // Delete the viabilidadVisa
        restViabilidadVisaMockMvc.perform(delete("/api/viabilidad-visas/{id}", viabilidadVisa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ViabilidadVisa in Elasticsearch
        verify(mockViabilidadVisaSearchRepository, times(1)).deleteById(viabilidadVisa.getId());
    }

    @Test
    @Transactional
    public void searchViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaService.save(viabilidadVisa);
        when(mockViabilidadVisaSearchRepository.search(queryStringQuery("id:" + viabilidadVisa.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(viabilidadVisa), PageRequest.of(0, 1), 1));
        // Search the viabilidadVisa
        restViabilidadVisaMockMvc.perform(get("/api/_search/viabilidad-visas?query=id:" + viabilidadVisa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viabilidadVisa.getId().intValue())))
            .andExpect(jsonPath("$.[*].destino").value(hasItem(DEFAULT_DESTINO.toString())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.toString())))
            .andExpect(jsonPath("$.[*].nacionalidadPrincipal").value(hasItem(DEFAULT_NACIONALIDAD_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].otraNacionalidad").value(hasItem(DEFAULT_OTRA_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].viajaAcompanado").value(hasItem(DEFAULT_VIAJA_ACOMPANADO.booleanValue())))
            .andExpect(jsonPath("$.[*].personasCargo").value(hasItem(DEFAULT_PERSONAS_CARGO.toString())))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].profesion").value(hasItem(DEFAULT_PROFESION)))
            .andExpect(jsonPath("$.[*].fechaGadruacion").value(hasItem(DEFAULT_FECHA_GADRUACION.toString())))
            .andExpect(jsonPath("$.[*].ocupacionActual").value(hasItem(DEFAULT_OCUPACION_ACTUAL.toString())))
            .andExpect(jsonPath("$.[*].carta").value(hasItem(DEFAULT_CARTA.booleanValue())))
            .andExpect(jsonPath("$.[*].suspendidoEstudios").value(hasItem(DEFAULT_SUSPENDIDO_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].peridoSupencionEstu").value(hasItem(DEFAULT_PERIDO_SUPENCION_ESTU.toString())))
            .andExpect(jsonPath("$.[*].razonSuspencion").value(hasItem(DEFAULT_RAZON_SUSPENCION)))
            .andExpect(jsonPath("$.[*].tipoContrato").value(hasItem(DEFAULT_TIPO_CONTRATO.toString())))
            .andExpect(jsonPath("$.[*].licenciaLaboral").value(hasItem(DEFAULT_LICENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].nivelSalarial").value(hasItem(DEFAULT_NIVEL_SALARIAL.toString())))
            .andExpect(jsonPath("$.[*].tipoLaborIndependiente").value(hasItem(DEFAULT_TIPO_LABOR_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoIndependiente").value(hasItem(DEFAULT_TIEMPO_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].nivelIngresosIndependiente").value(hasItem(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoDesempleado").value(hasItem(DEFAULT_TIEMPO_DESEMPLEADO.toString())))
            .andExpect(jsonPath("$.[*].quienFinanciaEstudios").value(hasItem(DEFAULT_QUIEN_FINANCIA_ESTUDIOS.toString())))
            .andExpect(jsonPath("$.[*].parentesco").value(hasItem(DEFAULT_PARENTESCO.toString())))
            .andExpect(jsonPath("$.[*].presupuestoDisponible").value(hasItem(DEFAULT_PRESUPUESTO_DISPONIBLE.toString())))
            .andExpect(jsonPath("$.[*].ahorroDisponible").value(hasItem(DEFAULT_AHORRO_DISPONIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].idioma").value(hasItem(DEFAULT_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificarIdioma").value(hasItem(DEFAULT_CERTIFICAR_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificacionIdioma").value(hasItem(DEFAULT_CERTIFICACION_IDIOMA)))
            .andExpect(jsonPath("$.[*].puntajeCertificacion").value(hasItem(DEFAULT_PUNTAJE_CERTIFICACION)))
            .andExpect(jsonPath("$.[*].salidasPais").value(hasItem(DEFAULT_SALIDAS_PAIS)))
            .andExpect(jsonPath("$.[*].paisesVisitados").value(hasItem(DEFAULT_PAISES_VISITADOS)))
            .andExpect(jsonPath("$.[*].visaPais").value(hasItem(DEFAULT_VISA_PAIS)))
            .andExpect(jsonPath("$.[*].estudiadoAnterior").value(hasItem(DEFAULT_ESTUDIADO_ANTERIOR.booleanValue())))
            .andExpect(jsonPath("$.[*].fueraPais").value(hasItem(DEFAULT_FUERA_PAIS.booleanValue())))
            .andExpect(jsonPath("$.[*].paisFuera").value(hasItem(DEFAULT_PAIS_FUERA)))
            .andExpect(jsonPath("$.[*].extenderEstadia").value(hasItem(DEFAULT_EXTENDER_ESTADIA.booleanValue())))
            .andExpect(jsonPath("$.[*].negadoVisa").value(hasItem(DEFAULT_NEGADO_VISA.booleanValue())))
            .andExpect(jsonPath("$.[*].paisNegado").value(hasItem(DEFAULT_PAIS_NEGADO)))
            .andExpect(jsonPath("$.[*].familiaresDestino").value(hasItem(DEFAULT_FAMILIARES_DESTINO.booleanValue())))
            .andExpect(jsonPath("$.[*].estatusMigratorio").value(hasItem(DEFAULT_ESTATUS_MIGRATORIO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apelliod").value(hasItem(DEFAULT_APELLIOD)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViabilidadVisa.class);
        ViabilidadVisa viabilidadVisa1 = new ViabilidadVisa();
        viabilidadVisa1.setId(1L);
        ViabilidadVisa viabilidadVisa2 = new ViabilidadVisa();
        viabilidadVisa2.setId(viabilidadVisa1.getId());
        assertThat(viabilidadVisa1).isEqualTo(viabilidadVisa2);
        viabilidadVisa2.setId(2L);
        assertThat(viabilidadVisa1).isNotEqualTo(viabilidadVisa2);
        viabilidadVisa1.setId(null);
        assertThat(viabilidadVisa1).isNotEqualTo(viabilidadVisa2);
    }
}
