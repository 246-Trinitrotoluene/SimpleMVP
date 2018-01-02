package org.diordna.simplemvp.loading;

/**
 * @author orange
 * @function Loading动画接口
 */

public interface ILoading {
    void showLoading();
    void showLoading(String text);
    void hideLoading();
    void setLoadingText(String text);
    void destroy();
}
