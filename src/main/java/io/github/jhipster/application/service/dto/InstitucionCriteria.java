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
 * Criteria class for the {@link io.github.jhipster.application.domain.Institucion} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.InstitucionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /institucions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class InstitucionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter codigo;

    private StringFilter nombre;

    private StringFilter direccion;

    private StringFilter website;

    private StringFilter contacto;

    private StringFilter representante;

    private StringFilter skype;

    private StringFilter telefono;

    private StringFilter estatus;

    private StringFilter tipoProgramas;

    private StringFilter programaEstandar;

    private StringFilter programaIntensivo;

    private StringFilter programaNegocios;

    private StringFilter preparacionExamen;

    private StringFilter programaAcademico;

    private IntegerFilter descuento;

    private StringFilter inscripcion;

    private StringFilter materiales;

    private StringFilter seguroMedico;

    private StringFilter alojamientoSencillo;

    private StringFilter alojamientoDoble;

    private StringFilter transporteAeropuerto;

    private StringFilter tipoCurso;

    private StringFilter temporadaAlta;

    private StringFilter temporadaBaja;

    private LocalDateFilter fechaInicial;

    private StringFilter horarios;

    private StringFilter instalaciones;

    private StringFilter nacionalidad;

    private LongFilter ciudadId;

    public InstitucionCriteria(){
    }

    public InstitucionCriteria(InstitucionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.codigo = other.codigo == null ? null : other.codigo.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.website = other.website == null ? null : other.website.copy();
        this.contacto = other.contacto == null ? null : other.contacto.copy();
        this.representante = other.representante == null ? null : other.representante.copy();
        this.skype = other.skype == null ? null : other.skype.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.estatus = other.estatus == null ? null : other.estatus.copy();
        this.tipoProgramas = other.tipoProgramas == null ? null : other.tipoProgramas.copy();
        this.programaEstandar = other.programaEstandar == null ? null : other.programaEstandar.copy();
        this.programaIntensivo = other.programaIntensivo == null ? null : other.programaIntensivo.copy();
        this.programaNegocios = other.programaNegocios == null ? null : other.programaNegocios.copy();
        this.preparacionExamen = other.preparacionExamen == null ? null : other.preparacionExamen.copy();
        this.programaAcademico = other.programaAcademico == null ? null : other.programaAcademico.copy();
        this.descuento = other.descuento == null ? null : other.descuento.copy();
        this.inscripcion = other.inscripcion == null ? null : other.inscripcion.copy();
        this.materiales = other.materiales == null ? null : other.materiales.copy();
        this.seguroMedico = other.seguroMedico == null ? null : other.seguroMedico.copy();
        this.alojamientoSencillo = other.alojamientoSencillo == null ? null : other.alojamientoSencillo.copy();
        this.alojamientoDoble = other.alojamientoDoble == null ? null : other.alojamientoDoble.copy();
        this.transporteAeropuerto = other.transporteAeropuerto == null ? null : other.transporteAeropuerto.copy();
        this.tipoCurso = other.tipoCurso == null ? null : other.tipoCurso.copy();
        this.temporadaAlta = other.temporadaAlta == null ? null : other.temporadaAlta.copy();
        this.temporadaBaja = other.temporadaBaja == null ? null : other.temporadaBaja.copy();
        this.fechaInicial = other.fechaInicial == null ? null : other.fechaInicial.copy();
        this.horarios = other.horarios == null ? null : other.horarios.copy();
        this.instalaciones = other.instalaciones == null ? null : other.instalaciones.copy();
        this.nacionalidad = other.nacionalidad == null ? null : other.nacionalidad.copy();
        this.ciudadId = other.ciudadId == null ? null : other.ciudadId.copy();
    }

    @Override
    public InstitucionCriteria copy() {
        return new InstitucionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCodigo() {
        return codigo;
    }

    public void setCodigo(StringFilter codigo) {
        this.codigo = codigo;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public StringFilter getWebsite() {
        return website;
    }

    public void setWebsite(StringFilter website) {
        this.website = website;
    }

    public StringFilter getContacto() {
        return contacto;
    }

    public void setContacto(StringFilter contacto) {
        this.contacto = contacto;
    }

    public StringFilter getRepresentante() {
        return representante;
    }

    public void setRepresentante(StringFilter representante) {
        this.representante = representante;
    }

    public StringFilter getSkype() {
        return skype;
    }

    public void setSkype(StringFilter skype) {
        this.skype = skype;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getEstatus() {
        return estatus;
    }

    public void setEstatus(StringFilter estatus) {
        this.estatus = estatus;
    }

    public StringFilter getTipoProgramas() {
        return tipoProgramas;
    }

    public void setTipoProgramas(StringFilter tipoProgramas) {
        this.tipoProgramas = tipoProgramas;
    }

    public StringFilter getProgramaEstandar() {
        return programaEstandar;
    }

    public void setProgramaEstandar(StringFilter programaEstandar) {
        this.programaEstandar = programaEstandar;
    }

    public StringFilter getProgramaIntensivo() {
        return programaIntensivo;
    }

    public void setProgramaIntensivo(StringFilter programaIntensivo) {
        this.programaIntensivo = programaIntensivo;
    }

    public StringFilter getProgramaNegocios() {
        return programaNegocios;
    }

    public void setProgramaNegocios(StringFilter programaNegocios) {
        this.programaNegocios = programaNegocios;
    }

    public StringFilter getPreparacionExamen() {
        return preparacionExamen;
    }

    public void setPreparacionExamen(StringFilter preparacionExamen) {
        this.preparacionExamen = preparacionExamen;
    }

    public StringFilter getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(StringFilter programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public IntegerFilter getDescuento() {
        return descuento;
    }

    public void setDescuento(IntegerFilter descuento) {
        this.descuento = descuento;
    }

    public StringFilter getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(StringFilter inscripcion) {
        this.inscripcion = inscripcion;
    }

    public StringFilter getMateriales() {
        return materiales;
    }

    public void setMateriales(StringFilter materiales) {
        this.materiales = materiales;
    }

    public StringFilter getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(StringFilter seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public StringFilter getAlojamientoSencillo() {
        return alojamientoSencillo;
    }

    public void setAlojamientoSencillo(StringFilter alojamientoSencillo) {
        this.alojamientoSencillo = alojamientoSencillo;
    }

    public StringFilter getAlojamientoDoble() {
        return alojamientoDoble;
    }

    public void setAlojamientoDoble(StringFilter alojamientoDoble) {
        this.alojamientoDoble = alojamientoDoble;
    }

    public StringFilter getTransporteAeropuerto() {
        return transporteAeropuerto;
    }

    public void setTransporteAeropuerto(StringFilter transporteAeropuerto) {
        this.transporteAeropuerto = transporteAeropuerto;
    }

    public StringFilter getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(StringFilter tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public StringFilter getTemporadaAlta() {
        return temporadaAlta;
    }

    public void setTemporadaAlta(StringFilter temporadaAlta) {
        this.temporadaAlta = temporadaAlta;
    }

    public StringFilter getTemporadaBaja() {
        return temporadaBaja;
    }

    public void setTemporadaBaja(StringFilter temporadaBaja) {
        this.temporadaBaja = temporadaBaja;
    }

    public LocalDateFilter getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDateFilter fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public StringFilter getHorarios() {
        return horarios;
    }

    public void setHorarios(StringFilter horarios) {
        this.horarios = horarios;
    }

    public StringFilter getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(StringFilter instalaciones) {
        this.instalaciones = instalaciones;
    }

    public StringFilter getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(StringFilter nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LongFilter getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(LongFilter ciudadId) {
        this.ciudadId = ciudadId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final InstitucionCriteria that = (InstitucionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codigo, that.codigo) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(website, that.website) &&
            Objects.equals(contacto, that.contacto) &&
            Objects.equals(representante, that.representante) &&
            Objects.equals(skype, that.skype) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(estatus, that.estatus) &&
            Objects.equals(tipoProgramas, that.tipoProgramas) &&
            Objects.equals(programaEstandar, that.programaEstandar) &&
            Objects.equals(programaIntensivo, that.programaIntensivo) &&
            Objects.equals(programaNegocios, that.programaNegocios) &&
            Objects.equals(preparacionExamen, that.preparacionExamen) &&
            Objects.equals(programaAcademico, that.programaAcademico) &&
            Objects.equals(descuento, that.descuento) &&
            Objects.equals(inscripcion, that.inscripcion) &&
            Objects.equals(materiales, that.materiales) &&
            Objects.equals(seguroMedico, that.seguroMedico) &&
            Objects.equals(alojamientoSencillo, that.alojamientoSencillo) &&
            Objects.equals(alojamientoDoble, that.alojamientoDoble) &&
            Objects.equals(transporteAeropuerto, that.transporteAeropuerto) &&
            Objects.equals(tipoCurso, that.tipoCurso) &&
            Objects.equals(temporadaAlta, that.temporadaAlta) &&
            Objects.equals(temporadaBaja, that.temporadaBaja) &&
            Objects.equals(fechaInicial, that.fechaInicial) &&
            Objects.equals(horarios, that.horarios) &&
            Objects.equals(instalaciones, that.instalaciones) &&
            Objects.equals(nacionalidad, that.nacionalidad) &&
            Objects.equals(ciudadId, that.ciudadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codigo,
        nombre,
        direccion,
        website,
        contacto,
        representante,
        skype,
        telefono,
        estatus,
        tipoProgramas,
        programaEstandar,
        programaIntensivo,
        programaNegocios,
        preparacionExamen,
        programaAcademico,
        descuento,
        inscripcion,
        materiales,
        seguroMedico,
        alojamientoSencillo,
        alojamientoDoble,
        transporteAeropuerto,
        tipoCurso,
        temporadaAlta,
        temporadaBaja,
        fechaInicial,
        horarios,
        instalaciones,
        nacionalidad,
        ciudadId
        );
    }

    @Override
    public String toString() {
        return "InstitucionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codigo != null ? "codigo=" + codigo + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (website != null ? "website=" + website + ", " : "") +
                (contacto != null ? "contacto=" + contacto + ", " : "") +
                (representante != null ? "representante=" + representante + ", " : "") +
                (skype != null ? "skype=" + skype + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (estatus != null ? "estatus=" + estatus + ", " : "") +
                (tipoProgramas != null ? "tipoProgramas=" + tipoProgramas + ", " : "") +
                (programaEstandar != null ? "programaEstandar=" + programaEstandar + ", " : "") +
                (programaIntensivo != null ? "programaIntensivo=" + programaIntensivo + ", " : "") +
                (programaNegocios != null ? "programaNegocios=" + programaNegocios + ", " : "") +
                (preparacionExamen != null ? "preparacionExamen=" + preparacionExamen + ", " : "") +
                (programaAcademico != null ? "programaAcademico=" + programaAcademico + ", " : "") +
                (descuento != null ? "descuento=" + descuento + ", " : "") +
                (inscripcion != null ? "inscripcion=" + inscripcion + ", " : "") +
                (materiales != null ? "materiales=" + materiales + ", " : "") +
                (seguroMedico != null ? "seguroMedico=" + seguroMedico + ", " : "") +
                (alojamientoSencillo != null ? "alojamientoSencillo=" + alojamientoSencillo + ", " : "") +
                (alojamientoDoble != null ? "alojamientoDoble=" + alojamientoDoble + ", " : "") +
                (transporteAeropuerto != null ? "transporteAeropuerto=" + transporteAeropuerto + ", " : "") +
                (tipoCurso != null ? "tipoCurso=" + tipoCurso + ", " : "") +
                (temporadaAlta != null ? "temporadaAlta=" + temporadaAlta + ", " : "") +
                (temporadaBaja != null ? "temporadaBaja=" + temporadaBaja + ", " : "") +
                (fechaInicial != null ? "fechaInicial=" + fechaInicial + ", " : "") +
                (horarios != null ? "horarios=" + horarios + ", " : "") +
                (instalaciones != null ? "instalaciones=" + instalaciones + ", " : "") +
                (nacionalidad != null ? "nacionalidad=" + nacionalidad + ", " : "") +
                (ciudadId != null ? "ciudadId=" + ciudadId + ", " : "") +
            "}";
    }

}
