import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ICotizacion, Cotizacion } from 'app/shared/model/cotizacion.model';
import { CotizacionService } from './cotizacion.service';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { PerfilUsuarioService } from 'app/entities/perfil-usuario';
import { IProgramas } from 'app/shared/model/programas.model';
import { ProgramasService } from 'app/entities/programas';

@Component({
  selector: 'jhi-cotizacion-update',
  templateUrl: './cotizacion-update.component.html'
})
export class CotizacionUpdateComponent implements OnInit {
  isSaving: boolean;

  perfilusuarios: IPerfilUsuario[];

  programas: IProgramas[];
  fechaViajeDp: any;

  editForm = this.fb.group({
    id: [],
    fechaViaje: [],
    alojamiento: [],
    pasajeAereo: [],
    transpAeropuerto: [],
    usario: [],
    curso: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected cotizacionService: CotizacionService,
    protected perfilUsuarioService: PerfilUsuarioService,
    protected programasService: ProgramasService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ cotizacion }) => {
      this.updateForm(cotizacion);
    });
    this.perfilUsuarioService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPerfilUsuario[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPerfilUsuario[]>) => response.body)
      )
      .subscribe((res: IPerfilUsuario[]) => (this.perfilusuarios = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.programasService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProgramas[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProgramas[]>) => response.body)
      )
      .subscribe((res: IProgramas[]) => (this.programas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(cotizacion: ICotizacion) {
    this.editForm.patchValue({
      id: cotizacion.id,
      fechaViaje: cotizacion.fechaViaje,
      alojamiento: cotizacion.alojamiento,
      pasajeAereo: cotizacion.pasajeAereo,
      transpAeropuerto: cotizacion.transpAeropuerto,
      usario: cotizacion.usario,
      curso: cotizacion.curso
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const cotizacion = this.createFromForm();
    if (cotizacion.id !== undefined) {
      this.subscribeToSaveResponse(this.cotizacionService.update(cotizacion));
    } else {
      this.subscribeToSaveResponse(this.cotizacionService.create(cotizacion));
    }
  }

  private createFromForm(): ICotizacion {
    return {
      ...new Cotizacion(),
      id: this.editForm.get(['id']).value,
      fechaViaje: this.editForm.get(['fechaViaje']).value,
      alojamiento: this.editForm.get(['alojamiento']).value,
      pasajeAereo: this.editForm.get(['pasajeAereo']).value,
      transpAeropuerto: this.editForm.get(['transpAeropuerto']).value,
      usario: this.editForm.get(['usario']).value,
      curso: this.editForm.get(['curso']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICotizacion>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackPerfilUsuarioById(index: number, item: IPerfilUsuario) {
    return item.id;
  }

  trackProgramasById(index: number, item: IProgramas) {
    return item.id;
  }
}
