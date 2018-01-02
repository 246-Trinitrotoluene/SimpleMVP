package org.diordna.library.initialize;

import org.diordna.library.ViewHelper;
import org.diordna.library.base.BasePresenter;
import org.diordna.library.base.IView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 初始化MVP框架
 * @author DiorDNA
 */
public class MVPInitialize <P extends BasePresenter<V>, V extends IView> {

    private P mPresenter;
    private V mView;
    private ViewHelper helper;

    public void init(Class<? extends BasePresenter<V>> clazz, V view) {

        this.init(clazz, view, null);
    }

    public void init(Class<? extends BasePresenter<V>> clazz, V view, ViewHelper helper) {

        this.mView = view;
        this.helper = helper;
        this.mPresenter = getPresenter(clazz);
        this.mPresenter.attach(this.mView);
    }

    /**
     * 获取Presenter
     * @return
     */
    public P getPresenter() {

        if (mPresenter != null) {
            return mPresenter;
        }

        return null;
    }

    private P getPresenter(Class clazz) {

        if (this.helper == null) {
            try {
                return (P) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return null;
        }

        try {
            Constructor c = clazz.getDeclaredConstructor(ViewHelper.class);
            c.setAccessible(true);
            return (P) c.newInstance(this.helper);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
