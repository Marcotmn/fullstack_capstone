import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { AnnuncioService } from 'src/app/service/annuncio.service';
import { Utente } from './utente.interface';
import { Annuncio } from '../annunci/annuncio';
import { ProposteService } from 'src/app/service/proposte.service';

@Component({
  selector: 'app-user',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UserComponent implements OnInit {
  utente: Utente | null = null;
  annunci: Annuncio[] = [];
  proposte: any[] = [];

  constructor(
    private userService: UserService,
    private annuncioService: AnnuncioService,
    private proposteService: ProposteService
  ) {}

  ngOnInit(): void {
    // Recupera l'utente corrente
    this.userService.getCurrentUser().subscribe((utente) => {
      this.utente = utente;

      // Seleziona gli annunci solo per utenti PRIVATO e AZIENDA
      if (
        utente &&
        (utente.ruolo === 'PRIVATO' || utente.ruolo === 'AZIENDA')
      ) {
        this.annuncioService
          .getAnnunciByUtente(utente.id)
          .subscribe((annunci) => {
            this.annunci = annunci;
          });
      }
      // Recupera le proposte solo per utenti SVILUPPATORE
      if (utente.ruolo === 'SVILUPPATORE') {
        this.proposteService
          .getProposteByUtente(utente.id)
          .subscribe((proposte) => {
            this.proposte = proposte;
          });
      }
    });
  }
}
