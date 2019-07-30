import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BlogContenido } from 'app/shared/model/blog-contenido.model';
import { BlogContenidoService } from './blog-contenido.service';
import { BlogContenidoComponent } from './blog-contenido.component';
import { BlogContenidoDetailComponent } from './blog-contenido-detail.component';
import { BlogContenidoUpdateComponent } from './blog-contenido-update.component';
import { BlogContenidoDeletePopupComponent } from './blog-contenido-delete-dialog.component';
import { IBlogContenido } from 'app/shared/model/blog-contenido.model';

@Injectable({ providedIn: 'root' })
export class BlogContenidoResolve implements Resolve<IBlogContenido> {
  constructor(private service: BlogContenidoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBlogContenido> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BlogContenido>) => response.ok),
        map((blogContenido: HttpResponse<BlogContenido>) => blogContenido.body)
      );
    }
    return of(new BlogContenido());
  }
}

export const blogContenidoRoute: Routes = [
  {
    path: '',
    component: BlogContenidoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.blogContenido.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BlogContenidoDetailComponent,
    resolve: {
      blogContenido: BlogContenidoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.blogContenido.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BlogContenidoUpdateComponent,
    resolve: {
      blogContenido: BlogContenidoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.blogContenido.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BlogContenidoUpdateComponent,
    resolve: {
      blogContenido: BlogContenidoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.blogContenido.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const blogContenidoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BlogContenidoDeletePopupComponent,
    resolve: {
      blogContenido: BlogContenidoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lifestudiesApp6App.blogContenido.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
