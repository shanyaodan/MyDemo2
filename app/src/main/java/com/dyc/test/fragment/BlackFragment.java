package com.dyc.test.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.print.PrintHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

/**
 * Created by win7 on 2016/7/22.
 */
public class BlackFragment extends BaseFragment {
    @Override
    public void initViews(ViewGroup rootView) {

        String str = getArguments().getString("key");
        TextView textView = (TextView) rootView.findViewById(R.id.content);
        textView.setText(str);

        doPhotoPrint();
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(getActivity());
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_menu_send);
        photoPrinter.printBitmap("droids.jpg - test print", bitmap);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_black;
    }

    @Override
    public void setViews(View rootView) {

    }


}
