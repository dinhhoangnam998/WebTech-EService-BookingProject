package webtech.gr14.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/acc/profiles/**").hasRole("GUEST")
                .antMatchers("/guest/reserve**").hasRole("GUEST")
                .antMatchers("/host/**").hasRole("HOST")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
            .formLogin()
                .loginPage("/acc/login")
                .loginProcessingUrl("/acc/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/acc/login/success")
                .failureUrl("/acc/login/failure")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/403");
    }
}
