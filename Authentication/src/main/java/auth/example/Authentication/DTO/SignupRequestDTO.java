package auth.example.Authentication.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
