package project.ecommerce.catalogue_management_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.ecommerce.catalogue_management_service.dto.ProductRequestDTO;
import project.ecommerce.catalogue_management_service.dto.ProductResponseDTO;
import project.ecommerce.catalogue_management_service.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO productDto) {
        return productService.createProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
