package com.iiuifyp.location.base.attendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iiuifyp.location.base.attendance.model.Admin;
import com.iiuifyp.location.base.attendance.model.Employee;
import com.iiuifyp.location.base.attendance.retrofit.RetrofitClient;
import com.iiuifyp.location.base.attendance.service.AdminLoginService;
import com.iiuifyp.location.base.attendance.service.EmployeeLoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edt_login_email, edt_login_password;
    int getCheck;
    Button btn_login;
    Admin admin;
    Employee employee;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getCheck = getIntent().getIntExtra("LOGINCHECK", 0);

        edt_login_email = findViewById(R.id.edt_login_email);
        edt_login_password = findViewById(R.id.edt_login_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    if(getCheck==1){
                        LoginADMIN();
                    }else if(getCheck==2){
                        LoginEmployee();
                    }
                }
            }
        });
    }

    private boolean validation() {
        boolean isvalid = true;
        String emailPattern;
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (edt_login_password.getText().toString().isEmpty())
        {
            edt_login_password.setError("fill this field");
            isvalid = false;
        }

        else  if (edt_login_email.getText().toString().isEmpty())
        {
            edt_login_email.setError("fill this field");
            isvalid = false;
        }
        else if (!edt_login_email.getText().toString().matches(emailPattern)) {
            edt_login_email.setError("Invalid email address");
            isvalid =false;
        }


        else{
            edt_login_email.setError(null);
            edt_login_password.setError(null);
            isvalid=true;
        }


        return isvalid;
    }


    private void LoginADMIN() {
        admin = new Admin();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging");
        progressDialog.setMessage("please wait");
        progressDialog.show();

        AdminLoginService service = RetrofitClient.getClient().create(AdminLoginService.class);
        Call<Admin> call = service.admin_login(edt_login_email.getText().toString(),
                edt_login_password.getText().toString());
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    admin=response.body();
                    if(!admin.isError()){
                        Toast.makeText(LoginActivity.this,
                                admin.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),
                                AdminHomePageActivity.class));
                        finishAffinity();
                    }else{
                        Toast.makeText(LoginActivity.this,
                                admin.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }









    private void LoginEmployee() {
        employee = new Employee();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging");
        progressDialog.setMessage("please wait");
        progressDialog.show();

        EmployeeLoginService service = RetrofitClient.getClient().create(EmployeeLoginService.class);
        Call<Employee> call = service.employee_login(edt_login_email.getText().toString(),
                edt_login_password.getText().toString());
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    employee=response.body();
                    if(!employee.isError()){
                        Toast.makeText(LoginActivity.this,
                                employee.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),
                                EmployeeHomeActivity.class));
                        finishAffinity();
                    }else{
                        Toast.makeText(LoginActivity.this,
                                employee.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

