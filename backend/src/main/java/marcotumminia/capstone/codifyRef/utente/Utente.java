package marcotumminia.capstone.codifyRef.utente;

import java.util.Collection;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"password", "cartaDiCredito"})
public class Utente implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String indirizzo;
    private String numeroTelefono;
    
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;  // mantenuto l'enum

    // Attributi specifici per Azienda
    private String nomeAzienda;
    private String tipoAzienda;
    private String partitaIvaAzienda;
    private String sitoWeb;

    // Attributo specifico per Privato
    private String codiceFiscale;

    // Attributi specifici per Sviluppatore
    private String titolo;
    private String bio;
    private String linkPortfolio;
    private String competenze;
    private String partitaIvaSviluppatore;  
    
    public Utente(
            String username,
            String nome,
            String cognome,
            String email,
            String password,
            String indirizzo,
            String numeroTelefono,
            Ruolo ruolo,
            String nomeAzienda,
            String tipoAzienda,
            String partitaIvaAzienda,
            String sitoWeb,
            String codiceFiscale,
            String titolo,
            String bio,
            String linkPortfolio,
            String competenze,
            String partitaIvaSviluppatore) {
    	
    this.username = username;
    this.nome = nome;
    this.cognome = cognome;
    this.email = email;
    this.password = password;
    this.indirizzo = indirizzo;
    this.numeroTelefono = numeroTelefono;
    this.ruolo = ruolo;
    this.nomeAzienda = nomeAzienda;
    this.tipoAzienda = tipoAzienda;
    this.partitaIvaAzienda = partitaIvaAzienda;
    this.sitoWeb = sitoWeb;
    this.codiceFiscale = codiceFiscale;
    this.titolo = titolo;
    this.bio = bio;
    this.linkPortfolio = linkPortfolio;
    this.competenze = competenze;
    this.partitaIvaSviluppatore = partitaIvaSviluppatore;
}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public UUID getIdUtente() {
		return id;
	}
}
	