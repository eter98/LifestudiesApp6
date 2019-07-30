import { Moment } from 'moment';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { IPasoCRM } from 'app/shared/model/paso-crm.model';
import { IAgencia } from 'app/shared/model/agencia.model';
import { ICotizacion } from 'app/shared/model/cotizacion.model';

export const enum Asesord {
  CarlosFranco = 'CarlosFranco',
  CatalinaFranco = 'CatalinaFranco'
}

export const enum Estadod {
  Pendiente = 'Pendiente',
  EnProceso = 'EnProceso',
  Finalizado = 'Finalizado',
  Expirado = 'Expirado'
}

export const enum TipoLeadd {
  AltaPrioridad = 'AltaPrioridad',
  MediaPrioridad = 'MediaPrioridad',
  BajaPrioridad = 'BajaPrioridad'
}

export interface ICRM {
  id?: number;
  fecha?: Moment;
  descripcion?: string;
  documentoContentType?: string;
  documento?: any;
  asesor?: Asesord;
  estado?: Estadod;
  tipoLead?: TipoLeadd;
  comentarios?: any;
  cerrado?: boolean;
  usuario?: IPerfilUsuario;
  proceso?: IPasoCRM;
  agencia?: IAgencia;
  cotizacion?: ICotizacion;
}

export class CRM implements ICRM {
  constructor(
    public id?: number,
    public fecha?: Moment,
    public descripcion?: string,
    public documentoContentType?: string,
    public documento?: any,
    public asesor?: Asesord,
    public estado?: Estadod,
    public tipoLead?: TipoLeadd,
    public comentarios?: any,
    public cerrado?: boolean,
    public usuario?: IPerfilUsuario,
    public proceso?: IPasoCRM,
    public agencia?: IAgencia,
    public cotizacion?: ICotizacion
  ) {
    this.cerrado = this.cerrado || false;
  }
}
