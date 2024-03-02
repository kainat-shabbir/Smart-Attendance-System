package com.iiuifyp.location.base.attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iiuifyp.location.base.attendance.R;
import com.iiuifyp.location.base.attendance.model.Query;

import java.util.List;

public class QueryAdapter extends BaseAdapter {

    List<Query> queryList;
    Context context;
    LayoutInflater inflater;

    public QueryAdapter(List<Query> queryList, Context context) {
        this.queryList = queryList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return queryList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_query, parent, false);
        TextView tv_item_query_name, tv_item_query_subject;
        tv_item_query_name = convertView.findViewById(R.id.tv_item_query_name);
        tv_item_query_subject = convertView.findViewById(R.id.tv_item_query_subject);
        tv_item_query_name.setText(queryList.get(position).getE_name());
        tv_item_query_subject.setText(queryList.get(position).getQ_title());
        return convertView;
    }
}
