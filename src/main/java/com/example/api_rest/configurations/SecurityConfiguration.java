package com.example.api_rest.configurations;

import com.example.api_rest.filter.CorsFilter;
import com.example.api_rest.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService securityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(securityService);
    }

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class) //adds your custom CorsFilter
                .formLogin()
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")
                .loginProcessingUrl("/auth")
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("api/v1/users/*").access("hasRole('ROLE_USER')");
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //Specify the person in charge of the Login and Password Encryption
        auth.userDetailsService(securityService).passwordEncoder(passwordEncoder());
    }
}
