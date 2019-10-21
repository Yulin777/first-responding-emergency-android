package com.yulin.ivan.firstrespondingemergency.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.yulin.ivan.firstrespondingemergency.R;
import com.yulin.ivan.firstrespondingemergency.helpers.MyFragmentManager;

/**
 * Created by Ivan Y. on 2019-10-20.
 */

public abstract class EmergencyFragment extends DialogFragment {
    public AlertDialog.Builder dialogBuilder;
    private MyFragmentManager myFragmentManager = MyFragmentManager.getInstance();
    public String message;
    public String yesAnswer;
    public String noAnswer;
    private int layout = -1;

    EmergencyFragment(String message, String yesAnswer, String noAnswer) {
        this.message = message;
        this.yesAnswer = yesAnswer;
        this.noAnswer = noAnswer;
    }

    EmergencyFragment(String message, String yesAnswer) {
        this.message = message;
        this.yesAnswer = yesAnswer;
        this.noAnswer = null;
    }

    EmergencyFragment(String message, String yesAnswer, int layout) {
        this.message = message;
        this.yesAnswer = yesAnswer;
        this.layout = layout;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyAlertDialogTheme);
        setCancelable(false); //avoid close on back press

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme).create();

        if (message != null) {
            alertDialog.setMessage(message);
        }

        if (yesAnswer != null) {

            alertDialog.setButton(Dialog.BUTTON_POSITIVE, yesAnswer,
                    (dialog, whichButton) -> {
                        onDismiss(dialog);
                        onYesSelected();
                    });
        }

        if (noAnswer != null) {

            alertDialog.setButton(Dialog.BUTTON_NEGATIVE, noAnswer,
                    (dialog, whichButton) -> {
                        onDismiss(dialog);
                        onNoSelected();
                    });

        }

        if (layout != -1) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layout, null);
//            dialogBuilder.setView(v);
            alertDialog.setView(v);
        }

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
//        alertDialog.getWindow().setLayout((6 * width) / 7, (4 * height) / 5);

        alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        alertDialog.getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;//setGravity(Gravity.CENTER);

        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 10;
        btnPositive.setLayoutParams(layoutParams);
        btnNegative.setLayoutParams(layoutParams);

        TextView textView = alertDialog.findViewById(android.R.id.message);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).topMargin = height / 2;
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).bottomMargin = 80;


        return alertDialog;
    }

    //default behaviour, separated to enable overriding
    void onYesSelected() {
        myFragmentManager.onPositiveAns(this.getClass().getName());
    }

    //default behaviour, separated to enable overriding
    void onNoSelected() {
        myFragmentManager.onNegativeAns(this.getClass().getName());
    }

}
