/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { CotizacionDeleteDialogComponent } from 'app/entities/cotizacion/cotizacion-delete-dialog.component';
import { CotizacionService } from 'app/entities/cotizacion/cotizacion.service';

describe('Component Tests', () => {
  describe('Cotizacion Management Delete Component', () => {
    let comp: CotizacionDeleteDialogComponent;
    let fixture: ComponentFixture<CotizacionDeleteDialogComponent>;
    let service: CotizacionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [CotizacionDeleteDialogComponent]
      })
        .overrideTemplate(CotizacionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CotizacionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CotizacionService);
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
