export const enum Estadod {
  Pendiente = 'Pendiente',
  EnProceso = 'EnProceso',
  Finalizado = 'Finalizado',
  Expirado = 'Expirado'
}

export interface IPasoCRM {
  id?: number;
  codigo?: number;
  descripcion?: string;
  duracionDias?: number;
  estado?: Estadod;
}

export class PasoCRM implements IPasoCRM {
  constructor(
    public id?: number,
    public codigo?: number,
    public descripcion?: string,
    public duracionDias?: number,
    public estado?: Estadod
  ) {}
}
