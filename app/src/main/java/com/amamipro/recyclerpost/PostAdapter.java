package com.amamipro.recyclerpost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<PostModel> listPost;
    private Activity activity;
    private Context context;

    public PostAdapter(Activity activity, List<PostModel> listPost, Context context) {
        this.listPost = listPost;
        this.activity = activity;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listpost,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.judul.setText(listPost.get(i).getTitle());
        viewHolder.isi.setText(listPost.get(i).getBody());
        viewHolder.tombolkomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CommentActivity.class)
                        .putExtra("postId", listPost.get(i).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView judul;
        private TextView isi;
        private Button tombolkomen;

        public ViewHolder(View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.post_judul);
            isi = itemView.findViewById(R.id.post_body);
            tombolkomen = itemView.findViewById(R.id.see_comment);
        }
    }


}
