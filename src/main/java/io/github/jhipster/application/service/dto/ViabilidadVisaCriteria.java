package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
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
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.ViabilidadVisa} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ViabilidadVisaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /viabilidad-visas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ViabilidadVisaCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Destinod
     */
    public static class DestinodFilter extends Filter<Destinod> {

        public DestinodFilter() {
        }

        public DestinodFilter(DestinodFilter filter) {
            super(filter);
        }

        @Override
        public DestinodFilter copy() {
            return new DestinodFilter(this);
        }

    }
    /**
     * Class for filtering TipoProgramad
     */
    public static class TipoProgramadFilter extends Filter<TipoProgramad> {

        public TipoProgramadFilter() {
        }

        public TipoProgramadFilter(TipoProgramadFilter filter) {
            super(filter);
        }

        @Override
        public TipoProgramadFilter copy() {
            return new TipoProgramadFilter(this);
        }

    }
    /**
     * Class for filtering Duraciond
     */
    public static class DuraciondFilter extends Filter<Duraciond> {

        public DuraciondFilter() {
        }

        public DuraciondFilter(DuraciondFilter filter) {
            super(filter);
        }

        @Override
        public DuraciondFilter copy() {
            return new DuraciondFilter(this);
        }

    }
    /**
     * Class for filtering Nacionalidadd
     */
    public static class NacionalidaddFilter extends Filter<Nacionalidadd> {

        public NacionalidaddFilter() {
        }

        public NacionalidaddFilter(NacionalidaddFilter filter) {
            super(filter);
        }

        @Override
        public NacionalidaddFilter copy() {
            return new NacionalidaddFilter(this);
        }

    }
    /**
     * Class for filtering Generod
     */
    public static class GenerodFilter extends Filter<Generod> {

        public GenerodFilter() {
        }

        public GenerodFilter(GenerodFilter filter) {
            super(filter);
        }

        @Override
        public GenerodFilter copy() {
            return new GenerodFilter(this);
        }

    }
    /**
     * Class for filtering EstadoCivild
     */
    public static class EstadoCivildFilter extends Filter<EstadoCivild> {

        public EstadoCivildFilter() {
        }

        public EstadoCivildFilter(EstadoCivildFilter filter) {
            super(filter);
        }

        @Override
        public EstadoCivildFilter copy() {
            return new EstadoCivildFilter(this);
        }

    }
    /**
     * Class for filtering PersonasCargod
     */
    public static class PersonasCargodFilter extends Filter<PersonasCargod> {

        public PersonasCargodFilter() {
        }

        public PersonasCargodFilter(PersonasCargodFilter filter) {
            super(filter);
        }

        @Override
        public PersonasCargodFilter copy() {
            return new PersonasCargodFilter(this);
        }

    }
    /**
     * Class for filtering NivelAcademicod
     */
    public static class NivelAcademicodFilter extends Filter<NivelAcademicod> {

        public NivelAcademicodFilter() {
        }

        public NivelAcademicodFilter(NivelAcademicodFilter filter) {
            super(filter);
        }

        @Override
        public NivelAcademicodFilter copy() {
            return new NivelAcademicodFilter(this);
        }

    }
    /**
     * Class for filtering OcupacionActuald
     */
    public static class OcupacionActualdFilter extends Filter<OcupacionActuald> {

        public OcupacionActualdFilter() {
        }

        public OcupacionActualdFilter(OcupacionActualdFilter filter) {
            super(filter);
        }

        @Override
        public OcupacionActualdFilter copy() {
            return new OcupacionActualdFilter(this);
        }

    }
    /**
     * Class for filtering PeridoSupencionEstud
     */
    public static class PeridoSupencionEstudFilter extends Filter<PeridoSupencionEstud> {

        public PeridoSupencionEstudFilter() {
        }

        public PeridoSupencionEstudFilter(PeridoSupencionEstudFilter filter) {
            super(filter);
        }

        @Override
        public PeridoSupencionEstudFilter copy() {
            return new PeridoSupencionEstudFilter(this);
        }

    }
    /**
     * Class for filtering TipoContratod
     */
    public static class TipoContratodFilter extends Filter<TipoContratod> {

        public TipoContratodFilter() {
        }

        public TipoContratodFilter(TipoContratodFilter filter) {
            super(filter);
        }

        @Override
        public TipoContratodFilter copy() {
            return new TipoContratodFilter(this);
        }

    }
    /**
     * Class for filtering NivelSalariald
     */
    public static class NivelSalarialdFilter extends Filter<NivelSalariald> {

        public NivelSalarialdFilter() {
        }

        public NivelSalarialdFilter(NivelSalarialdFilter filter) {
            super(filter);
        }

        @Override
        public NivelSalarialdFilter copy() {
            return new NivelSalarialdFilter(this);
        }

    }
    /**
     * Class for filtering TipoLaborIndependiented
     */
    public static class TipoLaborIndependientedFilter extends Filter<TipoLaborIndependiented> {

        public TipoLaborIndependientedFilter() {
        }

        public TipoLaborIndependientedFilter(TipoLaborIndependientedFilter filter) {
            super(filter);
        }

        @Override
        public TipoLaborIndependientedFilter copy() {
            return new TipoLaborIndependientedFilter(this);
        }

    }
    /**
     * Class for filtering QuienFinanciaEstudiosd
     */
    public static class QuienFinanciaEstudiosdFilter extends Filter<QuienFinanciaEstudiosd> {

        public QuienFinanciaEstudiosdFilter() {
        }

        public QuienFinanciaEstudiosdFilter(QuienFinanciaEstudiosdFilter filter) {
            super(filter);
        }

        @Override
        public QuienFinanciaEstudiosdFilter copy() {
            return new QuienFinanciaEstudiosdFilter(this);
        }

    }
    /**
     * Class for filtering Parentescod
     */
    public static class ParentescodFilter extends Filter<Parentescod> {

        public ParentescodFilter() {
        }

        public ParentescodFilter(ParentescodFilter filter) {
            super(filter);
        }

        @Override
        public ParentescodFilter copy() {
            return new ParentescodFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DestinodFilter destino;

    private TipoProgramadFilter tipoPrograma;

    private DuraciondFilter duracion;

    private NacionalidaddFilter nacionalidadPrincipal;

    private NacionalidaddFilter otraNacionalidad;

    private LocalDateFilter fechaNacimiento;

    private GenerodFilter genero;

    private EstadoCivildFilter estadoCivil;

    private BooleanFilter viajaAcompanado;

    private PersonasCargodFilter personasCargo;

    private NivelAcademicodFilter nivelAcademico;

    private StringFilter profesion;

    private LocalDateFilter fechaGadruacion;

    private OcupacionActualdFilter ocupacionActual;

    private BooleanFilter carta;

    private BooleanFilter suspendidoEstudios;

    private PeridoSupencionEstudFilter peridoSupencionEstu;

    private StringFilter razonSuspencion;

    private TipoContratodFilter tipoContrato;

    private BooleanFilter licenciaLaboral;

    private NivelSalarialdFilter nivelSalarial;

    private TipoLaborIndependientedFilter tipoLaborIndependiente;

    private PeridoSupencionEstudFilter tiempoIndependiente;

    private NivelSalarialdFilter nivelIngresosIndependiente;

    private PeridoSupencionEstudFilter tiempoDesempleado;

    private QuienFinanciaEstudiosdFilter quienFinanciaEstudios;

    private ParentescodFilter parentesco;

    private NivelSalarialdFilter presupuestoDisponible;

    private BooleanFilter ahorroDisponible;

    private BooleanFilter idioma;

    private BooleanFilter certificarIdioma;

    private StringFilter certificacionIdioma;

    private StringFilter puntajeCertificacion;

    private StringFilter salidasPais;

    private StringFilter paisesVisitados;

    private StringFilter visaPais;

    private BooleanFilter estudiadoAnterior;

    private BooleanFilter fueraPais;

    private StringFilter paisFuera;

    private BooleanFilter extenderEstadia;

    private BooleanFilter negadoVisa;

    private StringFilter paisNegado;

    private BooleanFilter familiaresDestino;

    private StringFilter estatusMigratorio;

    private StringFilter nombre;

    private StringFilter apelliod;

    private StringFilter correo;

    public ViabilidadVisaCriteria(){
    }

    public ViabilidadVisaCriteria(ViabilidadVisaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.destino = other.destino == null ? null : other.destino.copy();
        this.tipoPrograma = other.tipoPrograma == null ? null : other.tipoPrograma.copy();
        this.duracion = other.duracion == null ? null : other.duracion.copy();
        this.nacionalidadPrincipal = other.nacionalidadPrincipal == null ? null : other.nacionalidadPrincipal.copy();
        this.otraNacionalidad = other.otraNacionalidad == null ? null : other.otraNacionalidad.copy();
        this.fechaNacimiento = other.fechaNacimiento == null ? null : other.fechaNacimiento.copy();
        this.genero = other.genero == null ? null : other.genero.copy();
        this.estadoCivil = other.estadoCivil == null ? null : other.estadoCivil.copy();
        this.viajaAcompanado = other.viajaAcompanado == null ? null : other.viajaAcompanado.copy();
        this.personasCargo = other.personasCargo == null ? null : other.personasCargo.copy();
        this.nivelAcademico = other.nivelAcademico == null ? null : other.nivelAcademico.copy();
        this.profesion = other.profesion == null ? null : other.profesion.copy();
        this.fechaGadruacion = other.fechaGadruacion == null ? null : other.fechaGadruacion.copy();
        this.ocupacionActual = other.ocupacionActual == null ? null : other.ocupacionActual.copy();
        this.carta = other.carta == null ? null : other.carta.copy();
        this.suspendidoEstudios = other.suspendidoEstudios == null ? null : other.suspendidoEstudios.copy();
        this.peridoSupencionEstu = other.peridoSupencionEstu == null ? null : other.peridoSupencionEstu.copy();
        this.razonSuspencion = other.razonSuspencion == null ? null : other.razonSuspencion.copy();
        this.tipoContrato = other.tipoContrato == null ? null : other.tipoContrato.copy();
        this.licenciaLaboral = other.licenciaLaboral == null ? null : other.licenciaLaboral.copy();
        this.nivelSalarial = other.nivelSalarial == null ? null : other.nivelSalarial.copy();
        this.tipoLaborIndependiente = other.tipoLaborIndependiente == null ? null : other.tipoLaborIndependiente.copy();
        this.tiempoIndependiente = other.tiempoIndependiente == null ? null : other.tiempoIndependiente.copy();
        this.nivelIngresosIndependiente = other.nivelIngresosIndependiente == null ? null : other.nivelIngresosIndependiente.copy();
        this.tiempoDesempleado = other.tiempoDesempleado == null ? null : other.tiempoDesempleado.copy();
        this.quienFinanciaEstudios = other.quienFinanciaEstudios == null ? null : other.quienFinanciaEstudios.copy();
        this.parentesco = other.parentesco == null ? null : other.parentesco.copy();
        this.presupuestoDisponible = other.presupuestoDisponible == null ? null : other.presupuestoDisponible.copy();
        this.ahorroDisponible = other.ahorroDisponible == null ? null : other.ahorroDisponible.copy();
        this.idioma = other.idioma == null ? null : other.idioma.copy();
        this.certificarIdioma = other.certificarIdioma == null ? null : other.certificarIdioma.copy();
        this.certificacionIdioma = other.certificacionIdioma == null ? null : other.certificacionIdioma.copy();
        this.puntajeCertificacion = other.puntajeCertificacion == null ? null : other.puntajeCertificacion.copy();
        this.salidasPais = other.salidasPais == null ? null : other.salidasPais.copy();
        this.paisesVisitados = other.paisesVisitados == null ? null : other.paisesVisitados.copy();
        this.visaPais = other.visaPais == null ? null : other.visaPais.copy();
        this.estudiadoAnterior = other.estudiadoAnterior == null ? null : other.estudiadoAnterior.copy();
        this.fueraPais = other.fueraPais == null ? null : other.fueraPais.copy();
        this.paisFuera = other.paisFuera == null ? null : other.paisFuera.copy();
        this.extenderEstadia = other.extenderEstadia == null ? null : other.extenderEstadia.copy();
        this.negadoVisa = other.negadoVisa == null ? null : other.negadoVisa.copy();
        this.paisNegado = other.paisNegado == null ? null : other.paisNegado.copy();
        this.familiaresDestino = other.familiaresDestino == null ? null : other.familiaresDestino.copy();
        this.estatusMigratorio = other.estatusMigratorio == null ? null : other.estatusMigratorio.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.apelliod = other.apelliod == null ? null : other.apelliod.copy();
        this.correo = other.correo == null ? null : other.correo.copy();
    }

    @Override
    public ViabilidadVisaCriteria copy() {
        return new ViabilidadVisaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DestinodFilter getDestino() {
        return destino;
    }

    public void setDestino(DestinodFilter destino) {
        this.destino = destino;
    }

    public TipoProgramadFilter getTipoPrograma() {
        return tipoPrograma;
    }

    public void setTipoPrograma(TipoProgramadFilter tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }

    public DuraciondFilter getDuracion() {
        return duracion;
    }

    public void setDuracion(DuraciondFilter duracion) {
        this.duracion = duracion;
    }

    public NacionalidaddFilter getNacionalidadPrincipal() {
        return nacionalidadPrincipal;
    }

    public void setNacionalidadPrincipal(NacionalidaddFilter nacionalidadPrincipal) {
        this.nacionalidadPrincipal = nacionalidadPrincipal;
    }

    public NacionalidaddFilter getOtraNacionalidad() {
        return otraNacionalidad;
    }

    public void setOtraNacionalidad(NacionalidaddFilter otraNacionalidad) {
        this.otraNacionalidad = otraNacionalidad;
    }

    public LocalDateFilter getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateFilter fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GenerodFilter getGenero() {
        return genero;
    }

    public void setGenero(GenerodFilter genero) {
        this.genero = genero;
    }

    public EstadoCivildFilter getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivildFilter estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public BooleanFilter getViajaAcompanado() {
        return viajaAcompanado;
    }

    public void setViajaAcompanado(BooleanFilter viajaAcompanado) {
        this.viajaAcompanado = viajaAcompanado;
    }

    public PersonasCargodFilter getPersonasCargo() {
        return personasCargo;
    }

    public void setPersonasCargo(PersonasCargodFilter personasCargo) {
        this.personasCargo = personasCargo;
    }

    public NivelAcademicodFilter getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(NivelAcademicodFilter nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public StringFilter getProfesion() {
        return profesion;
    }

    public void setProfesion(StringFilter profesion) {
        this.profesion = profesion;
    }

    public LocalDateFilter getFechaGadruacion() {
        return fechaGadruacion;
    }

    public void setFechaGadruacion(LocalDateFilter fechaGadruacion) {
        this.fechaGadruacion = fechaGadruacion;
    }

    public OcupacionActualdFilter getOcupacionActual() {
        return ocupacionActual;
    }

    public void setOcupacionActual(OcupacionActualdFilter ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public BooleanFilter getCarta() {
        return carta;
    }

    public void setCarta(BooleanFilter carta) {
        this.carta = carta;
    }

    public BooleanFilter getSuspendidoEstudios() {
        return suspendidoEstudios;
    }

    public void setSuspendidoEstudios(BooleanFilter suspendidoEstudios) {
        this.suspendidoEstudios = suspendidoEstudios;
    }

    public PeridoSupencionEstudFilter getPeridoSupencionEstu() {
        return peridoSupencionEstu;
    }

    public void setPeridoSupencionEstu(PeridoSupencionEstudFilter peridoSupencionEstu) {
        this.peridoSupencionEstu = peridoSupencionEstu;
    }

    public StringFilter getRazonSuspencion() {
        return razonSuspencion;
    }

    public void setRazonSuspencion(StringFilter razonSuspencion) {
        this.razonSuspencion = razonSuspencion;
    }

    public TipoContratodFilter getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContratodFilter tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public BooleanFilter getLicenciaLaboral() {
        return licenciaLaboral;
    }

    public void setLicenciaLaboral(BooleanFilter licenciaLaboral) {
        this.licenciaLaboral = licenciaLaboral;
    }

    public NivelSalarialdFilter getNivelSalarial() {
        return nivelSalarial;
    }

    public void setNivelSalarial(NivelSalarialdFilter nivelSalarial) {
        this.nivelSalarial = nivelSalarial;
    }

    public TipoLaborIndependientedFilter getTipoLaborIndependiente() {
        return tipoLaborIndependiente;
    }

    public void setTipoLaborIndependiente(TipoLaborIndependientedFilter tipoLaborIndependiente) {
        this.tipoLaborIndependiente = tipoLaborIndependiente;
    }

    public PeridoSupencionEstudFilter getTiempoIndependiente() {
        return tiempoIndependiente;
    }

    public void setTiempoIndependiente(PeridoSupencionEstudFilter tiempoIndependiente) {
        this.tiempoIndependiente = tiempoIndependiente;
    }

    public NivelSalarialdFilter getNivelIngresosIndependiente() {
        return nivelIngresosIndependiente;
    }

    public void setNivelIngresosIndependiente(NivelSalarialdFilter nivelIngresosIndependiente) {
        this.nivelIngresosIndependiente = nivelIngresosIndependiente;
    }

    public PeridoSupencionEstudFilter getTiempoDesempleado() {
        return tiempoDesempleado;
    }

    public void setTiempoDesempleado(PeridoSupencionEstudFilter tiempoDesempleado) {
        this.tiempoDesempleado = tiempoDesempleado;
    }

    public QuienFinanciaEstudiosdFilter getQuienFinanciaEstudios() {
        return quienFinanciaEstudios;
    }

    public void setQuienFinanciaEstudios(QuienFinanciaEstudiosdFilter quienFinanciaEstudios) {
        this.quienFinanciaEstudios = quienFinanciaEstudios;
    }

    public ParentescodFilter getParentesco() {
        return parentesco;
    }

    public void setParentesco(ParentescodFilter parentesco) {
        this.parentesco = parentesco;
    }

    public NivelSalarialdFilter getPresupuestoDisponible() {
        return presupuestoDisponible;
    }

    public void setPresupuestoDisponible(NivelSalarialdFilter presupuestoDisponible) {
        this.presupuestoDisponible = presupuestoDisponible;
    }

    public BooleanFilter getAhorroDisponible() {
        return ahorroDisponible;
    }

    public void setAhorroDisponible(BooleanFilter ahorroDisponible) {
        this.ahorroDisponible = ahorroDisponible;
    }

    public BooleanFilter getIdioma() {
        return idioma;
    }

    public void setIdioma(BooleanFilter idioma) {
        this.idioma = idioma;
    }

    public BooleanFilter getCertificarIdioma() {
        return certificarIdioma;
    }

    public void setCertificarIdioma(BooleanFilter certificarIdioma) {
        this.certificarIdioma = certificarIdioma;
    }

    public StringFilter getCertificacionIdioma() {
        return certificacionIdioma;
    }

    public void setCertificacionIdioma(StringFilter certificacionIdioma) {
        this.certificacionIdioma = certificacionIdioma;
    }

    public StringFilter getPuntajeCertificacion() {
        return puntajeCertificacion;
    }

    public void setPuntajeCertificacion(StringFilter puntajeCertificacion) {
        this.puntajeCertificacion = puntajeCertificacion;
    }

    public StringFilter getSalidasPais() {
        return salidasPais;
    }

    public void setSalidasPais(StringFilter salidasPais) {
        this.salidasPais = salidasPais;
    }

    public StringFilter getPaisesVisitados() {
        return paisesVisitados;
    }

    public void setPaisesVisitados(StringFilter paisesVisitados) {
        this.paisesVisitados = paisesVisitados;
    }

    public StringFilter getVisaPais() {
        return visaPais;
    }

    public void setVisaPais(StringFilter visaPais) {
        this.visaPais = visaPais;
    }

    public BooleanFilter getEstudiadoAnterior() {
        return estudiadoAnterior;
    }

    public void setEstudiadoAnterior(BooleanFilter estudiadoAnterior) {
        this.estudiadoAnterior = estudiadoAnterior;
    }

    public BooleanFilter getFueraPais() {
        return fueraPais;
    }

    public void setFueraPais(BooleanFilter fueraPais) {
        this.fueraPais = fueraPais;
    }

    public StringFilter getPaisFuera() {
        return paisFuera;
    }

    public void setPaisFuera(StringFilter paisFuera) {
        this.paisFuera = paisFuera;
    }

    public BooleanFilter getExtenderEstadia() {
        return extenderEstadia;
    }

    public void setExtenderEstadia(BooleanFilter extenderEstadia) {
        this.extenderEstadia = extenderEstadia;
    }

    public BooleanFilter getNegadoVisa() {
        return negadoVisa;
    }

    public void setNegadoVisa(BooleanFilter negadoVisa) {
        this.negadoVisa = negadoVisa;
    }

    public StringFilter getPaisNegado() {
        return paisNegado;
    }

    public void setPaisNegado(StringFilter paisNegado) {
        this.paisNegado = paisNegado;
    }

    public BooleanFilter getFamiliaresDestino() {
        return familiaresDestino;
    }

    public void setFamiliaresDestino(BooleanFilter familiaresDestino) {
        this.familiaresDestino = familiaresDestino;
    }

    public StringFilter getEstatusMigratorio() {
        return estatusMigratorio;
    }

    public void setEstatusMigratorio(StringFilter estatusMigratorio) {
        this.estatusMigratorio = estatusMigratorio;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getApelliod() {
        return apelliod;
    }

    public void setApelliod(StringFilter apelliod) {
        this.apelliod = apelliod;
    }

    public StringFilter getCorreo() {
        return correo;
    }

    public void setCorreo(StringFilter correo) {
        this.correo = correo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ViabilidadVisaCriteria that = (ViabilidadVisaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(destino, that.destino) &&
            Objects.equals(tipoPrograma, that.tipoPrograma) &&
            Objects.equals(duracion, that.duracion) &&
            Objects.equals(nacionalidadPrincipal, that.nacionalidadPrincipal) &&
            Objects.equals(otraNacionalidad, that.otraNacionalidad) &&
            Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
            Objects.equals(genero, that.genero) &&
            Objects.equals(estadoCivil, that.estadoCivil) &&
            Objects.equals(viajaAcompanado, that.viajaAcompanado) &&
            Objects.equals(personasCargo, that.personasCargo) &&
            Objects.equals(nivelAcademico, that.nivelAcademico) &&
            Objects.equals(profesion, that.profesion) &&
            Objects.equals(fechaGadruacion, that.fechaGadruacion) &&
            Objects.equals(ocupacionActual, that.ocupacionActual) &&
            Objects.equals(carta, that.carta) &&
            Objects.equals(suspendidoEstudios, that.suspendidoEstudios) &&
            Objects.equals(peridoSupencionEstu, that.peridoSupencionEstu) &&
            Objects.equals(razonSuspencion, that.razonSuspencion) &&
            Objects.equals(tipoContrato, that.tipoContrato) &&
            Objects.equals(licenciaLaboral, that.licenciaLaboral) &&
            Objects.equals(nivelSalarial, that.nivelSalarial) &&
            Objects.equals(tipoLaborIndependiente, that.tipoLaborIndependiente) &&
            Objects.equals(tiempoIndependiente, that.tiempoIndependiente) &&
            Objects.equals(nivelIngresosIndependiente, that.nivelIngresosIndependiente) &&
            Objects.equals(tiempoDesempleado, that.tiempoDesempleado) &&
            Objects.equals(quienFinanciaEstudios, that.quienFinanciaEstudios) &&
            Objects.equals(parentesco, that.parentesco) &&
            Objects.equals(presupuestoDisponible, that.presupuestoDisponible) &&
            Objects.equals(ahorroDisponible, that.ahorroDisponible) &&
            Objects.equals(idioma, that.idioma) &&
            Objects.equals(certificarIdioma, that.certificarIdioma) &&
            Objects.equals(certificacionIdioma, that.certificacionIdioma) &&
            Objects.equals(puntajeCertificacion, that.puntajeCertificacion) &&
            Objects.equals(salidasPais, that.salidasPais) &&
            Objects.equals(paisesVisitados, that.paisesVisitados) &&
            Objects.equals(visaPais, that.visaPais) &&
            Objects.equals(estudiadoAnterior, that.estudiadoAnterior) &&
            Objects.equals(fueraPais, that.fueraPais) &&
            Objects.equals(paisFuera, that.paisFuera) &&
            Objects.equals(extenderEstadia, that.extenderEstadia) &&
            Objects.equals(negadoVisa, that.negadoVisa) &&
            Objects.equals(paisNegado, that.paisNegado) &&
            Objects.equals(familiaresDestino, that.familiaresDestino) &&
            Objects.equals(estatusMigratorio, that.estatusMigratorio) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(apelliod, that.apelliod) &&
            Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        destino,
        tipoPrograma,
        duracion,
        nacionalidadPrincipal,
        otraNacionalidad,
        fechaNacimiento,
        genero,
        estadoCivil,
        viajaAcompanado,
        personasCargo,
        nivelAcademico,
        profesion,
        fechaGadruacion,
        ocupacionActual,
        carta,
        suspendidoEstudios,
        peridoSupencionEstu,
        razonSuspencion,
        tipoContrato,
        licenciaLaboral,
        nivelSalarial,
        tipoLaborIndependiente,
        tiempoIndependiente,
        nivelIngresosIndependiente,
        tiempoDesempleado,
        quienFinanciaEstudios,
        parentesco,
        presupuestoDisponible,
        ahorroDisponible,
        idioma,
        certificarIdioma,
        certificacionIdioma,
        puntajeCertificacion,
        salidasPais,
        paisesVisitados,
        visaPais,
        estudiadoAnterior,
        fueraPais,
        paisFuera,
        extenderEstadia,
        negadoVisa,
        paisNegado,
        familiaresDestino,
        estatusMigratorio,
        nombre,
        apelliod,
        correo
        );
    }

    @Override
    public String toString() {
        return "ViabilidadVisaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (destino != null ? "destino=" + destino + ", " : "") +
                (tipoPrograma != null ? "tipoPrograma=" + tipoPrograma + ", " : "") +
                (duracion != null ? "duracion=" + duracion + ", " : "") +
                (nacionalidadPrincipal != null ? "nacionalidadPrincipal=" + nacionalidadPrincipal + ", " : "") +
                (otraNacionalidad != null ? "otraNacionalidad=" + otraNacionalidad + ", " : "") +
                (fechaNacimiento != null ? "fechaNacimiento=" + fechaNacimiento + ", " : "") +
                (genero != null ? "genero=" + genero + ", " : "") +
                (estadoCivil != null ? "estadoCivil=" + estadoCivil + ", " : "") +
                (viajaAcompanado != null ? "viajaAcompanado=" + viajaAcompanado + ", " : "") +
                (personasCargo != null ? "personasCargo=" + personasCargo + ", " : "") +
                (nivelAcademico != null ? "nivelAcademico=" + nivelAcademico + ", " : "") +
                (profesion != null ? "profesion=" + profesion + ", " : "") +
                (fechaGadruacion != null ? "fechaGadruacion=" + fechaGadruacion + ", " : "") +
                (ocupacionActual != null ? "ocupacionActual=" + ocupacionActual + ", " : "") +
                (carta != null ? "carta=" + carta + ", " : "") +
                (suspendidoEstudios != null ? "suspendidoEstudios=" + suspendidoEstudios + ", " : "") +
                (peridoSupencionEstu != null ? "peridoSupencionEstu=" + peridoSupencionEstu + ", " : "") +
                (razonSuspencion != null ? "razonSuspencion=" + razonSuspencion + ", " : "") +
                (tipoContrato != null ? "tipoContrato=" + tipoContrato + ", " : "") +
                (licenciaLaboral != null ? "licenciaLaboral=" + licenciaLaboral + ", " : "") +
                (nivelSalarial != null ? "nivelSalarial=" + nivelSalarial + ", " : "") +
                (tipoLaborIndependiente != null ? "tipoLaborIndependiente=" + tipoLaborIndependiente + ", " : "") +
                (tiempoIndependiente != null ? "tiempoIndependiente=" + tiempoIndependiente + ", " : "") +
                (nivelIngresosIndependiente != null ? "nivelIngresosIndependiente=" + nivelIngresosIndependiente + ", " : "") +
                (tiempoDesempleado != null ? "tiempoDesempleado=" + tiempoDesempleado + ", " : "") +
                (quienFinanciaEstudios != null ? "quienFinanciaEstudios=" + quienFinanciaEstudios + ", " : "") +
                (parentesco != null ? "parentesco=" + parentesco + ", " : "") +
                (presupuestoDisponible != null ? "presupuestoDisponible=" + presupuestoDisponible + ", " : "") +
                (ahorroDisponible != null ? "ahorroDisponible=" + ahorroDisponible + ", " : "") +
                (idioma != null ? "idioma=" + idioma + ", " : "") +
                (certificarIdioma != null ? "certificarIdioma=" + certificarIdioma + ", " : "") +
                (certificacionIdioma != null ? "certificacionIdioma=" + certificacionIdioma + ", " : "") +
                (puntajeCertificacion != null ? "puntajeCertificacion=" + puntajeCertificacion + ", " : "") +
                (salidasPais != null ? "salidasPais=" + salidasPais + ", " : "") +
                (paisesVisitados != null ? "paisesVisitados=" + paisesVisitados + ", " : "") +
                (visaPais != null ? "visaPais=" + visaPais + ", " : "") +
                (estudiadoAnterior != null ? "estudiadoAnterior=" + estudiadoAnterior + ", " : "") +
                (fueraPais != null ? "fueraPais=" + fueraPais + ", " : "") +
                (paisFuera != null ? "paisFuera=" + paisFuera + ", " : "") +
                (extenderEstadia != null ? "extenderEstadia=" + extenderEstadia + ", " : "") +
                (negadoVisa != null ? "negadoVisa=" + negadoVisa + ", " : "") +
                (paisNegado != null ? "paisNegado=" + paisNegado + ", " : "") +
                (familiaresDestino != null ? "familiaresDestino=" + familiaresDestino + ", " : "") +
                (estatusMigratorio != null ? "estatusMigratorio=" + estatusMigratorio + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (apelliod != null ? "apelliod=" + apelliod + ", " : "") +
                (correo != null ? "correo=" + correo + ", " : "") +
            "}";
    }

}
