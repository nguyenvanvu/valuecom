package com.androidexample.mvc;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by KienVang on 7/23/2015.
 */
public class MyDialog extends Dialog {
    public MyDialog(Context context, myOnClickListener myclick) {
        super(context);
        this.myListener = myclick;
    }


    public myOnClickListener myListener;

    // This is my interface //
    public interface myOnClickListener {
        void onButtonClick();
        void onButtonCancel();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mydialog);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myListener.onButtonClick();
                // I am giving the click to the
                // interface function which we need
                // to implements where we call this
                // class

            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myListener.onButtonCancel();
                cancel();
                // I am giving the click to the
                // interface function which we need
                // to implements where we call this
                // class

            }
        });
    }
}
