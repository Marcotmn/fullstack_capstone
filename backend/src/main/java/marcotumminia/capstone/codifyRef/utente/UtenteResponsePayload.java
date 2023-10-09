package marcotumminia.capstone.codifyRef.utente;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 

//////////QUELLO CHE VIENE MOSTRATO ALL'UTENTE DOPO LA REGISTRAZIONE
public class UtenteResponsePayload {
    private UUID id;
    private String username;
    private String email;
    private String indirizzo;
    private String numeroTelefono;
    private String cartaDiCredito;
    private Ruolo ruolo;

}