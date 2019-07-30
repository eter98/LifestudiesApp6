/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { PasoCRMDeleteDialogComponent } from 'app/entities/paso-crm/paso-crm-delete-dialog.component';
import { PasoCRMService } from 'app/entities/paso-crm/paso-crm.service';

describe('Component Tests', () => {
  describe('PasoCRM Management Delete Component', () => {
    let comp: PasoCRMDeleteDialogComponent;
    let fixture: ComponentFixture<PasoCRMDeleteDialogComponent>;
    let service: PasoCRMService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [PasoCRMDeleteDialogComponent]
      })
        .overrideTemplate(PasoCRMDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PasoCRMDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PasoCRMService);
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
