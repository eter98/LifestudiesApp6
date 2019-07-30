import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';

@Component({
  selector: 'jhi-viabilidad-visa-detail',
  templateUrl: './viabilidad-visa-detail.component.html'
})
export class ViabilidadVisaDetailComponent implements OnInit {
  viabilidadVisa: IViabilidadVisa;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ viabilidadVisa }) => {
      this.viabilidadVisa = viabilidadVisa;
    });
  }

  previousState() {
    window.history.back();
  }
}
