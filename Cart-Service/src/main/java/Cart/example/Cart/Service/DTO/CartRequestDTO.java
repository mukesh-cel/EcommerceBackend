package Cart.example.Cart.Service.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDTO {

    private long User_Id;
    private String Productname;
    private long quantity;
    private String token;
}
