export interface Utente {
  id: string;
  username: string;
  nome: string;
  cognome: string;
  email: string;
  password: string;
  indirizzo: string;
  numeroTelefono: string;
  ruolo: 'PRIVATO' | 'AZIENDA' | 'SVILUPPATORE';

  // Attributi specifici per Azienda
  nomeAzienda?: string;
  tipoAzienda?: string;
  partitaIvaAzienda?: string;
  sitoWeb?: string;

  // Attributo specifico per Privato
  codiceFiscale?: string;

  // Attributi specifici per Sviluppatore
  titolo?: string;
  bio?: string;
  linkPortfolio?: string;
  competenze?: string;
  partitaIvaSviluppatore?: string;
}
