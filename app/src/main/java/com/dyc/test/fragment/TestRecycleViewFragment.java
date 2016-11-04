package com.dyc.test.fragment;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import com.dyc.test.R;
import com.dyc.test.adapter.RecycleViewAdapter;
import com.dyc.test.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by win7 on 2016/8/18.
 */
public class TestRecycleViewFragment extends BaseFragment {
    RecyclerView rv = null;

    @Override
    public void initViews(ViewGroup rootView) {
        rv = (RecyclerView) rootView.findViewById(R.id.recycleview);
        ArrayList list = new ArrayList();
        list.add("sssssssss");
        list.add("bbbbbbbb");

        list.add("ccccccc");
        list.add("dddd");
        list.add("eeeee");
        list.add("fffff");
        list.add("ggggg");
        list.add("hhhhh");
        list.add("iiiiiiii");
        list.add("nnnnnnnnn");
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
       final RecycleViewAdapter rva=new RecycleViewAdapter(list);
        rv.setAdapter(rva);
        ItemTouchHelper touchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                rva.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                rva.delete(viewHolder.getAdapterPosition());
            }
        });
        touchHelper.attachToRecyclerView(rv);
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycleview_fragment;
    }

    @Override
    public void setViews(View rootView) {

    }



}
