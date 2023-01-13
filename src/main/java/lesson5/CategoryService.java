package lesson5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
