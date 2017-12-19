package com.app.dufflertemp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.dufflertemp.adapter.AdapterCreatePost;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


/**
 * Created by wlc on 31/10/17.
 */

public class FragmentCreatePost extends Fragment implements  View.OnClickListener{

    String file_path = "";

    ArrayList<Uri> imgUriList = new ArrayList<>();

    AdapterCreatePost adapterCreatePost;

    EditText tv_post_msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_create_post, container, false);

        tv_post_msg = (EditText) rootview.findViewById(R.id.tv_post_msg);

        TextView tv_cancel= (TextView) rootview.findViewById(R.id.tv_cancel);
        TextView tv_publish= (TextView) rootview.findViewById(R.id.tv_publish);

        tv_cancel.setOnClickListener(this);
        tv_publish.setOnClickListener(this);

        FrameLayout container_with_dotted_border = (FrameLayout) rootview.findViewById(R.id.container_with_dotted_border);

        RecyclerView recycle_view_create_post = (RecyclerView) rootview.findViewById(R.id.recycle_view_create_post);
        //recycle_view_create_post.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recycle_view_create_post.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        imgUriList.clear();


        imgUriList.add(Uri.parse("file:///data/data/com.app.dufflertemp/cache/cropped-665041246.jpg"));
        imgUriList.add(Uri.parse("file:///data/data/com.app.dufflertemp/cache/cropped206180600.jpg"));
        imgUriList.add(Uri.parse("file:///data/data/com.app.dufflertemp/cache/cropped1918378655.jpg"));

        adapterCreatePost = new AdapterCreatePost(getActivity(), imgUriList);
        recycle_view_create_post.setAdapter(adapterCreatePost);

        container_with_dotted_border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] options = {getString(R.string.take_photo), getString(R.string.from_gallary), getString(R.string.button_cancel)};


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle(getString(R.string.add_photo));

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {

                        if (options[item].equals(getString(R.string.take_photo))) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            file_path = getActivity().getExternalCacheDir() + "/img_temp.jpg";

                          /*  File file = new File(file_path);

                            if (!file.exists()) {
                                file.mkdirs();
                            }*/

                            Uri uri = Uri.fromFile(new File(file_path));
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                            startActivityForResult(intent, 1);

                        } else if (options[item].equals(getString(R.string.from_gallary))) {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 2);
                        }
                        else if (options[item].equals(getString(R.string.button_cancel))) {
                            dialog.dismiss();
                        }

                    }

                });

                builder.show();

            }
        });



        return  rootview;
        // Inflate the layout for this fragment

    }

    @Override
    public void onClick(View view) {
        if(R.id.tv_cancel==view.getId()){

        }else if(R.id.tv_publish == view.getId()){
            Log.d("click...","...tv_publish...");
            validateAndPublish();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Log.d("activityResult","length"+resultCode+"  "+requestCode);
        switch (requestCode) {
            case 1:                                                                //camera
                if (resultCode == RESULT_OK) {
                    Log.d("tempPath", "...camera" + file_path);
                    Uri uri= Uri.fromFile(new File(file_path));

                    CropImage.activity(uri).setAspectRatio(1,1).start(getContext(), this);

                }
                break;
            case 2:                                                                 //Gallery
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();

                    CropImage.activity(selectedImage).setAspectRatio(1,1).start(getContext(), this);

                }
                break;
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                Log.d("tempPath", "...call crop response" );
                CropImage.ActivityResult result = CropImage.getActivityResult(imageReturnedIntent);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    imgUriList.add(resultUri);

                    adapterCreatePost.notifyDataSetChanged();

                    Log.d("imgUriList", "...length...." + imgUriList.size() + "..resultUri.." + resultUri);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Log.d("tempPath", "...error"+error.getMessage() );
                    //Common.call_snackbar_method(error.getMessage(),this);
                }
                break;

        }

    }

    public void validateAndPublish(){

        if(!imgUriList.isEmpty()){

            MultipartBody.Part[] body = new MultipartBody.Part[imgUriList.size()]; //key,file name,file

            try{

                int i = 0;

                for(Uri tmpUri : imgUriList){

                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), Common.bitmap2byteAray(BitmapFactory.decodeFile(tmpUri.getPath())));

                    body[i] = MultipartBody.Part.createFormData("post_photos_"+i, "user_avatar.jpg", reqFile);

                    ++i;
                }


            }catch (Exception e){
                e.printStackTrace();
            }

            final ProgressDialog progressDialog=new ProgressDialog(getActivity());
            Common.showProgressDialog(progressDialog,"Loading...");

            RequestBody userid = Common.getStringPlain("44");
            RequestBody API_KEY = Common.getStringPlain("12345");
            RequestBody latitude = Common.getStringPlain("1111");
            RequestBody longitude = Common.getStringPlain("2222");
            RequestBody description = Common.getStringPlain(tv_post_msg.getText().toString());

            new Retrofit.Builder()
                    .baseUrl(APIInteface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(APIInteface.class)
                    .apiPostsRegisterWithImage(userid, API_KEY, latitude, longitude, description, body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Response<ResponseBody>>() {
                @Override
                public void onCompleted() {
                    Log.d("response_reg","...completed");
                    Common.dismissProgressDialog(progressDialog);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<ResponseBody> responseBodyResponse) {

                    try{
                        String data=responseBodyResponse.body().string();
                        Log.d("response_reg","...next"+data);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }else{
            // msg image compulsory !!
        }
    }


}