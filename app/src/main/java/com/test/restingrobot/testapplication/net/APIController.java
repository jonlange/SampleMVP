package com.test.restingrobot.testapplication.net;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.test.restingrobot.testapplication.util.ConfigHelper;
import com.test.restingrobot.testapplication.util.NetUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jon Lange, 6/11/18
 */

public class APIController {

    public static String TAG = "APIController";

    private static TestAPI mAPI = null;

    public static TestAPI getInstance() {
        if (mAPI == null) {

            OkHttpClient okclient;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            okclient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();


            final String baseUrl = ConfigHelper.BASE_URL;

            final Retrofit retrofit = new Retrofit.Builder()
                    .client(okclient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mAPI = retrofit.create(TestAPI.class);
        }

        return mAPI;
    }

    /**
     * Helper class that handles common errors across all API calls.
     * Uses the Retrofit2 Queue to run asynchronously.
     *
     * @param base_call - Retrofit2 Call to execute.
     * @param callback - Retrofit2 Callback to execute.
     */
    @SuppressWarnings("unchecked")
    public static void doAsyncApiCall(Call base_call, final Callback callback) {

        // Last second check for connectivity just in case
        if(NetUtil.isConnected()) {

            base_call.enqueue(new Callback() {

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    int statusCode = response.code();

                    if (!response.isSuccessful()) {
                        Log.d(TAG, "ERROR returned from API Call: " + statusCode);

                        String errorMessage = "";
                        try {
                            String json = response.errorBody().string();

                            // TODO: Add custom Error Handling

                            if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST ||
                                        statusCode == HttpURLConnection.HTTP_FORBIDDEN ||
                                        statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                                    errorMessage = "Unable to communicate with server. Please try again.";
                            }

                        } catch (IOException e) {
                            Log.e(TAG, "Exception getting API error" + e.getMessage());
                            e.printStackTrace();
                        } catch (JsonSyntaxException e) {
                            Log.e(TAG, "Exception parsing JSON API error" + e.getMessage());
                            e.printStackTrace();
                        }

                        //Calls the onFailure callback to handle errors.
                        callback.onFailure(call, new Throwable(errorMessage));
                        return;

                    }

                    callback.onResponse(call, response);

                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                    Log.d(TAG, "API call FAILURE", t);
                    callback.onFailure(call, t);
                }
            });
        }
        else {
            // Show connection Dialog.
            callback.onFailure(base_call, new Throwable(NetUtil.NO_NETWORK_ERROR));
        }
    }
}
