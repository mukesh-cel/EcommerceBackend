package EcommerceWebsite.example.Ecomerce.Controllers;

import EcommerceWebsite.example.Ecomerce.DTO.ProductDTO.ProductResponseDTO;
import EcommerceWebsite.example.Ecomerce.Models.Product;
import EcommerceWebsite.example.Ecomerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/allproducts")
    public List<ProductResponseDTO> getallproducts(){
        return productService.getallproducts();
    }

    @GetMapping("search/{name}")
    public ProductResponseDTO getproductsbyname(@PathVariable("name") String name){
        return productService.getproductsbyname(name);
    }

    @PostMapping("/addnewproduct/{categoryname}")
    public ProductResponseDTO addnewproduct(@PathVariable("categoryname") String name, @RequestBody Product product){
        return productService.addnewproduct(name,product);
    }

    @PostMapping("updatebyname/{name}")
    public ProductResponseDTO updateproductbyname(@PathVariable("name") String name,@RequestBody Product product){
        return productService.updateproductbyname(product);
    }

    @DeleteMapping("/{name}")
    public void deleteproductbyname(@PathVariable("name") String name){
        productService.deleteproductbyname(name);
    }


}
