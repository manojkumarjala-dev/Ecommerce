package project.ecommerce.catalogue_management_service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import project.ecommerce.catalogue_management_service.model.ProductEDB;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductEDB, Long> {
}

