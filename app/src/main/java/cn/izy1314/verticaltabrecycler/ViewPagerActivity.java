package cn.izy1314.verticaltabrecycler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.youngkaaa.yviewpager.YViewPager;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class ViewPagerActivity extends AppCompatActivity {
    VerticalTabLayout tabLayout;
    List<TestData> list;
    YViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
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
        }
    }

    private void initView(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ItemFragmentAdapter adapter = new ItemFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        /**
         * 使用setupWithViewPager需要测试android4.4手机上是否有问题，可能会出现bug
         */
        tabLayout.setupWithViewPager(viewPager);

        //第二种结合的方法
//        for (TestData testData : list){
//            tabLayout.addTab(new QTabView(getBaseContext()).setTitle(
//                    new QTabView.TabTitle.Builder().setContent(testData.getName()).build()));
//        }
//
//        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabView tab, int position) {
//                viewPager.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabReselected(TabView tab, int position) {
//
//            }
//        });
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                tabLayout.setTabSelected(i);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
    }


    class ItemFragmentAdapter extends FragmentPagerAdapter{
        public ItemFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return ItemFragment.getInstance(list.get(i));
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
