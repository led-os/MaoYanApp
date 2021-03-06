package com.gao.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gao.android.R;
import com.gao.android.model.Bean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GaoMatrix on 2016/9/8.
 */
public class MyAdapter extends AbstractAdapter {

    public MyAdapter(Context context, List<Bean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_adapter, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean bean = (Bean) mList.get(position);
        holder.title.setText(bean.getTitle());
        holder.desc.setText(bean.getDesc());
        holder.time.setText(bean.getTime());
        holder.phone.setText(bean.getPhone());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.phone)
        TextView phone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
