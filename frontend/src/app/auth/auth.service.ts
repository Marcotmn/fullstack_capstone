import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthData } from './auth-data';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { BehaviorSubject, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  baseURL = environment.baseURL;
  user!: AuthData;
  jwtHelper = new JwtHelperService();
  private authSubj = new BehaviorSubject<null | AuthData>(null);
  user$ = this.authSubj.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  private isAuthenticated = new BehaviorSubject<boolean>(false);

  login(email: string, password: string): Observable<any> {
    const credentials = { email, password };
    return this.http
      .post<any>('http://localhost:3001/auth/login', credentials)
      .pipe(
        map((response) => {
          console.log('Server Response:', response.accessToken);
          if (response.accessToken) {
            console.log('Token:', response.accessToken);
            localStorage.setItem('token', response.accessToken);
            this.isAuthenticated.next(true);
          }
          return response;
        })
      );
  }

  logout() {
    this.authSubj.next(null);
    localStorage.removeItem('token');
  }

  /*registra(data: { nome: string; email: string; password: string }) {
    return this.http.post(`${this.baseURL}register`, data);
  }
*/

  registraAzienda(data: {
    username: string;
    email: string;
    password: string;
    nome: string;
    cognome: string;
    indirizzo: string;
    numeroTelefono: string;
    nomeAzienda: string;
    tipoAzienda: string;
    partitaIvaAzienda: string;
    sitoWeb: string;

    // Altri campi specifici per l'azienda
  }): Observable<any> {
    return this.http.post(`${this.baseURL}auth/register`, data);
  }

  registraPrivato(data: {
    username: string;
    email: string;
    password: string;
    nome: string;
    cognome: string;
    indirizzo: string;
    numeroTelefono: string;
    codiceFiscale: string;
    // Altri campi specifici per il privato
  }): Observable<any> {
    return this.http.post(`${this.baseURL}auth/register`, data);
  }

  registraSviluppatore(data: {
    username: string;
    email: string;
    password: string;
    nome: string;
    cognome: string;
    indirizzo: string;
    numeroTelefono: string;
    titolo: string;
    bio: string;
    linkPortfolio: string;
    competenze: string;
    partitaIvaSviluppatore: string;
    // Altri campi specifici per lo sviluppatore
  }): Observable<any> {
    return this.http.post(`${this.baseURL}auth/register`, data);
  }

  getCurrentUserInfo(): Observable<any> {
    return this.http.get<any>('http://localhost:3001/users/current');
  }

  // Metodo aggiunto per ottenere il token JWT
  getToken(): string | null {
    const utenteLoggato = localStorage.getItem('user');
    if (utenteLoggato) {
      const datiUtente: AuthData = JSON.parse(utenteLoggato);
      return datiUtente.accessToken;
    }
    return null;
  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
