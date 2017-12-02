package kirandroid.yipl_android_list_me;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("/posts")
    Call<List<post>> getAllPosts();

    @GET("/posts/{id}")
    Call<List<post>> getPostById(@Path("id") int id);

    @GET("/posts/{id}/comments")
    Call<List<post>> getComments(@Path("id") int id);

    @GET("/posts?userId={id}")
    Call<List<post>> getPostofUser(@Path("id") int id);

}
