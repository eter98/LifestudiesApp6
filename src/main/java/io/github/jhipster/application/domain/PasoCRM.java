package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Estadod;

/**
 * A PasoCRM.
 */
@Entity
@Table(name = "paso_crm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "pasocrm")
public class PasoCRM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "duracion_dias")
    private Integer duracionDias;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estadod estado;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public PasoCRM codigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public PasoCRM descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public PasoCRM duracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
        return this;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

    public Estadod getEstado() {
        return estado;
    }

    public PasoCRM estado(Estadod estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estadod estado) {
        this.estado = estado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PasoCRM)) {
            return false;
        }
        return id != null && id.equals(((PasoCRM) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PasoCRM{" +
            "id=" + getId() +
            ", codigo=" + getCodigo() +
            ", descripcion='" + getDescripcion() + "'" +
            ", duracionDias=" + getDuracionDias() +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
