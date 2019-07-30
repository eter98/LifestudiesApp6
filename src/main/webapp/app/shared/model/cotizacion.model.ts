import { Moment } from 'moment';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { IProgramas } from 'app/shared/model/programas.model';
import { ICRM } from 'app/shared/model/crm.model';

export interface ICotizacion {
  id?: number;
  fechaViaje?: Moment;
  alojamiento?: boolean;
  pasajeAereo?: boolean;
  transpAeropuerto?: boolean;
  usario?: IPerfilUsuario;
  curso?: IProgramas;
  cRMS?: ICRM[];
}

export class Cotizacion implements ICotizacion {
  constructor(
    public id?: number,
    public fechaViaje?: Moment,
    public alojamiento?: boolean,
    public pasajeAereo?: boolean,
    public transpAeropuerto?: boolean,
    public usario?: IPerfilUsuario,
    public curso?: IProgramas,
    public cRMS?: ICRM[]
  ) {
    this.alojamiento = this.alojamiento || false;
    this.pasajeAereo = this.pasajeAereo || false;
    this.transpAeropuerto = this.transpAeropuerto || false;
  }
}
