package com.augmentis.ayp.crimin;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.augmentis.ayp.crimin.model.PictureUtils;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Apinya on 8/4/2016.
 */
public class PhotoDialog extends DialogFragment implements DialogInterface.OnClickListener  {

    private static final String PHOTOFILE = "PhotoDialog.PHOTOVIEW";
    private static final String EXTRA_FILE = "PhotoDialog.PHOTOFILE";


    private ImageView imageView;
    private File file;
    private Bitmap bitmap;


    public static PhotoDialog newInstance(File photoFile){
        PhotoDialog photoDialog = new PhotoDialog();
        Bundle args = new Bundle();
        args.putSerializable("PHOTOFILE", photoFile);
        photoDialog.setArguments(args);
        return photoDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        file = (File) getArguments().getSerializable("PHOTOFILE");

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);
        imageView = (ImageView) v.findViewById(R.id.photo_view);

        Bitmap bitmap = PictureUtils.getScaledBitmap(file.getPath(),
                getActivity() );

        imageView.setImageBitmap(bitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(v);
        builder.setPositiveButton(android.R.string.ok, this);

        return builder.create();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        file = (File) getArguments().getSerializable("PHOTOFILE");

        sendResult(Activity.RESULT_OK, file);

    }

    private void sendResult(int resultCode, File file) {

        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_FILE, file);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
