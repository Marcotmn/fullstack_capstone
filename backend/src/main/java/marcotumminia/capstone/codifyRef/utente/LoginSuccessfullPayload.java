package marcotumminia.capstone.codifyRef.utente;

import lombok.Data;

@Data
public class LoginSuccessfullPayload {
    private String accessToken;


    public LoginSuccessfullPayload(String accessToken) {
        this.accessToken = accessToken;

    }
}
