package co.ahuskano.something.listutils;

import com.activeandroid.Model;

/**
 * Created by ahuskano on 12.7.2015..
 */
public interface SomethingAdapterItem<T extends Model>  {
    public void findViews();

    public void fillDate(T model);
}
