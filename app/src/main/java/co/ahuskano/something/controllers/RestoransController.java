package co.ahuskano.something.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import co.ahuskano.something.api.RepslyAPI;
import co.ahuskano.something.api.RestaurantAPIModel;
import co.ahuskano.something.models.RestaurantDBModel;
import co.ahuskano.something.utils.APIUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class RestoransController extends DialogController {


    public RestoransController(Activity activity) {
        super(activity);
    }

    private Callback<RestaurantAPIModel[]> callbackRestorans = new Callback<RestaurantAPIModel[]>() {
        @Override
        public void success(RestaurantAPIModel[] restoransResponse, Response response) {
            new SaveData().execute(restoransResponse);

        }

        @Override
        public void failure(RetrofitError error) {
            if (getOnDataErrorListener() != null)
                getOnDataErrorListener().onDataErrorReceive(error);
        }
    };


    public void getRestorans() {
        download();
    }

    public void download() {
        APIUtils.getRestAdapter(RepslyAPI.API_LOCATION).create(RepslyAPI.class).getRestorans(callbackRestorans);
    }


    private void goNext(Boolean enable) {
        if (enable) {
            if (getOnDataReadListener() != null)
                getOnDataReadListener().onDataReceive(null);
            else {
                if (getOnDataErrorListener() != null)
                    getOnDataErrorListener().onDataErrorReceive(null);

            }
        }
    }

    private class SaveData extends AsyncTask<RestaurantAPIModel[], String, Boolean> {


        @Override
        protected void onPostExecute(Boolean enable) {
            super.onPostExecute(enable);
            goNext(enable);
        }

        @Override
        protected Boolean doInBackground(RestaurantAPIModel[]... params) {
            for (RestaurantAPIModel restaurantAPIModel : params[0]) {
                RestaurantDBModel db = RestaurantAPIModel.convertToDb(restaurantAPIModel);
                db.save();
            }
            return true;
        }
    }

}

