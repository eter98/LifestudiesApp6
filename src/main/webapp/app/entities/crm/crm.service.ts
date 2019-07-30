import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICRM } from 'app/shared/model/crm.model';

type EntityResponseType = HttpResponse<ICRM>;
type EntityArrayResponseType = HttpResponse<ICRM[]>;

@Injectable({ providedIn: 'root' })
export class CRMService {
  public resourceUrl = SERVER_API_URL + 'api/crms';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/crms';

  constructor(protected http: HttpClient) {}

  create(cRM: ICRM): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cRM);
    return this.http
      .post<ICRM>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cRM: ICRM): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cRM);
    return this.http
      .put<ICRM>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICRM>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICRM[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICRM[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(cRM: ICRM): ICRM {
    const copy: ICRM = Object.assign({}, cRM, {
      fecha: cRM.fecha != null && cRM.fecha.isValid() ? cRM.fecha.toJSON() : null
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
      res.body.forEach((cRM: ICRM) => {
        cRM.fecha = cRM.fecha != null ? moment(cRM.fecha) : null;
      });
    }
    return res;
  }
}
