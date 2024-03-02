package com.iiuifyp.location.base.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomePageActivity extends AppCompatActivity {
    CardView card_manage_employee,card_manage_queries,card_manage_timetable,card_manage_leaves,card_manage_reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        card_manage_employee = findViewById(R.id.card_manage_employee);
        card_manage_queries=findViewById(R.id.card_manage_queries);
        card_manage_leaves=findViewById(R.id.card_manage_leaves);
        card_manage_timetable=findViewById(R.id.card_manage_timetable);
        card_manage_reports=findViewById(R.id.card_manage_reports);
        card_manage_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AdminHomePageActivity.this, ManageReportsActivity.class);
                startActivity(intent);
            }
        });
        card_manage_leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AdminHomePageActivity.this, ManageLeavesActivity.class);
                startActivity(intent);
            }
        });
        card_manage_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomePageActivity.this,ManageTimetableActivity.class);
                startActivity(intent);
            }
        });
        card_manage_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AdminHomePageActivity.this,ViewAllEmployeeActivity.class);
                startActivity(intent);
            }
        });
        card_manage_queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AdminHomePageActivity.this,ManageQueriesActivity.class);
                startActivity(intent);
            }
        });

    }
}