package com.example.danyue.h5;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by danyue on 2017/4/2.
 */
public class WebListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> links;

    public WebListAdapter(Context context, ArrayList<String> links) {
        if (links == null) links = new ArrayList<>();
        this.context = context;
        this.links = links;
    }

    public void addLists(String s){
        links.add(s);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return links == null ? 0 : links.size();
    }

    @Override
    public Object getItem(int position) {
        return links == null ? "" : links.get(position);
    }

    @Override
    public long getItemId(int position) {
        return links == null ? 0 : links.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_weblist_item, null);
            holder.webLink = (TextView) convertView.findViewById(R.id.item_link);
            holder.delLink = (Button) convertView.findViewById(R.id.del_link);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.webLink.setText(links.get(position));

        holder.delLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (links.size() > 0) {
                    links.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }
    public final class ViewHolder {
        public TextView webLink;
        public Button delLink;
    }


}
