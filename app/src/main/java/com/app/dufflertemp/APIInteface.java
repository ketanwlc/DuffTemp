package com.app.dufflertemp;

import com.app.dufflertemp.model.ModelPostList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ketan on 27/5/17.
 */

public interface APIInteface {

    String BASE_URL = "http://wlctest.online/duffler/index.php/";

    String BASE_URL_ESTIMOTE = "https://cloud.estimote.com/v2/";

    @GET("devices/{identifier}/")
    Observable<Response<ResponseBody>> apiBeaconData(
            @Header("Authorization") String authkey
            , @Path("identifier") String identifier
    );

    @FormUrlEncoded
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostRegistrationData(@Field("email") String email,
                                                               //Observable<ModelRegistration> apiPostRegistrationData(@Field("email") String email,
                                                               @Field("password") String password,
                                                               @Field("X-API-KEY") String X_API_KEY,
                                                               @Field("firstname") String firstname,
                                                               @Field("lastname") String lastname,
                                                               @Field("street") String street,
                                                               @Field("city") String city,
                                                               @Field("user_avatar") String user_avatar,
                                                               @Field("bag_value") String bag_value,
                                                               @Field("location1") String location1,
                                                               @Field("location2") String location2,
                                                               @Field("location3") String location3,
                                                               @Field("location4") String location4,
                                                               @Field("location5") String location5);

    @FormUrlEncoded
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostRegistrationphase2(@Field("user_id") String user_id,
                                                                 @Field("X-API-KEY") String X_API_KEY,
                                                                 @Field("firstname") String firstname,
                                                                 @Field("lastname") String lastname,
                                                                 @Field("street") String street,
                                                                 @Field("city") String city,
                                                                 @Field("address") String address,
                                                                 @Field("wizard_step") String wizard_step,
                                                                 @Field("username") String username,
                                                                 @Field("country") String country);

    @FormUrlEncoded
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostProfileUpdate(@Field("user_id") String user_id,
                                                            @Field("X-API-KEY") String X_API_KEY,
                                                            @Field("firstname") String firstname,
                                                            @Field("lastname") String lastname,
                                                            @Field("street") String street,
                                                            @Field("city") String city,
                                                            @Field("address") String address,
                                                            @Field("wizard_step") String wizard_step,
                                                            @Field("username") String username,
                                                            @Field("pro_update") String pro_update,
                                                            @Field("country") String country);

    @FormUrlEncoded
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostRegistrationphase3(@Field("user_id") String user_id,
                                                                 @Field("X-API-KEY") String X_API_KEY,
                                                                 @Field("bag_value") String bag_value,
                                                                 @Field("wizard_step") String wizard_step);

    @FormUrlEncoded
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostCreateBag(@Field("user_id") String user_id,
                                                        @Field("X-API-KEY") String X_API_KEY,
                                                        @Field("bag_value") String bag_value);

    @FormUrlEncoded
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostRegistrationphase4(@Field("user_id") String user_id,
                                                                 @Field("X-API-KEY") String X_API_KEY,
                                                                 @Field("location1") String location1,
                                                                 @Field("location2") String location2,
                                                                 @Field("location3") String location3,
                                                                 @Field("location4") String location4,
                                                                 @Field("location5") String location5,
                                                                 @Field("wizard_step") String wizard_step);

    @Multipart
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostRegistrationphase2WithImage(@Part("user_id") RequestBody user_id,
                                                                          @Part("X-API-KEY") RequestBody X_API_KEY,
                                                                          @Part("firstname") RequestBody firstname,
                                                                          @Part("lastname") RequestBody lastname,
                                                                          @Part("street") RequestBody street,
                                                                          @Part("city") RequestBody city,
                                                                          @Part("address") RequestBody address,
                                                                          @Part("wizard_step") RequestBody wizard_step,
                                                                          @Part("username") RequestBody username,
                                                                          @Part("country") RequestBody country,
                                                                          @Part MultipartBody.Part params);

    @Multipart
    @POST("api/register")
    Observable<Response<ResponseBody>> apiPostProfileUpdateWithImage(@Part("user_id") RequestBody user_id,
                                                                     @Part("X-API-KEY") RequestBody X_API_KEY,
                                                                     @Part("firstname") RequestBody firstname,
                                                                     @Part("lastname") RequestBody lastname,
                                                                     @Part("street") RequestBody street,
                                                                     @Part("city") RequestBody city,
                                                                     @Part("address") RequestBody address,
                                                                     @Part("wizard_step") RequestBody wizard_step,
                                                                     @Part("username") RequestBody username,
                                                                     @Part("pro_update") RequestBody pro_update,
                                                                     @Part("country") RequestBody country,
                                                                     @Part MultipartBody.Part params);

    @FormUrlEncoded
    @POST("api/fb")
    Observable<Response<ResponseBody>> apiPostFBRegistration(@Field("email") String email,
                                                             @Field("fb_id") String fb_id,
                                                             @Field("X-API-KEY") String X_API_KEY,
                                                             @Field("firstname") String firstname,
                                                             @Field("lastname") String lastname,
                                                             @Field("street") String street,
                                                             @Field("city") String city,
                                                             @Field("address") String address,
                                                             @Field("user_avatar") String user_avatar,
                                                             @Field("bag_value") String bag_value,
                                                             @Field("location1") String location1,
                                                             @Field("location2") String location2,
                                                             @Field("location3") String location3,
                                                             @Field("location4") String location4,
                                                             @Field("location5") String location5);

