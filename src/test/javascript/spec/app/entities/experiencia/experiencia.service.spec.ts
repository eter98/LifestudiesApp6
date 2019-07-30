/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ExperienciaService } from 'app/entities/experiencia/experiencia.service';
import { IExperiencia, Experiencia } from 'app/shared/model/experiencia.model';

describe('Service Tests', () => {
  describe('Experiencia Service', () => {
    let injector: TestBed;
    let service: ExperienciaService;
    let httpMock: HttpTestingController;
    let elemDefault: IExperiencia;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ExperienciaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Experiencia(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fecha: currentDate.format(DATE_FORMAT)
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

      it('should create a Experiencia', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fecha: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fecha: currentDate
          },
          returnedFromService
        );
        service
          .create(new Experiencia(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Experiencia', async () => {
        const returnedFromService = Object.assign(
          {
            identificacion: 1,
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            mail: 'BBBBBB',
            area: 1,
            telefono: 1,
            nacionalidad: 'BBBBBB',
            paisDestino: 'BBBBBB',
            calificaPais: 1,
            programa: 'BBBBBB',
            institucion: 'BBBBBB',
            calificaInstitucion: 1,
            agencia: 'BBBBBB',
            calificaAgencia: 1,
            fecha: currentDate.format(DATE_FORMAT),
            contenido: 'BBBBBB',
            foto: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate
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

      it('should return a list of Experiencia', async () => {
        const returnedFromService = Object.assign(
          {
            identificacion: 1,
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            mail: 'BBBBBB',
            area: 1,
            telefono: 1,
            nacionalidad: 'BBBBBB',
            paisDestino: 'BBBBBB',
            calificaPais: 1,
            programa: 'BBBBBB',
            institucion: 'BBBBBB',
            calificaInstitucion: 1,
            agencia: 'BBBBBB',
            calificaAgencia: 1,
            fecha: currentDate.format(DATE_FORMAT),
            contenido: 'BBBBBB',
            foto: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fecha: currentDate
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

      it('should delete a Experiencia', async () => {
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
