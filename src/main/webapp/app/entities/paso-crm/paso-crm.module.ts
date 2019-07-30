import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  PasoCRMComponent,
  PasoCRMDetailComponent,
  PasoCRMUpdateComponent,
  PasoCRMDeletePopupComponent,
  PasoCRMDeleteDialogComponent,
  pasoCRMRoute,
  pasoCRMPopupRoute
} from './';

const ENTITY_STATES = [...pasoCRMRoute, ...pasoCRMPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PasoCRMComponent,
    PasoCRMDetailComponent,
    PasoCRMUpdateComponent,
    PasoCRMDeleteDialogComponent,
    PasoCRMDeletePopupComponent
  ],
  entryComponents: [PasoCRMComponent, PasoCRMUpdateComponent, PasoCRMDeleteDialogComponent, PasoCRMDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6PasoCRMModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
