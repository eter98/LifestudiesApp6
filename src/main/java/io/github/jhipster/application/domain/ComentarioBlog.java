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
 * A ComentarioBlog.
 */
@Entity
@Table(name = "comentario_blog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "comentarioblog")
public class ComentarioBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "calificacion")
    private Integer calificacion;

    @ManyToOne
    @JsonIgnoreProperties("comentarioBlogs")
    private User usuario;

    @ManyToOne
    @JsonIgnoreProperties("comentarioBlogs")
    private BlogContenido tituloBlog;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public ComentarioBlog comentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ComentarioBlog fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public ComentarioBlog calificacion(Integer calificacion) {
        this.calificacion = calificacion;
        return this;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public User getUsuario() {
        return usuario;
    }

    public ComentarioBlog usuario(User user) {
        this.usuario = user;
        return this;
    }

    public void setUsuario(User user) {
        this.usuario = user;
    }

    public BlogContenido getTituloBlog() {
        return tituloBlog;
    }

    public ComentarioBlog tituloBlog(BlogContenido blogContenido) {
        this.tituloBlog = blogContenido;
        return this;
    }

    public void setTituloBlog(BlogContenido blogContenido) {
        this.tituloBlog = blogContenido;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComentarioBlog)) {
            return false;
        }
        return id != null && id.equals(((ComentarioBlog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ComentarioBlog{" +
            "id=" + getId() +
            ", comentario='" + getComentario() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", calificacion=" + getCalificacion() +
            "}";
    }
}
