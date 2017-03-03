package com.updrv.commonlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 适配器基类
 * Created by lily on 2016/7/18.
 */

public class SimpleAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> list;

    public SimpleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public SimpleAdapter(Context mContext, List<T> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list!=null){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(list!=null){
            return position;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /**
     *
     * @param list
     */
    public void updateList(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

}
