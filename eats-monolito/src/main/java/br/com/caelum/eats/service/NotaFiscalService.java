package br.com.caelum.eats.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.caelum.eats.model.Pedido;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class NotaFiscalService {

	private Configuration freemarkerConfiguration;

	public NotaFiscalService(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}

	public String geraNotaPara(Pedido pedido) {
		try {
			Map<String, Object> data = new HashMap<>();
			data.put("pedido", pedido);
			Template template = freemarkerConfiguration.getTemplate("nota-fiscal.ftl");
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.toString();
		} catch (TemplateException | IOException e) {
			throw new RuntimeException("Erro ao gerar nota fiscal", e);
		}
	}
}
