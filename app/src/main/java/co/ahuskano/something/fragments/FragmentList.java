package co.ahuskano.something.fragments;

import android.view.View;

import co.ahuskano.something.R;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class FragmentList extends BaseFragment {

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public String getName() {
        return FragmentList.class.getSimpleName();
    }
}
