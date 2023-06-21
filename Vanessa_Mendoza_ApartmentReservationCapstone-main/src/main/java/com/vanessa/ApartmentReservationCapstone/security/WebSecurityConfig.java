package com.vanessa.ApartmentReservationCapstone.security;

import com.vanessa.ApartmentReservationCapstone.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/employee/read").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                .antMatchers("/employee/update", "/employee/create").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/employee/delete").hasRole("SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/index.html").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> (UserDetails) employeeRepository.findByFullName(username))
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
