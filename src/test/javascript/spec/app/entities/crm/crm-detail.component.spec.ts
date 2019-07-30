/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { CRMDetailComponent } from 'app/entities/crm/crm-detail.component';
import { CRM } from 'app/shared/model/crm.model';

describe('Component Tests', () => {
  describe('CRM Management Detail Component', () => {
    let comp: CRMDetailComponent;
    let fixture: ComponentFixture<CRMDetailComponent>;
    const route = ({ data: of({ cRM: new CRM(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [CRMDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CRMDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CRMDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cRM).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
