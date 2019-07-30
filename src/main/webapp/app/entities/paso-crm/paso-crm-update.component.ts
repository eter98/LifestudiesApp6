import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPasoCRM, PasoCRM } from 'app/shared/model/paso-crm.model';
import { PasoCRMService } from './paso-crm.service';

@Component({
  selector: 'jhi-paso-crm-update',
  templateUrl: './paso-crm-update.component.html'
})
export class PasoCRMUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    codigo: [],
    descripcion: [],
    duracionDias: [],
    estado: []
  });

  constructor(protected pasoCRMService: PasoCRMService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pasoCRM }) => {
      this.updateForm(pasoCRM);
    });
  }

  updateForm(pasoCRM: IPasoCRM) {
    this.editForm.patchValue({
      id: pasoCRM.id,
      codigo: pasoCRM.codigo,
      descripcion: pasoCRM.descripcion,
      duracionDias: pasoCRM.duracionDias,
      estado: pasoCRM.estado
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pasoCRM = this.createFromForm();
    if (pasoCRM.id !== undefined) {
      this.subscribeToSaveResponse(this.pasoCRMService.update(pasoCRM));
    } else {
      this.subscribeToSaveResponse(this.pasoCRMService.create(pasoCRM));
    }
  }

  private createFromForm(): IPasoCRM {
    return {
      ...new PasoCRM(),
      id: this.editForm.get(['id']).value,
      codigo: this.editForm.get(['codigo']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      duracionDias: this.editForm.get(['duracionDias']).value,
      estado: this.editForm.get(['estado']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPasoCRM>>) {
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
