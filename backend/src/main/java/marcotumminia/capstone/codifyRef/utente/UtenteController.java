package marcotumminia.capstone.codifyRef.utente;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import marcotumminia.capstone.codifyRef.annuncio.Annuncio;
import marcotumminia.capstone.codifyRef.annuncio.AnnuncioService;
import marcotumminia.capstone.codifyRef.proposta.Proposta;
import marcotumminia.capstone.codifyRef.proposta.PropostaService;


@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteService utenteService;
    
    @Autowired
    AnnuncioService annuncioService;
    
    @Autowired
    PropostaService propostaService;
    
    @PostMapping("/register")
    public ResponseEntity<Utente> registerUser(@RequestBody NuovoUtentePayload payload) {
        Utente createdUser = utenteService.save(payload);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/current")
    public ResponseEntity<Utente> getCurrentUser() {
        Utente utente = utenteService.getCurrentUser();

        if (utente != null) {
            return ResponseEntity.ok(utente);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listaUtenti")
    public List<Utente> getUsers() {
        return utenteService.getUsers();
    }

    @GetMapping("/{idUtente}")
    public Utente findById(@PathVariable UUID idUtente) {
        return utenteService.findById(idUtente);
    }
    @PutMapping("/{idUtente}")
    public Utente updateUser(@PathVariable UUID idUtente, @RequestBody NuovoUtentePayload body) {
        return utenteService.findByIdAndUpdate(idUtente, body);
    }

    @DeleteMapping("/{idUtente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID idUtente) {
        utenteService.findByIdAndDelete(idUtente);
    }
    
    
    
    
    //////PER TROVARE TUTTI GLI ANNUNCI DI UN UTENTE
    @GetMapping("/{utenteId}/annunci")
    public List<Annuncio> getAnnunciByUtente(@PathVariable UUID utenteId) {
        return annuncioService.findAnnunciByUtente(utenteId);
    }
    
    
    
    
    //////PER TROVARE TUTTE LE PROPOSTE DI UN UTENTE
    @GetMapping("/{utenteId}/proposte")
  	public List<Proposta> getProposteByUtente(@PathVariable UUID utenteId) {
      return propostaService.findByUtente(utenteId);
  	}
  
  
  
    ///// PAGINAZIONE PER TUTTE LE PROPOSTE DI UN UTENTE SPECIFICO
    @GetMapping("/{utenteId}/bacheca-proposte")
    public Page<Proposta> getProposteByUtente(
          @PathVariable UUID utenteId,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size,
          @RequestParam(defaultValue = "id") String sort) {
      return propostaService.findProposteByUtente(utenteId, page, size, sort);
    }

  

}