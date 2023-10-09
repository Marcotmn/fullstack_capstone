package marcotumminia.capstone.codifyRef.annuncio;

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

import marcotumminia.capstone.codifyRef.exceptions.NotFoundException;
import marcotumminia.capstone.codifyRef.utente.Utente;
import marcotumminia.capstone.codifyRef.utente.UtenteRepository;
import marcotumminia.capstone.codifyRef.utente.UtenteService;

@Service
public class AnnuncioService {

    @Autowired
    private AnnuncioRepository annuncioRepository;
    
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private UtenteRepository utenteRepository;
    
    
    ////PER CREARE L'ANNUNCIO
    public Annuncio createAnnuncio(AnnuncioPayload payload) {
    	
        Utente utenteAutenticato = utenteService.getCurrentUser();

        Annuncio annuncio = new Annuncio(
            payload.getTitolo(),
            payload.getDescrizione(),
            payload.getCategoria(),
            StatoAnnuncio.APERTO,
            payload.getBudgetPrevisto(),
            LocalDate.now()  
            
        );
        annuncio.setUtente(utenteAutenticato);

        return save(annuncio);
    }

    public Annuncio save(Annuncio annuncio) {
        return annuncioRepository.save(annuncio);
    }
    
    
    
    /////PER TROVARE UN ANNUNCIO BY ID    
    public Annuncio findAnnuncioById(UUID id) {
        Optional<Annuncio> optionalAnnuncio = annuncioRepository.findAnnuncioById(id);
        
        if (optionalAnnuncio.isPresent()) {
            return optionalAnnuncio.get();
        } else {
      
            return null;
        }
    }
    
    
    
    /////PAGINAZIONE DEGLI ANNUNCI PER HOME ANNUNCI
    public Page<Annuncio> find(int page, int size, String sort){
    	Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    	return annuncioRepository.findAll(pageable);
    }

   
    
    ///// METODO PER RECUPERARE TUTTI GLI ANNUNCI DI UN UTENTE
    public List<Annuncio> findAnnunciByUtente(UUID utenteId) {
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con l'ID: " + utenteId));
        
        return annuncioRepository.findByUtente(utente);
    }
    
	/////////METODO ELIMINARE UN ANNUNCIO SPECIFICO
    public void findByIdAndDelete(UUID id) {
    	if (annuncioRepository.existsById(id)) {
    		annuncioRepository.deleteById(id);
    	} else {  throw new NotFoundException("Annuncio non trovato con l'ID: " + id);
    	}
    }
    
    
    /////////METODO PER FILTRARE LA BACHECA DI ANNUNCIO PER CATEGORIA
    public Page<Annuncio> findByCategoria(CategoriaAnnuncio categoria, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return annuncioRepository.findByCategoria(categoria, pageable);
    }

        
}