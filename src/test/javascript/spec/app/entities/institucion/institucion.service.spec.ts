/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { InstitucionService } from 'app/entities/institucion/institucion.service';
import { IInstitucion, Institucion } from 'app/shared/model/institucion.model';

describe('Service Tests', () => {
  describe('Institucion Service', () => {
    let injector: TestBed;
    let service: InstitucionService;
    let httpMock: HttpTestingController;
    let elemDefault: IInstitucion;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(InstitucionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Institucion(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaInicial: currentDate.format(DATE_FORMAT)
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

      it('should create a Institucion', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaInicial: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaInicial: currentDate
          },
          returnedFromService
        );
        service
          .create(new Institucion(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Institucion', async () => {
        const returnedFromService = Object.assign(
          {
            codigo: 'BBBBBB',
            nombre: 'BBBBBB',
            descripcion: 'BBBBBB',
            direccion: 'BBBBBB',
            website: 'BBBBBB',
            contacto: 'BBBBBB',
            representante: 'BBBBBB',
            skype: 'BBBBBB',
            telefono: 'BBBBBB',
            estatus: 'BBBBBB',
            tipoProgramas: 'BBBBBB',
            programaEstandar: 'BBBBBB',
            programaIntensivo: 'BBBBBB',
            programaNegocios: 'BBBBBB',
            preparacionExamen: 'BBBBBB',
            programaAcademico: 'BBBBBB',
            descuento: 1,
            inscripcion: 'BBBBBB',
            materiales: 'BBBBBB',
            seguroMedico: 'BBBBBB',
            alojamientoSencillo: 'BBBBBB',
            alojamientoDoble: 'BBBBBB',
            transporteAeropuerto: 'BBBBBB',
            tipoCurso: 'BBBBBB',
            temporadaAlta: 'BBBBBB',
            temporadaBaja: 'BBBBBB',
            fechaInicial: currentDate.format(DATE_FORMAT),
            horarios: 'BBBBBB',
            instalaciones: 'BBBBBB',
            nacionalidad: 'BBBBBB',
            logo: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            foto3: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaInicial: currentDate
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

      it('should return a list of Institucion', async () => {
        const returnedFromService = Object.assign(
          {
            codigo: 'BBBBBB',
            nombre: 'BBBBBB',
            descripcion: 'BBBBBB',
            direccion: 'BBBBBB',
            website: 'BBBBBB',
            contacto: 'BBBBBB',
            representante: 'BBBBBB',
            skype: 'BBBBBB',
            telefono: 'BBBBBB',
            estatus: 'BBBBBB',
            tipoProgramas: 'BBBBBB',
            programaEstandar: 'BBBBBB',
            programaIntensivo: 'BBBBBB',
            programaNegocios: 'BBBBBB',
            preparacionExamen: 'BBBBBB',
            programaAcademico: 'BBBBBB',
            descuento: 1,
            inscripcion: 'BBBBBB',
            materiales: 'BBBBBB',
            seguroMedico: 'BBBBBB',
            alojamientoSencillo: 'BBBBBB',
            alojamientoDoble: 'BBBBBB',
            transporteAeropuerto: 'BBBBBB',
            tipoCurso: 'BBBBBB',
            temporadaAlta: 'BBBBBB',
            temporadaBaja: 'BBBBBB',
            fechaInicial: currentDate.format(DATE_FORMAT),
            horarios: 'BBBBBB',
            instalaciones: 'BBBBBB',
            nacionalidad: 'BBBBBB',
            logo: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            foto3: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaInicial: currentDate
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

      it('should delete a Institucion', async () => {
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
