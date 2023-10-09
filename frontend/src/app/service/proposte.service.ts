import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProposteService {
  private apiUrl = 'http://localhost:3001/proposte';

  constructor(private http: HttpClient) {}

  // Metodo per creare una nuova proposta per un annuncio
  creaProposta(annuncioId: string, payload: any): Observable<any> {
    return this.http.post<any>(
      `${this.apiUrl}/pubblica-proposta/${annuncioId}`,
      payload
    );
  }

  // Metodo per recuperare una proposta specifica per ID
  getPropostaById(propostaId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${propostaId}`);
  }

  // Metodo per recuperare tutte le proposte di un utente
  getProposteByUtente(utenteId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${utenteId}/proposte`);
  }

  // Metodo per accettare una proposta specifica
  accettaProposta(propostaId: string): Observable<any> {
    return this.http.post<any>(
      `${this.apiUrl}/accetta-proposta/${propostaId}`,
      {}
    );
  }

  // Metodo per rifiutare una proposta specifica
  rifiutaProposta(propostaId: string): Observable<any> {
    return this.http.post<any>(
      `${this.apiUrl}/rifiuta-proposta/${propostaId}`,
      {}
    );
  }

  // Metodo per recuperare tutte le proposte associate a un annuncio specifico
  getProposteByAnnuncio(annuncioId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/annuncio/${annuncioId}`);
  }
}
