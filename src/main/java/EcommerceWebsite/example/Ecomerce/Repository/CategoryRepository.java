package EcommerceWebsite.example.Ecomerce.Repository;

import EcommerceWebsite.example.Ecomerce.Models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = "select * from category where name =?1" , nativeQuery = true)
    Category findbyname(String name);

    @Modifying
    @Query(value = "delete from category c where c.name =?1", nativeQuery = true)
    void deletebyname(String name);
}
