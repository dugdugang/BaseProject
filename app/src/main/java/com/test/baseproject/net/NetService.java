package com.test.baseproject.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: duguang
 * Date 2017/9/22 0022.
 */

public class NetService {
    private static NetApi netApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static NetApi getNetApi() {
        if (netApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.douban.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            netApi = retrofit.create(NetApi.class);

        }
        return netApi;
    }
}
