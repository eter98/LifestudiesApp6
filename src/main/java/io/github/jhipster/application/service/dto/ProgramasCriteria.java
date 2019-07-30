package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.application.domain.enumeration.Monedad;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.Programas} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ProgramasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /programas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProgramasCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Monedad
     */
    public static class MonedadFilter extends Filter<Monedad> {

        public MonedadFilter() {
        }

        public MonedadFilter(MonedadFilter filter) {
            super(filter);
        }

        @Override
        public MonedadFilter copy() {
            return new MonedadFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter registro;

    private MonedadFilter moneda;

    private StringFilter curso;

    private StringFilter horario;

    private IntegerFilter frecuencia;

    private IntegerFilter duracion;

    private LongFilter institucionId;

    private LongFilter ciudadId;

    private LongFilter tipoProgramaId;

    public ProgramasCriteria(){
    }

    public ProgramasCriteria(ProgramasCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.registro = other.registro == null ? null : other.registro.copy();
        this.moneda = other.moneda == null ? null : other.moneda.copy();
        this.curso = other.curso == null ? null : other.curso.copy();
        this.horario = other.horario == null ? null : other.horario.copy();
        this.frecuencia = other.frecuencia == null ? null : other.frecuencia.copy();
        this.duracion = other.duracion == null ? null : other.duracion.copy();
        this.institucionId = other.institucionId == null ? null : other.institucionId.copy();
        this.ciudadId = other.ciudadId == null ? null : other.ciudadId.copy();
        this.tipoProgramaId = other.tipoProgramaId == null ? null : other.tipoProgramaId.copy();
    }

    @Override
    public ProgramasCriteria copy() {
        return new ProgramasCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRegistro() {
        return registro;
    }

    public void setRegistro(IntegerFilter registro) {
        this.registro = registro;
    }

    public MonedadFilter getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedadFilter moneda) {
        this.moneda = moneda;
    }

    public StringFilter getCurso() {
        return curso;
    }

    public void setCurso(StringFilter curso) {
        this.curso = curso;
    }

    public StringFilter getHorario() {
        return horario;
    }

    public void setHorario(StringFilter horario) {
        this.horario = horario;
    }

    public IntegerFilter getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(IntegerFilter frecuencia) {
        this.frecuencia = frecuencia;
    }

    public IntegerFilter getDuracion() {
        return duracion;
    }

    public void setDuracion(IntegerFilter duracion) {
        this.duracion = duracion;
    }

    public LongFilter getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(LongFilter institucionId) {
        this.institucionId = institucionId;
    }

    public LongFilter getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(LongFilter ciudadId) {
        this.ciudadId = ciudadId;
    }

    public LongFilter getTipoProgramaId() {
        return tipoProgramaId;
    }

    public void setTipoProgramaId(LongFilter tipoProgramaId) {
        this.tipoProgramaId = tipoProgramaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProgramasCriteria that = (ProgramasCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(registro, that.registro) &&
            Objects.equals(moneda, that.moneda) &&
            Objects.equals(curso, that.curso) &&
            Objects.equals(horario, that.horario) &&
            Objects.equals(frecuencia, that.frecuencia) &&
            Objects.equals(duracion, that.duracion) &&
            Objects.equals(institucionId, that.institucionId) &&
            Objects.equals(ciudadId, that.ciudadId) &&
            Objects.equals(tipoProgramaId, that.tipoProgramaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        registro,
        moneda,
        curso,
        horario,
        frecuencia,
        duracion,
        institucionId,
        ciudadId,
        tipoProgramaId
        );
    }

    @Override
    public String toString() {
        return "ProgramasCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (registro != null ? "registro=" + registro + ", " : "") +
                (moneda != null ? "moneda=" + moneda + ", " : "") +
                (curso != null ? "curso=" + curso + ", " : "") +
                (horario != null ? "horario=" + horario + ", " : "") +
                (frecuencia != null ? "frecuencia=" + frecuencia + ", " : "") +
                (duracion != null ? "duracion=" + duracion + ", " : "") +
                (institucionId != null ? "institucionId=" + institucionId + ", " : "") +
                (ciudadId != null ? "ciudadId=" + ciudadId + ", " : "") +
                (tipoProgramaId != null ? "tipoProgramaId=" + tipoProgramaId + ", " : "") +
            "}";
    }

}
