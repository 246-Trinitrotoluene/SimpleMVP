package org.diordna.simplemvp.loading.impl;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import org.diordna.simplemvp.loading.ILoading;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author orange
 * @function 加载动画的实现类
 * 接口内的所有方法均运行于UI线程上
 */

public class LoadingImpl implements ILoading {

    private SweetAlertDialog loading;
    private Handler handler;
    private Runnable runnable;

    private int color;

    public LoadingImpl(Context context) {
        loading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        handler = new Handler(Looper.getMainLooper());

        color = Color.rgb(0x33, 0xb5, 0xe5);
    }

    @Override
    public void showLoading() {

        runnable = new Runnable() {
            @Override
            public void run() {
                loading.getProgressHelper().setBarColor(color);
                loading.setCancelable(false);
                loading.setCanceledOnTouchOutside(false);
                loading.show();
            }
        };
        handler.post(runnable);
    }

    @Override
    public void showLoading(final String text) {

        runnable = new Runnable() {
            @Override
            public void run() {
                loading.getProgressHelper().setBarColor(color);
                loading.setCancelable(false);
                loading.setCanceledOnTouchOutside(false);
                loading.setTitleText(text);
                loading.show();
            }
        };
        handler.post(runnable);
    }

    @Override
    public void hideLoading() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                loading.dismiss();
            }
        });
    }

    @Override
    public void setLoadingText(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                loading.setTitleText(text);
            }
        });
    }

    @Override
    public void destroy() {
        loading = null;

        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
