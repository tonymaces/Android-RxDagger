package com.tonymaces.rxdaggerdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tonymaces.rxdaggerdemo.application.FlowerApplication;
import com.tonymaces.rxdaggerdemo.base.FlowerPresenter;
import com.tonymaces.rxdaggerdemo.model.Flower;
import com.tonymaces.rxdaggerdemo.model.FlowerAdapter;
import com.tonymaces.rxdaggerdemo.service.FlowerService;
import com.tonymaces.rxdaggerdemo.service.FlowerViewInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements FlowerViewInterface {

    @Inject
    FlowerService mService;

    private FlowerPresenter mPresenter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private FlowerAdapter mAdapter;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        ButterKnife.bind(MainActivity.this);
        configViews();
        mPresenter= new FlowerPresenter(MainActivity.this);
        mPresenter.onCreate();
    }

    private void resolveDependency() {
        ((FlowerApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void configViews() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FlowerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchFlowers();
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please Wait...");
        mDialog.show();
    }

    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        mDialog.dismiss();;
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT);
    }

    @Override
    public void onFlowers(List<Flower> flowers) {
        mAdapter.addFlowers(flowers);
    }

    @Override
    public Observable<List<Flower>> getFlowers() {
       return mService.getFlowers();
    }
}
