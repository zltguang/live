package com.zltguang.xialalist;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import static android.R.attr.focusable;
import static android.R.attr.layout_below;
import static android.R.attr.viewportHeight;
import static android.R.attr.width;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_number;
    private ImageButton ib_arraw;
    private PopupWindow pop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        et_number = (EditText) findViewById(R.id.et_number);
        ib_arraw = (ImageButton) findViewById(R.id.ib_arraw);
        ib_arraw.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_arraw:
                shownumberlist();
                break;
        }


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void shownumberlist() {
        //创建popupwindow
        if(pop == null){
            ListView listView = CreateListView();
            int width = et_number.getWidth();
            int height = 1200;
            boolean focusable = true;
            pop = new PopupWindow(listView,width,height);
            pop.setFocusable(focusable);
            pop.setOutsideTouchable(true);
            pop.setBackgroundDrawable(new ColorDrawable());
            pop.showAsDropDown(et_number,0,0,Gravity.BOTTOM);
            pop.setAnimationStyle(R.style.popwin_animtor1_style);




        }

        //显示popupwindow

        View  anchor = et_number;
        int xoff = 0;
        int yoff = 0;
        pop.showAsDropDown(anchor, xoff, yoff);



    }

    private ListView CreateListView() {
        ListView listView = (ListView) View.inflate(this, R.layout.number, null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) parent.getItemAtPosition(position);
                et_number.setText(str);
                pop.dismiss();
            }
        });
        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter(new NumberAdapter());
        return listView;
    }


}
