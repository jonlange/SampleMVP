package com.test.restingrobot.testapplication.userdetails;

import android.support.annotation.NonNull;

import com.test.restingrobot.testapplication.data.Post;
import com.test.restingrobot.testapplication.data.User;
import com.test.restingrobot.testapplication.users.UsersContract;
import com.test.restingrobot.testapplication.util.BasePresenter;
import com.test.restingrobot.testapplication.util.BaseView;

import java.util.List;

/**
 * Created by Jon Lange, 6/11/18
 */

public interface UserDetailContract {

    interface View extends BaseView<UsersContract.Presenter> {

        void setLoadingIndicator(boolean active);

        void showUserPosts(List<Post> posts);

        void showLoadingPostsError();

        void showNoPosts();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadPosts(boolean forceUpdate);

        void addNewPost();

        void editPost(@NonNull Post requestedPost);

        void deletePost(@NonNull Post completedTask);

        void setFiltering(PostsFilterType requestType);

        PostsFilterType getFiltering();

    }

}
