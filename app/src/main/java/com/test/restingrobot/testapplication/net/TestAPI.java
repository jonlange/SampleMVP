package com.test.restingrobot.testapplication.net;

import com.test.restingrobot.testapplication.data.Post;
import com.test.restingrobot.testapplication.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jon Lange, 6/11/18
 */

public interface TestAPI {

    /**
     * [
        {
            "id": 1,
            "name": "Leanne Graham",
            "username": "Bret",
            "email": "Sincere@april.biz",
            "address": {
                "street": "Kulas Light",
                "suite": "Apt. 556",
                "city": "Gwenborough",
                "zipcode": "92998-3874",
                "geo": {
                    "lat": "-37.3159",
                    "lng": "81.1496"
                }
            },
            "phone": "1-770-736-8031 x56442",
            "website": "hildegard.org",
            "company": {
                "name": "Romaguera-Crona",
                "catchPhrase": "Multi-layered client-server neural-net",
                "bs": "harness real-time e-markets"
            }
        }
       ]

     * @return - List of user objects
     */

    @GET("users/")
    Call<List<User>> getUsers();


    /** [
         {
            "userId": 1,
            "id": 1,
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
         }
        ]
     * @return - List of user post objects
     */
    @GET("post/{user_id}")
    Call<List<Post>> getPosts(@Path("user_id") String userId);


}
