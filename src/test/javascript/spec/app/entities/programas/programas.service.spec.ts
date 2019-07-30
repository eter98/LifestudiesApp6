/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ProgramasService } from 'app/entities/programas/programas.service';
import { IProgramas, Programas, Monedad } from 'app/shared/model/programas.model';

describe('Service Tests', () => {
  describe('Programas Service', () => {
    let injector: TestBed;
    let service: ProgramasService;
    let httpMock: HttpTestingController;
    let elemDefault: IProgramas;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProgramasService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Programas(0, 0, Monedad.COP, 'AAAAAAA', 'AAAAAAA', 0, 0, 'image/png', 'AAAAAAA', 'image/png', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Programas', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Programas(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Programas', async () => {
        const returnedFromService = Object.assign(
          {
            registro: 1,
            moneda: 'BBBBBB',
            curso: 'BBBBBB',
            horario: 'BBBBBB',
            frecuencia: 1,
            duracion: 1,
            foto1: 'BBBBBB',
            foto2: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Programas', async () => {
        const returnedFromService = Object.assign(
          {
            registro: 1,
            moneda: 'BBBBBB',
            curso: 'BBBBBB',
            horario: 'BBBBBB',
            frecuencia: 1,
            duracion: 1,
            foto1: 'BBBBBB',
            foto2: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a Programas', async () => {
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
