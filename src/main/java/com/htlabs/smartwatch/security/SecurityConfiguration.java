package com.htlabs.smartwatch.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		/*http.addFilter(requestHeaderAuthenticationFilter()).httpBasic().disable().formLogin().disable().csrf().disable()
				.authorizeRequests().mvcMatchers("/admin/*")
				.hasAnyRole(Roles.ADMIN.name()).antMatchers("/entry/*","/user/*","/admin/*").permitAll();
	 */
		http.cors().and().csrf().disable();
	}


//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
//	}

	@Bean
	public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
		RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
		requestHeaderAuthenticationFilter.setPrincipalRequestHeader("X-AUTH-TOKEN");
		requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());
		requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);

		return requestHeaderAuthenticationFilter;
	}

//	@Bean
//	public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
//		PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
//		preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(authUserDetailsService);
//
//		return preAuthenticatedAuthenticationProvider;
//	}
}
