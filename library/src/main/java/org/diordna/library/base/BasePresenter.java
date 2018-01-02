package org.diordna.library.base;

import org.diordna.library.ViewHelper;

import java.lang.ref.WeakReference;

/**
 * Presenter基类
 * @author DiorDNA
 */

public class BasePresenter <V extends IView> {

    protected ViewHelper helper;
    private WeakReference<V> mView = null;

    public BasePresenter() {

    }

    public BasePresenter(ViewHelper mViewHelper) {
        this.helper = mViewHelper;
    }

    /**
     * 绑定View
     * @param view
     */
    public void attach(V view) {
        mView = new WeakReference<>(view);
    }

    /**
     * 解绑View
     */
    public void detach() {

        if (mView == null) {
            mView.clear();
            mView = null;
        }
    }

    /**
     * 判断视图是否解绑
     * @return
     */
    public boolean isAttached() {

        return mView != null && mView.get() != null;
    }

    /**
     * 获取视图
     * @return
     */
    public V getView() {

        if (isAttached()) {
            return mView.get();
        }

        return null;
    }
}
