import { Component, OnInit } from '@angular/core';
import { Annuncio } from './annuncio';
import { AnnuncioService } from '../../service/annuncio.service';
import { CategoriaAnnuncio } from '../crea-annunci/categoria-annunci.enum';

@Component({
  selector: 'app-annunci',
  templateUrl: './annunci.component.html',
  styleUrls: ['./annunci.component.scss'],
})
export class AnnunciComponent implements OnInit {
  constructor(private annuncioService: AnnuncioService) {}

  annunci: Annuncio[] = [];
  filtroCategoria: string[] = [];
  categorieEnumKeys = Object.keys(CategoriaAnnuncio);
  currentPage = 0;
  pageSize = 10;
  totalPages = 0;
  sortField = 'dataPubblicazione';

  ngOnInit(): void {
    // Carica gli annunci all'inizio
    this.caricaAnnunci();
  }

  // Carica tutti gli annunci
  caricaAnnunci() {
    this.annuncioService
      .getAllAnnunci(this.currentPage, this.pageSize, this.sortField)
      .subscribe({
        next: (data: any) => {
          console.log('Dati degli annunci:', data.content);
          this.annunci = data.content;
          this.totalPages = data.totalPages;
        },
        error: (error) => {
          console.error('Errore nel recupero degli annunci:', error);
        },
      });
  }
  // Aggiungi queste funzioni alla classe AnnunciComponent
  paginaPrecedente() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.caricaAnnunci();
    }
  }

  paginaSuccessiva() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.caricaAnnunci();
    }
  }

  // Applica i filtri
  applicaFiltri() {
    // Chiamare il servizio o applicare i filtri localmente
    if (
      this.filtroCategoria.length === 0 ||
      this.filtroCategoria.includes('')
    ) {
      // Nessuna categoria selezionata o "Tutte le categorie" selezionata, carica tutti gli annunci
      this.caricaAnnunci();
    } else {
      // Hai selezionato una o più categorie diverse da "Tutte le categorie", quindi effettua il filtro
      this.annuncioService
        .getAnnunciByCategoria(this.filtroCategoria)
        .subscribe({
          next: (data: any) => {
            console.log('Annunci filtrati per categoria:', data.content);
            this.annunci = data.content;
          },
          error: (error) => {
            console.error('Errore nel recupero degli annunci filtrati:', error);
          },
        });
    }
  }
}
