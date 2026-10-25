package com.example.rbar_bcknd.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "Product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "ProductId")
    private Long productId;

    @Column(name = "ProductType")
    private String productType;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private Integer price;

    @Column(name = "ExpDate")
    private LocalDate expDate;

    @Column(name="StartDate")
    private LocalDate startDate;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "Description")
    private String description;

    @Column(name = "PhotoLink")
    private String photoLink;
}
