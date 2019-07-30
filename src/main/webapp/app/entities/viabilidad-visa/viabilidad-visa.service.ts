import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';

type EntityResponseType = HttpResponse<IViabilidadVisa>;
type EntityArrayResponseType = HttpResponse<IViabilidadVisa[]>;

@Injectable({ providedIn: 'root' })
export class ViabilidadVisaService {
  public resourceUrl = SERVER_API_URL + 'api/viabilidad-visas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/viabilidad-visas';

  constructor(protected http: HttpClient) {}

  create(viabilidadVisa: IViabilidadVisa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(viabilidadVisa);
    return this.http
      .post<IViabilidadVisa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(viabilidadVisa: IViabilidadVisa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(viabilidadVisa);
    return this.http
      .put<IViabilidadVisa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IViabilidadVisa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IViabilidadVisa[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IViabilidadVisa[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(viabilidadVisa: IViabilidadVisa): IViabilidadVisa {
    const copy: IViabilidadVisa = Object.assign({}, viabilidadVisa, {
      fechaNacimiento:
        viabilidadVisa.fechaNacimiento != null && viabilidadVisa.fechaNacimiento.isValid()
          ? viabilidadVisa.fechaNacimiento.format(DATE_FORMAT)
          : null,
      fechaGadruacion:
        viabilidadVisa.fechaGadruacion != null && viabilidadVisa.fechaGadruacion.isValid()
          ? viabilidadVisa.fechaGadruacion.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaNacimiento = res.body.fechaNacimiento != null ? moment(res.body.fechaNacimiento) : null;
      res.body.fechaGadruacion = res.body.fechaGadruacion != null ? moment(res.body.fechaGadruacion) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((viabilidadVisa: IViabilidadVisa) => {
        viabilidadVisa.fechaNacimiento = viabilidadVisa.fechaNacimiento != null ? moment(viabilidadVisa.fechaNacimiento) : null;
        viabilidadVisa.fechaGadruacion = viabilidadVisa.fechaGadruacion != null ? moment(viabilidadVisa.fechaGadruacion) : null;
      });
    }
    return res;
  }
}
