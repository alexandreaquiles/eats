package br.com.caelum.eats;

import org.springframework.context.annotation.Bean;

import freemarker.template.Configuration;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

	@Bean
	public Configuration getConfiguration() throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		cfg.setClassForTemplateLoading(FreeMarkerConfig.class, "/templates");
		cfg.setDefaultEncoding("UTF-8");
		return cfg;
	}

}