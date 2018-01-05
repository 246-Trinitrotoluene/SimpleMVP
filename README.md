# SimpleMVP

<p align="left">
    <a href="http://www.apache.org/licenses/LICENSE-2.0">
        <img src="http://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square" alt="License" />
    </a>
    <a>
        <img src="https://img.shields.io/badge/API-14-blue.svg?style=flat-square" alt="License" />
    </a>
</p>

> 针对mvp框架的封装

## 原理说明
MVP框架分为M、V、P三层，其中，需要在P层完成APP的功能实现，包括：在M层的具体业务逻辑，和在V层的视图显示。在本项目的封装中，主要聚焦在P层和V层的绑定上。
## 使用方法
1. 创建V层、P层，分别继承IView和BasePresenter。
```
V层：
```java
public interface ILoginView extends IView {

    void showLoading();
    void hideLoading();
}
```
```java
P层：
public class LoginPresenter extends BasePresenter<ILoginView> {
}
```
注：由于框架并未涉及到M层，因此在使用说明中并未提及，请按照业务逻辑自行实现。  
2. 在Activity或Fragment中初始化mvp框架
``` java
MVPInitialize<LoginPresenter, ILoginView> mvp = new MVPInitialize<>();
mvp.init(LoginPresenter.class, this, mViewHelper);
//mvp.init(LoginPresenter.class, this);
```
其中，mViewHelper是框架中自带的View工具类，根据具体情况选择是否传递。

### ViewHelper
提供了以下方法：
* setView(Activity), setView(Fragment)
设置View
* getContext()
获取上下文
* findView(Integer)
获取控件，不存在返回null
* setText(Integer, String), getText(Integer)
TextView及其子控件相关，设置或获取文本
* setClick(View.OnClickListener, int...)
设置N（N > 0）个控件的点击事件
* getIntentData(String)
获取Activity跳转传递的参数
## 更新说明
v1.0.0
* 初版