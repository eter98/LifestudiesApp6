import { Moment } from 'moment';
import { ICiudad } from 'app/shared/model/ciudad.model';

export interface IInstitucion {
  id?: number;
  codigo?: string;
  nombre?: string;
  descripcion?: any;
  direccion?: string;
  website?: string;
  contacto?: string;
  representante?: string;
  skype?: string;
  telefono?: string;
  estatus?: string;
  tipoProgramas?: string;
  programaEstandar?: string;
  programaIntensivo?: string;
  programaNegocios?: string;
  preparacionExamen?: string;
  programaAcademico?: string;
  descuento?: number;
  inscripcion?: string;
  materiales?: string;
  seguroMedico?: string;
  alojamientoSencillo?: string;
  alojamientoDoble?: string;
  transporteAeropuerto?: string;
  tipoCurso?: string;
  temporadaAlta?: string;
  temporadaBaja?: string;
  fechaInicial?: Moment;
  horarios?: string;
  instalaciones?: string;
  nacionalidad?: string;
  logoContentType?: string;
  logo?: any;
  foto1ContentType?: string;
  foto1?: any;
  foto2ContentType?: string;
  foto2?: any;
  foto3ContentType?: string;
  foto3?: any;
  ciudad?: ICiudad;
}

export class Institucion implements IInstitucion {
  constructor(
    public id?: number,
    public codigo?: string,
    public nombre?: string,
    public descripcion?: any,
    public direccion?: string,
    public website?: string,
    public contacto?: string,
    public representante?: string,
    public skype?: string,
    public telefono?: string,
    public estatus?: string,
    public tipoProgramas?: string,
    public programaEstandar?: string,
    public programaIntensivo?: string,
    public programaNegocios?: string,
    public preparacionExamen?: string,
    public programaAcademico?: string,
    public descuento?: number,
    public inscripcion?: string,
    public materiales?: string,
    public seguroMedico?: string,
    public alojamientoSencillo?: string,
    public alojamientoDoble?: string,
    public transporteAeropuerto?: string,
    public tipoCurso?: string,
    public temporadaAlta?: string,
    public temporadaBaja?: string,
    public fechaInicial?: Moment,
    public horarios?: string,
    public instalaciones?: string,
    public nacionalidad?: string,
    public logoContentType?: string,
    public logo?: any,
    public foto1ContentType?: string,
    public foto1?: any,
    public foto2ContentType?: string,
    public foto2?: any,
    public foto3ContentType?: string,
    public foto3?: any,
    public ciudad?: ICiudad
  ) {}
}
