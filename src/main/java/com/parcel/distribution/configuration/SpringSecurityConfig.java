package com.parcel.distribution.configuration;


import com.parcel.distribution.utils.PasswordEncoderUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Data
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery);
        //.passwordEncoder(passwordEncoderUtil);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/rest/*", "/", "/location/*", "/about", "/contact", "/parcel/distribution/login", "/parcel/distribution/registration", "/parcel/distribution/error/*", "/parcel/distribution/activeuser/*").permitAll()
                .antMatchers("/parcel/distribution/content/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_EMPLO")
                .antMatchers("/parcel/distribution/admin/*").hasAuthority("ROLE_ADMIN")
                .antMatchers("/parcel/distribution/editprofile/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_EMPLO")
                .antMatchers("/parcel/distribution/parcel/*").hasAuthority("ROLE_USER")
                .antMatchers("/parcel/distribution/contacts/*").hasAuthority("ROLE_USER")
                .antMatchers("/parcel/distribution/download/*").hasAuthority("ROLE_USER")
                .antMatchers("/warehouse/*").hasAuthority("ROLE_EMPLO").anyRequest()
                .authenticated().and().formLogin()
                .loginPage("/parcel/distribution/login").failureUrl("/parcel/distribution/error/failureLogin").failureForwardUrl("/parcel/distribution/error/failureLogin")
                .defaultSuccessUrl("/parcel/distribution/content/index").successForwardUrl("/parcel/distribution/content/index")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("../parcel/distribution/logout")).logoutUrl("../parcel/distribution/logout")
                .and().exceptionHandling()
                .accessDeniedPage("/parcel/distribution/error/accessDenied")
                .and().csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }
}
