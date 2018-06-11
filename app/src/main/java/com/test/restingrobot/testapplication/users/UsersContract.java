package com.test.restingrobot.testapplication.users;

import android.support.annotation.NonNull;

import com.test.restingrobot.testapplication.data.User;
import com.test.restingrobot.testapplication.util.BasePresenter;
import com.test.restingrobot.testapplication.util.BaseView;

import java.util.List;

/**
 * Created by Jon Lange, 6/11/18
 *
 * This specifies the contract between the view and the presenter.
 */
public interface UsersContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showUsers(List<User> users);

        void showUserDetailsUi(String userId);

        void showLoadingUsersError();

        void showNoUsers();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadUsers(boolean forceUpdate);

        void openUserDetails(@NonNull User requestedUser);
    }

}
