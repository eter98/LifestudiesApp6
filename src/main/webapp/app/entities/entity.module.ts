import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'institucion',
        loadChildren: './institucion/institucion.module#LifestudiesApp6InstitucionModule'
      },
      {
        path: 'programas',
        loadChildren: './programas/programas.module#LifestudiesApp6ProgramasModule'
      },
      {
        path: 'tipo-programa',
        loadChildren: './tipo-programa/tipo-programa.module#LifestudiesApp6TipoProgramaModule'
      },
      {
        path: 'cotizacion',
        loadChildren: './cotizacion/cotizacion.module#LifestudiesApp6CotizacionModule'
      },
      {
        path: 'perfil-usuario',
        loadChildren: './perfil-usuario/perfil-usuario.module#LifestudiesApp6PerfilUsuarioModule'
      },
      {
        path: 'agencia',
        loadChildren: './agencia/agencia.module#LifestudiesApp6AgenciaModule'
      },
      {
        path: 'pais',
        loadChildren: './pais/pais.module#LifestudiesApp6PaisModule'
      },
      {
        path: 'ciudad',
        loadChildren: './ciudad/ciudad.module#LifestudiesApp6CiudadModule'
      },
      {
        path: 'viabilidad-visa',
        loadChildren: './viabilidad-visa/viabilidad-visa.module#LifestudiesApp6ViabilidadVisaModule'
      },
      {
        path: 'blog-contenido',
        loadChildren: './blog-contenido/blog-contenido.module#LifestudiesApp6BlogContenidoModule'
      },
      {
        path: 'comentario-blog',
        loadChildren: './comentario-blog/comentario-blog.module#LifestudiesApp6ComentarioBlogModule'
      },
      {
        path: 'crm',
        loadChildren: './crm/crm.module#LifestudiesApp6CRMModule'
      },
      {
        path: 'paso-crm',
        loadChildren: './paso-crm/paso-crm.module#LifestudiesApp6PasoCRMModule'
      },
      {
        path: 'experiencia',
        loadChildren: './experiencia/experiencia.module#LifestudiesApp6ExperienciaModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6EntityModule {}
