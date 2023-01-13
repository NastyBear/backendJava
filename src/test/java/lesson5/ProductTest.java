package lesson5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ProductTest {

    static ProductService productService;

    Product product;
    Product product2;
    String result;
    Faker faker = new Faker();
    int id;

    @BeforeEach
    void pr22() throws IOException {
         product2 = new Product()
              //   .withId(3)
                .withTitle("Cheese")
                .withCategoryTitle("Food")
                .withPrice(100);
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer,product2);
        result = writer.toString();
    }


    @BeforeAll

    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach

    void setUp(){
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int)(Math.random()*10000));
    }

    @Test

    void getProductTest () throws IOException {
        Response<Product> response = productService.getProductById(1).execute();
       assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo("Bread"));
    }

    @Test

    void getProductTest2() throws IOException {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @Test
    void createProductTest() throws IOException {

        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
    }

    @Test
    void createProductTest2() throws IOException {

        Response<Product> response = productService.createProduct(product2).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
    }

//    @Test
//     void modifyProductTest () throws IOException {
//        Response<Product>response = productService.modifyProduct(result).execute();
//        assertThat(response.body().getTitle(), equalTo("Cheese"));
//    }

    @SneakyThrows
   //@AfterEach
    //@Test
    void tearDown(){
        Response<ResponseBody>response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
    }
}
