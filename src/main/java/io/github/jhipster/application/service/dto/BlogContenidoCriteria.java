package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Destinod;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.BlogContenido} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.BlogContenidoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /blog-contenidos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BlogContenidoCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Nacionalidadd
     */
    public static class NacionalidaddFilter extends Filter<Nacionalidadd> {

        public NacionalidaddFilter() {
        }

        public NacionalidaddFilter(NacionalidaddFilter filter) {
            super(filter);
        }

        @Override
        public NacionalidaddFilter copy() {
            return new NacionalidaddFilter(this);
        }

    }
    /**
     * Class for filtering Destinod
     */
    public static class DestinodFilter extends Filter<Destinod> {

        public DestinodFilter() {
        }

        public DestinodFilter(DestinodFilter filter) {
            super(filter);
        }

        @Override
        public DestinodFilter copy() {
            return new DestinodFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private StringFilter apellido;

    private StringFilter correo;

    private NacionalidaddFilter nacionalidad;

    private DestinodFilter paisEstudio;

    private IntegerFilter calificacionPais;

    private StringFilter ciudadVive;

    private IntegerFilter calificacionCiudad;

    private StringFilter programaRealizado;

    private StringFilter institucionEstudio;

    private IntegerFilter calificacionInstitucion;

    private BooleanFilter agenciaEstudios;

    private StringFilter nombreAgencia;

    private IntegerFilter calificacionAgencia;

    private IntegerFilter calificacionExperienciaEstudio;

    private LocalDateFilter fecha;

    private StringFilter titulo;

    private LongFilter usuarioId;

    public BlogContenidoCriteria(){
    }

    public BlogContenidoCriteria(BlogContenidoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.apellido = other.apellido == null ? null : other.apellido.copy();
        this.correo = other.correo == null ? null : other.correo.copy();
        this.nacionalidad = other.nacionalidad == null ? null : other.nacionalidad.copy();
        this.paisEstudio = other.paisEstudio == null ? null : other.paisEstudio.copy();
        this.calificacionPais = other.calificacionPais == null ? null : other.calificacionPais.copy();
        this.ciudadVive = other.ciudadVive == null ? null : other.ciudadVive.copy();
        this.calificacionCiudad = other.calificacionCiudad == null ? null : other.calificacionCiudad.copy();
        this.programaRealizado = other.programaRealizado == null ? null : other.programaRealizado.copy();
        this.institucionEstudio = other.institucionEstudio == null ? null : other.institucionEstudio.copy();
        this.calificacionInstitucion = other.calificacionInstitucion == null ? null : other.calificacionInstitucion.copy();
        this.agenciaEstudios = other.agenciaEstudios == null ? null : other.agenciaEstudios.copy();
        this.nombreAgencia = other.nombreAgencia == null ? null : other.nombreAgencia.copy();
        this.calificacionAgencia = other.calificacionAgencia == null ? null : other.calificacionAgencia.copy();
        this.calificacionExperienciaEstudio = other.calificacionExperienciaEstudio == null ? null : other.calificacionExperienciaEstudio.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.titulo = other.titulo == null ? null : other.titulo.copy();
        this.usuarioId = other.usuarioId == null ? null : other.usuarioId.copy();
    }

    @Override
    public BlogContenidoCriteria copy() {
        return new BlogContenidoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public StringFilter getCorreo() {
        return correo;
    }

    public void setCorreo(StringFilter correo) {
        this.correo = correo;
    }

    public NacionalidaddFilter getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(NacionalidaddFilter nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public DestinodFilter getPaisEstudio() {
        return paisEstudio;
    }

    public void setPaisEstudio(DestinodFilter paisEstudio) {
        this.paisEstudio = paisEstudio;
    }

    public IntegerFilter getCalificacionPais() {
        return calificacionPais;
    }

    public void setCalificacionPais(IntegerFilter calificacionPais) {
        this.calificacionPais = calificacionPais;
    }

    public StringFilter getCiudadVive() {
        return ciudadVive;
    }

    public void setCiudadVive(StringFilter ciudadVive) {
        this.ciudadVive = ciudadVive;
    }

    public IntegerFilter getCalificacionCiudad() {
        return calificacionCiudad;
    }

    public void setCalificacionCiudad(IntegerFilter calificacionCiudad) {
        this.calificacionCiudad = calificacionCiudad;
    }

    public StringFilter getProgramaRealizado() {
        return programaRealizado;
    }

    public void setProgramaRealizado(StringFilter programaRealizado) {
        this.programaRealizado = programaRealizado;
    }

    public StringFilter getInstitucionEstudio() {
        return institucionEstudio;
    }

    public void setInstitucionEstudio(StringFilter institucionEstudio) {
        this.institucionEstudio = institucionEstudio;
    }

    public IntegerFilter getCalificacionInstitucion() {
        return calificacionInstitucion;
    }

    public void setCalificacionInstitucion(IntegerFilter calificacionInstitucion) {
        this.calificacionInstitucion = calificacionInstitucion;
    }

    public BooleanFilter getAgenciaEstudios() {
        return agenciaEstudios;
    }

    public void setAgenciaEstudios(BooleanFilter agenciaEstudios) {
        this.agenciaEstudios = agenciaEstudios;
    }

    public StringFilter getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(StringFilter nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public IntegerFilter getCalificacionAgencia() {
        return calificacionAgencia;
    }

    public void setCalificacionAgencia(IntegerFilter calificacionAgencia) {
        this.calificacionAgencia = calificacionAgencia;
    }

    public IntegerFilter getCalificacionExperienciaEstudio() {
        return calificacionExperienciaEstudio;
    }

    public void setCalificacionExperienciaEstudio(IntegerFilter calificacionExperienciaEstudio) {
        this.calificacionExperienciaEstudio = calificacionExperienciaEstudio;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }

    public StringFilter getTitulo() {
        return titulo;
    }

    public void setTitulo(StringFilter titulo) {
        this.titulo = titulo;
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
        final BlogContenidoCriteria that = (BlogContenidoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(apellido, that.apellido) &&
            Objects.equals(correo, that.correo) &&
            Objects.equals(nacionalidad, that.nacionalidad) &&
            Objects.equals(paisEstudio, that.paisEstudio) &&
            Objects.equals(calificacionPais, that.calificacionPais) &&
            Objects.equals(ciudadVive, that.ciudadVive) &&
            Objects.equals(calificacionCiudad, that.calificacionCiudad) &&
            Objects.equals(programaRealizado, that.programaRealizado) &&
            Objects.equals(institucionEstudio, that.institucionEstudio) &&
            Objects.equals(calificacionInstitucion, that.calificacionInstitucion) &&
            Objects.equals(agenciaEstudios, that.agenciaEstudios) &&
            Objects.equals(nombreAgencia, that.nombreAgencia) &&
            Objects.equals(calificacionAgencia, that.calificacionAgencia) &&
            Objects.equals(calificacionExperienciaEstudio, that.calificacionExperienciaEstudio) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(titulo, that.titulo) &&
            Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombre,
        apellido,
        correo,
        nacionalidad,
        paisEstudio,
        calificacionPais,
        ciudadVive,
        calificacionCiudad,
        programaRealizado,
        institucionEstudio,
        calificacionInstitucion,
        agenciaEstudios,
        nombreAgencia,
        calificacionAgencia,
        calificacionExperienciaEstudio,
        fecha,
        titulo,
        usuarioId
        );
    }

    @Override
    public String toString() {
        return "BlogContenidoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (apellido != null ? "apellido=" + apellido + ", " : "") +
                (correo != null ? "correo=" + correo + ", " : "") +
                (nacionalidad != null ? "nacionalidad=" + nacionalidad + ", " : "") +
                (paisEstudio != null ? "paisEstudio=" + paisEstudio + ", " : "") +
                (calificacionPais != null ? "calificacionPais=" + calificacionPais + ", " : "") +
                (ciudadVive != null ? "ciudadVive=" + ciudadVive + ", " : "") +
                (calificacionCiudad != null ? "calificacionCiudad=" + calificacionCiudad + ", " : "") +
                (programaRealizado != null ? "programaRealizado=" + programaRealizado + ", " : "") +
                (institucionEstudio != null ? "institucionEstudio=" + institucionEstudio + ", " : "") +
                (calificacionInstitucion != null ? "calificacionInstitucion=" + calificacionInstitucion + ", " : "") +
                (agenciaEstudios != null ? "agenciaEstudios=" + agenciaEstudios + ", " : "") +
                (nombreAgencia != null ? "nombreAgencia=" + nombreAgencia + ", " : "") +
                (calificacionAgencia != null ? "calificacionAgencia=" + calificacionAgencia + ", " : "") +
                (calificacionExperienciaEstudio != null ? "calificacionExperienciaEstudio=" + calificacionExperienciaEstudio + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (titulo != null ? "titulo=" + titulo + ", " : "") +
                (usuarioId != null ? "usuarioId=" + usuarioId + ", " : "") +
            "}";
    }

}
