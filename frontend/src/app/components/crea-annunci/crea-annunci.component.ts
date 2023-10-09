import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { AnnuncioService } from 'src/app/service/annuncio.service';


import { CategoriaAnnuncio } from './categoria-annunci.enum';

@Component({
  selector: 'app-crea-annunci',
  templateUrl: './crea-annunci.component.html',
  styleUrls: ['./crea-annunci.component.scss'],
})
export class CreaAnnunciComponent implements OnInit {

  CategoriaAnnuncio = CategoriaAnnuncio;

  enumKeys = Object.keys(CategoriaAnnuncio);

  creaAnnuncioForm!: FormGroup;

  getCategoriaDisplayValue(categoriaKey: string): string {
    return CategoriaAnnuncio[categoriaKey as keyof typeof CategoriaAnnuncio];
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private annuncioService: AnnuncioService,

  ) {}

  ngOnInit(): void {
    this.creaAnnuncioForm = this.fb.group({
      titolo: ['', Validators.required],
      descrizione: ['', Validators.required],
      categoria: ['', Validators.required],
      budgetPrevisto: [0, Validators.required],
    });
  }

  onSubmit() {
    if (this.creaAnnuncioForm.valid) {
      this.annuncioService
        .createAnnuncio(this.creaAnnuncioForm.value)
        .subscribe({
          next: (response) => {
            alert('Annuncio creato con successo!');
            this.router.navigate(['/home']); // reindirizza alla pagina dell'annuncio
          },
          error: (error) => {
            alert('Per pubblicare un annuncio effettua il login');
            console.error("Errore durante la creazione dell'annuncio:", error);
          },
        });
    }
  }
}
