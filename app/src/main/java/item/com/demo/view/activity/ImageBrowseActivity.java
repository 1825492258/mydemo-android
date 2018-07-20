package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.item.sdk.base.activity.BaseCompatActivity;
import com.item.sdk.global.GlideApp;
import com.item.sdk.utils.AppUtils;
import com.item.sdk.utils.FileUtils;
import com.item.sdk.utils.ResourcesUtils;
import com.item.sdk.utils.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import item.com.demo.R;

/**
 * 图片查看Activity，只有加载图片以及保存图片等简单逻辑
 */
public class ImageBrowseActivity extends BaseCompatActivity {

    public static void show(Context activity, String url) {
        Intent intent = new Intent(activity, ImageBrowseActivity.class);
        intent.putExtra("image_url", url);
        activity.startActivity(intent);
    }

    @BindView(R.id.pv_pic)
    ImageView pvPic;
    @BindView(R.id.fab_save_pic)
    FloatingActionButton fabSavePic;
    @BindView(R.id.pb_pic_browse)
    ProgressBar pbPicBrowse;

    private String mImageUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_browse;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        fabSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast("1");
                save();
            }
        });
    }

    private Drawable m;

    /**
     * 保存图片
     * 如果是6.0手机，记得先申请权限，拿到权限后，再保存，不然会失败，我就是在这里被坑了
     */
    private void save() {
//        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.we);
        FileUtils.saveBitmap(this, mImageUrl, ((BitmapDrawable) m).getBitmap(), new FileUtils.SaveResultCallback() {
            @Override
            public void onSavedSuccess() {
                Snackbar.make(fabSavePic, "保存成功", Snackbar.LENGTH_SHORT).setActionTextColor
                        (ResourcesUtils.getColor(R.color.md_teal_500)).show();
            }

            @Override
            public void onSavedFailed() {
                Snackbar.make(fabSavePic, "保存失败", Snackbar.LENGTH_SHORT).setActionTextColor
                        (ResourcesUtils.getColor(R.color.md_teal_500)).show();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mImageUrl = getIntent().getStringExtra("image_url");
        Log.d("jiejie", "----" + mImageUrl);
        assert mImageUrl != null;
        loadImage();
//        if(mImageUrl.contains("gif")){
//            loadGif();
//        }else {
//            loadImage();
//        }
    }

    /**
     * 加载gif
     */
    private void loadGif() {
        GlideApp.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        // 在这里添加一些图片加载完成的操作
                        pbPicBrowse.setVisibility(View.GONE);
                    }
                });

        Glide.with(this).asDrawable().load("")
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                    }
                });
        Glide.with(this).asBitmap().load("")
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                    }
                });
        Glide.with(this).asFile().load("")
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {

                    }
                });
    }

    private void loadImage() {
        GlideApp.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .into(new ImageViewTarget<Drawable>(pvPic) {
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                        m = resource;
                        pbPicBrowse.setVisibility(View.GONE);
                        pvPic.setImageDrawable(resource);
                    }
                });
    }
}
