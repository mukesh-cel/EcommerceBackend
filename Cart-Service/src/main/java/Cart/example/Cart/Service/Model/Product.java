package Cart.example.Cart.Service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String tittle;
    private int price;
    private String Categoryname;
    private String Description;
    private String image;
    @ManyToOne
    private Cart cart;
}
