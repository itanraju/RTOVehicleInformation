//package com.rtovehicleinformation.Retrofit;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class APIClient {
//    private static Retrofit retrofit = null;
//
//    public static Retrofit getClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
//                .connectTimeout(1, TimeUnit.MINUTES)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(15, TimeUnit.SECONDS)
//                .build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(AppConstant.BASEURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//
//        return retrofit;
//    }
//}
