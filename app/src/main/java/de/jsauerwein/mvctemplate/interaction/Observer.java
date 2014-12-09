package de.jsauerwein.mvctemplate.interaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import de.jsauerwein.mvctemplate.AppContract;

public class Observer extends BroadcastReceiver {
    private final InteractionModel interactionModel;

    public Observer(InteractionModel interactionModel) {
        this.interactionModel = interactionModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String nextFragment = intent.getStringExtra(AppContract.FRAGMENT);
        this.interactionModel.switchFragments(nextFragment);
    }
}
