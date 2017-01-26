package com.example.kolin.testgglads.presentation.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kolin on 25.01.2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Post> postList = new ArrayList<>();
    private Picasso picasso;

    private OnSelectedItemListener listener;

    public interface OnSelectedItemListener {
        void onSelectedItem(Post selectedPost);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        picasso = Picasso.with(parent.getContext());
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textUpVotes.setText(String.valueOf(post.getVotesCount()));
        holder.textAbout.setText(post.getTagLine());
        holder.textName.setText(post.getName());
        picasso.load(post.getThumbnailUrl())
                .placeholder(R.color.colorWhite)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textName;
        private TextView textAbout;
        private TextView textUpVotes;
        private CircleImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.list_item_primary_text);
            textAbout = (TextView) itemView.findViewById(R.id.list_item_support_text);
            textUpVotes = (TextView) itemView.findViewById(R.id.list_item_votes);

            imageView = (CircleImageView) itemView.findViewById(R.id.list_item_thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=  null){
                        listener.onSelectedItem(postList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public void clear(){
        postList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> postList){
        this.postList.clear();
        this.postList.addAll(postList);
        notifyDataSetChanged();
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setListener(OnSelectedItemListener listener) {
        this.listener = listener;
    }
}
