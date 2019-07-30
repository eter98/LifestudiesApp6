import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlogContenido } from 'app/shared/model/blog-contenido.model';
import { BlogContenidoService } from './blog-contenido.service';

@Component({
  selector: 'jhi-blog-contenido-delete-dialog',
  templateUrl: './blog-contenido-delete-dialog.component.html'
})
export class BlogContenidoDeleteDialogComponent {
  blogContenido: IBlogContenido;

  constructor(
    protected blogContenidoService: BlogContenidoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.blogContenidoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'blogContenidoListModification',
        content: 'Deleted an blogContenido'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-blog-contenido-delete-popup',
  template: ''
})
export class BlogContenidoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blogContenido }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BlogContenidoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.blogContenido = blogContenido;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/blog-contenido', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/blog-contenido', { outlets: { popup: null } }]);
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
