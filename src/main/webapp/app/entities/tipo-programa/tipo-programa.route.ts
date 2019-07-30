import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoPrograma } from 'app/shared/model/tipo-programa.model';
import { TipoProgramaService } from './tipo-programa.service';
import { TipoProgramaComponent } from './tipo-programa.component';
import { TipoProgramaDetailComponent } from './tipo-programa-detail.component';
import { TipoProgramaUpdateComponent } from './tipo-programa-update.component';
import { TipoProgramaDeletePopupComponent } from './tipo-programa-delete-dialog.component';
import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';

@Injectable({ providedIn: 'root' })
export class TipoProgramaResolve implements Resolve<ITipoPrograma> {
  constructor(private service: TipoProgramaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipoPrograma> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TipoPrograma>) => response.ok),
        map((tipoPrograma: HttpResponse<TipoPrograma>) => tipoPrograma.body)
      );
    }
    return of(new TipoPrograma());
  }
}

export const tipoProgramaRoute: Routes = [
  {
    path: '',
    component: TipoProgramaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.tipoPrograma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TipoProgramaDetailComponent,
    resolve: {
      tipoPrograma: TipoProgramaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.tipoPrograma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TipoProgramaUpdateComponent,
    resolve: {
      tipoPrograma: TipoProgramaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.tipoPrograma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TipoProgramaUpdateComponent,
    resolve: {
      tipoPrograma: TipoProgramaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.tipoPrograma.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tipoProgramaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TipoProgramaDeletePopupComponent,
    resolve: {
      tipoPrograma: TipoProgramaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.tipoPrograma.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
