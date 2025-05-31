package project.ecommerce.catalogue_management_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Main category cannot be blank")
    private String mainCategory;

    private String subCategory;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @Positive(message = "Price must be a positive number")
    private int actualPrice;
}

