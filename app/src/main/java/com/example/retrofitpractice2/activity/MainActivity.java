package com.example.retrofitpractice2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofitpractice2.model.Comment;
import com.example.retrofitpractice2.JsonPlaceHolderApi;
import com.example.retrofitpractice2.model.Post;
import com.example.retrofitpractice2.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView postTextView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postTextView = findViewById(R.id.postTextView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPosts();

        //getComments();
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    postTextView.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for(Comment comment : comments){
                    String content = "";
                    content += "PostId: " + comment.getPostId() + "\n";
                    content += "Id: " + comment.getId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Body; " + comment.getText() + "\n\n";

                    postTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                postTextView.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new int[]{1,2,3},"id","desc");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    postTextView.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserId: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    postTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postTextView.setText(t.getMessage());
            }
        });
    }
}