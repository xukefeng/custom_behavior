package com.app.behavior.behavior.oppo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.app.behavior.App;
import com.app.behavior.BuildConfig;
import com.app.behavior.R;
import com.app.behavior.behavior.helper.HeaderScrollingViewBehavior;

import java.util.List;



public class OppoNewsContentBehavior extends HeaderScrollingViewBehavior {
    private static final String TAG = "UcNewsContentBehavior";
    private float lastX, lastY;

    public OppoNewsContentBehavior() {
    }

    public OppoNewsContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDependentViewChanged");
        }
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY((int) (-dependency.getTranslationY() / (getHeaderOffsetRange() * 1.0f) * getScrollRange(dependency)));

    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            lastX = ev.getX();
            lastY = ev.getY();
        }

        if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
            float currX = ev.getX();
            float currY = ev.getY();
            boolean horizontalScroll = Math.abs(currX - lastX) / Math.abs(currY - lastY) > 1;
            Log.e("xkff", "==========" + child.getTranslationY());
            if (horizontalScroll && child.getTranslationY() == 0)
                return true;
        }

        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    protected View findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (isDependOn(view))
                return view;
        }
        return null;
    }

    @Override
    protected int getScrollRange(View v) {
        if (isDependOn(v)) {
            return Math.max(0, v.getMeasuredHeight() - getFinalHeight());
        } else {
            return super.getScrollRange(v);
        }
    }

    private int getHeaderOffsetRange() {
        return App.getmContext().getResources().getDimensionPixelOffset(R.dimen.oppo_news_header_pager_offset);
    }

    private int getFinalHeight() {
        return App.getmContext().getResources().getDimensionPixelOffset(R.dimen.oppo_news_header_title_height);
    }


    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_uc_news_header_pager;
    }
}
