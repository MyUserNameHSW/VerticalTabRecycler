package cn.izy1314.verticaltabrecycler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VerticalTabLayout tabLayout;
    List<TestData> list;

    private int currentPosition = 0;
    private LinearLayoutManager llm;
    private int recyclerHeight;
    private VerticalTabLayout.OnTabSelectedListener tabSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recycler_view);
        tabLayout = findViewById(R.id.tab_layout);
        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            TestData testData = new TestData();
            List<String> itemList = new ArrayList<>();

            for (int j = 0; j < (i%2 == 0 ? 6 : 10); j++) {
                itemList.add("二级类目" + i + "-" + j);
            }
            testData.setItemName(itemList);
            testData.setName("类目"+ i);
            list.add(testData);
            tabLayout.addTab(new QTabView(getBaseContext()).setTitle(
                    new QTabView.TabTitle.Builder().setContent(testData.getName()).build()));
        }
    }

    private void initView(){
        llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        /**
         *getHeight 需延时获取，有网络请求时可响应结束后同步获取，否则异步获取
         */
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerHeight = recyclerView.getHeight();
                Log.d("recycleHeight",recyclerHeight + "");
                LinerRecycleAdapter adapter = new LinerRecycleAdapter(getBaseContext(),list,recyclerHeight);
                recyclerView.setAdapter(adapter);
            }
        });


        tabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                if (currentPosition != position){
                    llm.scrollToPositionWithOffset(position,0);
                    currentPosition = position;
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        };

        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (int i= 0; i < llm.getItemCount();i++){
                    if (null != llm.findViewByPosition(i)){
                        if (llm.findViewByPosition(i).getTop() < recyclerHeight / 2 && llm.findViewByPosition(i).getBottom() > recyclerHeight / 2){
                            if (tabLayout.getSelectedTabPosition() != i){
                                currentPosition = i;
                                tabLayout.setTabSelected(i);
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0){
                    tabLayout.removeOnTabSelectedListener(tabSelectedListener);
                }else if (newState == 3){
                    tabLayout.removeOnTabSelectedListener(tabSelectedListener);
                }else {
                    tabLayout.addOnTabSelectedListener(tabSelectedListener);
                }
            }
        };
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        recyclerView.addOnScrollListener(onScrollListener);
    }
}
