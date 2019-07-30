import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAgencia } from 'app/shared/model/agencia.model';

@Component({
  selector: 'jhi-agencia-detail',
  templateUrl: './agencia-detail.component.html'
})
export class AgenciaDetailComponent implements OnInit {
  agencia: IAgencia;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ agencia }) => {
      this.agencia = agencia;
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
