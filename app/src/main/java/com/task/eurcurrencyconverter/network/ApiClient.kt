package com.spotspal.fanartec.network

import com.spotspal.fanartec.core.MyApplication
import com.spotspal.fanartec.network.NetworkUtils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class ApiClient {


    private var retrofit: Retrofit? = null

    // String TOKEN="Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjRiYzRjMzQ4NGJjZWY3YjAzZGVjZWQ5N2QyNmVmNDY0YTA2NWExY2Q4ODRhMzFmMGQ3NWZiMWM0MzMwNzMwZjE5OTdiMmI1OGIwNDIzMDAyIn0.eyJhdWQiOiIyIiwianRpIjoiNGJjNGMzNDg0YmNlZjdiMDNkZWNlZDk3ZDI2ZWY0NjRhMDY1YTFjZDg4NGEzMWYwZDc1ZmIxYzQzMzA3MzBmMTk5N2IyYjU4YjA0MjMwMDIiLCJpYXQiOjE1NTE4ODA0NDIsIm5iZiI6MTU1MTg4MDQ0MiwiZXhwIjoxNTgzNTAyODQyLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.pa8ihwio7awBDOKUobU6ZJrNNcnLkqIfYWuAEhrgp_r6HTl8hpdc4s1mjChwAsFWPkAKeqGtofa-3Tk72cXQcqHjNytB6p7gXuqeZRhArsZ3ovfJ6SvQRz-ltk8vPDIyffV_KJ98GfcKgmd12S1mIbNsr_pXOZF5l6zyNmR6gClDlJjYtheEBD41kNIIgxoOmDwOk9EXf4jMDVRf0tytjp7yII1Vtlg1JuTc0cBXyeQVXePfBegdfRCYJToMat7TTCUQwNwl64UsQOLtSJ6kwxgqlZZdYJopRlJoSqSfZx_nIg--RAc0PLmpochRKz4TqWfKUFNKo1Nwd1ksr418QfRz93gW5xc73Zqcv9G3W4TMjya97AHw_sMUk_9jx1Xv_-xzQQ4sdSEI1qWOx8jWP9PprU_oT6XnMDa_l6XkrvPqX3wFUaZqBSmmbaEipuYx5kNGTeaoo_N8M_iI6zoB743DTQ2OZP733SfYDfhoD5i_0hpklGzqe80yGiAmJSfVVfh2ooUiERRGN2EN37o4rNY1c7A7vdjgZPux0XSmWWjsDqUlSp7HvsbfrFALjbFjutvC_0bzRfkH7UvnOeLzNoLtt0fwsz6ixb7d6eQF71PXqeqWYgXjUW9Pk_dAOhYH2PlktApZ_mhKNqqnuOzYF7ysxRGCP3r19OavhHzW26U";

    val client: Retrofit
        get() {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                builder.addInterceptor(
                    NetworkConnectionInterceptor(MyApplication.getInstance()!!.applicationContext)
                )
                builder.addInterceptor(interceptor)

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        builder.connectTimeout(40, TimeUnit.SECONDS)
                            .readTimeout(40, TimeUnit.SECONDS)
                            .writeTimeout(40, TimeUnit.SECONDS)
                            .build()
                    )
                    .build()

            }
            return retrofit!!
        }

}