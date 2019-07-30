/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { PerfilUsuarioService } from 'app/entities/perfil-usuario/perfil-usuario.service';
import { IPerfilUsuario, PerfilUsuario } from 'app/shared/model/perfil-usuario.model';

describe('Service Tests', () => {
  describe('PerfilUsuario Service', () => {
    let injector: TestBed;
    let service: PerfilUsuarioService;
    let httpMock: HttpTestingController;
    let elemDefault: IPerfilUsuario;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PerfilUsuarioService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PerfilUsuario(
        0,
        currentDate,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        0,
        false,
        0,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        false,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaInicio: currentDate.format(DATE_FORMAT)
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

      it('should create a PerfilUsuario', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaInicio: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaInicio: currentDate
          },
          returnedFromService
        );
        service
          .create(new PerfilUsuario(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a PerfilUsuario', async () => {
        const returnedFromService = Object.assign(
          {
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            identificacion: 1,
            mail: 'BBBBBB',
            area: 1,
            telefono: 1,
            nivelAcademico: 'BBBBBB',
            areaAcademica: 'BBBBBB',
            terminoAcademico: 1,
            puntajeICFES: 1,
            promedioAcademico: 1,
            dominioIdioma: true,
            idiomas: 'BBBBBB',
            examenIdioma: true,
            examenRealizado: 'BBBBBB',
            puntajeIdioma: 1,
            becaOtorgada: true,
            tipoBeca: 1,
            becaInstitucion: 'BBBBBB',
            grupoSocial: true,
            fundacion: 'BBBBBB',
            monitor: true,
            monitorMateria: 'BBBBBB',
            logrosAcademicos: 'BBBBBB',
            experienciaLaboral: true,
            areaLaboral: 'BBBBBB',
            programarealizar: 1,
            programaArea: 'BBBBBB',
            fechaInicio: currentDate.format(DATE_FORMAT),
            programaEncontrado: true,
            nombrePrograma: 'BBBBBB',
            universidad: 'BBBBBB',
            pais: 1,
            merecedorBeca: 'BBBBBB',
            foto: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaInicio: currentDate
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

      it('should return a list of PerfilUsuario', async () => {
        const returnedFromService = Object.assign(
          {
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            identificacion: 1,
            mail: 'BBBBBB',
            area: 1,
            telefono: 1,
            nivelAcademico: 'BBBBBB',
            areaAcademica: 'BBBBBB',
            terminoAcademico: 1,
            puntajeICFES: 1,
            promedioAcademico: 1,
            dominioIdioma: true,
            idiomas: 'BBBBBB',
            examenIdioma: true,
            examenRealizado: 'BBBBBB',
            puntajeIdioma: 1,
            becaOtorgada: true,
            tipoBeca: 1,
            becaInstitucion: 'BBBBBB',
            grupoSocial: true,
            fundacion: 'BBBBBB',
            monitor: true,
            monitorMateria: 'BBBBBB',
            logrosAcademicos: 'BBBBBB',
            experienciaLaboral: true,
            areaLaboral: 'BBBBBB',
            programarealizar: 1,
            programaArea: 'BBBBBB',
            fechaInicio: currentDate.format(DATE_FORMAT),
            programaEncontrado: true,
            nombrePrograma: 'BBBBBB',
            universidad: 'BBBBBB',
            pais: 1,
            merecedorBeca: 'BBBBBB',
            foto: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaInicio: currentDate
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

      it('should delete a PerfilUsuario', async () => {
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
