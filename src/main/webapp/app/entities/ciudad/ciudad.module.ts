import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  CiudadComponent,
  CiudadDetailComponent,
  CiudadUpdateComponent,
  CiudadDeletePopupComponent,
  CiudadDeleteDialogComponent,
  ciudadRoute,
  ciudadPopupRoute
} from './';

const ENTITY_STATES = [...ciudadRoute, ...ciudadPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [CiudadComponent, CiudadDetailComponent, CiudadUpdateComponent, CiudadDeleteDialogComponent, CiudadDeletePopupComponent],
  entryComponents: [CiudadComponent, CiudadUpdateComponent, CiudadDeleteDialogComponent, CiudadDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6CiudadModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
