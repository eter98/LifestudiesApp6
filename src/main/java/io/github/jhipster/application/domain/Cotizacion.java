package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cotizacion.
 */
@Entity
@Table(name = "cotizacion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "cotizacion")
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "fecha_viaje")
    private LocalDate fechaViaje;

    @Column(name = "alojamiento")
    private Boolean alojamiento;

    @Column(name = "pasaje_aereo")
    private Boolean pasajeAereo;

    @Column(name = "transp_aeropuerto")
    private Boolean transpAeropuerto;

    @ManyToOne
    @JsonIgnoreProperties("cotizacions")
    private PerfilUsuario usario;

    @ManyToOne
    @JsonIgnoreProperties("cotizacions")
    private Programas curso;

    @OneToMany(mappedBy = "cotizacion")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CRM> cRMS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public Cotizacion fechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
        return this;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Boolean isAlojamiento() {
        return alojamiento;
    }

    public Cotizacion alojamiento(Boolean alojamiento) {
        this.alojamiento = alojamiento;
        return this;
    }

    public void setAlojamiento(Boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Boolean isPasajeAereo() {
        return pasajeAereo;
    }

    public Cotizacion pasajeAereo(Boolean pasajeAereo) {
        this.pasajeAereo = pasajeAereo;
        return this;
    }

    public void setPasajeAereo(Boolean pasajeAereo) {
        this.pasajeAereo = pasajeAereo;
    }

    public Boolean isTranspAeropuerto() {
        return transpAeropuerto;
    }

    public Cotizacion transpAeropuerto(Boolean transpAeropuerto) {
        this.transpAeropuerto = transpAeropuerto;
        return this;
    }

    public void setTranspAeropuerto(Boolean transpAeropuerto) {
        this.transpAeropuerto = transpAeropuerto;
    }

    public PerfilUsuario getUsario() {
        return usario;
    }

    public Cotizacion usario(PerfilUsuario perfilUsuario) {
        this.usario = perfilUsuario;
        return this;
    }

    public void setUsario(PerfilUsuario perfilUsuario) {
        this.usario = perfilUsuario;
    }

    public Programas getCurso() {
        return curso;
    }

    public Cotizacion curso(Programas programas) {
        this.curso = programas;
        return this;
    }

    public void setCurso(Programas programas) {
        this.curso = programas;
    }

    public Set<CRM> getCRMS() {
        return cRMS;
    }

    public Cotizacion cRMS(Set<CRM> cRMS) {
        this.cRMS = cRMS;
        return this;
    }

    public Cotizacion addCRM(CRM cRM) {
        this.cRMS.add(cRM);
        cRM.setCotizacion(this);
        return this;
    }

    public Cotizacion removeCRM(CRM cRM) {
        this.cRMS.remove(cRM);
        cRM.setCotizacion(null);
        return this;
    }

    public void setCRMS(Set<CRM> cRMS) {
        this.cRMS = cRMS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cotizacion)) {
            return false;
        }
        return id != null && id.equals(((Cotizacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
            "id=" + getId() +
            ", fechaViaje='" + getFechaViaje() + "'" +
            ", alojamiento='" + isAlojamiento() + "'" +
            ", pasajeAereo='" + isPasajeAereo() + "'" +
            ", transpAeropuerto='" + isTranspAeropuerto() + "'" +
            "}";
    }
}
