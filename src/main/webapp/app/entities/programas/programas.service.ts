import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProgramas } from 'app/shared/model/programas.model';

type EntityResponseType = HttpResponse<IProgramas>;
type EntityArrayResponseType = HttpResponse<IProgramas[]>;

@Injectable({ providedIn: 'root' })
export class ProgramasService {
  public resourceUrl = SERVER_API_URL + 'api/programas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/programas';

  constructor(protected http: HttpClient) {}

  create(programas: IProgramas): Observable<EntityResponseType> {
    return this.http.post<IProgramas>(this.resourceUrl, programas, { observe: 'response' });
  }

  update(programas: IProgramas): Observable<EntityResponseType> {
    return this.http.put<IProgramas>(this.resourceUrl, programas, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProgramas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProgramas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProgramas[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
