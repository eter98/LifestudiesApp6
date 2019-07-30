import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PasoCRM } from 'app/shared/model/paso-crm.model';
import { PasoCRMService } from './paso-crm.service';
import { PasoCRMComponent } from './paso-crm.component';
import { PasoCRMDetailComponent } from './paso-crm-detail.component';
import { PasoCRMUpdateComponent } from './paso-crm-update.component';
import { PasoCRMDeletePopupComponent } from './paso-crm-delete-dialog.component';
import { IPasoCRM } from 'app/shared/model/paso-crm.model';

@Injectable({ providedIn: 'root' })
export class PasoCRMResolve implements Resolve<IPasoCRM> {
  constructor(private service: PasoCRMService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPasoCRM> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PasoCRM>) => response.ok),
        map((pasoCRM: HttpResponse<PasoCRM>) => pasoCRM.body)
      );
    }
    return of(new PasoCRM());
  }
}

export const pasoCRMRoute: Routes = [
  {
    path: '',
    component: PasoCRMComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.pasoCRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PasoCRMDetailComponent,
    resolve: {
      pasoCRM: PasoCRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.pasoCRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PasoCRMUpdateComponent,
    resolve: {
      pasoCRM: PasoCRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.pasoCRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PasoCRMUpdateComponent,
    resolve: {
      pasoCRM: PasoCRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.pasoCRM.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pasoCRMPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PasoCRMDeletePopupComponent,
    resolve: {
      pasoCRM: PasoCRMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.pasoCRM.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
