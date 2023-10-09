import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  user: any;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Verifica se l'utente è autenticato
    if (this.authService.isLoggedIn()) {
      // Se l'utente è autenticato, reindirizza alla pagina di benvenuto autenticata
      this.router.navigate(['/annunci/bacheca-annunci']);
    }
  }
}
