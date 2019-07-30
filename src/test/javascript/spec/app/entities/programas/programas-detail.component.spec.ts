/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { ProgramasDetailComponent } from 'app/entities/programas/programas-detail.component';
import { Programas } from 'app/shared/model/programas.model';

describe('Component Tests', () => {
  describe('Programas Management Detail Component', () => {
    let comp: ProgramasDetailComponent;
    let fixture: ComponentFixture<ProgramasDetailComponent>;
    const route = ({ data: of({ programas: new Programas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [ProgramasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProgramasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProgramasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.programas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
