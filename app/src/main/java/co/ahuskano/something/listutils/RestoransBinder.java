package co.ahuskano.something.listutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.ahuskano.something.R;
import co.ahuskano.something.models.RestaurantDBModel;
import co.ahuskano.something.utils.FontUtils;

/**
 * Created by ahuskano on 12.7.2015..
 */
public class RestoransBinder extends DataBinder<RestoransBinder.ViewHolder> {

    public RestoransBinder(SomethingAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }
    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        return new ViewHolder(getView(parent));
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.findViews();
        holder.fillDate((RestaurantDBModel) mDataBindAdapter.getItem(position));
    }

    @Override
    public int getItemCount() {
        return mDataBindAdapter.getItemCount();
    }


    @Override
    public int provideLayout() {
        return R.layout.list_item;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements SomethingAdapterItem<RestaurantDBModel> {
        private View mRoot;
        private TextView name, address;

        public ViewHolder(View view) {
            super(view);
            this.mRoot = view;
        }

        @Override
        public void findViews() {
            name=(TextView) mRoot.findViewById(R.id.tvName);
            address=(TextView) mRoot.findViewById(R.id.tvAddress);
        }

        @Override
        public void fillDate(RestaurantDBModel model) {
            name.setText(model.getName());
            address.setText(model.getAddress());
        }


    }
}
