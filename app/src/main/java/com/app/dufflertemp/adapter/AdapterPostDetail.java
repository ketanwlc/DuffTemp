package com.app.dufflertemp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.dufflertemp.Common;
import com.app.dufflertemp.PostDetailActivity;
import com.app.dufflertemp.R;
import com.app.dufflertemp.model.ModelPostList;
import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by Ketan on 18/4/17.
 */

public class AdapterPostDetail extends RecyclerView.Adapter<AdapterPostDetail.MyHolder> {

    Context mContext;
    List<String> arrayList;

   public AdapterPostDetail(Context mContext, List<String> arrayList)
   {
       this.mContext=mContext;
       this.arrayList=arrayList;
   }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_detail,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        final String str_url = arrayList.get(position);

        if(!str_url.isEmpty()){

            Target t = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.img_big.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    // Toast.makeText(getActivity(), "Problem to load image", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };

            holder.img_big.setTag(t);

            Picasso.with(mContext).load(str_url).placeholder(R.color.colorPrimary).into(t);

        }else {
            holder.img_big.setImageResource(R.drawable.profile);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView img_big;

        public MyHolder(View itemView) {
            super(itemView);

            img_big = (ImageView) itemView.findViewById(R.id.img_big);
        }
    }

}
