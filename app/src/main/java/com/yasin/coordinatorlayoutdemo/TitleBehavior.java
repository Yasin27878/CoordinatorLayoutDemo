package com.yasin.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TitleBehavior extends CoordinatorLayout.Behavior<View> {
    public static final String TAG = TitleBehavior.class.getSimpleName();
    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;
    public TitleBehavior() {
    }
    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float alpha = 1 - (dy / deltaY);
        Log.d(TAG, "onDependentViewChanged: "+alpha);
        child.setAlpha(alpha);

        return true;

    }


}
