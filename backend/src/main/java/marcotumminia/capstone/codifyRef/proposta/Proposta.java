package marcotumminia.capstone.codifyRef.proposta;

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
import marcotumminia.capstone.codifyRef.annuncio.Annuncio;
import marcotumminia.capstone.codifyRef.utente.Utente;

@Entity
@Table(name = "proposte")
@Data
@NoArgsConstructor

public class Proposta {
	
	@Id
	@GeneratedValue
	private UUID id;
	
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "id_annuncio")
	private Annuncio annuncio;
	
	private String descrizione;
	private Double importoProposto;
	@Enumerated(EnumType.STRING)
	private StatoProposta statoProposta;
	private LocalDate dataProposta;
	
	public Proposta(Utente utente, Annuncio annuncio, String descrizione, Double importoProposto, StatoProposta statoProposta, LocalDate dataProposta) {
		this.annuncio = annuncio;
		this.descrizione = descrizione;
		this.importoProposto = importoProposto;
		this.statoProposta = statoProposta;
		this.dataProposta = dataProposta;
	}
}
