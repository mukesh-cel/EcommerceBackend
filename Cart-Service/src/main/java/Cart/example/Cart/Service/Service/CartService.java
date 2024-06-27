package Cart.example.Cart.Service.Service;

import Cart.example.Cart.Service.DTO.CartRequestDTO;
import Cart.example.Cart.Service.DTO.CartResponseDTO;
import Cart.example.Cart.Service.DTO.ProductResponseDTO;
import Cart.example.Cart.Service.Model.Cart;
import Cart.example.Cart.Service.Model.Product;
import Cart.example.Cart.Service.Repository.CartRepository;
import Cart.example.Cart.Service.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    private CartRepository cartRepository;
    private RestTemplateBuilder restTemplateBuilder;
    private ProductRepository productRepository;

    @Autowired
    public CartService (CartRepository cartRepository,RestTemplateBuilder restTemplateBuilder
    ,ProductRepository productRepository){
        this.restTemplateBuilder=restTemplateBuilder;
        this.cartRepository=cartRepository;
        this.productRepository=productRepository;
    }

    public ResponseEntity<CartResponseDTO> addtocart(CartRequestDTO cartRequestDTO) {
        String produrl="http://localhost:8080/products/search/" + cartRequestDTO.getProductname();
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<Product> responseEntity=restTemplate.getForEntity(produrl, Product.class);
        Product product=responseEntity.getBody();

        if (!Objects.isNull(cartRepository.findbyuserIdandproductname(cartRequestDTO.getUser_Id(),
                    cartRequestDTO.getProductname()))) {

            Cart cart = cartRepository.findbyuserIdandproductname(cartRequestDTO.getUser_Id(),
                        cartRequestDTO.getProductname());

            cart.setQuantity(cartRequestDTO.getQuantity() + cart.getQuantity());
            cart.setAddedat(new Date());
            cart.setTotalcost(cart.getTotalcost() + (product.getPrice() * cartRequestDTO.getQuantity()));
            cartRepository.save(cart);
        }

        else {
            Cart cart = new Cart();
            cart.setAddedat(new Date());
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            cart.setProducts(productList);
            cart.setQuantity(cartRequestDTO.getQuantity());
            cart.setTotalcost(product.getPrice() * cartRequestDTO.getQuantity());
            cart.setUserId(cartRequestDTO.getUser_Id());
            cart.setProductname(cartRequestDTO.getProductname());
            cartRepository.save(cart);

            assert product != null;
            product.setCart(cart);;
            productRepository.save(product);
        }


        CartResponseDTO cartResponseDTO= new CartResponseDTO();

        cartResponseDTO.setAddedat(new Date());
        cartResponseDTO.setUserId(cartRequestDTO.getUser_Id());
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        List<ProductResponseDTO> productResponseDTOList= new ArrayList<>();
        for(Product p:productList){
            ProductResponseDTO productResponseDTO= new ProductResponseDTO();
            productResponseDTO.setCategoryname(p.getCategoryname());
            productResponseDTO.setName(p.getName());
            productResponseDTO.setImage(p.getImage());
            productResponseDTO.setTittle(p.getTittle());
            productResponseDTO.setPrice(p.getPrice());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTOList.add(productResponseDTO);

        }
        cartResponseDTO.setProductResponseDTOS(productResponseDTOList);
        cartResponseDTO.setQuantity(cartRequestDTO.getQuantity());
        cartResponseDTO.setTotalcost(product.getPrice()* cartRequestDTO.getQuantity());


        return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
    }


    public List<CartResponseDTO> cartreview(Long id) {
        List<Cart> cartList= cartRepository.findbyuserId(id);

        List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();

        for(Cart c: cartList){
            CartResponseDTO cartResponseDTO= new CartResponseDTO();
            cartResponseDTO.setAddedat(new Date());
            cartResponseDTO.setUserId(c.getUserId());
            List<Product> productList= c.getProducts();
            List<ProductResponseDTO> productResponseDTOList= new ArrayList<>();
            for(Product p:productList){
                ProductResponseDTO productResponseDTO= new ProductResponseDTO();
                productResponseDTO.setCategoryname(p.getCategoryname());
                productResponseDTO.setName(p.getName());
                productResponseDTO.setImage(p.getImage());
                productResponseDTO.setTittle(p.getTittle());
                productResponseDTO.setPrice(p.getPrice());
                productResponseDTO.setDescription(p.getDescription());
                productResponseDTOList.add(productResponseDTO);

            }
            cartResponseDTO.setProductResponseDTOS(productResponseDTOList);
            cartResponseDTO.setQuantity(c.getQuantity());
            cartResponseDTO.setTotalcost(c.getTotalcost());
            cartResponseDTOList.add(cartResponseDTO);
        }
        return cartResponseDTOList;
    }
    @Transactional
    public ResponseEntity deleteproductincart(CartRequestDTO cartRequestDTO) {
       Cart cart= cartRepository.findbyuserIdandproductname(cartRequestDTO.getUser_Id(),cartRequestDTO.getProductname());
       Product product= productRepository.findbyname(cartRequestDTO.getProductname());
       long ans=cart.getQuantity()-cartRequestDTO.getQuantity();
       if(ans==0){
           System.out.println(cart.getId());
           productRepository.deletbycartid(cart.getId());
           cartRepository.delete(cart);

           return new ResponseEntity(HttpStatus.OK);
       }
       cart.setQuantity(ans);
       cart.setTotalcost(cart.getTotalcost()- cartRequestDTO.getQuantity()*product.getPrice());
       cart.setAddedat(new Date());
       cartRepository.save(cart);
       return new ResponseEntity(HttpStatus.OK);
    }

}
