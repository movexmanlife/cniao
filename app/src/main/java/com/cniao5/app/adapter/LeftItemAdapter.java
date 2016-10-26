package com.cniao5.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cniao5.app.R;
import com.cniao5.app.application.App;
import com.cniao5.app.entity.LeftItemMenuEntity;
import com.cniao5.app.util.MenuDataUtil;

import java.util.List;

/**
 *
 */
public class LeftItemAdapter extends BaseAdapter {
    public LayoutInflater mInflater;
    private List<LeftItemMenuEntity> itemMenus;

    public LeftItemAdapter() {
        mInflater = LayoutInflater.from(App.getInstance());
        itemMenus = MenuDataUtil.getItemMenus();
    }

    @Override
    public int getCount() {
        return itemMenus != null ? itemMenus.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return itemMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _Holder = null;
        if (convertView == null) {
            _Holder = new Holder();
            convertView = mInflater.inflate(R.layout.item_left_menu_layout, null);
            _Holder.item_left_view_img = (ImageView) convertView.findViewById(R.id.item_left_view_img);
            _Holder.item_left_view_title = (TextView) convertView.findViewById(R.id.item_left_view_title);
            convertView.setTag(_Holder);
        } else {
            _Holder = (Holder) convertView.getTag();
        }
        _Holder.item_left_view_img.setImageResource(itemMenus.get(position).getLeftIcon());
        _Holder.item_left_view_title.setText(itemMenus.get(position).getTitle());
        return convertView;
    }

    private static class Holder {
        ImageView item_left_view_img;
        TextView item_left_view_title;
    }
}
