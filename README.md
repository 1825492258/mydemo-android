# mydemo-android
MyDemo
### 获取MD5: keytool -list -v -keystore app/keystore/release.jks

### 1.OkHttp-OkGo 网络请求
### https://github.com/jeasonlzy/okhttp-OkGo

### 2.ButterKnife 注释框架
### https://github.com/JakeWharton/butterknife

### 3.Glide
### https://github.com/bumptech/glide

### 4.CircleImageView 圆形头像
### https://github.com/hdodenhof/CircleImageView

### 5.BaseRecyclerViewAdapterHelper
### https://github.com/CymChad/BaseRecyclerViewAdapterHelper
### https://www.jianshu.com/p/b343fcff51b0

### 6.NineGridView展示图片的九宫格控件
### https://github.com/jeasonlzy/NineGridView

### 7.仿QQ空间，使用九宫格空间

### 8.生成二维码和扫描
### https://github.com/yipianfengye/android-zxingLibrary

### 9.样式比TabLayout多样的Tab库 FlycoTabLayout
### https://github.com/H07000223/FlycoTabLayout

### 10.Fragmentation 为"单Activity ＋ 多Fragment","多模块Activity + 多Fragment"架构而生
### https://github.com/YoKeyword/Fragmentation

### 11.UltraViewPager 轮播图使用
### https://github.com/alibaba/UltraViewPager

### 12.一种简单的倒计时写法
    private void initTimeDown(String my) {
        if (TextUtils.isEmpty(my) || !my.matches("^[0-9]*$")) return;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(Long.parseLong(my), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long temp = millisUntilFinished / 1000;
                long hours = temp / 3600;
                long minutes = (temp - (3600 * hours)) / 60;
                long seconds = temp - (3600 * hours) - (60 * minutes);
                timeHour.setText(hours > 9 ? "" + hours : "0" + hours);
                timeMinute.setText(minutes > 9 ? "" + minutes : "0" + minutes);
                timeSeconds.setText(seconds > 9 ? "" + seconds : "0" + seconds);
            }

            @Override
            public void onFinish() {
                timeHour.setText("00");
                timeMinute.setText("00");
                timeSeconds.setText("00");
            }
        }.start();
    }



