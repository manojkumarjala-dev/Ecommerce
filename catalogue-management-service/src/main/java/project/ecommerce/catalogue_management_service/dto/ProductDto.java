package project.ecommerce.catalogue_management_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long sku;
    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private double ratings;
    private int noOfRatings;
    private int actualPrice;
}
