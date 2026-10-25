package com.example.rbar_bcknd.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String productType;
    private String description;
    private Integer price;
    private String photoLink;
}
