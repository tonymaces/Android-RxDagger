package com.tonymaces.rxdaggerdemo.base;

import com.tonymaces.rxdaggerdemo.model.Flower;
import com.tonymaces.rxdaggerdemo.service.FlowerViewInterface;

import java.util.List;

import rx.Observer;

/**
 * Created by tonym on 02/07/2016.
 */
public class FlowerPresenter extends BasePresenter implements Observer<List<Flower>> {

    private  FlowerViewInterface mViewInterface;

    public FlowerPresenter(FlowerViewInterface viewInterface) {
        this.mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<Flower> flowers) {
        mViewInterface.onFlowers(flowers);
    }


    public void fetchFlowers() {
        unSubscribeAll();
        subscribe(mViewInterface.getFlowers(), FlowerPresenter.this);
    }

}
