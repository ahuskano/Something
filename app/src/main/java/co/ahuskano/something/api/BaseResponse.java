package co.ahuskano.something.api;

import java.util.List;

import co.ahuskano.something.models.RestaurantDBModel;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class BaseResponse {

    private List<RestaurantDBModel> restaurants;

    public List<RestaurantDBModel> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantDBModel> restaurants) {
        this.restaurants = restaurants;
    }
}
