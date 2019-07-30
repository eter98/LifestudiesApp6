import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPasoCRM } from 'app/shared/model/paso-crm.model';

@Component({
  selector: 'jhi-paso-crm-detail',
  templateUrl: './paso-crm-detail.component.html'
})
export class PasoCRMDetailComponent implements OnInit {
  pasoCRM: IPasoCRM;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pasoCRM }) => {
      this.pasoCRM = pasoCRM;
    });
  }

  previousState() {
    window.history.back();
  }
}
