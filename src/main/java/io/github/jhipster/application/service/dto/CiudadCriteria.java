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

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.Ciudad} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.CiudadResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ciudads?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CiudadCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombreCiudad;

    private StringFilter codigoCiudad;

    private LongFilter paisId;

    public CiudadCriteria(){
    }

    public CiudadCriteria(CiudadCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.nombreCiudad = other.nombreCiudad == null ? null : other.nombreCiudad.copy();
        this.codigoCiudad = other.codigoCiudad == null ? null : other.codigoCiudad.copy();
        this.paisId = other.paisId == null ? null : other.paisId.copy();
    }

    @Override
    public CiudadCriteria copy() {
        return new CiudadCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(StringFilter nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public StringFilter getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(StringFilter codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public LongFilter getPaisId() {
        return paisId;
    }

    public void setPaisId(LongFilter paisId) {
        this.paisId = paisId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CiudadCriteria that = (CiudadCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombreCiudad, that.nombreCiudad) &&
            Objects.equals(codigoCiudad, that.codigoCiudad) &&
            Objects.equals(paisId, that.paisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombreCiudad,
        codigoCiudad,
        paisId
        );
    }

    @Override
    public String toString() {
        return "CiudadCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombreCiudad != null ? "nombreCiudad=" + nombreCiudad + ", " : "") +
                (codigoCiudad != null ? "codigoCiudad=" + codigoCiudad + ", " : "") +
                (paisId != null ? "paisId=" + paisId + ", " : "") +
            "}";
    }

}