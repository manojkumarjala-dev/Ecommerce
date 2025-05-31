package project.ecommerce.catalogue_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Document(indexName = "products")
public class ProductEDB {

    @Id
    private Long sku;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @JsonProperty("main_category")
    @Field(name = "main_category", type = FieldType.Keyword)
    private String mainCategory;

    @JsonProperty("sub_category")
    @Field(name = "sub_category", type = FieldType.Keyword)
    private String subCategory;

    @JsonProperty("actual_price")
    @Field(name = "actual_price", type = FieldType.Double)
    private double actualPrice;

    private float ratings;

    @JsonProperty("no_of_ratings")
    @Field(name = "no_of_ratings", type = FieldType.Long)
    private long noOfRatings;

    private String image;
}
