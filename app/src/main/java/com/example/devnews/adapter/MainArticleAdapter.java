package com.example.devnews.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.devnews.R;
import com.example.devnews.model.Article;
import com.example.devnews.model.User;
import com.example.devnews.utils.OnRecyclerViewItemClickListener;

import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {
    private List<Article> articleArrayList;

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public MainArticleAdapter(List<Article> articleArrayList){
        this.articleArrayList = articleArrayList;
    }
    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i ){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fragment_news,null);
        return new MainArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainArticleAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        final User userModel = articleModel.getUser();
        if (!TextUtils.isEmpty(articleModel.getTitle())){
            viewHolder.postTitle.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getDescription())){
            viewHolder.postDescription.setText(articleModel.getDescription());
        }
        if (!TextUtils.isEmpty(userModel.getName())){
            viewHolder.userTitle.setText(userModel.getName());
        }
        if (!TextUtils.isEmpty(articleModel.getSlug())){
            viewHolder.postSlug.setText(articleModel.getSlug());
        }
        viewHolder.postLike.setText(String.valueOf(articleModel.getPositiveReactionsCount()));

        viewHolder.postComment.setText(String.valueOf(articleModel.getCommentsCount()));

        viewHolder.postTimePublished.setText(articleModel.getPublishedTimestamp());
        viewHolder.linearLayoutPost.setTag(articleModel);
//        Glide.with(viewHolder.itemView.getContext())
//                .load(articleModel.getCoverImage())
//                .into(viewHolder.postImage);
//
//        Glide.with(viewHolder.itemView.getContext())
//                .load(userModel.getProfileImage())
//                .into(viewHolder.userImage);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userTitle;
        private TextView postSlug;
        private ImageView userImage;
        private TextView postTitle;
        private TextView postDescription;
        private ImageView postImage;
        private TextView postLike;
        private TextView postTimePublished;
        private TextView postComment;

        private LinearLayout linearLayoutPost;

        ViewHolder(View view){
            super(view);

            postTitle = view.findViewById(R.id.item_fn_post_title);
            postDescription = view.findViewById(R.id.item_fn_post_description);
            postLike = view.findViewById(R.id.item_fn_like);
            postComment = view.findViewById(R.id.item_fn_comment);
            postImage = view.findViewById(R.id.item_fn_post_image);
            postTimePublished = view.findViewById(R.id.item_fn_time_published);
            userTitle = view.findViewById(R.id.item_fn_username);
            postSlug = view.findViewById(R.id.item_fn_post_slug);
            userImage = view.findViewById(R.id.item_fn_username_image);
            linearLayoutPost = view.findViewById(R.id.item_fn_ll_root);
            linearLayoutPost.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(onRecyclerViewItemClickListener != null){
                            onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(),view);
                    }
                }
            });





        }
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
