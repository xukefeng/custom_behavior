package com.app.behavior;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutId();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView(savedInstanceState);
        initData();

    }

    protected void initData() {
    }

    /**
     * 无参数无返回值的通知的通知
     */
//    protected void refreshUI() {
//        FunctionManager.getInstance().addFunction(new FunctionNoParamNoResult(this.getClass().getName()) {
//            @Override
//            public void function() {
//                //这里执行刷新逻辑
//                refreshUICallback();
//            }
//        });
//    }

    protected void refreshUICallback() {
    }
}
