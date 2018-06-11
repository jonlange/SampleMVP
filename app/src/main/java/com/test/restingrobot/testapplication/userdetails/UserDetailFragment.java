package com.test.restingrobot.testapplication.userdetails;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.restingrobot.testapplication.R;
import com.test.restingrobot.testapplication.data.Post;
import com.test.restingrobot.testapplication.users.UsersFragment;
import com.test.restingrobot.testapplication.users.UsersPresenter;

import java.util.List;

/**
 * Created by Jon Lange, 6/11/18
 */

public class UserDetailFragment extends Fragment implements UserDetailContract.View {

    private UserDetailContract.Presenter mPresenter;

    private UserDetailFragment.PostsAdapter mListAdapter;

    private LinearLayout mPostsView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserDetailFragment() {
    }

    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }

    @Override
    public void setPresenter(UsersPresenter presenter) {

    }

    @Override
    public void setDetailPresenter(UserDetailPresenter presenter) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showUserPosts(List<Post> posts) {

    }

    @Override
    public void showLoadingPostsError() {

    }

    @Override
    public void showNoPosts() {

    }

    @Override
    public boolean isActive() {
        return false;
    }


    private static class PostsAdapter extends BaseAdapter {

        private List<Post> mPosts;

        public PostsAdapter(List<Post> posts) {
            setList(posts);
        }

        public void replaceData(List<Post> posts) {
            setList(posts);
            notifyDataSetChanged();
        }

        private void setList(List<Post> posts) {
            mPosts = posts;
        }

        @Override
        public int getCount() {
            return mPosts.size();
        }

        @Override
        public Post getItem(int i) {
            return mPosts.get(i);
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

            final Post post = getItem(i);

            TextView title = (TextView) rowView.findViewById(R.id.userName);
            title.setText(post.getTitle());

            TextView body = (TextView) rowView.findViewById(R.id.email);
            body.setText(post.getBody());

            return rowView;
        }
    }

}
