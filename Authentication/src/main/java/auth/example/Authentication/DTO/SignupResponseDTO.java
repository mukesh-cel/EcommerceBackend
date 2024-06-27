package auth.example.Authentication.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDTO {
    private String email;
    private String firstname;
    private String lastname;
    private String token;
}
