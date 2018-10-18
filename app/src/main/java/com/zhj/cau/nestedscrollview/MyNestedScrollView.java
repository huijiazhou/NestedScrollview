package com.zhj.cau.nestedscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by zhj on 2018/10/8.
 */

public class MyNestedScrollView extends NestedScrollView {

    private int mTopViewHeight = 0;

    public MyNestedScrollView(@NonNull Context context) {
        super(context);

    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        Log.i("---myScrollview--", super.onStartNestedScroll(child, target, nestedScrollAxes) + "--onStartNestedScroll");
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }


    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
//        if (videoView.getVisibility() == View.GONE && headView.getVisibility() == View.GONE){
//
//        }
        Log.i("---myScrollview--",nestedScrollAxes+"onNestedScrollAccepted");
        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }


    @Override
    public void onStopNestedScroll(View target) {
        super.onStopNestedScroll(target);
        Log.i("---myScrollview--","onStopNestedScroll");
    }


    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            Log.i("---myScrollview--",dyConsumed+"onNestedScroll"+dyUnconsumed);

    }


    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop ) {
            scrollBy(0, dy);
            consumed[1] = dy;

        }
        Log.i("---myScrollview--",dy+"----onNestedPreScroll"+mTopViewHeight);
        super.onNestedPreScroll(target, dx, dy, consumed, type);
    }


    private int TOP_CHILD_FLING_THRESHOLD = 1;
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        //如果是recyclerView 根据判断第一个元素是哪个位置可以判断是否消耗
        //这里判断如果第一个元素的位置是大于TOP_CHILD_FLING_THRESHOLD的
        if (target instanceof RecyclerView && velocityY < 0) {

            RecyclerView recyclerView = (RecyclerView) target;
            final View firstChild = recyclerView.getChildAt(0);
            final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
            //滑到顶部不向下分发
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;

                            Log.i("---myScrollview--","----RecyclerView---"+consumed+"---"+childAdapterPosition);

        }
//        Log.i("---myScrollview--",consumed+"----onNestedPreScroll---"+velocityY+"--");
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }



    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
//        Log.i("---myScrollview--",velocityY+"onNestedPreFling");

        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    public void setTopViewHeight(int height){
        mTopViewHeight = height;
    }

    private View headView;
    private View videoView;
    public void setHeadView(View view){
        this.headView = view;
    }
    public void setVideoView(View view){
        this.videoView = view;
    }


}
