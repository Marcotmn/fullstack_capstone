// annuncio.model.ts

import { CategoriaAnnuncio } from '../crea-annunci/categoria-annunci.enum';

export interface Annuncio {
proposte: any;
  utente: any;
  id: string;
  titolo: string;
  descrizione: string;
  categoria: CategoriaAnnuncio;
  statoAnnuncio: string;
  budgetPrevisto: number;
  dataPubblicazione: string;
}
