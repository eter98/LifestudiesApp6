package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.ZonedDateTime;

import io.github.jhipster.application.domain.enumeration.Asesord;

import io.github.jhipster.application.domain.enumeration.Estadod;

import io.github.jhipster.application.domain.enumeration.TipoLeadd;

/**
 * A CRM.
 */
@Entity
@Table(name = "crm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "crm")
public class CRM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "fecha")
    private ZonedDateTime fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "documento")
    private byte[] documento;

    @Column(name = "documento_content_type")
    private String documentoContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "asesor")
    private Asesord asesor;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estadod estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_lead")
    private TipoLeadd tipoLead;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "cerrado")
    private Boolean cerrado;

    @ManyToOne
    @JsonIgnoreProperties("cRMS")
    private PerfilUsuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("cRMS")
    private PasoCRM proceso;

    @ManyToOne
    @JsonIgnoreProperties("cRMS")
    private Agencia agencia;

    @ManyToOne
    @JsonIgnoreProperties("cRMS")
    private Cotizacion cotizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public CRM fecha(ZonedDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CRM descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public CRM documento(byte[] documento) {
        this.documento = documento;
        return this;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public String getDocumentoContentType() {
        return documentoContentType;
    }

    public CRM documentoContentType(String documentoContentType) {
        this.documentoContentType = documentoContentType;
        return this;
    }

    public void setDocumentoContentType(String documentoContentType) {
        this.documentoContentType = documentoContentType;
    }

    public Asesord getAsesor() {
        return asesor;
    }

    public CRM asesor(Asesord asesor) {
        this.asesor = asesor;
        return this;
    }

    public void setAsesor(Asesord asesor) {
        this.asesor = asesor;
    }

    public Estadod getEstado() {
        return estado;
    }

    public CRM estado(Estadod estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estadod estado) {
        this.estado = estado;
    }

    public TipoLeadd getTipoLead() {
        return tipoLead;
    }

    public CRM tipoLead(TipoLeadd tipoLead) {
        this.tipoLead = tipoLead;
        return this;
    }

    public void setTipoLead(TipoLeadd tipoLead) {
        this.tipoLead = tipoLead;
    }

    public String getComentarios() {
        return comentarios;
    }

    public CRM comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Boolean isCerrado() {
        return cerrado;
    }

    public CRM cerrado(Boolean cerrado) {
        this.cerrado = cerrado;
        return this;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public PerfilUsuario getUsuario() {
        return usuario;
    }

    public CRM usuario(PerfilUsuario perfilUsuario) {
        this.usuario = perfilUsuario;
        return this;
    }

    public void setUsuario(PerfilUsuario perfilUsuario) {
        this.usuario = perfilUsuario;
    }

    public PasoCRM getProceso() {
        return proceso;
    }

    public CRM proceso(PasoCRM pasoCRM) {
        this.proceso = pasoCRM;
        return this;
    }

    public void setProceso(PasoCRM pasoCRM) {
        this.proceso = pasoCRM;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public CRM agencia(Agencia agencia) {
        this.agencia = agencia;
        return this;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public CRM cotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
        return this;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CRM)) {
            return false;
        }
        return id != null && id.equals(((CRM) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CRM{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", documentoContentType='" + getDocumentoContentType() + "'" +
            ", asesor='" + getAsesor() + "'" +
            ", estado='" + getEstado() + "'" +
            ", tipoLead='" + getTipoLead() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            ", cerrado='" + isCerrado() + "'" +
            "}";
    }
}
