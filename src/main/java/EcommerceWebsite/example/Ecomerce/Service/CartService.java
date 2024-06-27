package EcommerceWebsite.example.Ecomerce.Service;

import EcommerceWebsite.example.Ecomerce.Component.CartComponent;
import EcommerceWebsite.example.Ecomerce.DTO.CartDTO.CartRequestDTO;
import EcommerceWebsite.example.Ecomerce.DTO.CartDTO.CartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CartService {

    private CartComponent cartComponent;

    @Autowired
    public CartService (CartComponent cartComponent){
        this.cartComponent=cartComponent;
    }

    public ResponseEntity<CartResponseDTO> addtocart( CartRequestDTO cartRequestDTO) {
         return cartComponent.addtocart(cartRequestDTO);
    }

    public List<CartResponseDTO> cartreview(Long id) {
        return cartComponent.cartreview(id);
    }

    public ResponseEntity delete(CartRequestDTO cartRequestDTO) {
        return cartComponent.deleteproductincart(cartRequestDTO);
    }
}
