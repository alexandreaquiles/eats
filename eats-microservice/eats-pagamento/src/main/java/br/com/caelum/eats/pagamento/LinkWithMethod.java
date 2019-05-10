package br.com.caelum.eats.pagamento;

import org.springframework.hateoas.Link;

import lombok.Getter;

@Getter
public class LinkWithMethod extends Link {

	private static final long serialVersionUID = 1L;

	private String method;

	public LinkWithMethod(Link link, String method) {
		super(link.getHref(), link.getRel());
		this.method = method;
	}
}