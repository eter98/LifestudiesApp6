package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.PerfilUsuario} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PerfilUsuarioResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /perfil-usuarios?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PerfilUsuarioCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fechaNacimiento;

    private IntegerFilter identificacion;

    private StringFilter mail;

    private IntegerFilter area;

    private IntegerFilter telefono;

    private StringFilter nivelAcademico;

    private StringFilter areaAcademica;

    private IntegerFilter terminoAcademico;

    private IntegerFilter puntajeICFES;

    private IntegerFilter promedioAcademico;

    private BooleanFilter dominioIdioma;

    private StringFilter idiomas;

    private BooleanFilter examenIdioma;

    private StringFilter examenRealizado;

    private IntegerFilter puntajeIdioma;

    private BooleanFilter becaOtorgada;

    private IntegerFilter tipoBeca;

    private StringFilter becaInstitucion;

    private BooleanFilter grupoSocial;

    private StringFilter fundacion;

    private BooleanFilter monitor;

    private StringFilter monitorMateria;

    private StringFilter logrosAcademicos;

    private BooleanFilter experienciaLaboral;

    private StringFilter areaLaboral;

    private IntegerFilter programarealizar;

    private StringFilter programaArea;

    private LocalDateFilter fechaInicio;

    private BooleanFilter programaEncontrado;

    private StringFilter nombrePrograma;

    private StringFilter universidad;

    private IntegerFilter pais;

    private StringFilter merecedorBeca;

    private LongFilter usuarioId;

    public PerfilUsuarioCriteria(){
    }

    public PerfilUsuarioCriteria(PerfilUsuarioCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fechaNacimiento = other.fechaNacimiento == null ? null : other.fechaNacimiento.copy();
        this.identificacion = other.identificacion == null ? null : other.identificacion.copy();
        this.mail = other.mail == null ? null : other.mail.copy();
        this.area = other.area == null ? null : other.area.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.nivelAcademico = other.nivelAcademico == null ? null : other.nivelAcademico.copy();
        this.areaAcademica = other.areaAcademica == null ? null : other.areaAcademica.copy();
        this.terminoAcademico = other.terminoAcademico == null ? null : other.terminoAcademico.copy();
        this.puntajeICFES = other.puntajeICFES == null ? null : other.puntajeICFES.copy();
        this.promedioAcademico = other.promedioAcademico == null ? null : other.promedioAcademico.copy();
        this.dominioIdioma = other.dominioIdioma == null ? null : other.dominioIdioma.copy();
        this.idiomas = other.idiomas == null ? null : other.idiomas.copy();
        this.examenIdioma = other.examenIdioma == null ? null : other.examenIdioma.copy();
        this.examenRealizado = other.examenRealizado == null ? null : other.examenRealizado.copy();
        this.puntajeIdioma = other.puntajeIdioma == null ? null : other.puntajeIdioma.copy();
        this.becaOtorgada = other.becaOtorgada == null ? null : other.becaOtorgada.copy();
        this.tipoBeca = other.tipoBeca == null ? null : other.tipoBeca.copy();
        this.becaInstitucion = other.becaInstitucion == null ? null : other.becaInstitucion.copy();
        this.grupoSocial = other.grupoSocial == null ? null : other.grupoSocial.copy();
        this.fundacion = other.fundacion == null ? null : other.fundacion.copy();
        this.monitor = other.monitor == null ? null : other.monitor.copy();
        this.monitorMateria = other.monitorMateria == null ? null : other.monitorMateria.copy();
        this.logrosAcademicos = other.logrosAcademicos == null ? null : other.logrosAcademicos.copy();
        this.experienciaLaboral = other.experienciaLaboral == null ? null : other.experienciaLaboral.copy();
        this.areaLaboral = other.areaLaboral == null ? null : other.areaLaboral.copy();
        this.programarealizar = other.programarealizar == null ? null : other.programarealizar.copy();
        this.programaArea = other.programaArea == null ? null : other.programaArea.copy();
        this.fechaInicio = other.fechaInicio == null ? null : other.fechaInicio.copy();
        this.programaEncontrado = other.programaEncontrado == null ? null : other.programaEncontrado.copy();
        this.nombrePrograma = other.nombrePrograma == null ? null : other.nombrePrograma.copy();
        this.universidad = other.universidad == null ? null : other.universidad.copy();
        this.pais = other.pais == null ? null : other.pais.copy();
        this.merecedorBeca = other.merecedorBeca == null ? null : other.merecedorBeca.copy();
        this.usuarioId = other.usuarioId == null ? null : other.usuarioId.copy();
    }

    @Override
    public PerfilUsuarioCriteria copy() {
        return new PerfilUsuarioCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateFilter fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public IntegerFilter getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(IntegerFilter identificacion) {
        this.identificacion = identificacion;
    }

    public StringFilter getMail() {
        return mail;
    }

    public void setMail(StringFilter mail) {
        this.mail = mail;
    }

    public IntegerFilter getArea() {
        return area;
    }

    public void setArea(IntegerFilter area) {
        this.area = area;
    }

    public IntegerFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(IntegerFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(StringFilter nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public StringFilter getAreaAcademica() {
        return areaAcademica;
    }

    public void setAreaAcademica(StringFilter areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public IntegerFilter getTerminoAcademico() {
        return terminoAcademico;
    }

    public void setTerminoAcademico(IntegerFilter terminoAcademico) {
        this.terminoAcademico = terminoAcademico;
    }

    public IntegerFilter getPuntajeICFES() {
        return puntajeICFES;
    }

    public void setPuntajeICFES(IntegerFilter puntajeICFES) {
        this.puntajeICFES = puntajeICFES;
    }

    public IntegerFilter getPromedioAcademico() {
        return promedioAcademico;
    }

    public void setPromedioAcademico(IntegerFilter promedioAcademico) {
        this.promedioAcademico = promedioAcademico;
    }

    public BooleanFilter getDominioIdioma() {
        return dominioIdioma;
    }

    public void setDominioIdioma(BooleanFilter dominioIdioma) {
        this.dominioIdioma = dominioIdioma;
    }

    public StringFilter getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(StringFilter idiomas) {
        this.idiomas = idiomas;
    }

    public BooleanFilter getExamenIdioma() {
        return examenIdioma;
    }

    public void setExamenIdioma(BooleanFilter examenIdioma) {
        this.examenIdioma = examenIdioma;
    }

    public StringFilter getExamenRealizado() {
        return examenRealizado;
    }

    public void setExamenRealizado(StringFilter examenRealizado) {
        this.examenRealizado = examenRealizado;
    }

    public IntegerFilter getPuntajeIdioma() {
        return puntajeIdioma;
    }

    public void setPuntajeIdioma(IntegerFilter puntajeIdioma) {
        this.puntajeIdioma = puntajeIdioma;
    }

    public BooleanFilter getBecaOtorgada() {
        return becaOtorgada;
    }

    public void setBecaOtorgada(BooleanFilter becaOtorgada) {
        this.becaOtorgada = becaOtorgada;
    }

    public IntegerFilter getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(IntegerFilter tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public StringFilter getBecaInstitucion() {
        return becaInstitucion;
    }

    public void setBecaInstitucion(StringFilter becaInstitucion) {
        this.becaInstitucion = becaInstitucion;
    }

    public BooleanFilter getGrupoSocial() {
        return grupoSocial;
    }

    public void setGrupoSocial(BooleanFilter grupoSocial) {
        this.grupoSocial = grupoSocial;
    }

    public StringFilter getFundacion() {
        return fundacion;
    }

    public void setFundacion(StringFilter fundacion) {
        this.fundacion = fundacion;
    }

    public BooleanFilter getMonitor() {
        return monitor;
    }

    public void setMonitor(BooleanFilter monitor) {
        this.monitor = monitor;
    }

    public StringFilter getMonitorMateria() {
        return monitorMateria;
    }

    public void setMonitorMateria(StringFilter monitorMateria) {
        this.monitorMateria = monitorMateria;
    }

    public StringFilter getLogrosAcademicos() {
        return logrosAcademicos;
    }

    public void setLogrosAcademicos(StringFilter logrosAcademicos) {
        this.logrosAcademicos = logrosAcademicos;
    }

    public BooleanFilter getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(BooleanFilter experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public StringFilter getAreaLaboral() {
        return areaLaboral;
    }

    public void setAreaLaboral(StringFilter areaLaboral) {
        this.areaLaboral = areaLaboral;
    }

    public IntegerFilter getProgramarealizar() {
        return programarealizar;
    }

    public void setProgramarealizar(IntegerFilter programarealizar) {
        this.programarealizar = programarealizar;
    }

    public StringFilter getProgramaArea() {
        return programaArea;
    }

    public void setProgramaArea(StringFilter programaArea) {
        this.programaArea = programaArea;
    }

    public LocalDateFilter getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateFilter fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BooleanFilter getProgramaEncontrado() {
        return programaEncontrado;
    }

    public void setProgramaEncontrado(BooleanFilter programaEncontrado) {
        this.programaEncontrado = programaEncontrado;
    }

    public StringFilter getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(StringFilter nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public StringFilter getUniversidad() {
        return universidad;
    }

    public void setUniversidad(StringFilter universidad) {
        this.universidad = universidad;
    }

    public IntegerFilter getPais() {
        return pais;
    }

    public void setPais(IntegerFilter pais) {
        this.pais = pais;
    }

    public StringFilter getMerecedorBeca() {
        return merecedorBeca;
    }

    public void setMerecedorBeca(StringFilter merecedorBeca) {
        this.merecedorBeca = merecedorBeca;
    }

    public LongFilter getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(LongFilter usuarioId) {
        this.usuarioId = usuarioId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PerfilUsuarioCriteria that = (PerfilUsuarioCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
            Objects.equals(identificacion, that.identificacion) &&
            Objects.equals(mail, that.mail) &&
            Objects.equals(area, that.area) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(nivelAcademico, that.nivelAcademico) &&
            Objects.equals(areaAcademica, that.areaAcademica) &&
            Objects.equals(terminoAcademico, that.terminoAcademico) &&
            Objects.equals(puntajeICFES, that.puntajeICFES) &&
            Objects.equals(promedioAcademico, that.promedioAcademico) &&
            Objects.equals(dominioIdioma, that.dominioIdioma) &&
            Objects.equals(idiomas, that.idiomas) &&
            Objects.equals(examenIdioma, that.examenIdioma) &&
            Objects.equals(examenRealizado, that.examenRealizado) &&
            Objects.equals(puntajeIdioma, that.puntajeIdioma) &&
            Objects.equals(becaOtorgada, that.becaOtorgada) &&
            Objects.equals(tipoBeca, that.tipoBeca) &&
            Objects.equals(becaInstitucion, that.becaInstitucion) &&
            Objects.equals(grupoSocial, that.grupoSocial) &&
            Objects.equals(fundacion, that.fundacion) &&
            Objects.equals(monitor, that.monitor) &&
            Objects.equals(monitorMateria, that.monitorMateria) &&
            Objects.equals(logrosAcademicos, that.logrosAcademicos) &&
            Objects.equals(experienciaLaboral, that.experienciaLaboral) &&
            Objects.equals(areaLaboral, that.areaLaboral) &&
            Objects.equals(programarealizar, that.programarealizar) &&
            Objects.equals(programaArea, that.programaArea) &&
            Objects.equals(fechaInicio, that.fechaInicio) &&
            Objects.equals(programaEncontrado, that.programaEncontrado) &&
            Objects.equals(nombrePrograma, that.nombrePrograma) &&
            Objects.equals(universidad, that.universidad) &&
            Objects.equals(pais, that.pais) &&
            Objects.equals(merecedorBeca, that.merecedorBeca) &&
            Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fechaNacimiento,
        identificacion,
        mail,
        area,
        telefono,
        nivelAcademico,
        areaAcademica,
        terminoAcademico,
        puntajeICFES,
        promedioAcademico,
        dominioIdioma,
        idiomas,
        examenIdioma,
        examenRealizado,
        puntajeIdioma,
        becaOtorgada,
        tipoBeca,
        becaInstitucion,
        grupoSocial,
        fundacion,
        monitor,
        monitorMateria,
        logrosAcademicos,
        experienciaLaboral,
        areaLaboral,
        programarealizar,
        programaArea,
        fechaInicio,
        programaEncontrado,
        nombrePrograma,
        universidad,
        pais,
        merecedorBeca,
        usuarioId
        );
    }

    @Override
    public String toString() {
        return "PerfilUsuarioCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fechaNacimiento != null ? "fechaNacimiento=" + fechaNacimiento + ", " : "") +
                (identificacion != null ? "identificacion=" + identificacion + ", " : "") +
                (mail != null ? "mail=" + mail + ", " : "") +
                (area != null ? "area=" + area + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (nivelAcademico != null ? "nivelAcademico=" + nivelAcademico + ", " : "") +
                (areaAcademica != null ? "areaAcademica=" + areaAcademica + ", " : "") +
                (terminoAcademico != null ? "terminoAcademico=" + terminoAcademico + ", " : "") +
                (puntajeICFES != null ? "puntajeICFES=" + puntajeICFES + ", " : "") +
                (promedioAcademico != null ? "promedioAcademico=" + promedioAcademico + ", " : "") +
                (dominioIdioma != null ? "dominioIdioma=" + dominioIdioma + ", " : "") +
                (idiomas != null ? "idiomas=" + idiomas + ", " : "") +
                (examenIdioma != null ? "examenIdioma=" + examenIdioma + ", " : "") +
                (examenRealizado != null ? "examenRealizado=" + examenRealizado + ", " : "") +
                (puntajeIdioma != null ? "puntajeIdioma=" + puntajeIdioma + ", " : "") +
                (becaOtorgada != null ? "becaOtorgada=" + becaOtorgada + ", " : "") +
                (tipoBeca != null ? "tipoBeca=" + tipoBeca + ", " : "") +
                (becaInstitucion != null ? "becaInstitucion=" + becaInstitucion + ", " : "") +
                (grupoSocial != null ? "grupoSocial=" + grupoSocial + ", " : "") +
                (fundacion != null ? "fundacion=" + fundacion + ", " : "") +
                (monitor != null ? "monitor=" + monitor + ", " : "") +
                (monitorMateria != null ? "monitorMateria=" + monitorMateria + ", " : "") +
                (logrosAcademicos != null ? "logrosAcademicos=" + logrosAcademicos + ", " : "") +
                (experienciaLaboral != null ? "experienciaLaboral=" + experienciaLaboral + ", " : "") +
                (areaLaboral != null ? "areaLaboral=" + areaLaboral + ", " : "") +
                (programarealizar != null ? "programarealizar=" + programarealizar + ", " : "") +
                (programaArea != null ? "programaArea=" + programaArea + ", " : "") +
                (fechaInicio != null ? "fechaInicio=" + fechaInicio + ", " : "") +
                (programaEncontrado != null ? "programaEncontrado=" + programaEncontrado + ", " : "") +
                (nombrePrograma != null ? "nombrePrograma=" + nombrePrograma + ", " : "") +
                (universidad != null ? "universidad=" + universidad + ", " : "") +
                (pais != null ? "pais=" + pais + ", " : "") +
                (merecedorBeca != null ? "merecedorBeca=" + merecedorBeca + ", " : "") +
                (usuarioId != null ? "usuarioId=" + usuarioId + ", " : "") +
            "}";
    }

}
