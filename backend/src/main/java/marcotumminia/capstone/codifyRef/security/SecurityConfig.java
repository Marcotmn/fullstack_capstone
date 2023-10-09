package marcotumminia.capstone.codifyRef.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	JWTAuthFilter jwtFilter;
	@Autowired
	CorsFilter corsFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//http.cors(c -> c.disable());
		http.csrf(c -> c.disable());

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, JWTAuthFilter.class);
		
		//ROTTE LOGIN REGISTER
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/current/**").permitAll());

		
		/////ROTTE ANNUNCI
		////PUBBLICA ANNUNCIO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/nuovo-annuncio/**").permitAll());
		/////TROVA UN ANNUNCIO SPECIFICO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/{annuncioId}/**").permitAll());
		/////ELIMINA UN ANNUNCIO SPECIFICO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/elimina-annnuncio/{annuncioId}/**").permitAll());
		///// PAGINAZIONE ANNUNCI PER BACHECA ANNUNCI
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/bacheca-annunci/**").permitAll());
		///// PAGINAZIONE ANNUNCI PER BACHECA ANNUNCI FILTRATA PER CATEGORIA
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/annunci-per-categoria/**").permitAll());
		///PER TROVARE TUTTI GLI ANNUNCI DI UN UTENTE
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/{utenteId}/annunci/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/annunci/utente/{utenteId}/**").permitAll());
		
		////ROTTE PROPOSTA
		////PUBBLICA PROPOSTA
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/pubblica-proposta/{annuncioId}/**").permitAll());
		////TROVA PROPOSTA ID
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/{propostaId}/**").permitAll());
		////TROVA PROPOSTE DI UN UTENTE
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/{utenteId}/proposte/**").permitAll());
		////PAGINAZIONE PROPOSTE UTENTE	SPECIFICO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/{utenteId}/bacheca-proposte/**").permitAll());
		///// FILTRO PAGINAZIONE TUTTE LE PROPOSTE DI UN UTENTE SPECIFICO PER STATO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/proposte-utente/{utenteId}/stato/{statoProposta}/**").permitAll());
		////TROVA PROPOSTE DELL'ANNUNCIO
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte//annuncio/{annuncioId}/**").permitAll());
		////ELIMINA PROPOSTA
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/accetta-proposta/{propostaId}/**").permitAll());
		////ACCETTA PROPOSTA
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/proposte/rifiuta-proposta/{propostaId}/**").permitAll());

	
		
		return http.build();
	}

	//BEAN PER CRIPTARE LA PASSWORD
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
}