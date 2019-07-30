import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstitucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from './institucion.service';

@Component({
  selector: 'jhi-institucion-delete-dialog',
  templateUrl: './institucion-delete-dialog.component.html'
})
export class InstitucionDeleteDialogComponent {
  institucion: IInstitucion;

  constructor(
    protected institucionService: InstitucionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.institucionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'institucionListModification',
        content: 'Deleted an institucion'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-institucion-delete-popup',
  template: ''
})
export class InstitucionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ institucion }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(InstitucionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.institucion = institucion;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/institucion', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/institucion', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
