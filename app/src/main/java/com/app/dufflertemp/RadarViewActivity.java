package com.app.dufflertemp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nikunj on 20/12/17.
 */

public class RadarViewActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    IconTextView iconBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.icon_switch)
    IconTextView iconSwitch;
    @BindView(R.id.toolbar_post)
    Toolbar toolbarPost;
    @BindView(R.id.img_radar_view)
    ImageView imgRadarView;
    @BindView(R.id.img_mobile)
    ImageView imgMobile;
    @BindView(R.id.img_device)
    ImageView imgDevice;
    @BindView(R.id.frame_device_area)
    FrameLayout frameDeviceArea;

    int distance = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radar_view_activity);
        ButterKnife.bind(this);

        iconBack.setText("{fa-times}");

        tvTitle.setText("Radar View");

        Picasso.with(this).load(R.drawable.radar_view_bg).into(imgRadarView);

        // 110 = header 50 + movileView 30 + margin 20
        final float remainingPX = Common.getScreenHeight(this) - Common.pxFromDp(110, this);

        Log.d("setDeviceDP...","....." + remainingPX);

        imgDevice.setY(distance * (remainingPX/214));

        /*new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                distance = new Random().nextInt(200);

                imgDevice.setY(distance * (remainingPX/214));
            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second*/

        //imgDevice.setY(1873);

    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
