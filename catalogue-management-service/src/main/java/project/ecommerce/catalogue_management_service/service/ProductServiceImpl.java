package project.ecommerce.catalogue_management_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.ecommerce.catalogue_management_service.dto.ProductRequestDTO;
import project.ecommerce.catalogue_management_service.dto.ProductResponseDTO;
import project.ecommerce.catalogue_management_service.exception.ProductCreationFailedException;
import project.ecommerce.catalogue_management_service.exception.ProductNotFoundException;
import project.ecommerce.catalogue_management_service.model.Product;
import project.ecommerce.catalogue_management_service.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    private ProductResponseDTO mapToDto(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setSku(product.getSku());
        dto.setName(product.getName());
        dto.setMainCategory(product.getMainCategory());
        dto.setSubCategory(product.getSubCategory());
        dto.setImage(product.getImage());
        dto.setActualPrice(product.getActualPrice());
        return dto;
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
    public List<ProductResponseDTO> getProductsInPriceRange(Pageable pageable, int minPrice, int maxPrice) {
        Page<Product> page = productRepository.findByPriceRange(minPrice, maxPrice, pageable);
        return page.map(this::mapToDto).getContent();
    }


    @Override
    public List<ProductResponseDTO> getAllProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return new ArrayList<>(page.map(this::mapToDto)
                .getContent());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return mapToDto(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        try {
            Product product = mapToEntity(dto);
            Product saved = productRepository.save(product);
            return mapToDto(saved);
        } catch (Exception e) {
            throw new ProductCreationFailedException("Failed to create product: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Cannot delete — product with ID " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}
