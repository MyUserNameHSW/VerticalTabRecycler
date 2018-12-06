package cn.izy1314.verticaltabrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Create by hsw
 * on 2018/12/3.
 */
public class LinerRecycleAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<TestData> mList;
    int recycleHeight;
    public LinerRecycleAdapter(Context context, List<TestData> list,int recycleHeight) {
        this.mContext = context;
        this.mList = list;
        this.recycleHeight= recycleHeight;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinerItemViewHolder(mContext,viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinerItemViewHolder vh = (LinerItemViewHolder) viewHolder;

        ViewGroup.LayoutParams layoutParams = vh.llMain.getLayoutParams();

        /**
         * 点击tablayout最后一项时 如果类目不足一页，
         * 调整此item为recyclerView的高度
         */
        if (i == mList.size() - 1){
            layoutParams.height = recycleHeight;
        }else {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        vh.llMain.setLayoutParams(layoutParams);

        /**
         * 类目的数量过少，高度不足recyclerView高度一半时会和tablayout造成bug
         * 可根据自己的实际情况进行调整
         * 这个方法有点low ，如有更好的办法，请替换
         */
        if (mList.get(i).getItemName() != null && mList.get(i).getItemName().size() > 6){
            vh.blockView.setVisibility(View.GONE);
        }else {
            vh.blockView.setVisibility(View.VISIBLE);
        }


        vh.tvName.setText(mList.get(i).getName());
        GridLayoutManager glm = new GridLayoutManager(mContext,3);
        vh.recyclerView.setLayoutManager(glm);
        GridRecycleAdapter adapter = new GridRecycleAdapter(mContext,mList.get(i).getItemName());
        vh.recyclerView.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
