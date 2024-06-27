package auth.example.Authentication.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class AuthenticationToken extends BaseModel{
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "Id")
    private User user;
    private String token;
    private Date created_At;
}
