package com.tonymaces.rxdaggerdemo.service;

import com.tonymaces.rxdaggerdemo.model.Flower;

import java.util.List;

import rx.Observable;

/**
 * Created by tonym on 02/07/2016.
 */
public interface FlowerViewInterface {

    void onCompleted();

    void onError(String message);

    void onFlowers(List<Flower> flowers);

    Observable<List<Flower>> getFlowers();
}
