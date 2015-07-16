package co.ahuskano.something.dialogs;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by ahuskano on 16.7.2015..
 */
public abstract class BaseDialog {

    public interface OnDialogSuccess {
        void onDialogSucces();
    }
    public static final int CREATE = 2;
    public static final int EDIT = 3;

    protected OnDialogSuccess listener;
    protected Activity activity;
    protected MaterialDialog dialog;
    protected int status=0;
    protected boolean wrapInScrollView = true;

    abstract void setDialog();

    abstract void setViews(View view);

    abstract void save();

    abstract boolean validate();

    abstract void updateUI();

    abstract void fillDate();

    abstract void blockInput(boolean status);

    public OnDialogSuccess getListener() {
        return listener;
    }

    public void setListener(OnDialogSuccess listener) {
        this.listener = listener;
    }

    public BaseDialog(Activity activity, int status) {
        this.activity = activity;
        this.status=status;
        setDialog();
        setViews(dialog.getView());
        if(status==BaseDialog.EDIT) {
            fillDate();
        }
    }
    public BaseDialog(Activity activity, int status, OnDialogSuccess listener) {
        this.activity = activity;
        this.status=status;
        this.listener=listener;
        setDialog();
        setViews(dialog.getView());
        if(status==BaseDialog.EDIT) {
            fillDate();
        }
    }

    protected void onPositiveClicked() {
        if (validate()) {
            save();
            updateUI();
        } else {
            toastIt("NEMERE");
        }
    }

    private void toastIt(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
