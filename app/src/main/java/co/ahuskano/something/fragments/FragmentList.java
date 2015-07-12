package co.ahuskano.something.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import co.ahuskano.something.R;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.RestoransController;
import co.ahuskano.something.listutils.SomethingAdapter;
import co.ahuskano.something.models.RestaurantDBModel;
import retrofit.RetrofitError;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class FragmentList extends BaseFragment implements BaseController.OnDataReadListener, BaseController.OnDataErrorListener {

    private RecyclerView restorans;
    private SomethingAdapter<RestaurantDBModel> adapter;
    @Override
    public void initViews(View view) {
        this.restorans = (RecyclerView) view.findViewById(R.id.restorans);
        this.restorans.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        this.adapter=new SomethingAdapter<>(response.getRestaurants());
        this.restorans.setAdapter(adapter);
    }

    @Override
    public void onDataErrorReceive(RetrofitError error) {

    }
}
