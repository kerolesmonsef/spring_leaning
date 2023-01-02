package com.example.qgame.configs;

import com.example.qgame.configs.auth.DatabaseAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PUBLIC_MATCHERS = {
            "/admin/login",
    };

    @Autowired
    private DatabaseAdminDetailsService adminDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/admin/**")
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, "/admin/products").hasAuthority("LIST_PRODUCT")
                .antMatchers(HttpMethod.GET, "/admin/products/create").hasAuthority("CREATE_PRODUCT")
                .antMatchers("/admin/authorities/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/performLogin")
                .defaultSuccessUrl("/admin")
                .and()
//                .logout().logoutUrl("/admin/logout").logoutSuccessUrl("/logoutSuccess").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .logout()
                .logoutUrl(("/admin/logout"))
                .logoutSuccessUrl("/admin")
                .deleteCookies("JSESSIONID","remember-me")
                .invalidateHttpSession(true)
                .and();
//                .httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(adminDetailsService);
    }


}
