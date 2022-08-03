package com.example.qgame.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_MATCHERS = {
            "/assets/**",
            "/test/**",
            "/seed",
            "/images/**",

    };

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("$2y$10$iO9CihB5wL5CxwQCYHBs.OxdsX/qqOUzV7VW7KNxLelV8S/c0ZEea")// 1234
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();


//        http
//                .authorizeRequests().
//                antMatchers(PUBLIC_MATCHERS).
//                permitAll().anyRequest().authenticated();
//
//        http
//                .csrf().disable().cors().disable()
//                .formLogin().failureUrl("/login?error")
//                .loginPage("/login").permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
//                .and()
//                .rememberMe();
    }

}
