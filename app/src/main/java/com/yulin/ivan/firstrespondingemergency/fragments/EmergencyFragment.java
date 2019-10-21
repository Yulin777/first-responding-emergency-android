package com.yulin.ivan.firstrespondingemergency.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

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
        setCancelable(false); //avoid close on back press
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

//        dialogBuilder = new AlertDialog.Builder(getActivity());

        if (message != null) {
//            dialogBuilder.setMessage(message);
            alertDialog.setMessage(message);
        }

        if (yesAnswer != null) {

            alertDialog.setButton(Dialog.BUTTON_POSITIVE, yesAnswer,
                    (dialog, whichButton) -> {
                        onDismiss(dialog);
                        onYesSelected();
                    });

//            dialogBuilder.setPositiveButton(yesAnswer,
//                    (dialog, whichButton) -> {
//                        onDismiss(dialog);
//                        onYesSelected();
//                    });
        }

        if (noAnswer != null) {

            alertDialog.setButton(Dialog.BUTTON_NEGATIVE, noAnswer,
                    (dialog, whichButton) -> {
                        onDismiss(dialog);
                        onNoSelected();
                    });

//            dialogBuilder.setNegativeButton(noAnswer,
//                    (dialog, whichButton) -> {
//                        onCancel(dialog);
//                        onNoSelected();
//                    });
        }

//        if (layout != -1) {
//            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View v = inflater.inflate(layout, null);
////            dialogBuilder.setView(v);
////            alertDialog.setView(v);
//        }

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        alertDialog.getWindow().setLayout((6 * width) / 7, (4 * height) / 5);


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
