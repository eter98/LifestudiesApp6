package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Agencia.
 */
@Entity
@Table(name = "agencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "agencia")
public class Agencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "nombre_agencia")
    private String nombreAgencia;

    @Column(name = "codigo")
    private String codigo;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "encargado")
    private String encargado;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "whatsapp")
    private String whatsapp;

    @Column(name = "asesor_1")
    private String asesor1;

    @Lob
    @Column(name = "foto_asesor_1")
    private byte[] fotoAsesor1;

    @Column(name = "foto_asesor_1_content_type")
    private String fotoAsesor1ContentType;

    @Column(name = "asesor_2")
    private String asesor2;

    @Lob
    @Column(name = "foto_asesor_2")
    private byte[] fotoAsesor2;

    @Column(name = "foto_asesor_2_content_type")
    private String fotoAsesor2ContentType;

    @Column(name = "asesor_3")
    private String asesor3;

    @Lob
    @Column(name = "foto_asesor_3")
    private byte[] fotoAsesor3;

    @Column(name = "foto_asesor_3_content_type")
    private String fotoAsesor3ContentType;

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

    @Column(name = "sede_1")
    private String sede1;

    @Column(name = "sede_2")
    private String sede2;

    @ManyToOne
    @JsonIgnoreProperties("agencias")
    private Pais pais;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public Agencia nombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
        return this;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public Agencia codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Agencia descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEncargado() {
        return encargado;
    }

    public Agencia encargado(String encargado) {
        this.encargado = encargado;
        return this;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDireccion() {
        return direccion;
    }

    public Agencia direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Agencia telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public Agencia email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public Agencia whatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
        return this;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getAsesor1() {
        return asesor1;
    }

    public Agencia asesor1(String asesor1) {
        this.asesor1 = asesor1;
        return this;
    }

    public void setAsesor1(String asesor1) {
        this.asesor1 = asesor1;
    }

    public byte[] getFotoAsesor1() {
        return fotoAsesor1;
    }

    public Agencia fotoAsesor1(byte[] fotoAsesor1) {
        this.fotoAsesor1 = fotoAsesor1;
        return this;
    }

    public void setFotoAsesor1(byte[] fotoAsesor1) {
        this.fotoAsesor1 = fotoAsesor1;
    }

    public String getFotoAsesor1ContentType() {
        return fotoAsesor1ContentType;
    }

    public Agencia fotoAsesor1ContentType(String fotoAsesor1ContentType) {
        this.fotoAsesor1ContentType = fotoAsesor1ContentType;
        return this;
    }

    public void setFotoAsesor1ContentType(String fotoAsesor1ContentType) {
        this.fotoAsesor1ContentType = fotoAsesor1ContentType;
    }

    public String getAsesor2() {
        return asesor2;
    }

    public Agencia asesor2(String asesor2) {
        this.asesor2 = asesor2;
        return this;
    }

    public void setAsesor2(String asesor2) {
        this.asesor2 = asesor2;
    }

    public byte[] getFotoAsesor2() {
        return fotoAsesor2;
    }

    public Agencia fotoAsesor2(byte[] fotoAsesor2) {
        this.fotoAsesor2 = fotoAsesor2;
        return this;
    }

    public void setFotoAsesor2(byte[] fotoAsesor2) {
        this.fotoAsesor2 = fotoAsesor2;
    }

    public String getFotoAsesor2ContentType() {
        return fotoAsesor2ContentType;
    }

    public Agencia fotoAsesor2ContentType(String fotoAsesor2ContentType) {
        this.fotoAsesor2ContentType = fotoAsesor2ContentType;
        return this;
    }

    public void setFotoAsesor2ContentType(String fotoAsesor2ContentType) {
        this.fotoAsesor2ContentType = fotoAsesor2ContentType;
    }

    public String getAsesor3() {
        return asesor3;
    }

    public Agencia asesor3(String asesor3) {
        this.asesor3 = asesor3;
        return this;
    }

    public void setAsesor3(String asesor3) {
        this.asesor3 = asesor3;
    }

    public byte[] getFotoAsesor3() {
        return fotoAsesor3;
    }

    public Agencia fotoAsesor3(byte[] fotoAsesor3) {
        this.fotoAsesor3 = fotoAsesor3;
        return this;
    }

    public void setFotoAsesor3(byte[] fotoAsesor3) {
        this.fotoAsesor3 = fotoAsesor3;
    }

    public String getFotoAsesor3ContentType() {
        return fotoAsesor3ContentType;
    }

    public Agencia fotoAsesor3ContentType(String fotoAsesor3ContentType) {
        this.fotoAsesor3ContentType = fotoAsesor3ContentType;
        return this;
    }

    public void setFotoAsesor3ContentType(String fotoAsesor3ContentType) {
        this.fotoAsesor3ContentType = fotoAsesor3ContentType;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Agencia logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Agencia logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public Agencia foto1(byte[] foto1) {
        this.foto1 = foto1;
        return this;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getFoto1ContentType() {
        return foto1ContentType;
    }

    public Agencia foto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
        return this;
    }

    public void setFoto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public Agencia foto2(byte[] foto2) {
        this.foto2 = foto2;
        return this;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public String getFoto2ContentType() {
        return foto2ContentType;
    }

    public Agencia foto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
        return this;
    }

    public void setFoto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
    }

    public String getSede1() {
        return sede1;
    }

    public Agencia sede1(String sede1) {
        this.sede1 = sede1;
        return this;
    }

    public void setSede1(String sede1) {
        this.sede1 = sede1;
    }

    public String getSede2() {
        return sede2;
    }

    public Agencia sede2(String sede2) {
        this.sede2 = sede2;
        return this;
    }

    public void setSede2(String sede2) {
        this.sede2 = sede2;
    }

    public Pais getPais() {
        return pais;
    }

    public Agencia pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agencia)) {
            return false;
        }
        return id != null && id.equals(((Agencia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Agencia{" +
            "id=" + getId() +
            ", nombreAgencia='" + getNombreAgencia() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", encargado='" + getEncargado() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", email='" + getEmail() + "'" +
            ", whatsapp='" + getWhatsapp() + "'" +
            ", asesor1='" + getAsesor1() + "'" +
            ", fotoAsesor1='" + getFotoAsesor1() + "'" +
            ", fotoAsesor1ContentType='" + getFotoAsesor1ContentType() + "'" +
            ", asesor2='" + getAsesor2() + "'" +
            ", fotoAsesor2='" + getFotoAsesor2() + "'" +
            ", fotoAsesor2ContentType='" + getFotoAsesor2ContentType() + "'" +
            ", asesor3='" + getAsesor3() + "'" +
            ", fotoAsesor3='" + getFotoAsesor3() + "'" +
            ", fotoAsesor3ContentType='" + getFotoAsesor3ContentType() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            ", foto1='" + getFoto1() + "'" +
            ", foto1ContentType='" + getFoto1ContentType() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", foto2ContentType='" + getFoto2ContentType() + "'" +
            ", sede1='" + getSede1() + "'" +
            ", sede2='" + getSede2() + "'" +
            "}";
    }
}
