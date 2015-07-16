package co.ahuskano.something.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.ahuskano.something.R;
import co.ahuskano.something.constants.AppConstants;
import co.ahuskano.something.dialogs.BaseDialog;
import co.ahuskano.something.dialogs.RestaurantDialog;
import co.ahuskano.something.models.RestaurantDBModel;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by ahuskano on 12.7.2015..
 */
public class FragmentRestaurant extends BaseFragment implements BaseDialog.OnDialogSuccess,View.OnClickListener, MenuItem.OnMenuItemClickListener {

    private final int KEY_TAKE_PHOTO = 1;
    private final int KEY_SHOW_MAP = 2;
    private final int KEY_EDIT = 3;

    private RestaurantDBModel restaurant;
    private long idRestaurant = 0;
    private ImageView image;
    private String mCurrentPhotoPath;
    private TextView distance, address;

    @Override
    public void initViews(View view) {
        setHasOptionsMenu(true);
        idRestaurant = getActivity().getIntent().getLongExtra(AppConstants.KEY_RESTAURANT_ID, 0);
        if (idRestaurant != 0)
            restaurant = RestaurantDBModel.getRestoran(idRestaurant);
        else
            getActivity().finish();
        getActivity().setTitle(restaurant.getName());
        distance = (TextView) view.findViewById(R.id.tvDistanceValue);
        address = (TextView) view.findViewById(R.id.tvAddressValue);
        address.setText(restaurant.getAddress());
        image = (ImageView) view.findViewById(R.id.photo);
        if (restaurant.getPicture() != null && restaurant.getPicture() != "") {
            setPic(restaurant.getPicture());
        }
        view.findViewById(R.id.action_button).setOnClickListener(this);


        SmartLocation.with(getActivity()).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        if (location != null) {
                            Location res = new Location("al");
                            res.setLongitude(Double.valueOf(restaurant.getLongitude()));
                            res.setLatitude(Double.valueOf(restaurant.getLatitude()));
                            float me = location.distanceTo(res);
                            distance.setText(me + "m");
                        }
                    }
                });
        fillDate();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, KEY_SHOW_MAP, 0, R.string.restaurant_activity_map)
                .setIcon(R.drawable.ic_action_location_searching)
                .setOnMenuItemClickListener(this)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add(0, KEY_EDIT, 0, R.string.restaurant_activity_edit)
                .setIcon(R.drawable.ic_action_edit)
                .setOnMenuItemClickListener(this)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_restaurant;
    }

    @Override
    public String getName() {
        return FragmentRestaurant.class.getSimpleName();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case KEY_EDIT:
                getActivity().getIntent().putExtra(AppConstants.KEY_RESTAURANT_ID, restaurant.getId());
                new RestaurantDialog(getActivity(), BaseDialog.EDIT,this);
                return true;
            case KEY_SHOW_MAP:
                getActivity().getIntent().putExtra(AppConstants.KEY_RESTAURANT_ID, restaurant.getId());
                getActivity().getIntent().putExtra(AppConstants.KEY_RESTAURANT_NAME, restaurant.getName());
                getActivity().getIntent().putExtra(AppConstants.KEY_RESTAURANT_LNG, Double.valueOf(restaurant.getLongitude()));
                getActivity().getIntent().putExtra(AppConstants.KEY_RESTAURANT_LAT, Double.valueOf(restaurant.getLatitude()));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new FragmentMap()).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                reportIt(getString(R.string.error_msg_picture_taking), AppConstants.KEY_TOAST);
            }
            if (photoFile != null) {
                Uri uri = Uri.fromFile(photoFile);
                mCurrentPhotoPath = uri.getPath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        uri);
                startActivityForResult(takePictureIntent, KEY_TAKE_PHOTO);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_TAKE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                setPic(mCurrentPhotoPath);
            } else
                reportIt(getString(R.string.error_msg_picture_saving), AppConstants.KEY_TOAST);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "restaurant" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File file = new File(storageDir, imageFileName + ".jpg");
        mCurrentPhotoPath = "file:" + file.getAbsolutePath();
        return file;
    }

    private void setPic(String path) {
        int targetW = image.getWidth();
        int targetH = image.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = 2;
        if (photoH != 0 && photoW != 0 && targetH != 0 && targetW != 0)
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        image.setImageBitmap(bitmap);
        restaurant.setPicture(path);
        restaurant.save();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_button:
                takePicture();
                break;
        }
    }

    private void fillDate() {
        idRestaurant = getActivity().getIntent().getLongExtra(AppConstants.KEY_RESTAURANT_ID, 0);
        if (idRestaurant != 0)
            restaurant = RestaurantDBModel.getRestoran(idRestaurant);
        else
            getActivity().finish();
        getActivity().setTitle(restaurant.getName());
        if (address != null)
            address.setText(restaurant.getAddress());
        if (restaurant.getPicture() != null && restaurant.getPicture() != "") {
            if (image != null)
                setPic(restaurant.getPicture());
        }

    }


    @Override
    public void onDialogSucces() {
        fillDate();
    }
}
