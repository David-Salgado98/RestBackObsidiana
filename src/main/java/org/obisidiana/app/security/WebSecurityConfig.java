package org.obisidiana.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager authManager) throws Exception {

        JWTAutheticationFilter jwtAuthenticationFilter = new JWTAutheticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()

                .anyRequest()
                //.permitAll();//desahibilitamos la seguridad
                .authenticated()//habilitamos la seguridad
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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

    @Bean//interceptamos
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

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/pages/filtrar*","/pages/detalleProducto","/pages/nuevocliente");
    }

    public static void main(String[] args) {
        System.out.println("password:"+new BCryptPasswordEncoder().encode("contra"));
    }
}
