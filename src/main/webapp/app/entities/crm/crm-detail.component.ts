import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICRM } from 'app/shared/model/crm.model';

@Component({
  selector: 'jhi-crm-detail',
  templateUrl: './crm-detail.component.html'
})
export class CRMDetailComponent implements OnInit {
  cRM: ICRM;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cRM }) => {
      this.cRM = cRM;
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
