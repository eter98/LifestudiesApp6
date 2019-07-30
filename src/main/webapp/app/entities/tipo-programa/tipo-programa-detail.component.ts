import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';

@Component({
  selector: 'jhi-tipo-programa-detail',
  templateUrl: './tipo-programa-detail.component.html'
})
export class TipoProgramaDetailComponent implements OnInit {
  tipoPrograma: ITipoPrograma;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoPrograma }) => {
      this.tipoPrograma = tipoPrograma;
    });
  }

  previousState() {
    window.history.back();
  }
}
