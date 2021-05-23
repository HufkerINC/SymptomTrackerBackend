package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import dk.symptomtracker.symptomtracker_backend.Sevices.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@RestController
public class AccountRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/createAccount")
    public ResponseEntity createAccount(WebRequest dataFromFormCreateAccount){

        CreateAccountService createAccountService = new CreateAccountService();

        String password = dataFromFormCreateAccount.getParameter("password");
        String password2 = dataFromFormCreateAccount.getParameter("passwordRepeat");
        String email = dataFromFormCreateAccount.getParameter("email");

        if (!(password.equals(password2))) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST); } // Status code 400

        boolean accountAdded = createAccountService.addAccountToDB(email, password, passwordEncoder, userRepository);

        if(accountAdded){
            return new ResponseEntity(HttpStatus.CREATED) ; } // Status code 201

        else{
            return new ResponseEntity(HttpStatus.FORBIDDEN); } // Status code 403

    }
}

// info: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
// info: https://www.baeldung.com/spring-mvc-controller-custom-http-status-code
// info: https://stackoverflow.com/questions/24292373/spring-boot-rest-controller-how-to-return-different-http-status-codes