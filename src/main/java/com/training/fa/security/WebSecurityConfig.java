package com.training.fa.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.training.fa.service.UserDetailServicelmpl;
import com.training.fa.userprinciple.UserDetaillmpl;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
      
	@Autowired
	private UserDetailServicelmpl UserDetailService;
	
	
	
	//encoder passwork user
	@Bean
	PasswordEncoder passwordEncoder() {
			
	 return new BCryptPasswordEncoder();
		}
		
	
	@Override
	public void configure(AuthenticationManagerBuilder ath) throws Exception {
		ath.userDetailsService(UserDetailService) // supply userservice for spring security
		.passwordEncoder(passwordEncoder());     //supply password encoder
	}
	
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		 http.cors().and().csrf().disable()
		//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/home/**","/","/css/**","/fonts/**","/img/**/","/images/**",
				"/js/**","/sass/**","/Source/**","/vendor/**","/register/**").permitAll()
		
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/home/checkout/**").hasRole("USER")
		.anyRequest().authenticated();
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/admin/403");
		
		http.authorizeRequests().and().formLogin()//
		.loginPage("/login").permitAll()//
        // Submit URL của trang login
         .loginProcessingUrl("/login") // action page Login is /login
        .loginPage("/login").permitAll()//
        .defaultSuccessUrl("/login-success",false)
        .failureUrl("/login?error=true")
        
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		
		http.cors().and().csrf().disable();
		http.oauth2Login()
		 .loginPage("/login")
		 .defaultSuccessUrl("/oauth2/login/success",false)
		 .failureUrl("/oauth2/login/form/error")
		 .authorizationEndpoint()
		       .baseUri("/oauth2/authorization")
		       .authorizationRequestRepository(getRepository())
		 .and().tokenEndpoint()
		       .accessTokenResponseClient(getToken());
		
	}  
	
	
	
	@Bean
	public AuthorizationRequestRepository<OAuth2AuthorizationRequest> getRepository(){
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}
	
	@Bean
	public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> getToken(){
		
		return new DefaultAuthorizationCodeTokenResponseClient();
	}
	
	
	
    }










	
	


