package project.ecommerce.catalogue_management_service.service;
import java.util.List;

import org.springframework.data.domain.Pageable;
import project.ecommerce.catalogue_management_service.dto.ProductRequestDTO;
import project.ecommerce.catalogue_management_service.dto.ProductResponseDTO;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts(Pageable pageable);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTODto);
    void deleteProduct(Long id);
    List<ProductResponseDTO> getProductsInPriceRange(Pageable pageable, int minPrice, int maxPrice);
 //   List<ProductResponseDTO> getProductsSortedByPrice(String mainCategory, int priceRange);
    // Add more methods as needed
}
