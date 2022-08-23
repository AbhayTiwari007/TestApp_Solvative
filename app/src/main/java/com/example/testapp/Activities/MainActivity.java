package com.example.testapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.Apis.RetrofitClient;
import com.example.testapp.Database.DbHelper;
import com.example.testapp.ModelsandAdapters.Adapter;
import com.example.testapp.ModelsandAdapters.DataModel;
import com.example.testapp.ModelsandAdapters.Datum;
import com.example.testapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<Datum> arrayList;
    RecyclerView recyclerView;
    Adapter adapter;
    ProgressBar progressBar;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainActivityRv);
        progressBar = findViewById(R.id.progressMain_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        dbHelper=new DbHelper(MainActivity.this);


        getData();
    }

    private void getData() {
        Call<DataModel> call = RetrofitClient.getInstance().getMyApi().getList();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    arrayList = new ArrayList<>();
                    arrayList.addAll(response.body().getData());
                    adapter = new Adapter(arrayList, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    dbHelper.deleteData();
                    for (int i = 0; i < arrayList.size(); i++) {
                        Log.d("Taggs", "first name:-" + arrayList.get(i).getFirstName());
                        dbHelper.setAllData(arrayList.get(i).getFirstName(),arrayList.get(i).getLastName(),arrayList.get(i).getEmail(),arrayList.get(i).getAvatar());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d("Taggs", "on failed ");
                Toast.makeText(MainActivity.this, "On fail method call", Toast.LENGTH_SHORT).show();

                arrayList = new ArrayList<>();
               arrayList.addAll( dbHelper.getAllData());
                adapter = new Adapter(arrayList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
//                dbHelper.deleteData();
                for (int i = 0; i < arrayList.size(); i++) {
                    Log.d("Taggs", "first name:-" + arrayList.get(i).getFirstName());
//                    dbHelper.setAllData(arrayList.get(i).getFirstName(),arrayList.get(i).getLastName(),arrayList.get(i).getEmail(),arrayList.get(i).getAvatar());
                }

            }
        });
    }
}