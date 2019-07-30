package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A PerfilUsuario.
 */
@Entity
@Table(name = "perfil_usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "perfilusuario")
public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "identificacion")
    private Integer identificacion;

    @Column(name = "mail")
    private String mail;

    @Column(name = "area")
    private Integer area;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "area_academica")
    private String areaAcademica;

    @Column(name = "termino_academico")
    private Integer terminoAcademico;

    @Column(name = "puntaje_icfes")
    private Integer puntajeICFES;

    @Column(name = "promedio_academico")
    private Integer promedioAcademico;

    @Column(name = "dominio_idioma")
    private Boolean dominioIdioma;

    @Column(name = "idiomas")
    private String idiomas;

    @Column(name = "examen_idioma")
    private Boolean examenIdioma;

    @Column(name = "examen_realizado")
    private String examenRealizado;

    @Column(name = "puntaje_idioma")
    private Integer puntajeIdioma;

    @Column(name = "beca_otorgada")
    private Boolean becaOtorgada;

    @Column(name = "tipo_beca")
    private Integer tipoBeca;

    @Column(name = "beca_institucion")
    private String becaInstitucion;

    @Column(name = "grupo_social")
    private Boolean grupoSocial;

    @Column(name = "fundacion")
    private String fundacion;

    @Column(name = "monitor")
    private Boolean monitor;

    @Column(name = "monitor_materia")
    private String monitorMateria;

    @Column(name = "logros_academicos")
    private String logrosAcademicos;

    @Column(name = "experiencia_laboral")
    private Boolean experienciaLaboral;

    @Column(name = "area_laboral")
    private String areaLaboral;

    @Column(name = "programarealizar")
    private Integer programarealizar;

    @Column(name = "programa_area")
    private String programaArea;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "programa_encontrado")
    private Boolean programaEncontrado;

    @Column(name = "nombre_programa")
    private String nombrePrograma;

    @Column(name = "universidad")
    private String universidad;

    @Column(name = "pais")
    private Integer pais;

    @Column(name = "merecedor_beca")
    private String merecedorBeca;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @OneToOne
    @JoinColumn(unique = true)
    private User usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public PerfilUsuario fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public PerfilUsuario identificacion(Integer identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getMail() {
        return mail;
    }

    public PerfilUsuario mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getArea() {
        return area;
    }

    public PerfilUsuario area(Integer area) {
        this.area = area;
        return this;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public PerfilUsuario telefono(Integer telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public PerfilUsuario nivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
        return this;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public PerfilUsuario areaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
        return this;
    }

    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public Integer getTerminoAcademico() {
        return terminoAcademico;
    }

    public PerfilUsuario terminoAcademico(Integer terminoAcademico) {
        this.terminoAcademico = terminoAcademico;
        return this;
    }

    public void setTerminoAcademico(Integer terminoAcademico) {
        this.terminoAcademico = terminoAcademico;
    }

    public Integer getPuntajeICFES() {
        return puntajeICFES;
    }

    public PerfilUsuario puntajeICFES(Integer puntajeICFES) {
        this.puntajeICFES = puntajeICFES;
        return this;
    }

    public void setPuntajeICFES(Integer puntajeICFES) {
        this.puntajeICFES = puntajeICFES;
    }

    public Integer getPromedioAcademico() {
        return promedioAcademico;
    }

    public PerfilUsuario promedioAcademico(Integer promedioAcademico) {
        this.promedioAcademico = promedioAcademico;
        return this;
    }

    public void setPromedioAcademico(Integer promedioAcademico) {
        this.promedioAcademico = promedioAcademico;
    }

    public Boolean isDominioIdioma() {
        return dominioIdioma;
    }

    public PerfilUsuario dominioIdioma(Boolean dominioIdioma) {
        this.dominioIdioma = dominioIdioma;
        return this;
    }

    public void setDominioIdioma(Boolean dominioIdioma) {
        this.dominioIdioma = dominioIdioma;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public PerfilUsuario idiomas(String idiomas) {
        this.idiomas = idiomas;
        return this;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Boolean isExamenIdioma() {
        return examenIdioma;
    }

    public PerfilUsuario examenIdioma(Boolean examenIdioma) {
        this.examenIdioma = examenIdioma;
        return this;
    }

    public void setExamenIdioma(Boolean examenIdioma) {
        this.examenIdioma = examenIdioma;
    }

    public String getExamenRealizado() {
        return examenRealizado;
    }

    public PerfilUsuario examenRealizado(String examenRealizado) {
        this.examenRealizado = examenRealizado;
        return this;
    }

    public void setExamenRealizado(String examenRealizado) {
        this.examenRealizado = examenRealizado;
    }

    public Integer getPuntajeIdioma() {
        return puntajeIdioma;
    }

    public PerfilUsuario puntajeIdioma(Integer puntajeIdioma) {
        this.puntajeIdioma = puntajeIdioma;
        return this;
    }

    public void setPuntajeIdioma(Integer puntajeIdioma) {
        this.puntajeIdioma = puntajeIdioma;
    }

    public Boolean isBecaOtorgada() {
        return becaOtorgada;
    }

    public PerfilUsuario becaOtorgada(Boolean becaOtorgada) {
        this.becaOtorgada = becaOtorgada;
        return this;
    }

    public void setBecaOtorgada(Boolean becaOtorgada) {
        this.becaOtorgada = becaOtorgada;
    }

    public Integer getTipoBeca() {
        return tipoBeca;
    }

    public PerfilUsuario tipoBeca(Integer tipoBeca) {
        this.tipoBeca = tipoBeca;
        return this;
    }

    public void setTipoBeca(Integer tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public String getBecaInstitucion() {
        return becaInstitucion;
    }

    public PerfilUsuario becaInstitucion(String becaInstitucion) {
        this.becaInstitucion = becaInstitucion;
        return this;
    }

    public void setBecaInstitucion(String becaInstitucion) {
        this.becaInstitucion = becaInstitucion;
    }

    public Boolean isGrupoSocial() {
        return grupoSocial;
    }

    public PerfilUsuario grupoSocial(Boolean grupoSocial) {
        this.grupoSocial = grupoSocial;
        return this;
    }

    public void setGrupoSocial(Boolean grupoSocial) {
        this.grupoSocial = grupoSocial;
    }

    public String getFundacion() {
        return fundacion;
    }

    public PerfilUsuario fundacion(String fundacion) {
        this.fundacion = fundacion;
        return this;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    public Boolean isMonitor() {
        return monitor;
    }

    public PerfilUsuario monitor(Boolean monitor) {
        this.monitor = monitor;
        return this;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }

    public String getMonitorMateria() {
        return monitorMateria;
    }

    public PerfilUsuario monitorMateria(String monitorMateria) {
        this.monitorMateria = monitorMateria;
        return this;
    }

    public void setMonitorMateria(String monitorMateria) {
        this.monitorMateria = monitorMateria;
    }

    public String getLogrosAcademicos() {
        return logrosAcademicos;
    }

    public PerfilUsuario logrosAcademicos(String logrosAcademicos) {
        this.logrosAcademicos = logrosAcademicos;
        return this;
    }

    public void setLogrosAcademicos(String logrosAcademicos) {
        this.logrosAcademicos = logrosAcademicos;
    }

    public Boolean isExperienciaLaboral() {
        return experienciaLaboral;
    }

    public PerfilUsuario experienciaLaboral(Boolean experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
        return this;
    }

    public void setExperienciaLaboral(Boolean experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getAreaLaboral() {
        return areaLaboral;
    }

    public PerfilUsuario areaLaboral(String areaLaboral) {
        this.areaLaboral = areaLaboral;
        return this;
    }

    public void setAreaLaboral(String areaLaboral) {
        this.areaLaboral = areaLaboral;
    }

    public Integer getProgramarealizar() {
        return programarealizar;
    }

    public PerfilUsuario programarealizar(Integer programarealizar) {
        this.programarealizar = programarealizar;
        return this;
    }

    public void setProgramarealizar(Integer programarealizar) {
        this.programarealizar = programarealizar;
    }

    public String getProgramaArea() {
        return programaArea;
    }

    public PerfilUsuario programaArea(String programaArea) {
        this.programaArea = programaArea;
        return this;
    }

    public void setProgramaArea(String programaArea) {
        this.programaArea = programaArea;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public PerfilUsuario fechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Boolean isProgramaEncontrado() {
        return programaEncontrado;
    }

    public PerfilUsuario programaEncontrado(Boolean programaEncontrado) {
        this.programaEncontrado = programaEncontrado;
        return this;
    }

    public void setProgramaEncontrado(Boolean programaEncontrado) {
        this.programaEncontrado = programaEncontrado;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public PerfilUsuario nombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
        return this;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getUniversidad() {
        return universidad;
    }

    public PerfilUsuario universidad(String universidad) {
        this.universidad = universidad;
        return this;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Integer getPais() {
        return pais;
    }

    public PerfilUsuario pais(Integer pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }

    public String getMerecedorBeca() {
        return merecedorBeca;
    }

    public PerfilUsuario merecedorBeca(String merecedorBeca) {
        this.merecedorBeca = merecedorBeca;
        return this;
    }

    public void setMerecedorBeca(String merecedorBeca) {
        this.merecedorBeca = merecedorBeca;
    }

    public byte[] getFoto() {
        return foto;
    }

    public PerfilUsuario foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public PerfilUsuario fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public User getUsuario() {
        return usuario;
    }

    public PerfilUsuario usuario(User user) {
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
        if (!(o instanceof PerfilUsuario)) {
            return false;
        }
        return id != null && id.equals(((PerfilUsuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PerfilUsuario{" +
            "id=" + getId() +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", identificacion=" + getIdentificacion() +
            ", mail='" + getMail() + "'" +
            ", area=" + getArea() +
            ", telefono=" + getTelefono() +
            ", nivelAcademico='" + getNivelAcademico() + "'" +
            ", areaAcademica='" + getAreaAcademica() + "'" +
            ", terminoAcademico=" + getTerminoAcademico() +
            ", puntajeICFES=" + getPuntajeICFES() +
            ", promedioAcademico=" + getPromedioAcademico() +
            ", dominioIdioma='" + isDominioIdioma() + "'" +
            ", idiomas='" + getIdiomas() + "'" +
            ", examenIdioma='" + isExamenIdioma() + "'" +
            ", examenRealizado='" + getExamenRealizado() + "'" +
            ", puntajeIdioma=" + getPuntajeIdioma() +
            ", becaOtorgada='" + isBecaOtorgada() + "'" +
            ", tipoBeca=" + getTipoBeca() +
            ", becaInstitucion='" + getBecaInstitucion() + "'" +
            ", grupoSocial='" + isGrupoSocial() + "'" +
            ", fundacion='" + getFundacion() + "'" +
            ", monitor='" + isMonitor() + "'" +
            ", monitorMateria='" + getMonitorMateria() + "'" +
            ", logrosAcademicos='" + getLogrosAcademicos() + "'" +
            ", experienciaLaboral='" + isExperienciaLaboral() + "'" +
            ", areaLaboral='" + getAreaLaboral() + "'" +
            ", programarealizar=" + getProgramarealizar() +
            ", programaArea='" + getProgramaArea() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", programaEncontrado='" + isProgramaEncontrado() + "'" +
            ", nombrePrograma='" + getNombrePrograma() + "'" +
            ", universidad='" + getUniversidad() + "'" +
            ", pais=" + getPais() +
            ", merecedorBeca='" + getMerecedorBeca() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            "}";
    }
}
