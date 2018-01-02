package org.diordna.simplemvp.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.diordna.library.ViewHelper;
import org.diordna.library.base.BasePresenter;
import org.diordna.simplemvp.NextActivity;
import org.diordna.simplemvp.R;
import org.diordna.simplemvp.mvp.biz.LoginBiz;
import org.diordna.simplemvp.interfaces.Result;
import org.diordna.simplemvp.mvp.model.LoginModel;
import org.diordna.simplemvp.mvp.view.ILoginView;

/**
 * @author DiorDNA
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter() {
    }

    public LoginPresenter(ViewHelper mViewHelper) {
        super(mViewHelper);
    }

    private Result result = new Result() {
        @Override
        public void success(Object... args) {
            getView().hideLoading();

            Intent intent = new Intent(helper.getContext(), NextActivity.class);
            helper.getContext().startActivity(intent);
            ((Activity) helper.getContext()).finish();
        }

        @Override
        public void fail(String errmsg) {
            getView().hideLoading();
            Toast.makeText(helper.getContext().getApplicationContext(),
                    errmsg,
                    Toast.LENGTH_SHORT).show();
        }
    };

    public void login() {

        String username = helper.getText(R.id.username);
        String password = helper.getText(R.id.password);
        LoginModel model = new LoginModel(username, password);

        getView().showLoading();
        new LoginBiz(model).login(result);
    }
}
