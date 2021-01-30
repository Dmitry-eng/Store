package com.shop.config;

import com.shop.service.app.UserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private UserAuthorization userAuthorization;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/**").permitAll()
//                .antMatchers(HttpMethod.PUT, "/**").permitAll()
//                .and()
// //added
//                .httpBasic();  //added
//        http.csrf().disable();
//    }

    // create two users, admin and user
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.csrf().disable();
//
//    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthorization).passwordEncoder(bCryptPasswordEncoder());

    }
}