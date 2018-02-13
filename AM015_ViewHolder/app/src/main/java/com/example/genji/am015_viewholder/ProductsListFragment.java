package com.example.genji.am015_viewholder;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by genji on 4/2/16.
 */
public class ProductsListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // initialize products array

        products = new ArrayList<>(Arrays.asList(Product.products));

        ProductsArrayAdapter adapter = new ProductsArrayAdapter(getActivity(), products);
        //use this below for a correct initialization
        setListAdapter(adapter);

        // connect to
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position + "\n name: " + (products.get(position)).getName() + "\n" +
                "description: " + (products.get(position)).getDescription(), Toast.LENGTH_SHORT).show();
    }
}