import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IProgramas } from 'app/shared/model/programas.model';

@Component({
  selector: 'jhi-programas-detail',
  templateUrl: './programas-detail.component.html'
})
export class ProgramasDetailComponent implements OnInit {
  programas: IProgramas;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ programas }) => {
      this.programas = programas;
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
