package br.com.caelum.eats;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.caelum.eats.service.JwtAuthenticationEntryPoint;
import br.com.caelum.eats.service.JwtAuthenticationFilter;
import br.com.caelum.eats.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserService userService;
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	public SecurityConfig(	UserService userService,
							JwtAuthenticationFilter jwtAuthenticationFilter,
							JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
		this.userService = userService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/restaurantes/**", "/pedidos/**", "/recomendacoes/**", "/pagamentos/**", "/tipos-de-cozinha/**", "/formas-de-pagamento/**").permitAll()
			.antMatchers("/socket/**").permitAll()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/parceiros/**").hasRole("PARCEIRO")
			.anyRequest().authenticated()
			.and()
			.cors()
			.and()
			.csrf().disable().formLogin().disable().httpBasic().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

		;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
