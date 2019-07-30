import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Institucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from './institucion.service';
import { InstitucionComponent } from './institucion.component';
import { InstitucionDetailComponent } from './institucion-detail.component';
import { InstitucionUpdateComponent } from './institucion-update.component';
import { InstitucionDeletePopupComponent } from './institucion-delete-dialog.component';
import { IInstitucion } from 'app/shared/model/institucion.model';

@Injectable({ providedIn: 'root' })
export class InstitucionResolve implements Resolve<IInstitucion> {
  constructor(private service: InstitucionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInstitucion> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Institucion>) => response.ok),
        map((institucion: HttpResponse<Institucion>) => institucion.body)
      );
    }
    return of(new Institucion());
  }
}

export const institucionRoute: Routes = [
  {
    path: '',
    component: InstitucionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.institucion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InstitucionDetailComponent,
    resolve: {
      institucion: InstitucionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.institucion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InstitucionUpdateComponent,
    resolve: {
      institucion: InstitucionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.institucion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InstitucionUpdateComponent,
    resolve: {
      institucion: InstitucionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.institucion.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const institucionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: InstitucionDeletePopupComponent,
    resolve: {
      institucion: InstitucionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.institucion.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
