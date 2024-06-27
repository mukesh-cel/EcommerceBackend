package Cart.example.Cart.Service.Controller;

import Cart.example.Cart.Service.DTO.CartRequestDTO;
import Cart.example.Cart.Service.DTO.CartResponseDTO;
import Cart.example.Cart.Service.Exception.CustomException;
import Cart.example.Cart.Service.Service.CartService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public CartController(CartService cartService,RestTemplateBuilder restTemplateBuilder){
        this.cartService=cartService;
        this.restTemplateBuilder=restTemplateBuilder;
    }

    @PostMapping("/addtocart")
    public ResponseEntity<CartResponseDTO> addtocart(@RequestBody CartRequestDTO cartRequestDTO){

        String validateuserurl="http://localhost:8090/users/validateuserandtoken/" + cartRequestDTO.getUser_Id()
                +"/"+cartRequestDTO.getToken();


        RestTemplate restTemplate=restTemplateBuilder.build();
        Object Httpstatus = null;
        ResponseEntity<HttpStatus> httpStatus= restTemplate.postForEntity(validateuserurl, Httpstatus,HttpStatus.class);

        return  cartService.addtocart(cartRequestDTO);
    }

    @GetMapping("/review/{Id}")
    public List<CartResponseDTO> cartreview(@PathVariable("Id") Long Id){
        return cartService.cartreview(Id);
    }

    @DeleteMapping("/deleteproductincart")
    public ResponseEntity deleteproductincart(@RequestBody CartRequestDTO cartRequestDTO){
        String validateuserurl="http://localhost:8090/users/validateuserandtoken/" + cartRequestDTO.getUser_Id()
                +"/"+cartRequestDTO.getToken();
        RestTemplate restTemplate=restTemplateBuilder.build();
        Object Httpstatus = null;
        ResponseEntity<HttpStatus> httpStatus= restTemplate.postForEntity(validateuserurl, Httpstatus,HttpStatus.class);

        return cartService.deleteproductincart(cartRequestDTO);
    }
}
