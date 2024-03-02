package com.iiuifyp.location.base.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewAllEmployeeActivity extends AppCompatActivity {
    Button btn_add_employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
        btn_add_employee= findViewById(R.id.btn_add_employee);
        btn_add_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ViewAllEmployeeActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }
}