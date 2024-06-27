package Cart.example.Cart.Service.Repository;

import Cart.example.Cart.Service.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from cart_service.product where name =?1", nativeQuery = true)
    Product findbyname(String productname);

    @Modifying
    @Query(value = "delete from cart_service.product where cart_id =?1" , nativeQuery = true)
    void deletbycartid(long id);
}
