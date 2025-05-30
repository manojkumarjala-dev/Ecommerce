package project.ecommerce.catalogue_management_service.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private double ratings;
    private int noOfRatings;
    private double actualPrice;

    // Constructors, getters, setters
}
