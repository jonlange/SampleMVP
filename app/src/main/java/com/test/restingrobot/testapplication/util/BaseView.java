package com.test.restingrobot.testapplication.util;

import com.test.restingrobot.testapplication.userdetails.UserDetailPresenter;
import com.test.restingrobot.testapplication.users.UsersPresenter;

/**
 * Created by Jon Lange, 6/11/18
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(UsersPresenter presenter);

    // HACK
    void setDetailPresenter(UserDetailPresenter presenter);
}
