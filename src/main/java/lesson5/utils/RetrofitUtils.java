package lesson5.utils;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class RetrofitUtils {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Properties prop = new Properties();
    private static InputStream configFile;

    static {
        try{
            configFile = new FileInputStream("src/main/resources/my.prop");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public String getBaseUrl() throws IOException {
        prop.load(configFile);
        return prop.getProperty("baseUrl");
    }
    public Retrofit getRetrofit() throws IOException {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
