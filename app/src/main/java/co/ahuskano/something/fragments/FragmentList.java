package co.ahuskano.something.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;

import co.ahuskano.something.R;
import co.ahuskano.something.RestaurantActivity;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.constants.AppConstants;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.RestoransController;
import co.ahuskano.something.dialogs.BaseDialog;
import co.ahuskano.something.dialogs.RestaurantDialog;
import co.ahuskano.something.listutils.RecyclerItemClickListener;
import co.ahuskano.something.listutils.SomethingAdapter;
import co.ahuskano.something.models.RestaurantDBModel;
import retrofit.RetrofitError;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class FragmentList extends BaseFragment implements BaseDialog.OnDialogSuccess,View.OnClickListener,BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private RecyclerView restorans;
    private SomethingAdapter<RestaurantDBModel> adapter;

    @Override
    public void initViews(View view) {
        view.findViewById(R.id.action_button).setOnClickListener(this);
        this.restorans = (RecyclerView) view.findViewById(R.id.restorans);
        this.restorans.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.restorans.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                        intent.putExtra(AppConstants.KEY_RESTAURANT_ID,adapter.getItem(position).getId());
                        startActivity(intent);
                    }
                })
        );
        RestoransController controller = new RestoransController(getActivity());
        controller.setOnDataReadListener(this);
        controller.setOnDataErrorListener(this);
        controller.getRestorans();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public String getName() {
        return FragmentList.class.getSimpleName();
    }

    @Override
    public void onDataReceive(BaseResponse response) {
        this.adapter = new SomethingAdapter<>(response.getRestaurants());
        this.restorans.setAdapter(adapter);
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_button:
                new RestaurantDialog(getActivity(), BaseDialog.CREATE,this);
                break;
        }
    }

    @Override
    public void onDialogSucces() {
        RestoransController controller = new RestoransController(getActivity());
        controller.setOnDataReadListener(this);
        controller.setOnDataErrorListener(this);
        controller.getRestorans();

    }
}
