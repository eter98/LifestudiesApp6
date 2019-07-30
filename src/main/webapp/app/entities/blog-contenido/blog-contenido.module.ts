import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  BlogContenidoComponent,
  BlogContenidoDetailComponent,
  BlogContenidoUpdateComponent,
  BlogContenidoDeletePopupComponent,
  BlogContenidoDeleteDialogComponent,
  blogContenidoRoute,
  blogContenidoPopupRoute
} from './';

const ENTITY_STATES = [...blogContenidoRoute, ...blogContenidoPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BlogContenidoComponent,
    BlogContenidoDetailComponent,
    BlogContenidoUpdateComponent,
    BlogContenidoDeleteDialogComponent,
    BlogContenidoDeletePopupComponent
  ],
  entryComponents: [
    BlogContenidoComponent,
    BlogContenidoUpdateComponent,
    BlogContenidoDeleteDialogComponent,
    BlogContenidoDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6BlogContenidoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
