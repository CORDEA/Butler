package jp.cordea.butler.api

import jp.cordea.butler.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Yoshihiro Tanaka on 2016/07/08.
 */

interface IJenkinsService {

    @GET("api/json")
    fun getJenkins(@Query("tree") tree: String? = null): Call<Jenkins>

    @GET("view/{path}/api/json")
    fun getJobs(@Path("path") path: String, @Query("tree") tree: String? = null): Call<Jobs>

    @GET("job/{path}/api/json")
    fun getJobDetail(@Path("path") path: String, @Query("tree") tree: String? = null): Call<jp.cordea.butler.model.detail.JobDetail>

    @GET("job/{path}/api/json")
    fun getBuilds(@Path("path") path: String, @Query("tree") tree: String? = null): Call<jp.cordea.butler.model.overview.JobDetail>

    @GET("asynchPeople/api/json")
    fun getUsers(@Query("tree") tree: String? = null): Call<Users>

    @GET("computer/api/json")
    fun getComputers(@Query("tree") tree: String? = null): Call<Computers>

    @GET("computer/{path}/api/json")
    fun getComputerDetail(@Path("path") path: String, @Query("tree") tree: String? = null): Call<ComputerDetail>

    @POST("job/{job}/build")
    fun buildProject(@Path("job") job: String): Call<Void>

    @FormUrlEncoded
    @POST("job/{job}/buildWithParameters")
    fun buildWithParamProject(@Path("job") job: String, @FieldMap map: Map<String, String>): Call<Void>

}
