import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Programas } from 'app/shared/model/programas.model';
import { ProgramasService } from './programas.service';
import { ProgramasComponent } from './programas.component';
import { ProgramasDetailComponent } from './programas-detail.component';
import { ProgramasUpdateComponent } from './programas-update.component';
import { ProgramasDeletePopupComponent } from './programas-delete-dialog.component';
import { IProgramas } from 'app/shared/model/programas.model';

@Injectable({ providedIn: 'root' })
export class ProgramasResolve implements Resolve<IProgramas> {
  constructor(private service: ProgramasService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IProgramas> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Programas>) => response.ok),
        map((programas: HttpResponse<Programas>) => programas.body)
      );
    }
    return of(new Programas());
  }
}

export const programasRoute: Routes = [
  {
    path: '',
    component: ProgramasComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.programas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProgramasDetailComponent,
    resolve: {
      programas: ProgramasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.programas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProgramasUpdateComponent,
    resolve: {
      programas: ProgramasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.programas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProgramasUpdateComponent,
    resolve: {
      programas: ProgramasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.programas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const programasPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProgramasDeletePopupComponent,
    resolve: {
      programas: ProgramasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.programas.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
