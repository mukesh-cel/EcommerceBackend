package auth.example.Authentication.Service;


import auth.example.Authentication.Models.AuthenticationToken;
import auth.example.Authentication.Models.User;
import auth.example.Authentication.Repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationTokenService {

    private TokenRepository tokenRepository;
    private SecretKey secretKey;

    @Autowired
    public AuthenticationTokenService (TokenRepository tokenRepository){
        this.tokenRepository=tokenRepository;
        this.secretKey= Jwts.SIG.HS256.key().build();;
    }

    public AuthenticationToken save(User user) {
        AuthenticationToken authenticationToken= new AuthenticationToken();
        authenticationToken.setUser(user);
        authenticationToken.setCreated_At(new Date());

        Map<String, Object> jwtdata = new HashMap<>();
        jwtdata.put("email", user.getEmail());
        jwtdata.put("firstname", user.getFirstname());
        jwtdata.put("lastname" , user.getLastname());

        String token= Jwts.builder().claims(jwtdata).signWith(secretKey).compact();

        authenticationToken.setToken(token);

        return tokenRepository.save(authenticationToken);
    }


}
