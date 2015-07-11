package co.ahuskano.something.api;

import com.google.gson.annotations.Expose;

import co.ahuskano.something.models.RestaurantDBModel;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class RestaurantAPIModel {

    @Expose
    private String Name;

    @Expose
    private String Address;

    @Expose
    private String Longitude;

    @Expose
    private String Latitude;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public static RestaurantDBModel convertToDb(RestaurantAPIModel restaurantAPIModel) {
        RestaurantDBModel restoran = new RestaurantDBModel();
        restoran.setName(restaurantAPIModel.getName());
        restoran.setAddress(restaurantAPIModel.getAddress());
        restoran.setLatitude(restaurantAPIModel.getLatitude());
        restoran.setLongitude(restaurantAPIModel.getLongitude());
        return restoran;
    }
}
