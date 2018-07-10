package item.com.demo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.item.sdk.base.fragment.BaseCompatFragment;

/**
 * Created by wuzongjie on 2018/7/5
 */
public class TextFragment extends BaseCompatFragment{
    @Override
    public void initUI(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    /**
     * 这是懒加载
     * @param savedInstanceState Bundle
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }
}
