package EcommerceWebsite.example.Ecomerce.Service;

import EcommerceWebsite.example.Ecomerce.Component.Usercomponent;
import EcommerceWebsite.example.Ecomerce.DTO.UserDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Usercomponent usercomponent;

    @Autowired
    public UserService(Usercomponent usercomponent){
        this.usercomponent=usercomponent;
    }

    public ResponseEntity<SignupResponseDTO> signup(SignupRequestDTO signupRequestDTO) {
        return usercomponent.signup(signupRequestDTO);
    }

    public ResponseEntity<SigninResponseDTO> signin(SigninRequestDTO signinRequestDTO) {
        return  usercomponent.signin(signinRequestDTO);
    }

    public ResponseEntity passwordreset(PassowordresetDTO passowordresetDTO) {
        return usercomponent.passwordreset(passowordresetDTO);
    }
}
