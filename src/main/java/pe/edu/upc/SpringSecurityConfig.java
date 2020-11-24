package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import pe.edu.upc.auth.handler.LoginSuccessHandler;
import pe.edu.upc.serviceimpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/imagenes/**","/registry/save","/registry","/registry/save/","/bundles/**","registro.html","login","/landing/**","/landing/index/**","/static/**","/static/css/**","/static/imagenes/**","/registro","/registry/er/").permitAll().anyRequest()
				.authenticated().and().formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/user/bienvenido")
				.and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/error_403");
	}
	

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
