package com.test.restingrobot.testapplication.data.source.local;

import android.support.annotation.NonNull;

import com.test.restingrobot.testapplication.data.source.UserDataSource;

/**
 * Created by Jon Lange, 6/11/18
 */

public class UserLocalDataSource implements UserDataSource{

    // TODO: Add local caching
    @Override
    public void loadUsers(@NonNull LoadUsersCallback callback) {

    }
    @Override
    public void loadPosts(@NonNull String userId, @NonNull LoadPostsCallback callback) {

    }

}
