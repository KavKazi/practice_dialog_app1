package com.example.practice_dialog_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    SharedPreferences sp;
    EditText citeEditText,lastNameEditText,firstNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_datepicker,btn_save;
        Context context = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_datepicker = (Button) findViewById(R.id.btn_datePiker);
        btn_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        firstNameEditText=findViewById(R.id.firstNameEditText);
        lastNameEditText=findViewById(R.id.lastNameEditText);
        citeEditText= findViewById(R.id.citeEditText);
        sp = getSharedPreferences("details1",0);

        String strfirstNameEditText=sp.getString("firstNameEditText",null);
        String strLastNameEditText=sp.getString("lastNameEditText",null);
        String strCiteEditText=sp.getString("citeEditText",null);
        if (strfirstNameEditText!=null&&lastNameEditText!=null&&citeEditText!=null){
            firstNameEditText.setHint(strfirstNameEditText);
            lastNameEditText.setHint(strLastNameEditText);
            citeEditText.setHint(strCiteEditText);
        }


        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("are you shure you want to save all the data?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("firstNameEditText",strfirstNameEditText.toString());
                                editor.putString("lastNameEditText",strLastNameEditText.toString());
                                editor.putString("citeEditText",strCiteEditText.toString());
                                editor.commit();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        Button btn_datepicker = (Button) findViewById(R.id.btn_datePiker);
        btn_datepicker.setText(currentDateString);

    }
}