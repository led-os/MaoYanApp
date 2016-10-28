package com.mao.movie.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mao.movie.App;
import com.mao.movie.R;
import com.mao.movie.model.BannerModel;
import com.mao.movie.retrofit.ApiService;
import com.mao.movie.retrofit.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页的推荐Fragment
 */
public class MainRecommendFragment extends Fragment implements BGABanner.OnItemClickListener, BGABanner.Adapter {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.banner)
    BGABanner mBanner;
    @BindView(R.id.recommendMoreTextView)
    TextView mRecommendMoreTextView;
    @BindView(R.id.recommendRecyclerView)
    RecyclerView mRecommendRecyclerView;
    @BindView(R.id.hotMoreTextView)
    TextView mHotMoreTextView;
    @BindView(R.id.hotRecyclerView)
    RecyclerView mHotRecyclerView;

    private

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);

        init();

        getBannerData();
        return view;
    }

    private void init() {
        mRecommendRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mHotRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    }

    private void getBannerData() {
        RetrofitClient.getClient(ApiService.class).fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setAdapter(MainRecommendFragment.this);
                mBanner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
        Toast.makeText(App.getInstance(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Glide.with(getActivity().getApplicationContext())
                .load(model)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .into((ImageView) view);
    }

    @OnClick({R.id.recommendMoreTextView, R.id.hotMoreTextView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommendMoreTextView:
                break;
            case R.id.hotMoreTextView:
                break;
        }
    }
}