package com.inspire12.homepage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    @Value("${env.dev:}")
    String dev;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password, enabled from user where email=?")
//                .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
//                .passwordEncoder(passwordEncoder());

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/error/**", "/static/**", "/img/**", "/js/**", "/css/**", "/scss/**", "/plugins/**", "/fonts/**");
    }

    @Component
    public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic().disable();
        httpSecurity
                .authorizeRequests()
//                .anyRequest().authenticated()
//                .antMatchers("/login", "/signup").anonymous()
                .antMatchers("/login", "/signup", "/", "/index", "/about").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/*.js").permitAll();
        httpSecurity
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        httpSecurity
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
                .logout()
                .logoutSuccessUrl("/")
//                .and()
//                .ignoringAntMatchers("/h2-console/**")
                .and()
                .headers().frameOptions().disable();

        httpSecurity.csrf().disable();
        if (dev.equals("local")) return;
        httpSecurity
                .authorizeRequests()
                .antMatchers("/h2-console/**").access("hasRole('ADMIN') and hasRole('DBA')")
//                .antMatchers("/board").authenticated()
                .antMatchers("/article").authenticated();
//                .antMatchers("/resources/**").permitAll().anyRequest().permitAll();


    }
}
