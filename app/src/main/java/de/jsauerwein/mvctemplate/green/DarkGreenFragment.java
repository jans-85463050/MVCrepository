package de.jsauerwein.mvctemplate.green;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.jsauerwein.mvctemplate.AppContract;
import de.jsauerwein.mvctemplate.R;

public class DarkGreenFragment extends Fragment implements View.OnClickListener {

    private static final int PREV = 0;
    private static final int NEXT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dark_green, container, false);
        Button prev = (Button) view.findViewById(R.id.prev);
        prev.setTag(PREV);
        prev.setOnClickListener(this);
        Button next = (Button) view.findViewById(R.id.next);
        next.setTag(NEXT);
        next.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int type = (Integer) v.getTag();
        Intent interaction = new Intent(AppContract.BROADCAST_INTERACTION);
        switch(type) {
            case PREV:
                interaction.putExtra(AppContract.FRAGMENT, AppContract.LIGHT_GREEN);
                break;
            case NEXT:
                interaction.putExtra(AppContract.FRAGMENT, AppContract.LIGHT_GREEN);
                break;
            default:
                break;
        }

        LocalBroadcastManager.getInstance(this.getActivity()).sendBroadcast(interaction);
    }
}
