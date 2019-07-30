/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { ProgramasDeleteDialogComponent } from 'app/entities/programas/programas-delete-dialog.component';
import { ProgramasService } from 'app/entities/programas/programas.service';

describe('Component Tests', () => {
  describe('Programas Management Delete Component', () => {
    let comp: ProgramasDeleteDialogComponent;
    let fixture: ComponentFixture<ProgramasDeleteDialogComponent>;
    let service: ProgramasService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [ProgramasDeleteDialogComponent]
      })
        .overrideTemplate(ProgramasDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProgramasDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProgramasService);
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
