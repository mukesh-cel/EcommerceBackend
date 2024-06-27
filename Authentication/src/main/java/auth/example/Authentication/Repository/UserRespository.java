package auth.example.Authentication.Repository;

import auth.example.Authentication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserRespository extends JpaRepository<User,Long> {
    @Query(value = "select * from user where email =?1" , nativeQuery = true)
    User findbyemail(String email);
}
