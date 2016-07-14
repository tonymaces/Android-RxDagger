package com.tonymaces.rxdaggerdemo.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by tonym on 02/07/2016.
 */
@Singleton
@Component(modules=NetworkModule.class)
public interface NetworkComponent {
    Retrofit retrofit();
}
