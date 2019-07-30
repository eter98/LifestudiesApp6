import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPerfilUsuario, PerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { PerfilUsuarioService } from './perfil-usuario.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-perfil-usuario-update',
  templateUrl: './perfil-usuario-update.component.html'
})
export class PerfilUsuarioUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];
  fechaNacimientoDp: any;
  fechaInicioDp: any;

  editForm = this.fb.group({
    id: [],
    fechaNacimiento: [],
    identificacion: [],
    mail: [],
    area: [],
    telefono: [],
    nivelAcademico: [],
    areaAcademica: [],
    terminoAcademico: [],
    puntajeICFES: [],
    promedioAcademico: [],
    dominioIdioma: [],
    idiomas: [],
    examenIdioma: [],
    examenRealizado: [],
    puntajeIdioma: [],
    becaOtorgada: [],
    tipoBeca: [],
    becaInstitucion: [],
    grupoSocial: [],
    fundacion: [],
    monitor: [],
    monitorMateria: [],
    logrosAcademicos: [],
    experienciaLaboral: [],
    areaLaboral: [],
    programarealizar: [],
    programaArea: [],
    fechaInicio: [],
    programaEncontrado: [],
    nombrePrograma: [],
    universidad: [],
    pais: [],
    merecedorBeca: [],
    foto: [],
    fotoContentType: [],
    usuario: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected perfilUsuarioService: PerfilUsuarioService,
    protected userService: UserService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ perfilUsuario }) => {
      this.updateForm(perfilUsuario);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(perfilUsuario: IPerfilUsuario) {
    this.editForm.patchValue({
      id: perfilUsuario.id,
      fechaNacimiento: perfilUsuario.fechaNacimiento,
      identificacion: perfilUsuario.identificacion,
      mail: perfilUsuario.mail,
      area: perfilUsuario.area,
      telefono: perfilUsuario.telefono,
      nivelAcademico: perfilUsuario.nivelAcademico,
      areaAcademica: perfilUsuario.areaAcademica,
      terminoAcademico: perfilUsuario.terminoAcademico,
      puntajeICFES: perfilUsuario.puntajeICFES,
      promedioAcademico: perfilUsuario.promedioAcademico,
      dominioIdioma: perfilUsuario.dominioIdioma,
      idiomas: perfilUsuario.idiomas,
      examenIdioma: perfilUsuario.examenIdioma,
      examenRealizado: perfilUsuario.examenRealizado,
      puntajeIdioma: perfilUsuario.puntajeIdioma,
      becaOtorgada: perfilUsuario.becaOtorgada,
      tipoBeca: perfilUsuario.tipoBeca,
      becaInstitucion: perfilUsuario.becaInstitucion,
      grupoSocial: perfilUsuario.grupoSocial,
      fundacion: perfilUsuario.fundacion,
      monitor: perfilUsuario.monitor,
      monitorMateria: perfilUsuario.monitorMateria,
      logrosAcademicos: perfilUsuario.logrosAcademicos,
      experienciaLaboral: perfilUsuario.experienciaLaboral,
      areaLaboral: perfilUsuario.areaLaboral,
      programarealizar: perfilUsuario.programarealizar,
      programaArea: perfilUsuario.programaArea,
      fechaInicio: perfilUsuario.fechaInicio,
      programaEncontrado: perfilUsuario.programaEncontrado,
      nombrePrograma: perfilUsuario.nombrePrograma,
      universidad: perfilUsuario.universidad,
      pais: perfilUsuario.pais,
      merecedorBeca: perfilUsuario.merecedorBeca,
      foto: perfilUsuario.foto,
      fotoContentType: perfilUsuario.fotoContentType,
      usuario: perfilUsuario.usuario
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
    const perfilUsuario = this.createFromForm();
    if (perfilUsuario.id !== undefined) {
      this.subscribeToSaveResponse(this.perfilUsuarioService.update(perfilUsuario));
    } else {
      this.subscribeToSaveResponse(this.perfilUsuarioService.create(perfilUsuario));
    }
  }

  private createFromForm(): IPerfilUsuario {
    return {
      ...new PerfilUsuario(),
      id: this.editForm.get(['id']).value,
      fechaNacimiento: this.editForm.get(['fechaNacimiento']).value,
      identificacion: this.editForm.get(['identificacion']).value,
      mail: this.editForm.get(['mail']).value,
      area: this.editForm.get(['area']).value,
      telefono: this.editForm.get(['telefono']).value,
      nivelAcademico: this.editForm.get(['nivelAcademico']).value,
      areaAcademica: this.editForm.get(['areaAcademica']).value,
      terminoAcademico: this.editForm.get(['terminoAcademico']).value,
      puntajeICFES: this.editForm.get(['puntajeICFES']).value,
      promedioAcademico: this.editForm.get(['promedioAcademico']).value,
      dominioIdioma: this.editForm.get(['dominioIdioma']).value,
      idiomas: this.editForm.get(['idiomas']).value,
      examenIdioma: this.editForm.get(['examenIdioma']).value,
      examenRealizado: this.editForm.get(['examenRealizado']).value,
      puntajeIdioma: this.editForm.get(['puntajeIdioma']).value,
      becaOtorgada: this.editForm.get(['becaOtorgada']).value,
      tipoBeca: this.editForm.get(['tipoBeca']).value,
      becaInstitucion: this.editForm.get(['becaInstitucion']).value,
      grupoSocial: this.editForm.get(['grupoSocial']).value,
      fundacion: this.editForm.get(['fundacion']).value,
      monitor: this.editForm.get(['monitor']).value,
      monitorMateria: this.editForm.get(['monitorMateria']).value,
      logrosAcademicos: this.editForm.get(['logrosAcademicos']).value,
      experienciaLaboral: this.editForm.get(['experienciaLaboral']).value,
      areaLaboral: this.editForm.get(['areaLaboral']).value,
      programarealizar: this.editForm.get(['programarealizar']).value,
      programaArea: this.editForm.get(['programaArea']).value,
      fechaInicio: this.editForm.get(['fechaInicio']).value,
      programaEncontrado: this.editForm.get(['programaEncontrado']).value,
      nombrePrograma: this.editForm.get(['nombrePrograma']).value,
      universidad: this.editForm.get(['universidad']).value,
      pais: this.editForm.get(['pais']).value,
      merecedorBeca: this.editForm.get(['merecedorBeca']).value,
      fotoContentType: this.editForm.get(['fotoContentType']).value,
      foto: this.editForm.get(['foto']).value,
      usuario: this.editForm.get(['usuario']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfilUsuario>>) {
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

  trackUserById(index: number, item: IUser) {
    return item.id;
  }
}
