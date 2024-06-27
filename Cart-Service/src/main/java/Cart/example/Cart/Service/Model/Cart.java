package Cart.example.Cart.Service.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Cart extends BaseModel{
    private long userId;
    private String productname;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    private Date addedat;
    private long quantity;
    private long totalcost;
}
