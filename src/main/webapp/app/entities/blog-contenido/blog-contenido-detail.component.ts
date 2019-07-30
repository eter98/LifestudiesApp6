import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IBlogContenido } from 'app/shared/model/blog-contenido.model';

@Component({
  selector: 'jhi-blog-contenido-detail',
  templateUrl: './blog-contenido-detail.component.html'
})
export class BlogContenidoDetailComponent implements OnInit {
  blogContenido: IBlogContenido;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ blogContenido }) => {
      this.blogContenido = blogContenido;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
