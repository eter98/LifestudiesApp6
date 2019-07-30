import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPasoCRM } from 'app/shared/model/paso-crm.model';
import { PasoCRMService } from './paso-crm.service';

@Component({
  selector: 'jhi-paso-crm-delete-dialog',
  templateUrl: './paso-crm-delete-dialog.component.html'
})
export class PasoCRMDeleteDialogComponent {
  pasoCRM: IPasoCRM;

  constructor(protected pasoCRMService: PasoCRMService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.pasoCRMService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'pasoCRMListModification',
        content: 'Deleted an pasoCRM'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-paso-crm-delete-popup',
  template: ''
})
export class PasoCRMDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pasoCRM }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PasoCRMDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pasoCRM = pasoCRM;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/paso-crm', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/paso-crm', { outlets: { popup: null } }]);
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
