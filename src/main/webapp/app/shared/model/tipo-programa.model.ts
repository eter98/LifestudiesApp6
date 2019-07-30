export interface ITipoPrograma {
  id?: number;
  tipoPrograma?: string;
}

export class TipoPrograma implements ITipoPrograma {
  constructor(public id?: number, public tipoPrograma?: string) {}
}
