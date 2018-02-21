package com.example.genji.am016_sqlite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OperateActivity extends AppCompatActivity implements InputDialog.AddListener {

    /* OLD solution
    AlertDialog.Builder builder;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {

                /*
                 * Snackbar.make(view, "Add a product", Snackbar.LENGTH_LONG)
                 * .setAction("Action", null).show();
                 */

                /* OLD solution
                showInputDialog();
                */

                FragmentManager fm = getFragmentManager();
                InputDialog dialogFragment = new InputDialog();
                dialogFragment.show(fm, "MyInputDialog");
            }
        );
    }

    /* OLD Solution
    protected void showInputDialog() {

        builder = new AlertDialog.Builder(this);

        View viewAlert = getLayoutInflater().inflate(R.layout.dialog_add, null);
        final EditText editName = (EditText) viewAlert.findViewById(R.id.product_name);
        final EditText editDescription = (EditText) viewAlert.findViewById(R.id.product_description);

        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_product);
        builder.setView(viewAlert);
        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                Product product = new Product(name, description);
                ProductsDataSource pds = new ProductsDataSource(OperateActivity.this);
                // DB transaction
                pds.open();
                pds.createProduct(product);
                pds.close();
                // get FragmentManager in order to obtain FragmentList
                FragmentManager fm = OperateActivity.this.getFragmentManager();
                ProductsListFragment plf = (ProductsListFragment)fm.findFragmentById(R.id.fragment);
                // update list fragment
                ProductsArrayAdapter adapter = (ProductsArrayAdapter)plf.getListAdapter();
                adapter.add(product);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }
    */

    @Override
    public void addProduct(Product product){
        ProductsDataSource pds = new ProductsDataSource(this);
        // DB transaction
        pds.open();
        pds.createProduct(product);
        pds.close();
        // get FragmentManager in order to obtain FragmentList
        FragmentManager fm = getFragmentManager();
        ProductsListFragment plf = (ProductsListFragment)fm.findFragmentById(R.id.fragment);
        // update list fragment
        ProductsArrayAdapter adapter = (ProductsArrayAdapter)plf.getListAdapter();
        adapter.add(product);
        adapter.notifyDataSetChanged();

    }
}


