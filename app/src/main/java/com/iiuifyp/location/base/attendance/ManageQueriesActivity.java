package com.iiuifyp.location.base.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.iiuifyp.location.base.attendance.adapter.QueryAdapter;
import com.iiuifyp.location.base.attendance.model.Query;
import com.iiuifyp.location.base.attendance.retrofit.RetrofitClient;
import com.iiuifyp.location.base.attendance.service.GetQueryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageQueriesActivity extends AppCompatActivity {

    Spinner choose_query_status;
    ListView lv_all_queries;
    String status_array[] = {"Pending", "In-Process", "Completed"};
    List<Query> queryList = new ArrayList<>();
    int code;
    QueryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_queries);
        choose_query_status = findViewById(R.id.choose_query_status);
        lv_all_queries = findViewById(R.id.lv_all_queries);
        SpinnerWork();
    }

    private void SpinnerWork() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageQueriesActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                status_array);
        choose_query_status.setAdapter(adapter);
        choose_query_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    getQueries("P");
                }else if(position==1){
                    getQueries("IP");
                }else if(position==2){
                    getQueries("C");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getQueries(String status) {
        if(queryList!=null){
            queryList.clear();
        }
        GetQueryService service = RetrofitClient.getClient().create(GetQueryService.class);
        Call<JsonObject> call = service.getQuery(status);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    code = response.code();
                    if (code == 200) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                            JSONArray jsonArray = jsonObject.getJSONArray("records");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);

                                queryList.add(new Query(
                                        data.getInt("q_id"),
                                        data.getString("q_title"),
                                        data.getString("q_description"),
                                        data.getString("q_created_DateTime"),
                                        data.getString("e_name")));

                            }

                            adapter=new QueryAdapter(queryList,ManageQueriesActivity.this);
                            lv_all_queries.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(ManageQueriesActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}