package com.blog.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                authorizeRequests()
                .antMatchers("/post/add").hasAuthority("USER")
                .antMatchers("/post/create").hasAuthority("USER")
                .antMatchers("/post/edit/**").hasAuthority("USER")
                .antMatchers("/comment/add/**").hasAuthority("USER")
                .antMatchers("/post/delete/**").hasAuthority("USER")
                .antMatchers("/comment/delete/**/**").hasAuthority("USER")
                .antMatchers("/rating/**/**/**").hasAuthority("USER")
                .antMatchers("/rating/**/**/**/**").hasAuthority("USER")
                .antMatchers("/profile/**").hasAuthority("USER")
                .antMatchers("/api/**/**/**").permitAll()//hasAuthority("ADMIN")
                .antMatchers("/api/**/**").permitAll()//hasAuthority("ADMIN")
                .antMatchers("/api/**").permitAll()//hasAuthority("ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("/users").hasAuthority("ADMIN")
                .antMatchers("/post/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**", "/script/**", "/images/**", "/bootstrap/**", "/jquery/**").permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginPage("/login").permitAll()
                    .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
        .and().csrf().ignoringAntMatchers("/api/**/**", "/api/**/**/**");
    }

}




