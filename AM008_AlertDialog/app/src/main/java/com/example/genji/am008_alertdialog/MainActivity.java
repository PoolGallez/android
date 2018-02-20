package com.example.genji.am008_alertdialog;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
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
                builder = getBuilder(1, this, R.style.MyAlertDialogStyle);
                builder.show();
                break;
            case R.id.compat_2:
                builder = getBuilder(2, this, R.style.MyAlertDialogStyle);
                AppCompatDialog alert = builder.create();
                alert.show();
                break;
            case R.id.compat_3:
                builder = getBuilder(3, this, R.style.MyAlertDialogStyle);
                builder.show();
                break;
            case R.id.dialog_fragment:
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


    // factory method for builder
    public static AlertDialog.Builder getBuilder(int i, Context context, int theme){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, theme);
        switch(i){
            case 1:
                builder.setTitle("AppCompatDialog 01");
                builder.setMessage("Lorem ipsum dolor...");
                builder.setPositiveButton("OK", (DialogInterface dialogInterface, int position) ->
                        Toast.makeText(context, "Pressed OK", Toast.LENGTH_SHORT).show()
                );
                builder.setNegativeButton("Cancel", null);
                return builder;
            case 2:
                builder = new AlertDialog.Builder(context, theme);
                CharSequence[] choices = {"uno", "due", "tre"};
                boolean[] checkedChoices = {true, false, true};
                builder.setTitle("Choose something");
                builder.setMultiChoiceItems(choices, checkedChoices,
                        (DialogInterface dialogInterface, int position, boolean b) ->
                                Toast.makeText(context, position + " is " + b, Toast.LENGTH_SHORT).show()
                );
                builder.setPositiveButton("zap", null);
                return builder;
            case 3:
                LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View viewAlert = layoutInflater.inflate(R.layout.custom, null);
                builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.custom2);
                builder.setView(viewAlert);
                builder.setNeutralButton("do", (DialogInterface dialogInterface, int position) ->
                        Toast.makeText(context, "neutral button is n: " + position , Toast.LENGTH_SHORT).show()
                );
                return builder;
            default:
                // it has never happened
                return  builder;
        }
    }
}
