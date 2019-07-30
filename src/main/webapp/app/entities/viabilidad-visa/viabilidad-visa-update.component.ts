import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IViabilidadVisa, ViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';
import { ViabilidadVisaService } from './viabilidad-visa.service';

@Component({
  selector: 'jhi-viabilidad-visa-update',
  templateUrl: './viabilidad-visa-update.component.html'
})
export class ViabilidadVisaUpdateComponent implements OnInit {
  isSaving: boolean;
  fechaNacimientoDp: any;
  fechaGadruacionDp: any;

  editForm = this.fb.group({
    id: [],
    destino: [],
    tipoPrograma: [],
    duracion: [],
    nacionalidadPrincipal: [],
    otraNacionalidad: [],
    fechaNacimiento: [],
    genero: [],
    estadoCivil: [],
    viajaAcompanado: [],
    personasCargo: [],
    nivelAcademico: [],
    profesion: [],
    fechaGadruacion: [],
    ocupacionActual: [],
    carta: [],
    suspendidoEstudios: [],
    peridoSupencionEstu: [],
    razonSuspencion: [],
    tipoContrato: [],
    licenciaLaboral: [],
    nivelSalarial: [],
    tipoLaborIndependiente: [],
    tiempoIndependiente: [],
    nivelIngresosIndependiente: [],
    tiempoDesempleado: [],
    quienFinanciaEstudios: [],
    parentesco: [],
    presupuestoDisponible: [],
    ahorroDisponible: [],
    idioma: [],
    certificarIdioma: [],
    certificacionIdioma: [],
    puntajeCertificacion: [],
    salidasPais: [],
    paisesVisitados: [],
    visaPais: [],
    estudiadoAnterior: [],
    fueraPais: [],
    paisFuera: [],
    extenderEstadia: [],
    negadoVisa: [],
    paisNegado: [],
    familiaresDestino: [],
    estatusMigratorio: [],
    nombre: [],
    apelliod: [],
    correo: []
  });

