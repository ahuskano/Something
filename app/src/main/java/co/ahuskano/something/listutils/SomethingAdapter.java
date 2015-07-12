package co.ahuskano.something.listutils;

import com.activeandroid.Model;

import java.util.List;

import co.ahuskano.something.constants.AppConstants;
import co.ahuskano.something.models.RestaurantDBModel;

/**
 * Created by ahuskano on 12.7.2015..
 */
public class SomethingAdapter<T extends Model> extends DataBinderAdapter {

    private List<T> items;

    public SomethingAdapter(List<T> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(0) instanceof RestaurantDBModel)
            return AppConstants.KEY_RESTAURANT;
        else
            return 0;
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        switch (viewType) {
            case AppConstants.KEY_RESTAURANT:
                return (T) new RestoransBinder(this);
            default:
                return (T) new RestoransBinder(this);
        }
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        return binderPosition;
    }

    @Override
    public int getBinderPosition(int position) {
        return position;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItem(int position) {
        return items.get(position);
    }
}
