package EcommerceWebsite.example.Ecomerce.Component;

import EcommerceWebsite.example.Ecomerce.DTO.UserDTO.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class Usercomponent {

    private RestTemplateBuilder restTemplateBuilder;
    private String userurl="http://localhost:8090/users";

    public Usercomponent(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public ResponseEntity<SignupResponseDTO> signup(SignupRequestDTO signupRequestDTO) {

        String signupurl=userurl+"/signup";
        RestTemplate restTemplate =restTemplateBuilder.build();

       return restTemplate.postForEntity(signupurl, signupRequestDTO,SignupResponseDTO.class);

    }

    public ResponseEntity<SigninResponseDTO> signin(SigninRequestDTO signinRequestDTO) {
        String siginurl=userurl+"/signin";

        RestTemplate restTemplate=restTemplateBuilder.build();

        return restTemplate.postForEntity(siginurl,signinRequestDTO,SigninResponseDTO.class);
    }

    public ResponseEntity passwordreset(PassowordresetDTO passowordresetDTO) {
        String pswdurl=userurl+"/passwordreset";

        RestTemplate restTemplate=restTemplateBuilder.build();

         restTemplate.postForEntity(pswdurl,passowordresetDTO,PassowordresetDTO.class);

         return new ResponseEntity<>(HttpStatus.OK);
    }
}
