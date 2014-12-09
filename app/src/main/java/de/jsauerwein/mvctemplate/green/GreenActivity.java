package de.jsauerwein.mvctemplate.green;

import android.os.Bundle;
import de.jsauerwein.mvctemplate.AppContract;
import de.jsauerwein.mvctemplate.drawer.DrawerActivity;
import de.jsauerwein.mvctemplate.interaction.InteractionModel;

public class GreenActivity extends DrawerActivity {
    private InteractionModel interactionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.interactionModel = (InteractionModel)this.getSupportFragmentManager().findFragmentByTag(AppContract.CONTROLLER);
        if(this.interactionModel == null) {
            this.interactionModel = new InteractionModel();
            Bundle arguments = new Bundle();
            arguments.putStringArray(AppContract.FRAGMENTS, new String[]{AppContract.LIGHT_GREEN, AppContract.DARK_GREEN});
            this.interactionModel.setArguments(arguments);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(this.interactionModel, AppContract.CONTROLLER)
                    .commit();
        }
    }
}
