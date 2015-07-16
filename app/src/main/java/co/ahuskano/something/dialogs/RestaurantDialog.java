package co.ahuskano.something.dialogs;

import android.app.Activity;
import android.location.Location;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import co.ahuskano.something.R;
import co.ahuskano.something.constants.AppConstants;
import co.ahuskano.something.models.RestaurantDBModel;
import de.psdev.formvalidations.EditTextErrorHandler;
import de.psdev.formvalidations.Field;
import de.psdev.formvalidations.Form;
import de.psdev.formvalidations.validations.NotEmpty;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by ahuskano on 16.7.2015..
 */
public class RestaurantDialog extends BaseDialog {

    private RestaurantDBModel restaurant;
    private AppCompatEditText name, address;

    public RestaurantDialog(Activity activity, int status) {
        super(activity, status);
    }
    public RestaurantDialog(Activity activity, int status, OnDialogSuccess listener) {
        super(activity, status, listener);
    }

    private Location restaurantLocation;

    @Override
    void setDialog() {
        if (activity.getIntent().getLongExtra(AppConstants.KEY_RESTAURANT_ID, 0) != 0)
            restaurant = RestaurantDBModel.getRestoran(activity.getIntent().getLongExtra(AppConstants.KEY_RESTAURANT_ID, 0));

        SmartLocation.with(activity).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        if (location != null) {
                            restaurantLocation = location;
                        }
                    }
                });
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this.activity)
                .customView(R.layout.dialog_restaurant, wrapInScrollView)
                .negativeText(R.string.dialog_negative_button)
                .positiveText(R.string.dialog_positive_button)
                .neutralText(R.string.dialog_neutral_button)
                .callback(new MaterialDialog.ButtonCallback() {
                              @Override
                              public void onPositive(MaterialDialog dialog) {
                                  if (restaurant == null)
                                      restaurant = new RestaurantDBModel();
                                  if (validate()) {
                                      restaurant.setName(name.getText().toString());
                                      restaurant.setAddress(address.getText().toString());
                                      restaurant.save();
                                      dialog.dismiss();
                                      Toast.makeText(activity, R.string.dialog_restaurant_savedn, Toast.LENGTH_SHORT).show();
                                      if (getListener() != null) getListener().onDialogSucces();
                                  }
                              }

                              @Override
                              public void onNegative(MaterialDialog dialog) {
                                  dialog.dismiss();
                              }

                              @Override
                              public void onNeutral(MaterialDialog dialog) {
                                  if (restaurant == null)
                                      restaurant = new RestaurantDBModel();
                                  if (restaurantLocation != null) {
                                      restaurant.setLongitude(String.valueOf(restaurantLocation.getLongitude()));
                                      restaurant.setLatitude(String.valueOf(restaurantLocation.getLatitude()));
                                      Toast.makeText(activity, R.string.dialog_location_updated, Toast.LENGTH_SHORT).show();
                                  }
                              }
                          }


                )

                .autoDismiss(false);
        if (status == CREATE) {
            builder.title(R.string.dialog_new_title);
        } else {
            builder.title(R.string.dialog_edit_title);
        }
        this.dialog = builder.show();

    }

    @Override
    void setViews(View view) {
        name = (AppCompatEditText) view.findViewById(R.id.etName);
        address = (AppCompatEditText) view.findViewById(R.id.etAddress);
    }

    @Override
    void save() {

    }

    @Override
    boolean validate() {
        final Form mForm = Form.create();
        mForm.addField(Field.using(name).validate(NotEmpty.build()));
        mForm.addField(Field.using(address).validate(NotEmpty.build()));
        mForm.errorHandler(new EditTextErrorHandler());
        if (restaurant.getLatitude() == null || restaurant.getLongitude() == null) {
            return false;
        }
        return mForm.isValid();
    }

    @Override
    void updateUI() {

    }

    @Override
    void fillDate() {
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
    }

    @Override
    void blockInput(boolean status) {

    }
}
