package co.ahuskano.something.listutils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ahuskano on 12.7.2015..
 */

abstract public class DataBinder<T extends RecyclerView.ViewHolder> {
    public SomethingAdapter mDataBindAdapter;

    public DataBinder(SomethingAdapter dataBindAdapter) {
        mDataBindAdapter = dataBindAdapter;
    }

    public View getView(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(
                provideLayout(), parent, false);
        return view;
    }

    abstract public T newViewHolder(ViewGroup parent);

    abstract public void bindViewHolder(T holder, int position);

    abstract public int getItemCount();

    abstract public int provideLayout();


}
