package Cart.example.Cart.Service.DTO;

import Cart.example.Cart.Service.Model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CartResponseDTO {
    private Long userId;
    private Date addedat;
    private long quantity;
    private long totalcost;
    private List<ProductResponseDTO> productResponseDTOS;
}
