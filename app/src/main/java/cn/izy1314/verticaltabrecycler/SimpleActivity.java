package cn.izy1314.verticaltabrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SimpleActivity extends AppCompatActivity {

    private TextView tvName;
    private VerticalTabLayout tabLayout;
    private RecyclerView recyclerView;
    List<TestData> list;

    private GridRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        tvName = findViewById(R.id.tv_name);
        tabLayout = findViewById(R.id.tab_layout);
        recyclerView = findViewById(R.id.recycler_view);
        initData();
    }

    private void initData(){
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

        GridLayoutManager glm = new GridLayoutManager(getBaseContext(),3);
        recyclerView.setLayoutManager(glm);
        adapter = new GridRecycleAdapter(getBaseContext(),list.get(0).getItemName());
        tvName.setText(list.get(0).getName());
        recyclerView.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                adapter.setList(list.get(position).getItemName());
                tvName.setText(list.get(position).getName());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

}
