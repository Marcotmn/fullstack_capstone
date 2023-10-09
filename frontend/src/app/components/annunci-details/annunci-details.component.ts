import { Component, OnInit } from '@angular/core';
import { AnnuncioService } from 'src/app/service/annuncio.service';
import { ProposteService } from 'src/app/service/proposte.service';
import { ActivatedRoute } from '@angular/router';
import { Annuncio } from '../annunci/annuncio';
import { Proposta } from '../crea-proposte/proposta.interface';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-annunci-details',
  templateUrl: './annunci-details.component.html',
  styleUrls: ['./annunci-details.component.scss'],
})
export class AnnunciDetailsComponent implements OnInit {
  annuncio!: Annuncio;
  utente: any;
  annuncioId!: string;
  proposte: Proposta[] = [];
  isProprietarioAnnuncio = false;
  isSviluppatore = false;

  constructor(
    private annuncioService: AnnuncioService,
    private proposteService: ProposteService,
    private route: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.annuncioId = this.route.snapshot.params['annuncioId'];
    if (this.annuncioId) {
      this.caricaDettagliAnnuncio(this.annuncioId);
      this.caricaProposteAnnuncio(this.annuncioId);
    }
  }

  caricaDettagliAnnuncio(annuncioId: string) {
    this.annuncioService.getAnnuncioById(annuncioId).subscribe({
      next: (annuncio) => {
        console.log("Dettagli dell'annuncio:", annuncio);
        this.annuncio = annuncio;
        this.utente = annuncio.utente;

        this.userService.getCurrentUser().subscribe({
          next: (utenteAutenticato) => {
            this.isProprietarioAnnuncio =
              utenteAutenticato.id === this.utente.id;

            // Aggiungi qui la condizione per verificare se l'utente è uno sviluppatore
            // e assegna un valore a una variabile booleana che userai nel template.
            this.isSviluppatore = utenteAutenticato.ruolo === 'SVILUPPATORE';
          },
          error: (error) => {
            console.error(
              "Errore nel recupero delle informazioni dell'utente autenticato:",
              error
            );
          },
        });
      },
      error: (error) => {
        console.error("Errore nel recupero dei dettagli dell'annuncio:", error);
      },
    });
  }

  caricaProposteAnnuncio(annuncioId: string) {
    this.userService.getCurrentUser().subscribe({
      next: (utenteAutenticato) => {
        this.isProprietarioAnnuncio = utenteAutenticato.id === this.utente.id;

        if (this.isProprietarioAnnuncio) {
          this.proposteService.getProposteByAnnuncio(annuncioId).subscribe({
            next: (proposte) => {
              console.log("Proposte dell'annuncio:", proposte);
              this.proposte = proposte;
            },
            error: (error) => {
              console.error(
                "Errore nel recupero delle proposte dell'annuncio:",
                error
              );
            },
          });
        }
      },
      error: (error) => {
        console.error(
          "Errore nel recupero delle informazioni dell'utente autenticato:",
          error
        );
      },
    });
  }

  accettaProposta(proposta: Proposta) {
    this.proposteService.accettaProposta(proposta.id).subscribe({
      next: (response) => {
        alert(
          'Proposta accettata con successo! invieremo una notifica allo sviluppatore'
        );
        proposta.statoProposta = 'ACCETTATA';
      },
      error: (error) => {},
    });
  }

  rifiutaProposta(proposta: Proposta) {
    this.proposteService.rifiutaProposta(proposta.id).subscribe({
      next: (response) => {
        alert('Proposta rifiutata con successo!');
        proposta.statoProposta = 'RIFIUTATA';
      },
      error: (error) => {
        alert('Errore durante il rifiuto della proposta.');
        console.error('Errore durante il rifiuto della proposta:', error);
      },
    });
  }
}
