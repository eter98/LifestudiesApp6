import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProgramas } from 'app/shared/model/programas.model';
import { ProgramasService } from './programas.service';

@Component({
  selector: 'jhi-programas-delete-dialog',
  templateUrl: './programas-delete-dialog.component.html'
})
export class ProgramasDeleteDialogComponent {
  programas: IProgramas;

  constructor(protected programasService: ProgramasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.programasService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'programasListModification',
        content: 'Deleted an programas'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-programas-delete-popup',
  template: ''
})
export class ProgramasDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ programas }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProgramasDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.programas = programas;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/programas', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/programas', { outlets: { popup: null } }]);
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
