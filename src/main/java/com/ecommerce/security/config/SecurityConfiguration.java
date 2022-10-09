package com.ecommerce.security.config;

import com.ecommerce.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/authenticate").permitAll()

                //STORES
                .antMatchers("/stores").permitAll()
                .antMatchers(HttpMethod.POST, "/stores/create").hasAuthority("manager")
                .antMatchers(HttpMethod.POST, "/stores/addpayment").hasAuthority("manager")
                .antMatchers(HttpMethod.DELETE, "/stores").hasAuthority("manager")
                .antMatchers(HttpMethod.POST, "/stores/addpublication").hasAuthority("seller")
                .antMatchers(HttpMethod.GET, "/stores/*").permitAll()

                //USERS
                .antMatchers(HttpMethod.GET,"/users/").hasAuthority("manager")
                .antMatchers(HttpMethod.DELETE,"/users/").hasAuthority("manager")
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/").hasAuthority("manager")

                //SWAGGER
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**").permitAll()
                .antMatchers("/users/setrole/**").hasAuthority("manager")

                //SELLER
                .antMatchers(HttpMethod.GET, "/seller").hasAuthority("seller")
                .antMatchers(HttpMethod.POST, "/seller").hasAuthority("manager")
                .antMatchers(HttpMethod.POST, "/seller/chooseproduct").hasAuthority("seller")
                .antMatchers(HttpMethod.PUT, "/seller").hasAuthority("seller")
                .antMatchers(HttpMethod.DELETE, "/seller").hasAuthority("manager")

                //PRODUCTS
                .antMatchers(HttpMethod.GET, "/products").hasAuthority("seller")
                .antMatchers(HttpMethod.POST, "/products").hasAuthority("manager")
                .antMatchers(HttpMethod.DELETE, "/products").hasAuthority("manager")
                .antMatchers(HttpMethod.GET, "/products/allsell").hasAuthority("manager")
                .antMatchers(HttpMethod.PATCH, "/products/setBaseCustomization").hasAuthority("manager")

                //BASE CUSTOMIZATION
                .antMatchers(HttpMethod.POST, "/customizations/base").hasAuthority("manager")
                .antMatchers(HttpMethod.GET, "/customizations/base").hasAuthority("manager")
                .antMatchers(HttpMethod.DELETE, "/customizations/base").hasAuthority("manager")

                //SHOPPING CART
                /*
                .antMatchers( HttpMethod.POST, "/shoppingcart").hasAuthority("manager")
                .antMatchers(HttpMethod.GET, "/shoppingcart").hasAuthority("manager")
                .antMatchers(HttpMethod.DELETE, "/shoppingcart").hasAuthority("manager")
                .antMatchers(HttpMethod.PATCH, "/shoppingcart/addproduct").hasAuthority("manager")
                */
                .antMatchers(  "/shoppingcart").permitAll()

                //.anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
