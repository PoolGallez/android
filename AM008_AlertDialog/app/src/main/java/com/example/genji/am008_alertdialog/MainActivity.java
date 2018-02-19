package com.example.genji.am008_alertdialog;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        AlertDialog.Builder builder;
        FragmentManager manager = getFragmentManager();

        DialogInterface.OnClickListener listener_OK = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Pressed OK", Toast.LENGTH_SHORT).show();
            }
        };

        DialogInterface.OnMultiChoiceClickListener listener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                Toast.makeText(MainActivity.this, i + " is " + b, Toast.LENGTH_SHORT).show();
            }
        };

        switch (view.getId()) {
            case R.id.date_picker:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(manager, "date_picker");
                break;
            case R.id.time_picker:
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(manager, "time_picker");
                break;
            case R.id.compat_1:
                builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
                builder.setTitle("AppCompatDialog 01");
                builder.setMessage("Lorem ipsum dolor...");
                builder.setPositiveButton("OK", listener_OK);
                builder.setNegativeButton("Cancel", null);
                builder.show();
                break;
            case R.id.compat_2:
                CharSequence[] choices = {"uno", "due", "tre"};
                boolean[] checkedChoices = {true, false, true};
                builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
                builder.setTitle("Choose something");
                builder.setPositiveButton("zap", null);
                builder.setMultiChoiceItems(choices, checkedChoices, listener);
                // builder.setItems(choices, null);
                // here below you can proceed as in the previous case
                AppCompatDialog alert = builder.create();
                alert.show();
                break;
            case R.id.compat_3:
                builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
                // the trick here is ew ContextThemeWrapper(this, R.style.MyAlertDialogStyle)
                // it must be final
                final EditText etNickName = new EditText(new ContextThemeWrapper(this, R.style.MyAlertDialogStyle));
                // adda a view
                builder.setView(etNickName);
                builder.setTitle(R.string.custom_dialog);
                final String username = "Ciccio";
                final String roomName = "happy room";
                builder.setMessage(username + " has invited you to join " + roomName + " for lots of fun chatting");
                builder.setPositiveButton("Enter user name", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(etNickName.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this, "Enter a user name", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("MyTAG", "You have entered: " + etNickName.getText().toString());
                            TextView text = MainActivity.this.findViewById(R.id.textView);
                            text.setText("You have entered: " + etNickName.getText().toString());
                        }
                    }
                });
                builder.show();
                break;
            case R.id.compat_4:
                View viewAlert = getLayoutInflater().inflate(R.layout.custom, null);
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.custom2);
                builder.setView(viewAlert);
                builder.setNeutralButton("zap", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "neutral button is n: " + i , Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.dfragment_3:

                int style, theme;
                String sStyle, sTheme;
                sStyle = ((EditText)this.findViewById(R.id.style)).getText().toString();
                sTheme = ((EditText)this.findViewById(R.id.theme)).getText().toString();
                style = (sStyle == null) ? 0 : Integer.parseInt(sStyle);
                theme = (sTheme == null) ? 0 : Integer.parseInt(sTheme);

                FragmentTransaction ft = manager.beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                DialogFragment newFragment = MyDFragment01.newInstance(style, theme);
                newFragment.show(ft, "dialog");

        }
    }

}
