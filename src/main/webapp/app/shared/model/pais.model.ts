export interface IPais {
  id?: number;
  nombrePais?: string;
  codigoPais?: string;
}

export class Pais implements IPais {
  constructor(public id?: number, public nombrePais?: string, public codigoPais?: string) {}
}
