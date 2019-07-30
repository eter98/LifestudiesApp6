import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  AgenciaComponent,
  AgenciaDetailComponent,
  AgenciaUpdateComponent,
  AgenciaDeletePopupComponent,
  AgenciaDeleteDialogComponent,
  agenciaRoute,
  agenciaPopupRoute
} from './';

const ENTITY_STATES = [...agenciaRoute, ...agenciaPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AgenciaComponent,
    AgenciaDetailComponent,
    AgenciaUpdateComponent,
    AgenciaDeleteDialogComponent,
    AgenciaDeletePopupComponent
  ],
  entryComponents: [AgenciaComponent, AgenciaUpdateComponent, AgenciaDeleteDialogComponent, AgenciaDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6AgenciaModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
