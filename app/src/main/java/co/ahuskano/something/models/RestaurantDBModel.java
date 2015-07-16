package co.ahuskano.something.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by ahuskano on 11.7.2015..
 */
@Table(name = "Restaurant")
public class RestaurantDBModel extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name="picture")
    private String picture;

    public RestaurantDBModel() {
    }

    public RestaurantDBModel(String name, String address, String longitude, String latitude) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public RestaurantDBModel(String name, String address, String longitude, String latitude, String picture) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static List<RestaurantDBModel> getRestorans() {
        return new Select().from(RestaurantDBModel.class).execute();
    }

    public static RestaurantDBModel getRestoran(long id) {
        return new Select().from(RestaurantDBModel.class).where("Id=?", id).executeSingle();
    }
}
