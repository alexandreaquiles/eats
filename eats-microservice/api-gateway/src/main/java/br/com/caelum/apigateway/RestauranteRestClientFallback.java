package br.com.caelum.apigateway;

import org.springframework.stereotype.Component;

@Component
class RestauranteRestClientFallback implements RestauranteRestClient {

	@Override
	public RestauranteDto porId(Long id) {
		//busca de um cache
		RestauranteDto dto = new RestauranteDto();
		dto.setId(id);
		return dto;
	}
	
}