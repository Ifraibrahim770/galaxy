package com.ibrahim.galaxy.http;

import com.google.gson.JsonObject;
import com.ibrahim.galaxy.model.GalaxyImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("search")
    Call<GalaxyImage> getGalaxyImageInfo(
            @Query("q")  String query,
            @Query("media_type") String media,
            @Query("year_start") String startYear,
            @Query("year_end") String endYear
    );
}


