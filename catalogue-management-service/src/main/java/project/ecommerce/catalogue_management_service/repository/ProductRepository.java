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

    @Query("SELECT p FROM Product p " +
            "WHERE (:mainCategory IS NULL OR p.mainCategory = :mainCategory) " +
            "AND (:minPrice IS NULL OR p.actualPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.actualPrice <= :maxPrice)")
    Page<Product> findFilteredProducts(
            @Param("mainCategory") String mainCategory,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            Pageable pageable
    );

    @Query("""
    SELECT p FROM Product p
    WHERE (
        LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.mainCategory) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.subCategory) LIKE LOWER(CONCAT('%', :keyword, '%'))
    )
    AND (:mainCategory IS NULL OR p.mainCategory = :mainCategory)
    AND (:minPrice IS NULL OR p.actualPrice >= :minPrice)
    AND (:maxPrice IS NULL OR p.actualPrice <= :maxPrice)
""")
    Page<Product> searchByKeywordAndFilters(
            @Param("keyword") String keyword,
            @Param("mainCategory") String mainCategory,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            Pageable pageable
    );

}

