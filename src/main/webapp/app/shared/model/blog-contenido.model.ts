import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export const enum Nacionalidadd {
  Argentina = 'Argentina',
  Bolivia = 'Bolivia',
  Brasil = 'Brasil',
  Chile = 'Chile',
  Colombia = 'Colombia',
  Costa_Rica = 'Costa_Rica',
  Ecuador = 'Ecuador',
  Guatemala = 'Guatemala',
  Honduras = 'Honduras',
  Mexico = 'Mexico',
  Nicaragua = 'Nicaragua',
  Panama = 'Panama',
  Paraguay = 'Paraguay',
  Peru = 'Peru',
  Uruguay = 'Uruguay',
  Venezuela = 'Venezuela'
}

export const enum Destinod {
  ALEMANIA = 'ALEMANIA',
  AUSTRALIA = 'AUSTRALIA',
  CANADA = 'CANADA',
  ESPANA = 'ESPANA',
  ESTADOS_UNIDOS = 'ESTADOS_UNIDOS',
  FRANCIA = 'FRANCIA',
  INGLATERRA = 'INGLATERRA',
  IRLANDA = 'IRLANDA',
  ITALIA = 'ITALIA',
  JAPON = 'JAPON',
  MALTA = 'MALTA',
  NUEVA_ZELANDA = 'NUEVA_ZELANDA',
  SUDAFRICA = 'SUDAFRICA',
  SUIZA = 'SUIZA'
}

export interface IBlogContenido {
  id?: number;
  nombre?: string;
  apellido?: string;
  correo?: string;
  nacionalidad?: Nacionalidadd;
  paisEstudio?: Destinod;
  calificacionPais?: number;
  ciudadVive?: string;
  calificacionCiudad?: number;
  programaRealizado?: string;
  institucionEstudio?: string;
  calificacionInstitucion?: number;
  agenciaEstudios?: boolean;
  nombreAgencia?: string;
  calificacionAgencia?: number;
  calificacionExperienciaEstudio?: number;
  fecha?: Moment;
  titulo?: string;
  imagenContentType?: string;
  imagen?: any;
  contenido?: any;
  usuario?: IUser;
}

export class BlogContenido implements IBlogContenido {
  constructor(
    public id?: number,
    public nombre?: string,
    public apellido?: string,
    public correo?: string,
    public nacionalidad?: Nacionalidadd,
    public paisEstudio?: Destinod,
    public calificacionPais?: number,
    public ciudadVive?: string,
    public calificacionCiudad?: number,
    public programaRealizado?: string,
    public institucionEstudio?: string,
    public calificacionInstitucion?: number,
    public agenciaEstudios?: boolean,
    public nombreAgencia?: string,
    public calificacionAgencia?: number,
    public calificacionExperienciaEstudio?: number,
    public fecha?: Moment,
    public titulo?: string,
    public imagenContentType?: string,
    public imagen?: any,
    public contenido?: any,
    public usuario?: IUser
  ) {
    this.agenciaEstudios = this.agenciaEstudios || false;
  }
}
