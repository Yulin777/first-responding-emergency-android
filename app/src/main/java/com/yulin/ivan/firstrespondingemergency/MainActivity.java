package com.yulin.ivan.firstrespondingemergency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.yulin.ivan.firstrespondingemergency.fragments.BinaryFragment;
import com.yulin.ivan.firstrespondingemergency.helpers.MyFragmentManager;

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
        replaceFragment(myFragmentManager.getFirstFragment(), myFragmentManager.getFirstFragmentTag());
    }

    @Override
    public void replaceFragment(BinaryFragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragment != null) {
            fragment.show(ft, tag);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, fragment, fragment.getClass().getName())
//                    .addToBackStack(null)
//                    .commit();
//            fragment.show(getSupportFragmentManager(), fragment.getClass().getName());
        } else {
            finish();
        }

    }

}
