package com.app.dufflertemp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.dufflertemp.adapter.AdapterPostList;
import com.app.dufflertemp.adapter.AdapterPostSearch;
import com.app.dufflertemp.model.ModelPostList;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by wlc on 8/11/17.
 */
public class SearchPostActivity extends AppCompatActivity{

    RecyclerView recycle_view;
    AdapterPostList adapterPostSearchList;
    EditText edit_search;
    ImageView img_search_clear;

    List<ModelPostList.Root> postSearchLists;

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
                    img_search_clear.setVisibility(View.VISIBLE);
                    filter(String.valueOf(charSequence));
                }else {
                    img_search_clear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (edit_search.getText().toString().trim().length() > 0){
                        getSearchPost(edit_search.getText().toString());
                        Common.hideSoftKeyboard(SearchPostActivity.this);
                    }
                    return true;
                }
                return false;
            }
        });

        img_search_clear = (ImageView) findViewById(R.id.img_search_clear);

        img_search_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_search.setText("");
                postSearchLists.clear();
                adapterPostSearchList.notifyDataSetChanged();
                Common.hideSoftKeyboard(SearchPostActivity.this);
            }
        });

        recycle_view= (RecyclerView) findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(SearchPostActivity.this));
        //recycle_view.addItemDecoration(new SimpleDividerItemDecoration(SearchPostActivity.this));
        recycle_view.setHasFixedSize(true);
        recycle_view.setItemViewCacheSize(20);
        recycle_view.setDrawingCacheEnabled(true);

        postSearchLists = new ArrayList<>();
       /* for(int i=0;i<10;i++)
        {
            arr.add(i+"");
        }*/
        IconTextView icon_back= (IconTextView) findViewById(R.id.icon_back);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapterPostSearchList=new AdapterPostList(SearchPostActivity.this, postSearchLists);
        recycle_view.setAdapter(adapterPostSearchList);


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


    public void getSearchPost(String searchText){

        final ProgressDialog progressDialog=new ProgressDialog(this);
        Common.showProgressDialog(progressDialog,"Loading...");

        //RequestBody API_KEY = Common.getStringPlain("12345");
        String API_KEY = "12345";

        new Retrofit.Builder()
                .baseUrl(APIInteface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(APIInteface.class)
                .apiPostSearchList(API_KEY, searchText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response<ModelPostList>>() {
            @Override
            public void onCompleted() {
                Log.d("response_reg","...completed");
                Common.dismissProgressDialog(progressDialog);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<ModelPostList> responseBodyResponse) {

                try{
                    //String data=responseBodyResponse.body().string();

                    if(responseBodyResponse.isSuccessful()){

                        ModelPostList modelPostList = responseBodyResponse.body();

                        Log.d("response_reg","...next.."+modelPostList.getStatus());

                        if(modelPostList.getStatus().equalsIgnoreCase("success")){

                            postSearchLists.clear();
                            postSearchLists.addAll(modelPostList.getRoot());

                            adapterPostSearchList.notifyDataSetChanged();

                            //Log.d("response_reg","...postLists.."+postLists.get(0).getDescription());

                        }

                    }



                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

}
