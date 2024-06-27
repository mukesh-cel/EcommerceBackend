package EcommerceWebsite.example.Ecomerce.DTO.CartDTO;

import lombok.Getter;
import lombok.Setter;

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
