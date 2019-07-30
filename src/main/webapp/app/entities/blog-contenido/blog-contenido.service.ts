import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlogContenido } from 'app/shared/model/blog-contenido.model';

type EntityResponseType = HttpResponse<IBlogContenido>;
type EntityArrayResponseType = HttpResponse<IBlogContenido[]>;

@Injectable({ providedIn: 'root' })
export class BlogContenidoService {
  public resourceUrl = SERVER_API_URL + 'api/blog-contenidos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/blog-contenidos';

  constructor(protected http: HttpClient) {}

  create(blogContenido: IBlogContenido): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blogContenido);
    return this.http
      .post<IBlogContenido>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(blogContenido: IBlogContenido): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(blogContenido);
    return this.http
      .put<IBlogContenido>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBlogContenido>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBlogContenido[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBlogContenido[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(blogContenido: IBlogContenido): IBlogContenido {
    const copy: IBlogContenido = Object.assign({}, blogContenido, {
      fecha: blogContenido.fecha != null && blogContenido.fecha.isValid() ? blogContenido.fecha.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((blogContenido: IBlogContenido) => {
        blogContenido.fecha = blogContenido.fecha != null ? moment(blogContenido.fecha) : null;
      });
    }
    return res;
  }
}
