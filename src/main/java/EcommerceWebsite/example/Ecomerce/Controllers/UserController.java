package EcommerceWebsite.example.Ecomerce.Controllers;


import EcommerceWebsite.example.Ecomerce.DTO.UserDTO.*;
import EcommerceWebsite.example.Ecomerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController (UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(SignupRequestDTO signupRequestDTO){
         return userService.signup(signupRequestDTO);
    }
    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDTO> signin(@RequestBody SigninRequestDTO signinRequestDTO){
        return userService.signin(signinRequestDTO);
    }

    @PostMapping("/passwordreset")
    public ResponseEntity paswwordreset(@RequestBody PassowordresetDTO passowordresetDTO){
        return userService.passwordreset(passowordresetDTO);
    }
}
