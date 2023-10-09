package marcotumminia.capstone.codifyRef.utente;

import lombok.Data;

@Data

public class RegistrationSuccessResponse {
  private String message;

  public RegistrationSuccessResponse(String message) {
      this.message = message;
  }

}
