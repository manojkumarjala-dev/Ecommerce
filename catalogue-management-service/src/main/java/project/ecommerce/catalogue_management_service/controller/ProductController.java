package project.ecommerce.catalogue_management_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "false") boolean sortByPrice
    ) {
        Pageable pageable = sortByPrice
                ? PageRequest.of(page, size, Sort.by("actualPrice").ascending())
                : PageRequest.of(page, size);

        List<ProductResponseDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-price")
    public ResponseEntity<List<ProductResponseDTO>> getProductsInPriceRange(
            @RequestParam int minPrice,
            @RequestParam int maxPrice,
            @RequestParam(defaultValue = "false") boolean sortByPriceDesc,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = sortByPriceDesc
                ? PageRequest.of(page, size, Sort.by("actualPrice").descending())
                : PageRequest.of(page, size, Sort.by("actualPrice").ascending());
        List<ProductResponseDTO> products = productService.getProductsInPriceRange(pageable, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.getProductById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productDto) {
        ProductResponseDTO productResponseDTO = productService.createProduct(productDto);
        return ResponseEntity.ok(productResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
