import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';

@Component({
  selector: 'jhi-perfil-usuario-detail',
  templateUrl: './perfil-usuario-detail.component.html'
})
export class PerfilUsuarioDetailComponent implements OnInit {
  perfilUsuario: IPerfilUsuario;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ perfilUsuario }) => {
      this.perfilUsuario = perfilUsuario;
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
