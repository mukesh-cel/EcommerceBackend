package EcommerceWebsite.example.Ecomerce.DTO.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private String tittle;
    private int price;
    private String Category;
    private String Description;
    private String image;
}

