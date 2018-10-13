package com.masumdroid.photos.api;


import com.masumdroid.photos.model.Album;
import com.masumdroid.photos.model.User;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("albums")
    Call<List<User>> getUser(@Query("userId") int userId);

    @GET("photos")
    Call<List<Album>> getAlbum(@Query("albumId") int albumId);
}
