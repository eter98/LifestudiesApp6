/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { TipoProgramaDeleteDialogComponent } from 'app/entities/tipo-programa/tipo-programa-delete-dialog.component';
import { TipoProgramaService } from 'app/entities/tipo-programa/tipo-programa.service';

describe('Component Tests', () => {
  describe('TipoPrograma Management Delete Component', () => {
    let comp: TipoProgramaDeleteDialogComponent;
    let fixture: ComponentFixture<TipoProgramaDeleteDialogComponent>;
    let service: TipoProgramaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [TipoProgramaDeleteDialogComponent]
      })
        .overrideTemplate(TipoProgramaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoProgramaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoProgramaService);
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
