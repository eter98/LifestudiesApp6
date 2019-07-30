import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  CRMComponent,
  CRMDetailComponent,
  CRMUpdateComponent,
  CRMDeletePopupComponent,
  CRMDeleteDialogComponent,
  cRMRoute,
  cRMPopupRoute
} from './';

const ENTITY_STATES = [...cRMRoute, ...cRMPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [CRMComponent, CRMDetailComponent, CRMUpdateComponent, CRMDeleteDialogComponent, CRMDeletePopupComponent],
  entryComponents: [CRMComponent, CRMUpdateComponent, CRMDeleteDialogComponent, CRMDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6CRMModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
