package de.jsauerwein.mvctemplate.interaction;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import de.jsauerwein.mvctemplate.AppContract;
import de.jsauerwein.mvctemplate.R;
import de.jsauerwein.mvctemplate.UiFragmentFactory;

/**
 * This Fragment is responsible for handling the interactions: exchanging the fragments, communicating with the fragments, ...
 */
public class InteractionModel extends Fragment {

    private LocalBroadcastManager localBroadcastManager;
    private String[] associatedFragmentTags;
    private Observer observer;
    private String currentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.getActivity());
        this.observer = new Observer(this);

        Bundle arguments = this.getArguments();
        this.associatedFragmentTags = arguments.getStringArray(AppContract.FRAGMENTS);
        if(this.associatedFragmentTags.length > 0) {
            this.currentFragment = this.associatedFragmentTags[0];
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for(String associatedFragmentTag : this.associatedFragmentTags) {
            Fragment associatedFragment = this.getFragmentManager().findFragmentByTag(associatedFragmentTag);
            if (associatedFragment == null) {
                associatedFragment = UiFragmentFactory.buildFragment(associatedFragmentTag);
                this.getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, associatedFragment, associatedFragmentTag)
                        .hide(associatedFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        this.localBroadcastManager.registerReceiver(this.observer, new IntentFilter(AppContract.BROADCAST_INTERACTION));

        Fragment actualFragment = this.getFragmentManager().findFragmentByTag(this.currentFragment);
        if(actualFragment != null) {
            this.getFragmentManager()
                    .beginTransaction()
                    .show(actualFragment)
                    .commit();
        }
    }

    public void switchFragments(String tag) {
        Fragment actualFragment = this.getFragmentManager().findFragmentByTag(this.currentFragment);
        Fragment nextFragment = this.getFragmentManager().findFragmentByTag(tag);
        if(nextFragment != null) {
            if (this.currentFragment != null) {
                this.getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .hide(actualFragment)
                        .show(nextFragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                this.getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .show(nextFragment)
                        .commit();
            }

            this.currentFragment = tag;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        this.localBroadcastManager.unregisterReceiver(this.observer);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
