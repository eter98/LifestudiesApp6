import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IExperiencia } from 'app/shared/model/experiencia.model';

@Component({
  selector: 'jhi-experiencia-detail',
  templateUrl: './experiencia-detail.component.html'
})
export class ExperienciaDetailComponent implements OnInit {
  experiencia: IExperiencia;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ experiencia }) => {
      this.experiencia = experiencia;
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
