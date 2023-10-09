package marcotumminia.capstone.codifyRef.proposta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marcotumminia.capstone.codifyRef.annuncio.Annuncio;
import marcotumminia.capstone.codifyRef.utente.Utente;


@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {
	List<Proposta> findByUtente(Utente utente);
	
	Optional<Proposta> findPropostaById(UUID id);

    Optional<Proposta> findByAnnuncioAndUtente(Annuncio annuncio, Utente utente);    
    
    /////PAGINAZIONE PROPOSTE PER LISTA PROPOSTE UTENTE
    Page<Proposta> findProposteByUtente(Utente utente, Pageable pageable);
    
    ///PAGINAZIONE PROPOSTE PER LISTA PROPOSTE UTENTE FILTRATA PER STATO
    Page<Proposta> findByUtenteAndStatoProposta(Utente utente, StatoProposta statoProposta, Pageable pageable);
    
    /////PAGINAZIONE
    Page<Proposta> findByStatoProposta(Utente utente, StatoProposta statoProposta, Pageable pageable);

    List<Proposta> findByAnnuncio(Annuncio annuncio);
  }
