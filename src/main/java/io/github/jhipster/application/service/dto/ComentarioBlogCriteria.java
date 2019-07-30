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
 * Criteria class for the {@link io.github.jhipster.application.domain.ComentarioBlog} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ComentarioBlogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /comentario-blogs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ComentarioBlogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fecha;

    private IntegerFilter calificacion;

    private LongFilter usuarioId;

    private LongFilter tituloBlogId;

    public ComentarioBlogCriteria(){
    }

    public ComentarioBlogCriteria(ComentarioBlogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.calificacion = other.calificacion == null ? null : other.calificacion.copy();
        this.usuarioId = other.usuarioId == null ? null : other.usuarioId.copy();
        this.tituloBlogId = other.tituloBlogId == null ? null : other.tituloBlogId.copy();
    }

    @Override
    public ComentarioBlogCriteria copy() {
        return new ComentarioBlogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }

    public IntegerFilter getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(IntegerFilter calificacion) {
        this.calificacion = calificacion;
    }

    public LongFilter getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(LongFilter usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LongFilter getTituloBlogId() {
        return tituloBlogId;
    }

    public void setTituloBlogId(LongFilter tituloBlogId) {
        this.tituloBlogId = tituloBlogId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ComentarioBlogCriteria that = (ComentarioBlogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(calificacion, that.calificacion) &&
            Objects.equals(usuarioId, that.usuarioId) &&
            Objects.equals(tituloBlogId, that.tituloBlogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fecha,
        calificacion,
        usuarioId,
        tituloBlogId
        );
    }

    @Override
    public String toString() {
        return "ComentarioBlogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (calificacion != null ? "calificacion=" + calificacion + ", " : "") +
                (usuarioId != null ? "usuarioId=" + usuarioId + ", " : "") +
                (tituloBlogId != null ? "tituloBlogId=" + tituloBlogId + ", " : "") +
            "}";
    }

}
