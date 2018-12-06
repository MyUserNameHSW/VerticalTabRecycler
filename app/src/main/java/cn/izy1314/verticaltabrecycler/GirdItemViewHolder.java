package cn.izy1314.verticaltabrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Create by hsw
 * on 2018/12/3.
 */
public class GirdItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public GirdItemViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.vh_gird_item, parent, false));
        tvName = itemView.findViewById(R.id.tv_name);
    }
}
