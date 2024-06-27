package auth.example.Authentication.Service;


import auth.example.Authentication.DTO.*;
import auth.example.Authentication.Exception.CustomException;
import auth.example.Authentication.Models.AuthenticationToken;
import auth.example.Authentication.Models.User;
import auth.example.Authentication.Repository.TokenRepository;
import auth.example.Authentication.Repository.UserRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;


import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    private UserRespository userRespository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    public UserService(UserRespository userRespository, BCryptPasswordEncoder bCryptPasswordEncoder
                                                    ,AuthenticationTokenService authenticationTokenService
                                                             ,TokenRepository tokenRepository) {
        this.userRespository=userRespository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authenticationTokenService=authenticationTokenService;
        this.tokenRepository=tokenRepository;

    }

    @Transactional
    public ResponseEntity<SignupResponseDTO> signup(SignupRequestDTO signupRequestDTO) {

        if(Objects.nonNull(userRespository.findbyemail(signupRequestDTO.getEmail()))){
            throw new CustomException("user already exists");
        }

        User user1= new User();
        user1.setEmail(signupRequestDTO.getEmail());
        user1.setFirstname(signupRequestDTO.getFirstname());
        user1.setLastname(signupRequestDTO.getLastname());
        user1.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));

        User saveduser = userRespository.save(user1);



        AuthenticationToken authenticationToken= authenticationTokenService.save(saveduser);


        SignupResponseDTO signupResponseDTO= new SignupResponseDTO();
        signupResponseDTO.setEmail(saveduser.getEmail());
        signupResponseDTO.setFirstname(saveduser.getFirstname());
        signupResponseDTO.setLastname(saveduser.getLastname());
        signupResponseDTO.setToken(authenticationToken.getToken());

        return new ResponseEntity<>(signupResponseDTO, HttpStatus.OK);
    }

    public ResponseEntity<SigninResponseDTO> signin(SigninRequestDTO signinRequestDTO) {

        if(Objects.isNull(userRespository.findbyemail(signinRequestDTO.getEmail()))){
            throw new CustomException("No user exists, wrong user mail");
        }

        User user=userRespository.findbyemail(signinRequestDTO.getEmail());

        if(!bCryptPasswordEncoder.matches(signinRequestDTO.getPassword(), user.getPassword())){
            throw new CustomException("Wrong password");
        }


        AuthenticationToken authenticationToken= tokenRepository.findbyuser_id(user.getId());

        SigninResponseDTO signinResponseDTO= new SigninResponseDTO();
        signinResponseDTO.setEmail(user.getEmail());
        signinResponseDTO.setToken(authenticationToken.getToken());

        return new ResponseEntity<>(signinResponseDTO, HttpStatus.OK);


    }

    public ResponseEntity passwordreset(PassowordresetDTO passowordresetDTO) {
        if(Objects.isNull(userRespository.findbyemail(passowordresetDTO.getEmail()))){
            throw new CustomException("No user found");
        }
        User user=userRespository.findbyemail(passowordresetDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(passowordresetDTO.getNewpassword()));
        userRespository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity Validateuserandtoken(Long id, String token) {

        if(Objects.isNull(tokenRepository.findbytoken(token))){
            throw new CustomException("invalid token");
        }

        long Id= tokenRepository.findbyusertoken(token);

        if(Id!=id){
            throw new CustomException("user invalid");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