    @FormUrlEncoded
    @POST("api/login")
    Observable<Response<ResponseBody>> apiPostLoginData(@Field("email") String email,
                                                        @Field("password") String password,
                                                        @Field("X-API-KEY") String X_API_KEY);

    @FormUrlEncoded
    @POST("api/uniqueemail")
    Observable<Response<ResponseBody>> apiUniqueUserCheck(@Field("user_id") String user_id,
                                                          @Field("X-API-KEY") String X_API_KEY,
                                                          @Field("username") String username);

    @FormUrlEncoded
    @POST("api/changemail")
    Observable<Response<ResponseBody>> apiChangeEmail(@Field("user_id") String user_id,
                                                      @Field("X-API-KEY") String X_API_KEY,
                                                      @Field("email") String email);

    @FormUrlEncoded
    @POST("api/changepassword")
    Observable<Response<ResponseBody>> apiChangePassword(@Field("X-API-KEY") String X_API_KEY,
                                                         @Field("email") String email);

    @GET("api/bagslist")
    Observable<Response<ResponseBody>> apiBagsList(@Query("user_id") String user_id,
                                                   @Query("X-API-KEY") String X_API_KEY);

    @GET("api/allcount")
    Observable<Response<ResponseBody>> apiAllCount(@Query("user_id") String user_id,
                                                   @Query("X-API-KEY") String X_API_KEY);
    @FormUrlEncoded
    @POST("api/alarmupdate")
    Observable<Response<ResponseBody>> apiAlarmUpdate(@Field("user_id") String user_id,
                                                      @Field("X-API-KEY") String X_API_KEY,
                                                      @Field("alarm") String alarm);

    @FormUrlEncoded
    @POST("api/bagsupdate")
    Observable<Response<ResponseBody>> apiBagUpdate(@Field("user_id") String user_id,
                                                    @Field("X-API-KEY") String X_API_KEY,
                                                    @Field("bag_value") String bag_value,
                                                    @Field("bag_id") String bag_id,
                                                    @Field("bag_name") String bag_name,
                                                    @Field("beacon_id") String beacon_id);

    @FormUrlEncoded
    @POST("api/bagsupdate")
    Observable<Response<ResponseBody>> apiBagUpdateBeaconValue(@Field("bag_id") String bag_id,
                                                               @Field("user_id") String user_id,
                                                               @Field("X-API-KEY") String X_API_KEY,
                                                               @Field("major") String major,
                                                               @Field("minor") String minor,
                                                               @Field("beacon_name") String beacon_name,
                                                               @Field("beacon_identifier") String beacon_identifier,
                                                               @Field("uuid") String uuid,
                                                               @Field("beacon_value") String beacon_value);

    @FormUrlEncoded
    @POST("api/bagsupdate")
    Observable<Response<ResponseBody>> apiBagUpdateUnpair(@Field("bag_id") String bag_id,
                                                          @Field("user_id") String user_id,
                                                          @Field("X-API-KEY") String X_API_KEY,
                                                          @Field("beacon_identifier") String beacon_identifier,
                                                          @Field("beacon_value") String beacon_value);

    @FormUrlEncoded
    @POST("api/bagsupdate")
    Observable<Response<ResponseBody>> apiBagUpdateAlerm(@Field("bag_id") String bag_id,
                                                         @Field("user_id") String user_id,
                                                         @Field("X-API-KEY") String X_API_KEY,
                                                         @Field("beacon_alarm") String beacon_identifier
    );


    @Multipart
    @POST("api/bagsupdate")
    Observable<Response<ResponseBody>> apiBagUpdateWithImage(@Part("user_id") RequestBody user_id,
                                                             @Part("X-API-KEY") RequestBody X_API_KEY,
                                                             @Part("bag_value") RequestBody bag_value,
                                                             @Part("bag_id") RequestBody bag_id,
                                                             @Part("bag_name") RequestBody bag_name,
                                                             @Part("beacon_id") RequestBody beacon_id,
                                                             @Part MultipartBody.Part params);

    @Multipart
    @POST("api/postsregister")
    Observable<Response<ResponseBody>> apiPostsRegisterWithImage(@Part("user_id") RequestBody user_id,
                                                                 @Part("X-API-KEY") RequestBody X_API_KEY,
                                                                 @Part("latitude") RequestBody latitude,
                                                                 @Part("longitude") RequestBody longitude,
                                                                 @Part("description") RequestBody description,
                                                                 @Part MultipartBody.Part[] params);

    @FormUrlEncoded
    @POST("api/postlist")
    Observable<Response<ModelPostList>> apiPostList(@Field("X-API-KEY") String X_API_KEY);


    @FormUrlEncoded
    @POST("api/searchpost")
    Observable<Response<ModelPostList>> apiPostSearchList(@Field("X-API-KEY") String X_API_KEY,
                                                          @Field("username") String search_text);


    String POLICY_URL =  "https://duffler.com/pages/privacy-plicy";


}

