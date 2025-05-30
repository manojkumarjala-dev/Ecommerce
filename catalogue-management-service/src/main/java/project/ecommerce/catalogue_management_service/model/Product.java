package project.ecommerce.catalogue_management_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private double ratings;
    private int noOfRatings;
    private double actualPrice;

    // Constructors, getters, setters
}
