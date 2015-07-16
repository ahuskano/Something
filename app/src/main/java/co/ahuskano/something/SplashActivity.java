package co.ahuskano.something;

/**
 * Created by ahuskano on 16.7.2015..
 */

public class SplashActivity extends SplashAbstract {

    @Override
    public int provideLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public int getSplashTime() {
        return 1500;
    }

    @Override
    public Class getNextClassActivity() {

        return HomeActivity.class;
    }
}