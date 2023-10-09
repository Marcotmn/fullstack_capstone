import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProposteService } from 'src/app/service/proposte.service';

@Component({
  selector: 'app-crea-proposte',
  templateUrl: './crea-proposte.component.html',
  styleUrls: ['./crea-proposte.component.scss'],
})
export class CreaProposteComponent implements OnInit {
  creaPropostaForm!: FormGroup;
  annuncioId!: string; // Aggiunto l'ID dell'annuncio

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private proposteService: ProposteService
  ) {}

  ngOnInit(): void {
    this.creaPropostaForm = this.fb.group({
      descrizione: ['', Validators.required],
      importoProposto: ['', Validators.required],
    });

    // Ottenere l'ID dell'annuncio dalla route
    this.route.params.subscribe((params) => {
      this.annuncioId = params['annuncioId'];
    });
  }

  onSubmit() {
    if (this.creaPropostaForm.valid) {
      this.proposteService
        .creaProposta(this.annuncioId, this.creaPropostaForm.value)
        .subscribe({
          next: (response) => {
            alert('Proposta inviata con successo!');
            this.router.navigate(['/home']); // reindirizza alla pagina dell'annuncio
          },
          error: (error) => {
            alert('Per pubblicare una proposta effettua il login');
            console.error('Errore durante la creazione della proposta:', error);
          },
        });
    }
  }
}
