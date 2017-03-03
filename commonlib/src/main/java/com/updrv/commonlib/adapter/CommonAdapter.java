package com.updrv.commonlib.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * 适配器基类
 * Created by lily on 2016/7/18.
 */

public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> list;

    private int mLayoutId;

    public CommonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public CommonAdapter(Context mContext, List<T> list) {
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
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        parserItem(viewHolder, position);
        return convertView;
    }

    public abstract void parserItem(ViewHolder viewHolder,int position);

    /**
     *
     * @param list
     */
    public void updateList(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder{

        private View parentView;

        public ViewHolder setText(int rid, CharSequence text){
            ((TextView)parentView.findViewById(rid)).setText(text);
            return this;
        }

        public ViewHolder setTextColor(int rid, int color){
            ((TextView)parentView.findViewById(rid)).setTextColor(color);
            return this;
        }

        public ViewHolder setTextSize(int rid, int size){
            ((TextView)parentView.findViewById(rid)).setTextSize(size);
            return this;
        }

        public ViewHolder setImageResource(int rid, int source){
            ((ImageView)parentView.findViewById(rid)).setImageResource(source);
            return this;
        }

        public ViewHolder setOnClickListener(int rid, View.OnClickListener listener){
            parentView.findViewById(rid).setOnClickListener(listener);
            return this;
        }

        public ViewHolder setOnClickListener(View.OnClickListener listener){
            parentView.setOnClickListener(listener);
            return this;
        }

    }



}
