package marcotumminia.capstone.codifyRef.proposta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import marcotumminia.capstone.codifyRef.annuncio.Annuncio;
import marcotumminia.capstone.codifyRef.annuncio.AnnuncioService;
import marcotumminia.capstone.codifyRef.annuncio.CategoriaAnnuncio;
import marcotumminia.capstone.codifyRef.exceptions.NotFoundException;
import marcotumminia.capstone.codifyRef.utente.Utente;
import marcotumminia.capstone.codifyRef.utente.UtenteRepository;
import marcotumminia.capstone.codifyRef.utente.UtenteService;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;
        
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
   private  AnnuncioService annuncioService;
    
    @Autowired
    private UtenteRepository utenteRepository;
    
    ////PER CREARE LA PROPOSTA
    public Proposta createProposta(UUID annuncioId, PropostaPayload payload) {
    	
    	//OTTIENI L'ANNUNCIO E L'UTENTE AUTENTICATO DA ASSOCIARE ALLA PROPOSTA
        Utente utenteAutenticato = utenteService.getCurrentUser();
        Annuncio annuncio = annuncioService.findAnnuncioById(annuncioId);
           
        Proposta proposta = new Proposta();
        proposta.setUtente(utenteAutenticato);
        proposta.setAnnuncio(annuncio);
        proposta.setDescrizione(payload.getDescrizione());
        proposta.setImportoProposto(payload.getImportoProposto());
        proposta.setDataProposta(LocalDate.now()); 
        proposta.setStatoProposta(StatoProposta.IN_ATTESA);

        return propostaRepository.save(proposta);
    }
    
    public Proposta saveProposta(Proposta proposta) {
        return propostaRepository.save(proposta);
    }
    
    
    
    /////PER TROVARE PROPOSTA BY ID    
    public Proposta findPropostaById(UUID propostaId) {
        Optional<Proposta> optionalProposta = propostaRepository.findPropostaById(propostaId);
        
        if (optionalProposta.isPresent()) {
            return optionalProposta.get();
        } else {
      
            return null;
        }
    }
    
    
    /////METODO PER RECUPERARE TUTTE LE PROPOSTE DI UN UTENTE
    public List<Proposta> findByUtente(UUID utenteId) {
    	Utente utente = utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException("Utente non trovato con l'ID: " + utenteId));
        return propostaRepository.findByUtente(utente);
    }
    
    
    
    /////PAGINAZIONE DELLE PROPOSTE PER PAGINA PROPOSTE UTENTE
    public Page<Proposta> findProposteByUtente(UUID utenteId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con l'ID: " + utenteId));
        return propostaRepository.findProposteByUtente(utente, pageable);
    }
    

    /////////PAGINAZIONE METODO PER FILTRARE LE PROPOSTE PER STATO
    public Page<Proposta> findByStatoProposta(UUID utenteId, StatoProposta statoProposta, int page, int size, String sort) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    	Utente utente = utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException("Utente non trovato con l'ID: " + utenteId));
    	return propostaRepository.findByUtenteAndStatoProposta(utente, statoProposta, pageable);
    
    }
    

    /////////METODO PER ELIMINARE UNA PROPOSTA SPECIFICA
    public void findByIdAndDelete(UUID id) {
    	if (propostaRepository.existsById(id)) {
    		propostaRepository.deleteById(id);
    	} else {  throw new NotFoundException("Annuncio non trovato con l'ID: " + id);
    	}
    }
    
    public List<Proposta> findByAnnuncio(Annuncio annuncio) {
        return propostaRepository.findByAnnuncio(annuncio);
    }


}
