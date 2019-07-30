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
 * Criteria class for the {@link io.github.jhipster.application.domain.Experiencia} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ExperienciaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /experiencias?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ExperienciaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter identificacion;

    private StringFilter nombre;

    private StringFilter apellido;

    private StringFilter mail;

    private IntegerFilter area;

    private IntegerFilter telefono;

    private StringFilter nacionalidad;

    private StringFilter paisDestino;

    private IntegerFilter calificaPais;

    private StringFilter programa;

    private StringFilter institucion;

    private IntegerFilter calificaInstitucion;

    private StringFilter agencia;

    private IntegerFilter calificaAgencia;

    private LocalDateFilter fecha;

    public ExperienciaCriteria(){
    }

    public ExperienciaCriteria(ExperienciaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.identificacion = other.identificacion == null ? null : other.identificacion.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.apellido = other.apellido == null ? null : other.apellido.copy();
        this.mail = other.mail == null ? null : other.mail.copy();
        this.area = other.area == null ? null : other.area.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.nacionalidad = other.nacionalidad == null ? null : other.nacionalidad.copy();
        this.paisDestino = other.paisDestino == null ? null : other.paisDestino.copy();
        this.calificaPais = other.calificaPais == null ? null : other.calificaPais.copy();
        this.programa = other.programa == null ? null : other.programa.copy();
        this.institucion = other.institucion == null ? null : other.institucion.copy();
        this.calificaInstitucion = other.calificaInstitucion == null ? null : other.calificaInstitucion.copy();
        this.agencia = other.agencia == null ? null : other.agencia.copy();
        this.calificaAgencia = other.calificaAgencia == null ? null : other.calificaAgencia.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
    }

    @Override
    public ExperienciaCriteria copy() {
        return new ExperienciaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(IntegerFilter identificacion) {
        this.identificacion = identificacion;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getApellido() {
        return apellido;
    }

    public void setApellido(StringFilter apellido) {
        this.apellido = apellido;
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

    public StringFilter getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(StringFilter nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public StringFilter getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(StringFilter paisDestino) {
        this.paisDestino = paisDestino;
    }

    public IntegerFilter getCalificaPais() {
        return calificaPais;
    }

    public void setCalificaPais(IntegerFilter calificaPais) {
        this.calificaPais = calificaPais;
    }

    public StringFilter getPrograma() {
        return programa;
    }

    public void setPrograma(StringFilter programa) {
        this.programa = programa;
    }

    public StringFilter getInstitucion() {
        return institucion;
    }

    public void setInstitucion(StringFilter institucion) {
        this.institucion = institucion;
    }

    public IntegerFilter getCalificaInstitucion() {
        return calificaInstitucion;
    }

    public void setCalificaInstitucion(IntegerFilter calificaInstitucion) {
        this.calificaInstitucion = calificaInstitucion;
    }

    public StringFilter getAgencia() {
        return agencia;
    }

    public void setAgencia(StringFilter agencia) {
        this.agencia = agencia;
    }

    public IntegerFilter getCalificaAgencia() {
        return calificaAgencia;
    }

    public void setCalificaAgencia(IntegerFilter calificaAgencia) {
        this.calificaAgencia = calificaAgencia;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ExperienciaCriteria that = (ExperienciaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(identificacion, that.identificacion) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(apellido, that.apellido) &&
            Objects.equals(mail, that.mail) &&
            Objects.equals(area, that.area) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(nacionalidad, that.nacionalidad) &&
            Objects.equals(paisDestino, that.paisDestino) &&
            Objects.equals(calificaPais, that.calificaPais) &&
            Objects.equals(programa, that.programa) &&
            Objects.equals(institucion, that.institucion) &&
            Objects.equals(calificaInstitucion, that.calificaInstitucion) &&
            Objects.equals(agencia, that.agencia) &&
            Objects.equals(calificaAgencia, that.calificaAgencia) &&
            Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        identificacion,
        nombre,
        apellido,
        mail,
        area,
        telefono,
        nacionalidad,
        paisDestino,
        calificaPais,
        programa,
        institucion,
        calificaInstitucion,
        agencia,
        calificaAgencia,
        fecha
        );
    }

    @Override
    public String toString() {
        return "ExperienciaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (identificacion != null ? "identificacion=" + identificacion + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (apellido != null ? "apellido=" + apellido + ", " : "") +
                (mail != null ? "mail=" + mail + ", " : "") +
                (area != null ? "area=" + area + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (nacionalidad != null ? "nacionalidad=" + nacionalidad + ", " : "") +
                (paisDestino != null ? "paisDestino=" + paisDestino + ", " : "") +
                (calificaPais != null ? "calificaPais=" + calificaPais + ", " : "") +
                (programa != null ? "programa=" + programa + ", " : "") +
                (institucion != null ? "institucion=" + institucion + ", " : "") +
                (calificaInstitucion != null ? "calificaInstitucion=" + calificaInstitucion + ", " : "") +
                (agencia != null ? "agencia=" + agencia + ", " : "") +
                (calificaAgencia != null ? "calificaAgencia=" + calificaAgencia + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
            "}";
    }

}
