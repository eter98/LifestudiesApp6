/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { CRMUpdateComponent } from 'app/entities/crm/crm-update.component';
import { CRMService } from 'app/entities/crm/crm.service';
import { CRM } from 'app/shared/model/crm.model';

describe('Component Tests', () => {
  describe('CRM Management Update Component', () => {
    let comp: CRMUpdateComponent;
    let fixture: ComponentFixture<CRMUpdateComponent>;
    let service: CRMService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [CRMUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CRMUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CRMUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CRMService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CRM(123);
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
        const entity = new CRM();
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
