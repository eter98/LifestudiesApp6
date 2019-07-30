import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { PerfilUsuarioService } from './perfil-usuario.service';
import { PerfilUsuarioComponent } from './perfil-usuario.component';
import { PerfilUsuarioDetailComponent } from './perfil-usuario-detail.component';
import { PerfilUsuarioUpdateComponent } from './perfil-usuario-update.component';
import { PerfilUsuarioDeletePopupComponent } from './perfil-usuario-delete-dialog.component';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';

@Injectable({ providedIn: 'root' })
export class PerfilUsuarioResolve implements Resolve<IPerfilUsuario> {
  constructor(private service: PerfilUsuarioService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPerfilUsuario> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PerfilUsuario>) => response.ok),
        map((perfilUsuario: HttpResponse<PerfilUsuario>) => perfilUsuario.body)
      );
    }
    return of(new PerfilUsuario());
  }
}

export const perfilUsuarioRoute: Routes = [
  {
    path: '',
    component: PerfilUsuarioComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.perfilUsuario.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PerfilUsuarioDetailComponent,
    resolve: {
      perfilUsuario: PerfilUsuarioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.perfilUsuario.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PerfilUsuarioUpdateComponent,
    resolve: {
      perfilUsuario: PerfilUsuarioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.perfilUsuario.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PerfilUsuarioUpdateComponent,
    resolve: {
      perfilUsuario: PerfilUsuarioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.perfilUsuario.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const perfilUsuarioPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PerfilUsuarioDeletePopupComponent,
    resolve: {
      perfilUsuario: PerfilUsuarioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.perfilUsuario.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
