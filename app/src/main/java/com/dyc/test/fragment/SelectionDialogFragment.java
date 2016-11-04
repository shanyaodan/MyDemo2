package com.dyc.test.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.dyc.test.R;
import com.dyc.test.tools.FragmentManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by win7 on 2016/8/29.
 */
public class SelectionDialogFragment extends DialogFragment {

    public static Uri imagUrl;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.CustomFragmentDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_frombutton);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        /**
         * 从相机中选择图片
         */
        dialog.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File fout = new File(Environment.getExternalStorageDirectory(),"outputimage.png");
                try {
                  if(fout.exists()) {
                      fout.delete();
                  }
                    fout.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imagUrl = Uri.fromFile(fout);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imagUrl);
               getParentFragment().startActivityForResult(intent,1);
                dismiss();
            }
        });


        dialog.findViewById(R.id.buton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            File outImage = new File(Environment.getExternalStorageDirectory(),"outputimage.png");
            if(outImage.exists()){
                outImage.delete();
            }
                try {
                    outImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

             imagUrl = Uri.fromFile(outImage);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imagUrl);
                getParentFragment().startActivityForResult(intent,3);
                dismiss();
            }
        });


        return dialog;
    }


}
