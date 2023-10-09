package marcotumminia.capstone.codifyRef.proposta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import marcotumminia.capstone.codifyRef.annuncio.Annuncio;
import marcotumminia.capstone.codifyRef.annuncio.AnnuncioService;
import marcotumminia.capstone.codifyRef.annuncio.StatoAnnuncio;
import marcotumminia.capstone.codifyRef.utente.Utente;
import marcotumminia.capstone.codifyRef.utente.UtenteService;


@RestController
@RequestMapping("/proposte")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @Autowired
    private AnnuncioService annuncioService;

    @Autowired
    private UtenteService utenteService;
    
    @PostMapping("/pubblica-proposta/{annuncioId}")
    public ResponseEntity<Map<String, Object>> creaProposta(
        @PathVariable UUID annuncioId,
        @RequestBody PropostaPayload payload
    ) {
        Annuncio annuncio = annuncioService.findAnnuncioById(annuncioId);

            Proposta proposta = propostaService.createProposta(annuncioId, payload);
            Utente utente = annuncio.getUtente();

            Map<String, Object> response = new HashMap<>();
            response.put("Stato proposta", "La proposta è stata inviata correttamente ed è in stato di attesa.");
            response.put("idProposta", proposta.getId());
            response.put("Nome annuncio", annuncio.getTitolo());

            return ResponseEntity.ok(response);
        
    }
    
    
    ///// PER TROVARE UNA PROPOSTA SPECIFICA CON ID
    @GetMapping("/{propostaId}")
    public Proposta findPropostaById(@PathVariable UUID propostaId) {
        return propostaService.findPropostaById(propostaId);
    }
    
    ///// FILTRO PER TROVARE TUTTE LE PROPOSTE DI UN UTENTE
    @GetMapping("/{utenteId}/proposte")
    public List<Proposta> getProposteByUtente(@PathVariable UUID utenteId) {
        return propostaService.findByUtente(utenteId);
    }
    
    
    ///// PAGINAZIONE TUTTE LE PROPOSTE DI UN UTENTE SPECIFICO
    @GetMapping("/proposte-utente/{utenteId}")
    public Page<Proposta> getProposteByUtente(
            @PathVariable UUID utenteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        return propostaService.findProposteByUtente(utenteId, page, size, sort);
    }

    
    ///// FILTRO PAGINAZIONE TUTTE LE PROPOSTE DI UN UTENTE SPECIFICO PER STATO
    @GetMapping("/proposte-utente/{utenteId}/stato/{statoProposta}")
    public Page<Proposta> getProposteByUtenteAndStato(
            @PathVariable UUID utenteId,
            @PathVariable StatoProposta statoProposta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        return propostaService.findByStatoProposta(utenteId, statoProposta, page, size, sort);
    }
    
    
    
    ///// PER CANCELLARE UN ANNUNCIO SPECIFICO
    	@DeleteMapping("/elimina-proposta/{propostaId}")
    	@ResponseStatus(HttpStatus.NO_CONTENT)
    	public void deleteProposta(@PathVariable UUID propostaId) {
        	propostaService.findByIdAndDelete(propostaId);
   	}

    	@PostMapping("/accetta-proposta/{propostaId}")
        public ResponseEntity<?> accettaProposta(@PathVariable UUID propostaId) {
            Proposta proposta = propostaService.findPropostaById(propostaId);

            if (proposta == null) {
                return ResponseEntity.notFound().build();
            }

            Annuncio annuncio = proposta.getAnnuncio();

            if (annuncio == null) {
                return ResponseEntity.badRequest().build();
            }

            // Verifica che l'utente che accetta la proposta sia l'utente proprietario dell'annuncio
            Utente utenteAutenticato = utenteService.getCurrentUser();
            if (!annuncio.getUtente().equals(utenteAutenticato)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Imposta lo stato dell'annuncio su IN_CORSO
            annuncio.setStatoAnnuncio(StatoAnnuncio.IN_CORSO);
            annuncioService.save(annuncio);

            // Imposta lo stato della proposta su ACCETTATA
            proposta.setStatoProposta(StatoProposta.ACCETTATA);
            propostaService.saveProposta(proposta);

            return ResponseEntity.ok().build();
        }

        @PostMapping("/rifiuta-proposta/{propostaId}")
        public ResponseEntity<?> rifiutaProposta(@PathVariable UUID propostaId) {
            Proposta proposta = propostaService.findPropostaById(propostaId);

            if (proposta == null) {
                return ResponseEntity.notFound().build();
            }

            Annuncio annuncio = proposta.getAnnuncio();

            if (annuncio == null) {
                return ResponseEntity.badRequest().build();
            }

            // Verifica che l'utente che rifiuta la proposta sia l'utente proprietario dell'annuncio
            Utente utenteAutenticato = utenteService.getCurrentUser();
            if (!annuncio.getUtente().equals(utenteAutenticato)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Imposta lo stato della proposta su RIFIUTATA
            proposta.setStatoProposta(StatoProposta.RIFIUTATA);
            propostaService.saveProposta(proposta);

            return ResponseEntity.ok().build();
        }
        
        @GetMapping("/annuncio/{annuncioId}")
        public List<Proposta> getProposteByAnnuncio(@PathVariable UUID annuncioId) {
            Annuncio annuncio = annuncioService.findAnnuncioById(annuncioId);
            if (annuncio == null) {
                // Gestisci il caso in cui l'annuncio non sia trovato, ad esempio restituendo una risposta 404
                return Collections.emptyList(); // Oppure restituisci una lista vuota
            }

            return propostaService.findByAnnuncio(annuncio);
        }


}