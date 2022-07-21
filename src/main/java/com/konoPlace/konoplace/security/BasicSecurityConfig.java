package com.konoPlace.konoplace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)  throws Exception{
        auth.userDetailsService(userDetailsService);

        auth.inMemoryAuthentication().withUser("root").password(passwordEncoder().encode("root"))
                .authorities("ROLE_ADMIN");
    }

    /* */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
        .authorizeRequests().antMatchers("/css/**", "/js/**", "/assets/**" , "/user/**" , "/mesa/**" , "/reserva/**"
        ).permitAll().and()
        .formLogin()
        .loginPage("/user/login")
        .loginProcessingUrl("/user/login")
        .usernameParameter("email")
        .passwordParameter("pass")
        .defaultSuccessUrl("/mesa" ,true).failureForwardUrl("/login")
.permitAll();
        
                

               
    }

}
