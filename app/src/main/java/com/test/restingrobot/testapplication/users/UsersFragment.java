package com.test.restingrobot.testapplication.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.test.restingrobot.testapplication.R;
import com.test.restingrobot.testapplication.data.User;
import com.test.restingrobot.testapplication.userdetails.UserDetailActivity;
import com.test.restingrobot.testapplication.userdetails.UserDetailPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Users.
 */
public class UsersFragment extends Fragment implements UsersContract.View {

    private UsersContract.Presenter mPresenter;

    private UsersAdapter mListAdapter;

    private LinearLayout mUsersView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersFragment() {
    }

    public static UsersFragment newInstance() {
       return new UsersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new UsersAdapter(new ArrayList<User>(0), mItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull UsersPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setDetailPresenter(UserDetailPresenter presenter) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.users_fragment, container, false);

        mUsersView = (LinearLayout) root.findViewById(R.id.usersLL);

        // Set up users view
        ListView listView = (ListView) root.findViewById(R.id.users_list);
        listView.setAdapter(mListAdapter);

        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }

        // TODO: Add loading indicator

    }

    @Override
    public void showUsers(List<User> users) {
        mListAdapter.replaceData(users);

        mUsersView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserDetailsUi(String userId) {
        Intent intent = new Intent(getContext(), UserDetailActivity.class);
        intent.putExtra(UserDetailActivity.EXTRA_USER_ID, userId);
        startActivity(intent);
    }

    @Override
    public void showLoadingUsersError() {
        showMessage("Loading Users Error");
    }

    @Override
    public void showNoUsers() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    /**
     * Listener for clicks on user in the ListView.
     */
    UserItemListener mItemListener = new UserItemListener() {

        @Override
        public void onUserClick(User clickedUser) {
            mPresenter.openUserDetails(clickedUser);
        }
    };


    private static class UsersAdapter extends BaseAdapter {

        private List<User> mUsers;
        private UserItemListener mItemListener;

        public UsersAdapter(List<User> users, UserItemListener itemListener) {
            setList(users);
            mItemListener = itemListener;
        }

        public void replaceData(List<User> users) {
            setList(users);
            notifyDataSetChanged();
        }

        private void setList(List<User> users) {
            mUsers = users;
        }

        @Override
        public int getCount() {
            return mUsers.size();
        }

        @Override
        public User getItem(int i) {
            return mUsers.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.user_item, viewGroup, false);
            }

            final User user = getItem(i);

            TextView userNameTV = (TextView) rowView.findViewById(R.id.userName);
            userNameTV.setText(user.getUserName());

            TextView emailTV = (TextView) rowView.findViewById(R.id.email);
            emailTV.setText(user.getEmail());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onUserClick(user);
                }
            });

            return rowView;
        }
    }

    public interface UserItemListener {
        void onUserClick(User clickedUser);
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

}
