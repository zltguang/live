package com.zltguang.xialalist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class NumberAdapter extends BaseAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    {
        for(int i = 0 ; i< 30 ; i++){
            list.add(String.valueOf(10000+ i));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //创建VIEW
        ViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.list_item,null);
            holder = new ViewHolder();
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            holder.ib_delete = (ImageButton) convertView.findViewById(R.id.ib_delete);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_text.setText(list.get(position));
        holder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    class ViewHolder {
        TextView tv_text;
        ImageButton ib_delete;

    }
}
