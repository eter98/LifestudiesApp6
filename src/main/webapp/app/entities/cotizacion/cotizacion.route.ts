import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cotizacion } from 'app/shared/model/cotizacion.model';
import { CotizacionService } from './cotizacion.service';
import { CotizacionComponent } from './cotizacion.component';
import { CotizacionDetailComponent } from './cotizacion-detail.component';
import { CotizacionUpdateComponent } from './cotizacion-update.component';
import { CotizacionDeletePopupComponent } from './cotizacion-delete-dialog.component';
import { ICotizacion } from 'app/shared/model/cotizacion.model';

@Injectable({ providedIn: 'root' })
export class CotizacionResolve implements Resolve<ICotizacion> {
  constructor(private service: CotizacionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICotizacion> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Cotizacion>) => response.ok),
        map((cotizacion: HttpResponse<Cotizacion>) => cotizacion.body)
      );
    }
    return of(new Cotizacion());
  }
}

export const cotizacionRoute: Routes = [
  {
    path: '',
    component: CotizacionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cotizacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CotizacionDetailComponent,
    resolve: {
      cotizacion: CotizacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cotizacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CotizacionUpdateComponent,
    resolve: {
      cotizacion: CotizacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cotizacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CotizacionUpdateComponent,
    resolve: {
      cotizacion: CotizacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cotizacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const cotizacionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CotizacionDeletePopupComponent,
    resolve: {
      cotizacion: CotizacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cotizacion.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
