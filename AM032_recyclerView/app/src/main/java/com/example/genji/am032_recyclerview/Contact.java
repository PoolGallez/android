package com.example.genji.am032_recyclerview;

import java.util.ArrayList;

/**
 * Created by genji on 3/31/16.
 */
public class Contact {

    private static int lastContactId = 0;

    private String name;
    private String email;
    private boolean online;

    public Contact(String name, String email, boolean online) {
        this.name = name;
        this.email = email;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isOnline() {
        return online;
    }



    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact("Person " + (++lastContactId), "meail" + +i + "@zappo.it", i <= numContacts / 2));
        }

        return contacts;
    }
}
