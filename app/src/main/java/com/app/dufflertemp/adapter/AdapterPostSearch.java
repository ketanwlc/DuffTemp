package com.app.dufflertemp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.dufflertemp.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by Ketan on 18/4/17.
 */

public class AdapterPostSearch extends RecyclerView.Adapter<AdapterPostSearch.MyHolder> {

    Context mContext;
    //ArrayList<ModelYourProfession1> arrayList;
    public static int previous_position=-1;
    int pos;
    ArrayList<String> arrayList;

   public AdapterPostSearch(Context mContext, ArrayList<String> arrayList)
   {
       this.mContext=mContext;
       this.arrayList=arrayList;
   }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_search,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {


        //holder.tv_title.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        IconTextView img_item;
        TextView tv_title;
        LinearLayout ll_main;
        RadioButton rb_item;

        public MyHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
