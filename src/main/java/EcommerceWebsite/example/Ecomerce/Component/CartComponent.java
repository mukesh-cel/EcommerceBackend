package EcommerceWebsite.example.Ecomerce.Component;

import EcommerceWebsite.example.Ecomerce.DTO.CartDTO.CartRequestDTO;
import EcommerceWebsite.example.Ecomerce.DTO.CartDTO.CartResponseDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class CartComponent {

    private RestTemplateBuilder restTemplateBuilder;
    private String carturl="http://localhost:8091/cart";

    public CartComponent(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public ResponseEntity<CartResponseDTO> addtocart( CartRequestDTO cartRequestDTO) {

        String addcarturl=carturl+ "/addtocart";
        RestTemplate restTemplate= restTemplateBuilder.build();

        return  restTemplate.postForEntity(addcarturl, cartRequestDTO, CartResponseDTO.class);
    }

    public List<CartResponseDTO> cartreview(Long id) {
        String reviewurl=carturl+"/review/"+ id;

        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<CartResponseDTO[]> responseEntity =restTemplate.
                                              getForEntity(reviewurl, CartResponseDTO[].class);

        CartResponseDTO[] cartResponseDTOS= responseEntity.getBody();

        return Arrays.asList(cartResponseDTOS);
    }


    public ResponseEntity deleteproductincart(@RequestBody CartRequestDTO cartRequestDTO){
        String deleteurl=carturl+"/deleteproductincart";

        RestTemplate restTemplate= restTemplateBuilder.build();


        //restTemplate.exchange(deleteurl,HttpMethod.DELETE,null,cartRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

}
