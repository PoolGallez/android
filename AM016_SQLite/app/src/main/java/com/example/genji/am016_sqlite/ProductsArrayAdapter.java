package com.example.genji.am016_sqlite;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by genji on 2/9/16.
 */
public class ProductsArrayAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    // the products arrayList
    private ArrayList<Product> products;

    // this object will be tag
    static class ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView description;
    }

    public ProductsArrayAdapter(Activity context, ArrayList<Product> products) {
        super(context, R.layout.rowlayout, products);
        // alternative
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
            rowView = inflater.inflate(R.layout.rowlayout, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.name = rowView.findViewById(R.id.name);
            viewHolder.description = rowView.findViewById(R.id.description);
            viewHolder.image = rowView
                    .findViewById(R.id.icon);
            // take memory of the view
            rowView.setTag(viewHolder);
        } else {
            // reuse the object
            viewHolder = (ViewHolder) rowView.getTag();
        }
        viewHolder.image.setImageResource(R.mipmap.product);
        viewHolder.name.setText(p.getName());
        viewHolder.description.setText(p.getDescription());
        return rowView;
    }
}
