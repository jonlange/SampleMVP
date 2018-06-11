package com.test.restingrobot.testapplication.users;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.test.restingrobot.testapplication.R;
import com.test.restingrobot.testapplication.application.TestApplication;
import com.test.restingrobot.testapplication.data.source.remote.UserRemoteDataSource;
import com.test.restingrobot.testapplication.util.ActivityUtils;


public class UsersActivity extends AppCompatActivity {

    private static String TAG = "UsersActivity";

    private UserRemoteDataSource mUserDS;
    private UsersPresenter mUsersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUserDS = UserRemoteDataSource.getInstance(TestApplication.getContext());

        UsersFragment usersFragment =
                (UsersFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (usersFragment == null) {
            // Create the fragment
            usersFragment = UsersFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), usersFragment, R.id.contentFrame);
        }

        // Create the presenter
        mUsersPresenter = new UsersPresenter(usersFragment, mUserDS);

        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            // TODO: Handle this case
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
