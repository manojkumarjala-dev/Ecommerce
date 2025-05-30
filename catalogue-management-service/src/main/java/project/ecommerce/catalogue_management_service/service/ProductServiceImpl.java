package project.ecommerce.catalogue_management_service.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ecommerce.catalogue_management_service.dto.ProductDto;
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
    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
//        modelMapper.map(product, productDto);
        productDto.setSku(product.getSku());
        productDto.setName(product.getName());
        productDto.setMainCategory(product.getMainCategory());
        productDto.setSubCategory(product.getSubCategory());
        productDto.setImage(product.getImage());
        productDto.setRatings(product.getRatings());
        productDto.setNoOfRatings(product.getNoOfRatings());
        productDto.setActualPrice(product.getActualPrice());
        return productDto;
    }

    private Product mapToEntity(ProductDto dto) {
        Product product = new Product();
//        modelMapper.map(dto, product);
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setMainCategory(dto.getMainCategory());
        product.setSubCategory(dto.getSubCategory());
        product.setImage(dto.getImage());
        product.setRatings(dto.getRatings());
        product.setNoOfRatings(dto.getNoOfRatings());
        product.setActualPrice(dto.getActualPrice());

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
