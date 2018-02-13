package com.example.genji.am015_viewholder;

/**
 * Created by genji on 4/2/16.
 */
public class Product {

    private String name;
    private String description;

    public Product(String name, String description){
        this.name = name;
        this.description = description;
    }

    String getName(){
        return name;
    }

    String getDescription(){
        return description;
    }

    public static Product[] products = {
        new Product("gioppini", "panetti sfiziosi"),
        new Product("jambonetti", "salatini al prosciutto"),
        new Product("patatine sfizione", "patatine salate ed aromatizzate"),
        new Product("tarallini", "anelli di pane"),
        new Product("gallette", "gallette plus"),
        new Product("frollini plus", "biscotti all'uovo"),
        new Product("cioccolini", "frollini con gocce di cioccolata"),
        new Product("secchini", "biscotti secchi"),
        new Product("grissinini", "grissini piccoli e sottili"),
        new Product("patasplash", "le patatine da bordo piscina"),
        new Product("majopatas", "le patatine aromatizzate .."),
        new Product("crocchette al sesamo", "panetti al sesamo"),
        new Product("crocchette alla pancetta", "panetti alla pancetta"),
        new Product("biscotti al miglio e avena", "i biscotti cinguettanti")
        };


}
