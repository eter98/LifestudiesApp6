/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AgenciaService } from 'app/entities/agencia/agencia.service';
import { IAgencia, Agencia } from 'app/shared/model/agencia.model';

describe('Service Tests', () => {
  describe('Agencia Service', () => {
    let injector: TestBed;
    let service: AgenciaService;
    let httpMock: HttpTestingController;
    let elemDefault: IAgencia;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AgenciaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Agencia(
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
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
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

      it('should create a Agencia', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Agencia(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Agencia', async () => {
        const returnedFromService = Object.assign(
          {
            nombreAgencia: 'BBBBBB',
            codigo: 'BBBBBB',
            descripcion: 'BBBBBB',
            encargado: 'BBBBBB',
            direccion: 'BBBBBB',
            telefono: 'BBBBBB',
            email: 'BBBBBB',
            whatsapp: 'BBBBBB',
            asesor1: 'BBBBBB',
            fotoAsesor1: 'BBBBBB',
            asesor2: 'BBBBBB',
            fotoAsesor2: 'BBBBBB',
            asesor3: 'BBBBBB',
            fotoAsesor3: 'BBBBBB',
            logo: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            sede1: 'BBBBBB',
            sede2: 'BBBBBB'
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

      it('should return a list of Agencia', async () => {
        const returnedFromService = Object.assign(
          {
            nombreAgencia: 'BBBBBB',
            codigo: 'BBBBBB',
            descripcion: 'BBBBBB',
            encargado: 'BBBBBB',
            direccion: 'BBBBBB',
            telefono: 'BBBBBB',
            email: 'BBBBBB',
            whatsapp: 'BBBBBB',
            asesor1: 'BBBBBB',
            fotoAsesor1: 'BBBBBB',
            asesor2: 'BBBBBB',
            fotoAsesor2: 'BBBBBB',
            asesor3: 'BBBBBB',
            fotoAsesor3: 'BBBBBB',
            logo: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            sede1: 'BBBBBB',
            sede2: 'BBBBBB'
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

      it('should delete a Agencia', async () => {
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
