package marcotumminia.capstone.codifyRef.utente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
	Optional<Utente> findByEmail(String email);
    Utente findByEmailAndUsername(String email, String username);
    Optional <Utente> findByNome(String nome);

  
	
}