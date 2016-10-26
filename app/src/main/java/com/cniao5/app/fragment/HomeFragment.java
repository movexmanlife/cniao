package com.cniao5.app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cniao5.adapter.helper.BaseAdapterHelper;
import com.cniao5.adapter.helper.QuickAdapter;
import com.cniao5.app.R;
import com.cniao5.app.biz.ArticleListManager;
import com.cniao5.app.biz.HeadDataManager;
import com.cniao5.app.biz.HomeNewsDataManager;
import com.cniao5.app.common.Config;
import com.cniao5.app.common.DefineView;
import com.cniao5.app.entity.AdHeadEntity;
import com.cniao5.app.entity.ArticleListEntity;
import com.cniao5.app.entity.CategoriesEntity;
import com.cniao5.app.entity.HomeNewsEntity;
import com.cniao5.app.base.BaseFragment;
import com.cniao5.app.util.OkhttpManager;
import com.cniao5.cwidgetutils.AutoGallery;
import com.cniao5.cwidgetutils.FlowIndicator;
import com.cniao5.cwidgetutils.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * 当前类注释:
 */
public class HomeFragment extends BaseFragment implements DefineView {
    private View mView;
    private static final String KEY = "EXTRA";
    private PullToRefreshListView home_listview;
    private List<ArticleListEntity> articleListEntityList;
    private QuickAdapter<ArticleListEntity> quickAdapter;
    private String[] masks;
    private int[] mask_colors;
    private FrameLayout home_framelayout;
    private LinearLayout loading, empty, error;
    private View headView;
    private LayoutInflater mInflater;

    public static HomeFragment newInstance(CategoriesEntity extra) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, extra);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            mInflater = LayoutInflater.from(getActivity());
            headView = mInflater.inflate(R.layout.gallery_indicator_layout, null);
            initView();
            initValidata();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        home_listview = (PullToRefreshListView) mView.findViewById(R.id.home_listview);
        home_listview.addHeaderView(headView);
        home_framelayout = (FrameLayout) mView.findViewById(R.id.home_framelayout);
        loading = (LinearLayout) mView.findViewById(R.id.loading);
        empty = (LinearLayout) mView.findViewById(R.id.empty);
        error = (LinearLayout) mView.findViewById(R.id.error);
    }

    @Override
    public void initValidata() {
        masks = new String[]{"氪TV", "O2O", "新硬件", "Fun!!", "企业服务", "Fit&Health", "在线教育", "互联网金融", "大公司", "专栏"};
        mask_colors = new int[]{R.color.mask_tags_1, R.color.mask_tags_2,
                R.color.mask_tags_3, R.color.mask_tags_4, R.color.mask_tags_5,
                R.color.mask_tags_6, R.color.mask_tags_7, R.color.mask_tags_8,
                R.color.mask_tags_9, R.color.mask_tags_10, R.color.mask_tags_11, R.color.mask_tags_12};

        home_listview.setVisibility(View.GONE);
        home_framelayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);

        OkhttpManager.getAsync("http://blog.csdn.net/mobile/newarticle.html", new OkhttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {
                Log.d("zttjianggqq", "首页新闻数据加载失败...");
            }

            @Override
            public void requestSuccess(String result) {
                Document document = Jsoup.parse(result, Config.CSDN_URL);
                articleListEntityList = new ArticleListManager().getArticleListBean(document);
                if (articleListEntityList != null) {
                    home_listview.setVisibility(View.VISIBLE);
                    home_framelayout.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    empty.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    bindData();
                } else {
                    home_listview.setVisibility(View.GONE);
                    home_framelayout.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
        quickAdapter = new QuickAdapter<ArticleListEntity>(getActivity(), R.layout.item_home_news_layout, articleListEntityList) {
            @Override
            protected void convert(BaseAdapterHelper helper, ArticleListEntity item) {
                helper.setText(R.id.item_news_tv_name, item.getNickname())
                        .setText(R.id.item_news_tv_tag, item.getTag())
                        .setText(R.id.item_news_tv_time, item.getTime())
                        .setText(R.id.item_news_tv_title, item.getTitle())
                        .setText(R.id.item_news_tv_desc, item.getDesc())
                        .setText(R.id.item_news_tv_browse, String.valueOf(item.getBrowse()))
                        .setImageUrl(R.id.item_news_img_icon, item.getAvatarUrl());;
            }
        };
        home_listview.setAdapter(quickAdapter);
    }

}
