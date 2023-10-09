import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:3001/utenti';

  constructor(private http: HttpClient) {}

  // Metodo per recuperare le informazioni dell'utente corrente
  getCurrentUser(): Observable<any> {
    const url = `${this.baseUrl}/current`; 
    return this.http.get(url);
  }
}
