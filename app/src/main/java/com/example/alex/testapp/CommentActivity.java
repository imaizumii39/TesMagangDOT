package com.example.alex.testapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static CommentAdapter adapter;
    private static List<CommentData> data_list;
    private TextView txt_title, txt_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        txt_title=(TextView) findViewById(R.id.txt_title);
        txt_body=(TextView) findViewById(R.id.txt_body);
        data_list = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CommentAdapter(this,data_list);
        recyclerView.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            txt_title.setText(extras.getString("title"));
            txt_body.setText(extras.getString("body"));
            MyTask myTask = new MyTask(Integer.parseInt(extras.getString("id")));
            myTask.execute();
        }


    }

    private static class MyTask extends AsyncTask<Void, Void, String> {
        int id;
        MyTask(int id){
            this.id = id;
        }
        @Override
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();

        }
        @Override
        protected String doInBackground(Void... voids) {
            String urlSearch ="http://jsonplaceholder.typicode.com/posts/"+id+"/comments" ;
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
                    CommentData data = new CommentData(object.getString("name"),object.getString("email"),object.getString("body"),object.getInt("postId"));
                    System.out.println(data);
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
}
