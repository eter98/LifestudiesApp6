package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TipoPrograma.
 */
@Entity
@Table(name = "tipo_programa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tipoprograma")
public class TipoPrograma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "tipo_programa")
    private String tipoPrograma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPrograma() {
        return tipoPrograma;
    }

    public TipoPrograma tipoPrograma(String tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
        return this;
    }

    public void setTipoPrograma(String tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoPrograma)) {
            return false;
        }
        return id != null && id.equals(((TipoPrograma) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipoPrograma{" +
            "id=" + getId() +
            ", tipoPrograma='" + getTipoPrograma() + "'" +
            "}";
    }
}
