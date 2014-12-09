package de.jsauerwein.mvctemplate.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.jsauerwein.mvctemplate.R;

public class DrawerAdapter<T> extends ArrayAdapter {
    private Object[] objects;
    private LayoutInflater inflater;
    int resource;

    public DrawerAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        this.objects = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            rowView = inflater.inflate(this.resource, null);
            // prepare view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.drawer_title);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        String title = (String) this.objects[position];
        viewHolder.title.setText(title);
        viewHolder.id = (String)this.objects[position];

        return rowView;
    }

    static class ViewHolder {
        TextView title;
        String id;
    }
}
