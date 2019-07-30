import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Experiencia } from 'app/shared/model/experiencia.model';
import { ExperienciaService } from './experiencia.service';
import { ExperienciaComponent } from './experiencia.component';
import { ExperienciaDetailComponent } from './experiencia-detail.component';
import { ExperienciaUpdateComponent } from './experiencia-update.component';
import { ExperienciaDeletePopupComponent } from './experiencia-delete-dialog.component';
import { IExperiencia } from 'app/shared/model/experiencia.model';

@Injectable({ providedIn: 'root' })
export class ExperienciaResolve implements Resolve<IExperiencia> {
  constructor(private service: ExperienciaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExperiencia> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Experiencia>) => response.ok),
        map((experiencia: HttpResponse<Experiencia>) => experiencia.body)
      );
    }
    return of(new Experiencia());
  }
}

export const experienciaRoute: Routes = [
  {
    path: '',
    component: ExperienciaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.experiencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExperienciaDetailComponent,
    resolve: {
      experiencia: ExperienciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.experiencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExperienciaUpdateComponent,
    resolve: {
      experiencia: ExperienciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.experiencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExperienciaUpdateComponent,
    resolve: {
      experiencia: ExperienciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.experiencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const experienciaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ExperienciaDeletePopupComponent,
    resolve: {
      experiencia: ExperienciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.experiencia.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
