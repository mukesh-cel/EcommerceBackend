package EcommerceWebsite.example.Ecomerce.DTO.ProductDTO;

import EcommerceWebsite.example.Ecomerce.Models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CategoryResponseDTO {
    private String name;
    private String description;
    private List<Product> products;
}
