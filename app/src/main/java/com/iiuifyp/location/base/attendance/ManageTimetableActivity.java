package com.iiuifyp.location.base.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageTimetableActivity extends AppCompatActivity {

    Button btn_add_timetable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_timetable);
        btn_add_timetable = findViewById(R.id.btn_add_timetable);
        btn_add_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageTimetableActivity.this, AddTimtableActivity.class);
                startActivity(intent);
            }
        });
    }
}