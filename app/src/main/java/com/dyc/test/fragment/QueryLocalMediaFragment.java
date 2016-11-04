package com.dyc.test.fragment;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dyc.test.R;
import com.dyc.test.base.BaseAdapter;
import com.dyc.test.base.BaseFragment;

import java.util.List;

/**
 * Created by win7 on 2016/8/24.
 */
public class QueryLocalMediaFragment extends BaseFragment
{
    @Override
    public void initViews(ViewGroup rootView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.querylocalmedia_fragment;
    }

    @Override
    public void setViews(View rootView) {
    ListView lv = (ListView) rootView.findViewById(R.id.listview);
        String[] cols = new String[] {
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS
        };

        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        MyQueryHandler async=new MyQueryHandler(context.getContentResolver());
        async.startQuery(0, null, uri,
                cols, null , null, MediaStore.Audio.Artists.ARTIST_KEY);
    }

    class MyQueryHandler extends AsyncQueryHandler{

        public MyQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            super.onQueryComplete(token, cookie, cursor);
            int cuount=cursor.getCount();
            cursor.moveToFirst();

            int index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST);

            for(int a=0;a<cuount;a++){
                String strs= cursor.getString(index);
                System.out.println("strstrstr:"+strs);
                cursor.moveToNext();
            }
        }
    }


    private class Myadater extends BaseAdapter{

        public Myadater(Context context, List data) {
            super(context, data);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_mediascan;
        }

        @Override
        public int[] getConvertItemIds() {
            return new int[]{R.id.text1,R.id.text2,R.id.text3};
        }

        @Override
        public void initViews(ViewHolder vh, int position) {

        }
    }


}
