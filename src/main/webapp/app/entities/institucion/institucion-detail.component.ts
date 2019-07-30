import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IInstitucion } from 'app/shared/model/institucion.model';

@Component({
  selector: 'jhi-institucion-detail',
  templateUrl: './institucion-detail.component.html'
})
export class InstitucionDetailComponent implements OnInit {
  institucion: IInstitucion;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ institucion }) => {
      this.institucion = institucion;
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
