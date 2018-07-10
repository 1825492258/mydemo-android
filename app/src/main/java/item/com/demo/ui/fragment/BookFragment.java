package item.com.demo.ui.fragment;

import android.os.Bundle;

import com.item.sdk.base.fragment.BaseCompatFragment;

/**
 * Created by wuzongjie on 2018/7/5
 */
public class BookFragment extends BaseCompatFragment{

    public static BookFragment newInstance(){
        Bundle ars = new Bundle();
        BookFragment fragment = new BookFragment();
        fragment.setArguments(ars);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initUI(Bundle savedInstanceState) {

    }
}
