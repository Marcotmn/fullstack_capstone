import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Ruolo } from './ruolo.enum';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  utente: any = {};

  constructor(private authSrv: AuthService, private router: Router) {}

  ngOnInit(): void {}

  registrati(form: NgForm): void {
    if (form.valid) {
      switch (this.utente.ruolo) {
        case Ruolo.AZIENDA:
          this.authSrv.registraAzienda(form.value).subscribe(
            (response) => {
              console.log(response);
              this.router.navigate(['/login']);
            },
            (error) => {
              console.error(error);
              if (error.status === 400) {
                alert('Email già registrata');
                this.router.navigate(['/registrazione']);
              }
            }
          );
          break;
        case Ruolo.PRIVATO:
          this.authSrv.registraPrivato(form.value).subscribe(
            (response) => {
              console.log(response);
              this.router.navigate(['/login']);
            },
            (error) => {
              console.error(error);
              if (error.status === 400) {
                alert('Email già registrata');
                this.router.navigate(['/registrazione']);
              }
            }
          );
          break;
        case Ruolo.SVILUPPATORE:
          this.authSrv.registraSviluppatore(form.value).subscribe(
            (response) => {
              console.log(response);
              this.router.navigate(['/login']);
            },
            (error) => {
              console.error(error);
              if (error.status === 400) {
                alert('Email già registrata');
                this.router.navigate(['/registrazione']);
              }
            }
          );
          break;
        default:
          console.error('Ruolo non valido');
      }
    }
  }
}
