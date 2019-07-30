import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  ExperienciaComponent,
  ExperienciaDetailComponent,
  ExperienciaUpdateComponent,
  ExperienciaDeletePopupComponent,
  ExperienciaDeleteDialogComponent,
  experienciaRoute,
  experienciaPopupRoute
} from './';

const ENTITY_STATES = [...experienciaRoute, ...experienciaPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ExperienciaComponent,
    ExperienciaDetailComponent,
    ExperienciaUpdateComponent,
    ExperienciaDeleteDialogComponent,
    ExperienciaDeletePopupComponent
  ],
  entryComponents: [ExperienciaComponent, ExperienciaUpdateComponent, ExperienciaDeleteDialogComponent, ExperienciaDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6ExperienciaModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
