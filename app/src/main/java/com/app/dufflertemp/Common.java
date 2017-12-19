package com.app.dufflertemp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

/*import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;*/

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Ketan on 22/12/15.
 */
public class Common {

    public static String MAIN_ACTION = "callactivity_from_notification";
    public static String TRACK_ACTION = "track_from_notification";
    // google hash key  2E:74:EC:9A:9E:1D:46:66:2C:0A:E3:1D:09:3F:D3:7B:23:2C:63:A8
    // google hash key  3F:6D:44:05:4C:98:FB:90:E7:EC:C9:03:EF:58:E8:D9:47:18:E9:2C

    //public static String internet_msg="No internet connection";
    public static String SC_URLSCHEMA = "sampleproject";
    public static String SC_CLIENT_ID = "e80af51aa01a8dc86a7ebc24adbf8c7a";
    public static String SC_CLIENT_SECRET = "6367d1d3e4bc58bb9f61db875c01a49a";
    public static String SC_REDIRECT_URI = "sampleproject://oauth";

    public static String SC_API_URL = "https://api.soundcloud.com";
    //public static String SC_CLIENT_ID = "GXEVeICDvidtZBAB6YwNFiFGWgvhga5M";
    //public static String SC_CLIENT_SECRET = "SUBd5WlnHF8qMYQMI1oaUaCLRpHmuchB";
    //public static String SC_REDIRECT_URI = "http://BalancedBreakfast://oauth";

    //public static String INSTAGRAM_CLIENT_ID  = "db71f0af4f5e4966909fd98817a6873f";
    public static String INSTAGRAM_CLIENT_ID  = "9ade13377d354787a2624f109bc6a9d8";   //client
    //public static String INSTAGRAM_REDIRECT_URI = "http://www.whitelotuscorporation.com/";
    public static String INSTAGRAM_REDIRECT_URI = "http://www.blncdbrkfst.com/";      //client
    public static String INSTAGRAM_CLIENTSERCRET = "489e8191527b49faaa39c0705b255357";
    public static String INSTAGRAM_SCOPE = "likes+comments+relationships";


    //public static String TWITTER_CONSUMER_KEY="TLLJL3Jzu8Vha54uEOZxrt0YR";   //testing
    //public static String TWITTER_CONSUMER_SECRETE="HLPD8AEOQ91lieJDsgJfJ3iCOQdEYZeefYmjalSNgTcL2zME0x";

    public static String TWITTER_CONSUMER_KEY="kGeV109d60bktsPBUnIm6tRbG";  //client
    public static String TWITTER_CONSUMER_SECRETE="XxeL7VIFnfNJXbeDN9HN5vD4wZFXyCzo09HD33V9GD4RUMrCba";

    public static String YOUTUBEDRIVESCOPE = "https://www.googleapis.com/auth/youtube.readonly"; // youtube

