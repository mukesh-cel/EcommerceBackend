package EcommerceWebsite.example.Ecomerce.Controllers;


import EcommerceWebsite.example.Ecomerce.DTO.CartDTO.CartRequestDTO;
import EcommerceWebsite.example.Ecomerce.DTO.CartDTO.CartResponseDTO;
import EcommerceWebsite.example.Ecomerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public  CartController (CartService cartService){
        this.cartService=cartService;
    }

    @PostMapping("/addtocart")
    public ResponseEntity<CartResponseDTO> addtocart(@RequestBody CartRequestDTO cartRequestDTO){
        return cartService.addtocart(cartRequestDTO);
    }

    @GetMapping("/review/{Id}")
    public List<CartResponseDTO> cartreview(@PathVariable("Id") Long Id){
        return cartService.cartreview(Id);
    }

    @DeleteMapping("/deleteproductincart")
    public ResponseEntity deleteproductincart(@RequestBody CartRequestDTO cartRequestDTO){
        return cartService.delete(cartRequestDTO);
    }
}
