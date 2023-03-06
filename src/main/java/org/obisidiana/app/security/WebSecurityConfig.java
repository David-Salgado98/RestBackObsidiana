package org.obisidiana.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                //.permitAll();//desahibilitamos la seguridad
                .authenticated()//habilitamos la seguridad
                .and()
                .csrf().disable();

        return  http.build();

    }
    //crear usuarios de paso
   // @Bean
    public UserDetailsService userDetailsService(){
        UserDetails juan = User.builder()
                .username("juan")
                //.password("{noop}fierro")//para que no este encriptada noop
                .password(passwordEncoder().encode("fierro"))//para que no este encriptada noop
                .roles("USER")
                .build();

        UserDetails pedro = User.builder()
                .username("pedro")
                //.password("{noop}fierro")//para que no este encriptada noop
                .password(passwordEncoder().encode("fierro"))//para que no este encriptada noop
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(juan,pedro);
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println("password:"+new BCryptPasswordEncoder().encode("contra"));
    }
}
