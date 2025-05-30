package project.ecommerce.catalogue_management_service.service;
import java.util.List;

import project.ecommerce.catalogue_management_service.dto.ProductRequestDTO;
import project.ecommerce.catalogue_management_service.dto.ProductResponseDTO;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTODto);
    void deleteProduct(Long id);
    // Add more methods as needed
}
