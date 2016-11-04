package com.dyc.test.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by win7 on 2016/8/29.
 */
public class PictureAndCameraFragment extends BaseFragment {

    private ImageView iamge;
    private Uri uri;
    @Override
    public void initViews(ViewGroup rootView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.picandcam_fragment;
    }

    @Override
    public void setViews(View rootView) {
        iamge = (ImageView) rootView.findViewById(R.id.showpic);
        Button button= (Button) rootView.findViewById(R.id.getpic);
        ImageView image = (ImageView) rootView.findViewById(R.id.showpic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SelectionDialogFragment dialog = new SelectionDialogFragment();
                dialog.show(getChildFragmentManager(),"");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = null;
        if(resultCode== Activity.RESULT_OK) {
            switch (requestCode){
                case 1:

                     uri =SelectionDialogFragment.imagUrl;
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra("crop",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,SelectionDialogFragment.imagUrl);
                    startActivityForResult(intent,2);
                    break;
                case 3:
                    if(null!=data&&null!=data.getData()) {
                        uri=data.getData();
                    }
                     intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra("crop",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,SelectionDialogFragment.imagUrl);
                    startActivityForResult(intent,2);
                    /**
                     * 图库要用data.getData()
                     */




                    break;
                case 2:

                         uri =SelectionDialogFragment.imagUrl;
//                        if(null!=data&&null!=data.getData()) {
//                            uri=data.getData();
//                        }
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    iamge.setImageBitmap(bitmap);

                        break;

            }

        }
    }
}
