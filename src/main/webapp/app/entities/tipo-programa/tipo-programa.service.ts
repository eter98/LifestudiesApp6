import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';

type EntityResponseType = HttpResponse<ITipoPrograma>;
type EntityArrayResponseType = HttpResponse<ITipoPrograma[]>;

@Injectable({ providedIn: 'root' })
export class TipoProgramaService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-programas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/tipo-programas';

  constructor(protected http: HttpClient) {}

  create(tipoPrograma: ITipoPrograma): Observable<EntityResponseType> {
    return this.http.post<ITipoPrograma>(this.resourceUrl, tipoPrograma, { observe: 'response' });
  }

  update(tipoPrograma: ITipoPrograma): Observable<EntityResponseType> {
    return this.http.put<ITipoPrograma>(this.resourceUrl, tipoPrograma, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITipoPrograma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoPrograma[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoPrograma[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
