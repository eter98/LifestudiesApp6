/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { PasoCRMUpdateComponent } from 'app/entities/paso-crm/paso-crm-update.component';
import { PasoCRMService } from 'app/entities/paso-crm/paso-crm.service';
import { PasoCRM } from 'app/shared/model/paso-crm.model';

describe('Component Tests', () => {
  describe('PasoCRM Management Update Component', () => {
    let comp: PasoCRMUpdateComponent;
    let fixture: ComponentFixture<PasoCRMUpdateComponent>;
    let service: PasoCRMService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [PasoCRMUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PasoCRMUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PasoCRMUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PasoCRMService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PasoCRM(123);
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
        const entity = new PasoCRM();
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
