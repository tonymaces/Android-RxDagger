package com.tonymaces.rxdaggerdemo.application;

import android.app.Application;

import com.tonymaces.rxdaggerdemo.dependencies.ApiComponent;
import com.tonymaces.rxdaggerdemo.dependencies.DaggerApiComponent;
import com.tonymaces.rxdaggerdemo.dependencies.DaggerNetworkComponent;
import com.tonymaces.rxdaggerdemo.dependencies.NetworkComponent;
import com.tonymaces.rxdaggerdemo.dependencies.NetworkModule;
import com.tonymaces.rxdaggerdemo.model.Constant;

/**
 * Created by tonym on 02/07/2016.
 */
public class FlowerApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    private NetworkComponent getNetworkComponent() {
       return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public  ApiComponent getApiComponent(){
        return mApiComponent;
    }
}
