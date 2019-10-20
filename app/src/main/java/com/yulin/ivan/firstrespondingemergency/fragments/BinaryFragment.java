package com.yulin.ivan.firstrespondingemergency.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.yulin.ivan.firstrespondingemergency.helpers.MyFragmentManager;

/**
 * Created by Ivan Y. on 2019-10-20.
 */

public class BinaryFragment extends DialogFragment {

    MyFragmentManager myFragmentManager = MyFragmentManager.getInstance();
    public String title;
    public String okString;
    public String cancelString;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton(okString,
                        (dialog, whichButton) -> {
                            onDismiss(dialog);
                            onYesSelected();
                        }
                )
                .setNegativeButton(cancelString,
                        (dialog, whichButton) -> {
                            onCancel(dialog);
                            onNoSelected();
                        }
                );

        return b.create();
    }

    //default behaviour, separated to enable overriding
    void onYesSelected() {
        myFragmentManager.onYesSelected(this.getClass().getName());
    }

    //default behaviour, separated to enable overriding
    void onNoSelected() {
        myFragmentManager.onNoSelected(this.getClass().getName());
    }
}
