package com.example.salonbella.security;

import com.example.salonbella.entity.UserEntity;
import com.example.salonbella.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    private AuthenticationSuccessHandler successHandler;

    @Autowired
    public ApplicationSecurityConfig(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/register","/confirm").permitAll()
                .antMatchers("/adminDashboard","/admin-scheduled-reservations","/admin-get-scheduled-reservations","/admin-cancel-reservation","/admin-block-reservation","/admin-get-blocked-reservations","/admin-unblock-reservation","/admin-schedule reservation","/admin-add-product","/admin-get-orders","/admin-change-status","/admin-cancel-order","/admin-remove-product").hasRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher(("/logout"), "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
