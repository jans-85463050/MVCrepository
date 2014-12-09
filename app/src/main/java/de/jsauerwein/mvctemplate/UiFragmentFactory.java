package de.jsauerwein.mvctemplate;

import android.support.v4.app.Fragment;
import de.jsauerwein.mvctemplate.blue.BlueFragment;
import de.jsauerwein.mvctemplate.blue.DarkBlueFragment;
import de.jsauerwein.mvctemplate.blue.LightBlueFragment;
import de.jsauerwein.mvctemplate.green.DarkGreenFragment;
import de.jsauerwein.mvctemplate.green.LightGreenFragment;

import java.util.HashMap;

public class UiFragmentFactory {
    private static final int LIGHT_BLUE = 0;
    private static final int BLUE = 1;
    private static final int DARK_BLUE = 2;
    private static final int LIGHT_GREEN = 3;
    private static final int DARK_GREEN = 4;

    private static final HashMap<String, Integer> FRAGMENTS = new HashMap<String, Integer>();
    static {
        FRAGMENTS.put(AppContract.LIGHT_BLUE, LIGHT_BLUE);
        FRAGMENTS.put(AppContract.BLUE, BLUE);
        FRAGMENTS.put(AppContract.DARK_BLUE, DARK_BLUE);
        FRAGMENTS.put(AppContract.LIGHT_GREEN, LIGHT_GREEN);
        FRAGMENTS.put(AppContract.DARK_GREEN, DARK_GREEN);
    }

    public static final Fragment buildFragment(String tag) {
        switch(FRAGMENTS.get(tag)) {
            case LIGHT_BLUE:
                return new LightBlueFragment();
            case BLUE:
                return new BlueFragment();
            case DARK_BLUE:
                return new DarkBlueFragment();
            case LIGHT_GREEN:
                return new LightGreenFragment();
            case DARK_GREEN:
                return new DarkGreenFragment();
            default:
                return new DarkBlueFragment();
        }
    }
}
