package com.yulin.ivan.firstrespondingemergency.helpers;

import com.yulin.ivan.firstrespondingemergency.fragments.BinaryFragment;
import com.yulin.ivan.firstrespondingemergency.fragments.FirstFragment;
import com.yulin.ivan.firstrespondingemergency.fragments.SecondFragment;

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

    public BinaryFragment getFirstFragment() {
        return new FirstFragment();
    }

    public void onYesSelected(String fragmentClassName) {
        BinaryFragment frag = getNextFragment(fragmentClassName);

        if (frag != null) {
            String tag = getFragmentTag(frag.getClass().getName());
            activity.replaceFragment(frag, tag);
        }
    }

    /**
     * hard coding tags for fragments
     *
     * @param fragmentClassName
     * @return a fragment tag that replaces current fragment
     */
    private String getFragmentTag(String fragmentClassName) {
        if (fragmentClassName.equals(FirstFragment.class.getName())) {
            return "first";
        } else if (fragmentClassName.equals(SecondFragment.class.getName())) {
            return "second";
        } else {
            return null;
        }
    }

    public void onNoSelected(String fragmentClassName) {
        getPreviousFragment(fragmentClassName);
    }

    /**
     * flow is decided here
     *
     * @param fragmentClassName current fragment to be replaces
     * @return a fragment that replaces the current one
     */
    private BinaryFragment getNextFragment(String fragmentClassName) {
        if (fragmentClassName.equals(FirstFragment.class.getName())) {
            return new SecondFragment();
        } else {
            return null;
        }
    }

    private BinaryFragment getPreviousFragment(String fragmentClassName) {
       /*
        if (fragmentClassName.equals(*//*ThirdFragment*//*.class.getName())) {
            return new *//*Something*//*Fragment();
        } else {
            return null;
        }
        */
        return null; //todo remove this line
    }

    public String getFirstFragmentTag() {
        return getFragmentTag(FirstFragment.class.getName());
    }

    public interface FragmentReplacerActivity {
        void replaceFragment(BinaryFragment fragment, String tag);
    }
}
