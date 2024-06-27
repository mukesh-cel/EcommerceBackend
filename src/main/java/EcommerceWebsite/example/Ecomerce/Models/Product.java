package EcommerceWebsite.example.Ecomerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String tittle;
    private int price;
    @ManyToOne
    private Category Category;
    private String Description;
    private String image;
}
