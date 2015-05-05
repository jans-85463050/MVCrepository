package de.jsauerwein.mvctemplate.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import de.jsauerwein.mvctemplate.R;
import de.jsauerwein.mvctemplate.blue.BlueActivity;
import de.jsauerwein.mvctemplate.green.GreenActivity;

public class DrawerActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    private static final String BLUE = "Blue";
    private static final String GREEN = "Green";

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_layout);

        this.drawerLayout = (DrawerLayout) findViewById(R.id.base_layout);
        String[] drawerItems = new String[] { BLUE, GREEN };
        ListView drawerList = (ListView) this.findViewById(R.id.drawer);
        drawerList.setAdapter(new DrawerAdapter<String>(this, R.layout.drawer_item, drawerItems));
        drawerList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.drawerLayout.closeDrawers();
        DrawerAdapter.ViewHolder viewHolder = (DrawerAdapter.ViewHolder)view.getTag();
        String viewId = viewHolder.id;
        Intent switchActivity = null;
        if(BLUE.equals(viewId)) {
            switchActivity = new Intent(this, BlueActivity.class);
        } else if(GREEN.equals(viewId)) {
            switchActivity = new Intent(this, GreenActivity.class);
        }

        if(switchActivity != null) {
            this.startActivity(switchActivity);
        }
    }
}
