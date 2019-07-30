import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IExperiencia, Experiencia } from 'app/shared/model/experiencia.model';
import { ExperienciaService } from './experiencia.service';

@Component({
  selector: 'jhi-experiencia-update',
  templateUrl: './experiencia-update.component.html'
})
export class ExperienciaUpdateComponent implements OnInit {
  isSaving: boolean;
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    identificacion: [],
    nombre: [],
    apellido: [],
    mail: [],
    area: [],
    telefono: [],
    nacionalidad: [],
    paisDestino: [],
    calificaPais: [],
    programa: [],
    institucion: [],
    calificaInstitucion: [],
    agencia: [],
    calificaAgencia: [],
    fecha: [],
    contenido: [],
    foto: [],
    fotoContentType: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected experienciaService: ExperienciaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ experiencia }) => {
      this.updateForm(experiencia);
    });
  }

  updateForm(experiencia: IExperiencia) {
    this.editForm.patchValue({
      id: experiencia.id,
      identificacion: experiencia.identificacion,
      nombre: experiencia.nombre,
      apellido: experiencia.apellido,
      mail: experiencia.mail,
      area: experiencia.area,
      telefono: experiencia.telefono,
      nacionalidad: experiencia.nacionalidad,
      paisDestino: experiencia.paisDestino,
      calificaPais: experiencia.calificaPais,
      programa: experiencia.programa,
      institucion: experiencia.institucion,
      calificaInstitucion: experiencia.calificaInstitucion,
      agencia: experiencia.agencia,
      calificaAgencia: experiencia.calificaAgencia,
      fecha: experiencia.fecha,
      contenido: experiencia.contenido,
      foto: experiencia.foto,
      fotoContentType: experiencia.fotoContentType
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
    const experiencia = this.createFromForm();
    if (experiencia.id !== undefined) {
      this.subscribeToSaveResponse(this.experienciaService.update(experiencia));
    } else {
      this.subscribeToSaveResponse(this.experienciaService.create(experiencia));
    }
  }

  private createFromForm(): IExperiencia {
    return {
      ...new Experiencia(),
      id: this.editForm.get(['id']).value,
      identificacion: this.editForm.get(['identificacion']).value,
      nombre: this.editForm.get(['nombre']).value,
      apellido: this.editForm.get(['apellido']).value,
      mail: this.editForm.get(['mail']).value,
      area: this.editForm.get(['area']).value,
      telefono: this.editForm.get(['telefono']).value,
      nacionalidad: this.editForm.get(['nacionalidad']).value,
      paisDestino: this.editForm.get(['paisDestino']).value,
      calificaPais: this.editForm.get(['calificaPais']).value,
      programa: this.editForm.get(['programa']).value,
      institucion: this.editForm.get(['institucion']).value,
      calificaInstitucion: this.editForm.get(['calificaInstitucion']).value,
      agencia: this.editForm.get(['agencia']).value,
      calificaAgencia: this.editForm.get(['calificaAgencia']).value,
      fecha: this.editForm.get(['fecha']).value,
      contenido: this.editForm.get(['contenido']).value,
      fotoContentType: this.editForm.get(['fotoContentType']).value,
      foto: this.editForm.get(['foto']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExperiencia>>) {
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
}
