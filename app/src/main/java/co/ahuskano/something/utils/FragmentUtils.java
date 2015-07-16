package co.ahuskano.something.utils;

import android.support.v4.app.Fragment;

import co.ahuskano.something.fragments.FragmentDashboard;
import co.ahuskano.something.fragments.FragmentList;
import co.ahuskano.something.fragments.FragmentMap;

/**
 * Created by ahuskano on 11.7.2015..
 */
public class FragmentUtils {

    public static final int KEY_FRAGMENT_LIST = 1;
    public static final int KEY_FRAGMENT_MAP = 2;


    public static Fragment provideFragment(int ID) {
        switch (ID) {
            case KEY_FRAGMENT_LIST:
                return new FragmentList();
            case KEY_FRAGMENT_MAP:
                return new FragmentMap();
            default:
                return new FragmentList();
        }
    }
}
