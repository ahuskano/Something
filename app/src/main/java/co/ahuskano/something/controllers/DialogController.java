package co.ahuskano.something.controllers;

import android.app.Activity;
import android.app.ProgressDialog;

import co.ahuskano.something.R;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class DialogController extends BaseController {


    private Activity activity;
    private ProgressDialog dialog;

    public DialogController(Activity activity){
        this.activity=activity;
    }
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
