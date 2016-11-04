package com.dyc.test.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.dyc.test.R;
import com.dyc.test.base.BaseAdapter;
import com.dyc.test.entity.Post;

import java.util.List;

public class VolleyAdapter extends BaseAdapter {

    public VolleyAdapter(Context context, List<? extends Object> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public int getLayoutId() {
        return R.layout.item_testvolley1;
    }

    public int[] getConvertItemIds() {
        return new int[]{R.id.title_data, R.id.content_data,};
    }

    public void initViews(ViewHolder vh, int position) {
        Post post = (Post) entitys.get(position);
        ((TextView) vh.getView(R.id.title_data)).setText(post.title);
        ((TextView) vh.getView(R.id.content_data)).setText(Html.fromHtml(post.content));
    }


}
