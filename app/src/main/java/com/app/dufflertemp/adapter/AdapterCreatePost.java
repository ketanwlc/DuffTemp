package com.app.dufflertemp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.dufflertemp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;


/**
 * Created by Ketan on 18/4/17.
 */

public class AdapterCreatePost extends RecyclerView.Adapter<AdapterCreatePost.MyHolder> {

    Context mContext;
    ArrayList<Uri> arrayList;

   public AdapterCreatePost(Context mContext, ArrayList<Uri> arrayList)
   {
       this.mContext=mContext;
       this.arrayList=arrayList;
   }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_post,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        //Picasso.with(mContext).load(arrayList.get(position)).into(holder.img_create_post);

        Target t = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.img_create_post.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // Toast.makeText(getActivity(), "Problem to load image", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.with(mContext).load(arrayList.get(position)).placeholder(R.color.colorPrimary).into(t);
        holder.img_create_post.setTag(t);


        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(position);

                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView img_create_post;
        ImageView img_close;

        public MyHolder(View itemView) {
            super(itemView);

            img_create_post = (ImageView) itemView.findViewById(R.id.img_create_post);
            img_close = (ImageView) itemView.findViewById(R.id.img_close);

        }
    }

}
