package com.amamipro.recyclerpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static String urlpost = "http://jsonplaceholder.typicode.com/posts";
    private List<PostModel> listPostm = new ArrayList<PostModel>();

    private RecyclerView lView;
    PostAdapter adapter;
    private String responseBody;
    private JSONObject obj;
    private OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.);

        ambildata();
        lView = (RecyclerView) findViewById(R.id.recycler_post);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lView.setLayoutManager(layoutManager);
        adapter = new PostAdapter(this, listPostm,getApplicationContext());
        lView.setAdapter(adapter);

    }

    private void ambildata(){
        Request request = new Request.Builder()
                .url(urlpost)
                .cacheControl(new CacheControl.Builder().noCache().build())
                .build();

        client.newCall(request).enqueue((new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ON FAILURE",e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    throw new IOException("(salah jsonnya)Unexpected Code " + response);
                } else {
                    final String hasil = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parse(hasil);
                        }
                    });
                }
            }
        }));
    }

    public void parse(String strjson){
        try {
            Log.d("STRJSON",strjson);
            JSONArray jsonarray = new JSONArray(strjson);
            for (int i = 0; i < jsonarray.length(); i++) {
                PostModel post = new PostModel();
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String userId = jsonobject.getString("userId");
                String id = jsonobject.getString("id");
                String title = jsonobject.getString("title");
                String body = jsonobject.getString("body");
                post.setUserId(userId);
                post.setId(id);
                post.setTitle(title);
                post.setBody(body);
                listPostm.add(post);

            }
            adapter.notifyDataSetChanged();
        } catch (Throwable t) {
            Log.e("My App ar", "Jseon orr: \"" + t + "\"");
        }
        }

}

