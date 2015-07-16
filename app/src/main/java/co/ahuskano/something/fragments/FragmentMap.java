package co.ahuskano.something.fragments;

import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import co.ahuskano.something.R;
import co.ahuskano.something.constants.AppConstants;
import co.ahuskano.something.models.RestaurantDBModel;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by ahuskano on 15.7.2015..
 */
public class FragmentMap extends BaseFragment implements OnMapReadyCallback {

    @Override
    public void initViews(View view) {
        showDialog();
        SupportMapFragment mapFragment = new SupportMapFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public String getName() {
        return FragmentMap.class.getSimpleName();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (getActivity().getIntent().getLongExtra(AppConstants.KEY_RESTAURANT_ID, 0) != 0) {
            String name = getActivity().getIntent().getStringExtra(AppConstants.KEY_RESTAURANT_NAME);
            Double lng = getActivity().getIntent().getDoubleExtra(AppConstants.KEY_RESTAURANT_LNG, 0);
            Double lat = getActivity().getIntent().getDoubleExtra(AppConstants.KEY_RESTAURANT_LAT, 0);
            googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                    .title(name));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 10));
            dismissDialog();

        } else {
            for (RestaurantDBModel restaurantDBModel : RestaurantDBModel.getRestorans()) {
                googleMap.addMarker(
                        new MarkerOptions().position(
                                new LatLng(
                                        Double.valueOf(restaurantDBModel.getLatitude()),
                                        Double.valueOf(restaurantDBModel.getLongitude())))
                                .title(restaurantDBModel.getName()));
            }
            SmartLocation.with(getActivity()).location()
                    .oneFix()
                    .start(new OnLocationUpdatedListener() {
                        @Override
                        public void onLocationUpdated(Location location) {
                            if (location != null) {
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
                                dismissDialog();
                            }
                        }
                    });
        }

    }
}
