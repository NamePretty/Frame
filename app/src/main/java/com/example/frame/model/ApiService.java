package com.example.frame.model;

import com.example.frame.data.BaseResp;
import com.example.frame.data.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * retrofit api 接口类
 *
 * @packageName: com.example.frame.model
 * @fileName: ApiServer
 * @date: 2018/7/5  9:45
 * @author: zdj
 */

public interface ApiService {

    /**
     * 登录
     */
    @POST("login/restfulLogin")
    @FormUrlEncoded
    Observable<BaseResp<UserInfo>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResp<UserInfo>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
//
//    /**
//     * 获取 项目详细信息列表数据
//     */
//    @GET("project/list/{page}/json")
//    Observable<BaseResp<DemoDetailListBean>> getDemoDetailList(@Path("page") int page, @Query("cid") int id);
//
//    /**
//     *
//     * 查询搜索结果
//     * @param page
//     * @param key
//     * @return
//     */
//    @POST("/article/query/{page}/json")
//    Observable<BaseResp<HomePageArticleBean>> getSearechResult(@Path("page") int page, @Query("k") String key);

}
