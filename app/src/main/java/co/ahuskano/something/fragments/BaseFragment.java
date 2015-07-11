package co.ahuskano.something.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.ahuskano.something.constants.AppConstants;

/**
 * Created by ahuskano on 11.7.2015..
 */
public abstract class BaseFragment extends Fragment {

    public abstract void initViews(View view);

    public abstract int getLayout();

    public abstract String getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(getLayout(), container, false);
        try {
            initViews(root);
        } catch (Throwable e) {
            getActivity().finish();
        }
        return root;
    }

    public void reportIt(String msg, int type) {
        switch (type) {
            case AppConstants.KEY_LOG:

                break;
            case AppConstants.KEY_TOAST:

                break;
            default:

        }

    }
}
