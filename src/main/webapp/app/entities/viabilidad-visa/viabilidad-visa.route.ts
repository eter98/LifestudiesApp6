import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';
import { ViabilidadVisaService } from './viabilidad-visa.service';
import { ViabilidadVisaComponent } from './viabilidad-visa.component';
import { ViabilidadVisaDetailComponent } from './viabilidad-visa-detail.component';
import { ViabilidadVisaUpdateComponent } from './viabilidad-visa-update.component';
import { ViabilidadVisaDeletePopupComponent } from './viabilidad-visa-delete-dialog.component';
import { IViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';

@Injectable({ providedIn: 'root' })
export class ViabilidadVisaResolve implements Resolve<IViabilidadVisa> {
  constructor(private service: ViabilidadVisaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IViabilidadVisa> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ViabilidadVisa>) => response.ok),
        map((viabilidadVisa: HttpResponse<ViabilidadVisa>) => viabilidadVisa.body)
      );
    }
    return of(new ViabilidadVisa());
  }
}

export const viabilidadVisaRoute: Routes = [
  {
    path: '',
    component: ViabilidadVisaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.viabilidadVisa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ViabilidadVisaDetailComponent,
    resolve: {
      viabilidadVisa: ViabilidadVisaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.viabilidadVisa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ViabilidadVisaUpdateComponent,
    resolve: {
      viabilidadVisa: ViabilidadVisaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.viabilidadVisa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ViabilidadVisaUpdateComponent,
    resolve: {
      viabilidadVisa: ViabilidadVisaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.viabilidadVisa.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const viabilidadVisaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ViabilidadVisaDeletePopupComponent,
    resolve: {
      viabilidadVisa: ViabilidadVisaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.viabilidadVisa.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
