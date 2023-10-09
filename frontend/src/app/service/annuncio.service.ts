import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Annuncio } from '../components/annunci/annuncio';
import * as UUID from 'uuid';

@Injectable({
  providedIn: 'root',
})
export class AnnuncioService {
  private apiUrl = 'http://localhost:3001/annunci';

  constructor(private http: HttpClient) {}

  //METODO PER CREARE L'ANNUNCIO
  createAnnuncio(payload: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/nuovo-annuncio`, payload);
  }

  //METODO PER RECUPERARE TUTTI GLI ANNUNCI (BACHECA PAGINATA)
  getAllAnnunci(
    page: number,
    size: number,
    sort: string
  ): Observable<Annuncio[]> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', sort);

    return this.http.get<Annuncio[]>(`${this.apiUrl}/bacheca-annunci`, {
      params,
    });
  }

  //METODO PER FILTRARE GLI ANNUNCI IN BACHECA PER CATEGORIA
  getAnnunciByCategoria(categorie: string[]) {
    const categorieString = categorie.join(',');
    return this.http.get<Annuncio[]>(
      `${this.apiUrl}/annunci-per-categoria?categoria=${categorieString}`
    );
  }

  //METODO PER RECUPERARE I DETTAGLI DI UN ANNUNCIO SPECIFICO
  getAnnuncioById(annuncioId: string): Observable<Annuncio> {
    return this.http.get<Annuncio>(`${this.apiUrl}/${annuncioId}`);
  }

  // Metodo per recuperare gli annunci di un utente
  getAnnunciByUtente(utenteId: string): Observable<Annuncio[]> {
    return this.http.get<Annuncio[]>(`${this.apiUrl}/utente/${utenteId}`);
  }
}
