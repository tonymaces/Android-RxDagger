package com.tonymaces.rxdaggerdemo.dependencies;


import com.tonymaces.rxdaggerdemo.MainActivity;

import dagger.Component;

/**
 * Created by tonym on 02/07/2016.
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject (MainActivity activity);
}
