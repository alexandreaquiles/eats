package br.com.caelum.eats;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import br.com.caelum.eats.AmqpConfig.PagamentoSource;

@EnableBinding(PagamentoSource.class)
@Configuration
public class AmqpConfig {

	public static interface PagamentoSource {
		String PAGAMENTOS_CONFIRMADOS = "pagamentosConfirmados";
		
		@Output
		MessageChannel pagamentosConfirmados();
	}
	
}
