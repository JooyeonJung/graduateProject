package org.techtown.songthemarket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {
    @POST("/categorys/")
    Call<Category> post_category(@Body Category category);

    @GET("/categorys")
    Call<List<Category>> get_category();

    @GET("/categorys/{pk}/")
    Call<Category> get_pk_category(@Path("pk") int pk);

}
