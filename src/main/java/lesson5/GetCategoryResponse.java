package lesson5;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "products",
            "title"
    })
    @Generated("jsonschema2pojo")
    public class GetCategoryResponse {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("products")
        private List<Product> products = null;
        @JsonProperty("title")
        private String title;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("products")
        public List<Product> getProducts() {
            return products;
        }

        @JsonProperty("products")
        public void setProducts(List<Product> products) {
            this.products = products;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }


