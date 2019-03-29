package com.amamipro.recyclerpost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class CommentActivity extends AppCompatActivity {
    private String urlcomment;
    private RecyclerView cView;
    private List<CommentModel> listCommentm = new ArrayList<>();
    CommentAdapter commentAdapter;
    private OkHttpClient client = new OkHttpClient();
    private String postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                postId = null;
            } else {
                postId = extras.getString("postId");
            }
        } else {
            postId = (String) savedInstanceState.getSerializable("postId");
        }

        urlcomment = "http://jsonplaceholder.typicode.com/posts/" + postId + "/comments";

        ambildata();
        cView = (RecyclerView) findViewById(R.id.recycler_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cView.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(this, listCommentm);
        cView.setAdapter(commentAdapter);
    }

    private void ambildata() {
        Request request = new Request.Builder()
                .url(urlcomment)
                .cacheControl(new CacheControl.Builder().noCache().build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ON FAILURE", e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
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
        });
    }

    public void parse(String strjson) {
        try {
            Log.d("STRJSON", strjson);
            JSONArray jsonarray = new JSONArray(strjson);
            for (int i = 0; i < jsonarray.length(); i++) {
                CommentModel comment = new CommentModel();
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String postID = jsonobject.getString("postId");
                if(postID.equals(postId)) {
                    String id = jsonobject.getString("id");
                    String name = jsonobject.getString("name");
                    String email = jsonobject.getString("email");
                    String body = jsonobject.getString("body");
                    comment.setPostId(postID);
                    comment.setId(id);
                    comment.setName(name);
                    comment.setEmail(email);
                    comment.setComment(body);
                    listCommentm.add(comment);
                }
            }
            commentAdapter.notifyDataSetChanged();
        } catch (Throwable t) {
            Log.e("My App ar", "Jseon orr: \"" + t + "\"");
        }

    }
}
