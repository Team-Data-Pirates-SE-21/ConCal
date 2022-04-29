package com.example.concal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

// Class of loading dialog
public class LoadingDialog {

    private final Activity activity;
    private AlertDialog dialog;

    LoadingDialog(Activity myActivity){
        activity=myActivity;
    }

    @SuppressLint("InflateParams")
    void startLoadingDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }
}
