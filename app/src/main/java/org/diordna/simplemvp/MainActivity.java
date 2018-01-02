package org.diordna.simplemvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.diordna.library.ViewHelper;
import org.diordna.library.initialize.MVPInitialize;
import org.diordna.simplemvp.loading.ILoading;
import org.diordna.simplemvp.loading.impl.LoadingImpl;
import org.diordna.simplemvp.mvp.presenter.LoginPresenter;
import org.diordna.simplemvp.mvp.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private ViewHelper mViewHelper;
    private MVPInitialize<LoginPresenter, ILoginView> mvp;
    private ILoading loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mViewHelper = new ViewHelper(this);
        mvp = new MVPInitialize<>();
        mvp.init(LoginPresenter.class, this, mViewHelper);
        loading = new LoadingImpl(this);

        mViewHelper.setClick(this, R.id.login);
    }

    @Override
    public void showLoading() {
        loading.showLoading("正在登录中...");
    }

    @Override
    public void hideLoading() {
        loading.hideLoading();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                mvp.getPresenter().login();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mvp.getPresenter().detach();
    }
}
