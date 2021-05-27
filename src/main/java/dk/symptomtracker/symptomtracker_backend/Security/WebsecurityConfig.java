package dk.symptomtracker.symptomtracker_backend.Security;

import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
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

        // On which sites is authorization (session coockie) needen for access?
        http.authorizeRequests()
                .antMatchers("/createAccount/**").permitAll() // Disse sider må brugeren poste til uden at være logget ind (NB! Securyty sørger for at brugeren ligeledes må poste til /login)
                .anyRequest().authenticated() // Alle andre requests end ovenstående skal man være logget ind for at tilgå.

                // What should happen if the above restrictions on authorization of access is not met (client tries to access without cookie)
                // Instead of security default redirect --> failure to authorize senario
                // When client (witcout cookie) tries to contact protected URLs (endpoint they are not authorized to), create HttpStatusEntryPoint obj and return appropriate statuscode.
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // UNAUTHORIZED = statuscode 401.


                // Is the password and username correct. What should happen if authorization of user (through password and username) is a) confirmed or b) denied.
                // Instead of security default redirect.
                // Standard login form that sends 204-NO_CONTENT when login is OK and 401-UNAUTHORIZED when login fails
                .and()
                .formLogin()// Login happens through a form post.
                .successHandler((request, response, auth) -> response.setStatus(HttpStatus.NO_CONTENT.value()))
                .failureHandler(new SimpleUrlAuthenticationFailureHandler()) // UNAUTHORIZED = statuscode 401 is returned in pesponse.
                .usernameParameter("email") // Når man poster et logind så skal email tolkes som username. Ellers vil spring forvente et username.

                // There has to be an endpoint for logout.
                // There handler will return a no-content statuscode when succes instead of default redirect.
                // standard logout that sends 204-NO_CONTENT when logout is OK
                .and()
                .logout()// There has to be an endpoint for logout
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))

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
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:1234")); // Dette er noget lort fordi vi helst ikke vil hardcode den. Her skal der injectes nogle konfigurationer ind når jeg kommer til deployment en dag. Det kan lade sig gøre Heruku i hvert fald.
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type"));
        configuration.setAllowCredentials(true); // It is allowed for the client (browser) to access the cookie in response header.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }






    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(passwordEncoder());
    }

}

// Security configuration info on: https://stackoverflow.com/questions/31714585/spring-security-disable-login-page-redirect