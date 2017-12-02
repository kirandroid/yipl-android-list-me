package kirandroid.yipl_android_list_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private TextView postId, postTitle, postText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        APIService apiService = retrofit.create(APIService.class);

        getAllPosts(apiService);
        post newPost = new post();
        newPost.setId(100);
        newPost.setUserId(200);
        newPost.setTitle("Sample title");
        newPost.setBody("Datas");/*
        createPost(apiService, newPost);*/
    }



    private void getAllPosts(APIService apiService){
        Call<List<post>> getAllPostsCall = apiService.getAllPosts();

        getAllPostsCall.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {
                displayPost(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error while fetching posts", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });
    }

    private void initViews(){
        postId = (TextView) findViewById(R.id.postId);
        postTitle = (TextView) findViewById(R.id.postTitle);
        postText = (TextView) findViewById(R.id.postText);
    }

    private void displayPost(post post){
        postId.setText(post.getId().toString());
        postTitle.setText(post.getTitle());
        postText.setText(post.getBody());
    }
}
