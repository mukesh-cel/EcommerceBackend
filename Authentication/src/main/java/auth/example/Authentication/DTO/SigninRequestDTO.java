package auth.example.Authentication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequestDTO {

    private String email;
    private String password;
}
