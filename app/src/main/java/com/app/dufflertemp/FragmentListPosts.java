package com.app.dufflertemp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.dufflertemp.adapter.AdapterPostList;
import com.app.dufflertemp.model.ModelPostList;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wlc on 31/10/17.
 */

public class FragmentListPosts extends Fragment {

    IconTextView icon_switch,icon_search;
    Toolbar toolbar_post;
    RecyclerView recycle_view;
    AdapterPostList adapterPostList;
    LinearLayout ll_map;
    View rootview;


    Location mLastLocation;

    List<ModelPostList.Root> postLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rootview==null) {
            rootview = inflater.inflate(R.layout.fragment_list_posts, container, false);

            ll_map = (LinearLayout) rootview.findViewById(R.id.ll_map);
           /* SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);*/
        }

        recycle_view= (RecyclerView) rootview.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recycle_view.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recycle_view.setHasFixedSize(true);
        recycle_view.setItemViewCacheSize(20);
        recycle_view.setDrawingCacheEnabled(true);

        toolbar_post= (Toolbar) rootview.findViewById(R.id.toolbar_post);
        icon_switch= (IconTextView) rootview.findViewById(R.id.icon_switch);
        icon_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(icon_switch.getTag().toString().equals("list"))
                {
                    ll_map.setVisibility(View.VISIBLE);
                    recycle_view.setVisibility(View.GONE);
                    toolbar_post.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
                    icon_switch.setTag("map");
                    icon_switch.setText(getString(R.string.list_icon));
                }else{
                    recycle_view.setVisibility(View.VISIBLE);
                    ll_map.setVisibility(View.GONE);
                    toolbar_post.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
                   // toolbar_post.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
                    icon_switch.setTag("list");
                    icon_switch.setText(getString(R.string.map_icon));
                }
            }
        });

        icon_search= (IconTextView) rootview.findViewById(R.id.icon_search);
        icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(),SearchPostActivity.class));
            }
        });


        postLists = new ArrayList<>();

        /*for(int i=0;i<10;i++)
        {
            arr.add(i+"");
        }*/

        adapterPostList=new AdapterPostList(getActivity(), postLists);
        recycle_view.setAdapter(adapterPostList);

        getAllPost();

        return  rootview;

    }


    public void getAllPost(){

        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        Common.showProgressDialog(progressDialog,"Loading...");

        //RequestBody API_KEY = Common.getStringPlain("12345");
        String API_KEY = "12345";

        new Retrofit.Builder()
                .baseUrl(APIInteface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(APIInteface.class)
                .apiPostList(API_KEY)
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

                            postLists.clear();
                            postLists.addAll(modelPostList.getRoot());

                            adapterPostList.notifyDataSetChanged();

                            Log.d("response_reg","...postLists.."+postLists.get(0).getDescription());

                        }

                    }



                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}