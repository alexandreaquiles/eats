package br.com.caelum.eats.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.repository.FormaDePagamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormaDePagamentoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FormaDePagamentoRepository formaDePagamentoRepository;
	
	@Test
	public void todas() throws Exception {
		FormaDePagamento visaCredito = new FormaDePagamento(1L, FormaDePagamento.Tipo.CARTAO_CREDITO, "Visa Crédito");
		FormaDePagamento ticketRestaurante = new FormaDePagamento(2L, FormaDePagamento.Tipo.VALE_REFEICAO, "Ticket Restaurante");
		
		List<FormaDePagamento> formasDePagamento = Arrays.asList(visaCredito, ticketRestaurante);
		
		Mockito.when(formaDePagamentoRepository.findAll()).thenReturn(formasDePagamento);
		
		
		this.mockMvc.perform(get("/admin/formas-de-pagamento"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$.[0].id").value(1L))
			.andExpect(jsonPath("$.[0].tipo").value(FormaDePagamento.Tipo.CARTAO_CREDITO.name()))
			.andExpect(jsonPath("$.[0].nome").value("Visa Crédito"))
			.andExpect(jsonPath("$.[1].id").value(2L))
			.andExpect(jsonPath("$.[1].tipo").value(FormaDePagamento.Tipo.VALE_REFEICAO.name()))
			.andExpect(jsonPath("$.[1].nome").value("Ticket Restaurante"));
		
	}
	
	@Test
	public void detalhaPorId() throws Exception {
		FormaDePagamento visaCredito = new FormaDePagamento(1L, FormaDePagamento.Tipo.CARTAO_CREDITO, "Visa Crédito");
		
		Mockito.when(formaDePagamentoRepository.findById(1L)).thenReturn(Optional.of(visaCredito));
		
		
		this.mockMvc.perform(get("/admin/formas-de-pagamento/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id").value(1L))
			.andExpect(jsonPath("$.tipo").value(FormaDePagamento.Tipo.CARTAO_CREDITO.name()))
			.andExpect(jsonPath("$.nome").value("Visa Crédito"));
		
	}

	@Test
	public void tipos() throws Exception {
		this.mockMvc.perform(get("/admin/formas-de-pagamento/tipos"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$.length()").value(4))
			.andExpect(jsonPath("$.[0]").value("DINHEIRO"))
			.andExpect(jsonPath("$.[1]").value("CARTAO_CREDITO"))
			.andExpect(jsonPath("$.[2]").value("CARTAO_DEBITO"))
			.andExpect(jsonPath("$.[3]").value("VALE_REFEICAO"));

	}
}
