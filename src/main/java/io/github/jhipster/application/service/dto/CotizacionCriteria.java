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
 * Criteria class for the {@link io.github.jhipster.application.domain.Cotizacion} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.CotizacionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cotizacions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CotizacionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fechaViaje;

    private BooleanFilter alojamiento;

    private BooleanFilter pasajeAereo;

    private BooleanFilter transpAeropuerto;

    private LongFilter usarioId;

    private LongFilter cursoId;

    private LongFilter cRMId;

    public CotizacionCriteria(){
    }

    public CotizacionCriteria(CotizacionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fechaViaje = other.fechaViaje == null ? null : other.fechaViaje.copy();
        this.alojamiento = other.alojamiento == null ? null : other.alojamiento.copy();
        this.pasajeAereo = other.pasajeAereo == null ? null : other.pasajeAereo.copy();
        this.transpAeropuerto = other.transpAeropuerto == null ? null : other.transpAeropuerto.copy();
        this.usarioId = other.usarioId == null ? null : other.usarioId.copy();
        this.cursoId = other.cursoId == null ? null : other.cursoId.copy();
        this.cRMId = other.cRMId == null ? null : other.cRMId.copy();
    }

    @Override
    public CotizacionCriteria copy() {
        return new CotizacionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDateFilter fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public BooleanFilter getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(BooleanFilter alojamiento) {
        this.alojamiento = alojamiento;
    }

    public BooleanFilter getPasajeAereo() {
        return pasajeAereo;
    }

    public void setPasajeAereo(BooleanFilter pasajeAereo) {
        this.pasajeAereo = pasajeAereo;
    }

    public BooleanFilter getTranspAeropuerto() {
        return transpAeropuerto;
    }

    public void setTranspAeropuerto(BooleanFilter transpAeropuerto) {
        this.transpAeropuerto = transpAeropuerto;
    }

    public LongFilter getUsarioId() {
        return usarioId;
    }

    public void setUsarioId(LongFilter usarioId) {
        this.usarioId = usarioId;
    }

    public LongFilter getCursoId() {
        return cursoId;
    }

    public void setCursoId(LongFilter cursoId) {
        this.cursoId = cursoId;
    }

    public LongFilter getCRMId() {
        return cRMId;
    }

    public void setCRMId(LongFilter cRMId) {
        this.cRMId = cRMId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CotizacionCriteria that = (CotizacionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fechaViaje, that.fechaViaje) &&
            Objects.equals(alojamiento, that.alojamiento) &&
            Objects.equals(pasajeAereo, that.pasajeAereo) &&
            Objects.equals(transpAeropuerto, that.transpAeropuerto) &&
            Objects.equals(usarioId, that.usarioId) &&
            Objects.equals(cursoId, that.cursoId) &&
            Objects.equals(cRMId, that.cRMId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fechaViaje,
        alojamiento,
        pasajeAereo,
        transpAeropuerto,
        usarioId,
        cursoId,
        cRMId
        );
    }

    @Override
    public String toString() {
        return "CotizacionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fechaViaje != null ? "fechaViaje=" + fechaViaje + ", " : "") +
                (alojamiento != null ? "alojamiento=" + alojamiento + ", " : "") +
                (pasajeAereo != null ? "pasajeAereo=" + pasajeAereo + ", " : "") +
                (transpAeropuerto != null ? "transpAeropuerto=" + transpAeropuerto + ", " : "") +
                (usarioId != null ? "usarioId=" + usarioId + ", " : "") +
                (cursoId != null ? "cursoId=" + cursoId + ", " : "") +
                (cRMId != null ? "cRMId=" + cRMId + ", " : "") +
            "}";
    }

}
