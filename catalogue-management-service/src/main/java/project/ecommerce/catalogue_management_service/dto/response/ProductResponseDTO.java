package project.ecommerce.catalogue_management_service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long sku;
    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private int actualPrice;
}

