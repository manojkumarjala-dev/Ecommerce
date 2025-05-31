package project.ecommerce.catalogue_management_service.service;


import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import project.ecommerce.catalogue_management_service.model.ProductEDB;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl {

    private final ElasticsearchClient elasticsearchClient;

    public List<ProductEDB> search(String keyword, String mainCategory, String subCategory, Double minPrice, Double maxPrice, int page, int size) throws IOException {
        try {
            // Compose the main bool query
            Query finalQuery = Query.of(q -> q
                    .bool(b -> {
                        b.must(m -> m
                                .multiMatch(mm -> mm
                                        .fields("name", "main_category", "sub_category")
                                        .query(keyword)
                                        .fuzziness("AUTO")
                                )
                        );

                        if (mainCategory != null && !mainCategory.isBlank()) {
                            b.filter(f -> f.term(t -> t.field("main_category").value(mainCategory)));
                        }

                        if (subCategory != null && !subCategory.isBlank()) {
                            b.filter(f -> f.term(t -> t.field("sub_category").value(subCategory)));
                        }

                        if (minPrice != null || maxPrice != null) {
                            b.filter(f -> f.range(r -> r
                                    .field("actual_price")
                                    .gte(minPrice != null ? JsonData.of(minPrice) : null)
                                    .lte(maxPrice != null ? JsonData.of(maxPrice) : null)
                            ));
                        }

                        return b;
                    })
            );

            SearchResponse<ProductEDB> response = elasticsearchClient.search(s -> s
                            .index("products")
                            .from(page * size)
                            .size(size)
                            .query(finalQuery),
                    ProductEDB.class
            );

            return response.hits().hits().stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Elasticsearch search error: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }}
