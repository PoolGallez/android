# AM306_RecyclerView

Prima di iniziare suggeriamo di dare uno sguardo a
> Support Library Package 

[qui](https://developer.android.com/topic/libraries/support-library/packages.html) la documentazione.

Useremo per gli item delle `CardView` estende `FrameLayout` che come ben sappiamo contiene un solo elemento e quindi, tipicamente un altro layout; progettato quindi il list item si procede in modo easy. 

RecyclerView è un oggetto molto flessibile, a differenze di una ListView possiamo organizzare il materiale non solo verticalmente infatti `RecyclerView.LayoutManager` può essere un linear layout come nel nostro caso
```
 rv.setLayoutManager(new LinearLayoutManager(this));
```
od un grid layout.

## Adapter

Questa decisamente è la classe più interessante
```
RecyclerView.Adapter
```
che viene dichiarato con il generic
```
RecyclerView.ViewHolder
```
Nel nostro caso
```
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private static ArrayList<Contact> contacts;
    private static Context context;

    public ContactsAdapter(ArrayList<Contact> contacts, Context context) {
        ...
    }


    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ...
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Contact contact = contacts.get(position);

        // Set item views based on the data model
        ...

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder 
        implements View.OnClickListener{

        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contact_name);
            messageButton = itemView.findViewById(R.id.message_button);
            messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    Contact contact = contacts.get(position);
                    Toast.makeText(context, contact.getEmail(), Toast.LENGTH_SHORT).show();

                }
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
```
Invitiamo a studiare le API ed il metodo `getLayoutPosition()` [qui](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html#getLayoutPosition()).