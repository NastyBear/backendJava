package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ExTest extends AbstractTest{

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void getExampleTest(){
        given()
                .queryParam("apiKey",getApiKey())
                .queryParam("query","cake")
                .queryParam("cuisine","indian")
                .queryParam("diet","vegan")
                .queryParam("maxReadyTime","60")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .body("number",equalTo(10))
                .statusCode(200);

    }

    @Test
    void getExampleTest2(){
        given()
                .queryParam("apiKey",getApiKey())
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
                .statusCode(200);

    }
    @Test
    void getExampleTest3(){
        given()
                .queryParam("apiKey",getApiKey())
                .queryParam("query","cake")
                .queryParam("cuisine","indian")
                .queryParam("diet","vegan")
                .queryParam("maxReadyTime","60")
                .queryParam("number","0")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .body("number",equalTo(1))
                .statusCode(200);

    }
    @Test
    void getExampleTest4(){
        given()
                .queryParam("apiKey",getApiKey())
                .formParam("query","сок")
                .formParam("cuisine","indian")
                .formParam("diet","vegan")
                .formParam("maxReadyTime","60")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200)
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
                .queryParam("apiKey",getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo("{\"cuisine\":\"Italian\",\"cuisines\":[\"Italian\",\"Mediterranean\",\"European\"],\"confidence\":0.0}"));
    }
    @Test
    void postExampleTest2(){
        given()
                .queryParam("apiKey",getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine",equalTo("Italian"));
    }
    @Test
    void postExampleTest3(){
        given()
                .queryParam("apiKey",getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Finnish Whipped Porridge with Yogurt Cream (Vispipuuro)")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine",equalTo("Nordic"));
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
                .queryParam("apiKey",getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Finnish Whipped Porridge with Yogurt Cream (Vispipuuro)")
                .formParam("ingredientList","tortilla")
                .formParam("language","en")
                .when()
                .post(getBaseUrlPost())
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine",equalTo("Nordic"));
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
