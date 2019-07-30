/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { ViabilidadVisaDeleteDialogComponent } from 'app/entities/viabilidad-visa/viabilidad-visa-delete-dialog.component';
import { ViabilidadVisaService } from 'app/entities/viabilidad-visa/viabilidad-visa.service';

describe('Component Tests', () => {
  describe('ViabilidadVisa Management Delete Component', () => {
    let comp: ViabilidadVisaDeleteDialogComponent;
    let fixture: ComponentFixture<ViabilidadVisaDeleteDialogComponent>;
    let service: ViabilidadVisaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [ViabilidadVisaDeleteDialogComponent]
      })
        .overrideTemplate(ViabilidadVisaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ViabilidadVisaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ViabilidadVisaService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
