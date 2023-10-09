package marcotumminia.capstone.codifyRef.utente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NuovoUtentePayload {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String indirizzo;
    private String numeroTelefono;
    private Ruolo ruolo;
    private String nomeAzienda;
    private String tipoAzienda;
    private String partitaIvaAzienda;
    private String sitoWeb;
    private String codiceFiscale;
    private String titolo;
    private String bio;
    private String linkPortfolio;
    private String competenze;
    private String partitaIvaSviluppatore;
}
