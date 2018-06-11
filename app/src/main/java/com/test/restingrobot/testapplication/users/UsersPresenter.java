package com.test.restingrobot.testapplication.users;

import android.support.annotation.NonNull;
import android.util.Log;

import com.test.restingrobot.testapplication.data.User;
import com.test.restingrobot.testapplication.data.source.UserDataSource;
import com.test.restingrobot.testapplication.data.source.remote.UserRemoteDataSource;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Jon Lange, 6/11/18
 *
 * Listens to user actions from the UI ({@link UsersFragment}), retrieves the data and updates the
 * UI as required.
 */

public class UsersPresenter implements UsersContract.Presenter {

    private static final String TAG = "UsersPresenter";

    private final UsersContract.View mUsersView;
    private final UserRemoteDataSource mUserDS;

    private boolean mFirstLoad = true;

    public UsersPresenter(@NonNull UsersContract.View usersView, @NonNull UserRemoteDataSource userDS) {

        mUsersView = usersView;
        mUserDS = userDS;

        mUsersView.setPresenter(this);
    }

    @Override
    public void start() {
        loadUsers(true);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadUsers(boolean forceUpdate) {
        loadUsers(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    @Override
    public void openUserDetails(@NonNull User requestedUser) {
        mUsersView.showUserDetailsUi(requestedUser.getId());
    }


    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link com.test.restingrobot.testapplication.data.source.UserDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadUsers(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mUsersView.setLoadingIndicator(true);
        }

        // Load Users on application start
        mUserDS.loadUsers(new UserDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                Log.d(TAG, "Users Loaded " + users.size());

                // The view may not be able to handle UI updates anymore
                if (!mUsersView.isActive()) {
                    return;
                }

                if (showLoadingUI) {
                    mUsersView.setLoadingIndicator(false);
                }

                processUsers(users);
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.d(TAG, "Users not Loaded " + message);

                // The view may not be able to handle UI updates anymore
                if (!mUsersView.isActive()) {
                    return;
                }

                mUsersView.showLoadingUsersError();

            }
        });
    }

    private void processUsers(List<User> users) {
        if (users.isEmpty()) {
            // Show a message indicating there are no users for that filter type.
            processEmptyUsers();
        } else {
            mUsersView.showUsers(users);
        }
    }

    private void processEmptyUsers() {
        mUsersView.showNoUsers();
    }

}
