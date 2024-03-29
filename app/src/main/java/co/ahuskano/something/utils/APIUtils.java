package co.ahuskano.something.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class APIUtils {

    public static final String RETROFIT_DEBUG = "API";

    public static RestAdapter getRestAdapter(String endpoint) {
        return new RestAdapter.Builder().setEndpoint(endpoint).setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
            @Override
            public void log(String message) {
                Log.d(RETROFIT_DEBUG, message);
            }
        }).build();
    }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
