import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IComentarioBlog, ComentarioBlog } from 'app/shared/model/comentario-blog.model';
import { ComentarioBlogService } from './comentario-blog.service';
import { IUser, UserService } from 'app/core';
import { IBlogContenido } from 'app/shared/model/blog-contenido.model';
import { BlogContenidoService } from 'app/entities/blog-contenido';

@Component({
  selector: 'jhi-comentario-blog-update',
  templateUrl: './comentario-blog-update.component.html'
})
export class ComentarioBlogUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  blogcontenidos: IBlogContenido[];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    comentario: [],
    fecha: [],
    calificacion: [],
    usuario: [],
    tituloBlog: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected comentarioBlogService: ComentarioBlogService,
    protected userService: UserService,
    protected blogContenidoService: BlogContenidoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ comentarioBlog }) => {
      this.updateForm(comentarioBlog);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.blogContenidoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBlogContenido[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlogContenido[]>) => response.body)
      )
      .subscribe((res: IBlogContenido[]) => (this.blogcontenidos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(comentarioBlog: IComentarioBlog) {
    this.editForm.patchValue({
      id: comentarioBlog.id,
      comentario: comentarioBlog.comentario,
      fecha: comentarioBlog.fecha,
      calificacion: comentarioBlog.calificacion,
      usuario: comentarioBlog.usuario,
      tituloBlog: comentarioBlog.tituloBlog
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

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const comentarioBlog = this.createFromForm();
    if (comentarioBlog.id !== undefined) {
      this.subscribeToSaveResponse(this.comentarioBlogService.update(comentarioBlog));
    } else {
      this.subscribeToSaveResponse(this.comentarioBlogService.create(comentarioBlog));
    }
  }

  private createFromForm(): IComentarioBlog {
    return {
      ...new ComentarioBlog(),
      id: this.editForm.get(['id']).value,
      comentario: this.editForm.get(['comentario']).value,
      fecha: this.editForm.get(['fecha']).value,
      calificacion: this.editForm.get(['calificacion']).value,
      usuario: this.editForm.get(['usuario']).value,
      tituloBlog: this.editForm.get(['tituloBlog']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComentarioBlog>>) {
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

  trackBlogContenidoById(index: number, item: IBlogContenido) {
    return item.id;
  }
}
