package com.example.retrofitpractice2;

import com.example.retrofitpractice2.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("contacts/")
    Call <Movie> getMovies();
}
