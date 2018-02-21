package com.example.genji.am016_sqlite;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by genji on 2/9/16.
 */
public class ProductsListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private ProductsDataSource pds;
    private ArrayList<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // DB transaction
        pds = new ProductsDataSource(getActivity());
        pds.open();
        // every time the fragment is attached you initialize the list products
        // pds.initialize();
        products = pds.getAllProducts();
        pds.close();

        ProductsArrayAdapter adapter = new ProductsArrayAdapter(getActivity(), products);
        //use this below for a correct initialization
        setListAdapter(adapter);

        // connect to
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // *** NB these transactions should be locked ***
        // DB transaction
        Product product = (Product) products.get(position);
        pds.open();
        pds.deleteProduct(product.getName());
        pds.close();
        // FIRST Snackbar appear
        Snackbar.make(view, ((Product) products.get(position)).getName() + " deleted", Snackbar.LENGTH_LONG)
                .show();
        // THEN update list
        ProductsArrayAdapter adapter = (ProductsArrayAdapter)getListAdapter();
        adapter.remove(product);
        adapter.notifyDataSetChanged();
    }
}
