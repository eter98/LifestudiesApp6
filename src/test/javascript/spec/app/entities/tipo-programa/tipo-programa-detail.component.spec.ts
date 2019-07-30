/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { TipoProgramaDetailComponent } from 'app/entities/tipo-programa/tipo-programa-detail.component';
import { TipoPrograma } from 'app/shared/model/tipo-programa.model';

describe('Component Tests', () => {
  describe('TipoPrograma Management Detail Component', () => {
    let comp: TipoProgramaDetailComponent;
    let fixture: ComponentFixture<TipoProgramaDetailComponent>;
    const route = ({ data: of({ tipoPrograma: new TipoPrograma(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [TipoProgramaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TipoProgramaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoProgramaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoPrograma).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
