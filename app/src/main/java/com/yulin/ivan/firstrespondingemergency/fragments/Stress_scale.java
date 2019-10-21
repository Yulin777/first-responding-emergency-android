package com.yulin.ivan.firstrespondingemergency.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yulin.ivan.firstrespondingemergency.R;

public class Stress_scale extends EmergencyFragment implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;

    public Stress_scale() {
        super("שם פרטי, דרג את עוצמת תחושת הלחץ שבו אתה נמצא על פני הסולם שאתה רואה כרגע",
                "אישור",
                R.layout.fragment_stress_scale);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stress_scale, container, false);
        Button okBtn = rootView.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(v -> onYesSelected());
//        TextView scaleNumber = rootView.findViewById(R.id.scale_number);
//        seekBar = rootView.findViewById(R.id.stress_seek_bar);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        seekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
