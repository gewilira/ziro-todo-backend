package com.ziro.todo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.enabled:true}")
	private boolean securityEnabled;

	private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(new AntPathRequestMatcher("/user/register"),
			new AntPathRequestMatcher("/actuator/**"), new AntPathRequestMatcher("/swagger**"),
			new AntPathRequestMatcher("/swagger/**"), new AntPathRequestMatcher("/webjars/**"));

	private final UserDetailsService customUserDetailService;

	@Autowired
	public SecurityConfig(UserDetailsService customUserDetailService) {
		this.customUserDetailService = customUserDetailService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(final WebSecurity web) {
		if (!securityEnabled) {
			web.ignoring().antMatchers("/**");
		} else {
			web.ignoring().requestMatchers(PUBLIC_URLS);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		if (securityEnabled)
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/register").permitAll()
					.antMatchers("/actuators/**").hasAnyRole("ADMIN").antMatchers("/api/**")
					.authenticated();
	}
}
