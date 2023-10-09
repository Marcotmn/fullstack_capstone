import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProposteService } from 'src/app/service/proposte.service';
import { AnnuncioService } from 'src/app/service/annuncio.service';

@Component({
  selector: 'app-proposte-details',
  templateUrl: './proposte-details.component.html',
  styleUrls: ['./proposte-details.component.scss'],
})
export class ProposteDetailsComponent implements OnInit {
  proposta: any; // Definisci la struttura dati della tua proposta
  annuncio: any;

  constructor(
    private route: ActivatedRoute,
    private proposteService: ProposteService,
    private annuncioService: AnnuncioService
  ) {}

  ngOnInit(): void {
    const propostaId = this.route.snapshot.paramMap.get('id');
    const annuncioId = this.route.snapshot.paramMap.get('annuncioId'); // Aggiungi questa riga

    if (propostaId !== null) {
      this.proposteService.getPropostaById(propostaId).subscribe((proposta) => {
        this.proposta = proposta;

        // Ora, chiamiamo il servizio AnnuncioService per recuperare i dettagli dell'annuncio
        if (annuncioId !== null) {
          // Verifica che annuncioId non sia nullo
          this.annuncioService
            .getAnnuncioById(annuncioId)
            .subscribe((annuncio) => {
              this.annuncio = annuncio;
            });
        }
      });
    } else {
      console.error('ID proposta non valido.');
    }
  }
}
