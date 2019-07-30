/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { BlogContenidoDetailComponent } from 'app/entities/blog-contenido/blog-contenido-detail.component';
import { BlogContenido } from 'app/shared/model/blog-contenido.model';

describe('Component Tests', () => {
  describe('BlogContenido Management Detail Component', () => {
    let comp: BlogContenidoDetailComponent;
    let fixture: ComponentFixture<BlogContenidoDetailComponent>;
    const route = ({ data: of({ blogContenido: new BlogContenido(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [BlogContenidoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BlogContenidoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlogContenidoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blogContenido).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
