package br.com.caelum.eats.restaurante;

import org.springframework.stereotype.Service;

import br.com.caelum.eats.seguranca.AuthorizationTargetService;
import br.com.caelum.eats.seguranca.Role;
import br.com.caelum.eats.seguranca.User;

@Service
public class RestauranteAuthorizationTargetService implements AuthorizationTargetService {

	private RestauranteRepository restauranteRepo;

	public RestauranteAuthorizationTargetService(RestauranteRepository restauranteRepo) {
		this.restauranteRepo = restauranteRepo;
	}

	@Override
	public Long getTargetIdByUser(User user) {
		if (user.isInRole(Role.ROLES.PARCEIRO)) {
			Restaurante restaurante = restauranteRepo.findByUser(user);
			if (restaurante != null) {
				return restaurante.getId();
			}
		}
		return null;
	}

}
