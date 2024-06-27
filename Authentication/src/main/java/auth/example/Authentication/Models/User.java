package auth.example.Authentication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    @OneToOne(mappedBy = "user")
    private AuthenticationToken authentication;
}