    public static String fb_api_verson="2.0";
    public static String fb_app_name="BB_Breakfast";
    //AIzaSyCOTujowOXdbVnNIUCAM77EuF06qnILzWc   //
    public static boolean isValidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static float dpFromPx(float px, Context mContext) {
        return px / mContext.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(float dp, Context mContext) {
        return dp * mContext.getResources().getDisplayMetrics().density;
    }

    //================================================================
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String Date_return_from_string(String date)
    {
            String date_new = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yy hh:mm:ss");
               // SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyy");
                Date newDate = format.parse(date);

                format = new SimpleDateFormat("MMMM-dd");
                date_new = format.format(newDate);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return date_new;
    }

    public static String time_return_from_string(String date)
    {
        String date_new = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yy HH:mm:ss");
            // SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyy");
            Date newDate = format.parse(date);

            format = new SimpleDateFormat("hh:mm a");
            date_new = format.format(newDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date_new;
    }

    public static String time_convert_24_to_12(String date)
    {
        String date_new = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            // SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyy");
            Date newDate = format.parse(date);

            format = new SimpleDateFormat("hh:mm aa");
            date_new = format.format(newDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date_new;
    }

    public static String myChangeDate(String date)
    {
        String date_new = null;
        try {
            //SimpleDateFormat format = new SimpleDateFormat("dd-mm-yy hh:mm");
            SimpleDateFormat format = new SimpleDateFormat("yyy-mm-dd");
            Date newDate = format.parse(date);

            format = new SimpleDateFormat("MMM-dd-EE");
            date_new = format.format(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date_new;
    }


    //===============Preferences all for application===============

    public static void setPreference_login_data(Context context,String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("login_exit", Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getPreference_login_data(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("login_exit",
                Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);
        return sp.getString(key, "0");
    }

    public static String convertDate(String dateInMilliseconds,String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }
    //============================================================

    public static boolean isConnectingToInternet(Context context) {
        boolean value = false;
        try {
            
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            value= activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    public static void hideKeyBoardFromWindow(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive())
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isMyServiceRunning(Class<?> serviceClass,Activity act) {
        ActivityManager manager = (ActivityManager) act.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

   /* public static void showTitleDialog(Context context, String title, String msg)
    {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(title);
        builder.titleGravity(GravityEnum.CENTER);
        builder.content(msg);
        builder.cancelable(false);
        builder.positiveText("Ok");
        builder.positiveColor(Color.parseColor("#303F9F"));
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }*/

    public static Bitmap loadBitmapFromViewBGWhite(View v,Context context) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawColor(ContextCompat.getColor(context, android.R.color.darker_gray));
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

   /* public static void showDialDialog(final Context context,final String number)
    {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(context.getString(R.string.app_name));
       // builder.titleGravity(GravityEnum.CENTER);
        builder.content(context.getString(R.string.msg_sure_call));
        builder.cancelable(false);
        builder.positiveText(context.getString(R.string.call));
        builder.positiveColor(Color.parseColor("#303F9F"));
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                try {

                   *//* Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                    phoneIntent.setData(Uri.parse("tel:"+ number));
                    context.startActivity(phoneIntent);
*//*
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context,
                            "Call failed, please try again later!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.negativeText(context.getString(R.string.cancle));
        builder.negativeColor(Color.parseColor("#303F9F"));
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }*/

  /*  //======================UTC to local==================

    mFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date dateet1 = null;
    try {
        dateet1 = mFormatter.parse(full_time);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    mFormatter.setTimeZone(TimeZone.getDefault());
    String formattedDate1 = mFormatter.format(dateet1);

    Log.d("finddate", "" +full_time + "  " + " " + formattedDate1);*/
    //======================================================

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        //bitmap.recycle();

        return output;
    }

    public static String formatURI(String url) {
        if (url.startsWith("http://")) {
            return url;
        } else if (url.startsWith("www")) {
            return "http://" + url;
        } else if (url.startsWith("https://")) {

            url= url.replace("https://","http://");
            return  url;

        }else
        {
            if(!url.contains("http://") && !url.contains("www") )
            {
                url="http://www."+url;
            }
            return  url;
        }

    }

    public static int getScreenWidth(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();

        if (android.os.Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);

            return size.x;
        } else {
            return display.getWidth();
        }
    }

    public static int getScreenHeight(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();

        if (android.os.Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);

            return size.y;
        } else {
            return display.getHeight();
        }
    }

    public static byte[] bitmap2byteAray(Bitmap bitmap){

        byte[] imageInByte = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            imageInByte = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  imageInByte;
    }

    public static void exportDatabse(Context context) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+context.getPackageName()+"//databases//"+"db_plannu";
                String backupDBPath = "db_plannu";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }

    public static JSONArray cur2Json(Cursor cursor) {

        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        rowObject.put(cursor.getColumnName(i),
                                cursor.getString(i));
                    } catch (Exception e) {
                        Log.d("cur2Json", e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        return resultSet;

    }
    public static String calenderToUSString(Date date) {
        String myFormat = "MM/dd/yyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        return sdf.format(date);
    }

   /* public static JSONArray UpdateGson(JSONArray jsonArray)
    {
        Gson gson=new Gson();
        JsonElement element = gson.fromJson(jsonArray.toString(), JsonElement.class);
        Log.d("CONVERT_JSON","START.."+element);
        //======================================
        JsonArray jArray = element.getAsJsonArray();
        for(int i=0;i<jArray.size();i++)
        {
            JsonElement jelement=jArray.get(i);
            JsonObject jObj=jelement.getAsJsonObject();
            JsonElement e =jObj.get("working");
            if(e.isJsonPrimitive())
            {
                jObj.remove("working");
                String value=e.getAsString();
                Log.d("CONVERT_JSON","BEFORE.."+value);
                if(value.equals("1"))
                    jObj.addProperty("working",true);
                else jObj.addProperty("working",false);
            }
        }
        String value= gson.toJson(jArray);
        try {
            jsonArray=new JSONArray(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("CONVERT_JSON","AFTER.."+jsonArray);
        return jsonArray;
    }*/

    public static boolean isFutureDate(String d1,String d2)
    {
        try{

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);

            System.out.println("Date1"+sdf.format(date1));
            System.out.println("Date2"+sdf.format(date2));System.out.println();

            // after() will return true if and only if date1 is after date 2
            if(date1.after(date2)){
                System.out.println("Date1 is after Date2");
                return true;
            }
            System.out.println();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static int getStatusBarHeight(Activity act) {
        int result = 0;
        int resourceId = act.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = act.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("country_code.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static Bitmap resizeBitmapByDrawableName(String drawableName,int width, int height,Context context){
        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(),context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }

    public static void showProgressDialog(ProgressDialog mProgressDialog, String message) {
        try {

            if (mProgressDialog != null && !mProgressDialog.isShowing()) {
                mProgressDialog.setMessage(message);
                //mProgressDialog.setCancelable(isCancelable);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgressDialog(ProgressDialog mProgressDialog) {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public static String decodeFile(String name,String path,int DESIREDWIDTH, int DESIREDHEIGHT) {
        String strMyImagePath = null;
        Bitmap scaledBitmap = null;

        Log.d("call_compress","..."+name+"  "+path);
        try {
            // Part 1: Decode image
            Bitmap unscaledBitmap = ScalingUtilities.decodeFile(path, DESIREDWIDTH, DESIREDHEIGHT, ScalingUtilities.ScalingLogic.FIT);

            if (!(unscaledBitmap.getWidth() <= DESIREDWIDTH && unscaledBitmap.getHeight() <= DESIREDHEIGHT)) {
                // Part 2: Scale image
                scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, DESIREDWIDTH, DESIREDHEIGHT, ScalingUtilities.ScalingLogic.FIT);
            } else {
                unscaledBitmap.recycle();
                return path;
            }
            // Store to tmp file

            String extr = Environment.getExternalStorageDirectory().toString();
            File mFolder = new File(extr + "/TEMP_BUILDER");
            if (!mFolder.exists()) {
                mFolder.mkdir();
            }
            File[] array=mFolder.listFiles();

            String s = name+(array.length+1)+".PNG";
            File f = new File(mFolder.getAbsolutePath(), s);

            strMyImagePath = f.getAbsolutePath();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }

            scaledBitmap.recycle();
        } catch (Throwable e) {
        }

        if (strMyImagePath == null) {
            return path;
        }
        return strMyImagePath;

    }
*/
    public static void loadImage(Context context, String imgUrl, final ImageView imageView) {

        Target t = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Log.i("userImage Link...", "....onBitmapLoaded");
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.i("userImage Link...", "....onBitmapFailed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.i("userImage Link...", "....onPrepareLoad");
            }
        };

        //Picasso.with(context).load(imgUrl).resize(120, 120).into(t);
        Picasso.with(context).load(imgUrl).into(t);

        imageView.setTag(t);

    }

    public static void call_snackbar_method(String msg,Activity act)
    {
        Snackbar snackbar = Snackbar
                .make(act.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    public static String getAuthToken() {     //APP_ID and APP_TOKEN  (username/password)
        byte[] data = new byte[0];
        try {
            data = ("duffler-i3a" + ":" + "a5772e86fced0c44643a8c3f55383794").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }

    public static RequestBody getStringPlain(String temp){
            return RequestBody.create(MediaType.parse("text/plain"),temp);
    }
}
