package com.example.rbar_bcknd.Service;

import com.example.rbar_bcknd.DataTransferObject.ProductDTO;
import com.example.rbar_bcknd.Entity.Product;
import com.example.rbar_bcknd.UserRepository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductHandler {
    private final ProductRepository productRepository;

    ProductHandler(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<ProductDTO> ProductQueryAll(){
        System.out.println("Ide eljutt a függvny");
        List<Product> products =productRepository.searchActiveProducts(LocalDate.now());
        List<ProductDTO> productDTOS=products.stream().map(product -> new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getProductType(),
                product.getDescription(),
                product.getPrice(),
                product.getPhotoLink()
        )).collect(Collectors.toList());
        System.out.println("Ide is eljutt a függvny");
        System.out.println(productDTOS);
        return productDTOS;
    }

    public ProductDTO ProductQuerySingle(Long productId){
        Product products=productRepository.searchSingleProduct(LocalDate.now(), productId);
        return new ProductDTO(products.getProductId(),
                products.getName(),
                products.getProductType(),
                products.getDescription(),
                products.getPrice(),
                products.getPhotoLink());
    }
}
