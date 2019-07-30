/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { BlogContenidoUpdateComponent } from 'app/entities/blog-contenido/blog-contenido-update.component';
import { BlogContenidoService } from 'app/entities/blog-contenido/blog-contenido.service';
import { BlogContenido } from 'app/shared/model/blog-contenido.model';

describe('Component Tests', () => {
  describe('BlogContenido Management Update Component', () => {
    let comp: BlogContenidoUpdateComponent;
    let fixture: ComponentFixture<BlogContenidoUpdateComponent>;
    let service: BlogContenidoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [BlogContenidoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BlogContenidoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlogContenidoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlogContenidoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlogContenido(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BlogContenido();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
