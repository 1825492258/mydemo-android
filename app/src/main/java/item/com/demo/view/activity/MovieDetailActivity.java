package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.item.sdk.base.activity.BaseCompatActivity;
import com.item.sdk.global.GlideApp;
import com.item.sdk.utils.ResourcesUtils;
import com.item.sdk.utils.StatusBarUtil;
import com.item.sdk.utils.ToastUtils;
import com.item.sdk.widgets.CompatNestedScrollView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import item.com.demo.R;
import item.com.demo.adapter.MovieDetailAdapter;
import item.com.demo.bean.MovieDetailBean;
import item.com.demo.bean.SubBean;
import item.com.demo.net.JsonCallback;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MovieDetailActivity extends BaseCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_header_bg)
    ImageView ivHeaderBg;
    @BindView(R.id.iv_movie_photo)
    ImageView ivMoviePhoto;
    @BindView(R.id.tv_movie_rating_rate)
    TextView tvMovieRatingRate;
    @BindView(R.id.tv_movie_rating_number)
    TextView tvMovieRatingNumber;
    @BindView(R.id.tv_collect_count)
    TextView tvCollectCount;
    @BindView(R.id.tv_movie_directors)
    TextView tvMovieDirectors;
    @BindView(R.id.tv_movie_casts)
    TextView tvMovieCasts;
    @BindView(R.id.tv_movie_genres)
    TextView tvMovieGenres;
    @BindView(R.id.tv_movie_date)
    TextView tvMovieDate;
    @BindView(R.id.tv_movie_city)
    TextView tvMovieCity;
    @BindView(R.id.tv_movie_sub_title)
    TextView tvMovieSubTitle;
    @BindView(R.id.tv_moive_summary)
    TextView tvMovieSummary;
    @BindView(R.id.rv_movie_detail)
    RecyclerView rvMovieDetail;
    @BindView(R.id.iv_toolbar_bg)
    ImageView ivToolbarBg;
    @BindView(R.id.nsv_scrollview)
    CompatNestedScrollView nsvScrollView;
    private SubBean.SubjectsBean mSubjectBean;

    public static void show(Activity context, SubBean.SubjectsBean bean, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("bean", bean);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context, imageView, ResourcesUtils.getString(R.string.transition_movie_img)
        );
        // 与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movice_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        if (getIntent() != null) {
            mSubjectBean = (SubBean.SubjectsBean) getIntent().getSerializableExtra
                    ("bean");
        } else {
            onBackPressedSupport();
        }
    }

    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }
    private MovieDetailAdapter mAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.immersive(this);
        initTitleBar(toolbar, mSubjectBean.getTitle());
        initHeaderView(mSubjectBean);
        nsvScrollView.bindAlphaView(ivToolbarBg);
        getMovideDetails(mSubjectBean.getId());
        mAdapter = new MovieDetailAdapter(null);
        mAdapter.setEmptyView(ResourcesUtils.inflate(R.layout.view_empty));
        rvMovieDetail.setLayoutManager(new LinearLayoutManager(this));
        rvMovieDetail.setNestedScrollingEnabled(false);
        rvMovieDetail.setAdapter(mAdapter);
    }

    private void getMovideDetails(String id) {
        OkGo.<MovieDetailBean>get("https://api.douban.com/v2/movie/subject/" + id)
                .execute(new JsonCallback<MovieDetailBean>() {
                    @Override
                    public void onError(String errMessage) {
                        ToastUtils.showToast(errMessage);
                    }

                    @Override
                    public void onSuccess(Response<MovieDetailBean> response) {
                        MovieDetailBean bean = response.body();
                        tvMovieSubTitle.setText(bean.getAkaString());
                        tvMovieSummary.setText(bean.getSummary());
                        List<MovieDetailBean.DirectorsBean> beans = bean.getDirectors();
                        beans.addAll(bean.getCasts());
                        mAdapter.setNewData(beans);
                    }
                });
    }

    /**
     * 显示网络虚化图片
     *
     * @param context   context
     * @param imgUrl    图片url
     * @param imageView 要显示的imageview
     */
    public static void displayBlurImg(Context context, String imgUrl, ImageView imageView) {
        // "23":模糊度；"3":图片缩放4倍后再进行模糊
        GlideApp.with(context)
                .load(imgUrl)
                .error(R.drawable.ic_default_color)
                .placeholder(R.drawable.ic_default_color)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(23, 3)))
                .into(imageView);

    }

    /**
     * 显示资源虚化图片
     *
     * @param context    context
     * @param resourceId 图片资源id
     * @param imageView  要显示的imageview
     */
    public static void displayBlurImg(Context context, Integer resourceId, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        GlideApp.with(context)
                .load(resourceId)
                .error(R.drawable.ic_default_color)
                .placeholder(R.drawable.ic_default_color)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(23, 3)))
                .into(imageView);

    }

    private void initHeaderView(SubBean.SubjectsBean subjectsBean) {
        tvMovieRatingNumber.setText(String.valueOf(subjectsBean.getRating().getAverage()));
        tvCollectCount.setText(String.valueOf(subjectsBean.getCollect_count()));
        tvMovieDirectors.setText(subjectsBean.getDirectorsString());
        tvMovieCasts.setText(subjectsBean.getActorsString());
        tvMovieGenres.setText(subjectsBean.getGenresString());
        tvMovieDate.setText(subjectsBean.getYear());
        GlideApp.with(this).load(subjectsBean.getImages().getLarge()).into(ivMoviePhoto);
        displayBlurImg(this, subjectsBean.getImages().getLarge(), ivHeaderBg);
        displayBlurImg(this, subjectsBean.getImages().getLarge(), ivToolbarBg);

        int headerBgHeight;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            headerBgHeight = toolbar.getLayoutParams().height + StatusBarUtil.getStatusBarHeight(this);
        } else {
            headerBgHeight = toolbar.getLayoutParams().height;
        }
        // 使背景图向上移动到图片的最低端，保留（toolbar+状态栏）的高度
        // 实际上此时ivToolbarBg高度还是330dp，只是除了toolbar外，剩下部分是透明状态
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams)
                ivToolbarBg.getLayoutParams();
        int marginTop = ivToolbarBg.getLayoutParams().height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
    }
}
