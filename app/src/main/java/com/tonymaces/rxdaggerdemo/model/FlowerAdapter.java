package com.tonymaces.rxdaggerdemo.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tonymaces.rxdaggerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonym on 03/07/2016.
 */
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    private List<Flower> mFlowers;

    public FlowerAdapter() {
        this.mFlowers = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType){
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_item,parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Flower currFlower = mFlowers.get(position);
        holder.mName.setText(currFlower.getName());
        holder.mPrice.setText("$ "+Double.toString(currFlower.getPrice()));
        Picasso.with(holder.itemView.getContext()).load("http://services.hanselandpetal.com/photos/" + currFlower.getPhoto()).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mFlowers.size();
    }

    public void addFlowers(List<Flower> flowers) {
        mFlowers.addAll(flowers);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ImageView mPhoto;
        private TextView mName,mPrice;

        public Holder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.flowerPhoto);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
        }
    }
}
