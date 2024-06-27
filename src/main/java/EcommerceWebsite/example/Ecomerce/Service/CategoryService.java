package EcommerceWebsite.example.Ecomerce.Service;

import EcommerceWebsite.example.Ecomerce.DTO.ProductDTO.CategoryResponseDTO;
import EcommerceWebsite.example.Ecomerce.Models.Category;
import EcommerceWebsite.example.Ecomerce.Repository.CategoryRepository;
import EcommerceWebsite.example.Ecomerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    public CategoryResponseDTO conversionto(Category category){
        CategoryResponseDTO categoryResponseDTO= new CategoryResponseDTO();
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setDescription(category.getDescription());
        int m=category.getProducts().size();
        while(m>0){
            categoryResponseDTO.setProducts(category.getProducts());
            m--;
        }

        return categoryResponseDTO;
    }
    public CategoryResponseDTO createcategory(Category category){
        Category category1= categoryRepository.save(category);
        return conversionto(category1);
    }

    public List<CategoryResponseDTO> listallcategories(){
        List<Category> categories=categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTO= new ArrayList<>();
        for(Category c:categories){
            CategoryResponseDTO categoryResponseDTO1= new CategoryResponseDTO();
            categoryResponseDTO1.setName(c.getName());
            categoryResponseDTO.add(categoryResponseDTO1);
        }
        return categoryResponseDTO;
    }

    public void deletecategory(String name){
        categoryRepository.deletebyname(name);
    }

    public CategoryResponseDTO update(String name,  Category category){
        Category category1= categoryRepository.findbyname(name);
        if(category.getName()!=null) {
            category1.setName(category.getName());
        }
        if(category.getDescription()!=null) {
            category1.setDescription(category.getDescription());
        }
        categoryRepository.save(category1);
        return conversionto(category1);
    }
}
