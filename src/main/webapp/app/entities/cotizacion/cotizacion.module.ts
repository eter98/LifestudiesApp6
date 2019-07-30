import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  CotizacionComponent,
  CotizacionDetailComponent,
  CotizacionUpdateComponent,
  CotizacionDeletePopupComponent,
  CotizacionDeleteDialogComponent,
  cotizacionRoute,
  cotizacionPopupRoute
} from './';

const ENTITY_STATES = [...cotizacionRoute, ...cotizacionPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CotizacionComponent,
    CotizacionDetailComponent,
    CotizacionUpdateComponent,
    CotizacionDeleteDialogComponent,
    CotizacionDeletePopupComponent
  ],
  entryComponents: [CotizacionComponent, CotizacionUpdateComponent, CotizacionDeleteDialogComponent, CotizacionDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6CotizacionModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
