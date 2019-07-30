import { Moment } from 'moment';

export interface IExperiencia {
  id?: number;
  identificacion?: number;
  nombre?: string;
  apellido?: string;
  mail?: string;
  area?: number;
  telefono?: number;
  nacionalidad?: string;
  paisDestino?: string;
  calificaPais?: number;
  programa?: string;
  institucion?: string;
  calificaInstitucion?: number;
  agencia?: string;
  calificaAgencia?: number;
  fecha?: Moment;
  contenido?: any;
  fotoContentType?: string;
  foto?: any;
}

export class Experiencia implements IExperiencia {
  constructor(
    public id?: number,
    public identificacion?: number,
    public nombre?: string,
    public apellido?: string,
    public mail?: string,
    public area?: number,
    public telefono?: number,
    public nacionalidad?: string,
    public paisDestino?: string,
    public calificaPais?: number,
    public programa?: string,
    public institucion?: string,
    public calificaInstitucion?: number,
    public agencia?: string,
    public calificaAgencia?: number,
    public fecha?: Moment,
    public contenido?: any,
    public fotoContentType?: string,
    public foto?: any
  ) {}
}
