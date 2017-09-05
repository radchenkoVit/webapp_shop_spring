package configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Import(DBConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("developer").password("password").roles("USER", "DEVELOPER");
//    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        /*
        * In memory authe definition by setting username and password
        * */

        authBuilder.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");

        authBuilder.inMemoryAuthentication()
                .withUser("developer")
                .password("password")
                .roles("DEVELOPER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/addApplication.html").hasRole("DEVELOPER") //forbid to see add application page except user with Developer Role
                .antMatchers("/**").hasAnyRole("USER", "DEVELOPER")
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .exceptionHandling().accessDeniedPage("/error"); //TODO: change page
    }
}
