package auth.example.Authentication.Controller;

import auth.example.Authentication.DTO.*;
import auth.example.Authentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
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

    @PostMapping("/validateuserandtoken/{Id}/{token}")
    public ResponseEntity Validateuserandtoken ( @PathVariable("Id") Long Id,@PathVariable String token
                                                ){
        return userService.Validateuserandtoken(Id,token);
    }
}
