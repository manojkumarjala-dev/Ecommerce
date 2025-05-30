package project.ecommerce.catalogue_management_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ecommerce.catalogue_management_service.dto.ProductRequestDTO;
import project.ecommerce.catalogue_management_service.dto.ProductResponseDTO;
import project.ecommerce.catalogue_management_service.model.Product;
import project.ecommerce.catalogue_management_service.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;

    // Map Product <-> ProductDto (manual or with ModelMapper/MapStruct)
    private ProductResponseDTO mapToDto(Product product) {
        ProductResponseDTO productResponseDto = new ProductResponseDTO();
        productResponseDto.setSku(product.getSku());
        productResponseDto.setName(product.getName());
        productResponseDto.setMainCategory(product.getMainCategory());
        productResponseDto.setSubCategory(product.getSubCategory());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setActualPrice(product.getActualPrice());
        return productResponseDto;
    }

    private Product mapToEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setMainCategory(dto.getMainCategory());
        product.setSubCategory(dto.getSubCategory());
        product.setImage(dto.getImage());
        product.setActualPrice(dto.getActualPrice());

        return product;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDto(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = mapToEntity(productRequestDTO);
        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
