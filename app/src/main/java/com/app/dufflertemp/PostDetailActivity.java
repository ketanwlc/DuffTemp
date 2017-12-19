package com.app.dufflertemp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.dufflertemp.adapter.AdapterPostDetail;
import com.app.dufflertemp.model.ModelPostList;
import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nikunj on 19/12/17.
 */

public class PostDetailActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    IconTextView iconBack;
    @BindView(R.id.tv_title)
    TextView tv_Title;
    @BindView(R.id.icon_switch)
    IconTextView iconSwitch;
    @BindView(R.id.toolbar_post)
    Toolbar toolbarPost;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_duffler_desc)
    TextView tvDufflerDesc;
    @BindView(R.id.recycle_view_post_detail)
    RecyclerView recycleViewPostDetail;


    ModelPostList.Root model;

    List<String> photoUrl;
    AdapterPostDetail adapterPostDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail_activity);
        ButterKnife.bind(this);

        recycleViewPostDetail.setLayoutManager(new LinearLayoutManager(this));
        //recycle_view.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recycleViewPostDetail.setHasFixedSize(true);
        recycleViewPostDetail.setItemViewCacheSize(20);
        recycleViewPostDetail.setDrawingCacheEnabled(true);

        if(getIntent().getExtras().containsKey("post_detail_key")){
            Gson gson = new Gson();
            String str = getIntent().getStringExtra("post_detail_key");
            model = gson.fromJson(str, ModelPostList.Root.class);
        }

        if(model==null){
            return;
        }

        tv_Title.setText("Post details");

        tvDufflerDesc.setText(model.getDescription());

        tv_name.setText("@" + model.getUsername());

        tvLocation.setText(model.getLatitude() + "," + model.getLongitude());

        photoUrl = new ArrayList<>();

        photoUrl = Arrays.asList(model.getPostPhotos().split(","));

        adapterPostDetail = new AdapterPostDetail(this, photoUrl);
        recycleViewPostDetail.setAdapter(adapterPostDetail);

        if(!model.getUserAvatar().isEmpty()){

            Target t = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageView.setImageBitmap(Common.getCircleBitmap(bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    // Toast.makeText(getActivity(), "Problem to load image", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };

            imageView.setTag(t);

            Picasso.with(this).load(model.getUserAvatar()).placeholder(R.color.colorPrimary).into(t);

        }else {
            imageView.setImageResource(R.drawable.profile);
        }

    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
