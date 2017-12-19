package com.app.dufflertemp.adapter;

import android.content.Context;
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
import com.app.dufflertemp.R;
import com.app.dufflertemp.model.ModelPostList;
import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ketan on 18/4/17.
 */

public class AdapterPostList extends RecyclerView.Adapter<AdapterPostList.MyHolder> {

    Context mContext;
    //ArrayList<ModelYourProfession1> arrayList;
    public static int previous_position=-1;
    int pos;
    List<ModelPostList.Root> arrayList;

   public AdapterPostList(Context mContext, List<ModelPostList.Root> arrayList)
   {
       this.mContext=mContext;
       this.arrayList=arrayList;
   }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        ModelPostList.Root model = arrayList.get(position);

        if(!model.getUserAvatar().isEmpty()){

            Target t = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.img_avatar.setImageBitmap(Common.getCircleBitmap(bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    // Toast.makeText(getActivity(), "Problem to load image", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };

            holder.img_avatar.setTag(t);

            Picasso.with(mContext).load(model.getUserAvatar()).placeholder(R.color.colorPrimary).into(t);

        }else {
            holder.img_avatar.setImageResource(R.drawable.profile);
        }

        if(!model.getPostPhotos().isEmpty()){

            String photoUrl = model.getPostPhotos().split(",")[0];

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

            Picasso.with(mContext).load(photoUrl).placeholder(R.color.colorPrimary).into(t);

        }else {
            holder.img_big.setImageResource(R.color.gray_dark);
        }


        holder.tv_title.setText("@" + model.getUsername());

        holder.tv_desc.setText(model.getLatitude() + "," + model.getLongitude());

        holder.tv_duffler_msg.setText(model.getDescription());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView img_avatar;
        ImageView img_big;
        IconTextView img_item;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_duffler_msg;
        LinearLayout ll_main;
        RadioButton rb_item;

        public MyHolder(View itemView) {
            super(itemView);

            img_avatar = (ImageView) itemView.findViewById(R.id.imageView);
            img_big = (ImageView) itemView.findViewById(R.id.img_big);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            tv_duffler_msg = (TextView) itemView.findViewById(R.id.tv_duffler_msg);
        }
    }

}
