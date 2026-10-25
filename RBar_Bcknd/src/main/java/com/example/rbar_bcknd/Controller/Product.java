package com.example.rbar_bcknd.Controller;

import com.example.rbar_bcknd.DataTransferObject.ProductDTO;
import com.example.rbar_bcknd.Service.ProductHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Product")
@CrossOrigin(origins = "http://localhost:4200")
public class Product {
    @Autowired
    private ProductHandler productHandler;


    @GetMapping("/GetAll")
    public List<ProductDTO> GetAllProduct(){
        return productHandler.ProductQueryAll();
    }

    @GetMapping("/Get")
    public ProductDTO GetProduct(Long productId){
        return productHandler.ProductQuerySingle(productId);
    }
}
