import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { ICRM, CRM } from 'app/shared/model/crm.model';
import { CRMService } from './crm.service';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { PerfilUsuarioService } from 'app/entities/perfil-usuario';
import { IPasoCRM } from 'app/shared/model/paso-crm.model';
import { PasoCRMService } from 'app/entities/paso-crm';
import { IAgencia } from 'app/shared/model/agencia.model';
import { AgenciaService } from 'app/entities/agencia';
import { ICotizacion } from 'app/shared/model/cotizacion.model';
import { CotizacionService } from 'app/entities/cotizacion';

@Component({
  selector: 'jhi-crm-update',
  templateUrl: './crm-update.component.html'
})
export class CRMUpdateComponent implements OnInit {
  isSaving: boolean;

  perfilusuarios: IPerfilUsuario[];

  pasocrms: IPasoCRM[];

  agencias: IAgencia[];

  cotizacions: ICotizacion[];

  editForm = this.fb.group({
    id: [],
    fecha: [],
    descripcion: [],
    documento: [],
    documentoContentType: [],
    asesor: [],
    estado: [],
    tipoLead: [],
    comentarios: [],
    cerrado: [],
    usuario: [],
    proceso: [],
    agencia: [],
    cotizacion: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected cRMService: CRMService,
    protected perfilUsuarioService: PerfilUsuarioService,
    protected pasoCRMService: PasoCRMService,
    protected agenciaService: AgenciaService,
    protected cotizacionService: CotizacionService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ cRM }) => {
      this.updateForm(cRM);
    });
    this.perfilUsuarioService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPerfilUsuario[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPerfilUsuario[]>) => response.body)
      )
      .subscribe((res: IPerfilUsuario[]) => (this.perfilusuarios = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.pasoCRMService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPasoCRM[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPasoCRM[]>) => response.body)
      )
      .subscribe((res: IPasoCRM[]) => (this.pasocrms = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.agenciaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IAgencia[]>) => mayBeOk.ok),
        map((response: HttpResponse<IAgencia[]>) => response.body)
      )
      .subscribe((res: IAgencia[]) => (this.agencias = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.cotizacionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICotizacion[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICotizacion[]>) => response.body)
      )
      .subscribe((res: ICotizacion[]) => (this.cotizacions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(cRM: ICRM) {
    this.editForm.patchValue({
      id: cRM.id,
      fecha: cRM.fecha != null ? cRM.fecha.format(DATE_TIME_FORMAT) : null,
      descripcion: cRM.descripcion,
      documento: cRM.documento,
      documentoContentType: cRM.documentoContentType,
      asesor: cRM.asesor,
      estado: cRM.estado,
      tipoLead: cRM.tipoLead,
      comentarios: cRM.comentarios,
      cerrado: cRM.cerrado,
      usuario: cRM.usuario,
      proceso: cRM.proceso,
      agencia: cRM.agencia,
      cotizacion: cRM.cotizacion
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const cRM = this.createFromForm();
    if (cRM.id !== undefined) {
      this.subscribeToSaveResponse(this.cRMService.update(cRM));
    } else {
      this.subscribeToSaveResponse(this.cRMService.create(cRM));
    }
  }

  private createFromForm(): ICRM {
    return {
      ...new CRM(),
      id: this.editForm.get(['id']).value,
      fecha: this.editForm.get(['fecha']).value != null ? moment(this.editForm.get(['fecha']).value, DATE_TIME_FORMAT) : undefined,
      descripcion: this.editForm.get(['descripcion']).value,
      documentoContentType: this.editForm.get(['documentoContentType']).value,
      documento: this.editForm.get(['documento']).value,
      asesor: this.editForm.get(['asesor']).value,
      estado: this.editForm.get(['estado']).value,
      tipoLead: this.editForm.get(['tipoLead']).value,
      comentarios: this.editForm.get(['comentarios']).value,
      cerrado: this.editForm.get(['cerrado']).value,
      usuario: this.editForm.get(['usuario']).value,
      proceso: this.editForm.get(['proceso']).value,
      agencia: this.editForm.get(['agencia']).value,
      cotizacion: this.editForm.get(['cotizacion']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICRM>>) {
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

  trackPasoCRMById(index: number, item: IPasoCRM) {
    return item.id;
  }

  trackAgenciaById(index: number, item: IAgencia) {
    return item.id;
  }

  trackCotizacionById(index: number, item: ICotizacion) {
    return item.id;
  }
}
