import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { LifestudiesApp6SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [LifestudiesApp6SharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [LifestudiesApp6SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesApp6SharedModule {
  static forRoot() {
    return {
      ngModule: LifestudiesApp6SharedModule
    };
  }
}
