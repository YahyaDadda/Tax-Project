package ma.gi.security;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/* ## in Memory Authentication ##
		auth.inMemoryAuthentication().withUser("yahya").password("{noop}123").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("amine").password("{noop}123").roles("USER");
		*/
		
		PasswordEncoder MD5encoder = new MessageDigestPasswordEncoder("MD5");

		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select username as principal, password as credentials, active from user where username=?")
		.authoritiesByUsernameQuery("select username as principal, rolename as role from user_role where username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(MD5encoder);
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
