package com.example.genji.am015_viewholder;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by genji on 4/2/16. Updated on 12/02/2018
 */
public class ProductsArrayAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    // the products arrayList
    private ArrayList<Product> products;

    private static final String TAG = "ViewHolder";

    // this object will be tag
    static class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView description;
    }

    public ProductsArrayAdapter(Activity context, ArrayList products) {
        super(context, R.layout.row_layout, products);
        // this also work
        // super(context, 0, products);
        this.context = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;

        Product p = products.get(position);

        // reuse view: ViewHolder pattern
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_layout, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.name = rowView.findViewById(R.id.name);
            viewHolder.description = rowView.findViewById(R.id.description);
            viewHolder.image = rowView.findViewById(R.id.icon);
            // take memory of the view
            rowView.setTag(viewHolder);
            // Log steTag()
            Log.d(TAG, "setTag() for object in position: " + position + "; product name: " + p.getName());
        } else {
            // reuse the object
            viewHolder = (ViewHolder) rowView.getTag();
            // Log steTag()
            Log.d(TAG, "getTag() for object in position: " + position);
        }
        viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        viewHolder.name.setText(p.getName());
        viewHolder.description.setText(p.getDescription());
        return rowView;
    }
}

