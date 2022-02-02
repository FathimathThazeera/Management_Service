package com.example.demo.config;

import com.example.demo.CustomUserDetailsService;
import com.example.demo.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    private final CustomUserDetailsService customUserDetailsService;

  /* @Bean
    AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider provider
               = new DaoAuthenticationProvider();
       provider.setUserDetailsService(userDetailsService);
      // provider.setPasswordEncoder(new BCryptPasswordEncoder());
       return provider;
   }*/

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
      // http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().anyRequest().authenticated().and().httpBasic();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/account/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/account/authenticate")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

