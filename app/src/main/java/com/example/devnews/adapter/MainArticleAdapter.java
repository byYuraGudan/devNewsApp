package com.example.devnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.devnews.R;
import com.example.devnews.activity.UserActivity;
import com.example.devnews.activity.WebActivity;
import com.example.devnews.model.Article;
import com.example.devnews.model.User;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {

    private List<Article> articleArrayList;
    private Context context;
    public MainArticleAdapter(List<Article> articleArrayList, Context context){
        this.articleArrayList = articleArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i ){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fragment_news,null);
        return new MainArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainArticleAdapter.ViewHolder viewHolder, final int position) {
        Article articleModel = getItem(position);
        User userModel =  articleModel.getUser();
        if (!TextUtils.isEmpty(articleModel.getTitle())){
            viewHolder.postTitle.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getDescription())){
            viewHolder.postDescription.setText(articleModel.getDescription());
        } else viewHolder.postDescription.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(userModel.getName())){
            viewHolder.userTitle.setText(userModel.getName());
        }
        if (!TextUtils.isEmpty(articleModel.getSlug())){
            viewHolder.postSlug.setText(articleModel.getSlug());
        }
        viewHolder.postLike.setText(String.valueOf(articleModel.getPositiveReactionsCount()));

        viewHolder.postComment.setText(String.valueOf(articleModel.getCommentsCount()));

        if (articleModel.getCoverImage() != null) {
            Glide.with(context)
                    .load(articleModel.getCoverImage())
                    .centerInside()
                    .dontAnimate().into(viewHolder.postImage);
        } else viewHolder.postImage.setVisibility(View.GONE);
        if (userModel.getProfileImage() != null){
            Glide.with(context)
                    .load(userModel.getProfileImage())
                    .circleCrop()
                    .dontAnimate().into(viewHolder.userImage);
        } else viewHolder.userImage.setVisibility(View.GONE);
        try {
            Log.d("date",articleModel.getPublishedTimestamp().toString());
            Date date1= null;
            date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(articleModel.getPublishedTimestamp().toString());
            viewHolder.postTimePublished.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date1));
        } catch (ParseException e) {
            e.printStackTrace();
            viewHolder.postTimePublished.setText(articleModel.getPublishedTimestamp().toString());
        }
        viewHolder.postLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Article article = getItem(position);
                if (!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent webActivity = new Intent(view.getContext(), WebActivity.class);
                    webActivity.putExtra("url",article.getUrl());
                    context.startActivity(webActivity);
                }
                Log.d("ll_root","OnItemClick");
            }
        });

        viewHolder.userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = getItem(position).getUser();
                if (!TextUtils.isEmpty(user.getUsername())){
                    Intent userActivity = new Intent(view.getContext(), UserActivity.class);
                    userActivity.putExtra(UserActivity.USER_URL,user.getUsername());
                    context.startActivity(userActivity);
                }
            }
        });
    }

    private Article getItem(int position) {
        return articleArrayList.get(position);
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

        private RelativeLayout postLayout;
        private RelativeLayout userLayout;

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
            postLayout = view.findViewById(R.id.item_fn_ll_root_post);
            userLayout = view.findViewById(R.id.item_fn_rl_root_user);
        }
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
}