import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInstitucion } from 'app/shared/model/institucion.model';

type EntityResponseType = HttpResponse<IInstitucion>;
type EntityArrayResponseType = HttpResponse<IInstitucion[]>;

@Injectable({ providedIn: 'root' })
export class InstitucionService {
  public resourceUrl = SERVER_API_URL + 'api/institucions';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/institucions';

  constructor(protected http: HttpClient) {}

  create(institucion: IInstitucion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(institucion);
    return this.http
      .post<IInstitucion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(institucion: IInstitucion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(institucion);
    return this.http
      .put<IInstitucion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInstitucion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInstitucion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInstitucion[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(institucion: IInstitucion): IInstitucion {
    const copy: IInstitucion = Object.assign({}, institucion, {
      fechaInicial:
        institucion.fechaInicial != null && institucion.fechaInicial.isValid() ? institucion.fechaInicial.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaInicial = res.body.fechaInicial != null ? moment(res.body.fechaInicial) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((institucion: IInstitucion) => {
        institucion.fechaInicial = institucion.fechaInicial != null ? moment(institucion.fechaInicial) : null;
      });
    }
    return res;
  }
}
