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
 * Criteria class for the {@link io.github.jhipster.application.domain.Agencia} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.AgenciaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /agencias?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AgenciaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombreAgencia;

    private StringFilter codigo;

    private StringFilter encargado;

    private StringFilter direccion;

    private StringFilter telefono;

    private StringFilter email;

    private StringFilter whatsapp;

    private StringFilter asesor1;

    private StringFilter asesor2;

    private StringFilter asesor3;

    private StringFilter sede1;

    private StringFilter sede2;

    private LongFilter paisId;

    public AgenciaCriteria(){
    }

    public AgenciaCriteria(AgenciaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.nombreAgencia = other.nombreAgencia == null ? null : other.nombreAgencia.copy();
        this.codigo = other.codigo == null ? null : other.codigo.copy();
        this.encargado = other.encargado == null ? null : other.encargado.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.whatsapp = other.whatsapp == null ? null : other.whatsapp.copy();
        this.asesor1 = other.asesor1 == null ? null : other.asesor1.copy();
        this.asesor2 = other.asesor2 == null ? null : other.asesor2.copy();
        this.asesor3 = other.asesor3 == null ? null : other.asesor3.copy();
        this.sede1 = other.sede1 == null ? null : other.sede1.copy();
        this.sede2 = other.sede2 == null ? null : other.sede2.copy();
        this.paisId = other.paisId == null ? null : other.paisId.copy();
    }

    @Override
    public AgenciaCriteria copy() {
        return new AgenciaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(StringFilter nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public StringFilter getCodigo() {
        return codigo;
    }

    public void setCodigo(StringFilter codigo) {
        this.codigo = codigo;
    }

    public StringFilter getEncargado() {
        return encargado;
    }

    public void setEncargado(StringFilter encargado) {
        this.encargado = encargado;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(StringFilter whatsapp) {
        this.whatsapp = whatsapp;
    }

    public StringFilter getAsesor1() {
        return asesor1;
    }

    public void setAsesor1(StringFilter asesor1) {
        this.asesor1 = asesor1;
    }

    public StringFilter getAsesor2() {
        return asesor2;
    }

    public void setAsesor2(StringFilter asesor2) {
        this.asesor2 = asesor2;
    }

    public StringFilter getAsesor3() {
        return asesor3;
    }

    public void setAsesor3(StringFilter asesor3) {
        this.asesor3 = asesor3;
    }

    public StringFilter getSede1() {
        return sede1;
    }

    public void setSede1(StringFilter sede1) {
        this.sede1 = sede1;
    }

    public StringFilter getSede2() {
        return sede2;
    }

    public void setSede2(StringFilter sede2) {
        this.sede2 = sede2;
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
        final AgenciaCriteria that = (AgenciaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombreAgencia, that.nombreAgencia) &&
            Objects.equals(codigo, that.codigo) &&
            Objects.equals(encargado, that.encargado) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(email, that.email) &&
            Objects.equals(whatsapp, that.whatsapp) &&
            Objects.equals(asesor1, that.asesor1) &&
            Objects.equals(asesor2, that.asesor2) &&
            Objects.equals(asesor3, that.asesor3) &&
            Objects.equals(sede1, that.sede1) &&
            Objects.equals(sede2, that.sede2) &&
            Objects.equals(paisId, that.paisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombreAgencia,
        codigo,
        encargado,
        direccion,
        telefono,
        email,
        whatsapp,
        asesor1,
        asesor2,
        asesor3,
        sede1,
        sede2,
        paisId
        );
    }

    @Override
    public String toString() {
        return "AgenciaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombreAgencia != null ? "nombreAgencia=" + nombreAgencia + ", " : "") +
                (codigo != null ? "codigo=" + codigo + ", " : "") +
                (encargado != null ? "encargado=" + encargado + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (whatsapp != null ? "whatsapp=" + whatsapp + ", " : "") +
                (asesor1 != null ? "asesor1=" + asesor1 + ", " : "") +
                (asesor2 != null ? "asesor2=" + asesor2 + ", " : "") +
                (asesor3 != null ? "asesor3=" + asesor3 + ", " : "") +
                (sede1 != null ? "sede1=" + sede1 + ", " : "") +
                (sede2 != null ? "sede2=" + sede2 + ", " : "") +
                (paisId != null ? "paisId=" + paisId + ", " : "") +
            "}";
    }

}
