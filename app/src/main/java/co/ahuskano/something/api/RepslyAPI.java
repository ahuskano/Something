package co.ahuskano.something.api;

import co.ahuskano.something.models.RestaurantDBModel;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by ahuskano on 11.7.2015..
 */
public interface RepslyAPI {

    public static String RESTORANS_METHOD="/54ef80f5a11ac4d607752717";

    public static String API_PREFIX="v2/";
    public static String API_LOCATION="http://www.mocky.io/"+API_PREFIX;

    @GET(RESTORANS_METHOD)
    void getRestorans(Callback<RestaurantAPIModel[]> callback);

}
