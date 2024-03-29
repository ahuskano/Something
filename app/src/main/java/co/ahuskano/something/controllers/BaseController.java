package co.ahuskano.something.controllers;

import co.ahuskano.something.api.BaseResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahuskano on 11.7.2015..
 */
public abstract class BaseController {

    public interface OnDataReadListener {
        void onDataReceive(BaseResponse response);
    }

    public interface OnDataErrorListener {
        void onDataErrorReceive(RetrofitError error);
    }

    protected OnDataReadListener onDataReadListener;

    protected OnDataErrorListener onDataErrorListener;

    public BaseController() {

    }

    private Callback<BaseResponse> responseCallback = new Callback<BaseResponse>() {
        @Override
        public void success(BaseResponse response, Response response2) {
            if (onDataReadListener != null)
                onDataReadListener.onDataReceive(response);

        }

        @Override
        public void failure(RetrofitError error) {
            if (onDataErrorListener != null)
                onDataErrorListener.onDataErrorReceive(error);
        }
    };

    public OnDataReadListener getOnDataReadListener() {
        return onDataReadListener;
    }

    public void setOnDataReadListener(OnDataReadListener onDataReadListener) {
        this.onDataReadListener = onDataReadListener;
    }

    public OnDataErrorListener getOnDataErrorListener() {
        return onDataErrorListener;
    }

    public void setOnDataErrorListener(OnDataErrorListener onDataErrorListener) {
        this.onDataErrorListener = onDataErrorListener;
    }
}
