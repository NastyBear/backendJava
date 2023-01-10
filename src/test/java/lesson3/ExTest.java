package lesson3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lesson4.AddMealResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExTest extends AbstractTest{
    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeEach
    void beforeTest(){
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }
    @BeforeEach
    void beforeTestHead(){
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey",getApiKey())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void getExampleTest(){
        given()
                .spec(requestSpecification)
                .queryParam("query","cake")
                .queryParam("cuisine","indian")
                .queryParam("diet","vegan")
                .queryParam("maxReadyTime","60")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .body("number",equalTo(10))
                .spec(responseSpecification);

    }

    @Test
    void getExampleTest2(){
        given()
                .spec(requestSpecification)
                .queryParam("query","cake")
                .queryParam("cuisine","indian")
                .queryParam("diet","vegan")
                .queryParam("maxReadyTime","60")
                .queryParam("number","1")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .body("number",equalTo(1))
                .spec(responseSpecification);

    }
    @Test
    void getExampleTest3(){
        given()
                .spec(requestSpecification)
                .queryParam("query","cake")
                .queryParam("cuisine","indian")
                .queryParam("diet","vegan")
                .queryParam("maxReadyTime","60")
                .queryParam("number","0")
                .when()
                .get(getBaseUrl())
                .prettyPeek()
                .then()
                .assertThat()
                .body("number",equalTo(1))
                .spec(responseSpecification);

    }
    @Test
    void getExampleTest4(){
        given()
                .spec(requestSpecification)
                .formParam("query","сок")
                .formParam("cuisine","indian")
                .formParam("diet","vegan")
                .formParam("maxReadyTime","60")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .body("offset",equalTo(0));

    }
    @Test
    void getExampleTest5(){
        given()
                .when()
                .get(getBaseUrl())
                .then()
                .statusCode(401);

    }

    @Test
    void postExampleTest(){
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .body(equalTo("{\"cuisine\":\"Mediterranean\",\"cuisines\":[\"Mediterranean\",\"European\",\"Italian\"],\"confidence\":0.0}"));
    }
    @Test
    void postExampleTest2(){
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .body("cuisine",equalTo("Mediterranean"));
    }
    @Test
    void postExampleTest3(){
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Finnish Whipped Porridge with Yogurt Cream (Vispipuuro)")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .body("cuisine",equalTo("Scandinavian"));
    }
    @Test
    void postExampleTest4(){
        given()
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .statusCode(401);

    }
    @Test
    void postExampleTest5(){
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Finnish Whipped Porridge with Yogurt Cream (Vispipuuro)")
                .formParam("ingredientList","tortilla")
                .formParam("language","en")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .body("cuisine",equalTo("Scandinavian"));
    }


    @Test
    void getAccountInfoWithExternalEndpointTest(){
        AddMealResponse response = given()
                .spec(requestSpecification)
                .queryParam("query","cake")
                .queryParam("cuisine","indian")
                .queryParam("diet","vegan")
                .queryParam("maxReadyTime","60")
                .queryParam("number","0")
                .when()
                .get(getBaseUrl())
                .prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(AddMealResponse.class);
        assertThat(response.getOffset(),equalTo(0));

    }

    @Test
    void addMealTest1() {
        given()
                .queryParam("hash","98f8e16cbbd825c975c630b2c334c2a992060b92")
                .queryParam("apiKey",getApiKey())
                .when()
                .get("https://api.spoonacular.com/mealplanner/your-users-name640/shopping-list")
                .then()
                .statusCode(200);
        String id = given()
                .queryParam("hash", "98f8e16cbbd825c975c630b2c334c2a992060b92")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name640/items")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "98f8e16cbbd825c975c630b2c334c2a992060b92")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/your-users-name640/items/" + id)
                .then()
                .statusCode(200);
    }
}
