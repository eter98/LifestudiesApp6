import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';

type EntityResponseType = HttpResponse<IPerfilUsuario>;
type EntityArrayResponseType = HttpResponse<IPerfilUsuario[]>;

@Injectable({ providedIn: 'root' })
export class PerfilUsuarioService {
  public resourceUrl = SERVER_API_URL + 'api/perfil-usuarios';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/perfil-usuarios';

  constructor(protected http: HttpClient) {}

  create(perfilUsuario: IPerfilUsuario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(perfilUsuario);
    return this.http
      .post<IPerfilUsuario>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(perfilUsuario: IPerfilUsuario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(perfilUsuario);
    return this.http
      .put<IPerfilUsuario>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPerfilUsuario>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPerfilUsuario[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPerfilUsuario[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(perfilUsuario: IPerfilUsuario): IPerfilUsuario {
    const copy: IPerfilUsuario = Object.assign({}, perfilUsuario, {
      fechaNacimiento:
        perfilUsuario.fechaNacimiento != null && perfilUsuario.fechaNacimiento.isValid()
          ? perfilUsuario.fechaNacimiento.format(DATE_FORMAT)
          : null,
      fechaInicio:
        perfilUsuario.fechaInicio != null && perfilUsuario.fechaInicio.isValid() ? perfilUsuario.fechaInicio.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaNacimiento = res.body.fechaNacimiento != null ? moment(res.body.fechaNacimiento) : null;
      res.body.fechaInicio = res.body.fechaInicio != null ? moment(res.body.fechaInicio) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((perfilUsuario: IPerfilUsuario) => {
        perfilUsuario.fechaNacimiento = perfilUsuario.fechaNacimiento != null ? moment(perfilUsuario.fechaNacimiento) : null;
        perfilUsuario.fechaInicio = perfilUsuario.fechaInicio != null ? moment(perfilUsuario.fechaInicio) : null;
      });
    }
    return res;
  }
}
