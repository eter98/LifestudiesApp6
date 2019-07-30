/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ViabilidadVisaService } from 'app/entities/viabilidad-visa/viabilidad-visa.service';
import {
  IViabilidadVisa,
  ViabilidadVisa,
  Destinod,
  TipoProgramad,
  Duraciond,
  Nacionalidadd,
  Generod,
  EstadoCivild,
  PersonasCargod,
  NivelAcademicod,
  OcupacionActuald,
  PeridoSupencionEstud,
  TipoContratod,
  NivelSalariald,
  TipoLaborIndependiented,
  QuienFinanciaEstudiosd,
  Parentescod
} from 'app/shared/model/viabilidad-visa.model';

describe('Service Tests', () => {
  describe('ViabilidadVisa Service', () => {
    let injector: TestBed;
    let service: ViabilidadVisaService;
    let httpMock: HttpTestingController;
    let elemDefault: IViabilidadVisa;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ViabilidadVisaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ViabilidadVisa(
        0,
        Destinod.ALEMANIA,
        TipoProgramad.Programas_Idiomas,
        Duraciond.UN_MES,
        Nacionalidadd.Argentina,
        Nacionalidadd.Argentina,
        currentDate,
        Generod.Hombre,
        EstadoCivild.Soltero,
        false,
        PersonasCargod.NINGUNA,
        NivelAcademicod.PRIMARIA,
        'AAAAAAA',
        currentDate,
        OcupacionActuald.Estudiante,
        false,
        false,
        PeridoSupencionEstud.SEIS_MESES,
        'AAAAAAA',
        TipoContratod.TERMINO_FIJO,
        false,
        NivelSalariald.MENOS_US600,
        TipoLaborIndependiented.PROFESIONAL_CUENTA_PROPIA,
        PeridoSupencionEstud.SEIS_MESES,
        NivelSalariald.MENOS_US600,
        PeridoSupencionEstud.SEIS_MESES,
        QuienFinanciaEstudiosd.Tu_mismo,
        Parentescod.Padre_Madre,
        NivelSalariald.MENOS_US600,
        false,
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaGadruacion: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a ViabilidadVisa', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaGadruacion: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaGadruacion: currentDate
          },
          returnedFromService
        );
        service
          .create(new ViabilidadVisa(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ViabilidadVisa', async () => {
        const returnedFromService = Object.assign(
          {
            destino: 'BBBBBB',
            tipoPrograma: 'BBBBBB',
            duracion: 'BBBBBB',
            nacionalidadPrincipal: 'BBBBBB',
            otraNacionalidad: 'BBBBBB',
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            genero: 'BBBBBB',
            estadoCivil: 'BBBBBB',
            viajaAcompanado: true,
            personasCargo: 'BBBBBB',
            nivelAcademico: 'BBBBBB',
            profesion: 'BBBBBB',
            fechaGadruacion: currentDate.format(DATE_FORMAT),
            ocupacionActual: 'BBBBBB',
            carta: true,
            suspendidoEstudios: true,
            peridoSupencionEstu: 'BBBBBB',
            razonSuspencion: 'BBBBBB',
            tipoContrato: 'BBBBBB',
            licenciaLaboral: true,
            nivelSalarial: 'BBBBBB',
            tipoLaborIndependiente: 'BBBBBB',
            tiempoIndependiente: 'BBBBBB',
            nivelIngresosIndependiente: 'BBBBBB',
            tiempoDesempleado: 'BBBBBB',
            quienFinanciaEstudios: 'BBBBBB',
            parentesco: 'BBBBBB',
            presupuestoDisponible: 'BBBBBB',
            ahorroDisponible: true,
            idioma: true,
            certificarIdioma: true,
            certificacionIdioma: 'BBBBBB',
            puntajeCertificacion: 'BBBBBB',
            salidasPais: 'BBBBBB',
            paisesVisitados: 'BBBBBB',
            visaPais: 'BBBBBB',
            estudiadoAnterior: true,
            fueraPais: true,
            paisFuera: 'BBBBBB',
            extenderEstadia: true,
            negadoVisa: true,
            paisNegado: 'BBBBBB',
            familiaresDestino: true,
            estatusMigratorio: 'BBBBBB',
            nombre: 'BBBBBB',
            apelliod: 'BBBBBB',
            correo: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaGadruacion: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of ViabilidadVisa', async () => {
        const returnedFromService = Object.assign(
          {
            destino: 'BBBBBB',
            tipoPrograma: 'BBBBBB',
            duracion: 'BBBBBB',
            nacionalidadPrincipal: 'BBBBBB',
            otraNacionalidad: 'BBBBBB',
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            genero: 'BBBBBB',
            estadoCivil: 'BBBBBB',
            viajaAcompanado: true,
            personasCargo: 'BBBBBB',
            nivelAcademico: 'BBBBBB',
            profesion: 'BBBBBB',
            fechaGadruacion: currentDate.format(DATE_FORMAT),
            ocupacionActual: 'BBBBBB',
            carta: true,
            suspendidoEstudios: true,
            peridoSupencionEstu: 'BBBBBB',
            razonSuspencion: 'BBBBBB',
            tipoContrato: 'BBBBBB',
            licenciaLaboral: true,
            nivelSalarial: 'BBBBBB',
            tipoLaborIndependiente: 'BBBBBB',
            tiempoIndependiente: 'BBBBBB',
            nivelIngresosIndependiente: 'BBBBBB',
            tiempoDesempleado: 'BBBBBB',
            quienFinanciaEstudios: 'BBBBBB',
            parentesco: 'BBBBBB',
            presupuestoDisponible: 'BBBBBB',
            ahorroDisponible: true,
            idioma: true,
            certificarIdioma: true,
            certificacionIdioma: 'BBBBBB',
            puntajeCertificacion: 'BBBBBB',
            salidasPais: 'BBBBBB',
            paisesVisitados: 'BBBBBB',
            visaPais: 'BBBBBB',
            estudiadoAnterior: true,
            fueraPais: true,
            paisFuera: 'BBBBBB',
            extenderEstadia: true,
            negadoVisa: true,
            paisNegado: 'BBBBBB',
            familiaresDestino: true,
            estatusMigratorio: 'BBBBBB',
            nombre: 'BBBBBB',
            apelliod: 'BBBBBB',
            correo: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaGadruacion: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ViabilidadVisa', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
