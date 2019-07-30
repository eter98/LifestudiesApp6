package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.application.domain.enumeration.Asesord;
import io.github.jhipster.application.domain.enumeration.Estadod;
import io.github.jhipster.application.domain.enumeration.TipoLeadd;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.CRM} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.CRMResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /crms?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CRMCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Asesord
     */
    public static class AsesordFilter extends Filter<Asesord> {

        public AsesordFilter() {
        }

        public AsesordFilter(AsesordFilter filter) {
            super(filter);
        }

        @Override
        public AsesordFilter copy() {
            return new AsesordFilter(this);
        }

    }
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
    /**
     * Class for filtering TipoLeadd
     */
    public static class TipoLeaddFilter extends Filter<TipoLeadd> {

        public TipoLeaddFilter() {
        }

        public TipoLeaddFilter(TipoLeaddFilter filter) {
            super(filter);
        }

        @Override
        public TipoLeaddFilter copy() {
            return new TipoLeaddFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter fecha;

    private StringFilter descripcion;

    private AsesordFilter asesor;

    private EstadodFilter estado;

    private TipoLeaddFilter tipoLead;

    private BooleanFilter cerrado;

    private LongFilter usuarioId;

    private LongFilter procesoId;

    private LongFilter agenciaId;

    private LongFilter cotizacionId;

    public CRMCriteria(){
    }

    public CRMCriteria(CRMCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.descripcion = other.descripcion == null ? null : other.descripcion.copy();
        this.asesor = other.asesor == null ? null : other.asesor.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.tipoLead = other.tipoLead == null ? null : other.tipoLead.copy();
        this.cerrado = other.cerrado == null ? null : other.cerrado.copy();
        this.usuarioId = other.usuarioId == null ? null : other.usuarioId.copy();
        this.procesoId = other.procesoId == null ? null : other.procesoId.copy();
        this.agenciaId = other.agenciaId == null ? null : other.agenciaId.copy();
        this.cotizacionId = other.cotizacionId == null ? null : other.cotizacionId.copy();
    }

    @Override
    public CRMCriteria copy() {
        return new CRMCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getFecha() {
        return fecha;
    }

    public void setFecha(ZonedDateTimeFilter fecha) {
        this.fecha = fecha;
    }

    public StringFilter getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(StringFilter descripcion) {
        this.descripcion = descripcion;
    }

    public AsesordFilter getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesordFilter asesor) {
        this.asesor = asesor;
    }

    public EstadodFilter getEstado() {
        return estado;
    }

    public void setEstado(EstadodFilter estado) {
        this.estado = estado;
    }

    public TipoLeaddFilter getTipoLead() {
        return tipoLead;
    }

    public void setTipoLead(TipoLeaddFilter tipoLead) {
        this.tipoLead = tipoLead;
    }

    public BooleanFilter getCerrado() {
        return cerrado;
    }

    public void setCerrado(BooleanFilter cerrado) {
        this.cerrado = cerrado;
    }

    public LongFilter getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(LongFilter usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LongFilter getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(LongFilter procesoId) {
        this.procesoId = procesoId;
    }

    public LongFilter getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(LongFilter agenciaId) {
        this.agenciaId = agenciaId;
    }

    public LongFilter getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(LongFilter cotizacionId) {
        this.cotizacionId = cotizacionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CRMCriteria that = (CRMCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(descripcion, that.descripcion) &&
            Objects.equals(asesor, that.asesor) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(tipoLead, that.tipoLead) &&
            Objects.equals(cerrado, that.cerrado) &&
            Objects.equals(usuarioId, that.usuarioId) &&
            Objects.equals(procesoId, that.procesoId) &&
            Objects.equals(agenciaId, that.agenciaId) &&
            Objects.equals(cotizacionId, that.cotizacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fecha,
        descripcion,
        asesor,
        estado,
        tipoLead,
        cerrado,
        usuarioId,
        procesoId,
        agenciaId,
        cotizacionId
        );
    }

    @Override
    public String toString() {
        return "CRMCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fecha != null ? "fecha=" + fecha + ", " : "") +
                (descripcion != null ? "descripcion=" + descripcion + ", " : "") +
                (asesor != null ? "asesor=" + asesor + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (tipoLead != null ? "tipoLead=" + tipoLead + ", " : "") +
                (cerrado != null ? "cerrado=" + cerrado + ", " : "") +
                (usuarioId != null ? "usuarioId=" + usuarioId + ", " : "") +
                (procesoId != null ? "procesoId=" + procesoId + ", " : "") +
                (agenciaId != null ? "agenciaId=" + agenciaId + ", " : "") +
                (cotizacionId != null ? "cotizacionId=" + cotizacionId + ", " : "") +
            "}";
    }

}
