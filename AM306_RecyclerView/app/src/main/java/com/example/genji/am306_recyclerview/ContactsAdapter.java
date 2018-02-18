package com.example.genji.am306_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by genji on 2/17/18.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private static ArrayList<Contact> contacts;
    private static Context context;

    public ContactsAdapter(ArrayList<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }


    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Contact contact = contacts.get(position);

        // Set item views based on the data model
        TextView textView = holder.nameTextView;
        textView.setText(contact.getName());

        Button button = holder.messageButton;

        if (contact.isOnline()) {
            button.setText("Message");
            button.setEnabled(true);
        }
        else {
            button.setText("Offline");
            button.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contact_name);
            messageButton = itemView.findViewById(R.id.message_button);
            messageButton.setOnClickListener((View v) -> {
                int position = getLayoutPosition();
                Contact contact = contacts.get(position);
                Toast.makeText(context, contact.getEmail(), Toast.LENGTH_SHORT).show();
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
            Contact contact = contacts.get(position);
            Toast.makeText(context, contact.getName() + ": "+ position, Toast.LENGTH_SHORT).show();
        }

    }
}
