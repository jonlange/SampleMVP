package com.test.restingrobot.testapplication.data.source;

import android.support.annotation.NonNull;

import com.test.restingrobot.testapplication.data.Post;
import com.test.restingrobot.testapplication.data.User;

import java.util.List;

/**
 * Created by Jon Lange, 6/11/18
 */

public interface UserDataSource {

    interface LoadUsersCallback {
        void onUsersLoaded(List<User> userList);
        void onDataNotAvailable(String message);
    }

    interface LoadPostsCallback {
        void onPostsLoaded(List<Post> postList);
        void onDataNotAvailable(String message);
    }


    // Load List of Users
    void loadUsers(@NonNull LoadUsersCallback callback);

    // Load a list of Posts
    void loadPosts(@NonNull String userId, @NonNull LoadPostsCallback callback);

}
