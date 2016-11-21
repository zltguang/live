package com.zltguang.ad;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_viewpager;
    private int[] imageResIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
    };

    private String[] descs = {
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀",
    };
    private ImageView[] imageViews = new ImageView[imageResIds.length];
    private TextView tv_desc;
    private LinearLayout ll_dian;
    private View[] dots = new View[imageResIds.length];
    private View currentstate;
    private int size = imageResIds.length * 10000 *100;
    private int halfsize = size/2;
    private static final int SWITCH_IMAGE = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case SWITCH_IMAGE:
                    switchImage();
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp_viewpager = (ViewPager) findViewById(R.id.vp_viewpager);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        ll_dian = (LinearLayout) findViewById(R.id.ll_dian);


        for (int i = 0; i<imageViews.length;i++){
           imageViews[i] = new ImageView(this);
           imageViews[i].setBackgroundResource(imageResIds[i]);
           dots[i]  = new View(this);
           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5,5);
            if(i != 0){
                params.leftMargin = 5;
            }
           dots[i].setLayoutParams(params);
           dots[i].setBackgroundResource(R.drawable.selected_dot);
           ll_dian.addView(dots[i]);

       }
        setDescAndNot(0);
        vp_viewpager.setAdapter(new MyAdapter());
        vp_viewpager.setOffscreenPageLimit(4);
        vp_viewpager.setOnPageChangeListener(mOnPageChangeListener);
        vp_viewpager.setCurrentItem(halfsize);
    }




    protected void switchImage() {
       int current =  vp_viewpager.getCurrentItem();
        if(current == vp_viewpager.getAdapter().getCount()-1){
            current = halfsize ;
        }else{
            current++;
        }

        vp_viewpager.setCurrentItem(current);
        sendSwitchImageMessage();


    }

    @Override
    protected void onStart() {
        super.onStart();
        sendSwitchImageMessage();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeSwitchImageMessage();
    }

    private void sendSwitchImageMessage() {
        handler.sendEmptyMessageDelayed(SWITCH_IMAGE,3000);
    }
    private void removeSwitchImageMessage() {
        handler.removeMessages(SWITCH_IMAGE);
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            position = position % imageViews.length;
            setDescAndNot(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_IDLE){
                sendSwitchImageMessage();
            }else{
                removeSwitchImageMessage();
            }

        }
    };
    private void setDescAndNot(int position) {
        tv_desc.setText(descs[position]);
        if(currentstate != null){
            currentstate.setSelected(false); ;
        }
        currentstate = dots[position];
        currentstate.setSelected(true);
    }


    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
//            return imageViews.length;
            return size;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % imageViews.length;
            ImageView imageView = imageViews[position];
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
