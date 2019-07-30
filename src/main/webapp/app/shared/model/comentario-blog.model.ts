import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IBlogContenido } from 'app/shared/model/blog-contenido.model';

export interface IComentarioBlog {
  id?: number;
  comentario?: any;
  fecha?: Moment;
  calificacion?: number;
  usuario?: IUser;
  tituloBlog?: IBlogContenido;
}

export class ComentarioBlog implements IComentarioBlog {
  constructor(
    public id?: number,
    public comentario?: any,
    public fecha?: Moment,
    public calificacion?: number,
    public usuario?: IUser,
    public tituloBlog?: IBlogContenido
  ) {}
}
