package co.ahuskano.something.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import co.ahuskano.something.R;
import co.ahuskano.something.constants.AppConstants;

/**
 * Created by ahuskano on 11.7.2015..
 */
public abstract class BaseFragment extends Fragment {

    private ProgressDialog dialog;

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
                Log.d("test", msg);
                break;
            case AppConstants.KEY_TOAST:
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                break;
            default:

        }

    }

    public void showDialog(){
        if(dialog==null)
            setUpDialog();
        dialog.show();
    }

    private void setUpDialog(){
        dialog=new ProgressDialog(getActivity(), R.style.DialogTheme);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
    }

    public void dismissDialog(){
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();
    }

}
