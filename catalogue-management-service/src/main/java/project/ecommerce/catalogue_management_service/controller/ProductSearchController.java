package project.ecommerce.catalogue_management_service.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ecommerce.catalogue_management_service.model.ProductEDB;
import project.ecommerce.catalogue_management_service.service.ProductSearchServiceImpl;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchServiceImpl productSearchService;

    @GetMapping
    public ResponseEntity<List<ProductEDB>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String mainCategory,
            @RequestParam(required = false) String subCategory,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws IOException {
        List<ProductEDB> results = productSearchService.search(
                keyword, mainCategory, subCategory, minPrice, maxPrice, page, size
        );
        return ResponseEntity.ok(results);
    }
}
