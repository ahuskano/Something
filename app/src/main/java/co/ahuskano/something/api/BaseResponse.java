package co.ahuskano.something.api;

import com.google.gson.annotations.Expose;

import co.ahuskano.something.models.RestaurantDBModel;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class BaseResponse {

    @Expose
    private RestaurantDBModel[] restaurants;

    public RestaurantDBModel[] getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantDBModel[] restaurants) {
        this.restaurants = restaurants;
    }
}
