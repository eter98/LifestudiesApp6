package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

import io.github.jhipster.application.domain.enumeration.Destinod;

import io.github.jhipster.application.domain.enumeration.TipoProgramad;

import io.github.jhipster.application.domain.enumeration.Duraciond;

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

import io.github.jhipster.application.domain.enumeration.QuienFinanciaEstudiosd;

import io.github.jhipster.application.domain.enumeration.Parentescod;

/**
 * A ViabilidadVisa.
 */
@Entity
@Table(name = "viabilidad_visa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "viabilidadvisa")
public class ViabilidadVisa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "destino")
    private Destinod destino;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_programa")
    private TipoProgramad tipoPrograma;

    @Enumerated(EnumType.STRING)
    @Column(name = "duracion")
    private Duraciond duracion;

    @Enumerated(EnumType.STRING)
    @Column(name = "nacionalidad_principal")
    private Nacionalidadd nacionalidadPrincipal;

    @Enumerated(EnumType.STRING)
    @Column(name = "otra_nacionalidad")
    private Nacionalidadd otraNacionalidad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Generod genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivild estadoCivil;

    @Column(name = "viaja_acompanado")
    private Boolean viajaAcompanado;

    @Enumerated(EnumType.STRING)
    @Column(name = "personas_cargo")
    private PersonasCargod personasCargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_academico")
    private NivelAcademicod nivelAcademico;

    @Column(name = "profesion")
    private String profesion;

    @Column(name = "fecha_gadruacion")
    private LocalDate fechaGadruacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "ocupacion_actual")
    private OcupacionActuald ocupacionActual;

    @Column(name = "carta")
    private Boolean carta;

    @Column(name = "suspendido_estudios")
    private Boolean suspendidoEstudios;

    @Enumerated(EnumType.STRING)
    @Column(name = "perido_supencion_estu")
    private PeridoSupencionEstud peridoSupencionEstu;

    @Column(name = "razon_suspencion")
    private String razonSuspencion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contrato")
    private TipoContratod tipoContrato;

    @Column(name = "licencia_laboral")
    private Boolean licenciaLaboral;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_salarial")
    private NivelSalariald nivelSalarial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_labor_independiente")
    private TipoLaborIndependiented tipoLaborIndependiente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tiempo_independiente")
    private PeridoSupencionEstud tiempoIndependiente;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_ingresos_independiente")
    private NivelSalariald nivelIngresosIndependiente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tiempo_desempleado")
    private PeridoSupencionEstud tiempoDesempleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "quien_financia_estudios")
    private QuienFinanciaEstudiosd quienFinanciaEstudios;

    @Enumerated(EnumType.STRING)
    @Column(name = "parentesco")
    private Parentescod parentesco;

    @Enumerated(EnumType.STRING)
    @Column(name = "presupuesto_disponible")
    private NivelSalariald presupuestoDisponible;

    @Column(name = "ahorro_disponible")
    private Boolean ahorroDisponible;

    @Column(name = "idioma")
    private Boolean idioma;

    @Column(name = "certificar_idioma")
    private Boolean certificarIdioma;

    @Column(name = "certificacion_idioma")
    private String certificacionIdioma;

    @Column(name = "puntaje_certificacion")
    private String puntajeCertificacion;

    @Column(name = "salidas_pais")
    private String salidasPais;

    @Column(name = "paises_visitados")
    private String paisesVisitados;

    @Column(name = "visa_pais")
    private String visaPais;

    @Column(name = "estudiado_anterior")
    private Boolean estudiadoAnterior;

    @Column(name = "fuera_pais")
    private Boolean fueraPais;

    @Column(name = "pais_fuera")
    private String paisFuera;

    @Column(name = "extender_estadia")
    private Boolean extenderEstadia;

    @Column(name = "negado_visa")
    private Boolean negadoVisa;

    @Column(name = "pais_negado")
    private String paisNegado;

    @Column(name = "familiares_destino")
    private Boolean familiaresDestino;

    @Column(name = "estatus_migratorio")
    private String estatusMigratorio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apelliod")
    private String apelliod;

    @Column(name = "correo")
    private String correo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destinod getDestino() {
        return destino;
    }

    public ViabilidadVisa destino(Destinod destino) {
        this.destino = destino;
        return this;
    }

    public void setDestino(Destinod destino) {
        this.destino = destino;
    }

    public TipoProgramad getTipoPrograma() {
        return tipoPrograma;
    }

    public ViabilidadVisa tipoPrograma(TipoProgramad tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
        return this;
    }

    public void setTipoPrograma(TipoProgramad tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }

    public Duraciond getDuracion() {
        return duracion;
    }

    public ViabilidadVisa duracion(Duraciond duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(Duraciond duracion) {
        this.duracion = duracion;
    }

    public Nacionalidadd getNacionalidadPrincipal() {
        return nacionalidadPrincipal;
    }

    public ViabilidadVisa nacionalidadPrincipal(Nacionalidadd nacionalidadPrincipal) {
        this.nacionalidadPrincipal = nacionalidadPrincipal;
        return this;
    }

    public void setNacionalidadPrincipal(Nacionalidadd nacionalidadPrincipal) {
        this.nacionalidadPrincipal = nacionalidadPrincipal;
    }

    public Nacionalidadd getOtraNacionalidad() {
        return otraNacionalidad;
    }

    public ViabilidadVisa otraNacionalidad(Nacionalidadd otraNacionalidad) {
        this.otraNacionalidad = otraNacionalidad;
        return this;
    }

    public void setOtraNacionalidad(Nacionalidadd otraNacionalidad) {
        this.otraNacionalidad = otraNacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public ViabilidadVisa fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Generod getGenero() {
        return genero;
    }

    public ViabilidadVisa genero(Generod genero) {
        this.genero = genero;
        return this;
    }

    public void setGenero(Generod genero) {
        this.genero = genero;
    }

    public EstadoCivild getEstadoCivil() {
        return estadoCivil;
    }

    public ViabilidadVisa estadoCivil(EstadoCivild estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public void setEstadoCivil(EstadoCivild estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Boolean isViajaAcompanado() {
        return viajaAcompanado;
    }

    public ViabilidadVisa viajaAcompanado(Boolean viajaAcompanado) {
        this.viajaAcompanado = viajaAcompanado;
        return this;
    }

    public void setViajaAcompanado(Boolean viajaAcompanado) {
        this.viajaAcompanado = viajaAcompanado;
    }

    public PersonasCargod getPersonasCargo() {
        return personasCargo;
    }

    public ViabilidadVisa personasCargo(PersonasCargod personasCargo) {
        this.personasCargo = personasCargo;
        return this;
    }

    public void setPersonasCargo(PersonasCargod personasCargo) {
        this.personasCargo = personasCargo;
    }

    public NivelAcademicod getNivelAcademico() {
        return nivelAcademico;
    }

    public ViabilidadVisa nivelAcademico(NivelAcademicod nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
        return this;
    }

    public void setNivelAcademico(NivelAcademicod nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getProfesion() {
        return profesion;
    }

    public ViabilidadVisa profesion(String profesion) {
        this.profesion = profesion;
        return this;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public LocalDate getFechaGadruacion() {
        return fechaGadruacion;
    }

    public ViabilidadVisa fechaGadruacion(LocalDate fechaGadruacion) {
        this.fechaGadruacion = fechaGadruacion;
        return this;
    }

    public void setFechaGadruacion(LocalDate fechaGadruacion) {
        this.fechaGadruacion = fechaGadruacion;
    }

    public OcupacionActuald getOcupacionActual() {
        return ocupacionActual;
    }

    public ViabilidadVisa ocupacionActual(OcupacionActuald ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
        return this;
    }

    public void setOcupacionActual(OcupacionActuald ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public Boolean isCarta() {
        return carta;
    }

    public ViabilidadVisa carta(Boolean carta) {
        this.carta = carta;
        return this;
    }

    public void setCarta(Boolean carta) {
        this.carta = carta;
    }

    public Boolean isSuspendidoEstudios() {
        return suspendidoEstudios;
    }

    public ViabilidadVisa suspendidoEstudios(Boolean suspendidoEstudios) {
        this.suspendidoEstudios = suspendidoEstudios;
        return this;
    }

    public void setSuspendidoEstudios(Boolean suspendidoEstudios) {
        this.suspendidoEstudios = suspendidoEstudios;
    }

    public PeridoSupencionEstud getPeridoSupencionEstu() {
        return peridoSupencionEstu;
    }

    public ViabilidadVisa peridoSupencionEstu(PeridoSupencionEstud peridoSupencionEstu) {
        this.peridoSupencionEstu = peridoSupencionEstu;
        return this;
    }

    public void setPeridoSupencionEstu(PeridoSupencionEstud peridoSupencionEstu) {
        this.peridoSupencionEstu = peridoSupencionEstu;
    }

    public String getRazonSuspencion() {
        return razonSuspencion;
    }

    public ViabilidadVisa razonSuspencion(String razonSuspencion) {
        this.razonSuspencion = razonSuspencion;
        return this;
    }

    public void setRazonSuspencion(String razonSuspencion) {
        this.razonSuspencion = razonSuspencion;
    }

    public TipoContratod getTipoContrato() {
        return tipoContrato;
    }

    public ViabilidadVisa tipoContrato(TipoContratod tipoContrato) {
        this.tipoContrato = tipoContrato;
        return this;
    }

    public void setTipoContrato(TipoContratod tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Boolean isLicenciaLaboral() {
        return licenciaLaboral;
    }

    public ViabilidadVisa licenciaLaboral(Boolean licenciaLaboral) {
        this.licenciaLaboral = licenciaLaboral;
        return this;
    }

    public void setLicenciaLaboral(Boolean licenciaLaboral) {
        this.licenciaLaboral = licenciaLaboral;
    }

    public NivelSalariald getNivelSalarial() {
        return nivelSalarial;
    }

    public ViabilidadVisa nivelSalarial(NivelSalariald nivelSalarial) {
        this.nivelSalarial = nivelSalarial;
        return this;
    }

    public void setNivelSalarial(NivelSalariald nivelSalarial) {
        this.nivelSalarial = nivelSalarial;
    }

    public TipoLaborIndependiented getTipoLaborIndependiente() {
        return tipoLaborIndependiente;
    }

    public ViabilidadVisa tipoLaborIndependiente(TipoLaborIndependiented tipoLaborIndependiente) {
        this.tipoLaborIndependiente = tipoLaborIndependiente;
        return this;
    }

    public void setTipoLaborIndependiente(TipoLaborIndependiented tipoLaborIndependiente) {
        this.tipoLaborIndependiente = tipoLaborIndependiente;
    }

    public PeridoSupencionEstud getTiempoIndependiente() {
        return tiempoIndependiente;
    }

    public ViabilidadVisa tiempoIndependiente(PeridoSupencionEstud tiempoIndependiente) {
        this.tiempoIndependiente = tiempoIndependiente;
        return this;
    }

    public void setTiempoIndependiente(PeridoSupencionEstud tiempoIndependiente) {
        this.tiempoIndependiente = tiempoIndependiente;
    }

    public NivelSalariald getNivelIngresosIndependiente() {
        return nivelIngresosIndependiente;
    }

    public ViabilidadVisa nivelIngresosIndependiente(NivelSalariald nivelIngresosIndependiente) {
        this.nivelIngresosIndependiente = nivelIngresosIndependiente;
        return this;
    }

    public void setNivelIngresosIndependiente(NivelSalariald nivelIngresosIndependiente) {
        this.nivelIngresosIndependiente = nivelIngresosIndependiente;
    }

    public PeridoSupencionEstud getTiempoDesempleado() {
        return tiempoDesempleado;
    }

    public ViabilidadVisa tiempoDesempleado(PeridoSupencionEstud tiempoDesempleado) {
        this.tiempoDesempleado = tiempoDesempleado;
        return this;
    }

    public void setTiempoDesempleado(PeridoSupencionEstud tiempoDesempleado) {
        this.tiempoDesempleado = tiempoDesempleado;
    }

    public QuienFinanciaEstudiosd getQuienFinanciaEstudios() {
        return quienFinanciaEstudios;
    }

    public ViabilidadVisa quienFinanciaEstudios(QuienFinanciaEstudiosd quienFinanciaEstudios) {
        this.quienFinanciaEstudios = quienFinanciaEstudios;
        return this;
    }

    public void setQuienFinanciaEstudios(QuienFinanciaEstudiosd quienFinanciaEstudios) {
        this.quienFinanciaEstudios = quienFinanciaEstudios;
    }

    public Parentescod getParentesco() {
        return parentesco;
    }

    public ViabilidadVisa parentesco(Parentescod parentesco) {
        this.parentesco = parentesco;
        return this;
    }

    public void setParentesco(Parentescod parentesco) {
        this.parentesco = parentesco;
    }

    public NivelSalariald getPresupuestoDisponible() {
        return presupuestoDisponible;
    }

    public ViabilidadVisa presupuestoDisponible(NivelSalariald presupuestoDisponible) {
        this.presupuestoDisponible = presupuestoDisponible;
        return this;
    }

    public void setPresupuestoDisponible(NivelSalariald presupuestoDisponible) {
        this.presupuestoDisponible = presupuestoDisponible;
    }

    public Boolean isAhorroDisponible() {
        return ahorroDisponible;
    }

    public ViabilidadVisa ahorroDisponible(Boolean ahorroDisponible) {
        this.ahorroDisponible = ahorroDisponible;
        return this;
    }

    public void setAhorroDisponible(Boolean ahorroDisponible) {
        this.ahorroDisponible = ahorroDisponible;
    }

    public Boolean isIdioma() {
        return idioma;
    }

    public ViabilidadVisa idioma(Boolean idioma) {
        this.idioma = idioma;
        return this;
    }

    public void setIdioma(Boolean idioma) {
        this.idioma = idioma;
    }

    public Boolean isCertificarIdioma() {
        return certificarIdioma;
    }

    public ViabilidadVisa certificarIdioma(Boolean certificarIdioma) {
        this.certificarIdioma = certificarIdioma;
        return this;
    }

    public void setCertificarIdioma(Boolean certificarIdioma) {
        this.certificarIdioma = certificarIdioma;
    }

    public String getCertificacionIdioma() {
        return certificacionIdioma;
    }

    public ViabilidadVisa certificacionIdioma(String certificacionIdioma) {
        this.certificacionIdioma = certificacionIdioma;
        return this;
    }

    public void setCertificacionIdioma(String certificacionIdioma) {
        this.certificacionIdioma = certificacionIdioma;
    }

    public String getPuntajeCertificacion() {
        return puntajeCertificacion;
    }

    public ViabilidadVisa puntajeCertificacion(String puntajeCertificacion) {
        this.puntajeCertificacion = puntajeCertificacion;
        return this;
    }

    public void setPuntajeCertificacion(String puntajeCertificacion) {
        this.puntajeCertificacion = puntajeCertificacion;
    }

    public String getSalidasPais() {
        return salidasPais;
    }

    public ViabilidadVisa salidasPais(String salidasPais) {
        this.salidasPais = salidasPais;
        return this;
    }

    public void setSalidasPais(String salidasPais) {
        this.salidasPais = salidasPais;
    }

    public String getPaisesVisitados() {
        return paisesVisitados;
    }

    public ViabilidadVisa paisesVisitados(String paisesVisitados) {
        this.paisesVisitados = paisesVisitados;
        return this;
    }

    public void setPaisesVisitados(String paisesVisitados) {
        this.paisesVisitados = paisesVisitados;
    }

    public String getVisaPais() {
        return visaPais;
    }

    public ViabilidadVisa visaPais(String visaPais) {
        this.visaPais = visaPais;
        return this;
    }

    public void setVisaPais(String visaPais) {
        this.visaPais = visaPais;
    }

    public Boolean isEstudiadoAnterior() {
        return estudiadoAnterior;
    }

    public ViabilidadVisa estudiadoAnterior(Boolean estudiadoAnterior) {
        this.estudiadoAnterior = estudiadoAnterior;
        return this;
    }

    public void setEstudiadoAnterior(Boolean estudiadoAnterior) {
        this.estudiadoAnterior = estudiadoAnterior;
    }

    public Boolean isFueraPais() {
        return fueraPais;
    }

    public ViabilidadVisa fueraPais(Boolean fueraPais) {
        this.fueraPais = fueraPais;
        return this;
    }

    public void setFueraPais(Boolean fueraPais) {
        this.fueraPais = fueraPais;
    }

    public String getPaisFuera() {
        return paisFuera;
    }

    public ViabilidadVisa paisFuera(String paisFuera) {
        this.paisFuera = paisFuera;
        return this;
    }

    public void setPaisFuera(String paisFuera) {
        this.paisFuera = paisFuera;
    }

    public Boolean isExtenderEstadia() {
        return extenderEstadia;
    }

    public ViabilidadVisa extenderEstadia(Boolean extenderEstadia) {
        this.extenderEstadia = extenderEstadia;
        return this;
    }

    public void setExtenderEstadia(Boolean extenderEstadia) {
        this.extenderEstadia = extenderEstadia;
    }

    public Boolean isNegadoVisa() {
        return negadoVisa;
    }

    public ViabilidadVisa negadoVisa(Boolean negadoVisa) {
        this.negadoVisa = negadoVisa;
        return this;
    }

    public void setNegadoVisa(Boolean negadoVisa) {
        this.negadoVisa = negadoVisa;
    }

    public String getPaisNegado() {
        return paisNegado;
    }

    public ViabilidadVisa paisNegado(String paisNegado) {
        this.paisNegado = paisNegado;
        return this;
    }

    public void setPaisNegado(String paisNegado) {
        this.paisNegado = paisNegado;
    }

    public Boolean isFamiliaresDestino() {
        return familiaresDestino;
    }

    public ViabilidadVisa familiaresDestino(Boolean familiaresDestino) {
        this.familiaresDestino = familiaresDestino;
        return this;
    }

    public void setFamiliaresDestino(Boolean familiaresDestino) {
        this.familiaresDestino = familiaresDestino;
    }

    public String getEstatusMigratorio() {
        return estatusMigratorio;
    }

    public ViabilidadVisa estatusMigratorio(String estatusMigratorio) {
        this.estatusMigratorio = estatusMigratorio;
        return this;
    }

    public void setEstatusMigratorio(String estatusMigratorio) {
        this.estatusMigratorio = estatusMigratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public ViabilidadVisa nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApelliod() {
        return apelliod;
    }

    public ViabilidadVisa apelliod(String apelliod) {
        this.apelliod = apelliod;
        return this;
    }

    public void setApelliod(String apelliod) {
        this.apelliod = apelliod;
    }

    public String getCorreo() {
        return correo;
    }

    public ViabilidadVisa correo(String correo) {
        this.correo = correo;
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViabilidadVisa)) {
            return false;
        }
        return id != null && id.equals(((ViabilidadVisa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ViabilidadVisa{" +
            "id=" + getId() +
            ", destino='" + getDestino() + "'" +
            ", tipoPrograma='" + getTipoPrograma() + "'" +
            ", duracion='" + getDuracion() + "'" +
            ", nacionalidadPrincipal='" + getNacionalidadPrincipal() + "'" +
            ", otraNacionalidad='" + getOtraNacionalidad() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", genero='" + getGenero() + "'" +
            ", estadoCivil='" + getEstadoCivil() + "'" +
            ", viajaAcompanado='" + isViajaAcompanado() + "'" +
            ", personasCargo='" + getPersonasCargo() + "'" +
            ", nivelAcademico='" + getNivelAcademico() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", fechaGadruacion='" + getFechaGadruacion() + "'" +
            ", ocupacionActual='" + getOcupacionActual() + "'" +
            ", carta='" + isCarta() + "'" +
            ", suspendidoEstudios='" + isSuspendidoEstudios() + "'" +
            ", peridoSupencionEstu='" + getPeridoSupencionEstu() + "'" +
            ", razonSuspencion='" + getRazonSuspencion() + "'" +
            ", tipoContrato='" + getTipoContrato() + "'" +
            ", licenciaLaboral='" + isLicenciaLaboral() + "'" +
            ", nivelSalarial='" + getNivelSalarial() + "'" +
            ", tipoLaborIndependiente='" + getTipoLaborIndependiente() + "'" +
            ", tiempoIndependiente='" + getTiempoIndependiente() + "'" +
            ", nivelIngresosIndependiente='" + getNivelIngresosIndependiente() + "'" +
            ", tiempoDesempleado='" + getTiempoDesempleado() + "'" +
            ", quienFinanciaEstudios='" + getQuienFinanciaEstudios() + "'" +
            ", parentesco='" + getParentesco() + "'" +
            ", presupuestoDisponible='" + getPresupuestoDisponible() + "'" +
            ", ahorroDisponible='" + isAhorroDisponible() + "'" +
            ", idioma='" + isIdioma() + "'" +
            ", certificarIdioma='" + isCertificarIdioma() + "'" +
            ", certificacionIdioma='" + getCertificacionIdioma() + "'" +
            ", puntajeCertificacion='" + getPuntajeCertificacion() + "'" +
            ", salidasPais='" + getSalidasPais() + "'" +
            ", paisesVisitados='" + getPaisesVisitados() + "'" +
            ", visaPais='" + getVisaPais() + "'" +
            ", estudiadoAnterior='" + isEstudiadoAnterior() + "'" +
            ", fueraPais='" + isFueraPais() + "'" +
            ", paisFuera='" + getPaisFuera() + "'" +
            ", extenderEstadia='" + isExtenderEstadia() + "'" +
            ", negadoVisa='" + isNegadoVisa() + "'" +
            ", paisNegado='" + getPaisNegado() + "'" +
            ", familiaresDestino='" + isFamiliaresDestino() + "'" +
            ", estatusMigratorio='" + getEstatusMigratorio() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apelliod='" + getApelliod() + "'" +
            ", correo='" + getCorreo() + "'" +
            "}";
    }
}
