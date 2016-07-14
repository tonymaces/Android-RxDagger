package com.tonymaces.rxdaggerdemo.base;

/**
 * Created by tonym on 02/07/2016.
 */
public interface Presenter {

    void onCreate();

    void onPause();

    void onResume();

    void onDestroy();

}
