package Cart.example.Cart.Service.Repository;


import Cart.example.Cart.Service.Model.Cart;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select * from cart_service.cart where user_id =?1 ", nativeQuery = true)
    List<Cart> findbyuserId(Long id);
    @Query(value = "select * from cart_service.cart where user_id =?1 and product_id =?2" , nativeQuery = true)
    Cart findbyuserIdandproductId(long userId, long id);

    @Query(value = "select * from cart_service.cart where user_id =?1 and productname =?2" , nativeQuery = true)
    Cart findbyuserIdandproductname(long userId, String productname);

    //void deleteById();
}
