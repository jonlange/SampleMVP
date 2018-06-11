package com.test.restingrobot.testapplication.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.restingrobot.testapplication.data.Post;
import com.test.restingrobot.testapplication.data.User;
import com.test.restingrobot.testapplication.data.source.UserDataSource;
import com.test.restingrobot.testapplication.net.APIController;
import com.test.restingrobot.testapplication.net.TestAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jon Lange, 6/11/18
 */

public class UserRemoteDataSource implements UserDataSource {

    private static final String TAG = "UserRemoteDataSource";

    private static UserRemoteDataSource mInstance;

    private UserRemoteDataSource() { }

    public static UserRemoteDataSource getInstance(@NonNull Context ctx) {
        if (mInstance == null) {
            mInstance = new UserRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public void loadUsers(@NonNull final LoadUsersCallback callback) {
        Log.d(TAG, "Loading User List");

        TestAPI apiService = APIController.getInstance();

        Call<List<User>> call = apiService.getUsers();

        Callback<List<User>> cb = new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                Log.d(TAG,"Received " + users.size() + " users.");
                callback.onUsersLoaded(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "Error loading users " + t.getMessage());
                callback.onDataNotAvailable(t.getMessage());
            }
        };

        APIController.doAsyncApiCall(call, cb);

    }

    @Override
    public void loadPosts(@NonNull final String user_id, @NonNull final LoadPostsCallback callback) {
        Log.d(TAG, "Loading User List");

        TestAPI apiService = APIController.getInstance();

        Call<List<Post>> call = apiService.getPosts(user_id);

        Callback<List<Post>> cb = new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                Log.d(TAG,"Received " + posts.size() + " posts.");
                callback.onPostsLoaded(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "Error loading users " + t.getMessage());
                callback.onDataNotAvailable(t.getMessage());
            }
        };

        APIController.doAsyncApiCall(call, cb);

    }


}
