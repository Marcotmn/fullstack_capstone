package marcotumminia.capstone.codifyRef.annuncio;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnnuncioPayload {
    private String titolo;
    private String descrizione;
    private CategoriaAnnuncio categoria;
    private Double budgetPrevisto;
    private LocalDate dataPubblicazione;
}
