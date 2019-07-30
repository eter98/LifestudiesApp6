import { Moment } from 'moment';

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

export const enum TipoProgramad {
  Programas_Idiomas = 'Programas_Idiomas',
  Curso_Verano_idiomas = 'Curso_Verano_idiomas',
  Programa_preparacion_Examenes_idioma = 'Programa_preparacion_Examenes_idioma',
  Programa_Pregrado = 'Programa_Pregrado',
  Programa_Posgrado = 'Programa_Posgrado',
  Programas_Cortos_Ejecutivos = 'Programas_Cortos_Ejecutivos'
}

export const enum Duraciond {
  UN_MES = 'UN_MES',
  DOS_MESES = 'DOS_MESES',
  TRES_MESES = 'TRES_MESES',
  CUATRO_MESES = 'CUATRO_MESES',
  CINCO_MESES = 'CINCO_MESES',
  SEIS_MESES = 'SEIS_MESES',
  SIETE_MESES = 'SIETE_MESES',
  OCHO_MESES = 'OCHO_MESES',
  NUEVE_MESES = 'NUEVE_MESES',
  DIEZ_MESES = 'DIEZ_MESES',
  ONCE_MESES = 'ONCE_MESES',
  DOCE_MESES = 'DOCE_MESES'
}

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

export const enum Generod {
  Hombre = 'Hombre',
  Mujer = 'Mujer',
  LBGTI = 'LBGTI',
  Indeterminado = 'Indeterminado'
}

export const enum EstadoCivild {
  Soltero = 'Soltero',
  Casado = 'Casado',
  Viudo = 'Viudo',
  Separado = 'Separado',
  Union_Libre = 'Union_Libre'
}

export const enum PersonasCargod {
  NINGUNA = 'NINGUNA',
  UNA = 'UNA',
  DOS = 'DOS',
  TRES = 'TRES',
  MAS_DE_CUATRO = 'MAS_DE_CUATRO'
}

export const enum NivelAcademicod {
  PRIMARIA = 'PRIMARIA',
  SECUNDARIA = 'SECUNDARIA',
  PREGRADO = 'PREGRADO',
  POSGRADO = 'POSGRADO',
  DOCTORADO = 'DOCTORADO'
}

export const enum OcupacionActuald {
  Estudiante = 'Estudiante',
  Estudiante_Graduado_sin_Laborar = 'Estudiante_Graduado_sin_Laborar',
  Empleado = 'Empleado',
  Independiente = 'Independiente',
  Desempleado = 'Desempleado'
}

export const enum PeridoSupencionEstud {
  SEIS_MESES = 'SEIS_MESES',
  UN_ANO = 'UN_ANO',
  DOS_ANOS = 'DOS_ANOS',
  MAS_DOS_ANOS = 'MAS_DOS_ANOS'
}

export const enum TipoContratod {
  TERMINO_FIJO = 'TERMINO_FIJO',
  TERMINO_INDEFNIDO = 'TERMINO_INDEFNIDO',
  PRESTACION_SERVICIOS = 'PRESTACION_SERVICIOS'
}

export const enum NivelSalariald {
  MENOS_US600 = 'MENOS_US600',
  US700_US1000 = 'US700_US1000',
  US1000_US1500 = 'US1000_US1500',
  US1500_US2000 = 'US1500_US2000',
  SUPERIOR_US2000 = 'SUPERIOR_US2000'
}

export const enum TipoLaborIndependiented {
  PROFESIONAL_CUENTA_PROPIA = 'PROFESIONAL_CUENTA_PROPIA',
  ESTABLECIMIENTO_COMERCIO = 'ESTABLECIMIENTO_COMERCIO',
  CONTRATO_PRESTACION_SERVICIOS = 'CONTRATO_PRESTACION_SERVICIOS'
}

export const enum QuienFinanciaEstudiosd {
  Tu_mismo = 'Tu_mismo',
  Un_familiar = 'Un_familiar',
  Empleador = 'Empleador',
  Beca_total = 'Beca_total',
  Beca_Parcial = 'Beca_Parcial'
}

export const enum Parentescod {
  Padre_Madre = 'Padre_Madre',
  Hermano = 'Hermano',
  Abuelos = 'Abuelos',
  Tios = 'Tios',
  Otros = 'Otros'
}

