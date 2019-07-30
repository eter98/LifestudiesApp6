/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { PasoCRMDetailComponent } from 'app/entities/paso-crm/paso-crm-detail.component';
import { PasoCRM } from 'app/shared/model/paso-crm.model';

describe('Component Tests', () => {
  describe('PasoCRM Management Detail Component', () => {
    let comp: PasoCRMDetailComponent;
    let fixture: ComponentFixture<PasoCRMDetailComponent>;
    const route = ({ data: of({ pasoCRM: new PasoCRM(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [PasoCRMDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PasoCRMDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PasoCRMDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pasoCRM).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
