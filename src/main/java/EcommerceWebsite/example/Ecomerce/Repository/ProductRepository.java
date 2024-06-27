package EcommerceWebsite.example.Ecomerce.Repository;

import EcommerceWebsite.example.Ecomerce.Models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "select * from ecommercedb3.product where name = ?1", nativeQuery = true)
    Product findbyname(String name);

    @Modifying
    @Query(value = "delete from product p where p.name =?1", nativeQuery = true)
    void deletebyname(String name);

    @Query(value = "select from product p where p.category_id =?1" , nativeQuery = true)
    List<Product> findbycategoryId(UUID uuid);
}
