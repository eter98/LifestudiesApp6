import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IBlogContenido, BlogContenido } from 'app/shared/model/blog-contenido.model';
import { BlogContenidoService } from './blog-contenido.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-blog-contenido-update',
  templateUrl: './blog-contenido-update.component.html'
})
export class BlogContenidoUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    apellido: [],
    correo: [],
    nacionalidad: [],
    paisEstudio: [],
    calificacionPais: [],
    ciudadVive: [],
    calificacionCiudad: [],
    programaRealizado: [],
    institucionEstudio: [],
    calificacionInstitucion: [],
    agenciaEstudios: [],
    nombreAgencia: [],
    calificacionAgencia: [],
    calificacionExperienciaEstudio: [],
    fecha: [],
    titulo: [],
    imagen: [],
    imagenContentType: [],
    contenido: [],
    usuario: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected blogContenidoService: BlogContenidoService,
    protected userService: UserService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ blogContenido }) => {
      this.updateForm(blogContenido);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(blogContenido: IBlogContenido) {
    this.editForm.patchValue({
      id: blogContenido.id,
      nombre: blogContenido.nombre,
      apellido: blogContenido.apellido,
      correo: blogContenido.correo,
      nacionalidad: blogContenido.nacionalidad,
      paisEstudio: blogContenido.paisEstudio,
      calificacionPais: blogContenido.calificacionPais,
      ciudadVive: blogContenido.ciudadVive,
      calificacionCiudad: blogContenido.calificacionCiudad,
      programaRealizado: blogContenido.programaRealizado,
      institucionEstudio: blogContenido.institucionEstudio,
      calificacionInstitucion: blogContenido.calificacionInstitucion,
      agenciaEstudios: blogContenido.agenciaEstudios,
      nombreAgencia: blogContenido.nombreAgencia,
      calificacionAgencia: blogContenido.calificacionAgencia,
      calificacionExperienciaEstudio: blogContenido.calificacionExperienciaEstudio,
      fecha: blogContenido.fecha,
      titulo: blogContenido.titulo,
      imagen: blogContenido.imagen,
      imagenContentType: blogContenido.imagenContentType,
      contenido: blogContenido.contenido,
      usuario: blogContenido.usuario
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
    const blogContenido = this.createFromForm();
    if (blogContenido.id !== undefined) {
      this.subscribeToSaveResponse(this.blogContenidoService.update(blogContenido));
    } else {
      this.subscribeToSaveResponse(this.blogContenidoService.create(blogContenido));
    }
  }

  private createFromForm(): IBlogContenido {
    return {
      ...new BlogContenido(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      apellido: this.editForm.get(['apellido']).value,
      correo: this.editForm.get(['correo']).value,
      nacionalidad: this.editForm.get(['nacionalidad']).value,
      paisEstudio: this.editForm.get(['paisEstudio']).value,
      calificacionPais: this.editForm.get(['calificacionPais']).value,
      ciudadVive: this.editForm.get(['ciudadVive']).value,
      calificacionCiudad: this.editForm.get(['calificacionCiudad']).value,
      programaRealizado: this.editForm.get(['programaRealizado']).value,
      institucionEstudio: this.editForm.get(['institucionEstudio']).value,
      calificacionInstitucion: this.editForm.get(['calificacionInstitucion']).value,
      agenciaEstudios: this.editForm.get(['agenciaEstudios']).value,
      nombreAgencia: this.editForm.get(['nombreAgencia']).value,
      calificacionAgencia: this.editForm.get(['calificacionAgencia']).value,
      calificacionExperienciaEstudio: this.editForm.get(['calificacionExperienciaEstudio']).value,
      fecha: this.editForm.get(['fecha']).value,
      titulo: this.editForm.get(['titulo']).value,
      imagenContentType: this.editForm.get(['imagenContentType']).value,
      imagen: this.editForm.get(['imagen']).value,
      contenido: this.editForm.get(['contenido']).value,
      usuario: this.editForm.get(['usuario']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlogContenido>>) {
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
