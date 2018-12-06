package cn.izy1314.verticaltabrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Create by hsw
 * on 2018/12/3.
 */
public class GridRecycleAdapter extends RecyclerView.Adapter {
    Context context;
    List<String> list;
    public GridRecycleAdapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GirdItemViewHolder(context,viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GirdItemViewHolder vh = (GirdItemViewHolder) viewHolder;
        vh.tvName.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
