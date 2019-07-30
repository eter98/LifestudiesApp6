import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';
import { TipoProgramaService } from './tipo-programa.service';

@Component({
  selector: 'jhi-tipo-programa-delete-dialog',
  templateUrl: './tipo-programa-delete-dialog.component.html'
})
export class TipoProgramaDeleteDialogComponent {
  tipoPrograma: ITipoPrograma;

  constructor(
    protected tipoProgramaService: TipoProgramaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tipoProgramaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tipoProgramaListModification',
        content: 'Deleted an tipoPrograma'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tipo-programa-delete-popup',
  template: ''
})
export class TipoProgramaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoPrograma }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TipoProgramaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tipoPrograma = tipoPrograma;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tipo-programa', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tipo-programa', { outlets: { popup: null } }]);
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
