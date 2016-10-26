package com.cniao5.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.cniao5.app.R;
import com.cniao5.app.adapter.LeftItemAdapter;
import com.cniao5.app.common.DefineView;
import com.cniao5.app.base.BaseActivity;
import com.cniao5.app.widget.DragLayout;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主Activity类
 */
public class MainActivity extends BaseActivity implements DefineView {
    public DragLayout getmDragLayout() {
        return mDragLayout;
    }

    @BindView(R.id.drag_layout)
    DragLayout mDragLayout;
    @BindView(R.id.top_bar_icon)
    ImageView mTopBarIcon;
    @BindView(R.id.lv_left_main)
    ListView mLeftMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setStatusBar();
        initView();
        initValidata();
        initListener();
        bindData();
    }

    public void initView() {
        mDragLayout = (DragLayout) findViewById(R.id.drag_layout);
        mTopBarIcon = (ImageView) findViewById(R.id.top_bar_icon);
        mLeftMenuView = (ListView) findViewById(R.id.lv_left_main);
    }

    @Override
    public void initValidata() {
        mLeftMenuView.setAdapter(new LeftItemAdapter());
    }

    @Override
    public void initListener() {
        mDragLayout.setDragListener(new CustomDragListener());
        mTopBarIcon.setOnClickListener(new CustomOnClickListener());

    }

    @Override
    public void bindData() {

    }

    class CustomDragListener implements DragLayout.DragListener {
        /**
         * 界面打开
         */
        @Override
        public void onOpen() {

        }

        /**
         * 界面关闭
         */
        @Override
        public void onClose() {

        }

        /**
         * 界面进行滑动
         *
         * @param percent
         */
        @Override
        public void onDrag(float percent) {
            ViewHelper.setAlpha(mTopBarIcon, 1 - percent);
        }
    }

    class CustomOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View arg0) {
            mDragLayout.open();
        }
    }

}
