package com.test.restingrobot.testapplication.userdetails;

import android.support.annotation.NonNull;
import android.util.Log;

import com.test.restingrobot.testapplication.data.Post;
import com.test.restingrobot.testapplication.data.User;
import com.test.restingrobot.testapplication.data.source.UserDataSource;
import com.test.restingrobot.testapplication.data.source.remote.UserRemoteDataSource;

import java.util.List;

/**
 * Created by Jon Lange, 6/11/18
 */

public class UserDetailPresenter implements  UserDetailContract.Presenter {


    private static final String TAG = "UserDetailPresenter";

    private final UserDetailContract.View mPostsView;
    private final UserRemoteDataSource mUserDS;

    private final String mUserId;

    private boolean mFirstLoad = true;

    public UserDetailPresenter(@NonNull UserDetailContract.View postsView,
                               @NonNull UserRemoteDataSource userDS,
                                @NonNull String userId) {

        mPostsView = postsView;
        mUserDS = userDS;
        mUserId = userId;

        mPostsView.setDetailPresenter(this);
    }

    @Override
    public void start() {
        loadPosts(true);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadPosts(boolean forceUpdate) {
        loadPosts(forceUpdate || mFirstLoad, true);
    }

    @Override
    public void addNewPost() {

    }

    @Override
    public void editPost(@NonNull Post requestedPost) {

    }

    @Override
    public void deletePost(@NonNull Post completedTask) {

    }

    @Override
    public void setFiltering(PostsFilterType requestType) {

    }

    @Override
    public PostsFilterType getFiltering() {
        return null;
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link com.test.restingrobot.testapplication.data.source.UserDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadPosts(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mPostsView.setLoadingIndicator(true);
        }

        // Load Users on application start
        mUserDS.loadPosts(mUserId, new UserDataSource.LoadPostsCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                Log.d(TAG, "Posts Loaded " + posts.size());

                // The view may not be able to handle UI updates anymore
                if (!mPostsView.isActive()) {
                    return;
                }

                if (showLoadingUI) {
                    mPostsView.setLoadingIndicator(false);
                }

                processPosts(posts);
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.d(TAG, "Users not Loaded " + message);

                // The view may not be able to handle UI updates anymore
                if (!mPostsView.isActive()) {
                    return;
                }

                //mPostsView.showLoadingUsersError();

            }
        });
    }

    private void processPosts(List<Post> posts) {
        if (posts.isEmpty()) {
            // Show a message indicating there are no users for that filter type.

        } else {
            mPostsView.showUserPosts(posts);
        }
    }

}
