package br.com.caelum.eats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.AuthenticationDto;
import br.com.caelum.eats.dto.LoginDto;
import br.com.caelum.eats.model.User;
import br.com.caelum.eats.service.JwtTokenManager;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@PostMapping
	public ResponseEntity<AuthenticationDto> authenticate(@RequestBody LoginDto login) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				login.getUsername(), login.getPassword());

		try {
			Authentication authentication = authManager.authenticate(authenticationToken);
			User user = (User) authentication.getPrincipal();
			String jwt = jwtTokenManager.generateToken(user);
			AuthenticationDto tokenResponse = new AuthenticationDto(user, jwt);
			return ResponseEntity.ok(tokenResponse);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
