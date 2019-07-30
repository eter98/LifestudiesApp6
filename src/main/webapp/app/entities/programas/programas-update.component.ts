import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProgramas, Programas } from 'app/shared/model/programas.model';
import { ProgramasService } from './programas.service';
import { IInstitucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from 'app/entities/institucion';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad';
import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';
import { TipoProgramaService } from 'app/entities/tipo-programa';

@Component({
  selector: 'jhi-programas-update',
  templateUrl: './programas-update.component.html'
})
export class ProgramasUpdateComponent implements OnInit {
  isSaving: boolean;

  institucions: IInstitucion[];

  ciudads: ICiudad[];

  tipoprogramas: ITipoPrograma[];

  editForm = this.fb.group({
    id: [],
    registro: [],
    moneda: [],
    curso: [],
    horario: [],
    frecuencia: [],
    duracion: [],
    foto1: [],
    foto1ContentType: [],
    foto2: [],
    foto2ContentType: [],
    institucion: [],
    ciudad: [],
    tipoPrograma: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected programasService: ProgramasService,
    protected institucionService: InstitucionService,
    protected ciudadService: CiudadService,
    protected tipoProgramaService: TipoProgramaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ programas }) => {
      this.updateForm(programas);
    });
    this.institucionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IInstitucion[]>) => mayBeOk.ok),
        map((response: HttpResponse<IInstitucion[]>) => response.body)
      )
      .subscribe((res: IInstitucion[]) => (this.institucions = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.ciudadService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICiudad[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICiudad[]>) => response.body)
      )
      .subscribe((res: ICiudad[]) => (this.ciudads = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.tipoProgramaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITipoPrograma[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITipoPrograma[]>) => response.body)
      )
      .subscribe((res: ITipoPrograma[]) => (this.tipoprogramas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(programas: IProgramas) {
    this.editForm.patchValue({
      id: programas.id,
      registro: programas.registro,
      moneda: programas.moneda,
      curso: programas.curso,
      horario: programas.horario,
      frecuencia: programas.frecuencia,
      duracion: programas.duracion,
      foto1: programas.foto1,
      foto1ContentType: programas.foto1ContentType,
      foto2: programas.foto2,
      foto2ContentType: programas.foto2ContentType,
      institucion: programas.institucion,
      ciudad: programas.ciudad,
      tipoPrograma: programas.tipoPrograma
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
    const programas = this.createFromForm();
    if (programas.id !== undefined) {
      this.subscribeToSaveResponse(this.programasService.update(programas));
    } else {
      this.subscribeToSaveResponse(this.programasService.create(programas));
    }
  }

  private createFromForm(): IProgramas {
    return {
      ...new Programas(),
      id: this.editForm.get(['id']).value,
      registro: this.editForm.get(['registro']).value,
      moneda: this.editForm.get(['moneda']).value,
      curso: this.editForm.get(['curso']).value,
      horario: this.editForm.get(['horario']).value,
      frecuencia: this.editForm.get(['frecuencia']).value,
      duracion: this.editForm.get(['duracion']).value,
      foto1ContentType: this.editForm.get(['foto1ContentType']).value,
      foto1: this.editForm.get(['foto1']).value,
      foto2ContentType: this.editForm.get(['foto2ContentType']).value,
      foto2: this.editForm.get(['foto2']).value,
      institucion: this.editForm.get(['institucion']).value,
      ciudad: this.editForm.get(['ciudad']).value,
      tipoPrograma: this.editForm.get(['tipoPrograma']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProgramas>>) {
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

  trackInstitucionById(index: number, item: IInstitucion) {
    return item.id;
  }

  trackCiudadById(index: number, item: ICiudad) {
    return item.id;
  }

  trackTipoProgramaById(index: number, item: ITipoPrograma) {
    return item.id;
  }
}
