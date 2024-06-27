package EcommerceWebsite.example.Ecomerce.Controllers;

import EcommerceWebsite.example.Ecomerce.DTO.ProductDTO.CategoryResponseDTO;
import EcommerceWebsite.example.Ecomerce.DTO.ProductDTO.ProductResponseDTO;
import EcommerceWebsite.example.Ecomerce.Models.Category;
import EcommerceWebsite.example.Ecomerce.Service.CategoryService;
import EcommerceWebsite.example.Ecomerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;
    @Autowired
    public CategoryController(CategoryService categoryService,ProductService productService){
        this.categoryService=categoryService;
        this.productService=productService;
    }
    @PostMapping("/createcategoryname")
    public CategoryResponseDTO createcategory(@RequestBody Category category){
        return categoryService.createcategory(category);
    }
    @GetMapping("/allcategories")
    public List<CategoryResponseDTO> listallcategories(){
        return categoryService.listallcategories();
    }

    @GetMapping("/{name}/products")
    public List<ProductResponseDTO> listcategoryproducts(@PathVariable("name") String name){
        return productService.listcategoryproducts(name);
    }

    @DeleteMapping("/{name}")
    public void deletecategory(@PathVariable("name") String name){
        categoryService.deletecategory(name);
    }

    @PostMapping("/updatecategoryname/{name}")
    public CategoryResponseDTO update(@PathVariable("name") String name, @RequestBody Category category){
        return categoryService.update(name,category);
    }
}