  constructor(protected viabilidadVisaService: ViabilidadVisaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ viabilidadVisa }) => {
      this.updateForm(viabilidadVisa);
    });
  }

  updateForm(viabilidadVisa: IViabilidadVisa) {
    this.editForm.patchValue({
      id: viabilidadVisa.id,
      destino: viabilidadVisa.destino,
      tipoPrograma: viabilidadVisa.tipoPrograma,
      duracion: viabilidadVisa.duracion,
      nacionalidadPrincipal: viabilidadVisa.nacionalidadPrincipal,
      otraNacionalidad: viabilidadVisa.otraNacionalidad,
      fechaNacimiento: viabilidadVisa.fechaNacimiento,
      genero: viabilidadVisa.genero,
      estadoCivil: viabilidadVisa.estadoCivil,
      viajaAcompanado: viabilidadVisa.viajaAcompanado,
      personasCargo: viabilidadVisa.personasCargo,
      nivelAcademico: viabilidadVisa.nivelAcademico,
      profesion: viabilidadVisa.profesion,
      fechaGadruacion: viabilidadVisa.fechaGadruacion,
      ocupacionActual: viabilidadVisa.ocupacionActual,
      carta: viabilidadVisa.carta,
      suspendidoEstudios: viabilidadVisa.suspendidoEstudios,
      peridoSupencionEstu: viabilidadVisa.peridoSupencionEstu,
      razonSuspencion: viabilidadVisa.razonSuspencion,
      tipoContrato: viabilidadVisa.tipoContrato,
      licenciaLaboral: viabilidadVisa.licenciaLaboral,
      nivelSalarial: viabilidadVisa.nivelSalarial,
      tipoLaborIndependiente: viabilidadVisa.tipoLaborIndependiente,
      tiempoIndependiente: viabilidadVisa.tiempoIndependiente,
      nivelIngresosIndependiente: viabilidadVisa.nivelIngresosIndependiente,
      tiempoDesempleado: viabilidadVisa.tiempoDesempleado,
      quienFinanciaEstudios: viabilidadVisa.quienFinanciaEstudios,
      parentesco: viabilidadVisa.parentesco,
      presupuestoDisponible: viabilidadVisa.presupuestoDisponible,
      ahorroDisponible: viabilidadVisa.ahorroDisponible,
      idioma: viabilidadVisa.idioma,
      certificarIdioma: viabilidadVisa.certificarIdioma,
      certificacionIdioma: viabilidadVisa.certificacionIdioma,
      puntajeCertificacion: viabilidadVisa.puntajeCertificacion,
      salidasPais: viabilidadVisa.salidasPais,
      paisesVisitados: viabilidadVisa.paisesVisitados,
      visaPais: viabilidadVisa.visaPais,
      estudiadoAnterior: viabilidadVisa.estudiadoAnterior,
      fueraPais: viabilidadVisa.fueraPais,
      paisFuera: viabilidadVisa.paisFuera,
      extenderEstadia: viabilidadVisa.extenderEstadia,
      negadoVisa: viabilidadVisa.negadoVisa,
      paisNegado: viabilidadVisa.paisNegado,
      familiaresDestino: viabilidadVisa.familiaresDestino,
      estatusMigratorio: viabilidadVisa.estatusMigratorio,
      nombre: viabilidadVisa.nombre,
      apelliod: viabilidadVisa.apelliod,
      correo: viabilidadVisa.correo
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const viabilidadVisa = this.createFromForm();
    if (viabilidadVisa.id !== undefined) {
      this.subscribeToSaveResponse(this.viabilidadVisaService.update(viabilidadVisa));
    } else {
      this.subscribeToSaveResponse(this.viabilidadVisaService.create(viabilidadVisa));
    }
  }

  private createFromForm(): IViabilidadVisa {
    return {
      ...new ViabilidadVisa(),
      id: this.editForm.get(['id']).value,
      destino: this.editForm.get(['destino']).value,
      tipoPrograma: this.editForm.get(['tipoPrograma']).value,
      duracion: this.editForm.get(['duracion']).value,
      nacionalidadPrincipal: this.editForm.get(['nacionalidadPrincipal']).value,
      otraNacionalidad: this.editForm.get(['otraNacionalidad']).value,
      fechaNacimiento: this.editForm.get(['fechaNacimiento']).value,
      genero: this.editForm.get(['genero']).value,
      estadoCivil: this.editForm.get(['estadoCivil']).value,
      viajaAcompanado: this.editForm.get(['viajaAcompanado']).value,
      personasCargo: this.editForm.get(['personasCargo']).value,
      nivelAcademico: this.editForm.get(['nivelAcademico']).value,
      profesion: this.editForm.get(['profesion']).value,
      fechaGadruacion: this.editForm.get(['fechaGadruacion']).value,
      ocupacionActual: this.editForm.get(['ocupacionActual']).value,
      carta: this.editForm.get(['carta']).value,
      suspendidoEstudios: this.editForm.get(['suspendidoEstudios']).value,
      peridoSupencionEstu: this.editForm.get(['peridoSupencionEstu']).value,
      razonSuspencion: this.editForm.get(['razonSuspencion']).value,
      tipoContrato: this.editForm.get(['tipoContrato']).value,
      licenciaLaboral: this.editForm.get(['licenciaLaboral']).value,
      nivelSalarial: this.editForm.get(['nivelSalarial']).value,
      tipoLaborIndependiente: this.editForm.get(['tipoLaborIndependiente']).value,
      tiempoIndependiente: this.editForm.get(['tiempoIndependiente']).value,
      nivelIngresosIndependiente: this.editForm.get(['nivelIngresosIndependiente']).value,
      tiempoDesempleado: this.editForm.get(['tiempoDesempleado']).value,
      quienFinanciaEstudios: this.editForm.get(['quienFinanciaEstudios']).value,
      parentesco: this.editForm.get(['parentesco']).value,
      presupuestoDisponible: this.editForm.get(['presupuestoDisponible']).value,
      ahorroDisponible: this.editForm.get(['ahorroDisponible']).value,
      idioma: this.editForm.get(['idioma']).value,
      certificarIdioma: this.editForm.get(['certificarIdioma']).value,
      certificacionIdioma: this.editForm.get(['certificacionIdioma']).value,
      puntajeCertificacion: this.editForm.get(['puntajeCertificacion']).value,
      salidasPais: this.editForm.get(['salidasPais']).value,
      paisesVisitados: this.editForm.get(['paisesVisitados']).value,
      visaPais: this.editForm.get(['visaPais']).value,
      estudiadoAnterior: this.editForm.get(['estudiadoAnterior']).value,
      fueraPais: this.editForm.get(['fueraPais']).value,
      paisFuera: this.editForm.get(['paisFuera']).value,
      extenderEstadia: this.editForm.get(['extenderEstadia']).value,
      negadoVisa: this.editForm.get(['negadoVisa']).value,
      paisNegado: this.editForm.get(['paisNegado']).value,
      familiaresDestino: this.editForm.get(['familiaresDestino']).value,
      estatusMigratorio: this.editForm.get(['estatusMigratorio']).value,
      nombre: this.editForm.get(['nombre']).value,
      apelliod: this.editForm.get(['apelliod']).value,
      correo: this.editForm.get(['correo']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IViabilidadVisa>>) {
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
