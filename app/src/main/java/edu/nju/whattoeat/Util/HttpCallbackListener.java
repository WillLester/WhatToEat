package edu.nju.whattoeat.Util;

import android.net.http.HttpResponseCache;

import okhttp3.Response;

/**
 * Created by st0001 on 2017/3/29.
 */

public interface HttpCallbackListener {

    //服务器成功相应时调用
    void onFinish(Response response);

    //网络操作出现错误时调用
    void onError(Exception e);
}
