package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.application.domain.enumeration.Estadod;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.PasoCRM} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PasoCRMResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /paso-crms?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PasoCRMCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Estadod
     */
    public static class EstadodFilter extends Filter<Estadod> {

        public EstadodFilter() {
        }

        public EstadodFilter(EstadodFilter filter) {
            super(filter);
        }

        @Override
        public EstadodFilter copy() {
            return new EstadodFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter codigo;

    private StringFilter descripcion;

    private IntegerFilter duracionDias;

    private EstadodFilter estado;

    public PasoCRMCriteria(){
    }

    public PasoCRMCriteria(PasoCRMCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.codigo = other.codigo == null ? null : other.codigo.copy();
        this.descripcion = other.descripcion == null ? null : other.descripcion.copy();
        this.duracionDias = other.duracionDias == null ? null : other.duracionDias.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
    }

    @Override
    public PasoCRMCriteria copy() {
        return new PasoCRMCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCodigo() {
        return codigo;
    }

    public void setCodigo(IntegerFilter codigo) {
        this.codigo = codigo;
    }

    public StringFilter getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(StringFilter descripcion) {
        this.descripcion = descripcion;
    }

    public IntegerFilter getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(IntegerFilter duracionDias) {
        this.duracionDias = duracionDias;
    }

    public EstadodFilter getEstado() {
        return estado;
    }

    public void setEstado(EstadodFilter estado) {
        this.estado = estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PasoCRMCriteria that = (PasoCRMCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codigo, that.codigo) &&
            Objects.equals(descripcion, that.descripcion) &&
            Objects.equals(duracionDias, that.duracionDias) &&
            Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codigo,
        descripcion,
        duracionDias,
        estado
        );
    }

    @Override
    public String toString() {
        return "PasoCRMCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codigo != null ? "codigo=" + codigo + ", " : "") +
                (descripcion != null ? "descripcion=" + descripcion + ", " : "") +
                (duracionDias != null ? "duracionDias=" + duracionDias + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
            "}";
    }

}
