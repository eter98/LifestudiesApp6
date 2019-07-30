/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { ViabilidadVisaUpdateComponent } from 'app/entities/viabilidad-visa/viabilidad-visa-update.component';
import { ViabilidadVisaService } from 'app/entities/viabilidad-visa/viabilidad-visa.service';
import { ViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';

describe('Component Tests', () => {
  describe('ViabilidadVisa Management Update Component', () => {
    let comp: ViabilidadVisaUpdateComponent;
    let fixture: ComponentFixture<ViabilidadVisaUpdateComponent>;
    let service: ViabilidadVisaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [ViabilidadVisaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ViabilidadVisaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ViabilidadVisaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ViabilidadVisaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ViabilidadVisa(123);
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
        const entity = new ViabilidadVisa();
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