export interface IViabilidadVisa {
  id?: number;
  destino?: Destinod;
  tipoPrograma?: TipoProgramad;
  duracion?: Duraciond;
  nacionalidadPrincipal?: Nacionalidadd;
  otraNacionalidad?: Nacionalidadd;
  fechaNacimiento?: Moment;
  genero?: Generod;
  estadoCivil?: EstadoCivild;
  viajaAcompanado?: boolean;
  personasCargo?: PersonasCargod;
  nivelAcademico?: NivelAcademicod;
  profesion?: string;
  fechaGadruacion?: Moment;
  ocupacionActual?: OcupacionActuald;
  carta?: boolean;
  suspendidoEstudios?: boolean;
  peridoSupencionEstu?: PeridoSupencionEstud;
  razonSuspencion?: string;
  tipoContrato?: TipoContratod;
  licenciaLaboral?: boolean;
  nivelSalarial?: NivelSalariald;
  tipoLaborIndependiente?: TipoLaborIndependiented;
  tiempoIndependiente?: PeridoSupencionEstud;
  nivelIngresosIndependiente?: NivelSalariald;
  tiempoDesempleado?: PeridoSupencionEstud;
  quienFinanciaEstudios?: QuienFinanciaEstudiosd;
  parentesco?: Parentescod;
  presupuestoDisponible?: NivelSalariald;
  ahorroDisponible?: boolean;
  idioma?: boolean;
  certificarIdioma?: boolean;
  certificacionIdioma?: string;
  puntajeCertificacion?: string;
  salidasPais?: string;
  paisesVisitados?: string;
  visaPais?: string;
  estudiadoAnterior?: boolean;
  fueraPais?: boolean;
  paisFuera?: string;
  extenderEstadia?: boolean;
  negadoVisa?: boolean;
  paisNegado?: string;
  familiaresDestino?: boolean;
  estatusMigratorio?: string;
  nombre?: string;
  apelliod?: string;
  correo?: string;
}

export class ViabilidadVisa implements IViabilidadVisa {
  constructor(
    public id?: number,
    public destino?: Destinod,
    public tipoPrograma?: TipoProgramad,
    public duracion?: Duraciond,
    public nacionalidadPrincipal?: Nacionalidadd,
    public otraNacionalidad?: Nacionalidadd,
    public fechaNacimiento?: Moment,
    public genero?: Generod,
    public estadoCivil?: EstadoCivild,
    public viajaAcompanado?: boolean,
    public personasCargo?: PersonasCargod,
    public nivelAcademico?: NivelAcademicod,
    public profesion?: string,
    public fechaGadruacion?: Moment,
    public ocupacionActual?: OcupacionActuald,
    public carta?: boolean,
    public suspendidoEstudios?: boolean,
    public peridoSupencionEstu?: PeridoSupencionEstud,
    public razonSuspencion?: string,
    public tipoContrato?: TipoContratod,
    public licenciaLaboral?: boolean,
    public nivelSalarial?: NivelSalariald,
    public tipoLaborIndependiente?: TipoLaborIndependiented,
    public tiempoIndependiente?: PeridoSupencionEstud,
    public nivelIngresosIndependiente?: NivelSalariald,
    public tiempoDesempleado?: PeridoSupencionEstud,
    public quienFinanciaEstudios?: QuienFinanciaEstudiosd,
    public parentesco?: Parentescod,
    public presupuestoDisponible?: NivelSalariald,
    public ahorroDisponible?: boolean,
    public idioma?: boolean,
    public certificarIdioma?: boolean,
    public certificacionIdioma?: string,
    public puntajeCertificacion?: string,
    public salidasPais?: string,
    public paisesVisitados?: string,
    public visaPais?: string,
    public estudiadoAnterior?: boolean,
    public fueraPais?: boolean,
    public paisFuera?: string,
    public extenderEstadia?: boolean,
    public negadoVisa?: boolean,
    public paisNegado?: string,
    public familiaresDestino?: boolean,
    public estatusMigratorio?: string,
    public nombre?: string,
    public apelliod?: string,
    public correo?: string
  ) {
    this.viajaAcompanado = this.viajaAcompanado || false;
    this.carta = this.carta || false;
    this.suspendidoEstudios = this.suspendidoEstudios || false;
    this.licenciaLaboral = this.licenciaLaboral || false;
    this.ahorroDisponible = this.ahorroDisponible || false;
    this.idioma = this.idioma || false;
    this.certificarIdioma = this.certificarIdioma || false;
    this.estudiadoAnterior = this.estudiadoAnterior || false;
    this.fueraPais = this.fueraPais || false;
    this.extenderEstadia = this.extenderEstadia || false;
    this.negadoVisa = this.negadoVisa || false;
    this.familiaresDestino = this.familiaresDestino || false;
  }
}
