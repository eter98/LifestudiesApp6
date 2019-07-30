import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';
import { ViabilidadVisaService } from './viabilidad-visa.service';

@Component({
  selector: 'jhi-viabilidad-visa-delete-dialog',
  templateUrl: './viabilidad-visa-delete-dialog.component.html'
})
export class ViabilidadVisaDeleteDialogComponent {
  viabilidadVisa: IViabilidadVisa;

  constructor(
    protected viabilidadVisaService: ViabilidadVisaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.viabilidadVisaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'viabilidadVisaListModification',
        content: 'Deleted an viabilidadVisa'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-viabilidad-visa-delete-popup',
  template: ''
})
export class ViabilidadVisaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ viabilidadVisa }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ViabilidadVisaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.viabilidadVisa = viabilidadVisa;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/viabilidad-visa', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/viabilidad-visa', { outlets: { popup: null } }]);
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
