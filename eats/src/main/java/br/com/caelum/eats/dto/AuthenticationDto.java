package br.com.caelum.eats.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import br.com.caelum.eats.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDto {

	private String username;
	private List<String> roles;
	private String token;

	public AuthenticationDto(User user, String jwtToken) {
		this(user.getName(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()), jwtToken);
	}

}
