import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITipoPrograma, TipoPrograma } from 'app/shared/model/tipo-programa.model';
import { TipoProgramaService } from './tipo-programa.service';

@Component({
  selector: 'jhi-tipo-programa-update',
  templateUrl: './tipo-programa-update.component.html'
})
export class TipoProgramaUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    tipoPrograma: []
  });

  constructor(protected tipoProgramaService: TipoProgramaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tipoPrograma }) => {
      this.updateForm(tipoPrograma);
    });
  }

  updateForm(tipoPrograma: ITipoPrograma) {
    this.editForm.patchValue({
      id: tipoPrograma.id,
      tipoPrograma: tipoPrograma.tipoPrograma
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tipoPrograma = this.createFromForm();
    if (tipoPrograma.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoProgramaService.update(tipoPrograma));
    } else {
      this.subscribeToSaveResponse(this.tipoProgramaService.create(tipoPrograma));
    }
  }

  private createFromForm(): ITipoPrograma {
    return {
      ...new TipoPrograma(),
      id: this.editForm.get(['id']).value,
      tipoPrograma: this.editForm.get(['tipoPrograma']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoPrograma>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
