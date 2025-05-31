package project.ecommerce.catalogue_management_service.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.ecommerce.catalogue_management_service.dto.request.ProductRequestDTO;
import project.ecommerce.catalogue_management_service.dto.response.ProductResponseDTO;

public interface ProductService {
    Page<ProductResponseDTO> getFilteredProducts(
            String mainCategory,
            Integer minPrice,
            Integer maxPrice,
            Pageable pageable
    );
    List<ProductResponseDTO> getAllProducts(Pageable pageable);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTODto);
    void deleteProduct(Long id);
    List<ProductResponseDTO> getProductsInPriceRange(Pageable pageable, int minPrice, int maxPrice);
}
