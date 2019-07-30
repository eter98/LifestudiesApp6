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
 * Criteria class for the {@link io.github.jhipster.application.domain.Pais} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PaisResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pais?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaisCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombrePais;

    private StringFilter codigoPais;

    public PaisCriteria(){
    }

    public PaisCriteria(PaisCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.nombrePais = other.nombrePais == null ? null : other.nombrePais.copy();
        this.codigoPais = other.codigoPais == null ? null : other.codigoPais.copy();
    }

    @Override
    public PaisCriteria copy() {
        return new PaisCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(StringFilter nombrePais) {
        this.nombrePais = nombrePais;
    }

    public StringFilter getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(StringFilter codigoPais) {
        this.codigoPais = codigoPais;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaisCriteria that = (PaisCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombrePais, that.nombrePais) &&
            Objects.equals(codigoPais, that.codigoPais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombrePais,
        codigoPais
        );
    }

    @Override
    public String toString() {
        return "PaisCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombrePais != null ? "nombrePais=" + nombrePais + ", " : "") +
                (codigoPais != null ? "codigoPais=" + codigoPais + ", " : "") +
            "}";
    }

}
