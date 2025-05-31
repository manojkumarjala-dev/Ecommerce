package project.ecommerce.catalogue_management_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterRequest {
    private int page = 0;
    private int size = 10;
    private boolean sortByPrice = false;
    private String mainCategory;
    private Integer minPrice;
    private Integer maxPrice;
    private String keyword;
}
