package marcotumminia.capstone.codifyRef.utente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import marcotumminia.capstone.codifyRef.exceptions.BadRequestException;
import marcotumminia.capstone.codifyRef.exceptions.NotFoundException;


@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof Utente) {
            Utente user = (Utente) principal;
            String currentUserName = user.getNome();
            Optional<Utente> userOptional = utenteRepository.findByNome(currentUserName);

            if (userOptional.isPresent()) {
                return userOptional.get();
            }
        }

        return null;
    }
    

	public Utente save(NuovoUtentePayload body) {
		utenteRepository.findByEmail(body.getEmail()).ifPresent(utente -> {
			throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
		});
		Utente newUtente = new Utente(body.getUsername(),body.getNome(), body.getCognome(), body.getEmail(),body.getPassword(),body.getIndirizzo(), body.getNumeroTelefono(), body.getRuolo(), body.getNomeAzienda(), body.getTipoAzienda(), body.getPartitaIvaAzienda(), body.getSitoWeb(), body.getCodiceFiscale(), body.getTitolo(), body.getBio(), body.getLinkPortfolio(), body.getCompetenze(), body.getPartitaIvaSviluppatore());
		return utenteRepository.save(newUtente);
	}

	//////////METODO CHE RICERCA L'UTENTE TRAMITE L'EMAIL
    public Utente findByEmail(String email) {
		return utenteRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
	
    //////////METODO CHE RICERCA TUTTI GLI UTENTI
    public List<Utente> getUsers() {
        return utenteRepository.findAll();
    }
	
    //////////METODO CHE RICERCA L'UTENTE TRAMITE Id
    public Utente findById(UUID id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isPresent()) {
            return utente.get();
        } else {
            throw new RuntimeException("Utente non trovato con ID: " + id); 
        }
    }
	
    //////////METODO CHE CERCA L'UTENTE TRAMITE ID E POI LO AGGIORNA
    public Utente findByIdAndUpdate(UUID id, NuovoUtentePayload body) {
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if (utenteOptional.isPresent()) {
            Utente utenteAggiornato = utenteOptional.get();
            
            return utenteRepository.save(utenteAggiornato);
        } else {
            
            return null; 
        }
    }
	
    //////////METODO CHE CERCA L'UTENTE TRAMITE ID E POI LO ELIMINA
    public void findByIdAndDelete(UUID id) {
        if (utenteRepository.existsById(id)) {
            utenteRepository.deleteById(id);
        } else {// Puoi gestire l'errore come preferisci
        }
    }
    
    public Utente findByEmailAndUsername(String email, String username) {
        return utenteRepository.findByEmailAndUsername(email, username);
    }
   
   
}