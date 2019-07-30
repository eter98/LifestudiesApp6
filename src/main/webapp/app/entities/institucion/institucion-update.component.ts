import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IInstitucion, Institucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from './institucion.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad';

@Component({
  selector: 'jhi-institucion-update',
  templateUrl: './institucion-update.component.html'
})
export class InstitucionUpdateComponent implements OnInit {
  isSaving: boolean;

  ciudads: ICiudad[];
  fechaInicialDp: any;

  editForm = this.fb.group({
    id: [],
    codigo: [],
    nombre: [],
    descripcion: [],
    direccion: [],
    website: [],
    contacto: [],
    representante: [],
    skype: [],
    telefono: [],
    estatus: [],
    tipoProgramas: [],
    programaEstandar: [],
    programaIntensivo: [],
    programaNegocios: [],
    preparacionExamen: [],
    programaAcademico: [],
    descuento: [],
    inscripcion: [],
    materiales: [],
    seguroMedico: [],
    alojamientoSencillo: [],
    alojamientoDoble: [],
    transporteAeropuerto: [],
    tipoCurso: [],
    temporadaAlta: [],
    temporadaBaja: [],
    fechaInicial: [],
    horarios: [],
    instalaciones: [],
    nacionalidad: [],
    logo: [],
    logoContentType: [],
    foto1: [],
    foto1ContentType: [],
    foto2: [],
    foto2ContentType: [],
    foto3: [],
    foto3ContentType: [],
    ciudad: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected institucionService: InstitucionService,
    protected ciudadService: CiudadService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ institucion }) => {
      this.updateForm(institucion);
    });
    this.ciudadService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICiudad[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICiudad[]>) => response.body)
      )
      .subscribe((res: ICiudad[]) => (this.ciudads = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(institucion: IInstitucion) {
    this.editForm.patchValue({
      id: institucion.id,
      codigo: institucion.codigo,
      nombre: institucion.nombre,
      descripcion: institucion.descripcion,
      direccion: institucion.direccion,
      website: institucion.website,
      contacto: institucion.contacto,
      representante: institucion.representante,
      skype: institucion.skype,
      telefono: institucion.telefono,
      estatus: institucion.estatus,
      tipoProgramas: institucion.tipoProgramas,
      programaEstandar: institucion.programaEstandar,
      programaIntensivo: institucion.programaIntensivo,
      programaNegocios: institucion.programaNegocios,
      preparacionExamen: institucion.preparacionExamen,
      programaAcademico: institucion.programaAcademico,
      descuento: institucion.descuento,
      inscripcion: institucion.inscripcion,
      materiales: institucion.materiales,
      seguroMedico: institucion.seguroMedico,
      alojamientoSencillo: institucion.alojamientoSencillo,
      alojamientoDoble: institucion.alojamientoDoble,
      transporteAeropuerto: institucion.transporteAeropuerto,
      tipoCurso: institucion.tipoCurso,
      temporadaAlta: institucion.temporadaAlta,
      temporadaBaja: institucion.temporadaBaja,
      fechaInicial: institucion.fechaInicial,
      horarios: institucion.horarios,
      instalaciones: institucion.instalaciones,
      nacionalidad: institucion.nacionalidad,
      logo: institucion.logo,
      logoContentType: institucion.logoContentType,
      foto1: institucion.foto1,
      foto1ContentType: institucion.foto1ContentType,
      foto2: institucion.foto2,
      foto2ContentType: institucion.foto2ContentType,
      foto3: institucion.foto3,
      foto3ContentType: institucion.foto3ContentType,
      ciudad: institucion.ciudad
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
    const institucion = this.createFromForm();
    if (institucion.id !== undefined) {
      this.subscribeToSaveResponse(this.institucionService.update(institucion));
    } else {
      this.subscribeToSaveResponse(this.institucionService.create(institucion));
    }
  }

  private createFromForm(): IInstitucion {
    return {
      ...new Institucion(),
      id: this.editForm.get(['id']).value,
      codigo: this.editForm.get(['codigo']).value,
      nombre: this.editForm.get(['nombre']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      direccion: this.editForm.get(['direccion']).value,
      website: this.editForm.get(['website']).value,
      contacto: this.editForm.get(['contacto']).value,
      representante: this.editForm.get(['representante']).value,
      skype: this.editForm.get(['skype']).value,
      telefono: this.editForm.get(['telefono']).value,
      estatus: this.editForm.get(['estatus']).value,
      tipoProgramas: this.editForm.get(['tipoProgramas']).value,
      programaEstandar: this.editForm.get(['programaEstandar']).value,
      programaIntensivo: this.editForm.get(['programaIntensivo']).value,
      programaNegocios: this.editForm.get(['programaNegocios']).value,
      preparacionExamen: this.editForm.get(['preparacionExamen']).value,
      programaAcademico: this.editForm.get(['programaAcademico']).value,
      descuento: this.editForm.get(['descuento']).value,
      inscripcion: this.editForm.get(['inscripcion']).value,
      materiales: this.editForm.get(['materiales']).value,
      seguroMedico: this.editForm.get(['seguroMedico']).value,
      alojamientoSencillo: this.editForm.get(['alojamientoSencillo']).value,
      alojamientoDoble: this.editForm.get(['alojamientoDoble']).value,
      transporteAeropuerto: this.editForm.get(['transporteAeropuerto']).value,
      tipoCurso: this.editForm.get(['tipoCurso']).value,
      temporadaAlta: this.editForm.get(['temporadaAlta']).value,
      temporadaBaja: this.editForm.get(['temporadaBaja']).value,
      fechaInicial: this.editForm.get(['fechaInicial']).value,
      horarios: this.editForm.get(['horarios']).value,
      instalaciones: this.editForm.get(['instalaciones']).value,
      nacionalidad: this.editForm.get(['nacionalidad']).value,
      logoContentType: this.editForm.get(['logoContentType']).value,
      logo: this.editForm.get(['logo']).value,
      foto1ContentType: this.editForm.get(['foto1ContentType']).value,
      foto1: this.editForm.get(['foto1']).value,
      foto2ContentType: this.editForm.get(['foto2ContentType']).value,
      foto2: this.editForm.get(['foto2']).value,
      foto3ContentType: this.editForm.get(['foto3ContentType']).value,
      foto3: this.editForm.get(['foto3']).value,
      ciudad: this.editForm.get(['ciudad']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstitucion>>) {
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

  trackCiudadById(index: number, item: ICiudad) {
    return item.id;
  }
}
