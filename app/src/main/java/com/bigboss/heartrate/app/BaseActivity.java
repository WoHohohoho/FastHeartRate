package com.bigboss.heartrate.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;


/**
 * 从所有Activity中抽出的基类，实现了Activity的通用操作，
 * 减少重复代码的编写
 */

public abstract class BaseActivity extends AppCompatActivity {
    // 透明标题栏类型
    public final static int TRANSPARENT_STATUS_BAR = 0;
    public final static int DEFAULT_STATUS_BAR = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBar();

        setContentView(getContentViewID());

        initViews();

        doAfterInitView();
    }

    /**
     * @return 需要的StatusBar类型.有TRANSPARENT_STATUS_BAR代表透明标题栏
     */
    public int getStatusBarType(){
        return DEFAULT_STATUS_BAR;
    }

    /**
     * 初始化Activity中的View
     */
    protected abstract void initViews();

    /**
     * 获得contentView的ID
     *
     * @return setContentView(int id)中的id
     */
    protected abstract int getContentViewID();

    /**
     * 在InitView结束之后调用。一般用来处理其他初始化事物
     */
    protected abstract void doAfterInitView();

    /**
     * 设置StatusBar
     */
    private void setTransparentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 设置StatusBar
     */
    private void setStatusBar() {
        switch (getStatusBarType()){
            case TRANSPARENT_STATUS_BAR:
                setTransparentStatusBar();
                break;
        }
    }

}