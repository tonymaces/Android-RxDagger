package com.tonymaces.rxdaggerdemo.service;

import com.tonymaces.rxdaggerdemo.model.Flower;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tonym on 02/07/2016.
 */
public interface FlowerService {
    @GET("/feeds/flowers.json")
    Observable<List<Flower>> getFlowers();
}
