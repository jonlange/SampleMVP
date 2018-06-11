package com.test.restingrobot.testapplication.userdetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.test.restingrobot.testapplication.R;

/**
 * Created by Jon Lange, 6/11/18
 */

public class UserDetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_detail_activity);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested user id
        String userId = getIntent().getStringExtra(EXTRA_USER_ID);

//        UserDetalFragment taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.contentFrame);
//
//        if (taskDetailFragment == null) {
//            taskDetailFragment = TaskDetailFragment.newInstance(taskId);
//
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
//                    taskDetailFragment, R.id.contentFrame);
//        }
//
//        // Create the presenter
//        new TaskDetailPresenter(
//                Injection.provideUseCaseHandler(),
//                taskId,
//                taskDetailFragment,
//                Injection.provideGetTask(getApplicationContext()),
//                Injection.provideCompleteTasks(getApplicationContext()),
//                Injection.provideActivateTask(getApplicationContext()),
//                Injection.provideDeleteTask(getApplicationContext()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
