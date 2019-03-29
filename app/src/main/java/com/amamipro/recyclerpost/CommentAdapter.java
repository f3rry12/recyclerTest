package com.amamipro.recyclerpost;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<CommentModel> listComment;
    private Activity activity;

    public CommentAdapter(Activity activity, List<CommentModel> listComment) {
        this.listComment = listComment;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_listcomment, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(listComment.get(i).getName());
        viewHolder.email.setText(listComment.get(i).getEmail());
        viewHolder.komentar.setText(listComment.get(i).getComment());
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private TextView komentar;

        public ViewHolder(View view) {
            super(view);
            name=(TextView)view.findViewById(R.id.name);
            email=(TextView)view.findViewById(R.id.email);
            komentar=(TextView)view.findViewById(R.id.comment_body);
        }
    }
}
