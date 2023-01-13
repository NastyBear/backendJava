package lesson5;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.annotation.processing.Generated;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @With

    @JsonPropertyOrder({
            "id",
            "title",
            "price",
            "categoryTitle"
    })
    @Generated("jsonschema2pojo")
    public class Product {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("price")
        private Integer price;
        @JsonProperty("categoryTitle")
        private String categoryTitle;
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

        @JsonProperty("title")
        public String getTitle(String milk) {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("price")
        public Integer getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(Integer price) {
            this.price = price;
        }

        @JsonProperty("categoryTitle")
        public String getCategoryTitle() {
            return categoryTitle;
        }

        @JsonProperty("categoryTitle")
        public void setCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
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
