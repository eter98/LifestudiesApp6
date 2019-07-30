/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { CotizacionDetailComponent } from 'app/entities/cotizacion/cotizacion-detail.component';
import { Cotizacion } from 'app/shared/model/cotizacion.model';

describe('Component Tests', () => {
  describe('Cotizacion Management Detail Component', () => {
    let comp: CotizacionDetailComponent;
    let fixture: ComponentFixture<CotizacionDetailComponent>;
    const route = ({ data: of({ cotizacion: new Cotizacion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [CotizacionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CotizacionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CotizacionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cotizacion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
