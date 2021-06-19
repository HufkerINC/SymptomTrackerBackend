package dk.symptomtracker.symptomtracker_backend.Security;

import dk.symptomtracker.symptomtracker_backend.Model.User;
import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Arrays;
import java.util.Optional;


public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User is not Found");
        }

        User user = userOptional.get();

        // Der bruges fully qualified name fordi der er tale om at der kaldes en konstructor på
        // Sprins securitys usgave af user og ikke en model-klasse-user. Der gives en email,
        // et password og en list af roles med som parametre til konstruktoren.
        // I dette tilfælde har useren kun en role "user" men det kunne være at han fx også skulle
        // have admin rettingheder.
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("user")));
    }

}
