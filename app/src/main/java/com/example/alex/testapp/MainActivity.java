package com.example.alex.testapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static PostAdapter adapter;
    private static List<PostData> data_list;
    private Button btn_retrieve;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        btn_retrieve=(Button) findViewById(R.id.btn_retrieve);
        data_list = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PostAdapter(this,data_list);
        recyclerView.setAdapter(adapter);


        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask myTask = new MyTask();
                if (checkAndRequestPermissions()){
                    myTask.execute();
                }
            }
        });


    }

    private static class MyTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();

        }
        @Override
        protected String doInBackground(Void... voids) {
            String urlSearch ="http://jsonplaceholder.typicode.com/posts" ;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    .url(urlSearch)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String bodyString = response.body().string();
                JSONArray array = new JSONArray(bodyString);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    PostData data = new PostData(object.getString("title"),object.getString("body"),object.getInt("id"));
                    data_list.add(data);
                }

            } catch (JSONException ex) {
                System.out.println("That's All");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private  boolean checkAndRequestPermissions() {
        int permissionInternet = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionInternet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
