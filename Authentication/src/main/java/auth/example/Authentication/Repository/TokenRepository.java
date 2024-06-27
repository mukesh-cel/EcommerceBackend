package auth.example.Authentication.Repository;

import auth.example.Authentication.Models.AuthenticationToken;
import auth.example.Authentication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Long> {

    @Query(value = "select * from auth_service.authentication_token where user_id =?1" , nativeQuery = true)
    AuthenticationToken findbyuser_id(Long Id);

    @Query(value = "select user_id from auth_service.authentication_token where token=?1" , nativeQuery = true)
    long findbyusertoken(String token);

    @Query(value = "select * from auth_service.authentication_token where token =?1" , nativeQuery = true)
    AuthenticationToken findbytoken(String token);
}
