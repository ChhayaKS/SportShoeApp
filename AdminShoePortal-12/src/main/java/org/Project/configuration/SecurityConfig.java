package org.Project.configuration;

import org.Project.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@SuppressWarnings({ "deprecation", "removal" })
	public void configure(HttpSecurity http) throws Exception {
		http .authorizeRequests().requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
		.requestMatchers("/user/**").hasRole("ROLE_USER")
		.requestMatchers("/welcome/*").permitAll()
		.and().formLogin().and().csrf().disable();
		
//		http.csrf().disable()
//				.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/register/**", "/shop/**").permitAll()
//						.requestMatchers("/login").permitAll().requestMatchers("/admin").hasRole("ADMIN"))
//				.formLogin(form -> form.loginPage("/login").permitAll().failureUrl("/login?error=true")
//						.defaultSuccessUrl("/").usernameParameter("username").passwordParameter("password"))
//				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//						.logoutSuccessUrl("/login").permitAll());
//		http.build();
	}

	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	protected void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers("/resourses/**", "/static/", "/productImage/**");
	}

}
