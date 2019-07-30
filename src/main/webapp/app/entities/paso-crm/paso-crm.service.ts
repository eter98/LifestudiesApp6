import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPasoCRM } from 'app/shared/model/paso-crm.model';

type EntityResponseType = HttpResponse<IPasoCRM>;
type EntityArrayResponseType = HttpResponse<IPasoCRM[]>;

@Injectable({ providedIn: 'root' })
export class PasoCRMService {
  public resourceUrl = SERVER_API_URL + 'api/paso-crms';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/paso-crms';

  constructor(protected http: HttpClient) {}

  create(pasoCRM: IPasoCRM): Observable<EntityResponseType> {
    return this.http.post<IPasoCRM>(this.resourceUrl, pasoCRM, { observe: 'response' });
  }

  update(pasoCRM: IPasoCRM): Observable<EntityResponseType> {
    return this.http.put<IPasoCRM>(this.resourceUrl, pasoCRM, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPasoCRM>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPasoCRM[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPasoCRM[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
