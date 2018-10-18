package com.zhj.cau.nestedscrollview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import static com.zhj.cau.nestedscrollview.ScreenUtils.getScreenHeight;
import static com.zhj.cau.nestedscrollview.ScreenUtils.getScreenWidth;

/**
 * Created by zhj on 2018/10/8.
 */

public class OneFragment extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MyNestedScrollView scrollView = view.findViewById(R.id.nestedscrollview);
        final RecyclerView recyclerView = view.findViewById(R.id.rv_one);
        TabLayout tab_inner = view.findViewById(R.id.tab_inner);
        ViewPager viewPager = view.findViewById(R.id.vp_two);

        int screenWidth = getScreenWidth(getActivity()); // 获取屏幕宽度
        int screenHeight = getScreenHeight(getActivity()); // 获取屏幕宽度
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        lp.width = screenWidth;
        lp.height = screenHeight -DisplayUtil.dp2px(getActivity(),107);
        viewPager.setLayoutParams(lp);
        scrollView.setHeadView(recyclerView);


        List list = new ArrayList();
        for (int i=0;i<20;i++){
            list.add(i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleviewAdapter adapter = new RecycleviewAdapter(list);
        recyclerView.setAdapter(adapter);


        TwoFragment fragment = new TwoFragment();
        TwoFragment fragment0 = new TwoFragment();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragment);
        fragmentList.add(fragment0);
        List<String> mList_tile = new ArrayList<String>();
        mList_tile.add("list1");
        mList_tile.add("list2");
        tab_inner.setTabMode(TabLayout.MODE_FIXED);
        TabAdapter tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager(),fragmentList,mList_tile);
        viewPager.setAdapter(tabAdapter);
        viewPager.setCurrentItem(0);
        tab_inner.setupWithViewPager(viewPager);

        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                int headHeight = recyclerView.getHeight(); // 获取高度
                scrollView.setTopViewHeight(headHeight);
                return true;

            }
        });


    }
}
