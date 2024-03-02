package com.iiuifyp.location.base.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CategoryActivity extends AppCompatActivity {

    CardView card_admin_login, card_user_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        card_admin_login = findViewById(R.id.card_admin_login);
        card_user_login = findViewById(R.id.card_user_login);

        card_admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this,
                        LoginActivity.class);
                intent.putExtra("LOGINCHECK", 1);
                startActivity(intent);
            }
        });

        card_user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this,
                        LoginActivity.class);
                intent.putExtra("LOGINCHECK", 2);
                startActivity(intent);
            }
        });

    }
}
