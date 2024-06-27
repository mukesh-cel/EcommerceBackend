package EcommerceWebsite.example.Ecomerce.DTO.UserDTO;


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
