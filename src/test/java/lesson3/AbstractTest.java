package lesson3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public abstract class AbstractTest {

    static Properties properties = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String baseUrlPost;

    @BeforeAll
    static void initTest()throws IOException{
        configFile = new FileInputStream("src/main/resources/my.prop");
        properties.load(configFile);

        apiKey = properties.getProperty("apiKey");
        baseUrl = properties.getProperty("base_url_get");
        baseUrlPost = properties.getProperty("base_url_post");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getBaseUrlPost() {
        return baseUrlPost;
    }
}

