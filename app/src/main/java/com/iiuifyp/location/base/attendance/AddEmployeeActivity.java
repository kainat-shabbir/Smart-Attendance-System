package com.iiuifyp.location.base.attendance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iiuifyp.location.base.attendance.model.Employee;
import com.iiuifyp.location.base.attendance.retrofit.RetrofitClient;
import com.iiuifyp.location.base.attendance.service.AddEmployeeService;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    ImageView employee_img;
    EditText employee_name, employee_password, employee_email, employee_phoneNo, employee_salary;
    RadioButton radio_male, radio_female;
    Spinner designation_spinner;
    Button add_employee_btn;
    File file;
    //    File file;
    Uri filePath;
    ProgressDialog progressDialog;
    Employee employee;
    String getdesignation;
    int get_gender;
    /*    Intent intent;*/
    String designationArray[] = {"Select Designation", "Teacher", "Security Guard", "Office Boy", "Clerks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        employee_img = findViewById(R.id.employee_img);
        employee_name = findViewById(R.id.employee_name);
        employee_password = findViewById(R.id.employee_password);
        employee_email = findViewById(R.id.employee_email);
        employee_phoneNo = findViewById(R.id.employee_phoneNo);
        employee_salary = findViewById(R.id.employee_salary);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        designation_spinner = findViewById(R.id.designation_spinner);
        add_employee_btn = findViewById(R.id.add_employee_btn);

        /*    intent = getIntent();*/

        Dexter.withActivity(AddEmployeeActivity.this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        employee_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogue();
                            }


                        });
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();

        ShowSpinner();


        add_employee_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if all fields are fill then run this method
                CreateEmployee();

                if (validation())
                //open next activity
                {
                }
            }
        });

    }

    private boolean validation() {
        boolean isvalid = true;
        String emailPattern;
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (employee_name.getText().toString().isEmpty()) {
            employee_name.setError("fill this field");
            isvalid = false;

        } else if (employee_password.getText().toString().isEmpty()) {
            employee_password.setError("fill this field");
            isvalid = false;
        }

        else if (employee_email.getText().toString().isEmpty()){
            employee_email.setError("fill this field");
            isvalid = false;
        }

        else if (!employee_email.getText().toString().matches(emailPattern)) {
            employee_email
                    .setError("Invalid email address");
            isvalid =false;
        }


        else if (employee_phoneNo.getText().toString().isEmpty()){

            employee_phoneNo.setError("fill this field");
            isvalid = false;
        }

      else if (employee_salary.getText().toString().isEmpty()){
            employee_salary.setError("fill this field");
            isvalid = false;


        }

        else if (!(radio_male.isChecked()) && !(radio_female.isChecked()))
        {
            radio_female.setError("this field is required to check");
            radio_male.setError("this field is required to check");
            isvalid = false;
        }


       else {
            employee_name.setError(null);
            employee_password.setError(null);
            employee_email.setError(null);
            employee_phoneNo.setError(null);
            employee_salary.setError(null);
            radio_female.setError(null);
        }

        return isvalid;
    }

    private void ShowSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEmployeeActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                designationArray);
        designation_spinner.setAdapter(adapter);
        designation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getdesignation = designation_spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showDialogue() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialogue, null);
        Button ok_btn = view.findViewById(R.id.ok_btn);
        Button cancel_button = view.findViewById(R.id.cancel_button);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).create();

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_CODE);
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing
                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    if (employee_img != null) {
                        employee_img.setBackground(null);
                    }
                    Uri selectedImage = data.getData();
                    try {
                        filePath = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        employee_img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                    break;


            }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void CreateEmployee() {
        employee = new Employee();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (filePath == null) {
            Toast.makeText(this,
                    "no image found", Toast.LENGTH_SHORT).show();
        } else {
            try {
                file = new File(getFilePath(AddEmployeeActivity.this, filePath));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            RequestBody requestfile = RequestBody.create(MediaType.parse("image/*"), file);

            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), employee_name.getText().toString());

            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), employee_email.getText().toString());

            RequestBody password = RequestBody.create(MediaType.parse("text/plain"), employee_password.getText().toString());

            RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), employee_phoneNo.getText().toString());

            RequestBody salary = RequestBody.create(MediaType.parse("text/plain"), employee_salary.getText().toString());

            RequestBody designation = RequestBody.create(MediaType.parse("text/plain"), getdesignation);

            RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1));

            MultipartBody.Part body = MultipartBody.Part.createFormData("e_profile_img", file.getName(), requestfile);

            AddEmployeeService service = RetrofitClient.getClient().create(AddEmployeeService.class);
            Call<Employee> call = service.add_employee(name, email, password, phone, salary, designation,
                    gender, body);
            call.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    if (response.isSuccessful()) {
                        employee = response.body();
                        if (!employee.isError()) {
                            Toast.makeText(AddEmployeeActivity.this,
                                    employee.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(AddEmployeeActivity.this,
                                    employee.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(AddEmployeeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {


            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
