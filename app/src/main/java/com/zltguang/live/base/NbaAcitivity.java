package com.zltguang.live.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zltguang.live.R;
import com.zltguang.live.adapter.NbaAdapter;

import java.io.IOException;


/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class NbaAcitivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        initView();
        initData();
    }

    public void initData() {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder().url("http://op.juhe.cn/onebox/basketball/nba?key=4712829b547ff998ebec0b41c9608fbb").build();

        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                System.out.println(result);
                TextView textView = (TextView) findViewById(R.id.tv_shuju );
                textView.setText(result);
            }
        });

    }


    public void initView() {
//        ListView listView = (ListView) findViewById(R.id.lv_nba);
//        NbaAdapter nbaAdapter = new NbaAdapter();
//        listView.setAdapter(nbaAdapter);

    }
}
