package project.ecommerce.catalogue_management_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ecommerce.catalogue_management_service.dto.ProductDto;
import project.ecommerce.catalogue_management_service.model.Product;
import project.ecommerce.catalogue_management_service.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Map Product <-> ProductDto (manual or with ModelMapper/MapStruct)
    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        // set fields
        return productDto;
    }

    private Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        // set fields
        return product;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
