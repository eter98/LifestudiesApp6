import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CRM } from 'app/shared/model/crm.model';
import { CRMService } from './crm.service';
import { CRMComponent } from './crm.component';
import { CRMDetailComponent } from './crm-detail.component';
import { CRMUpdateComponent } from './crm-update.component';
import { CRMDeletePopupComponent } from './crm-delete-dialog.component';
import { ICRM } from 'app/shared/model/crm.model';

@Injectable({ providedIn: 'root' })
export class CRMResolve implements Resolve<ICRM> {
  constructor(private service: CRMService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICRM> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CRM>) => response.ok),
        map((cRM: HttpResponse<CRM>) => cRM.body)
      );
    }
    return of(new CRM());
  }
}

export const cRMRoute: Routes = [
  {
    path: '',
    component: CRMComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CRMDetailComponent,
    resolve: {
      cRM: CRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CRMUpdateComponent,
    resolve: {
      cRM: CRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CRMUpdateComponent,
    resolve: {
      cRM: CRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const cRMPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CRMDeletePopupComponent,
    resolve: {
      cRM: CRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.cRM.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
