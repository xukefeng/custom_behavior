package com.app.behavior.behavior.oppo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.app.behavior.App;
import com.app.behavior.BuildConfig;
import com.app.behavior.R;
import com.app.behavior.behavior.helper.HeaderScrollingViewBehavior;

import java.util.List;


public class OppoNewsTabBehavior extends HeaderScrollingViewBehavior {
    private static final String TAG = "UcNewsTabBehavior";

    public OppoNewsTabBehavior() {
    }

    public OppoNewsTabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        super.layoutChild(parent, child, layoutDirection);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "layoutChild:top" + child.getTop() + ",height" + child.getHeight());
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDependentViewChanged: dependency.getTranslationY():" + dependency.getTranslationY());
        }
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        float offsetRange = dependency.getTop() + getFinalHeight() - child.getTop();
        int headerOffsetRange = getHeaderOffsetRange();
        if (dependency.getTranslationY() == headerOffsetRange) {
            child.setTranslationY(offsetRange);
        } else if (dependency.getTranslationY() == 0) {
            child.setTranslationY(0);
        } else {
            child.setTranslationY((int) (dependency.getTranslationY() / (getHeaderOffsetRange() * 1.0f) * offsetRange));
        }
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
