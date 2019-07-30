import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICotizacion } from 'app/shared/model/cotizacion.model';
import { CotizacionService } from './cotizacion.service';

@Component({
  selector: 'jhi-cotizacion-delete-dialog',
  templateUrl: './cotizacion-delete-dialog.component.html'
})
export class CotizacionDeleteDialogComponent {
  cotizacion: ICotizacion;

  constructor(
    protected cotizacionService: CotizacionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.cotizacionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'cotizacionListModification',
        content: 'Deleted an cotizacion'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-cotizacion-delete-popup',
  template: ''
})
export class CotizacionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cotizacion }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CotizacionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.cotizacion = cotizacion;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/cotizacion', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/cotizacion', { outlets: { popup: null } }]);
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
