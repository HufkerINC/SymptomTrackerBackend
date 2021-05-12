package dk.symptomtracker.symptomtracker_backend.Security;

import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Bean
    public UserDetailsServiceImpl userDetailsServiceImpl(){
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()// Vi vil gerne autoiserer hvilke http requests som må finde sted i hele programmet. Hvornår må programmet køre som "normalt"
                .antMatchers("/createAccount/**").permitAll() // Disse sider må brugeren poste til uden at være logget ind (NB! Securyty sørger for at brugeren ligeledes må poste til /login)
                .anyRequest().authenticated() // Alle andre requests end ovenstående skal man være logget ind for at tilgå.
                .and()
                .formLogin() // Man logger ind gennem en form i html som man poster fra.
                .loginPage("/login") // Den form som man skal poste ovenstående fra finder man ved at følge denne sti.
                .defaultSuccessUrl("/userMainPage") // Efter login skal brugeren rediregeres til denne side.
                .usernameParameter("email") // Når man poster et logind så skal email tolkes som username. Ellers vil spring forvente et username.
                .permitAll() // Alle de sider som er nødvendige for at gøre ovenstående skal være lovlige før log-ind
                .and()
                .logout().permitAll() // Der skal være en side man skal logge ud på og denne skal være lovlig
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // den side man logger ud på
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(passwordEncoder());
    }

}
