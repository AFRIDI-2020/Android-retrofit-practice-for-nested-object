package com.example.retrofitpractice2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.retrofitpractice2.ApiClient;
import com.example.retrofitpractice2.ApiInterface;
import com.example.retrofitpractice2.DataAdapter;
import com.example.retrofitpractice2.R;
import com.example.retrofitpractice2.model.Contact;
import com.example.retrofitpractice2.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Acvitivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acvitivity2);

        recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
    }

    private void loadData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getMovies().enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    List<Contact> contacts = response.body().getContacts();

                    for (int i = 0; i < contacts.size(); i++) {
                        dataAdapter = new DataAdapter((ArrayList<Contact>) contacts);
                        recyclerView.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}