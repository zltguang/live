package com.youoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_menu1;
    private RelativeLayout rl_menu2;
    private RelativeLayout rl_menu3;
    private Button bt_menu1;
    private Button bt_menu2;
    private boolean menu3showing = true;
    private boolean menu2showing = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_menu1 = (RelativeLayout) findViewById(R.id.rl_menu1);
        rl_menu2 = (RelativeLayout) findViewById(R.id.rl_menu2);
        rl_menu3 = (RelativeLayout) findViewById(R.id.rl_menu3);
        bt_menu1 = (Button) findViewById(R.id.bt_menu1);
        bt_menu2 = (Button) findViewById(R.id.bt_menu2);
        bt_menu2.setOnClickListener(this);
        bt_menu1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_menu2:
                if(Utils.HasAnimation()){
                    return;
                }
                if(menu3showing){
                    //隐藏menu3
                    Utils.hide(rl_menu3);
                }else{
                    //显示menu3
                    Utils.show(rl_menu3);
                }
                menu3showing = !menu3showing;
                break;
            case R.id.bt_menu1:
                if(Utils.HasAnimation()){
                    return;
                }
                if (menu3showing){
                    //先隐藏menu3
                    Utils.hide(rl_menu3);
                    menu3showing = false;
                    //在隐藏menu2(延时)
                    Utils.hide(rl_menu2,300);
                }else if(menu2showing){
                    //隐藏menu2
                Utils.hide(rl_menu2);
                }else{
                //显示menu2
                Utils.show(rl_menu2);

            }
                menu2showing= !menu2showing;
            break;


        }


    }
}
