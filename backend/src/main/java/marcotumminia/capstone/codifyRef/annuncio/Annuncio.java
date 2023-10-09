package marcotumminia.capstone.codifyRef.annuncio;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcotumminia.capstone.codifyRef.utente.Utente;


@Entity
@Table(name = "annunci")
@Data
@NoArgsConstructor

public class Annuncio {
	
	@ManyToOne
    @JoinColumn(name = "utente_id") 
    private Utente utente;
    
	@Id
	@GeneratedValue
	private UUID id;
		
	private String titolo;
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private CategoriaAnnuncio categoria;
	@Enumerated(EnumType.STRING)
	private StatoAnnuncio statoAnnuncio;
	private Double budgetPrevisto;
	private LocalDate dataPubblicazione;

	
	public Annuncio (String titolo, String descrizione, CategoriaAnnuncio categoria, StatoAnnuncio statoAnnuncio, Double budgetPrevisto, LocalDate dataPubblicazione) {
	
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.statoAnnuncio = statoAnnuncio;
		this.budgetPrevisto = budgetPrevisto;
		this.dataPubblicazione = dataPubblicazione;
	}
	
}
