import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IAgencia, Agencia } from 'app/shared/model/agencia.model';
import { AgenciaService } from './agencia.service';
import { IPais } from 'app/shared/model/pais.model';
import { PaisService } from 'app/entities/pais';

@Component({
  selector: 'jhi-agencia-update',
  templateUrl: './agencia-update.component.html'
})
export class AgenciaUpdateComponent implements OnInit {
  isSaving: boolean;

  pais: IPais[];

  editForm = this.fb.group({
    id: [],
    nombreAgencia: [],
    codigo: [],
    descripcion: [],
    encargado: [],
    direccion: [],
    telefono: [],
    email: [],
    whatsapp: [],
    asesor1: [],
    fotoAsesor1: [],
    fotoAsesor1ContentType: [],
    asesor2: [],
    fotoAsesor2: [],
    fotoAsesor2ContentType: [],
    asesor3: [],
    fotoAsesor3: [],
    fotoAsesor3ContentType: [],
    logo: [],
    logoContentType: [],
    foto1: [],
    foto1ContentType: [],
    foto2: [],
    foto2ContentType: [],
    sede1: [],
    sede2: [],
    pais: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected agenciaService: AgenciaService,
    protected paisService: PaisService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ agencia }) => {
      this.updateForm(agencia);
    });
    this.paisService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPais[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPais[]>) => response.body)
      )
      .subscribe((res: IPais[]) => (this.pais = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(agencia: IAgencia) {
    this.editForm.patchValue({
      id: agencia.id,
      nombreAgencia: agencia.nombreAgencia,
      codigo: agencia.codigo,
      descripcion: agencia.descripcion,
      encargado: agencia.encargado,
      direccion: agencia.direccion,
      telefono: agencia.telefono,
      email: agencia.email,
      whatsapp: agencia.whatsapp,
      asesor1: agencia.asesor1,
      fotoAsesor1: agencia.fotoAsesor1,
      fotoAsesor1ContentType: agencia.fotoAsesor1ContentType,
      asesor2: agencia.asesor2,
      fotoAsesor2: agencia.fotoAsesor2,
      fotoAsesor2ContentType: agencia.fotoAsesor2ContentType,
      asesor3: agencia.asesor3,
      fotoAsesor3: agencia.fotoAsesor3,
      fotoAsesor3ContentType: agencia.fotoAsesor3ContentType,
      logo: agencia.logo,
      logoContentType: agencia.logoContentType,
      foto1: agencia.foto1,
      foto1ContentType: agencia.foto1ContentType,
      foto2: agencia.foto2,
      foto2ContentType: agencia.foto2ContentType,
      sede1: agencia.sede1,
      sede2: agencia.sede2,
      pais: agencia.pais
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
    const agencia = this.createFromForm();
    if (agencia.id !== undefined) {
      this.subscribeToSaveResponse(this.agenciaService.update(agencia));
    } else {
      this.subscribeToSaveResponse(this.agenciaService.create(agencia));
    }
  }

  private createFromForm(): IAgencia {
    return {
      ...new Agencia(),
      id: this.editForm.get(['id']).value,
      nombreAgencia: this.editForm.get(['nombreAgencia']).value,
      codigo: this.editForm.get(['codigo']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      encargado: this.editForm.get(['encargado']).value,
      direccion: this.editForm.get(['direccion']).value,
      telefono: this.editForm.get(['telefono']).value,
      email: this.editForm.get(['email']).value,
      whatsapp: this.editForm.get(['whatsapp']).value,
      asesor1: this.editForm.get(['asesor1']).value,
      fotoAsesor1ContentType: this.editForm.get(['fotoAsesor1ContentType']).value,
      fotoAsesor1: this.editForm.get(['fotoAsesor1']).value,
      asesor2: this.editForm.get(['asesor2']).value,
      fotoAsesor2ContentType: this.editForm.get(['fotoAsesor2ContentType']).value,
      fotoAsesor2: this.editForm.get(['fotoAsesor2']).value,
      asesor3: this.editForm.get(['asesor3']).value,
      fotoAsesor3ContentType: this.editForm.get(['fotoAsesor3ContentType']).value,
      fotoAsesor3: this.editForm.get(['fotoAsesor3']).value,
      logoContentType: this.editForm.get(['logoContentType']).value,
      logo: this.editForm.get(['logo']).value,
      foto1ContentType: this.editForm.get(['foto1ContentType']).value,
      foto1: this.editForm.get(['foto1']).value,
      foto2ContentType: this.editForm.get(['foto2ContentType']).value,
      foto2: this.editForm.get(['foto2']).value,
      sede1: this.editForm.get(['sede1']).value,
      sede2: this.editForm.get(['sede2']).value,
      pais: this.editForm.get(['pais']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgencia>>) {
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

  trackPaisById(index: number, item: IPais) {
    return item.id;
  }
}
