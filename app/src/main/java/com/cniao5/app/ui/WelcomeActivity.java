package com.cniao5.app.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.cniao5.app.R;
import com.cniao5.app.base.BaseActivity;
import com.cniao5.app.widget.CustomVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动欢迎界面
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.welcome_button)
    Button mWelcomeBtn;
    @BindView(R.id.welcome_videoview)
    CustomVideoView mWelcomeVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mWelcomeVideoView.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.kr36));
        mWelcomeVideoView.start();
        mWelcomeVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mWelcomeVideoView.start();

            }
        });
    }

    @OnClick(R.id.welcome_button)
    public void gotoMain() {
        if (mWelcomeVideoView.isPlaying()) {
            mWelcomeVideoView.stopPlayback();
            mWelcomeVideoView = null;
        }
        openActivity(MainActivity.class);
        WelcomeActivity.this.finish();
    }
}
