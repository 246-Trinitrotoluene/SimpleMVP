package org.diordna.simplemvp.mvp.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.diordna.simplemvp.interfaces.Result;
import org.diordna.simplemvp.mvp.model.LoginModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * @author DiorDNA
 */

public class LoginBiz {

    private LoginModel model;
    private Result result;
    private StringCallback callback = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            result.fail("网络通讯异常");
        }

        @Override
        public void onResponse(String response, int id) {

            Map<String, String> params = getParams(response);
            String retCode = params.get("retcode");
            if ("success".equals(retCode)) {
                result.success();
            } else {
                result.fail(params.get("retmsg"));
            }
        }
    };

    public LoginBiz(LoginModel model) {
        this.model = model;
    }

    public void login(Result result) {

        this.result = result;
        OkHttpUtils.post()
                .url("http://www.250941.com:8080/test/api/login")
                .addParams("username", model.getUsername())
                .addParams("password", model.getPassword())
                .build()
                .execute(callback);
    }

    private Map<String, String> getParams(String response) {

        JSONObject jsonObject = JSON.parseObject(response);
        Map<String, String> params = new HashMap<>();
        for (Map.Entry entry : jsonObject.entrySet()) {
            params.put((String) entry.getKey(), (String) entry.getValue());
        }

        return params;
    }
}
