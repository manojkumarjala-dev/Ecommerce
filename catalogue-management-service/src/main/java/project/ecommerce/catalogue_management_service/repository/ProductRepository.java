package project.ecommerce.catalogue_management_service.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import project.ecommerce.catalogue_management_service.model.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query to find products by price range
    @Query("SELECT p FROM Product p WHERE p.actualPrice BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceRange(@Param("minPrice") int minPrice,
                                   @Param("maxPrice") int maxPrice,
                                   Pageable pageable);

}

