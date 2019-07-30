package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Institucion.
 */
@Entity
@Table(name = "institucion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "institucion")
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "website")
    private String website;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "representante")
    private String representante;

    @Column(name = "skype")
    private String skype;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "tipo_programas")
    private String tipoProgramas;

    @Column(name = "programa_estandar")
    private String programaEstandar;

    @Column(name = "programa_intensivo")
    private String programaIntensivo;

    @Column(name = "programa_negocios")
    private String programaNegocios;

    @Column(name = "preparacion_examen")
    private String preparacionExamen;

    @Column(name = "programa_academico")
    private String programaAcademico;

    @Column(name = "descuento")
    private Integer descuento;

    @Column(name = "inscripcion")
    private String inscripcion;

    @Column(name = "materiales")
    private String materiales;

    @Column(name = "seguro_medico")
    private String seguroMedico;

    @Column(name = "alojamiento_sencillo")
    private String alojamientoSencillo;

    @Column(name = "alojamiento_doble")
    private String alojamientoDoble;

    @Column(name = "transporte_aeropuerto")
    private String transporteAeropuerto;

    @Column(name = "tipo_curso")
    private String tipoCurso;

    @Column(name = "temporada_alta")
    private String temporadaAlta;

    @Column(name = "temporada_baja")
    private String temporadaBaja;

    @Column(name = "fecha_inicial")
    private LocalDate fechaInicial;

    @Column(name = "horarios")
    private String horarios;

    @Column(name = "instalaciones")
    private String instalaciones;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @Lob
    @Column(name = "foto_1")
    private byte[] foto1;

    @Column(name = "foto_1_content_type")
    private String foto1ContentType;

    @Lob
    @Column(name = "foto_2")
    private byte[] foto2;

    @Column(name = "foto_2_content_type")
    private String foto2ContentType;

    @Lob
    @Column(name = "foto_3")
    private byte[] foto3;

    @Column(name = "foto_3_content_type")
    private String foto3ContentType;

    @ManyToOne
    @JsonIgnoreProperties("institucions")
    private Ciudad ciudad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Institucion codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Institucion nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Institucion descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public Institucion direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWebsite() {
        return website;
    }

    public Institucion website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContacto() {
        return contacto;
    }

    public Institucion contacto(String contacto) {
        this.contacto = contacto;
        return this;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getRepresentante() {
        return representante;
    }

    public Institucion representante(String representante) {
        this.representante = representante;
        return this;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getSkype() {
        return skype;
    }

    public Institucion skype(String skype) {
        this.skype = skype;
        return this;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getTelefono() {
        return telefono;
    }

    public Institucion telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstatus() {
        return estatus;
    }

    public Institucion estatus(String estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getTipoProgramas() {
        return tipoProgramas;
    }

    public Institucion tipoProgramas(String tipoProgramas) {
        this.tipoProgramas = tipoProgramas;
        return this;
    }

    public void setTipoProgramas(String tipoProgramas) {
        this.tipoProgramas = tipoProgramas;
    }

    public String getProgramaEstandar() {
        return programaEstandar;
    }

    public Institucion programaEstandar(String programaEstandar) {
        this.programaEstandar = programaEstandar;
        return this;
    }

    public void setProgramaEstandar(String programaEstandar) {
        this.programaEstandar = programaEstandar;
    }

    public String getProgramaIntensivo() {
        return programaIntensivo;
    }

    public Institucion programaIntensivo(String programaIntensivo) {
        this.programaIntensivo = programaIntensivo;
        return this;
    }

    public void setProgramaIntensivo(String programaIntensivo) {
        this.programaIntensivo = programaIntensivo;
    }

    public String getProgramaNegocios() {
        return programaNegocios;
    }

    public Institucion programaNegocios(String programaNegocios) {
        this.programaNegocios = programaNegocios;
        return this;
    }

    public void setProgramaNegocios(String programaNegocios) {
        this.programaNegocios = programaNegocios;
    }

    public String getPreparacionExamen() {
        return preparacionExamen;
    }

    public Institucion preparacionExamen(String preparacionExamen) {
        this.preparacionExamen = preparacionExamen;
        return this;
    }

    public void setPreparacionExamen(String preparacionExamen) {
        this.preparacionExamen = preparacionExamen;
    }

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    public Institucion programaAcademico(String programaAcademico) {
        this.programaAcademico = programaAcademico;
        return this;
    }

    public void setProgramaAcademico(String programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public Institucion descuento(Integer descuento) {
        this.descuento = descuento;
        return this;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public String getInscripcion() {
        return inscripcion;
    }

    public Institucion inscripcion(String inscripcion) {
        this.inscripcion = inscripcion;
        return this;
    }

    public void setInscripcion(String inscripcion) {
        this.inscripcion = inscripcion;
    }

    public String getMateriales() {
        return materiales;
    }

    public Institucion materiales(String materiales) {
        this.materiales = materiales;
        return this;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    public Institucion seguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
        return this;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public String getAlojamientoSencillo() {
        return alojamientoSencillo;
    }

    public Institucion alojamientoSencillo(String alojamientoSencillo) {
        this.alojamientoSencillo = alojamientoSencillo;
        return this;
    }

    public void setAlojamientoSencillo(String alojamientoSencillo) {
        this.alojamientoSencillo = alojamientoSencillo;
    }

    public String getAlojamientoDoble() {
        return alojamientoDoble;
    }

    public Institucion alojamientoDoble(String alojamientoDoble) {
        this.alojamientoDoble = alojamientoDoble;
        return this;
    }

    public void setAlojamientoDoble(String alojamientoDoble) {
        this.alojamientoDoble = alojamientoDoble;
    }

    public String getTransporteAeropuerto() {
        return transporteAeropuerto;
    }

    public Institucion transporteAeropuerto(String transporteAeropuerto) {
        this.transporteAeropuerto = transporteAeropuerto;
        return this;
    }

    public void setTransporteAeropuerto(String transporteAeropuerto) {
        this.transporteAeropuerto = transporteAeropuerto;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public Institucion tipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
        return this;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getTemporadaAlta() {
        return temporadaAlta;
    }

    public Institucion temporadaAlta(String temporadaAlta) {
        this.temporadaAlta = temporadaAlta;
        return this;
    }

    public void setTemporadaAlta(String temporadaAlta) {
        this.temporadaAlta = temporadaAlta;
    }

    public String getTemporadaBaja() {
        return temporadaBaja;
    }

    public Institucion temporadaBaja(String temporadaBaja) {
        this.temporadaBaja = temporadaBaja;
        return this;
    }

    public void setTemporadaBaja(String temporadaBaja) {
        this.temporadaBaja = temporadaBaja;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public Institucion fechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
        return this;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getHorarios() {
        return horarios;
    }

    public Institucion horarios(String horarios) {
        this.horarios = horarios;
        return this;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getInstalaciones() {
        return instalaciones;
    }

    public Institucion instalaciones(String instalaciones) {
        this.instalaciones = instalaciones;
        return this;
    }

    public void setInstalaciones(String instalaciones) {
        this.instalaciones = instalaciones;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Institucion nacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Institucion logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Institucion logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public Institucion foto1(byte[] foto1) {
        this.foto1 = foto1;
        return this;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getFoto1ContentType() {
        return foto1ContentType;
    }

    public Institucion foto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
        return this;
    }

    public void setFoto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public Institucion foto2(byte[] foto2) {
        this.foto2 = foto2;
        return this;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public String getFoto2ContentType() {
        return foto2ContentType;
    }

    public Institucion foto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
        return this;
    }

    public void setFoto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
    }

    public byte[] getFoto3() {
        return foto3;
    }

    public Institucion foto3(byte[] foto3) {
        this.foto3 = foto3;
        return this;
    }

    public void setFoto3(byte[] foto3) {
        this.foto3 = foto3;
    }

    public String getFoto3ContentType() {
        return foto3ContentType;
    }

    public Institucion foto3ContentType(String foto3ContentType) {
        this.foto3ContentType = foto3ContentType;
        return this;
    }

    public void setFoto3ContentType(String foto3ContentType) {
        this.foto3ContentType = foto3ContentType;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Institucion ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Institucion)) {
            return false;
        }
        return id != null && id.equals(((Institucion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Institucion{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", website='" + getWebsite() + "'" +
            ", contacto='" + getContacto() + "'" +
            ", representante='" + getRepresentante() + "'" +
            ", skype='" + getSkype() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", estatus='" + getEstatus() + "'" +
            ", tipoProgramas='" + getTipoProgramas() + "'" +
            ", programaEstandar='" + getProgramaEstandar() + "'" +
            ", programaIntensivo='" + getProgramaIntensivo() + "'" +
            ", programaNegocios='" + getProgramaNegocios() + "'" +
            ", preparacionExamen='" + getPreparacionExamen() + "'" +
            ", programaAcademico='" + getProgramaAcademico() + "'" +
            ", descuento=" + getDescuento() +
            ", inscripcion='" + getInscripcion() + "'" +
            ", materiales='" + getMateriales() + "'" +
            ", seguroMedico='" + getSeguroMedico() + "'" +
            ", alojamientoSencillo='" + getAlojamientoSencillo() + "'" +
            ", alojamientoDoble='" + getAlojamientoDoble() + "'" +
            ", transporteAeropuerto='" + getTransporteAeropuerto() + "'" +
            ", tipoCurso='" + getTipoCurso() + "'" +
            ", temporadaAlta='" + getTemporadaAlta() + "'" +
            ", temporadaBaja='" + getTemporadaBaja() + "'" +
            ", fechaInicial='" + getFechaInicial() + "'" +
            ", horarios='" + getHorarios() + "'" +
            ", instalaciones='" + getInstalaciones() + "'" +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            ", foto1='" + getFoto1() + "'" +
            ", foto1ContentType='" + getFoto1ContentType() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", foto2ContentType='" + getFoto2ContentType() + "'" +
            ", foto3='" + getFoto3() + "'" +
            ", foto3ContentType='" + getFoto3ContentType() + "'" +
            "}";
    }
}
