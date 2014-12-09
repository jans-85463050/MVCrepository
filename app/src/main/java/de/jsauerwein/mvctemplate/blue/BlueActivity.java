package de.jsauerwein.mvctemplate.blue;

import android.os.Bundle;
import de.jsauerwein.mvctemplate.AppContract;
import de.jsauerwein.mvctemplate.interaction.InteractionModel;
import de.jsauerwein.mvctemplate.drawer.DrawerActivity;

public class BlueActivity extends DrawerActivity {
    private InteractionModel interactionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.interactionModel = (InteractionModel)this.getSupportFragmentManager().findFragmentByTag(AppContract.CONTROLLER);
        if(this.interactionModel == null) {
            this.interactionModel = new InteractionModel();
            Bundle arguments = new Bundle();
            arguments.putStringArray(AppContract.FRAGMENTS, new String[]{AppContract.LIGHT_BLUE, AppContract.BLUE, AppContract.DARK_BLUE});
            this.interactionModel.setArguments(arguments);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(this.interactionModel, AppContract.CONTROLLER)
                    .commit();
        }
    }
}
