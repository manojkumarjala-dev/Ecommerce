package project.ecommerce.catalogue_management_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sku;

    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private int actualPrice;

    // Constructors, getters, setters
}
