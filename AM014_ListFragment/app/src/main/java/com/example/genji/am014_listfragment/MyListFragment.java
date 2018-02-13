package com.example.genji.am014_listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by genji on 1/31/16. Updated on 11/03/2017
 */
public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // create the adapter
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.products, android.R.layout.test_list_item);
        // connect the adapter
        setListAdapter(adapter);
        // connect the listener
        getListView().setOnItemClickListener(this);
        
        /** without R.array.products
         * 
         * String[] products = new String[] { ... };
         * ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
         * android.R.layout.simple_list_item_1, values);
         * setListAdapter(adapter);
         * getListView().setOnItemClickListener(this);
         */
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
