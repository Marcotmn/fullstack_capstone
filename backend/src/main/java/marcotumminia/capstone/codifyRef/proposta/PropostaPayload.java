package marcotumminia.capstone.codifyRef.proposta;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PropostaPayload {

    private String descrizione;
    private Double importoProposto;
    private LocalDate dataProposta;
}
