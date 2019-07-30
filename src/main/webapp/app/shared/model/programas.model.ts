import { IInstitucion } from 'app/shared/model/institucion.model';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';

export const enum Monedad {
  COP = 'COP',
  USD = 'USD',
  EUR = 'EUR'
}

export interface IProgramas {
  id?: number;
  registro?: number;
  moneda?: Monedad;
  curso?: string;
  horario?: string;
  frecuencia?: number;
  duracion?: number;
  foto1ContentType?: string;
  foto1?: any;
  foto2ContentType?: string;
  foto2?: any;
  institucion?: IInstitucion;
  ciudad?: ICiudad;
  tipoPrograma?: ITipoPrograma;
}

export class Programas implements IProgramas {
  constructor(
    public id?: number,
    public registro?: number,
    public moneda?: Monedad,
    public curso?: string,
    public horario?: string,
    public frecuencia?: number,
    public duracion?: number,
    public foto1ContentType?: string,
    public foto1?: any,
    public foto2ContentType?: string,
    public foto2?: any,
    public institucion?: IInstitucion,
    public ciudad?: ICiudad,
    public tipoPrograma?: ITipoPrograma
  ) {}
}
