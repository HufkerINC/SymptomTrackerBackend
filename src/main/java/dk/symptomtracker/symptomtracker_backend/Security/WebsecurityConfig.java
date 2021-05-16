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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;
import java.util.Arrays;


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
                .loginPage("/SymptomTrackerFrontend/login") // Den form som man skal poste ovenstående fra finder man ved at følge denne sti.
                .defaultSuccessUrl("/userMainPage") // Efter login skal brugeren rediregeres til denne side.
                .usernameParameter("email") // Når man poster et logind så skal email tolkes som username. Ellers vil spring forvente et username.
                .permitAll() // Alle de sider som er nødvendige for at gøre ovenstående skal være lovlige før log-ind
                .and()
                .logout().permitAll() // Der skal være en side man skal logge ud på og denne skal være lovlig
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // den side man logger ud på
                .and()
                .cors(withDefaults()) // Fortæller Spring security at den skal bruge den CORS bean som vi definbere nedenfor.
                .csrf().disable(); // Vi fortæller Spring security at den sikkerhedsmekanismen med at sætte tokens ind på alle html sider og forvente at dette token bliver inkluderet i alle posts, slås fra. Dette er vi nødt til at disable fordi vi ikke bruger thymeleas som ellers plejer at tage sig af det.
    }


    // CORS er et sikkerhedssystem i alle browsere til at sikre at backenden kun accepterer requests som kommer fra de rigtige webdomæner.
    // Jeg skal bruge det her fordi jeg køre to forskellige servere til henholdsvis front- og back-end.
    // Spring starter security tilbyder måder at konfigurere SPRINGS CORS-funktionalitet.
    // Denne bean bruges til at fortælle Spring Security hvilke andre web domæner som også har lov til at sende services til os.
    // Den skal bruges fordi vores backend og frontend er separate og således har forskellige domæner (url, port mv.)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:63342")); // Dette er noget lort fordi vi helst ikke vil hardcode den. Her skal der injectes nogle konfigurationer ind når jeg kommer til deployment en dag. Det kan lade sig gøre Heruku i hvert fald.
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(passwordEncoder());
    }

}
