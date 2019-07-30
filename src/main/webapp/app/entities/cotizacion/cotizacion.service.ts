import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICotizacion } from 'app/shared/model/cotizacion.model';

type EntityResponseType = HttpResponse<ICotizacion>;
type EntityArrayResponseType = HttpResponse<ICotizacion[]>;

@Injectable({ providedIn: 'root' })
export class CotizacionService {
  public resourceUrl = SERVER_API_URL + 'api/cotizacions';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/cotizacions';

  constructor(protected http: HttpClient) {}

  create(cotizacion: ICotizacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cotizacion);
    return this.http
      .post<ICotizacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cotizacion: ICotizacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cotizacion);
    return this.http
      .put<ICotizacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICotizacion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICotizacion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICotizacion[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(cotizacion: ICotizacion): ICotizacion {
    const copy: ICotizacion = Object.assign({}, cotizacion, {
      fechaViaje: cotizacion.fechaViaje != null && cotizacion.fechaViaje.isValid() ? cotizacion.fechaViaje.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaViaje = res.body.fechaViaje != null ? moment(res.body.fechaViaje) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cotizacion: ICotizacion) => {
        cotizacion.fechaViaje = cotizacion.fechaViaje != null ? moment(cotizacion.fechaViaje) : null;
      });
    }
    return res;
  }
}
