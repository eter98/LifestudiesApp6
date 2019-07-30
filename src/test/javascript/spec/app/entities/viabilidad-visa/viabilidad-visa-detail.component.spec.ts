/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { ViabilidadVisaDetailComponent } from 'app/entities/viabilidad-visa/viabilidad-visa-detail.component';
import { ViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';

describe('Component Tests', () => {
  describe('ViabilidadVisa Management Detail Component', () => {
    let comp: ViabilidadVisaDetailComponent;
    let fixture: ComponentFixture<ViabilidadVisaDetailComponent>;
    const route = ({ data: of({ viabilidadVisa: new ViabilidadVisa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [ViabilidadVisaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ViabilidadVisaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ViabilidadVisaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.viabilidadVisa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
