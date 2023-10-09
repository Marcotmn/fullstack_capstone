package marcotumminia.capstone.codifyRef.annuncio;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annunci")
public class AnnuncioController {

    @Autowired
    private AnnuncioService annuncioService;
    
    
    ///// PER PUBBLICARE UN ANNUNCIO
    @PostMapping("/nuovo-annuncio")
    public ResponseEntity<Annuncio> createAnnuncio(@RequestBody AnnuncioPayload payload) {
			Annuncio createdAnnuncio = annuncioService.createAnnuncio(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnuncio);
    }
    
    
    ///// PER TROVARE UN ANNUNCIO SPECIFICO
    @GetMapping("/{annuncioId}")
    public Annuncio findAnnuncioById(@PathVariable UUID annuncioId) {
        return annuncioService.findAnnuncioById(annuncioId);
    }
    
 // Endpoint per recuperare tutti gli annunci di un utente
    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<Annuncio>> findAnnunciByUtente(@PathVariable UUID utenteId) {
        List<Annuncio> annunci = annuncioService.findAnnunciByUtente(utenteId);
        return new ResponseEntity<>(annunci, HttpStatus.OK);
    }
    
    
    //// PER POPOLARE LA LAGINA DEGLI ANNUNCI
    @GetMapping("/bacheca-annunci")
    public Page<Annuncio> getAnnunci(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size, 
            @RequestParam(defaultValue = "id") String sort) {
        return annuncioService.find(page, size, sort);
    }
    
    ///// PER CANCELLARE UN ANNUNCIO SPECIFICO
    @DeleteMapping("/elimina-annuncio/{annuncioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnnuncio(@PathVariable UUID annuncioId) {
        annuncioService.findByIdAndDelete(annuncioId);
    }
    
    @GetMapping("/annunci-per-categoria")
    public ResponseEntity<Page<Annuncio>> getAnnunciByCategoria(
            @RequestParam String categoria, // Cambia il tipo in String
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        try {
            CategoriaAnnuncio categoriaEnum = CategoriaAnnuncio.valueOf(categoria);
            Page<Annuncio> annunci = annuncioService.findByCategoria(categoriaEnum, page, size, sort);
            return ResponseEntity.ok(annunci);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Categoria non valida
        }
    }



    
}