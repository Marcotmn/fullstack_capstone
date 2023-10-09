package marcotumminia.capstone.codifyRef.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import marcotumminia.capstone.codifyRef.exceptions.UnauthorizedException;
import marcotumminia.capstone.codifyRef.utente.LoginSuccessfullPayload;
import marcotumminia.capstone.codifyRef.utente.NuovoUtentePayload;
import marcotumminia.capstone.codifyRef.utente.Utente;
import marcotumminia.capstone.codifyRef.utente.UtenteLoginPayload;
import marcotumminia.capstone.codifyRef.utente.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService utenteService;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder bcrypt;
	
	//ROTTE E METODI DI REGISTRAZIONE, LOGIN E LOGOUT
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUtente(@RequestBody NuovoUtentePayload body) {

		body.setPassword(bcrypt.encode(body.getPassword()));
		Utente created = utenteService.save(body);
		return created;
	}

	@PostMapping("/login")
    public LoginSuccessfullPayload login(@RequestBody UtenteLoginPayload body) {
        Utente user = utenteService.findByEmail(body.getEmail());

        if (bcrypt.matches(body.getPassword(), user.getPassword())) {
            String token = jwtTools.createToken(user);
            return new LoginSuccessfullPayload(token);

        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }


	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		System.out.println("Logout effettuato con successo");
		return ResponseEntity.ok("Logout effettuato con successo");

	}
}
