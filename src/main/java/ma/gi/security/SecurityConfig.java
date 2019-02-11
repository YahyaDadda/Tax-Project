package ma.gi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("yahya").password("{noop}123").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("amine").password("{noop}123").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/save").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/saisie").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/entreprises","/taxes").hasRole("USER");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
}
