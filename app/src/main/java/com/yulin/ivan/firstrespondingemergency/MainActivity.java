package com.yulin.ivan.firstrespondingemergency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.yulin.ivan.firstrespondingemergency.fragments.EmergencyFragment;
import com.yulin.ivan.firstrespondingemergency.helpers.MyFragmentManager;

/**
 * Created by Ivan Y. on 2019-10-20.
 */
public class MainActivity extends AppCompatActivity implements MyFragmentManager.FragmentReplacerActivity {

    MyFragmentManager myFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFragmentManager = MyFragmentManager.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //show the first fragment
        replaceFragment(myFragmentManager.getFirstFragment());
    }

    @Override
    public void replaceFragment(EmergencyFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragment != null) {
            fragment.show(ft,"");
        } else {
            finish();
        }

    }

}
