package com.tonymaces.rxdaggerdemo.dependencies;

import com.tonymaces.rxdaggerdemo.service.FlowerService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by tonym on 02/07/2016.
 */
@Module
public class ApiModule {
    @Provides
    @CustomScope
    FlowerService provideFlowerService(Retrofit retrofit){
        return  retrofit.create(FlowerService.class);
    }
}
