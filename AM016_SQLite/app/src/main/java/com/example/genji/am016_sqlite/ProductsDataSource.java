package com.example.genji.am016_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by genji on 2/9/16.
 */
public class ProductsDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,  MySQLiteHelper.COLUMN_DESCRIPTION};

    public ProductsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /* Create and/or open a database that will be used for reading and writing.
     * The first time this is called, the database will be opened and onCreate(SQLiteDatabase),
     * onUpgrade(SQLiteDatabase, int, int) and/or
     * onOpen(SQLiteDatabase) will be called.
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /* Close any open database object.
     */
    public void close() {
        dbHelper.close();
    }

    public Product createProduct(Product product) {
        // a sort of bundle ....
        ContentValues values = new ContentValues();
        // we can put Float, Integer, String and Byte
        values.put(MySQLiteHelper.COLUMN_NAME, product.getName());
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, product.getDescription());
        /* nullColumnHack = null
         * provides the name of nullable column name to explicitly insert a NULL
         */
        long insertId = database.insert(MySQLiteHelper.TABLE_PRODUCTS, null,
                values);
        /* parameters
         *  -> String tableName
         *  -> String[] columns
         *  -> selection: an SQL WHERE clause es "_id = 1"
         *  -> whereArgs: you can use ?s
         *  -> having
         *  -> order by
         *  -> limit
         */
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Product newProduct = cursorToProduct(cursor);
        cursor.close();
        return newProduct;
    }

    public void deleteProduct(Product product) {
        long id = product.getId();
        System.out.println("Product deleted with id: " + id);
        /*
         *  -> String tableName
         *  -> String[] columns
         *  -> whereArgs: you can use ?s
         */
        database.delete(MySQLiteHelper.TABLE_PRODUCTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void deleteProduct(String name) {
        System.out.println("Product deleted with name: " + name);
        /*
         *  -> String tableName
         *  -> String[] columns
         *  -> whereArgs: you can use ?s
         */
        database.delete(MySQLiteHelper.TABLE_PRODUCTS, MySQLiteHelper.COLUMN_NAME
                + " = \"" + name + "\"", null);
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return products;
    }

    private Product cursorToProduct(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getLong(0));
        product.setName(cursor.getString(1));
        product.setDescription(cursor.getString(2));
        return product;
    }

    //initialize DB
    public void initialize(){
        ArrayList<Product> products = new ArrayList<>();
        // add products
        products.add(new Product("gioppini", "panetti sfiziosi"));
        products.add(new Product("jambonetti", "salatini al prosciutto"));
        products.add(new Product("patatine sfizione", "patatine salate ed aromatizzate"));
        products.add(new Product("tarallini", "anelli di pane"));
        products.add(new Product("gallette", "gallette plus"));
        products.add(new Product("frollini plus", "biscotti all'uovo"));
        products.add(new Product("cioccolini", "frollini con gocce di cioccolata"));
        products.add(new Product("secchini", "biscotti secchi"));
        products.add(new Product("grissinini", "grissini piccoli e sottili"));
        products.add(new Product("patasplash", "le patatine da bordo piscina"));
        products.add(new Product("majopatas", "le patatine aromatizzate .."));
        products.add(new Product("crocchette al sesamo", "panetti al sesamo"));
        products.add(new Product("crocchette alla pancetta", "panetti alla pancetta"));
        products.add(new Product("biscotti al miglio e avena", "i biscotti cinguettanti"));

        for(Product product : products){
            createProduct(product);
        }
    }

    public void deleteAll() {
        database.delete(MySQLiteHelper.TABLE_PRODUCTS,
                null, null);
    }
}
