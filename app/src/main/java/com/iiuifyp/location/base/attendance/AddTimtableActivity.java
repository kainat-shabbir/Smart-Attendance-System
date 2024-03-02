package com.iiuifyp.location.base.attendance;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddTimtableActivity extends AppCompatActivity {
    Button btn_set_time_1, btn_set_time_2;
    TimePickerDialog timePickerDialog;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);
        btn_set_time_1 = findViewById(R.id.btn_set_time_1);
        btn_set_time_2 = findViewById(R.id.btn_set_time_2);
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        btn_set_time_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            btn_set_time_1.setText(hourOfDay + ":" + minute);
                        }
                    },hour, minute, android.text.format.DateFormat.is24HourFormat(context));
                    timePickerDialog.show();
            }
        });

        btn_set_time_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btn_set_time_2.setText(hourOfDay + ":" + minute);
                    }
                },hour, minute, android.text.format.DateFormat.is24HourFormat(context));
                timePickerDialog.show();

            }
        });


    }
}
