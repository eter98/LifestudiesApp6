/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LifestudiesApp6TestModule } from '../../../test.module';
import { BlogContenidoDeleteDialogComponent } from 'app/entities/blog-contenido/blog-contenido-delete-dialog.component';
import { BlogContenidoService } from 'app/entities/blog-contenido/blog-contenido.service';

describe('Component Tests', () => {
  describe('BlogContenido Management Delete Component', () => {
    let comp: BlogContenidoDeleteDialogComponent;
    let fixture: ComponentFixture<BlogContenidoDeleteDialogComponent>;
    let service: BlogContenidoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LifestudiesApp6TestModule],
        declarations: [BlogContenidoDeleteDialogComponent]
      })
        .overrideTemplate(BlogContenidoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlogContenidoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlogContenidoService);
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
