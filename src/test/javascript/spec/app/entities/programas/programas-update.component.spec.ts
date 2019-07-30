/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { ProgramasUpdateComponent } from 'app/entities/programas/programas-update.component';
import { ProgramasService } from 'app/entities/programas/programas.service';
import { Programas } from 'app/shared/model/programas.model';

describe('Component Tests', () => {
  describe('Programas Management Update Component', () => {
    let comp: ProgramasUpdateComponent;
    let fixture: ComponentFixture<ProgramasUpdateComponent>;
    let service: ProgramasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [ProgramasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProgramasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProgramasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProgramasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Programas(123);
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
        const entity = new Programas();
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
