package com.app.dufflertemp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.dufflertemp.adapter.AdapterPostSearch;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by wlc on 8/11/17.
 */
public class SearchPostActivity extends AppCompatActivity{

    RecyclerView recycle_view;
    AdapterPostSearch adapterPostSearch;
    EditText edit_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_post_activity);

        edit_search= (EditText) findViewById(R.id.edit_search);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edit_search.getText().toString().length() > 0) {
                    filter(String.valueOf(charSequence));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //TextView tv_title= (TextView) findViewById(R.id.tv_title);
        //tv_title.setText("");

        recycle_view= (RecyclerView) findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(SearchPostActivity.this));
        //recycle_view.addItemDecoration(new SimpleDividerItemDecoration(SearchPostActivity.this));
        recycle_view.setHasFixedSize(true);
        recycle_view.setItemViewCacheSize(20);
        recycle_view.setDrawingCacheEnabled(true);

        ArrayList<String> arr=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            arr.add(i+"");
        }
        IconTextView icon_back= (IconTextView) findViewById(R.id.icon_back);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapterPostSearch=new AdapterPostSearch(SearchPostActivity.this,arr);
        recycle_view.setAdapter(adapterPostSearch);


    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void filter(String charText) {


       /* try {
            charText = charText.toString().toUpperCase();

            Log.d("filtering", "" + Library.arrList_Library_List.size());


            arrList_Library_List.clear();
            for (int i = 0; i < Library.arrList_Library_List.size(); i++) {

                Model_Library_List model_library_list = Library.arrList_Library_List.get(i);
                String title = model_library_list.getTitle();
                //if (title.toUpperCase().startsWith(charText)) {
                if (title.toUpperCase().contains(charText)) {
                    arrList_Library_List.add(model_library_list);
                    Log.v("arr_distance_course", "." + arrList_Library_List.size());
                }
            }
            arrList_Library_List= common.call_sorting_arralist_of_model_by_category(arrList_Library_List);
            mainAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
