package EcommerceWebsite.example.Ecomerce.Service;

import EcommerceWebsite.example.Ecomerce.DTO.ProductDTO.ProductResponseDTO;
import EcommerceWebsite.example.Ecomerce.Models.Category;
import EcommerceWebsite.example.Ecomerce.Models.Product;
import EcommerceWebsite.example.Ecomerce.Repository.CategoryRepository;
import EcommerceWebsite.example.Ecomerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    public ProductResponseDTO conversionto(Product product){
        ProductResponseDTO productResponseDTO= new ProductResponseDTO();
        productResponseDTO.setName(product.getName());
        productResponseDTO.setTittle(product.getTittle());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setImage(product.getImage());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setCategoryname(product.getCategory().getName());

        return productResponseDTO;
    }


    public List<ProductResponseDTO> getallproducts(){
        List<Product> products=productRepository.findAll();

        List<ProductResponseDTO> productResponseDTO= new ArrayList<>();
        for(Product p: products){
            productResponseDTO.add(conversionto(p));
        }
        return productResponseDTO;
    }


    public ProductResponseDTO getproductsbyname(String name){
        return conversionto(productRepository.findbyname(name));
    }


    public ProductResponseDTO addnewproduct(String name,Product product){
        Category category= categoryRepository.findbyname(product.getCategory().getName());
        if(category==null){
            Category category1= new Category();
            category1.setName(product.getCategory().getName());
            category1.setDescription(product.getCategory().getDescription());
            List<Product> products= new ArrayList<>();
            products.add(product);
            category1.setProducts(products);
            categoryRepository.save(category1);
            product.setCategory(category1);
            Product product1= productRepository.save(product);
            return conversionto(product1);
        }
        product.setCategory(category);
        Product product1= productRepository.save(product);
        return conversionto(product1);

    }

    public List<ProductResponseDTO> listcategoryproducts(@PathVariable("name") String name){
        Category category=categoryRepository.findbyname(name);
        List<ProductResponseDTO> productResponseDTO= new ArrayList<>();
        List<Product> products=category.getProducts();
        for(Product p : products){
            productResponseDTO.add(conversionto(p));
        }
        return productResponseDTO;
    }

    public ProductResponseDTO updateproductbyname(Product product){
        Product product1=productRepository.findbyname(product.getName());   //iphone11
        product1.setName(product.getName());
        product1.setTittle(product.getTittle());
        product1.setPrice(product.getPrice());
        product1.setImage(product.getImage());
        product1.setDescription(product.getDescription());

        Category category= new Category();
        category.setName(product.getCategory().getName());
        product1.setCategory(category);

        productRepository.save(product1);

        return conversionto(product1);
    }

    public void deleteproductbyname( String name){
        productRepository.deletebyname(name);
    }

}
