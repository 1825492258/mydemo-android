package item.com.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.item.sdk.base.activity.BaseCompatActivity;
import com.ziyeyouhu.library.KeyboardTouchListener;
import com.ziyeyouhu.library.KeyboardUtil;

import item.com.demo.R;

import static com.ziyeyouhu.library.KeyboardUtil.INPUTTYPE_NUM_POINT;

public class KeyActivity extends BaseCompatActivity {

    public static void show(Activity activity) {
        Intent intent = new Intent(activity,KeyActivity.class);
        activity.startActivity(intent);
    }
    private LinearLayout rootView;
    private ScrollView scrollView;
    private EditText normalEd;
    private EditText specialEd;
    private KeyboardUtil keyboardUtil;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_key;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rootView = (LinearLayout) findViewById(R.id.root_view);
        scrollView = (ScrollView) findViewById(R.id.sv_main);

        normalEd = (EditText) findViewById(R.id.normal_ed);
        specialEd = (EditText) findViewById(R.id.special_ed);

        initMoveKeyBoard();
    }

    private void initMoveKeyBoard() {
        keyboardUtil = new KeyboardUtil(this, rootView, scrollView);
        keyboardUtil.setOtherEdittext(normalEd);
        // monitor the KeyBarod state
        keyboardUtil.setKeyBoardStateChangeListener(new KeyBoardStateListener());
        // monitor the finish or next Key
        keyboardUtil.setInputOverListener(new inputOverListener());
        specialEd.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_POINT, -1));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 ) {
            if(keyboardUtil.isShow){
                keyboardUtil.hideSystemKeyBoard();
                keyboardUtil.hideAllKeyBoard();
                keyboardUtil.hideKeyboardLayout();
            }else {
                return super.onKeyDown(keyCode, event);
            }

            return false;
        } else
            return super.onKeyDown(keyCode, event);
    }
    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
            Log.d("jiejie","state --" + state + "   editText" + editText.getText().toString());
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
            Log.d("jiejie","onClickType" + onclickType + "   " + "    editText" + editText.getText().toString());
//            System.out.println("editText" + editText.getText().toString());
        }
    }
}
