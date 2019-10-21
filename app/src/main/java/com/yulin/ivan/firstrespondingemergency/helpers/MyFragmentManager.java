package com.yulin.ivan.firstrespondingemergency.helpers;

import com.yulin.ivan.firstrespondingemergency.fragments.Answer_y_n_btn;
import com.yulin.ivan.firstrespondingemergency.fragments.EmergencyFragment;
import com.yulin.ivan.firstrespondingemergency.fragments.Calling_for_help;
import com.yulin.ivan.firstrespondingemergency.fragments.First_is_help_needed;
import com.yulin.ivan.firstrespondingemergency.fragments.Is_can_move;
import com.yulin.ivan.firstrespondingemergency.fragments.Is_hurt;
import com.yulin.ivan.firstrespondingemergency.fragments.Move_to_safety;
import com.yulin.ivan.firstrespondingemergency.fragments.Stress_scale;
import com.yulin.ivan.firstrespondingemergency.fragments.Wait_for_help;
import com.yulin.ivan.firstrespondingemergency.fragments.Welcome_message;

/**
 * Created by Ivan Y. on 2019-10-20.
 */

public class MyFragmentManager {
    private static FragmentReplacerActivity activity;
    private static MyFragmentManager instance;

    private MyFragmentManager() {
    }

    public static MyFragmentManager getInstance(FragmentReplacerActivity activity) {
        if (instance == null) {
            MyFragmentManager.activity = activity;
            instance = new MyFragmentManager();
        }
        return instance;
    }

    public static MyFragmentManager getInstance() {
        if (instance == null) {
            instance = new MyFragmentManager();
        }
        return instance;
    }

    public EmergencyFragment getFirstFragment() {
        return new First_is_help_needed();
//        return new Stress_scale();
    }

    public void onPositiveAns(String fragmentClassName) {
        EmergencyFragment frag = getPositiveAnsFragment(fragmentClassName);
        activity.replaceFragment(frag);
    }

    public void onNegativeAns(String fragmentClassName) {
        EmergencyFragment frag = getNegativeAnsFragment(fragmentClassName);
        activity.replaceFragment(frag);
    }

    /**
     * flow is decided here
     *
     * @param fragmentClassName current fragment to be replaces
     * @return a fragment that replaces the current one
     */
    private EmergencyFragment getPositiveAnsFragment(String fragmentClassName) {
        if (fragmentClassName.equals(First_is_help_needed.class.getName())) {
            return new Welcome_message();
        } else if (fragmentClassName.equals(Welcome_message.class.getName())) {
            return new Is_hurt();
        } else if (fragmentClassName.equals(Is_hurt.class.getName())) {
            return new Calling_for_help();
        } else if (fragmentClassName.equals(Calling_for_help.class.getName())) {
            return new Wait_for_help();
        } else if (fragmentClassName.equals(Is_can_move.class.getName())) {
            return new Move_to_safety();
        } else if (fragmentClassName.equals(Move_to_safety.class.getName())) {
           return new Stress_scale();
        } else {
            return null;
        }
//        return null; //todo remove this line when all complete
    }

    private EmergencyFragment getNegativeAnsFragment(String fragmentClassName) {

        if (fragmentClassName.equals(First_is_help_needed.class.getName())) {
            return null;
        } else if (fragmentClassName.equals(Is_hurt.class.getName())) {
            //todo
        } else if (fragmentClassName.equals(Calling_for_help.class.getName())) {
            return new Is_can_move();
        } else if (fragmentClassName.equals(Is_can_move.class.getName())) {
            return new Answer_y_n_btn();
        } else {
            return null;
        }
        return null; //todo remove this line when all complete
    }

    public interface FragmentReplacerActivity {
        void replaceFragment(EmergencyFragment fragment/*, String tag*/);
    }
}
