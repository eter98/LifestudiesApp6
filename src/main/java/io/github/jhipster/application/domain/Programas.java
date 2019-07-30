package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Monedad;

/**
 * A Programas.
 */
@Entity
@Table(name = "programas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "programas")
public class Programas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "registro")
    private Integer registro;

    @Enumerated(EnumType.STRING)
    @Column(name = "moneda")
    private Monedad moneda;

    @Column(name = "curso")
    private String curso;

    @Column(name = "horario")
    private String horario;

    @Column(name = "frecuencia")
    private Integer frecuencia;

    @Column(name = "duracion")
    private Integer duracion;

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

    @ManyToOne
    @JsonIgnoreProperties("programas")
    private Institucion institucion;

    @ManyToOne
    @JsonIgnoreProperties("programas")
    private Ciudad ciudad;

    @ManyToOne
    @JsonIgnoreProperties("programas")
    private TipoPrograma tipoPrograma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegistro() {
        return registro;
    }

    public Programas registro(Integer registro) {
        this.registro = registro;
        return this;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public Monedad getMoneda() {
        return moneda;
    }

    public Programas moneda(Monedad moneda) {
        this.moneda = moneda;
        return this;
    }

    public void setMoneda(Monedad moneda) {
        this.moneda = moneda;
    }

    public String getCurso() {
        return curso;
    }

    public Programas curso(String curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getHorario() {
        return horario;
    }

    public Programas horario(String horario) {
        this.horario = horario;
        return this;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public Programas frecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
        return this;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public Programas duracion(Integer duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public Programas foto1(byte[] foto1) {
        this.foto1 = foto1;
        return this;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getFoto1ContentType() {
        return foto1ContentType;
    }

    public Programas foto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
        return this;
    }

    public void setFoto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public Programas foto2(byte[] foto2) {
        this.foto2 = foto2;
        return this;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public String getFoto2ContentType() {
        return foto2ContentType;
    }

    public Programas foto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
        return this;
    }

    public void setFoto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public Programas institucion(Institucion institucion) {
        this.institucion = institucion;
        return this;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Programas ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public TipoPrograma getTipoPrograma() {
        return tipoPrograma;
    }

    public Programas tipoPrograma(TipoPrograma tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
        return this;
    }

    public void setTipoPrograma(TipoPrograma tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Programas)) {
            return false;
        }
        return id != null && id.equals(((Programas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Programas{" +
            "id=" + getId() +
            ", registro=" + getRegistro() +
            ", moneda='" + getMoneda() + "'" +
            ", curso='" + getCurso() + "'" +
            ", horario='" + getHorario() + "'" +
            ", frecuencia=" + getFrecuencia() +
            ", duracion=" + getDuracion() +
            ", foto1='" + getFoto1() + "'" +
            ", foto1ContentType='" + getFoto1ContentType() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", foto2ContentType='" + getFoto2ContentType() + "'" +
            "}";
    }
}
