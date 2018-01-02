package org.diordna.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 视图辅助工具
 * @author DiorDNA
 */

public class ViewHelper {

    private WeakReference<Object> view;
    private Map<Integer, ? extends View> components;

    private Map<String, Object> intentData;

    public ViewHelper() {
        components = new HashMap<>();
    }

    public ViewHelper(Object object) {

        this();

        if (object instanceof Activity || object instanceof Fragment) {
            view = new WeakReference<>(object);
            return;
        }

        throw new ClassCastException();
    }

    /**
     * 设置activity对象
     * @param activity
     */
    public void setView(Activity activity) {
        this.view = new WeakReference<Object>(activity);
    }

    /**
     * 设置fragment对象
     * @param fragment
     */
    public void setView(Fragment fragment) {
        this.view = new WeakReference<Object>(fragment);
    }

    /**
     * 获取context
     * @return
     */
    public Context getContext() {
        if (view.get() instanceof Activity) {
            return (Context) view.get();
        }

        if (view.get() instanceof Fragment) {
            Fragment fragment = (Fragment) view.get();
            return fragment.getContext();
        }

        return null;
    }

    /**
     * 获取控件
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T findView(int resId) {

        if (view == null) {
            return null;
        }

        if (view.get() instanceof Activity) {
            return findViewFromActivity((Activity) view.get(), resId);
        }

        if (view.get() instanceof Fragment) {
            return findViewFromFragment((Fragment) view.get(), resId);
        }

        return null;
    }

    public void setText(int resId, String text) {

        TextView tv = findView(resId);

        if (tv != null) {
            tv.setText(text);
        }
    }

    public String getText(int resId) {

        TextView tv = findView(resId);

        if (tv == null) {
            return "";
        }

        return tv.getText().toString();
    }

    public void setClick(View.OnClickListener listener, int... resIds) {

        for (int resId : resIds) {
            View view = findView(resId);
            if (view != null) {

                view.setOnClickListener(listener);
            }
        }
    }

    /**
     * 获取intent传送的数据
     * @param key
     * @return
     */
    public Object getIntentData(String key) {

        if (!(view.get() instanceof Activity)) {

            return null;
        }

        if (intentData == null) {

            intentData = new HashMap<>();

            Activity activity = (Activity) view.get();
            Intent intent = activity.getIntent();
            for (String k : intent.getExtras().keySet()) {

                Object data = intent.getExtras().get(k);
                intentData.put(k, data);
            }
        }

        return intentData.get(key);
    }

    /**
     * 从activity中获取控件
     * @param activity
     * @param resId
     * @param <T>
     * @return
     */
    private <T extends View> T findViewFromActivity(Activity activity, int resId) {

        if (activity == null) {
            return null;
        }

        T t = (T) components.get(resId);
        if (t == null) {
            t = activity.findViewById(resId);
        }
        return t;
    }

    /**
     * 从fragment中获取控件
     * @param fragment
     * @param resId
     * @param <T>
     * @return
     */
    private <T extends View> T findViewFromFragment(Fragment fragment, int resId) {

        if (fragment == null) {
            return null;
        }

        T t = (T) components.get(resId);
        if (t == null) {
            t = fragment.getView().findViewById(resId);
        }
        return t;
    }
}
