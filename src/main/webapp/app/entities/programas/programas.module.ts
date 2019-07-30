import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesApp6SharedModule } from 'app/shared';
import {
  ProgramasComponent,
  ProgramasDetailComponent,
  ProgramasUpdateComponent,
  ProgramasDeletePopupComponent,
  ProgramasDeleteDialogComponent,
  programasRoute,
  programasPopupRoute
} from './';

const ENTITY_STATES = [...programasRoute, ...programasPopupRoute];

@NgModule({
  imports: [LifestudiesApp6SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProgramasComponent,
    ProgramasDetailComponent,
    ProgramasUpdateComponent,
    ProgramasDeleteDialogComponent,
    ProgramasDeletePopupComponent
  ],
  entryComponents: [ProgramasComponent, ProgramasUpdateComponent, ProgramasDeleteDialogComponent, ProgramasDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6ProgramasModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
