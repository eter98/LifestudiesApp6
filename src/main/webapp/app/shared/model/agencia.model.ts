import { IPais } from 'app/shared/model/pais.model';

export interface IAgencia {
  id?: number;
  nombreAgencia?: string;
  codigo?: string;
  descripcion?: any;
  encargado?: string;
  direccion?: string;
  telefono?: string;
  email?: string;
  whatsapp?: string;
  asesor1?: string;
  fotoAsesor1ContentType?: string;
  fotoAsesor1?: any;
  asesor2?: string;
  fotoAsesor2ContentType?: string;
  fotoAsesor2?: any;
  asesor3?: string;
  fotoAsesor3ContentType?: string;
  fotoAsesor3?: any;
  logoContentType?: string;
  logo?: any;
  foto1ContentType?: string;
  foto1?: any;
  foto2ContentType?: string;
  foto2?: any;
  sede1?: string;
  sede2?: string;
  pais?: IPais;
}

export class Agencia implements IAgencia {
  constructor(
    public id?: number,
    public nombreAgencia?: string,
    public codigo?: string,
    public descripcion?: any,
    public encargado?: string,
    public direccion?: string,
    public telefono?: string,
    public email?: string,
    public whatsapp?: string,
    public asesor1?: string,
    public fotoAsesor1ContentType?: string,
    public fotoAsesor1?: any,
    public asesor2?: string,
    public fotoAsesor2ContentType?: string,
    public fotoAsesor2?: any,
    public asesor3?: string,
    public fotoAsesor3ContentType?: string,
    public fotoAsesor3?: any,
    public logoContentType?: string,
    public logo?: any,
    public foto1ContentType?: string,
    public foto1?: any,
    public foto2ContentType?: string,
    public foto2?: any,
    public sede1?: string,
    public sede2?: string,
    public pais?: IPais
  ) {}
}
