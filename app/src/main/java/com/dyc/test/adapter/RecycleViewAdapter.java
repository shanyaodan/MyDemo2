package com.dyc.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyc.test.R;

import java.util.ArrayList;

/**
 * Created by win7 on 2016/8/18.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private ArrayList datas;

    public RecycleViewAdapter(ArrayList datas) {
        this.datas = datas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview,parent,false);
        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText((String)datas.get(position));
    }

    @Override
    public int getItemCount() {
        return (null == datas | datas.size() == 0) ? 0 : datas.size();
    }


    public void delete(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }
    public void move(int from, int to) {
        String prev = (String) datas.remove(from);
        datas.add(to > from ? to - 1 : to, prev);
        notifyItemMoved(from, to);
    }
 public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public ViewHolder(TextView itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}
