package project.ecommerce.catalogue_management_service.service;
import java.util.List;

import project.ecommerce.catalogue_management_service.dto.ProductDto;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto createProduct(ProductDto productDto);
    void deleteProduct(Long id);
    // Add more methods as needed
}
