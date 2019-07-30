package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

import io.github.jhipster.application.domain.enumeration.Nacionalidadd;

import io.github.jhipster.application.domain.enumeration.Destinod;

/**
 * A BlogContenido.
 */
@Entity
@Table(name = "blog_contenido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "blogcontenido")
public class BlogContenido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nacionalidad")
    private Nacionalidadd nacionalidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "pais_estudio")
    private Destinod paisEstudio;

    @Column(name = "calificacion_pais")
    private Integer calificacionPais;

    @Column(name = "ciudad_vive")
    private String ciudadVive;

    @Column(name = "calificacion_ciudad")
    private Integer calificacionCiudad;

    @Column(name = "programa_realizado")
    private String programaRealizado;

    @Column(name = "institucion_estudio")
    private String institucionEstudio;

    @Column(name = "calificacion_institucion")
    private Integer calificacionInstitucion;

    @Column(name = "agencia_estudios")
    private Boolean agenciaEstudios;

    @Column(name = "nombre_agencia")
    private String nombreAgencia;

    @Column(name = "calificacion_agencia")
    private Integer calificacionAgencia;

    @Column(name = "calificacion_experiencia_estudio")
    private Integer calificacionExperienciaEstudio;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "titulo")
    private String titulo;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_content_type")
    private String imagenContentType;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "contenido")
    private String contenido;

    @ManyToOne
    @JsonIgnoreProperties("blogContenidos")
    private User usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public BlogContenido nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public BlogContenido apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public BlogContenido correo(String correo) {
        this.correo = correo;
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Nacionalidadd getNacionalidad() {
        return nacionalidad;
    }

    public BlogContenido nacionalidad(Nacionalidadd nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(Nacionalidadd nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Destinod getPaisEstudio() {
        return paisEstudio;
    }

    public BlogContenido paisEstudio(Destinod paisEstudio) {
        this.paisEstudio = paisEstudio;
        return this;
    }

    public void setPaisEstudio(Destinod paisEstudio) {
        this.paisEstudio = paisEstudio;
    }

    public Integer getCalificacionPais() {
        return calificacionPais;
    }

    public BlogContenido calificacionPais(Integer calificacionPais) {
        this.calificacionPais = calificacionPais;
        return this;
    }

    public void setCalificacionPais(Integer calificacionPais) {
        this.calificacionPais = calificacionPais;
    }

    public String getCiudadVive() {
        return ciudadVive;
    }

    public BlogContenido ciudadVive(String ciudadVive) {
        this.ciudadVive = ciudadVive;
        return this;
    }

    public void setCiudadVive(String ciudadVive) {
        this.ciudadVive = ciudadVive;
    }

    public Integer getCalificacionCiudad() {
        return calificacionCiudad;
    }

    public BlogContenido calificacionCiudad(Integer calificacionCiudad) {
        this.calificacionCiudad = calificacionCiudad;
        return this;
    }

    public void setCalificacionCiudad(Integer calificacionCiudad) {
        this.calificacionCiudad = calificacionCiudad;
    }

    public String getProgramaRealizado() {
        return programaRealizado;
    }

    public BlogContenido programaRealizado(String programaRealizado) {
        this.programaRealizado = programaRealizado;
        return this;
    }

    public void setProgramaRealizado(String programaRealizado) {
        this.programaRealizado = programaRealizado;
    }

    public String getInstitucionEstudio() {
        return institucionEstudio;
    }

    public BlogContenido institucionEstudio(String institucionEstudio) {
        this.institucionEstudio = institucionEstudio;
        return this;
    }

    public void setInstitucionEstudio(String institucionEstudio) {
        this.institucionEstudio = institucionEstudio;
    }

    public Integer getCalificacionInstitucion() {
        return calificacionInstitucion;
    }

    public BlogContenido calificacionInstitucion(Integer calificacionInstitucion) {
        this.calificacionInstitucion = calificacionInstitucion;
        return this;
    }

    public void setCalificacionInstitucion(Integer calificacionInstitucion) {
        this.calificacionInstitucion = calificacionInstitucion;
    }

    public Boolean isAgenciaEstudios() {
        return agenciaEstudios;
    }

    public BlogContenido agenciaEstudios(Boolean agenciaEstudios) {
        this.agenciaEstudios = agenciaEstudios;
        return this;
    }

    public void setAgenciaEstudios(Boolean agenciaEstudios) {
        this.agenciaEstudios = agenciaEstudios;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public BlogContenido nombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
        return this;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public Integer getCalificacionAgencia() {
        return calificacionAgencia;
    }

    public BlogContenido calificacionAgencia(Integer calificacionAgencia) {
        this.calificacionAgencia = calificacionAgencia;
        return this;
    }

    public void setCalificacionAgencia(Integer calificacionAgencia) {
        this.calificacionAgencia = calificacionAgencia;
    }

    public Integer getCalificacionExperienciaEstudio() {
        return calificacionExperienciaEstudio;
    }

    public BlogContenido calificacionExperienciaEstudio(Integer calificacionExperienciaEstudio) {
        this.calificacionExperienciaEstudio = calificacionExperienciaEstudio;
        return this;
    }

    public void setCalificacionExperienciaEstudio(Integer calificacionExperienciaEstudio) {
        this.calificacionExperienciaEstudio = calificacionExperienciaEstudio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public BlogContenido fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public BlogContenido titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public BlogContenido imagen(byte[] imagen) {
        this.imagen = imagen;
        return this;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public BlogContenido imagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
        return this;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public String getContenido() {
        return contenido;
    }

    public BlogContenido contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public User getUsuario() {
        return usuario;
    }

    public BlogContenido usuario(User user) {
        this.usuario = user;
        return this;
    }

    public void setUsuario(User user) {
        this.usuario = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlogContenido)) {
            return false;
        }
        return id != null && id.equals(((BlogContenido) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BlogContenido{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", paisEstudio='" + getPaisEstudio() + "'" +
            ", calificacionPais=" + getCalificacionPais() +
            ", ciudadVive='" + getCiudadVive() + "'" +
            ", calificacionCiudad=" + getCalificacionCiudad() +
            ", programaRealizado='" + getProgramaRealizado() + "'" +
            ", institucionEstudio='" + getInstitucionEstudio() + "'" +
            ", calificacionInstitucion=" + getCalificacionInstitucion() +
            ", agenciaEstudios='" + isAgenciaEstudios() + "'" +
            ", nombreAgencia='" + getNombreAgencia() + "'" +
            ", calificacionAgencia=" + getCalificacionAgencia() +
            ", calificacionExperienciaEstudio=" + getCalificacionExperienciaEstudio() +
            ", fecha='" + getFecha() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", imagenContentType='" + getImagenContentType() + "'" +
            ", contenido='" + getContenido() + "'" +
            "}";
    }
}
