/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BlogContenidoService } from 'app/entities/blog-contenido/blog-contenido.service';
import { IBlogContenido, BlogContenido, Nacionalidadd, Destinod } from 'app/shared/model/blog-contenido.model';

describe('Service Tests', () => {
  describe('BlogContenido Service', () => {
    let injector: TestBed;
    let service: BlogContenidoService;
    let httpMock: HttpTestingController;
    let elemDefault: IBlogContenido;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BlogContenidoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BlogContenido(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Nacionalidadd.Argentina,
        Destinod.ALEMANIA,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        'AAAAAAA',
        0,
        0,
        currentDate,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
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

      it('should create a BlogContenido', async () => {
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
          .create(new BlogContenido(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a BlogContenido', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            correo: 'BBBBBB',
            nacionalidad: 'BBBBBB',
            paisEstudio: 'BBBBBB',
            calificacionPais: 1,
            ciudadVive: 'BBBBBB',
            calificacionCiudad: 1,
            programaRealizado: 'BBBBBB',
            institucionEstudio: 'BBBBBB',
            calificacionInstitucion: 1,
            agenciaEstudios: true,
            nombreAgencia: 'BBBBBB',
            calificacionAgencia: 1,
            calificacionExperienciaEstudio: 1,
            fecha: currentDate.format(DATE_FORMAT),
            titulo: 'BBBBBB',
            imagen: 'BBBBBB',
            contenido: 'BBBBBB'
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

      it('should return a list of BlogContenido', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            correo: 'BBBBBB',
            nacionalidad: 'BBBBBB',
            paisEstudio: 'BBBBBB',
            calificacionPais: 1,
            ciudadVive: 'BBBBBB',
            calificacionCiudad: 1,
            programaRealizado: 'BBBBBB',
            institucionEstudio: 'BBBBBB',
            calificacionInstitucion: 1,
            agenciaEstudios: true,
            nombreAgencia: 'BBBBBB',
            calificacionAgencia: 1,
            calificacionExperienciaEstudio: 1,
            fecha: currentDate.format(DATE_FORMAT),
            titulo: 'BBBBBB',
            imagen: 'BBBBBB',
            contenido: 'BBBBBB'
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

      it('should delete a BlogContenido', async () => {
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
