import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICRM } from 'app/shared/model/crm.model';
import { CRMService } from './crm.service';

@Component({
  selector: 'jhi-crm-delete-dialog',
  templateUrl: './crm-delete-dialog.component.html'
})
export class CRMDeleteDialogComponent {
  cRM: ICRM;

  constructor(protected cRMService: CRMService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.cRMService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'cRMListModification',
        content: 'Deleted an cRM'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-crm-delete-popup',
  template: ''
})
export class CRMDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cRM }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CRMDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.cRM = cRM;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/crm', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/crm', { outlets: { popup: null } }]);
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
