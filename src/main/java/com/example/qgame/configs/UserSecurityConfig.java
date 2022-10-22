package com.example.qgame.configs;

import com.example.qgame.configs.auth.DatabaseUserDetailsService;
import com.example.qgame.configs.auth.JwtAuthenticationEntryPoint;
import com.example.qgame.filters.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public static final String[] PUBLIC_MATCHERS = {
            "/assets/**",
            "/test/**",
            "/seed",
            "/images/**",
            "/blogs/**",
            "/blog/**",
            "/products/**",
            "/product/**",
            "/category/**",
            "/categories/**",
            "/"
    };


    public static final String[] AUTHENTICATION_MATCHERS = {
            "/orders/create/**",
            "/products/*/like",
            "/products/*/dislike",
            "/products/likes"
    };

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("$2y$10$iO9CihB5wL5CxwQCYHBs.OxdsX/qqOUzV7VW7KNxLelV8S/c0ZEea")// 1234
                .roles("USER");

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTHENTICATION_MATCHERS).authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
