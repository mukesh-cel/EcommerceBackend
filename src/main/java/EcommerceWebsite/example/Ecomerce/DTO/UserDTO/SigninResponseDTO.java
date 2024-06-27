package EcommerceWebsite.example.Ecomerce.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninResponseDTO {
    private String email;
    private String token;
}
