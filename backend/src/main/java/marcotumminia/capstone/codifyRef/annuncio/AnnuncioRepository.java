package marcotumminia.capstone.codifyRef.annuncio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marcotumminia.capstone.codifyRef.utente.Utente;


@Repository
public interface AnnuncioRepository extends JpaRepository<Annuncio, UUID> {
	List<Annuncio> findByUtente(Utente utente);
    List<Annuncio> findByTitolo(String titolo);
    Optional<Annuncio> findAnnuncioById(UUID id);
    Optional<Annuncio> findFirstByOrderByIdAsc();
    
    ///PAGINAZIONE
    Page<Annuncio> findAll(Pageable pageable);

    ///PAGINAZIONE FILTRATA PER CATEGORIA
    Page<Annuncio> findByCategoria(CategoriaAnnuncio categoria, Pageable pageable);

}
