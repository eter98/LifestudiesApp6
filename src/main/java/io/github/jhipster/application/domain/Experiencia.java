package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Experiencia.
 */
@Entity
@Table(name = "experiencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "experiencia")
public class Experiencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "identificacion")
    private Integer identificacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "mail")
    private String mail;

    @Column(name = "area")
    private Integer area;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "pais_destino")
    private String paisDestino;

    @Column(name = "califica_pais")
    private Integer calificaPais;

    @Column(name = "programa")
    private String programa;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "califica_institucion")
    private Integer calificaInstitucion;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "califica_agencia")
    private Integer calificaAgencia;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "contenido")
    private String contenido;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public Experiencia identificacion(Integer identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Experiencia nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Experiencia apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public Experiencia mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getArea() {
        return area;
    }

    public Experiencia area(Integer area) {
        this.area = area;
        return this;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public Experiencia telefono(Integer telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Experiencia nacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public Experiencia paisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
        return this;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public Integer getCalificaPais() {
        return calificaPais;
    }

    public Experiencia calificaPais(Integer calificaPais) {
        this.calificaPais = calificaPais;
        return this;
    }

    public void setCalificaPais(Integer calificaPais) {
        this.calificaPais = calificaPais;
    }

    public String getPrograma() {
        return programa;
    }

    public Experiencia programa(String programa) {
        this.programa = programa;
        return this;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getInstitucion() {
        return institucion;
    }

    public Experiencia institucion(String institucion) {
        this.institucion = institucion;
        return this;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Integer getCalificaInstitucion() {
        return calificaInstitucion;
    }

    public Experiencia calificaInstitucion(Integer calificaInstitucion) {
        this.calificaInstitucion = calificaInstitucion;
        return this;
    }

    public void setCalificaInstitucion(Integer calificaInstitucion) {
        this.calificaInstitucion = calificaInstitucion;
    }

    public String getAgencia() {
        return agencia;
    }

    public Experiencia agencia(String agencia) {
        this.agencia = agencia;
        return this;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Integer getCalificaAgencia() {
        return calificaAgencia;
    }

    public Experiencia calificaAgencia(Integer calificaAgencia) {
        this.calificaAgencia = calificaAgencia;
        return this;
    }

    public void setCalificaAgencia(Integer calificaAgencia) {
        this.calificaAgencia = calificaAgencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Experiencia fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public Experiencia contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Experiencia foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public Experiencia fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Experiencia)) {
            return false;
        }
        return id != null && id.equals(((Experiencia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Experiencia{" +
            "id=" + getId() +
            ", identificacion=" + getIdentificacion() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", mail='" + getMail() + "'" +
            ", area=" + getArea() +
            ", telefono=" + getTelefono() +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", paisDestino='" + getPaisDestino() + "'" +
            ", calificaPais=" + getCalificaPais() +
            ", programa='" + getPrograma() + "'" +
            ", institucion='" + getInstitucion() + "'" +
            ", calificaInstitucion=" + getCalificaInstitucion() +
            ", agencia='" + getAgencia() + "'" +
            ", calificaAgencia=" + getCalificaAgencia() +
            ", fecha='" + getFecha() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            "}";
    }
}
