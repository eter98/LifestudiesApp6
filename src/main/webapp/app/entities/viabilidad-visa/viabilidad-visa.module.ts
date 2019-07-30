import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  ViabilidadVisaComponent,
  ViabilidadVisaDetailComponent,
  ViabilidadVisaUpdateComponent,
  ViabilidadVisaDeletePopupComponent,
  ViabilidadVisaDeleteDialogComponent,
  viabilidadVisaRoute,
  viabilidadVisaPopupRoute
} from './';

const ENTITY_STATES = [...viabilidadVisaRoute, ...viabilidadVisaPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ViabilidadVisaComponent,
    ViabilidadVisaDetailComponent,
    ViabilidadVisaUpdateComponent,
    ViabilidadVisaDeleteDialogComponent,
    ViabilidadVisaDeletePopupComponent
  ],
  entryComponents: [
    ViabilidadVisaComponent,
    ViabilidadVisaUpdateComponent,
    ViabilidadVisaDeleteDialogComponent,
    ViabilidadVisaDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6ViabilidadVisaModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
